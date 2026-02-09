package com.viajescarolina.shared.infra;

import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;

@Path("/")
public class CorsOptionsHandler {

    @OPTIONS
    @Path("{path:.*}")
    public Response handlePreflight(@PathParam("path") String path, @Context HttpHeaders headers) {
        String origin = headers.getHeaderString("Origin");
        
        if (origin != null && isAllowedOrigin(origin)) {
            return Response.ok()
                .header("Access-Control-Allow-Origin", origin)
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers", 
                    "origin, content-type, accept, authorization, x-requested-with")
                .header("Access-Control-Allow-Methods", 
                    "GET, POST, PUT, DELETE, OPTIONS, PATCH, HEAD")
                .header("Access-Control-Max-Age", "86400")
                .build();
        }
        
        return Response.ok().build();
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
