/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

/**
 * Pièce à conviction de type Munition.
 */
public class Munition extends PieceConviction {

    private static final long serialVersionUID = -4299687613359470387L;

    private String calibre;

    /**
     * @return Returns the munitionCalibre.
     */
    public String getCalibre() {
        return calibre;
    }

    /**
     * @param munitionCalibre
     *            The munitionCalibre to set.
     */
    public void setCalibre(String munitionCalibre) {
        this.calibre = munitionCalibre;
    }

}
