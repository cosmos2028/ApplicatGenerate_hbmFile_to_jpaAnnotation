/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.evenement.service.enumeration.TypeEvenementEnum;

/**
 * Il s'agit des �v�nements ne faisant pas partie des autres types donc sans mesure li�e, sans dispositif li�, sans
 * infraction li�e et ne faisant pas partie du domaine Scell�s.<br>
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
