package fr.gouv.justice.cassiopee.invariant.edition.model;

/**
 * @author sni
 */
public enum EtatDemandeEditionEnum {

    /** En attente */
    ATTENTE("En attente"),

    /** En cours de traitement */
    TRAITEMENT("En traitement"),

    /** Succes */
    SUCCES("Terminée"),

    /** Succes (export CSV partiel) */
    SUCCES_EXPORT_PARTIEL("Terminée (extraction partielle)"),

    /** Echec */
    ECHEC("Echec");

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
    private EtatDemandeEditionEnum(String code) {
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
