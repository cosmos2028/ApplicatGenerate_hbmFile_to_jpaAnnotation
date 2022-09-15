package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Décision procédurale concernant l'exception levée.
 */
public class DecisionProcedurale extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = -7933756332292606868L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.DECISION_PROCEDURALE;
    }

}
