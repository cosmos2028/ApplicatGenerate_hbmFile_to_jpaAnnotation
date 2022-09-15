package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Mesure indiquant le droit fixe de proc�dure � acquitter. Somme forfaitaire appliqu�e � une personne pour un jugement,
 * une ordonnance p�nale ou une ordonnance d'homologation rendu en fonction du type de d�cision et du contenu (mesures)
 * de cette d�cision (auteur condamn�, CPC abusive avec relaxe du ou des auteurs) et de crit�res li�s � la personne (par
 * ex., les mineurs ne sont pas assujettis). Mesure enregistr�e dans DIA afin notamment de faciliter l'extraction des
 * informations � transmettre au Tr�sor public pour le recouvrement.
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
