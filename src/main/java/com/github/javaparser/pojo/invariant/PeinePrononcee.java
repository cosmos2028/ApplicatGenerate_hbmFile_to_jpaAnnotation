package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * PeinePrononcee (utilsée à l'enregistrement d'une peineOuMesurePEM)
 */
public class PeinePrononcee extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = -4133244377748671685L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.PEINE_PRONONCEE;
    }

}
