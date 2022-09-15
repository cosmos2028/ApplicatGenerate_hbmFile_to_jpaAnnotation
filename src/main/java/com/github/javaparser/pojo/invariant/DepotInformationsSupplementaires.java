/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

/**
 * Objet temporaire utilisé pour l'implémentation qui regroupe un dépôt et le nombre d'objets présents dans ce dépôt
 */
public class DepotInformationsSupplementaires implements java.io.Serializable {

    /**
     * Identifiant de sérialisation
     */
    private static final long serialVersionUID = 1714852369658854712L;

    private Depot depot;

    private int nbObjets;

    /**
     * Retourne l'objet métier depot
     *
     * @return l'objet métier depot
     */
    public Depot getDepot() {
        return depot;
    }

    /**
     * Positionne l'objet métier depot
     *
     * @param depot
     *            l'objet métier depot
     */
    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    /**
     * Retourne le nombre d'objets (scellés ou objets en gardiennage) de ce dépôt
     *
     * @return le nombre d'objets (scellés ou objets en gardiennage) de ce dépôt
     */
    public int getNbObjets() {
        return nbObjets;
    }

    /**
     * Positionne le nombre d'objets (scellés ou objets en gardiennage) de ce dépôt
     *
     * @param nbObjets
     *            le nombre d'objets (scellés ou objets en gardiennage) de ce dépôt
     */
    public void setNbObjets(int nbObjets) {
        this.nbObjets = nbObjets;
    }
}
