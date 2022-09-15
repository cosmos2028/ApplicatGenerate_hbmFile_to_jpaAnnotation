package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.personneevenement.model.DecisionSurRecours;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.DetailDommageInteret;

public class DetailDommageInteretSurRecours extends DecisionSurRecours<DetailDommageInteret> {

    public Long getDecisionId() {
        return getDecision().getId();
    }

}
