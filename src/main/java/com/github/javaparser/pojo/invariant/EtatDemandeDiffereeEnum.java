package fr.gouv.justice.cassiopee.invariant.edition.model;

import fr.gouv.justice.cassiopee.common.exception.TechnicalException;

/**
 * énumération des états d'une demande différée
 */
public enum EtatDemandeDiffereeEnum {

    ATTENTE("ATTENTE"), TRAITEMENT("TRAITEMENT"), TERMINEE("TERMINEE"), ECHEC("ECHEC");

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
    private EtatDemandeDiffereeEnum(String code) {
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

    /**
     * recherche l'élément de l'énumération associé à un code
     *
     * @param code
     *            le code associé à un état
     * @return l'élément de l'énumération associé à un code
     */
    public static EtatDemandeDiffereeEnum findEtatDemandeDiffereeByCode(String code) {
        for (EtatDemandeDiffereeEnum etat : EtatDemandeDiffereeEnum.values()) {
            if (etat.getCode().equals(code)) {
                return etat;
            }
        }
        throw new TechnicalException("code état inconnu " + code);
    }
}
