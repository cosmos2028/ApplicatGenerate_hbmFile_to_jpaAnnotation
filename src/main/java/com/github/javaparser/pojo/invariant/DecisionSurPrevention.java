package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Décision sur prévention définie pour chaque infraction (coupable, relaxe, ...)
 */
public class DecisionSurPrevention extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = -5522723828369163034L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.DECISION_SUR_PREVENTION;
    }

}
