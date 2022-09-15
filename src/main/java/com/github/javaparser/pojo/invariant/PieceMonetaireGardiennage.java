package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Pi�ce � conviction de type pi�ce mon�taire. Peut �tre de type :
 * <ul>
 * <li>valeur mon�taire (euros et devises),</li>
 * <li>valeur mobili�re (titres, actions ...),</li>
 * <li>bijou ...</li>
 * </ul>
 */
public class PieceMonetaireGardiennage extends ObjetEnGardiennage {

    /**
     *
     */
    private static final long serialVersionUID = 3579508930930162450L;

    private BigDecimal totalDepose;

    private BigDecimal bloque;

    private Date dateDepotBanque;

    private String agenceBancaire;

    private String lieuDepot;

    private String numeroTitre;

    private String devises;

    private Boolean saisieArret;

    /**
     * Acesseur de l'agence bancaire
     *
     * @return Agence bancaire
     */
    public String getAgenceBancaire() {
        return agenceBancaire;
    }

    /**
     * Modification de l'agence bancaire
     *
     * @param agenceBancaire
     *            Nouvelle agence bancaire
     */
    public void setAgenceBancaire(String agenceBancaire) {
        this.agenceBancaire = agenceBancaire;
    }

    /**
     * Accesseur du montant bloqu�
     *
     * @return Montant bloqu�
     */
    public BigDecimal getBloque() {
        return bloque;
    }

    /**
     * Modification du montant bloqu�
     *
     * @param bloque
     *            Nouveau montant bloqu�
     */
    public void setBloque(BigDecimal bloque) {
        this.bloque = bloque;
    }

    /**
     * Accesseur de la date de d�p�t
     *
     * @return Date de d�p�t en banque
     */
    public Date getDateDepotBanque() {
        return dateDepotBanque;
    }

    /**
     * Modification de la date de d�p�t en banque
     *
     * @param dateDepotBanque
     *            Nouvelle date de d�p�t
     */
    public void setDateDepotBanque(Date dateDepotBanque) {
        this.dateDepotBanque = dateDepotBanque;
    }

    /**
     * Accesseur de la devise
     *
     * @return Devise
     */
    public String getDevises() {
        return devises;
    }

    /**
     * Modification de la devise
     *
     * @param devises
     *            Nouvelle devise
     */
    public void setDevises(String devises) {
        this.devises = devises;
    }

    /**
     * Accesseur du lieu du d�p�t
     *
     * @return Lieu du d�p�t
     */
    public String getLieuDepot() {
        return lieuDepot;
    }

    /**
     * Modification du lieu du d�p�t
     *
     * @param lieuDepot
     *            Nouveau lieu du d�p�t
     */
    public void setLieuDepot(String lieuDepot) {
        this.lieuDepot = lieuDepot;
    }

    /**
     * Accesseur du num�ro de titre
     *
     * @return Num�ro de titre
     */
    public String getNumeroTitre() {
        return numeroTitre;
    }

    /**
     * Modification du num�ro de titre
     *
     * @param numeroTitre
     *            Nouveau num�ro de titre
     */
    public void setNumeroTitre(String numeroTitre) {
        this.numeroTitre = numeroTitre;
    }

    /**
     * Accesseur de l'attribut saisie arr�t
     *
     * @return Saisie arr�t
     */
    public Boolean getSaisieArret() {
        return saisieArret;
    }

    /**
     * Modification de l'attribut saisie / arr�t
     *
     * @param saisieArret
     *            Nouvelle donn�e saisie / arr�t
     */
    public void setSaisieArret(Boolean saisieArret) {
        this.saisieArret = saisieArret;
    }

    /**
     * Accesseur du montant total d�pos�
     *
     * @return Montant total d�pos�
     */
    public BigDecimal getTotalDepose() {
        return totalDepose;
    }

    /**
     * Modification du montant total d�pos�
     *
     * @param totalDepose
     *            Nouveau total d�pos�
     */
    public void setTotalDepose(BigDecimal totalDepose) {
        this.totalDepose = totalDepose;
    }
}
