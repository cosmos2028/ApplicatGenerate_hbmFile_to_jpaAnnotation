/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.infraction.model;

import java.util.Date;

/**
 * Equipemement de terrain (radar) qui a réalisé la mesure de la vitesse pour une infraction routière.
 */
public class Equipement implements java.io.Serializable, Cloneable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -7699101771693439194L;

    /**
     * Description des conditions de constatation de l'infraction. Ex. par temps de pluie, neige
     */
    private String conditionPriseVue;

    /**
     * Indication du kilomètre sur la voie précisant le lieu de l'infraction. ex: RN10 Km 150
     */
    private String pointRepereKm;

    /** Numéro de l'équipement */
    private Integer numero;

    /** Marque de l'équipement */
    private String marque;

    /** Type de l'équipement */
    private String type;

    /** Date de vérification de l'équipement de mesure */
    private Date dateVerification;

    /** Organisme vérificateur de l'équipement */
    private String organismeVerificateur;

    /** précision type de lieu */
    private String precisionLieu;

    /**
     * Indique le type de lieu concerné. Ex: RN pour route nationale, AG pour agglomération, VS pour voie spécialisée
     */
    private TypeLieu typeLieu;

    /**
     * Retourne une description des conditions de prise de vue
     *
     * @return une description des conditions de prise de vue
     */
    public String getConditionPriseVue() {
        return conditionPriseVue;
    }

    /**
     * Renseigne la description des conditions de prise de vue
     *
     * @param conditionPriseVue
     *            la description
     */
    public void setConditionPriseVue(String conditionPriseVue) {
        this.conditionPriseVue = conditionPriseVue;
    }

    /**
     * Retourne la date de vérification de l'équipement
     *
     * @return la date de vérification de l'équipement
     */
    public Date getDateVerification() {
        return dateVerification;
    }

    /**
     * Renseigne la date de vérification de l'équipement
     *
     * @param dateVerification
     *            la date de vérification de l'équipement
     */
    public void setDateVerification(Date dateVerification) {
        this.dateVerification = dateVerification;
    }

    /**
     * Indique la marque de l'équipement
     *
     * @return la marque de l'équipement
     */
    public String getMarque() {
        return marque;
    }

    /**
     * Renseigne la marque de l'équipement
     *
     * @param marque
     *            marque de l'équipement
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * Indique le numéro de l'équipement
     *
     * @return le numéro de l'équipement
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * Renseigne le numéro de l'équipement
     *
     * @param numero
     *            numéro de l'équipement
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * Indique l'organisme vérificateur de l'équipement
     *
     * @return l'organisme vérificateur de l'équipement
     */
    public String getOrganismeVerificateur() {
        return organismeVerificateur;
    }

    /**
     * Renseigne l'organisme vérificateur de l'équipement
     *
     * @param organismeVerificateur
     *            organisme vérificateur de l'équipement
     */
    public void setOrganismeVerificateur(String organismeVerificateur) {
        this.organismeVerificateur = organismeVerificateur;
    }

    /**
     * Retourne le point de repère kilométrique qui indique le lieu où la mesure de la vitesse a été effectuée
     */
    public String getPointRepereKm() {
        return pointRepereKm;
    }

    /**
     * Renseigne le point de repère kilométrique qui indique le lieu où la mesure de la vitesse a été effectuée
     *
     * @param pointRepereKm
     *            point de repère kilométrique
     */
    public void setPointRepereKm(String pointRepereKm) {
        this.pointRepereKm = pointRepereKm;
    }

    /**
     * Retourne le type d'équipement
     *
     * @return le type d'équipement
     */
    public String getType() {
        return type;
    }

    /**
     * Renseigne le type d'équipement
     *
     * @param type
     *            le type d'équipement
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Retourne un libellé précisant le lieu
     *
     * @return un libellé précisant le lieu
     */
    public String getPrecisionLieu() {
        return precisionLieu;
    }

    /**
     * Renseigne un libellé précisant le lieu
     *
     * @param precisionLieu
     *            un libellé précisant le lieu
     */
    public void setPrecisionLieu(String precisionLieu) {
        this.precisionLieu = precisionLieu;
    }

    /**
     * Indique le type de lieu
     *
     * @return le type de lieu
     */
    public TypeLieu getTypeLieu() {
        return typeLieu;
    }

    /**
     * Renseigne le type de lieu
     *
     * @param typeLieu
     *            le type de lieu
     */
    public void setTypeLieu(TypeLieu typeLieu) {
        this.typeLieu = typeLieu;
    }

    @Override
    public Equipement clone() throws CloneNotSupportedException {
        return (Equipement) super.clone();
    }
}
