package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Exécution provisoire de la peine prononcée ou requise. Est proposée sous la forme de case à cocher
 */
public class ExecutionProvisoireSurPeine extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = 7000400397584021503L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.EXECUTION_PROVISOIRE_SUR_PEINE;
    }

}
