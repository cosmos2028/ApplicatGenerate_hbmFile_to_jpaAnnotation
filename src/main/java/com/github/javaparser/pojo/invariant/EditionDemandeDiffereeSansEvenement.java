/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.util.Date;

import fr.gouv.justice.cassiopee.processus.parquet.model.IdentificationParquet;
import fr.gouv.justice.cassiopee.service.audience.model.Audience;

/**
 * La classe demande d'édition représente une demande d'édition différée sans événement. Cet objet est persistant.
 */
public class EditionDemandeDiffereeSansEvenement extends EditionDemandeDiffereeAvecDestinataire {

    /** identification parquet de l'affaire */
    private IdentificationParquet identificationParquet;

    /** l'audience associée à la demande d'édition */
    private Audience audience;

    /** début intervalle de dates qui définit les objets à éditer */
    private Date intervalleDateDebut;

    /** fin intervalle de dates qui définit les objets à éditer */
    private Date intervalleDateFin;

    @Override
    public EditionDemandeDiffereeEnum getEditionDemandeDiffereeEnum() {
        return EditionDemandeDiffereeEnum.EDITION_DEMANDE_DIFFEREE_SANS_EVENEMENT;
    }

    /**
     * retourne l'audience associée à la demande d'édition
     *
     * @return l'audience associée à la demande d'édition
     */
    public Audience getAudience() {
        return audience;
    }

    /**
     * renseigne l'audience associée à la demande d'édition
     *
     * @param audience
     *            l'audience associée à la demande d'édition
     */
    public void setAudience(Audience audience) {
        this.audience = audience;
    }

    /**
     * retourne l'identification parquet associée à à la demande d'édition
     *
     * @return l'identification parquet associée à à la demande d'édition
     */
    public IdentificationParquet getIdentificationParquet() {
        return identificationParquet;
    }

    /**
     * renseigne l'identification parquet associée à à la demande d'édition
     *
     * @param identificationParquet
     *            l'identification parquet associée à à la demande d'édition
     */
    public void setIdentificationParquet(IdentificationParquet identificationParquet) {
        this.identificationParquet = identificationParquet;
    }

    /**
     * retourne le début de l'intervalle de dates qui définit les objets à éditer
     *
     * @return le début de l'intervalle de dates qui définit les objets à éditer
     */
    public Date getIntervalleDateDebut() {
        return intervalleDateDebut;
    }

    /**
     * renseigne le début de l'intervalle de dates qui définit les objets à éditer
     *
     * @param intervalleDateDebut
     *            le début de l'intervalle de dates qui définit les objets à éditer
     */
    public void setIntervalleDateDebut(Date intervalleDateDebut) {
        this.intervalleDateDebut = intervalleDateDebut;
    }

    /**
     * retourne la fin de l'intervalle de dates qui définit les objets à éditer
     *
     * @return le fin de l'intervalle de dates qui définit les objets à éditer
     */
    public Date getIntervalleDateFin() {
        return intervalleDateFin;
    }

    /**
     * renseigne la fin de l'intervalle de dates qui définit les objets à éditer
     *
     * @param intervalleDateFin
     *            la fin de l'intervalle de dates qui définit les objets à éditer
     */
    public void setIntervalleDateFin(Date intervalleDateFin) {
        this.intervalleDateFin = intervalleDateFin;
    }

}
