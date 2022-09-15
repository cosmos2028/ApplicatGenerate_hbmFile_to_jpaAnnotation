package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Obligation prononcée liée à la décision sur sanction. Exemple : Ajournement avec mise à l'épreuve.
 */
public class ObligationDecisionSanction extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = 278249658331382110L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.OBLIGATION_DECISION_SANCTION;
    }

}
