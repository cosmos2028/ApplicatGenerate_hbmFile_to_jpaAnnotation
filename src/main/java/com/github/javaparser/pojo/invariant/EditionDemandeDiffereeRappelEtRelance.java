/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;

/**
 * La classe demande d'�dition repr�sente une demande d'�dition diff�r�e de type "Rappel Et Relance". Cet objet est
 * persistant.
 */
public class EditionDemandeDiffereeRappelEtRelance extends EditionDemandeDiffereeSansEvenement {

    /** �v�nement associ� � la demande d'�dition */
    private Evenement evenement;

    @Override
    public EditionDemandeDiffereeEnum getEditionDemandeDiffereeEnum() {
        return EditionDemandeDiffereeEnum.EDITION_DEMANDE_DIFFEREE_RAPPEL_ET_RELANCE;
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
}
