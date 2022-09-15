/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.math.BigDecimal;

/**
 * Pi�ce � conviction de nature p�rissable. Il s'agit de denr�es p�rissables (par exemple : pr�l�vements biologiques) ou
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
