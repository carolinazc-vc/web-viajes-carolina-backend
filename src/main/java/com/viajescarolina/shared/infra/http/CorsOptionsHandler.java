package com.viajescarolina.shared.infra.http;

import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

/**
 * Manejador global de peticiones OPTIONS (CORS preflight).
 * Responde a todas las peticiones OPTIONS con headers CORS apropiados.
 */
@Path("{path:.*}")
@Produces(MediaType.APPLICATION_JSON)
public class CorsOptionsHandler {

    private static final Logger logger = Logger.getLogger(CorsOptionsHandler.class);

    @OPTIONS
    public Response handleCorsPreFlight() {
        logger.debug("✅ CORS preflight request received");

        return Response.ok()
                .header("Access-Control-Allow-Origin", "http://localhost:4200")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, PATCH, HEAD")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization, Accept, X-Requested-With")
                .header("Access-Control-Expose-Headers", "Content-Type, Authorization, X-Total-Count")
                .header("Access-Control-Max-Age", "3600")
                .header("Access-Control-Allow-Credentials", "true")
                .build();
    }
}

