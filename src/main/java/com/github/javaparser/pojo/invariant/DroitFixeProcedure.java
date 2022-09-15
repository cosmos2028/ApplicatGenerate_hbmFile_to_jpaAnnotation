package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Mesure indiquant le droit fixe de procédure à acquitter. Somme forfaitaire appliquée à une personne pour un jugement,
 * une ordonnance pénale ou une ordonnance d'homologation rendu en fonction du type de décision et du contenu (mesures)
 * de cette décision (auteur condamné, CPC abusive avec relaxe du ou des auteurs) et de critères liés à la personne (par
 * ex., les mineurs ne sont pas assujettis). Mesure enregistrée dans DIA afin notamment de faciliter l'extraction des
 * informations à transmettre au Trésor public pour le recouvrement.
 */
public class DroitFixeProcedure extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = 5551620208966536039L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.DROIT_FIXE_PROCEDURE;
    }

}
