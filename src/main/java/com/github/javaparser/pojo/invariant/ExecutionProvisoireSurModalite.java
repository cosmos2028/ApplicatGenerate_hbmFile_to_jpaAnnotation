package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Indique l'éventuelle exécution provisoire de la modalité d'exécution, d'application ou personnalisation. Est proposée
 * sous la forme de case à cocher
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
