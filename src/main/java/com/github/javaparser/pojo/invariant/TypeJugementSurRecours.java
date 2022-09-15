package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.DecisionSurRecours;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.PersonneEvenement;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.service.enumeration.PeineOuMesureTypeEnum;

public class TypeJugementSurRecours extends DecisionSurRecours<PersonneEvenement> {

    public Long getDecisionId() {
        return getDecision().getId();
    }

    @Override
    public void beforeSave() {
         if (PeineOuMesureTypeEnum.CONFIRMATION.is(getPeineOuMesureType().getCodeMesure())){
            Personne personne = getDecision().getPersonne();
            personne.setArretOpposableIntervenant(personne.getJugementOpposableIntervenant());
        }
    }
}
