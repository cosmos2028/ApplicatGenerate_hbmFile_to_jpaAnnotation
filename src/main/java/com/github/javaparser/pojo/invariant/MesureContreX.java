package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * PeinePrononcee (utilsée à l'enregistrement d'une peineOuMesurePEM)
 */
public class MesureContreX extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = 151017167260579283L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.MESURE_CONTRE_X;
    }

}
