package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * D�cision sur sanction d�finie pour chaque infraction pour laquelle l'auteur a �t� d�clar� coupable (condamn� � peine,
 * dispense de peine, ajournement qui peut-�tre avec obligation, ...).
 */
public class DecisionSurSanction extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = 9206530489165057003L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.DECISION_SUR_SANCTION;
    }

}
