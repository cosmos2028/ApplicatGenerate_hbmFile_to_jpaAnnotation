/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.util.Date;

import fr.gouv.justice.cassiopee.processus.parquet.model.IdentificationParquet;
import fr.gouv.justice.cassiopee.service.audience.model.Audience;

/**
 * La classe demande d'�dition repr�sente une demande d'�dition diff�r�e sans �v�nement. Cet objet est persistant.
 */
public class EditionDemandeDiffereeSansEvenement extends EditionDemandeDiffereeAvecDestinataire {

    /** identification parquet de l'affaire */
    private IdentificationParquet identificationParquet;

    /** l'audience associ�e � la demande d'�dition */
    private Audience audience;

    /** d�but intervalle de dates qui d�finit les objets � �diter */
    private Date intervalleDateDebut;

    /** fin intervalle de dates qui d�finit les objets � �diter */
    private Date intervalleDateFin;

    @Override
    public EditionDemandeDiffereeEnum getEditionDemandeDiffereeEnum() {
        return EditionDemandeDiffereeEnum.EDITION_DEMANDE_DIFFEREE_SANS_EVENEMENT;
    }

    /**
     * retourne l'audience associ�e � la demande d'�dition
     *
     * @return l'audience associ�e � la demande d'�dition
     */
    public Audience getAudience() {
        return audience;
    }

    /**
     * renseigne l'audience associ�e � la demande d'�dition
     *
     * @param audience
     *            l'audience associ�e � la demande d'�dition
     */
    public void setAudience(Audience audience) {
        this.audience = audience;
    }

    /**
     * retourne l'identification parquet associ�e � � la demande d'�dition
     *
     * @return l'identification parquet associ�e � � la demande d'�dition
     */
    public IdentificationParquet getIdentificationParquet() {
        return identificationParquet;
    }

    /**
     * renseigne l'identification parquet associ�e � � la demande d'�dition
     *
     * @param identificationParquet
     *            l'identification parquet associ�e � � la demande d'�dition
     */
    public void setIdentificationParquet(IdentificationParquet identificationParquet) {
        this.identificationParquet = identificationParquet;
    }

    /**
     * retourne le d�but de l'intervalle de dates qui d�finit les objets � �diter
     *
     * @return le d�but de l'intervalle de dates qui d�finit les objets � �diter
     */
    public Date getIntervalleDateDebut() {
        return intervalleDateDebut;
    }

    /**
     * renseigne le d�but de l'intervalle de dates qui d�finit les objets � �diter
     *
     * @param intervalleDateDebut
     *            le d�but de l'intervalle de dates qui d�finit les objets � �diter
     */
    public void setIntervalleDateDebut(Date intervalleDateDebut) {
        this.intervalleDateDebut = intervalleDateDebut;
    }

    /**
     * retourne la fin de l'intervalle de dates qui d�finit les objets � �diter
     *
     * @return le fin de l'intervalle de dates qui d�finit les objets � �diter
     */
    public Date getIntervalleDateFin() {
        return intervalleDateFin;
    }

    /**
     * renseigne la fin de l'intervalle de dates qui d�finit les objets � �diter
     *
     * @param intervalleDateFin
     *            la fin de l'intervalle de dates qui d�finit les objets � �diter
     */
    public void setIntervalleDateFin(Date intervalleDateFin) {
        this.intervalleDateFin = intervalleDateFin;
    }

}
