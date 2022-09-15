/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

/**
 * Objet temporaire pour retourner des criteres de recherche utilises dans la recherche du retour souhaite
 */
public class ParametrageRetourSouhaiteCriteria implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3933718839956017220L;

    Long serviceId;

    String codeElementStructure;

    String codeTypeElementStructure;

    /**
     * Constructeur de classe
     *
     * @param serviceId
     * @param codeElementStructure
     * @param codeTypeElementStructure
     */
    public ParametrageRetourSouhaiteCriteria(Long serviceId, String codeElementStructure,
            String codeTypeElementStructure) {

        this.serviceId = serviceId;
        this.codeElementStructure = codeElementStructure;
        this.codeTypeElementStructure = codeTypeElementStructure;
    }

    /**
     * @return String
     */
    public String getCodeElementStructure() {
        return codeElementStructure;
    }

    /**
     * @param codeElementStructure
     */
    public void setCodeElementStructure(String codeElementStructure) {
        this.codeElementStructure = codeElementStructure;
    }

    /**
     * @return String
     */
    public String getCodeTypeElementStructure() {
        return codeTypeElementStructure;
    }

    /**
     * @param codeTypeElementStructure
     */
    public void setCodeTypeElementStructure(String codeTypeElementStructure) {
        this.codeTypeElementStructure = codeTypeElementStructure;
    }

    /**
     * @return Long
     */
    public Long getServiceId() {
        return serviceId;
    }

    /**
     * @param serviceId
     */
    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}
