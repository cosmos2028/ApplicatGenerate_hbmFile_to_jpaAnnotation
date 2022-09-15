package fr.gouv.justice.cassiopee.invariant.infraction.model;

/**
 * �num�ration des �tats d'une infraction
 */
public enum EtatInfractionEnum {

    ENCOURS("ENCOURS"), DISQUALIFIEE("DISQUALIFIEE"), SUPPRIMEE("SUPPRIMEE"), AMNISTIEE("AMNISTIEE"), REQUIS("REQUIS")
    , INFIRMEE("INFIRMEE");

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
    private EtatInfractionEnum(String code) {
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
