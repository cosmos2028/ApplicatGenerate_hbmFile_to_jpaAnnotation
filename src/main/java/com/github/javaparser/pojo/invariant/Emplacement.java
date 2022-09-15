/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Objet métier décrivant chacun des différents emplacements que contient un local. Un emplacement est typé en fonction
 * du type de scellé qu'il peut contenir (arme, périssables ...) Chaque emplacement possède un certain nombre de places
 * possibles.
 */
public class Emplacement implements java.io.Serializable {

    private static final long serialVersionUID = -4299687613359470387L;

    private Long id;

    private Local local;

    private String mnemonique;

    private Integer nombrePlacesPossibles;

    private Integer seuilAlerte;

    private TypeScelle typeScelle;

    private Date dateOuverture;

    private Date dateFermeture;

    private String description;

    private Set<Objet> objets = new HashSet<Objet>();

    /**
     *
     */
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     */
    public Local getLocal() {
        return this.local;
    }

    public void setLocal(Local fkSceEmpLoc) {
        this.local = fkSceEmpLoc;
    }

    /**
     *
     */
    public String getMnemonique() {
        return this.mnemonique;
    }

    public void setMnemonique(String mnemonique) {
        this.mnemonique = mnemonique;
    }

    /**
     *
     */
    public Integer getNombrePlacesPossibles() {
        return this.nombrePlacesPossibles;
    }

    public void setNombrePlacesPossibles(Integer nombrePlacesPossibles) {
        this.nombrePlacesPossibles = nombrePlacesPossibles;
    }

    /**
     *
     */
    public Integer getSeuilAlerte() {
        return this.seuilAlerte;
    }

    public void setSeuilAlerte(Integer seuilAlerte) {
        this.seuilAlerte = seuilAlerte;
    }

    /**
     * @return the typeScelle
     */
    public TypeScelle getTypeScelle() {
        return typeScelle;
    }

    /**
     * @param typeScelle
     *            typeScelle à affecter
     */
    public void setTypeScelle(TypeScelle typeScelle) {
        this.typeScelle = typeScelle;
    }

    /**
     *
     */
    public Date getDateOuverture() {
        return this.dateOuverture;
    }

    public void setDateOuverture(Date dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    /**
     *
     */
    public Date getDateFermeture() {
        return this.dateFermeture;
    }

    public void setDateFermeture(Date dateFermeture) {
        this.dateFermeture = dateFermeture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     */
    public Set<Objet> getObjets() {
        return this.objets;
    }

    public void setObjets(Set<Objet> objets) {
        this.objets = objets;
    }

}
