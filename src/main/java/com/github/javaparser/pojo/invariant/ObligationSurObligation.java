package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Dans le cadre de Perben II, il est possible de prononcer un 4�me niveau de mesure, les obligations d'obligation.
 * Exemple : les obligations li�es � la modalit� d'ex�cution "Fractionnement" d'une PPL.
 */
public class ObligationSurObligation extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = -9202985928227356058L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.OBLIGATION_SUR_OBLIGATION;
    }

}
