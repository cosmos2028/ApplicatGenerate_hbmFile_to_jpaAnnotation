/*
 * Ce document est la propri?t? d'Atos Origin et du Minist?re de la Justice.
 * Il ne peut ?tre utilis?, reproduit ou divulgu? sans leur autorisation ?crite pr?alable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.util.Set;

import fr.gouv.justice.cassiopee.service.editiontype.service.enumeration.EditionTypeEnum;

/**
 * pi?ce d'?xecution, genre d'?dition type avec un suivi
 */
public class PieceExecutionType<EditionTypeDeclancheurPieceExecution> extends EditionType {

    /**
     *
     */
    private static final long serialVersionUID = 5679724877808947161L;

    // TODO ? int?grer dans les scripts de gen du param
    private Boolean pseudoPE;

    /**
     * champ mapp? par Hibernate TODO ? int?grer dans les scripts de gen du param
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
