package com.viajescarolina.shared.infra;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        String origin = requestContext.getHeaderString("Origin");
        
        if (origin != null && isAllowedOrigin(origin)) {
            responseContext.getHeaders().add("Access-Control-Allow-Origin", origin);
            responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
            responseContext.getHeaders().add("Access-Control-Allow-Headers", 
                "origin, content-type, accept, authorization, x-requested-with");
            responseContext.getHeaders().add("Access-Control-Allow-Methods", 
                "GET, POST, PUT, DELETE, OPTIONS, PATCH, HEAD");
            responseContext.getHeaders().add("Access-Control-Expose-Headers", 
                "Content-Type, Authorization, X-Total-Count");
            responseContext.getHeaders().add("Access-Control-Max-Age", "86400");
        }
    }

    private boolean isAllowedOrigin(String origin) {
        // Permitir localhost en cualquier puerto (desarrollo)
        if (origin.matches("http://localhost:\\d+")) {
            return true;
        }
        // Permitir ngrok
        if (origin.matches("https://.*\\.ngrok\\.io") || origin.matches("https://.*\\.ngrok-free\\.app")) {
            return true;
        }
        // Permitir dominios de producción
        if (origin.equals("https://web-viajes-carolina.web.app") ||
            origin.equals("https://web-viajes-carolina.firebaseapp.com") ||
            origin.equals("https://viajescarolina.com") ||
            origin.equals("https://www.viajescarolina.com")) {
            return true;
        }
        return false;
    }
}
