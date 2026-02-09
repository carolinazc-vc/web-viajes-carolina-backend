package com.viajescarolina.content.domain.port;

import com.viajescarolina.content.domain.home.CtaBlock;

import java.util.Optional;

/**
 * Puerto para acceso a datos de bloques CTA.
 */
public interface CtaRepository {

    /**
     * Busca el bloque CTA activo.
     * @return Optional con CtaBlock si existe uno activo
     */
    Optional<CtaBlock> findActiveCta();
}

