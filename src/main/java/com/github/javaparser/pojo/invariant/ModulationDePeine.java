package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Modulation de peine par infraction. Causes indépendantes des natures d'infraction, susceptibles d'alléger ou
 * d'aggraver le quantum de la peine. Exemple : "exclusion de l'excuse de minorité", "réduction de la peine encourue".
 */
public class ModulationDePeine extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = 9155515399163284071L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.MODULATION_DE_PEINE;
    }

}
