/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.evenement.service.enumeration.TypeEvenementEnum;

/**
 * Il s'agit des événements ne faisant pas partie des autres types donc sans mesure liée, sans dispositif lié, sans
 * infraction liée et ne faisant pas partie du domaine Scellés.<br>
 * EVTM002 8>
 */
@RegleDeGestion(RG.EVTM002)
public class EvenementSimple extends Evenement {

    private static final long serialVersionUID = 2411047415631573230L;

    @Override
    public TypeEvenementEnum getTypeEvenementEnum() {
        return TypeEvenementEnum.SIMPLE;
    }

}
