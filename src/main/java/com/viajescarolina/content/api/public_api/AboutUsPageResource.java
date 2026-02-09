package com.viajescarolina.content.api.public_api;

import com.viajescarolina.agency.app.AboutUsPageService;
import com.viajescarolina.agency.domain.AboutUsPage;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 * Resource público para la página "Nosotros".
 */
@Path("/api/pages/about-us")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Páginas Públicas", description = "Endpoints para páginas del sitio web")
public class AboutUsPageResource {

    @Inject
    AboutUsPageService aboutUsPageService;

    @GET
    @Operation(
            summary = "Obtener página Nosotros",
            description = "Retorna todos los datos necesarios para renderizar la página Nosotros: hero, info de agencia, misión/visión, valores, historia, equipo, certificaciones, trust badges y galería."
    )
    public Response getAboutUsPage() {
        AboutUsPage page = aboutUsPageService.getAboutUsPage();
        return Response.ok(page).build();
    }
}
