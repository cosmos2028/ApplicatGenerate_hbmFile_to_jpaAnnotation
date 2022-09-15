/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.CaracteristiqueType;

/**
 * Clé primaire de la table/classe Caractéristique, utilisée par Hibernate.
 */
public class CaracteristiqueId implements java.io.Serializable {

    private static final long serialVersionUID = -7728734408127399588L;

    private static final HashCodeEqualsHelper<CaracteristiqueId> HE_HELPER = HashCodeEqualsHelper.of(CaracteristiqueId.class, CaracteristiqueId::getCaracteristiqueType,
            CaracteristiqueId::getEvenement);


    private Evenement evenement;

    private CaracteristiqueType caracteristiqueType;

    /**
     * @return l' Evenement auquel cette caractéristique est rattachée.
     */
    public Evenement getEvenement() {
        return this.evenement;
    }

    /**
     * @param evenement
     *            l' Evenement auquel cette caractéristique est rattachée.
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    /**
     * @return le CaracteristiqueType correspondant au type de cette caractéristique.
     */
    public CaracteristiqueType getCaracteristiqueType() {
        return this.caracteristiqueType;
    }

    /**
     * Valorise le CaracteristiqueType correspondant au type de cette caractéristique.
     *
     * @param caracteristiqueType
     *            le CaracteristiqueType voulu.
     */
    public void setCaracteristiqueType(CaracteristiqueType caracteristiqueType) {
        this.caracteristiqueType = caracteristiqueType;
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
