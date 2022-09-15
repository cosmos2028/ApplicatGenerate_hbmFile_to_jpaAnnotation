/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;

/**
 * La classe demande d'édition représente une demande d'édition différée de type "Rappel Et Relance". Cet objet est
 * persistant.
 */
public class EditionDemandeDiffereeRappelEtRelance extends EditionDemandeDiffereeSansEvenement {

    /** événement associé à la demande d'édition */
    private Evenement evenement;

    @Override
    public EditionDemandeDiffereeEnum getEditionDemandeDiffereeEnum() {
        return EditionDemandeDiffereeEnum.EDITION_DEMANDE_DIFFEREE_RAPPEL_ET_RELANCE;
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
}
