package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

public class PersonneEvenementSurRecours extends DecisionSurRecours<PersonneEvenement> {

    public Long getDecisionId() {
        return getDecision().getId();
    }

}
