/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.io.Serializable;
import java.util.Set;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;

/**
 * Désigne une entité qui peut être destinataire d'un événement.
 *
 * @param <I>
 *            classe de l'identifiant: il peut s'agir soit d'un String (code) ou d'un Long (id).
 */
@RegleDeGestion(RG.RASM011)
public interface Destinataire<I extends Serializable> extends Correspondant<I> {

    /**
     * evenement reçus par ce destinataire
     *
     * @return
     */
    Set<Evenement> getEvenementRecus();

}
