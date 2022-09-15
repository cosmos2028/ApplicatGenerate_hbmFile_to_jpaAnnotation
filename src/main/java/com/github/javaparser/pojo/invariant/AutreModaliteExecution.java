package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Modalité d'exécution autre que le sursis et la peine si inobservation.
 */
public class AutreModaliteExecution extends ModaliteExecutionOuApplicationOuPersonnalisation {

    /**
     *
     */
    private static final long serialVersionUID = -509359409237799877L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.AUTRE_MODALITE_EXECUTION;
    }

}
