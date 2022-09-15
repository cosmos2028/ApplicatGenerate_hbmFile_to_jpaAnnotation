package fr.gouv.justice.cassiopee.invariant.scelle.model;

/**
 * Objet en gardiennage de type Arme.
 */
public class ArmeGardiennage extends ObjetEnGardiennage {

    /**
     *
     */
    private static final long serialVersionUID = 1371805995388821926L;

    private Boolean indicateurEtui;

    private String calibre;

    private Boolean modifiee;

    private String categorie;

    /**
     * Accesseur de l'indicateur �tui
     *
     * @return Indicateur �tui
     */
    public Boolean getIndicateurEtui() {
        return indicateurEtui;
    }

    /**
     * Modification de l'indicateur �tui
     *
     * @param indicateurEtui
     *            Nouvel indicateur
     */
    public void setIndicateurEtui(Boolean indicateurEtui) {
        this.indicateurEtui = indicateurEtui;
    }

    /**
     * Accesseur du calibre de l'arme � feu
     *
     * @return Calibre de l'arme
     */
    public String getCalibre() {
        return calibre;
    }

    /**
     * Modification du calibre de l'arme
     *
     * @param calibre
     *            Nouveau calibre de l'arme
     */
    public void setCalibre(String calibre) {
        this.calibre = calibre;
    }

    /**
     * Accesseur de l'indicateur pour savoir si l'arme a �t� modifi�e
     *
     * @return Indicateur de modification de l'arme
     */
    public Boolean isModifiee() {
        return modifiee;
    }

    /**
     * Modification de l'indicateur
     *
     * @param modifiee
     *            Nouvel indicateur d'arme modifi�e
     */
    public void setModifiee(Boolean modifiee) {
        this.modifiee = modifiee;
    }

    /**
     * Accesseur de la cat�gorie de l'objet
     *
     * @return Cat�gorie de l'objet
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * Modification de la cat�gorie de l'objet
     *
     * @param categorie
     *            Nouvelle cat�gorie de l'objet
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
