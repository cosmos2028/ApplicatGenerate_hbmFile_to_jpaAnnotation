package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.io.Serializable;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;

/**
 * Clé primaire pour LienEvenementMotif.
 */
public class LienEvenementAnterieurPK implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8079853939077568950L;

    private static final HashCodeEqualsHelper<LienEvenementAnterieurPK> HE_HELPER = HashCodeEqualsHelper.of(LienEvenementAnterieurPK.class, LienEvenementAnterieurPK::getEvenementAnterieur,
            LienEvenementAnterieurPK::getEvenementPosterieur);


    private Evenement evenementAnterieur;

    private Evenement evenementPosterieur;

    /**
     * @return Returns the evenementAnterieur.
     */
    public Evenement getEvenementAnterieur() {
        return evenementAnterieur;
    }

    /**
     * @param evenementAnterieur
     *            The evenementAnterieur to set.
     */
    public void setEvenementAnterieur(Evenement evenementAnterieur) {
        this.evenementAnterieur = evenementAnterieur;
    }

    /**
     * @return Returns the evenementPosterieur.
     */
    public Evenement getEvenementPosterieur() {
        return evenementPosterieur;
    }

    /**
     * @param evenementPosterieur
     *            The evenementPosterieur to set.
     */
    public void setEvenementPosterieur(Evenement evenementPosterieur) {
        this.evenementPosterieur = evenementPosterieur;
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
