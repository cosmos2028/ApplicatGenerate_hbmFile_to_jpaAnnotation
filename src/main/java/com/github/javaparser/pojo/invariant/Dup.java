package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;
import java.sql.Date;

import fr.gouv.justice.cassiopee.administration.acteur.interne.model.ActeurInterne;
import fr.gouv.justice.cassiopee.administration.structure.model.Service;
import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;
import fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire;

public class Dup implements Serializable, Cloneable {

    /**
     *
     */
    private static final long serialVersionUID = -6974962256437844624L;

    private static final HashCodeEqualsHelper<Dup> HE_HELPER = HashCodeEqualsHelper.of(Dup.class, Dup::getAffaire,
            Dup::getPersonne);


    /**
     *
     */
    private Long id;

    /**
     *
     */
    private Personne personne;

    /**
     *
     */
    private Affaire affaire;

    /**
     *
     */
    private Date dateCreation;

    private String tribunalRattachement;

    private ActeurInterne magistrat;

    private Service service;

    /**
     *
     */
    private Date dateArchivage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Affaire getAffaire() {
        return affaire;
    }

    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateArchivage() {
        return dateArchivage;
    }

    public void setDateArchivage(Date dateArchivage) {
        this.dateArchivage = dateArchivage;
    }

    public String getTribunalRattachement() {
        return tribunalRattachement;
    }

    public void setTribunalRattachement(String tribunalRattachement) {
        this.tribunalRattachement = tribunalRattachement;
    }

    /**
     * @return the magistrat
     */
    public ActeurInterne getMagistrat() {
        return magistrat;
    }

    /**
     * @param magistrat
     *            the magistrat to set
     */
    public void setMagistrat(ActeurInterne magistrat) {
        this.magistrat = magistrat;
    }

    /**
     * @return the service
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service
     *            the service to set
     */
    public void setService(Service service) {
        this.service = service;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return HE_HELPER.getHashCode(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        return HE_HELPER.areEqual(this, other);
    }

    /**
     * duplique un Dup
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Dup dup = (Dup) super.clone();
        return dup;
    }

}
