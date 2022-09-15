package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.util.ArrayList;
import java.util.List;

import fr.gouv.justice.cassiopee.service.audience.model.Audience;

public class MajEvenement {


    private Evenement evenement;

    private List<MajCaracteristique> caracteristiques = new ArrayList<>();
    
    private boolean isPersonnesConcernneesModified ;
    
    private Audience audiencebeforeUpdate;
    

    public MajEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public List<MajCaracteristique> getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(List<MajCaracteristique> caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    public boolean isPersonnesConcernneesModified() {
        return isPersonnesConcernneesModified;
    }

    public void setPersonnesConcernneesModified(boolean isPersonnesConcernneesModified) {
        this.isPersonnesConcernneesModified = isPersonnesConcernneesModified;
    }

    public Audience getAudiencebeforeUpdate() {
        return audiencebeforeUpdate;
    }

    public void setAudiencebeforeUpdate(Audience audiencebeforeUpdate) {
        this.audiencebeforeUpdate = audiencebeforeUpdate;
    }

    
}
