package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Modalité d'exécution de la réparation civile accordée.
 */
public class ModaliteExecutionReparationAccordee extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = 3415186844790768533L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.MODALITE_EXECUTION_REPARATION_ACCORDEE;
    }

}
