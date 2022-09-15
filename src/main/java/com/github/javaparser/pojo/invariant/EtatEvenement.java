/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;

/**
 * Etat d'un �v�nement.<br>
 * un �venement peut �tre actif, erron�, purge.
 *
 * @see fr.gouv.justice.cassiopee.invariant.evenement.service.enuration.EtatEvenementEnum
 */
@SuppressWarnings("nls")
public class EtatEvenement extends Codification {

    private static final long serialVersionUID = -8513831542087968856L;

}
