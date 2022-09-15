/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.evenement.service.enumeration.TypeEvenementEnum;

/**
 * Evénement juridique sur scellés. Permet de tracer une décision juridique concernant les scellés sans lien avec un
 * objet du domaine SCELLES. Il s'agit notamment d'une décision de restitution de scellés prononcée lors du jugement.<br>
 * RG.EVTM002 6>
 */
@RegleDeGestion(RG.EVTM002)
public class EvenementJuridiqueScelle extends EvenementGestionScelle {

    private static final long serialVersionUID = 6436460293586716294L;

    @Override
    public TypeEvenementEnum getTypeEvenementEnum() {
        return TypeEvenementEnum.JURIDIQUE_SCELLE;
    }

}
