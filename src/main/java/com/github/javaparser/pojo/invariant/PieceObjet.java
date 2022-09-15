/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.math.BigDecimal;

/**
 * Pièce à conviction de type objet de petite, moyenne ou grande taille. Cela peut être un meuble, un objet d'art, etc
 * ... Ne pas confondre avec la classe Objet.
 */
public class PieceObjet extends PieceConviction {

    private static final long serialVersionUID = -4299687613359470387L;

    private String largueur;

    private String longueur;

    private String hauteur;

    private BigDecimal poids;

    private String marque;

    /**
     * @return Returns the hauteur.
     */
    public String getHauteur() {
        return hauteur;
    }

    /**
     * @param hauteur
     *            The hauteur to set.
     */
    public void setHauteur(String hauteur) {
        this.hauteur = hauteur;
    }

    /**
     * @return Returns the largueur.
     */
    public String getLargueur() {
        return largueur;
    }

    /**
     * @param largueur
     *            The largueur to set.
     */
    public void setLargueur(String largueur) {
        this.largueur = largueur;
    }

    /**
     * @return Returns the longueur.
     */
    public String getLongueur() {
        return longueur;
    }

    /**
     * @param longueur
     *            The longueur to set.
     */
    public void setLongueur(String longueur) {
        this.longueur = longueur;
    }

    /**
     * @return Returns the marque.
     */
    public String getMarque() {
        return marque;
    }

    /**
     * @param marque
     *            The marque to set.
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * @return Returns the poids.
     */
    public BigDecimal getPoids() {
        return poids;
    }

    /**
     * @param poids
     *            The poids to set.
     */
    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

}
