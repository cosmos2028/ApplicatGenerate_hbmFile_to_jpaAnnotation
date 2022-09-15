package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Obligation liée à la modalité d'exécution, d'application ou de personnalisation d'une peine prononcée ou requise
 */
// TODO classe pas utilisé
public class ObligationSurModaliteExecutionOuApplicationOuPersonnalisation extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = -5375164095219402642L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.OBLIGATION_SUR_MODALITE_EXECUTION_OU_APPLICATION_OU_PERSONNALISATION;
    }

}
