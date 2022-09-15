/*
 * Ce document est la propri�t� d�Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import fr.gouv.justice.cassiopee.commons.metier.model.IdLibelle;

/**
 * interface pour les detinataire d'une �dition.
 *
 * @param <T>
 */
public interface DestinataireEdition<T> extends IdLibelle {

    T getDestinataireEditionId();

}
