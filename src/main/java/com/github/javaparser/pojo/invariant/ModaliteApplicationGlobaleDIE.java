package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Modalit� d'application globale s'appliquant � des d�cisions incluses dans des dispositifs d'autres jugements
 * (confusion de peine, r�vocation de sursis, ...)
 */
public class ModaliteApplicationGlobaleDIE extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = 3094433707561261878L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.MODALITE_APPLICATION_GLOBALE_DIE;
    }

}
