package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Exécution provisoire sur la mesure mineur Est proposée sous la forme de case à cocher
 */
public class ExecutionProvisoireMesureMineur extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = 2751117294928775133L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.EXECUTION_PROVISOIRE_MESURE_MINEUR;
    }

}
