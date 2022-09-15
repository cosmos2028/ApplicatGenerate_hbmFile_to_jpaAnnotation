/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;

/**
 * Un �v�nement de suivi concerne une mesure ou sur une pi�ce d'ex�cution. Il ne porte pas lui-m�me de mesure.
 */
@RegleDeGestion(RG.EVTM002)
public abstract class EvenementDeSuivi extends Evenement {

}
