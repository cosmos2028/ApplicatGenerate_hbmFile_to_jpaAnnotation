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
    SUCCES("Termin�e"),

    /** Succes (export CSV partiel) */
    SUCCES_EXPORT_PARTIEL("Termin�e (extraction partielle)"),

    /** Echec */
    ECHEC("Echec");

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
    private EtatDemandeEditionEnum(String code) {
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
