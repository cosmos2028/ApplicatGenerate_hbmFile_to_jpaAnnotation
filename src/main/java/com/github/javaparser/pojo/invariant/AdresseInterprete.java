/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;
import fr.gouv.justice.cassiopee.commons.metier.model.AdresseComplete;

/**
 * Adresse de l'interprète
 */
public class AdresseInterprete extends AdresseComplete {

    private static final long serialVersionUID = -3161812924527578314L;
    private static final HashCodeEqualsHelper<AdresseInterprete> HE_HELPER = HashCodeEqualsHelper.of(AdresseInterprete.class, AdresseInterprete::getLieuDit, AdresseInterprete::getId);

    @Override
    public boolean equals(Object obj){
        return HE_HELPER.areEqual(this, obj);
    }

    @Override
    public int hashCode() {
        return HE_HELPER.getHashCode(this);
    }

}
