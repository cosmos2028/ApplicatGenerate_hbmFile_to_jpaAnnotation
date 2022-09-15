package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.personneevenement.model.DecisionFrappeeAppel;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.PersonneEvenement;

/**
 * Voir {@link DecisionFrappeeAppel}.
 */
public class TypeJugementFrappeAppel extends DecisionFrappeeAppel<PersonneEvenement> {

    private PersonneEvenement personneEvenement;

    public PersonneEvenement getPersonneEvenement() {
        return personneEvenement;
    }

    public void setPersonneEvenement(PersonneEvenement personneEvenement) {
        this.personneEvenement = personneEvenement;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((personneEvenement == null) ? 0 : personneEvenement.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (obj == null || getClass() != obj.getClass())
            return false;
        TypeJugementFrappeAppel other = (TypeJugementFrappeAppel) obj;
        if (personneEvenement == null) {
            if (other.personneEvenement != null)
                return false;
        } else if (!personneEvenement.equals(other.personneEvenement))
            return false;
        return true;
    }

    @Override
    public PersonneEvenement getDecision() {
        return personneEvenement;
    }

    @Override
    public void setDecision(PersonneEvenement decision) {
        this.personneEvenement = decision;
    }

    @Override
    public Long getDecisionId() {
        return personneEvenement.getId();
    }


}
