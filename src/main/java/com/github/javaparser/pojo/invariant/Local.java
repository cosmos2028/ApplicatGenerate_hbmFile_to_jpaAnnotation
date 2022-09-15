/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.administration.structure.model.Tgi;

/**
 * Objet m�tier d�signant un local servant � l'entreposage de scell�s. Un local sera d�crit par un descriptif (zone de
 * texte libre) qui permet de le caract�riser. Un local peut aussi bien �tre un entrep�t, qu'une salle donn�e d'un
 * TGI...
 */
public class Local implements java.io.Serializable {

    private static final long serialVersionUID = 148618646846L;

    private Long id;

    private Tgi tgi;

    private String mnemonique;

    private Boolean indicateurVirtuel;

    private String description;

    private Date dateOuverture;

    private Date dateFermeture;

    private java.util.Set<Emplacement> emplacements = new HashSet<Emplacement>();

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
    public Tgi getTgi() {
        return this.tgi;
    }

    public void setTgi(Tgi tgi) {
        this.tgi = tgi;
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
    public Boolean getIndicateurVirtuel() {
        return this.indicateurVirtuel;
    }

    public void setIndicateurVirtuel(Boolean indicateurVirtuel) {
        this.indicateurVirtuel = indicateurVirtuel;
    }

    /**
     *
     */
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    /**
     * Renvoie les emplacements de ce local.
     */
    public Set<Emplacement> getEmplacements() {
        return this.emplacements;
    }

    /**
     * Valorise les emplacements de ce local.
     *
     * @param emplacements
     */
    public void setEmplacements(Set<Emplacement> emplacements) {
        this.emplacements = emplacements;
    }

}
