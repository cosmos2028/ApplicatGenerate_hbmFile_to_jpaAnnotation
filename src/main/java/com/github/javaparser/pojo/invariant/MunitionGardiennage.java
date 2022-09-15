package fr.gouv.justice.cassiopee.invariant.scelle.model;

/**
 * Pièce à conviction de type Munition.
 */
public class MunitionGardiennage extends ObjetEnGardiennage {

    /**
     *
     */
    private static final long serialVersionUID = -801401575614300776L;

    private String calibre;

    private String categorie;

    /**
     * Accesseur du calibre de la munition
     *
     * @return Returns Calibre de la munition gardée
     */
    public String getCalibre() {
        return calibre;
    }

    /**
     * Modification du calibre de la munition
     *
     * @param munitionCalibre
     *            Nouveau calibre
     */
    public void setCalibre(String munitionCalibre) {
        this.calibre = munitionCalibre;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
