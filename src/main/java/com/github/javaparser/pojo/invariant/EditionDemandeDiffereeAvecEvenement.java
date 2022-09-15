package fr.gouv.justice.cassiopee.invariant.edition.model;

import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.PersonneEvenement;
import fr.gouv.justice.cassiopee.processus.parquet.model.IdentificationParquet;

/**
 * La classe demande d'édition représente une demande d'édition différée avec événement. Cet objet est persistant.
 */
public class EditionDemandeDiffereeAvecEvenement extends EditionDemandeDiffereeAvecDestinataire {

    /** identification parquet de l'affaire */
    private IdentificationParquet identificationParquet;

    /** événement associé à la demande d'édition */
    private Evenement evenement;

    /** personne associée à la demande d'édition */
    private Personne personne;

    /** personne événement associé à la demande d'édition */
    private PersonneEvenement personneEvenement;

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

    /**
     * renseigne la personne événement associée à la demande d'édition
     *
     * @return la personne événement associée à la demande d'édition
     */
    public PersonneEvenement getPersonneEvenement() {
        return personneEvenement;
    }

    /**
     * renseigne la personne événement associée à la demande d'édition
     *
     * @param personneEvenement
     *            la personne événement associée à la demande d'édition
     */
    public void setPersonneEvenement(PersonneEvenement personneEvenement) {
        this.personneEvenement = personneEvenement;
    }

    @Override
    public EditionDemandeDiffereeEnum getEditionDemandeDiffereeEnum() {
        return EditionDemandeDiffereeEnum.EDITION_DEMANDE_DIFFEREE_AVEC_EVENEMENT;
    }

}
