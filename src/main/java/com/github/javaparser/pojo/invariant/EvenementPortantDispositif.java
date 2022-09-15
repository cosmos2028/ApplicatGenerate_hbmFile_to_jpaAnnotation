/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.evenement.service.enumeration.TypeEvenementEnum;

/**
 * Un événement de suivi concerne une mesure ou sur une pièce d'exécution. Il ne porte pas lui-même de mesure.<br>
 * RG.EVTM002 1>
 */
@RegleDeGestion(RG.EVTM002)
public class EvenementPortantDispositif extends Evenement {

    private static final long serialVersionUID = -2055958837559300082L;

    // TODO pas clair, cf specs DIA, tables PEV

    @Override
    public TypeEvenementEnum getTypeEvenementEnum() {
        return TypeEvenementEnum.PORTANT_DISPOSITIF;
    }

}
