/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.util.Set;

import fr.gouv.justice.cassiopee.service.editiontype.service.enumeration.EditionTypeEnum;

/**
 * pièce d'éxecution, genre d'édition type avec un suivi
 */
public class PieceExecutionType<EditionTypeDeclancheurPieceExecution> extends EditionType {

    /**
     *
     */
    private static final long serialVersionUID = 5679724877808947161L;

    // TODO à intégrer dans les scripts de gen du param
    private Boolean pseudoPE;

    /**
     * champ mappé par Hibernate TODO à intégrer dans les scripts de gen du param
     */
    private Boolean typeLienEnumObligatoireFlag;

    public Boolean getPseudoPE() {
        return pseudoPE;
    }

    public void setPseudoPE(Boolean pseudoPE) {
        this.pseudoPE = pseudoPE;
    }

    protected Boolean getTypeLienEnumObligatoireFlag() {
        return typeLienEnumObligatoireFlag;
    }

    protected void setTypeLienEnumObligatoireFlag(Boolean typeLienEnumObligatoireFlag) {
        this.typeLienEnumObligatoireFlag = typeLienEnumObligatoireFlag;
    }

    /**
     * @return Set<EditionTypeDeclancheurPieceExecution>
     */
    @SuppressWarnings("unchecked")
    public Set<EditionTypeDeclancheurPieceExecution> getDeclencheurs() {
        return super.getEditionTypeDeclencheurs();
    }

    public EditionTypeEnum getEditionTypeEnum() {
        return EditionTypeEnum.PieceExecution;
    }

}
