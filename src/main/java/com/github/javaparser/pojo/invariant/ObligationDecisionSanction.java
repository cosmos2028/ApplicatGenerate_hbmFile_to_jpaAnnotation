package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Obligation prononc�e li�e � la d�cision sur sanction. Exemple : Ajournement avec mise � l'�preuve.
 */
public class ObligationDecisionSanction extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = 278249658331382110L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.OBLIGATION_DECISION_SANCTION;
    }

}
