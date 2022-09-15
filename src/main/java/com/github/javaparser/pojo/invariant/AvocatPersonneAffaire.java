package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;

import fr.gouv.justice.cassiopee.administration.acteur.externe.model.AvocatExterne;
import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;
import fr.gouv.justice.cassiopee.commons.metier.model.IdLibelle;
import fr.gouv.justice.cassiopee.invariant.edition.model.DestinataireEdition;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Emetteur;

/**
 * AvocatPersonne de la personne de l'affaire.
 */
public class AvocatPersonneAffaire extends AvocatAffaire implements Serializable, Emetteur<Long>, IdLibelle,
        DestinataireEdition<Long> {

    private static final long serialVersionUID = 3476889349762083587L;

    private static final HashCodeEqualsHelper<AvocatPersonneAffaire> HE_HELPER = HashCodeEqualsHelper.of(AvocatPersonneAffaire.class, AvocatPersonneAffaire::getAvocatExterne);

    /**
     * Référence de l'avocatPersonneAffaire vers l'avocat externe
     */
    private AvocatExterne avocatExterne;

    /**
     * renvoie l'avocat externe qui correspond
     *
     * @return avocat externe
     */
    public AvocatExterne getAvocatExterne() {
        return avocatExterne;
    }

    /**
     * associe un avocat externe
     *
     * @param avocatExterne
     *            avocat externe
     */
    public void setAvocatExterne(AvocatExterne avocatExterne) {
        this.avocatExterne = avocatExterne;
    }

    @Override
    public int hashCode() {
        return HE_HELPER.getHashCode(this);
    }

    @Override
    public boolean equals(final Object other) {
        return HE_HELPER.areEqual(this, other);
    }

}
