package fr.gouv.justice.cassiopee.invariant.infraction.model;

/**
 * énumération des types de lien d'une infraction
 */
public enum TypeLienPersonneInfractionEnum {

    AUTEUR_INFRACTION("AUTEUR_INFRACTION"), VICTIME_INFRACTION("VICTIME_INFRACTION");

    /**
     * le code associé au type de lien
     */
    String code;

    /**
     * construit un élément de l'énumération
     *
     * @param code
     *            code du type de lien
     */
    private TypeLienPersonneInfractionEnum(String code) {
        this.code = code;
    }

    /**
     * retourne le code associé au type de lien
     *
     * @return le code associé au type de lien
     */
    public String getCode() {
        return code;
    }
}
