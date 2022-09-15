package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Décision sur sanction définie pour chaque infraction pour laquelle l'auteur a été déclaré coupable (condamné à peine,
 * dispense de peine, ajournement qui peut-être avec obligation, ...).
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
