/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.evenement.service.enumeration.TypeEvenementEnum;

/**
 * Un �v�nement de suivi concerne une mesure ou sur une pi�ce d'ex�cution. Il ne porte pas lui-m�me de mesure.<br>
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
