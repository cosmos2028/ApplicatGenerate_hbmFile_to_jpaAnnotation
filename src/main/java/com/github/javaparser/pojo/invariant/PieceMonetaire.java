/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.util.Date;

/**
 * Pi�ce � conviction de type pi�ce mon�taire. Peut �tre de type :
 * <ul>
 * <li>valeur mon�taire (euros et devises),</li>
 * <li>valeur mobili�re (titres, actions ...),</li>
 * <li>bijou ...</li>
 * </ul>
 */
public class PieceMonetaire extends PieceConviction {

    private static final long serialVersionUID = -4299687613359470387L;

    private Double totalDepose;

    private String devises;

    private Boolean saisieArret;

    private Double bloque;

    private Date dateDepotBanque;

    private String lieuDepot;

    private String libelle;

    private String numeroTitre;

    private String agenceBancaire;

    /**
     * @return Returns the agenceBancaire.
     */
    public String getAgenceBancaire() {
        return agenceBancaire;
    }

    /**
     * @param agenceBancaire
     *            The agenceBancaire to set.
     */
    public void setAgenceBancaire(String agenceBancaire) {
        this.agenceBancaire = agenceBancaire;
    }

    /**
     * @return Returns the bloque.
     */
    public Double getBloque() {
        return bloque;
    }

    /**
     * @param bloque
     *            The bloque to set.
     */
    public void setBloque(Double bloque) {
        this.bloque = bloque;
    }

    /**
     * @return Returns the dateDepotBanque.
     */
    public Date getDateDepotBanque() {
        return dateDepotBanque;
    }

    /**
     * @param dateDepotBanque
     *            The dateDepotBanque to set.
     */
    public void setDateDepotBanque(Date dateDepotBanque) {
        this.dateDepotBanque = dateDepotBanque;
    }

    /**
     * @return Returns the devises.
     */
    public String getDevises() {
        return devises;
    }

    /**
     * @param devises
     *            The devises to set.
     */
    public void setDevises(String devises) {
        this.devises = devises;
    }

    /**
     * @return Returns the libelle.
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * @param libelle
     *            The libelle to set.
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * @return Returns the lieuDepot.
     */
    public String getLieuDepot() {
        return lieuDepot;
    }

    /**
     * @param lieuDepot
     *            The lieuDepot to set.
     */
    public void setLieuDepot(String lieuDepot) {
        this.lieuDepot = lieuDepot;
    }

    /**
     * @return Returns the numeroTitre.
     */
    public String getNumeroTitre() {
        return numeroTitre;
    }

    /**
     * @param numeroTitre
     *            The numeroTitre to set.
     */
    public void setNumeroTitre(String numeroTitre) {
        this.numeroTitre = numeroTitre;
    }

    /**
     * @return Returns the saisieArret.
     */
    public Boolean getSaisieArret() {
        return saisieArret;
    }

    /**
     * @param saisieArret
     *            The saisieArret to set.
     */
    public void setSaisieArret(Boolean saisieArret) {
        this.saisieArret = saisieArret;
    }

    /**
     * @return Returns the totalDepose.
     */
    public Double getTotalDepose() {
        return totalDepose;
    }

    /**
     * @param totalDepose
     *            The totalDepose to set.
     */
    public void setTotalDepose(Double totalDepose) {
        this.totalDepose = totalDepose;
    }

}
