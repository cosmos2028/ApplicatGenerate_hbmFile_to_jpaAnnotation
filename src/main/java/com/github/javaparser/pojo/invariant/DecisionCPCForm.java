package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * D�cision sur la forme de la Constitution de la Partie Civile. Indique si la constitution de PC est recevable ou non.
 * Peut �tre motiv�e en cas de rejet. Est le � pendant � des � d�cisions proc�durales auteur �.
 */
public class DecisionCPCForm extends Decision {

    /**
     *
     */
    private static final long serialVersionUID = -1989179837943595779L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.DECISION_CPC_FORM;
    }

}
