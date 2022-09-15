package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.math.BigDecimal;

public class PieceObjetEnGardiennage extends ObjetEnGardiennage {
    /**
     *
     */
    private static final long serialVersionUID = -5037531060554802810L;

    private String largeur;

    private String longueur;

    private String hauteur;

    private BigDecimal poids;

    /**
     * Accesseur de l'attribut hauteur
     *
     * @return Hauteur de la pièce objet
     */
    public String getHauteur() {
        return hauteur;
    }

    /**
     * Modification de la hauteur de la pièce objet
     *
     * @param hauteur
     *            Hauteur de la pièce objet
     */
    public void setHauteur(String hauteur) {
        this.hauteur = hauteur;
    }

    /**
     * Accesseur de l'attribut largeur
     *
     * @return Largeur de la pièce objet
     */
    public String getLargeur() {
        return largeur;
    }

    /**
     * Modification de la largeur de la pièce objet
     *
     * @param largeur
     *            Largeur de la pièce objet
     */
    public void setLargeur(String largeur) {
        this.largeur = largeur;
    }

    /**
     * Accesseur de l'attribut longueur
     *
     * @return Longueur de la pièce objet
     */
    public String getLongueur() {
        return longueur;
    }

    /**
     * Modificaation de la longueur de la pièce objet
     *
     * @param longueur
     *            Longueur de la pièce objet
     */
    public void setLongueur(String longueur) {
        this.longueur = longueur;
    }

    /**
     * Accesseur de l'attribut poids
     *
     * @return Poids de la pièce objet
     */
    public BigDecimal getPoids() {
        return poids;
    }

    /**
     * Modificaation du poids de la pièce objet
     *
     * @param poids
     *            Poids de la pièce objet
     */
    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }
}
