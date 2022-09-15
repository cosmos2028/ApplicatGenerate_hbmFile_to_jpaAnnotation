package fr.gouv.justice.cassiopee.invariant.edition.model;

import fr.gouv.justice.cassiopee.common.exception.TechnicalException;

/**
 * �num�ration des �tats d'une demande diff�r�e
 */
public enum EtatDemandeDiffereeEnum {

    ATTENTE("ATTENTE"), TRAITEMENT("TRAITEMENT"), TERMINEE("TERMINEE"), ECHEC("ECHEC");

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
    private EtatDemandeDiffereeEnum(String code) {
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

    /**
     * recherche l'�l�ment de l'�num�ration associ� � un code
     *
     * @param code
     *            le code associ� � un �tat
     * @return l'�l�ment de l'�num�ration associ� � un code
     */
    public static EtatDemandeDiffereeEnum findEtatDemandeDiffereeByCode(String code) {
        for (EtatDemandeDiffereeEnum etat : EtatDemandeDiffereeEnum.values()) {
            if (etat.getCode().equals(code)) {
                return etat;
            }
        }
        throw new TechnicalException("code �tat inconnu " + code);
    }
}
