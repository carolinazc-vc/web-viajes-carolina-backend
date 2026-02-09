package com.viajescarolina.agency.app;

import com.viajescarolina.agency.infra.jpa.entity.AgencyInfoEntity;
import com.viajescarolina.agency.infra.jpa.entity.AgencySocialLinkEntity;
import com.viajescarolina.agency.infra.jpa.entity.OfficeEntity;
import com.viajescarolina.agency.infra.jpa.entity.OfficeBusinessHourEntity;
import com.viajescarolina.content.domain.home.AgencySummary;
import com.viajescarolina.content.domain.home.AgencySummary.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.inject.Inject;

import java.util.List;

/**
 * Implementación del servicio de agencia.
 * Consulta datos reales de la base de datos.
 */
@ApplicationScoped
public class AgencyServiceImpl implements AgencyService {

    @Inject
    EntityManager entityManager;

    @Override
    public AgencySummary getAgencySummaryForHome() {
        // Obtener la primera agencia de la BD (usualmente hay solo una)
        List<AgencyInfoEntity> agencies = entityManager
                .createQuery("SELECT a FROM AgencyInfoEntity a ORDER BY a.id ASC", AgencyInfoEntity.class)
                .setMaxResults(1)
                .getResultList();

        if (agencies.isEmpty()) {
            // Si no hay agencia, retornar resumen vacío
            return new AgencySummary();
        }

        AgencyInfoEntity agencyEntity = agencies.get(0);
        AgencySummary summary = new AgencySummary();
        summary.setId(String.valueOf(agencyEntity.id));
        summary.setName(agencyEntity.name);
        summary.setDescription(agencyEntity.description);

        // Configurar contact desde la agencia
        AgencyContact contact = new AgencyContact();
        contact.setEmail(agencyEntity.primaryEmail);
        contact.setPhone(agencyEntity.primaryPhone);
        contact.setWhatsapp(agencyEntity.whatsappNumber);
        contact.setAddress(agencyEntity.mainAddress);
        contact.setCity(agencyEntity.city);
        contact.setCountry(agencyEntity.country);
        summary.setContact(contact);

        // Obtener redes sociales de la BD
        List<AgencySocialLinkEntity> socialLinks = entityManager
                .createQuery(
                        "SELECT s FROM AgencySocialLinkEntity s WHERE s.agency.id = :agencyId AND s.isActive = true ORDER BY s.sortOrder ASC",
                        AgencySocialLinkEntity.class
                )
                .setParameter("agencyId", agencyEntity.id)
                .getResultList();

        // Mapear redes sociales
        AgencySocial social = new AgencySocial();
        for (AgencySocialLinkEntity link : socialLinks) {
            if ("FACEBOOK".equalsIgnoreCase(link.platform)) {
                social.setFacebook(link.url);
            } else if ("INSTAGRAM".equalsIgnoreCase(link.platform)) {
                social.setInstagram(link.url);
            } else if ("TWITTER".equalsIgnoreCase(link.platform)) {
                social.setTwitter(link.url);
            } else if ("YOUTUBE".equalsIgnoreCase(link.platform)) {
                social.setYoutube(link.url);
            }
        }
        summary.setSocial(social);

        // Obtener oficina principal para horarios de negocio
        List<OfficeEntity> offices = entityManager
                .createQuery(
                        "SELECT o FROM OfficeEntity o WHERE o.agency.id = :agencyId AND o.isMainOffice = true AND o.isActive = true",
                        OfficeEntity.class
                )
                .setParameter("agencyId", agencyEntity.id)
                .setMaxResults(1)
                .getResultList();

        if (!offices.isEmpty()) {
            OfficeEntity mainOffice = offices.get(0);

            // Obtener horarios de la oficina principal
            List<OfficeBusinessHourEntity> hours = entityManager
                    .createQuery(
                            "SELECT h FROM OfficeBusinessHourEntity h WHERE h.office.id = :officeId ORDER BY h.id ASC",
                            OfficeBusinessHourEntity.class
                    )
                    .setParameter("officeId", mainOffice.id)
                    .getResultList();

            // Construir strings de horarios
            AgencyBusinessHours businessHours = new AgencyBusinessHours();
            for (OfficeBusinessHourEntity hour : hours) {
                String timeRange = hour.isClosed ? "Cerrado" : (hour.openTime + " - " + hour.closeTime);

                if ("LUNES".equalsIgnoreCase(hour.dayOfWeek) || "MARTES".equalsIgnoreCase(hour.dayOfWeek) ||
                    "MIÉRCOLES".equalsIgnoreCase(hour.dayOfWeek) || "JUEVES".equalsIgnoreCase(hour.dayOfWeek) ||
                    "VIERNES".equalsIgnoreCase(hour.dayOfWeek)) {
                    businessHours.setWeekdays(timeRange);
                } else if ("SÁBADO".equalsIgnoreCase(hour.dayOfWeek)) {
                    businessHours.setSaturday(timeRange);
                } else if ("DOMINGO".equalsIgnoreCase(hour.dayOfWeek)) {
                    businessHours.setSunday(timeRange);
                }
            }
            summary.setBusinessHours(businessHours);
        }

        return summary;
    }
}

