/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.io.Serializable;

import fr.gouv.justice.cassiopee.processus.parquet.model.IdentificationParquet;

/**
 * Classe Englobante intervenant dans la Gestion des Retours R�els
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
