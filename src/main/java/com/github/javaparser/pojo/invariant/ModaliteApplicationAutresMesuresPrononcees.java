package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

public class ModaliteApplicationAutresMesuresPrononcees extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = 3671418672019190166L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.MODALITE_APPLICATION_GLOBALE_AUTRES_MESURES_PRONONCEES;
    }

}
