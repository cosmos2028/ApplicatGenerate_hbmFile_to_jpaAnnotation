package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.io.Serializable;
import java.util.Set;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;

/**
 * Désigne une entité qui peut être émetteur d'un événement.
 *
 * @param <I>
 *            le type de l'identifiant, peut être un String (code d'objet du SR) ou un Long (id d'objet métier).
 */
@RegleDeGestion(RG.RASM011)
public interface Emetteur<I extends Serializable> extends Correspondant<I> {

    /**
     * retrouve les evenements emis par cette emetteur
     *
     * @return
     */
    Set<Evenement> getEvenementEmis();

}
