package com.viajescarolina.content.api.public_api;

import com.viajescarolina.content.api.dto.HomePageDtoMapper;
import com.viajescarolina.content.api.dto.HomePageResponse;
import com.viajescarolina.content.app.HomePageService;
import com.viajescarolina.content.domain.home.HomePageData;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Resource REST que expone el endpoint público del Home.
 */
@Path("/api/pages/home")
@Produces(MediaType.APPLICATION_JSON)
public class HomePageResource {

    @Inject
    HomePageService homePageService;

    /**
     * Obtiene el contenido completo del Home público.
     * @return HomePageResponse con todos los bloques
     */
    @GET
    public HomePageResponse getHomePage() {
        HomePageData homePageData = homePageService.getHomePage();
        return HomePageDtoMapper.toDto(homePageData);
    }
}

