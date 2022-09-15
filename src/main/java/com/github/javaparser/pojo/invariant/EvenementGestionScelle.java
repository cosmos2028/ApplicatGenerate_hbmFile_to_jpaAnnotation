/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.evenement.service.enumeration.TypeEvenementEnum;

/**
 * Evenement de gesion de scellés. Il s'agit d'un événement spécifique au domaine Scellé concernant un ensemble d'objets
 * du domaine Scellés.<br>
 * RG.EVTM002 7>
 */
@RegleDeGestion(RG.EVTM002)
public class EvenementGestionScelle extends Evenement {

    private static final long serialVersionUID = -8530266400464239266L;

    @Override
    public TypeEvenementEnum getTypeEvenementEnum() {
        return TypeEvenementEnum.GESTION_SCELLE;
    }

}
