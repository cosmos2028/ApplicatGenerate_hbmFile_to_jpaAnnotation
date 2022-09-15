package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Modalité d'exécution, de type sursis
 */
public class Sursis extends ModaliteExecutionOuApplicationOuPersonnalisation {

    /**
     *
     */
    private static final long serialVersionUID = 81280946270059789L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.SURSIS;
    }

}
