/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.PeineOuMesure;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.processus.parquet.model.IdentificationParquet;

/**
 * La classe demande d'�dition repr�sente une demande d'�dition d'une pi�ce d'ex�cution. Cet objet est persistant.
 */
public class EditionDemandeDiffereePieceExecution extends EditionDemandeDifferee {

    /** identification parquet de l'affaire */
    private IdentificationParquet identificationParquet;

    /** �v�nement associ� � la demande d'�dition */
    private Evenement evenement;

    /** personne associ�e � la demande d'�dition */
    private Personne personne;

    /** peine ou mesure associ�e � la demande d'�dition */
    private PeineOuMesure peineOuMesure;

    @Override
    public EditionDemandeDiffereeEnum getEditionDemandeDiffereeEnum() {
        return EditionDemandeDiffereeEnum.EDITION_DEMANDE_DIFFEREE_PIECE_EXECUTION;
    }

    /**
     * retourne l'�v�nement associ� � la demande d'�dition
     *
     * @return l'�v�nement associ� � la demande d'�dition
     */
    public Evenement getEvenement() {
        return evenement;
    }

    /**
     * renseigne l'�v�nement associ� � la demande d'�dition
     *
     * @param evenement
     *            l'�v�nement associ� � la demande d'�dition
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
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
     * retourne la peine ou mesure associ�e � � la demande d'�dition
     *
     * @return la peine ou mesure associ�e � � la demande d'�dition
     */
    public PeineOuMesure getPeineOuMesure() {
        return peineOuMesure;
    }

    /**
     * renseigne la peine ou mesure associ�e � � la demande d'�dition
     *
     * @param peineOuMesure
     *            la peine ou mesure associ�e � � la demande d'�dition
     */
    public void setPeineOuMesure(PeineOuMesure peineOuMesure) {
        this.peineOuMesure = peineOuMesure;
    }

    /**
     * retourne la personne associ�e � la demande d'�dition
     *
     * @return la personne associ�e � la demande d'�dition
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * renseigne la personne associ�e � la demande d'�dition
     *
     * @param personne
     *            personne associ�e � la demande d'�dition
     */
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

}
