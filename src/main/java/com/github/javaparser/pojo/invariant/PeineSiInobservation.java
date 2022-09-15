package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Modalité de la peine prononcée ou requise en cas d'inobservation (concerne les articles 44 et 147 de Perben II)
 */
public class PeineSiInobservation extends ModaliteExecutionOuApplicationOuPersonnalisation {

    /**
     *
     */
    private static final long serialVersionUID = -8162218721922624943L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.PEINE_SI_INOBSERVATION;
    }

}
