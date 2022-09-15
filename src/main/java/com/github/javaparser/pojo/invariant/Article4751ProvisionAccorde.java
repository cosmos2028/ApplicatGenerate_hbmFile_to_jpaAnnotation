package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

public class Article4751ProvisionAccorde extends ReparationCivile {

    /**
     *
     */
    private static final long serialVersionUID = -7290908256158597672L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.ARTICLE_475_1_PROVISION_ACCORDE;
    }

}
