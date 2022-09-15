/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;
import fr.gouv.justice.cassiopee.commons.metier.model.AdresseComplete;

/**
 * Adresse de l'interpr�te
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
