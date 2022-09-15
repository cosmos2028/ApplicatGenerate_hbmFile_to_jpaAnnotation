package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Indique si la demande concernant l'article 475.1 a été accordée.
 */
public class Article4751Accorde extends ReparationCivile {

    /**
     *
     */
    private static final long serialVersionUID = -2673023902588035634L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.ARTICLE_475_1_ACCORDE;
    }

}
