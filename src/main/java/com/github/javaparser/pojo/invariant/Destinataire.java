/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.io.Serializable;
import java.util.Set;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;

/**
 * D�signe une entit� qui peut �tre destinataire d'un �v�nement.
 *
 * @param <I>
 *            classe de l'identifiant: il peut s'agir soit d'un String (code) ou d'un Long (id).
 */
@RegleDeGestion(RG.RASM011)
public interface Destinataire<I extends Serializable> extends Correspondant<I> {

    /**
     * evenement re�us par ce destinataire
     *
     * @return
     */
    Set<Evenement> getEvenementRecus();

}
