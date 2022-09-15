/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;
import fr.gouv.justice.cassiopee.commons.metier.model.AdresseComplete;

/**
 * Adresse de l'avocat
 */
public class AdresseAvocatPersonneAffaire extends AdresseComplete {

    private static final long serialVersionUID = 8136362745583200889L;

    private static final HashCodeEqualsHelper<AdresseAvocatPersonneAffaire> HE_HELPER = HashCodeEqualsHelper.of(AdresseAvocatPersonneAffaire.class, AdresseAvocatPersonneAffaire::getLieuDit, AdresseAvocatPersonneAffaire::getId);

    private String lieuDit;

    @Override
    public String getLieuDit() {
        return lieuDit;
    }

    @Override
    public void setLieuDit(String lieuDit) {
        this.lieuDit = lieuDit;
    }

    @Override
    public boolean equals(Object obj){
        return HE_HELPER.areEqual(this, obj);
    }

    @Override
    public int hashCode() {
        return HE_HELPER.getHashCode(this);
    }

}
