package com.viajescarolina.shared.infra.http;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

/**
 * Filtro CORS personalizado para permitir peticiones desde http://localhost:4200/
 * Se aplica a todos los endpoints automáticamente.
 */
@Provider
public class CorsFilter implements ContainerResponseFilter {

    private static final Logger logger = Logger.getLogger(CorsFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        String origin = requestContext.getHeaderString("Origin");

        logger.debug("CORS Filter - Origin: " + origin);
        logger.debug("CORS Filter - Method: " + requestContext.getMethod());

        // Permitir localhost:4200 (frontend Angular)
        if (origin != null && (origin.equals("http://localhost:4200") ||
                               origin.equals("http://localhost:4200/"))) {

            MultivaluedMap<String, Object> headers = responseContext.getHeaders();

            // Headers CORS principales
            headers.add("Access-Control-Allow-Origin", "http://localhost:4200");
            headers.add("Access-Control-Allow-Credentials", "true");
            headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, PATCH, HEAD");
            headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization, Accept, X-Requested-With");
            headers.add("Access-Control-Expose-Headers", "Content-Type, Authorization, X-Total-Count");
            headers.add("Access-Control-Max-Age", "3600");

            logger.debug("✅ CORS headers added for origin: " + origin);
        }
    }
}

