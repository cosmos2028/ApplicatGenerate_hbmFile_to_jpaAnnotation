/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire;
import fr.gouv.justice.cassiopee.processus.parquet.model.IdentificationParquet;

/**
 * Objet utilis� dans la gestion des suppressions logiques d'affaires
 */
public class SuppressionAffaire implements java.io.Serializable {

    /**
     * Num�ro de serialisation
     */
    private static final long serialVersionUID = 2580125137885168717L;

    private Affaire affaire;

    private IdentificationParquet identificationParquet;

    /**
     * Constructeur de classe
     *
     * @param affaire
     * @param identificationParquet
     */
    public SuppressionAffaire(Affaire affaire, IdentificationParquet identificationParquet) {
        this.affaire = affaire;
        this.identificationParquet = identificationParquet;
    }

    /**
     * Retourne une affaire
     *
     * @return Affaire
     */
    public Affaire getAffaire() {
        return affaire;
    }

    /**
     * Affecte une affaire
     *
     * @param affaire
     */
    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
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

}
