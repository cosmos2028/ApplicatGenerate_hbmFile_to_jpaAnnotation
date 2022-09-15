package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Indique l'�ventuelle ex�cution provisoire de la modalit� d'ex�cution, d'application ou personnalisation. Est propos�e
 * sous la forme de case � cocher
 */
public class ExecutionProvisoireSurModalite extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = -4050938519716792955L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.EXECUTION_PROVISOIRE_SUR_MODALITE;
    }

}
