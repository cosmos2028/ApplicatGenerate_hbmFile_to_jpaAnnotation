/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.io.Serializable;

import fr.gouv.justice.cassiopee.processus.parquet.model.IdentificationParquet;

/**
 * Classe Englobante intervenant dans la Gestion des Retours Réels
 */
public class EvenementRetour implements Serializable {

    private static final long serialVersionUID = -4299687613359470344L;

    private Evenement evenement;

    private IdentificationParquet identificationParquet;

    /**
     * Constructeur de classe
     *
     * @param evenement
     * @param identificationParquet
     */
    public EvenementRetour(Evenement evenement, IdentificationParquet identificationParquet) {
        super();
        this.evenement = evenement;
        this.identificationParquet = identificationParquet;
    }

    /**
     * @return retourne evenement.
     */
    public Evenement getEvenement() {
        return evenement;
    }

    /**
     * @param evenement
     *            affecte evenement.
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    /**
     * @return retourne identificationParquet.
     */
    public IdentificationParquet getIdentificationParquet() {
        return identificationParquet;
    }

    /**
     * @param identificationParquet
     *            affecte identificationParquet.
     */
    public void setIdentificationParquet(IdentificationParquet identificationParquet) {
        this.identificationParquet = identificationParquet;
    }
}
