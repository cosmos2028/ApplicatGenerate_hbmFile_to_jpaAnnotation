/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.affaire.model;

/**
 * Cette classe permet de représenter : (CF table AFF_UTILITAIRE_AFF_FAVORITE)
 */
public class UtilisateurAffaireFavorite implements java.io.Serializable {

    /** Identifiant pour la serialisation */
    private static final long serialVersionUID = -623380332882048045L;

    /** Identifiant de l'élément représentant un UtilisateurAffaireFavorite */
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
