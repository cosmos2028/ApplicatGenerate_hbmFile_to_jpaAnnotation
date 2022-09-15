package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Obligation liée à une peine prononcée ou requise
 */
public class ObligationSurPeine extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = 895232465921074216L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.OBLIGATION_SUR_PEINE;
    }

}
