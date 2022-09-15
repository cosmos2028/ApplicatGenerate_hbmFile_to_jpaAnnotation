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
     * Accesseur de l'indicateur étui
     *
     * @return Indicateur étui
     */
    public Boolean getIndicateurEtui() {
        return indicateurEtui;
    }

    /**
     * Modification de l'indicateur étui
     *
     * @param indicateurEtui
     *            Nouvel indicateur
     */
    public void setIndicateurEtui(Boolean indicateurEtui) {
        this.indicateurEtui = indicateurEtui;
    }

    /**
     * Accesseur du calibre de l'arme à feu
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
     * Accesseur de l'indicateur pour savoir si l'arme a été modifiée
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
     *            Nouvel indicateur d'arme modifiée
     */
    public void setModifiee(Boolean modifiee) {
        this.modifiee = modifiee;
    }

    /**
     * Accesseur de la catégorie de l'objet
     *
     * @return Catégorie de l'objet
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * Modification de la catégorie de l'objet
     *
     * @param categorie
     *            Nouvelle catégorie de l'objet
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
