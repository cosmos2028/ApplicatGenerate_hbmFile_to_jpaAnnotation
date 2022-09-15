package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Pièce à conviction de type pièce monétaire. Peut être de type :
 * <ul>
 * <li>valeur monétaire (euros et devises),</li>
 * <li>valeur mobilière (titres, actions ...),</li>
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
     * Accesseur du montant bloqué
     *
     * @return Montant bloqué
     */
    public BigDecimal getBloque() {
        return bloque;
    }

    /**
     * Modification du montant bloqué
     *
     * @param bloque
     *            Nouveau montant bloqué
     */
    public void setBloque(BigDecimal bloque) {
        this.bloque = bloque;
    }

    /**
     * Accesseur de la date de dépôt
     *
     * @return Date de dépôt en banque
     */
    public Date getDateDepotBanque() {
        return dateDepotBanque;
    }

    /**
     * Modification de la date de dépôt en banque
     *
     * @param dateDepotBanque
     *            Nouvelle date de dépôt
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
     * Accesseur du lieu du dépôt
     *
     * @return Lieu du dépôt
     */
    public String getLieuDepot() {
        return lieuDepot;
    }

    /**
     * Modification du lieu du dépôt
     *
     * @param lieuDepot
     *            Nouveau lieu du dépôt
     */
    public void setLieuDepot(String lieuDepot) {
        this.lieuDepot = lieuDepot;
    }

    /**
     * Accesseur du numéro de titre
     *
     * @return Numéro de titre
     */
    public String getNumeroTitre() {
        return numeroTitre;
    }

    /**
     * Modification du numéro de titre
     *
     * @param numeroTitre
     *            Nouveau numéro de titre
     */
    public void setNumeroTitre(String numeroTitre) {
        this.numeroTitre = numeroTitre;
    }

    /**
     * Accesseur de l'attribut saisie arrêt
     *
     * @return Saisie arrêt
     */
    public Boolean getSaisieArret() {
        return saisieArret;
    }

    /**
     * Modification de l'attribut saisie / arrêt
     *
     * @param saisieArret
     *            Nouvelle donnée saisie / arrêt
     */
    public void setSaisieArret(Boolean saisieArret) {
        this.saisieArret = saisieArret;
    }

    /**
     * Accesseur du montant total déposé
     *
     * @return Montant total déposé
     */
    public BigDecimal getTotalDepose() {
        return totalDepose;
    }

    /**
     * Modification du montant total déposé
     *
     * @param totalDepose
     *            Nouveau total déposé
     */
    public void setTotalDepose(BigDecimal totalDepose) {
        this.totalDepose = totalDepose;
    }
}
