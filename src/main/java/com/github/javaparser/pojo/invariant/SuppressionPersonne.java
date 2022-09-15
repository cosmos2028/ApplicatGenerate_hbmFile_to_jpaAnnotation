/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.processus.parquet.model.IdentificationParquet;

/**
 * Objet utilisé dans la gestion des suppressions logiques d'affaires
 */
public class SuppressionPersonne implements java.io.Serializable {

    /**
     * Numéro de serialisation
     */
    private static final long serialVersionUID = 2580125137885168717L;

    private Personne personne;

    private IdentificationParquet identificationParquet;

    /**
     * Constructeur de classe
     *
     * @param personne
     * @param identificationParquet
     */
    public SuppressionPersonne(Personne personne, IdentificationParquet identificationParquet) {
        this.personne = personne;
        this.identificationParquet = identificationParquet;
    }

    /**
     * Retourne une identificationParquet
     *
     * @return IdentificationParquet
     */
    public IdentificationParquet getIdentificationParquet() {
        return identificationParquet;
    }

    /**
     * Affecte une identification Parquet
     *
     * @param identificationParquet
     */
    public void setIdentificationParquet(IdentificationParquet identificationParquet) {
        this.identificationParquet = identificationParquet;
    }

    /**
     * Retourne une personne
     *
     * @return Personne
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * Affecte une personne
     *
     * @param personne
     */
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

}
