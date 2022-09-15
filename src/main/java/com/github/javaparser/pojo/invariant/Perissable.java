/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.math.BigDecimal;

/**
 * Pièce à conviction de nature périssable. Il s'agit de denrées périssables (par exemple : prélèvements biologiques) ou
 * de drogue.
 */
public class Perissable extends PieceConviction {

    private static final long serialVersionUID = -4299687613359470387L;

    private BigDecimal poidsSaisi;

    private BigDecimal poidsReel;

    /**
     * @return Returns the poidsReel.
     */
    public BigDecimal getPoidsReel() {
        return poidsReel;
    }

    /**
     * @param poidsReel
     *            The poidsReel to set.
     */
    public void setPoidsReel(BigDecimal poidsReel) {
        this.poidsReel = poidsReel;
    }

    /**
     * @return Returns the poidsSaisi.
     */
    public BigDecimal getPoidsSaisi() {
        return poidsSaisi;
    }

    /**
     * @param poidsSaisi
     *            The poidsSaisi to set.
     */
    public void setPoidsSaisi(BigDecimal poidsSaisi) {
        this.poidsSaisi = poidsSaisi;
    }

}
