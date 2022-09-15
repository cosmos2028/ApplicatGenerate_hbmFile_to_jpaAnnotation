/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

/**
 * Permet de recenser toutes les informations concernant le transfert d'un d�p�t vers un autre d�p�t. Cette classe
 * permet de conserver le lien vers le d�p�t initial.
 */
public class TransfertDepot extends Transfert implements java.io.Serializable {

    private static final long serialVersionUID = -4299687613359470387L;

    private Depot depotOrigine;

    /**
     *
     */
    public Depot getDepotOrigine() {
        return this.depotOrigine;
    }

    public void setDepotOrigine(Depot fkSceTraDepOri) {
        this.depotOrigine = fkSceTraDepOri;
    }

}
