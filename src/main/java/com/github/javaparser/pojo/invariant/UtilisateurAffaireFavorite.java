/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.affaire.model;

/**
 * Cette classe permet de repr�senter : (CF table AFF_UTILITAIRE_AFF_FAVORITE)
 */
public class UtilisateurAffaireFavorite implements java.io.Serializable {

    /** Identifiant pour la serialisation */
    private static final long serialVersionUID = -623380332882048045L;

    /** Identifiant de l'�l�ment repr�sentant un UtilisateurAffaireFavorite */
    private UtilisateurAffaireFavoriteId id;

    /**
     * retourne id.
     *
     * @return retourne id.
     */
    public UtilisateurAffaireFavoriteId getId() {
        return this.id;
    }

    /**
     * affecte id
     *
     * @param id
     */
    public void setId(UtilisateurAffaireFavoriteId id) {
        this.id = id;
    }

}
