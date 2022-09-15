/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;

/**
 * Etat d'un événement.<br>
 * un évenement peut être actif, erroné, purge.
 *
 * @see fr.gouv.justice.cassiopee.invariant.evenement.service.enuration.EtatEvenementEnum
 */
@SuppressWarnings("nls")
public class EtatEvenement extends Codification {

    private static final long serialVersionUID = -8513831542087968856L;

}
