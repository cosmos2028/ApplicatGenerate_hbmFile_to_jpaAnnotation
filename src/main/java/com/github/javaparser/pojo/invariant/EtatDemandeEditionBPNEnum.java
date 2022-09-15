package fr.gouv.justice.cassiopee.invariant.edition.model;

public enum EtatDemandeEditionBPNEnum {

    /** Edition demand�e */
    EDITION_DEMANDEE("EDITION_DEMANDEE"),

    /** Edition disponible */
    EDITION_DISPONIBLE("EDITION_DISPONIBLE"),

    /** Transmission au BPN en cours */
    TRANSMISSION_BPN_EN_COURS("TRANSMISSION_BPN_EN_COURS"),

    /** Transmission au BPN en �chec */
    TRANSMISSION_BPN_EN_ERREUR("TRANSMISSION_BPN_EN_ERREUR");

    /**
     * le code associ� � l'�tat
     */
    private String code;

    /**
     * construit un �l�ment de l'�num�ration
     *
     * @param code
     *            code de l'�tat
     */
    private EtatDemandeEditionBPNEnum(String code) {
        this.code = code;
    }

    /**
     * retourne le code associ� � l'�tat
     *
     * @return le code associ� � l'�tat
     */
    public String getCode() {
        return code;
    }

}
