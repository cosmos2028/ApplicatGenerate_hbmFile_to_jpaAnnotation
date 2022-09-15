/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.io.Serializable;
import java.util.Date;

/**
 * memoire
 */
public class Memoire implements Serializable {

    private static final long serialVersionUID = 13561141L;

    private Date dateDepot;

    private Double fraisAnnexeHt;

    private Double coutReclameHt;

    private Date dateDebutPeriode;

    private Date dateFinPeriode;

    private Double tauxTvaMemoire;

    private Double paiementMontantHt;

    private Double tauxTvaPaiement;

    private Date payementDateReglement;

    /**
     * @return the coutReclameHt
     */
    public Double getCoutReclameHt() {
        return coutReclameHt;
    }

    /**
     * @param coutReclameHt
     *            coutReclameHt � affecter
     */
    public void setCoutReclameHt(Double coutReclameHt) {
        this.coutReclameHt = coutReclameHt;
    }

    /**
     * @return the dateDebutPeriode
     */
    public Date getDateDebutPeriode() {
        return dateDebutPeriode;
    }

    /**
     * @param dateDebutPeriode
     *            dateDebutPeriode � affecter
     */
    public void setDateDebutPeriode(Date dateDebutPeriode) {
        this.dateDebutPeriode = dateDebutPeriode;
    }

    /**
     * @return the dateDepot
     */
    public Date getDateDepot() {
        return dateDepot;
    }

    /**
     * @param dateDepot
     *            dateDepot � affecter
     */
    public void setDateDepot(Date dateDepot) {
        this.dateDepot = dateDepot;
    }

    /**
     * @return the dateFinPeriode
     */
    public Date getDateFinPeriode() {
        return dateFinPeriode;
    }

    /**
     * @param dateFinPeriode
     *            dateFinPeriode � affecter
     */
    public void setDateFinPeriode(Date dateFinPeriode) {
        this.dateFinPeriode = dateFinPeriode;
    }

    /**
     * @return the fraisAnnexeHt
     */
    public Double getFraisAnnexeHt() {
        return fraisAnnexeHt;
    }

    /**
     * @param fraisAnnexeHt
     *            fraisAnnexeHt � affecter
     */
    public void setFraisAnnexeHt(Double fraisAnnexeHt) {
        this.fraisAnnexeHt = fraisAnnexeHt;
    }

    /**
     * @return the payementDateReglement
     */
    public Date getPayementDateReglement() {
        return payementDateReglement;
    }

    /**
     * @param payementDateReglement
     *            payementDateReglement � affecter
     */
    public void setPayementDateReglement(Date payementDateReglement) {
        this.payementDateReglement = payementDateReglement;
    }

    /**
     * Retourne le montant du paiement HT
     *
     * @return le montant du paiement HT
     */
    public Double getPaiementMontantHt() {
        return paiementMontantHt;
    }

    /**
     * Positionne le montant du paiement HT
     *
     * @param paiementMontantHt
     *            le montant du paiement HT
     */
    public void setPaiementMontantHt(Double paiementMontantHt) {
        this.paiementMontantHt = paiementMontantHt;
    }

    /**
     * Retourne le taux de tva pour le m�moire
     *
     * @return le taux de tva pour le m�moire
     */
    public Double getTauxTvaMemoire() {
        return tauxTvaMemoire;
    }

    /**
     * Positionne le taux de tva pour le m�moire
     *
     * @param tauxTvaMemoire
     *            le taux de tva pour le m�moire
     */
    public void setTauxTvaMemoire(Double tauxTvaMemoire) {
        this.tauxTvaMemoire = tauxTvaMemoire;
    }

    /**
     * Retourne le taux de tva pour le paiement
     *
     * @return le taux de tva pour le paiement
     */
    public Double getTauxTvaPaiement() {
        return tauxTvaPaiement;
    }

    /**
     * Positionne le taux de tva pour le paiement
     *
     * @param tauxTvaPaiement
     *            le taux de tva pour le paiement
     */
    public void setTauxTvaPaiement(Double tauxTvaPaiement) {
        this.tauxTvaPaiement = tauxTvaPaiement;
    }

}
