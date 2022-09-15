package fr.gouv.justice.cassiopee.invariant.scelle.model;

import fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire;

/**
 * La classe "transfert affaire" permet de recenser toutes les informations concernant le transfert d'un dépôt d'une
 * affaire vers une autre affaire. Cette classe permet de conserver le lien de l'affaire à laquelle le dépôt se
 * rattachait initialement.
 */
public class TransfertAffaire extends Transfert implements java.io.Serializable {

    private static final long serialVersionUID = -4299687613359470387L;

    private Affaire affaireOrigine;

    public Affaire getAffaireOrigine() {
        return this.affaireOrigine;
    }

    public void setAffaireOrigine(Affaire affaire) {
        this.affaireOrigine = affaire;
    }

}
