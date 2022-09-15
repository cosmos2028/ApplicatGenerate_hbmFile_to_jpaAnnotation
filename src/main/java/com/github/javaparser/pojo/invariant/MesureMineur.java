package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Le juge peut d�cider de prononcer au p�nal des "mesures de protection, d'assistance, de surveillance et d'�ducation"
 * ou des "sanctions �ducatives". Ces mesures sont li�es � l�ensemble des infractions pour lesquelles le mineur a �r�
 * d�clar� coupable.
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
