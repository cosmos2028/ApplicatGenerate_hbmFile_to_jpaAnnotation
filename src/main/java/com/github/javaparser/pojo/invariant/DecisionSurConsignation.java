package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Décision concernant les consignations et suivi.
 */
public class DecisionSurConsignation extends Decision {

    /**
     *
     */
    private static final long serialVersionUID = -4257939748533334206L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.DECISION_SUR_CONSIGNATION;
    }

}
