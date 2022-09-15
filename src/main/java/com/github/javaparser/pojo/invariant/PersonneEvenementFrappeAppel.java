package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.CaracteristiqueType;

/**
 * Voir {@link DecisionFrappeeAppel}.
 */
public class PersonneEvenementFrappeAppel extends DecisionFrappeeAppel<PersonneEvenement> {

    private PersonneEvenement personneEvenement;
    private String codeCaracteristique;
    private String codeMotif;
    private String infractionId;
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
        if (getClass() != obj.getClass())
            return false;
        PersonneEvenementFrappeAppel other = (PersonneEvenementFrappeAppel) obj;
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
    
	public String getCodeCaracteristique() {
		return codeCaracteristique;
	}

	public void setCodeCaracteristique(String codeCaracteristique) {
		this.codeCaracteristique = codeCaracteristique;
	}

	public String getCodeMotif() {
		return codeMotif;
	}

	public void setCodeMotif(String codeMotif) {
		this.codeMotif = codeMotif;
	}

	public String getInfractionId() {
		return infractionId;
	}

	public void setInfractionId(String infractionId) {
		this.infractionId = infractionId;
	}

	

}
