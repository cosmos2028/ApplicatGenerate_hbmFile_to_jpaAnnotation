package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * D�tail des dommages et int�r�ts accord�s lors du jugement.
 */
public class DIAccordes extends ReparationCivile {

    /**
     *
     */
    private static final long serialVersionUID = -5307389496595267344L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.DI_ACCORDES;
    }

}
