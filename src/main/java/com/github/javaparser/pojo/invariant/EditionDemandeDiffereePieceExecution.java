/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.PeineOuMesure;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.processus.parquet.model.IdentificationParquet;

/**
 * La classe demande d'édition représente une demande d'édition d'une pièce d'exécution. Cet objet est persistant.
 */
public class EditionDemandeDiffereePieceExecution extends EditionDemandeDifferee {

    /** identification parquet de l'affaire */
    private IdentificationParquet identificationParquet;

    /** événement associé à la demande d'édition */
    private Evenement evenement;

    /** personne associée à la demande d'édition */
    private Personne personne;

    /** peine ou mesure associée à la demande d'édition */
    private PeineOuMesure peineOuMesure;

    @Override
    public EditionDemandeDiffereeEnum getEditionDemandeDiffereeEnum() {
        return EditionDemandeDiffereeEnum.EDITION_DEMANDE_DIFFEREE_PIECE_EXECUTION;
    }

    /**
     * retourne l'événement associé à la demande d'édition
     *
     * @return l'événement associé à la demande d'édition
     */
    public Evenement getEvenement() {
        return evenement;
    }

    /**
     * renseigne l'événement associé à la demande d'édition
     *
     * @param evenement
     *            l'événement associé à la demande d'édition
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
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
     * retourne la peine ou mesure associée à à la demande d'édition
     *
     * @return la peine ou mesure associée à à la demande d'édition
     */
    public PeineOuMesure getPeineOuMesure() {
        return peineOuMesure;
    }

    /**
     * renseigne la peine ou mesure associée à à la demande d'édition
     *
     * @param peineOuMesure
     *            la peine ou mesure associée à à la demande d'édition
     */
    public void setPeineOuMesure(PeineOuMesure peineOuMesure) {
        this.peineOuMesure = peineOuMesure;
    }

    /**
     * retourne la personne associée à la demande d'édition
     *
     * @return la personne associée à la demande d'édition
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * renseigne la personne associée à la demande d'édition
     *
     * @param personne
     *            personne associée à la demande d'édition
     */
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

}
