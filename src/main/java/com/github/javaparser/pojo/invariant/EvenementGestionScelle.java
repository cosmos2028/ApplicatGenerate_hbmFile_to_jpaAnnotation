/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.evenement.service.enumeration.TypeEvenementEnum;

/**
 * Evenement de gesion de scell�s. Il s'agit d'un �v�nement sp�cifique au domaine Scell� concernant un ensemble d'objets
 * du domaine Scell�s.<br>
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
