/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

/**
 * Pi�ce � conviction de type Arme.
 */
public abstract class Arme extends PieceConviction {

    private static final long serialVersionUID = -4299687613359470387L;

    private String marque;

    private String numeroDeSerie;

    private Boolean indicateurEtui;

    /**
     * @return Returns the indicateurEtui.
     */
    public Boolean getIndicateurEtui() {
        return indicateurEtui;
    }

    /**
     * @param indicateurEtui
     *            The indicateurEtui to set.
     */
    public void setIndicateurEtui(Boolean indicateurEtui) {
        this.indicateurEtui = indicateurEtui;
    }

    /**
     * @return Returns the marque.
     */
    public String getMarque() {
        return marque;
    }

    /**
     * @param marque
     *            The marque to set.
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * @return Returns the numeroDeSerie.
     */
    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    /**
     * @param numeroDeSerie
     *            The numeroDeSerie to set.
     */
    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

}
