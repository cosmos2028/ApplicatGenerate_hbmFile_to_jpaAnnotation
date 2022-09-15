/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.infraction.model;

import java.util.Date;

/**
 * Equipemement de terrain (radar) qui a r�alis� la mesure de la vitesse pour une infraction routi�re.
 */
public class Equipement implements java.io.Serializable, Cloneable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -7699101771693439194L;

    /**
     * Description des conditions de constatation de l'infraction. Ex. par temps de pluie, neige
     */
    private String conditionPriseVue;

    /**
     * Indication du kilom�tre sur la voie pr�cisant le lieu de l'infraction. ex: RN10 Km 150
     */
    private String pointRepereKm;

    /** Num�ro de l'�quipement */
    private Integer numero;

    /** Marque de l'�quipement */
    private String marque;

    /** Type de l'�quipement */
    private String type;

    /** Date de v�rification de l'�quipement de mesure */
    private Date dateVerification;

    /** Organisme v�rificateur de l'�quipement */
    private String organismeVerificateur;

    /** pr�cision type de lieu */
    private String precisionLieu;

    /**
     * Indique le type de lieu concern�. Ex: RN pour route nationale, AG pour agglom�ration, VS pour voie sp�cialis�e
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
     * Retourne la date de v�rification de l'�quipement
     *
     * @return la date de v�rification de l'�quipement
     */
    public Date getDateVerification() {
        return dateVerification;
    }

    /**
     * Renseigne la date de v�rification de l'�quipement
     *
     * @param dateVerification
     *            la date de v�rification de l'�quipement
     */
    public void setDateVerification(Date dateVerification) {
        this.dateVerification = dateVerification;
    }

    /**
     * Indique la marque de l'�quipement
     *
     * @return la marque de l'�quipement
     */
    public String getMarque() {
        return marque;
    }

    /**
     * Renseigne la marque de l'�quipement
     *
     * @param marque
     *            marque de l'�quipement
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * Indique le num�ro de l'�quipement
     *
     * @return le num�ro de l'�quipement
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * Renseigne le num�ro de l'�quipement
     *
     * @param numero
     *            num�ro de l'�quipement
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * Indique l'organisme v�rificateur de l'�quipement
     *
     * @return l'organisme v�rificateur de l'�quipement
     */
    public String getOrganismeVerificateur() {
        return organismeVerificateur;
    }

    /**
     * Renseigne l'organisme v�rificateur de l'�quipement
     *
     * @param organismeVerificateur
     *            organisme v�rificateur de l'�quipement
     */
    public void setOrganismeVerificateur(String organismeVerificateur) {
        this.organismeVerificateur = organismeVerificateur;
    }

    /**
     * Retourne le point de rep�re kilom�trique qui indique le lieu o� la mesure de la vitesse a �t� effectu�e
     */
    public String getPointRepereKm() {
        return pointRepereKm;
    }

    /**
     * Renseigne le point de rep�re kilom�trique qui indique le lieu o� la mesure de la vitesse a �t� effectu�e
     *
     * @param pointRepereKm
     *            point de rep�re kilom�trique
     */
    public void setPointRepereKm(String pointRepereKm) {
        this.pointRepereKm = pointRepereKm;
    }

    /**
     * Retourne le type d'�quipement
     *
     * @return le type d'�quipement
     */
    public String getType() {
        return type;
    }

    /**
     * Renseigne le type d'�quipement
     *
     * @param type
     *            le type d'�quipement
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Retourne un libell� pr�cisant le lieu
     *
     * @return un libell� pr�cisant le lieu
     */
    public String getPrecisionLieu() {
        return precisionLieu;
    }

    /**
     * Renseigne un libell� pr�cisant le lieu
     *
     * @param precisionLieu
     *            un libell� pr�cisant le lieu
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
