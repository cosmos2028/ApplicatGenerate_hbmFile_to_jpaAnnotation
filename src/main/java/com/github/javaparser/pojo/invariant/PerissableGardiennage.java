package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.math.BigDecimal;

public class PerissableGardiennage extends ObjetEnGardiennage {

    private static final long serialVersionUID = -4299687613359470387L;

    private BigDecimal poidsSaisi;

    private BigDecimal poidsReel;

    /**
     * Accesseur du poid réel de l'objet périssable
     *
     * @return Poids réel de l'objet
     */
    public BigDecimal getPoidsReel() {
        return poidsReel;
    }

    /**
     * Modification du poid réel de l'objet périssable
     *
     * @param poidsReel
     *            Nouveau poid de l'objet
     */
    public void setPoidsReel(BigDecimal poidsReel) {
        this.poidsReel = poidsReel;
    }

    /**
     * Accesseur du poid saisi de l'objet périssable
     *
     * @return Poid saisi de l'objet
     */
    public BigDecimal getPoidsSaisi() {
        return poidsSaisi;
    }

    /**
     * Modification du poid saisi de l'objet périssable
     *
     * @param poidsSaisi
     *            Nouveau poid saisi de l'objet
     */
    public void setPoidsSaisi(BigDecimal poidsSaisi) {
        this.poidsSaisi = poidsSaisi;
    }
}
