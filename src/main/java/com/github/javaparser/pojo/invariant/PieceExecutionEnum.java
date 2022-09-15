package fr.gouv.justice.cassiopee.invariant.edition.model;

/**
 * �num�ration des pi�ce d'ex�cution dont les codes sont utilis�s
 */
public enum PieceExecutionEnum {

    FICHE_CASIER("713a"), FICHE_EXTRAIT_POUR_ECROU("713b"), FICHE_EXTRAIT_RECRUTEMENT("713f"), FICHE_EXTRAIT_INTERDICTION_BANCAIRE(
            "713i"), FICHE_extrait_aux_finances("713i"), FICHE_CONDAMNATION("744a");
    /**
     * le code associ� � la pi�ce d'ex�cution
     */
    private String code;

    /**
     * construit un �l�ment de l'�num�ration
     *
     * @param code
     *            code associ� � la pi�ce d'ex�cution
     */
    private PieceExecutionEnum(String code) {
        this.code = code;
    }

    /**
     * retourne le code associ� � la pi�ce d'ex�cution
     *
     * @return le code associ� � la pi�ce d'ex�cution
     */
    public String getCode() {
        return code;
    }
}
