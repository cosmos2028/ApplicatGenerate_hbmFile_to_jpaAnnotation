package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Le juge peut décider de prononcer au pénal des "mesures de protection, d'assistance, de surveillance et d'éducation"
 * ou des "sanctions éducatives". Ces mesures sont liées à l’ensemble des infractions pour lesquelles le mineur a éré
 * déclaré coupable.
 */
public class MesureMineur extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = -3088494453832647455L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.MESURE_MINEUR;
    }

}
