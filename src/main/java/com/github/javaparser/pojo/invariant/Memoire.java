/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
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
     *            coutReclameHt à affecter
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
     *            dateDebutPeriode à affecter
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
     *            dateDepot à affecter
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
     *            dateFinPeriode à affecter
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
     *            fraisAnnexeHt à affecter
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
     *            payementDateReglement à affecter
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
     * Retourne le taux de tva pour le mémoire
     *
     * @return le taux de tva pour le mémoire
     */
    public Double getTauxTvaMemoire() {
        return tauxTvaMemoire;
    }

    /**
     * Positionne le taux de tva pour le mémoire
     *
     * @param tauxTvaMemoire
     *            le taux de tva pour le mémoire
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
