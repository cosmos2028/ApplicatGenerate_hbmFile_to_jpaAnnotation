/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

/**
 * Pi�ce � conviction de type Arme � feu.
 */
public class ArmeAFeu extends Arme {

    private static final long serialVersionUID = -4299687613359470387L;

    private String calibre;

    private Boolean modifiee;

    /**
     * @return Returns the calibre.
     */
    public String getCalibre() {
        return calibre;
    }

    /**
     * @param calibre
     *            The calibre to set.
     */
    public void setCalibre(String calibre) {
        this.calibre = calibre;
    }

    /**
     * @return Returns the modifiee.
     */
    public Boolean isModifiee() {
        return modifiee;
    }

    /**
     * @param modifiee
     *            The modifiee to set.
     */
    public void setModifiee(Boolean modifiee) {
        this.modifiee = modifiee;
    }

}
