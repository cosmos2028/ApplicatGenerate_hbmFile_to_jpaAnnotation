/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.evenement.service.enumeration.TypeEvenementEnum;

/**
 * Ev�nement de suivi de pi�ce d'ex�cution (�dition). <br>
 * RG.EVTM002 3>
 */
@RegleDeGestion(RG.EVTM002)
public class EvenementDeSuiviPieceDExecution extends EvenementDeSuivi {

    private static final long serialVersionUID = -7690776520476209764L;

    // TODO devra suivre une edition !!!
    // private Edition editionSuivie; +get, set

    @Override
    public TypeEvenementEnum getTypeEvenementEnum() {
        return TypeEvenementEnum.SUIVI_PIECE_EXECUTION;
    }
}
