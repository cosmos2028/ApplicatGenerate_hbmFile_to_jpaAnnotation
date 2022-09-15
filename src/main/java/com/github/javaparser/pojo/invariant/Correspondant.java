package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.io.Serializable;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.CorrespondantTypeEnum;

/**
 * Désigne une entité qui peut être destinataire ou emetteur d'un événement.
 *
 * @param <I>
 *            classe de l'identifiant
 */
@RegleDeGestion(RG.RASM011)
public interface Correspondant<I extends Serializable> {

    /**
     * Retourne l'identifiant de ce Correspondant, attention ce n'est pas l'Identifier mais un String (code) ou un Long
     * (id).
     *
     * @return id
     */
    I getCorrespondantId();

    /**
     * Renvoie le type de ce Correspondant.
     *
     * @return un CorrespondantTypeEnumeration correspondant au type de ce Correspondant
     */
    CorrespondantTypeEnum getCorrespondantType();

    /**
     * Renvoie le libelle de ce Correspondant.
     *
     * @return libelle
     */
    @RegleDeGestion(RG.EVTM015)
    String getCorrespondantLibelle();
}
