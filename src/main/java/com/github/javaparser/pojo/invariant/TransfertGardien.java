/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.io.Serializable;

/**
 * Permet de recenser toutes les informations concernant le transfert d'un dépôt vers un autre dépôt. Cette classe
 * permet de conserver le lien vers le dépôt initial
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
