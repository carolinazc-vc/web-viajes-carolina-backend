package com.viajescarolina.content.api.public_api;

import com.viajescarolina.agency.app.ContactPageService;
import com.viajescarolina.agency.domain.ContactPage;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 * Resource público para la página "Contacto".
 */
@Path("/api/pages/contact")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Páginas Públicas", description = "Endpoints para páginas del sitio web")
public class ContactPageResource {

    @Inject
    ContactPageService contactPageService;

    @GET
    @Operation(
            summary = "Obtener página Contacto",
            description = "Retorna todos los datos necesarios para renderizar la página Contacto: hero, info de agencia, oficinas con horarios, configuración del formulario y redes sociales."
    )
    public Response getContactPage() {
        ContactPage page = contactPageService.getContactPage();
        return Response.ok(page).build();
    }
}
