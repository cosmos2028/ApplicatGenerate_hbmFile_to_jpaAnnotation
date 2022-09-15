package fr.gouv.justice.cassiopee.invariant.edition.model;

public enum EtatDemandeEditionBPNEnum {

    /** Edition demandée */
    EDITION_DEMANDEE("EDITION_DEMANDEE"),

    /** Edition disponible */
    EDITION_DISPONIBLE("EDITION_DISPONIBLE"),

    /** Transmission au BPN en cours */
    TRANSMISSION_BPN_EN_COURS("TRANSMISSION_BPN_EN_COURS"),

    /** Transmission au BPN en échec */
    TRANSMISSION_BPN_EN_ERREUR("TRANSMISSION_BPN_EN_ERREUR");

    /**
     * le code associé à l'état
     */
    private String code;

    /**
     * construit un élément de l'énumération
     *
     * @param code
     *            code de l'état
     */
    private EtatDemandeEditionBPNEnum(String code) {
        this.code = code;
    }

    /**
     * retourne le code associé à l'état
     *
     * @return le code associé à l'état
     */
    public String getCode() {
        return code;
    }

}
