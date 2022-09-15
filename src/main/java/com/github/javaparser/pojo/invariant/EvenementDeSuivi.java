/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;

/**
 * Un événement de suivi concerne une mesure ou sur une pièce d'exécution. Il ne porte pas lui-même de mesure.
 */
@RegleDeGestion(RG.EVTM002)
public abstract class EvenementDeSuivi extends Evenement {

}
