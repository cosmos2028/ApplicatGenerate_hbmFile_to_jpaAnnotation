package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Indique si la mesure "Provisions Accordées" a été prononcée.
 */
public class ProvisionsAccordees extends ReparationCivile {

    /**
     *
     */
    private static final long serialVersionUID = -6725420763410483470L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.PROVISIONS_ACCORDEES;
    }

}
