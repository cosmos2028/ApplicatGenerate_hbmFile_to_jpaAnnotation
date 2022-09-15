/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.evenement.service.enumeration.TypeEvenementEnum;

/**
 * Un �v�nement portant, c'est-�-dire cr�ant, une ou plusieurs mesures. RG.EVTM002 4>
 */
@RegleDeGestion(RG.EVTM002)
public class EvenementPortantMesure extends Evenement {

    private static final long serialVersionUID = -7690776520476209764L;

    @Override
    public TypeEvenementEnum getTypeEvenementEnum() {
        return TypeEvenementEnum.PORTANT_MESURE;
    }
}
