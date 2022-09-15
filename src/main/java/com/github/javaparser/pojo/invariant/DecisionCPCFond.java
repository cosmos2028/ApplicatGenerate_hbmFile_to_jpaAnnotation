package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Décision sur le fond de la Constitution de la Partie Civile.
 */
public class DecisionCPCFond extends Decision {

    /**
     *
     */
    private static final long serialVersionUID = 5776835602756282905L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.DECISION_CPC_FOND;
    }

}
