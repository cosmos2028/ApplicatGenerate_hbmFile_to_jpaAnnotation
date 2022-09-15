/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

/**
 * Pi�ce � conviction de type Munition.
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
