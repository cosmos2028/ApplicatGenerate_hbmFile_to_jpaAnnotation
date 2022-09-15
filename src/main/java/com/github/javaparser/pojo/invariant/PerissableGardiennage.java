package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.math.BigDecimal;

public class PerissableGardiennage extends ObjetEnGardiennage {

    private static final long serialVersionUID = -4299687613359470387L;

    private BigDecimal poidsSaisi;

    private BigDecimal poidsReel;

    /**
     * Accesseur du poid r�el de l'objet p�rissable
     *
     * @return Poids r�el de l'objet
     */
    public BigDecimal getPoidsReel() {
        return poidsReel;
    }

    /**
     * Modification du poid r�el de l'objet p�rissable
     *
     * @param poidsReel
     *            Nouveau poid de l'objet
     */
    public void setPoidsReel(BigDecimal poidsReel) {
        this.poidsReel = poidsReel;
    }

    /**
     * Accesseur du poid saisi de l'objet p�rissable
     *
     * @return Poid saisi de l'objet
     */
    public BigDecimal getPoidsSaisi() {
        return poidsSaisi;
    }

    /**
     * Modification du poid saisi de l'objet p�rissable
     *
     * @param poidsSaisi
     *            Nouveau poid saisi de l'objet
     */
    public void setPoidsSaisi(BigDecimal poidsSaisi) {
        this.poidsSaisi = poidsSaisi;
    }
}
