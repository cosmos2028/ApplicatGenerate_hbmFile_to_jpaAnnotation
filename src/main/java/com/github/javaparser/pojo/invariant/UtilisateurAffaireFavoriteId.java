/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */

package fr.gouv.justice.cassiopee.invariant.affaire.model;

import fr.gouv.justice.cassiopee.administration.acteur.interne.model.ActeurInterne;
import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;

/**
 * clé composée pour l'association entre les utilisateurs et ses affaires favoris
 */
public class UtilisateurAffaireFavoriteId implements java.io.Serializable {

    /** Identifiant pour la serialisation */
    private static final long serialVersionUID = -2361553121165163487L;

    private static final HashCodeEqualsHelper<UtilisateurAffaireFavoriteId> HE_HELPER = HashCodeEqualsHelper.of(UtilisateurAffaireFavoriteId.class, UtilisateurAffaireFavoriteId::getActeurInterne,
            UtilisateurAffaireFavoriteId::getAffaire);

    /** L'acteur interne utilisé par la clé composite */
    private ActeurInterne acteurInterne;

    /** L'affaire utilisé par la clé composite */
    private Affaire affaire;

    /**
     * @return Returns the acteurInterne.
     */
    public ActeurInterne getActeurInterne() {
        return acteurInterne;
    }

    /**
     * @param acteurInterne
     *            The acteurInterne to set.
     */
    public void setActeurInterne(ActeurInterne acteurInterne) {
        this.acteurInterne = acteurInterne;
    }

    /**
     * @return Returns the affaire.
     */
    public Affaire getAffaire() {
        return affaire;
    }

    /**
     * @param affaire
     *            The affaire to set.
     */
    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
    }

    /**
     * constructeur par défaut nécessaire pour hibernate
     */
    public UtilisateurAffaireFavoriteId() {
        super();
    }

    /**
     * Constructeur
     *
     * @param acteurInterne
     * @param affaire
     */
    public UtilisateurAffaireFavoriteId(ActeurInterne acteurInterne, Affaire affaire) {
        super();
        this.acteurInterne = acteurInterne;
        this.affaire = affaire;
    }

    @Override
    public boolean equals(final Object other) {
        return HE_HELPER.areEqual(this, other);
    }

    @Override
    public int hashCode() {
        return HE_HELPER.getHashCode(this);
    }

}
