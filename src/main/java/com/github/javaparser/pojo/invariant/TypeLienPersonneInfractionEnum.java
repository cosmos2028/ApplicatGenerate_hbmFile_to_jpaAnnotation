package fr.gouv.justice.cassiopee.invariant.infraction.model;

/**
 * �num�ration des types de lien d'une infraction
 */
public enum TypeLienPersonneInfractionEnum {

    AUTEUR_INFRACTION("AUTEUR_INFRACTION"), VICTIME_INFRACTION("VICTIME_INFRACTION");

    /**
     * le code associ� au type de lien
     */
    String code;

    /**
     * construit un �l�ment de l'�num�ration
     *
     * @param code
     *            code du type de lien
     */
    private TypeLienPersonneInfractionEnum(String code) {
        this.code = code;
    }

    /**
     * retourne le code associ� au type de lien
     *
     * @return le code associ� au type de lien
     */
    public String getCode() {
        return code;
    }
}
