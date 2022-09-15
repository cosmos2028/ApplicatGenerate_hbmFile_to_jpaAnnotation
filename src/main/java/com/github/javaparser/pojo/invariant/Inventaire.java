/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.util.Date;
import java.util.HashSet;

import fr.gouv.justice.cassiopee.administration.structure.model.Tgi;

/**
 * Un inventaire est constitu� � partir du stock dont sont extraits en une ou plusieurs fois, en fonction de leur type,
 * des pi�ces � conviction ou des objets en gardiennage. Des pi�ces ou objets peuvent �galement y �tre ajout�s / retir�s
 * individuellement par l'agent.
 * <p>
 * NOTE: Le stock n'est pas mod�lis� en tant que classe. L'appartenance ou pas des pi�ces � conviction ou objets en
 * gardiennage au stock se d�duit de l'attribut "indicateur stock" pr�sent au niveau de ces deux classes.
 */
public class Inventaire implements java.io.Serializable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -4299687613359470387L;

    /** Identifiant de l'inventaire */
    private Long id;

    /** Tgi contenant l'invantaire */
    private Tgi tgi;

    /** �tat de l'inventaire */
    private EtatInventaire etatInventaire;

    /** identifiant utilisateur de l'inventaire */
    private String identifiantInventaire;

    /** titre de l'inventaire */
    private String titreInventaire;

    /** Date de cr�ation de l'inventaire */
    private Date dateCreation;

    /** Date de modofication de l'inventaire */
    private Date dateModification;

    /** liste des PC contenu par l'inventaire */
    private java.util.Set<PieceConviction> pieceConvictions = new HashSet<PieceConviction>();

    /** listes des Objets en gardiennage contenu par l'inventaire */
    private java.util.Set<Objet> objets = new HashSet<Objet>();

    /**
     * Retourne l'identifier de l'inventaire
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Valorise l'identifier
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne l'�tat de l'inventaire
     */
    public EtatInventaire getEtatInventaire() {
        return this.etatInventaire;
    }

    /**
     * Valorise l'�tat de l'inventaire
     *
     * @param etatInventaire
     */
    public void setEtatInventaire(EtatInventaire etatInventaire) {
        this.etatInventaire = etatInventaire;
    }

    /**
     * Retourne l'identifiant utilisateur de l'inventaire
     */
    public String getIdentifiantInventaire() {
        return this.identifiantInventaire;
    }

    /**
     * Valorise l'identifiant utilisateur de l'inventaire
     *
     * @param identifiantInventaire
     */
    public void setIdentifiantInventaire(String identifiantInventaire) {
        this.identifiantInventaire = identifiantInventaire;
    }

    /**
     * Retourne le titre
     */
    public String getTitreInventaire() {
        return this.titreInventaire;
    }

    /**
     * Valorise le titre
     *
     * @param titreInventaire
     */
    public void setTitreInventaire(String titreInventaire) {
        this.titreInventaire = titreInventaire;
    }

    /**
     * Retourne la date de modification
     */
    public Date getDateModification() {
        return this.dateModification;
    }

    /**
     * Valorise la date de modification
     *
     * @param dateModification
     */
    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    /**
     * Retourne la liste des PCs de l'inventaire
     */
    public java.util.Set<fr.gouv.justice.cassiopee.invariant.scelle.model.PieceConviction> getPieceConvictions() {
        return this.pieceConvictions;
    }

    /**
     * Valorise la liste des PCs de l'inventaire
     *
     * @param pieceConvictions
     */
    public void setPieceConvictions(
            java.util.Set<fr.gouv.justice.cassiopee.invariant.scelle.model.PieceConviction> pieceConvictions) {
        this.pieceConvictions = pieceConvictions;
    }

    /**
     * Retourne la liste des OGs de l'inventaire
     */
    public java.util.Set<fr.gouv.justice.cassiopee.invariant.scelle.model.Objet> getObjets() {
        return this.objets;
    }

    /**
     * Valorise la liste des OGs de l'inventaire
     *
     * @param objets
     */
    public void setObjets(java.util.Set<fr.gouv.justice.cassiopee.invariant.scelle.model.Objet> objets) {
        this.objets = objets;
    }

    /**
     * Retourne le TGI
     *
     * @return Returns the tgi.
     */
    public Tgi getTgi() {
        return tgi;
    }

    /**
     * Valorise le TGI
     *
     * @param tgi
     *            The tgi to set.
     */
    public void setTgi(Tgi tgi) {
        this.tgi = tgi;
    }

    /**
     * Retourne la Date de creation de l'inventaire
     *
     * @return Returns the dateCreation.
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     * Valorise la Date de creation de l'inventaire
     *
     * @param dateCreation
     *            The dateCreation to set.
     */
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
