package fr.gouv.justice.cassiopee.invariant.edition.model;

/**
 * énumération des pièce d'exécution dont les codes sont utilisés
 */
public enum PieceExecutionEnum {

    FICHE_CASIER("713a"), FICHE_EXTRAIT_POUR_ECROU("713b"), FICHE_EXTRAIT_RECRUTEMENT("713f"), FICHE_EXTRAIT_INTERDICTION_BANCAIRE(
            "713i"), FICHE_extrait_aux_finances("713i"), FICHE_CONDAMNATION("744a");
    /**
     * le code associé à la pièce d'exécution
     */
    private String code;

    /**
     * construit un élément de l'énumération
     *
     * @param code
     *            code associé à la pièce d'exécution
     */
    private PieceExecutionEnum(String code) {
        this.code = code;
    }

    /**
     * retourne le code associé à la pièce d'exécution
     *
     * @return le code associé à la pièce d'exécution
     */
    public String getCode() {
        return code;
    }
}
