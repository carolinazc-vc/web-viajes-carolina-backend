package com.viajescarolina.agency.app;

import com.viajescarolina.content.domain.home.AgencySummary;

/**
 * Servicio de aplicación para información de agencia.
 */
public interface AgencyService {

    /**
     * Obtiene un resumen de la información de la agencia para mostrar en el home.
     * @return AgencySummary con la información de la agencia
     */
    AgencySummary getAgencySummaryForHome();
}

