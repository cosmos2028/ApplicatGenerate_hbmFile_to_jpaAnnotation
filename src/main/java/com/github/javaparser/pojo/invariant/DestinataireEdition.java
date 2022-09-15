/*
 * Ce document est la propriété d’Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import fr.gouv.justice.cassiopee.commons.metier.model.IdLibelle;

/**
 * interface pour les detinataire d'une édition.
 *
 * @param <T>
 */
public interface DestinataireEdition<T> extends IdLibelle {

    T getDestinataireEditionId();

}
