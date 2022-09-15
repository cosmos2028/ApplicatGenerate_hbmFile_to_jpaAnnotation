package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Obligation liée à la mesure pour mineur
 */
public class ObligationMesureMineur extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = 5957178361619125714L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.OBLIGATION_MESURE_MINEUR;
    }

}
