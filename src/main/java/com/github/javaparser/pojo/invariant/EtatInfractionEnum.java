package fr.gouv.justice.cassiopee.invariant.infraction.model;

/**
 * énumération des états d'une infraction
 */
public enum EtatInfractionEnum {

    ENCOURS("ENCOURS"), DISQUALIFIEE("DISQUALIFIEE"), SUPPRIMEE("SUPPRIMEE"), AMNISTIEE("AMNISTIEE"), REQUIS("REQUIS")
    , INFIRMEE("INFIRMEE");

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
    private EtatInfractionEnum(String code) {
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
