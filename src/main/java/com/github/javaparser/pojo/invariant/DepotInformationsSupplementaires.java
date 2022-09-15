/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

/**
 * Objet temporaire utilis� pour l'impl�mentation qui regroupe un d�p�t et le nombre d'objets pr�sents dans ce d�p�t
 */
public class DepotInformationsSupplementaires implements java.io.Serializable {

    /**
     * Identifiant de s�rialisation
     */
    private static final long serialVersionUID = 1714852369658854712L;

    private Depot depot;

    private int nbObjets;

    /**
     * Retourne l'objet m�tier depot
     *
     * @return l'objet m�tier depot
     */
    public Depot getDepot() {
        return depot;
    }

    /**
     * Positionne l'objet m�tier depot
     *
     * @param depot
     *            l'objet m�tier depot
     */
    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    /**
     * Retourne le nombre d'objets (scell�s ou objets en gardiennage) de ce d�p�t
     *
     * @return le nombre d'objets (scell�s ou objets en gardiennage) de ce d�p�t
     */
    public int getNbObjets() {
        return nbObjets;
    }

    /**
     * Positionne le nombre d'objets (scell�s ou objets en gardiennage) de ce d�p�t
     *
     * @param nbObjets
     *            le nombre d'objets (scell�s ou objets en gardiennage) de ce d�p�t
     */
    public void setNbObjets(int nbObjets) {
        this.nbObjets = nbObjets;
    }
}
