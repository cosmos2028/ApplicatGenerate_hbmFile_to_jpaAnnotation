package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Modalité d'application globale relative aux inscriptions au casier (Exclusion du B2, ...) du DIA courant.
 */
public class ModaliteApplicationGlobaleCasier extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = -1461703637525010969L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.MODALITE_APPLICATION_GLOBALE_CASIER;
    }

}
