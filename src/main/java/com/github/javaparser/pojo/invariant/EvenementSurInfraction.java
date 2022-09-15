package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.evenement.service.enumeration.TypeEvenementEnum;

/**
 * Evenement d'orientation sur infraction. Il s'agit d'événement concernant les infractions de l'affaire soit pour un à
 * n auteurs (PERSONNE CONCERNEE) soit sans auteur identifié (cas des affaires contre X). <br>
 * EVTM002 5>
 */
@RegleDeGestion(RG.EVTM002)
public class EvenementSurInfraction extends Evenement {

    private static final long serialVersionUID = 1306734336118852385L;

    @Override
    public TypeEvenementEnum getTypeEvenementEnum() {
        return TypeEvenementEnum.ORIENTATION_SUR_INFRACTION;
    }

}
