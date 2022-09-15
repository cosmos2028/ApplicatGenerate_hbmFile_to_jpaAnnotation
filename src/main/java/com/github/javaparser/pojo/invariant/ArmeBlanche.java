/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

/**
 * Pièce à conviction de type Arme blanche.
 */
public class ArmeBlanche extends Arme {

    private static final long serialVersionUID = -4299687613359470387L;

    private Float longueur;

    /**
     * @return Returns the longueur.
     */
    public Float getLongueur() {
        return longueur;
    }

    /**
     * @param longueur
     *            The longueur to set.
     */
    public void setLongueur(Float longueur) {
        this.longueur = longueur;
    }

}
