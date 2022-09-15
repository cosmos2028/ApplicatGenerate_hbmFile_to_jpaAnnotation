package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Réparations civiles autres que DI, Provision ou 475.1.
 */
public class AutreReparationCivile extends ReparationCivile {

    /**
     *
     */
    private static final long serialVersionUID = 5593627369214605176L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.AUTRE_REPARATION_CIVILE;
    }

}
