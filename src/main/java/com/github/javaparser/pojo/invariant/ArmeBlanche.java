/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

/**
 * Pi�ce � conviction de type Arme blanche.
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
