/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

/**
 * Permet de recenser toutes les informations concernant le transfert d'un dépôt vers un autre dépôt. Cette classe
 * permet de conserver le lien vers le dépôt initial.
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
