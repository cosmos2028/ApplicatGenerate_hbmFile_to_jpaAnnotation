package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.DecisionSurRecours;

public class EvenementSurRecours extends DecisionSurRecours<Evenement> {

    public Long getDecisionId() {
        return getDecision().getId();
    }

}
