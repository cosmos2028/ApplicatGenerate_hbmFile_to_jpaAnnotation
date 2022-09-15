/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.CaracteristiqueType;

/**
 * identifiant d'une valeur d'une caractéristique d'un événement extérieur. L'identifiant est composé de l'événement et
 * du type de caractéristique.
 */
public class ValeurCaracteristiqueEvenementExterieurId implements java.io.Serializable {

    /** version pour la sérialisation */
    private static final long serialVersionUID = -4421412200626595465L;

    private static final HashCodeEqualsHelper<ValeurCaracteristiqueEvenementExterieurId> HE_HELPER = HashCodeEqualsHelper.of(ValeurCaracteristiqueEvenementExterieurId.class, ValeurCaracteristiqueEvenementExterieurId::getCaracteristiqueType,
            ValeurCaracteristiqueEvenementExterieurId::getEvenementExterieur);


    private EvenementExterieur evenementExterieur;

    private CaracteristiqueType caracteristiqueType;

    public ValeurCaracteristiqueEvenementExterieurId() {
        super();
    }

    public ValeurCaracteristiqueEvenementExterieurId(EvenementExterieur evenementExterieur,
            CaracteristiqueType caracteristiqueType) {
        this.evenementExterieur = evenementExterieur;
        this.caracteristiqueType = caracteristiqueType;
    }

    public CaracteristiqueType getCaracteristiqueType() {
        return caracteristiqueType;
    }

    public void setCaracteristiqueType(CaracteristiqueType caracteristiqueType) {
        this.caracteristiqueType = caracteristiqueType;
    }

    public EvenementExterieur getEvenementExterieur() {
        return evenementExterieur;
    }

    public void setEvenementExterieur(EvenementExterieur evenementExterieur) {
        this.evenementExterieur = evenementExterieur;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(final Object other) {
        return HE_HELPER.areEqual(this, other);
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return HE_HELPER.getHashCode(this);
    }

}
