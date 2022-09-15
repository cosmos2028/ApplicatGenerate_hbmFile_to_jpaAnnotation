/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.infraction.model;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;

/**
 * Identifie les �l�ments factuels li�s � une infraction routi�re li�e � la vitesse:
 * <ul>
 * <li>caract�ristiques d'�quipement de terrain: num�ro, condition de prise de vue, point de rep�re kilom�trique,
 * pr�cision du lieu.</li>
 * <li>caract�ristiques de l'infraction: heure, vitesse limit�e autoris�e, vitesse retenue.</li>
 * </ul>
 */
public class Vitesse implements java.io.Serializable, Cloneable {
    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -3954063295034472062L;

    private static final HashCodeEqualsHelper<Vitesse> HE_HELPER = HashCodeEqualsHelper.of(Vitesse.class, Vitesse::getValeurMesure, Vitesse::getValeurRetenue);

    /** Vitesse mesur�e */
    private Float valeurMesure;

    /** Vitesse limite autoris�ee */
    private Short valeurLimiteAutorisee;

    /** Vitesse retenue */
    private Short valeurRetenue;

    /** Equipement de mesure */
    private Equipement equipement;

    /**
     * Indique la vitesse limite autoris�e
     *
     * @return la vitesse limite autoris�e
     */
    public Short getValeurLimiteAutorisee() {
        return valeurLimiteAutorisee;
    }

    /**
     * Renseigne la vitesse limite autoris�e
     *
     * @param valeurLimiteAutorisee
     *            la vitesse limite autoris�e
     */
    public void setValeurLimiteAutorisee(Short valeurLimiteAutorisee) {
        this.valeurLimiteAutorisee = valeurLimiteAutorisee;
    }

    /**
     * Indique la vitesse mesur�e
     *
     * @return la vitesse mesur�e
     */
    public Float getValeurMesure() {
        return valeurMesure;
    }

    /**
     * Renseigne la vitesse mesur�e
     *
     * @param valeurMesure
     *            la vitesse mesur�e
     */
    public void setValeurMesure(Float valeurMesure) {
        this.valeurMesure = valeurMesure;
    }

    /**
     * Indique la vitesse retenue
     *
     * @return la vitesse retenue
     */
    public Short getValeurRetenue() {
        return valeurRetenue;
    }

    /**
     * Renseigne la vitesse retenue
     *
     * @param valeurRetenue
     *            la vitesse retenue
     */
    public void setValeurRetenue(Short valeurRetenue) {
        this.valeurRetenue = valeurRetenue;
    }

    /**
     * Indique l'�quipement de mesure dans le cas d'une infraction routi�re
     */
    public Equipement getEquipement() {
        return equipement;
    }

    /**
     * Renseigne l'�quipement de mesure de la vitesse dans le cas d'une infraction routi�re
     *
     * @param equipement
     *            l'�quipement de mesure
     */
    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    @Override
    public Vitesse clone() throws CloneNotSupportedException {
        Vitesse clone = (Vitesse) super.clone();
        if (this.equipement != null) {
            clone.setEquipement(this.equipement.clone());
        }
        return clone;
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