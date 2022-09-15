/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.CaracteristiqueType;

/**
 * identifiant d'une valeur d'une caract�ristique d'un �v�nement ext�rieur. L'identifiant est compos� de l'�v�nement et
 * du type de caract�ristique.
 */
public class ValeurCaracteristiqueEvenementExterieurId implements java.io.Serializable {

    /** version pour la s�rialisation */
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
