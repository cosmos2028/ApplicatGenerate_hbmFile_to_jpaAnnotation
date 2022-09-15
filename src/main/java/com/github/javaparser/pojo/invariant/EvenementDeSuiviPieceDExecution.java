/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.evenement.service.enumeration.TypeEvenementEnum;

/**
 * Evénement de suivi de pièce d'exécution (édition). <br>
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
