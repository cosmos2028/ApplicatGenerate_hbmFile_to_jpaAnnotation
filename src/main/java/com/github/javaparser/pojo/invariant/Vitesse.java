/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.infraction.model;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;

/**
 * Identifie les éléments factuels liés à une infraction routière liée à la vitesse:
 * <ul>
 * <li>caractéristiques d'équipement de terrain: numéro, condition de prise de vue, point de repère kilométrique,
 * précision du lieu.</li>
 * <li>caractéristiques de l'infraction: heure, vitesse limitée autorisée, vitesse retenue.</li>
 * </ul>
 */
public class Vitesse implements java.io.Serializable, Cloneable {
    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -3954063295034472062L;

    private static final HashCodeEqualsHelper<Vitesse> HE_HELPER = HashCodeEqualsHelper.of(Vitesse.class, Vitesse::getValeurMesure, Vitesse::getValeurRetenue);

    /** Vitesse mesurée */
    private Float valeurMesure;

    /** Vitesse limite autoriséee */
    private Short valeurLimiteAutorisee;

    /** Vitesse retenue */
    private Short valeurRetenue;

    /** Equipement de mesure */
    private Equipement equipement;

    /**
     * Indique la vitesse limite autorisée
     *
     * @return la vitesse limite autorisée
     */
    public Short getValeurLimiteAutorisee() {
        return valeurLimiteAutorisee;
    }

    /**
     * Renseigne la vitesse limite autorisée
     *
     * @param valeurLimiteAutorisee
     *            la vitesse limite autorisée
     */
    public void setValeurLimiteAutorisee(Short valeurLimiteAutorisee) {
        this.valeurLimiteAutorisee = valeurLimiteAutorisee;
    }

    /**
     * Indique la vitesse mesurée
     *
     * @return la vitesse mesurée
     */
    public Float getValeurMesure() {
        return valeurMesure;
    }

    /**
     * Renseigne la vitesse mesurée
     *
     * @param valeurMesure
     *            la vitesse mesurée
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
     * Indique l'équipement de mesure dans le cas d'une infraction routière
     */
    public Equipement getEquipement() {
        return equipement;
    }

    /**
     * Renseigne l'équipement de mesure de la vitesse dans le cas d'une infraction routière
     *
     * @param equipement
     *            l'équipement de mesure
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