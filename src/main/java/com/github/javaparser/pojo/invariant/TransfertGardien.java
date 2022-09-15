/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.io.Serializable;

/**
 * Permet de recenser toutes les informations concernant le transfert d'un d�p�t vers un autre d�p�t. Cette classe
 * permet de conserver le lien vers le d�p�t initial
 */
public class TransfertGardien extends Transfert implements Serializable {

    private static final long serialVersionUID = -4299687613359470387L;

    private Gardiennage gardiennageOrigine;

    /**
     * @return gardiennage
     */
    public Gardiennage getGardiennageOrigine() {
        return this.gardiennageOrigine;
    }

    /**
     * @param gardiennage
     */
    public void setGardiennageOrigine(Gardiennage gardiennage) {
        this.gardiennageOrigine = gardiennage;
    }

}
