package com.viajescarolina.core.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

/**
 * Filtro CORS para ngrok dinámico.
 *
 * Quarkus en application.yaml NO soporta wildcards (*.ngrok.io),
 * así que este filtro agrega soporte dinámico para:
 * - http://*.ngrok.io
 * - https://*.ngrok.io
 * - http://*.ngrok-free.app
 * - https://*.ngrok-free.app
 *
 * Complementa la configuración de application.yaml sin conflictuar.
 */
@Provider
@ApplicationScoped
public class CorsFilter implements ContainerResponseFilter {

    private static final Logger logger = Logger.getLogger(CorsFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        String origin = requestContext.getHeaderString("Origin");
        String method = requestContext.getMethod();

        // Si no hay origin, ignorar (no es petición CORS)
        if (origin == null || origin.isEmpty()) {
            return;
        }

        // Validar si es ngrok (HTTP o HTTPS)
        if (isNgrokOrigin(origin)) {
            addCorsHeaders(responseContext, origin);

            // Para peticiones OPTIONS (preflight), retornar 200 OK
            if ("OPTIONS".equalsIgnoreCase(method)) {
                responseContext.setStatus(Response.Status.OK.getStatusCode());
                logger.debug("✅ CORS preflight (OPTIONS) permitido para: " + origin);
            } else {
                logger.debug("✅ CORS permitido para " + method + " desde: " + origin);
            }
        } else {
            // No es ngrok, dejar que Quarkus YAML lo maneje (localhost)
            if (logger.isDebugEnabled()) {
                logger.debug("⚪ CORS origin no es ngrok: " + origin + " (Quarkus YAML lo maneja)");
            }
        }
    }

    /**
     * Agrega los headers CORS necesarios.
     */
    private void addCorsHeaders(ContainerResponseContext responseContext, String origin) {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", origin);
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, PATCH, OPTIONS");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "Origin, Content-Type, Authorization, X-Requested-With, Accept");
        responseContext.getHeaders().add("Access-Control-Expose-Headers", "Content-Type, Authorization");
        responseContext.getHeaders().add("Access-Control-Max-Age", "86400");
    }

    /**
     * Valida si el origen es ngrok.
     * Soporta:
     * - http://abc123.ngrok.io
     * - https://abc123.ngrok.io
     * - http://abc123.ngrok-free.app
     * - https://abc123.ngrok-free.app
     */
    private boolean isNgrokOrigin(String origin) {
        if (origin == null || origin.isEmpty()) {
            return false;
        }

        // Remover protocolo para validación
        String host = origin.toLowerCase();

        // Remover http:// o https://
        if (host.startsWith("https://")) {
            host = host.substring(8);
        } else if (host.startsWith("http://")) {
            host = host.substring(7);
        }

        // Remover puerto si existe
        if (host.contains(":")) {
            host = host.substring(0, host.indexOf(":"));
        }

        // Validar que sea ngrok
        return host.contains(".ngrok.io") || host.contains(".ngrok-free.app");
    }
}

