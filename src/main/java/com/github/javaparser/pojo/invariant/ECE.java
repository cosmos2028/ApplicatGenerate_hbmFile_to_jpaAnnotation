package fr.gouv.justice.cassiopee.invariant.eurojust.model.ece;

import java.util.Date;
import java.util.Set;

import fr.gouv.justice.cassiopee.administration.acteur.interne.model.ActeurInterne;
import fr.gouv.justice.cassiopee.administration.structure.model.Juridiction;
import fr.gouv.justice.cassiopee.administration.structure.model.Service;
import fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire;
import fr.gouv.justice.cassiopee.invariant.eurojust.model.partenaire.Partenaire;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;

public class ECE {

    private Long id;
    private Affaire affaire;
    private Date dateCreation;
    private Date dateDissolution;
    private String voletAffaire;
    private String lieuOperation;
    private String responsable;
    private Service service;
    private Juridiction juridiction;
    private ActeurInterne acteurInterne;
    private Set<Partenaire> partenaireECEList;
    private Set<Evenement> evenementList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateDissolution() {
        return dateDissolution;
    }

    public void setDateDissolution(Date dateDissolution) {
        this.dateDissolution = dateDissolution;
    }

    public String getVoletAffaire() {
        return voletAffaire;
    }

    public void setVoletAffaire(String voletAffaire) {
        this.voletAffaire = voletAffaire;
    }

    public Set<Partenaire> getPartenaireECEList() {
        return partenaireECEList;
    }

    public void setPartenaireECEList(Set<Partenaire> partenaireECEList) {
        this.partenaireECEList = partenaireECEList;
    }

    public Set<Evenement> getEvenementList() {
        return evenementList;
    }

    public void setEvenementList(Set<Evenement> evenementList) {
        this.evenementList = evenementList;
    }

    public String getLieuOperation() {
        return lieuOperation;
    }

    public void setLieuOperation(String lieuOperation) {
        this.lieuOperation = lieuOperation;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Affaire getAffaire() {
        return affaire;
    }

    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Juridiction getJuridiction() {
        return juridiction;
    }

    public void setJuridiction(Juridiction juridiction) {
        this.juridiction = juridiction;
    }

    public ActeurInterne getActeurInterne() {
        return acteurInterne;
    }

    public void setActeurInterne(ActeurInterne acteurInterne) {
        this.acteurInterne = acteurInterne;
    }

}
