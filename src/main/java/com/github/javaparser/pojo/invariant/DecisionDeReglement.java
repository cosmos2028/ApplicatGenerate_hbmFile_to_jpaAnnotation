package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * D�cision prise par le juge d'instruction sur les liens infraction-auteur lors de l'ordonnance de r�glement. Cette
 * d�cision est une mesure qui peut �tre non-lieu, ou renvoi devant un tribunal ou une mise en accusation.
 */
public class DecisionDeReglement extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = -2598237067804328351L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.DECISION_DE_REGLEMENT;
    }

}
