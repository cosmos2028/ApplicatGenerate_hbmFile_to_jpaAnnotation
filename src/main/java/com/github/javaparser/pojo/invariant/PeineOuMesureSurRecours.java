package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.personneevenement.model.DecisionSurRecours;

public class PeineOuMesureSurRecours extends DecisionSurRecours<PeineOuMesure> {

    public Long getDecisionId() {
        return getDecision().getId();
    }

}
