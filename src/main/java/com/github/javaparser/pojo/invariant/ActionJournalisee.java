/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.util.Date;

import fr.gouv.justice.cassiopee.administration.acteur.interne.model.ActeurInterne;
import fr.gouv.justice.cassiopee.administration.structure.model.Service;

/**
 * Type d'action journalis�e
 */
public class ActionJournalisee implements java.io.Serializable {

    private static final long serialVersionUID = -4299687613359470388L;

    private Long id;

    private Date dateAction;

    private TypeActionJournalisee typeActionJournalisee;

    private ActeurInterne responsable;

    private Service responsableService;

    /**
     * peut-�tre nul
     */
    private Depot depot;

    /**
     * peut-�tre nul scelle ou objet en gardiennage
     */
    private Objet objet;

    /**
     * peut-�tre nul
     */
    private PieceConviction pieceConviction;

    private String complementInformatif;

    /**
     * @return Returns the dateAction.
     */
    public Date getDateAction() {
        return dateAction;
    }

    /**
     * @param dateAction
     *            The dateAction to set.
     */
    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

    /**
     * @return Returns the depot.
     */
    public Depot getDepot() {
        return depot;
    }

    /**
     * @param depot
     *            The depot to set.
     */
    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * peut-�tre nul scelle ou objet en gardiennage
     *
     * @return Returns the objet.
     */
    public Objet getObjet() {
        return objet;
    }

    /**
     * peut-�tre nul scelle ou objet en gardiennage
     *
     * @param objet
     *            The objet to set.
     */
    public void setObjet(Objet objet) {
        this.objet = objet;
    }

    /**
     * peut-�tre nul
     *
     * @return Returns the pieceConviction.
     */
    public PieceConviction getPieceConviction() {
        return pieceConviction;
    }

    /**
     * peut-�tre nul
     *
     * @param pieceConviction
     *            The pieceConviction to set.
     */
    public void setPieceConviction(PieceConviction pieceConviction) {
        this.pieceConviction = pieceConviction;
    }

    /**
     * @return Returns the responsable.
     */
    public ActeurInterne getResponsable() {
        return responsable;
    }

    /**
     * @param responsable
     *            The responsable to set.
     */
    public void setResponsable(ActeurInterne responsable) {
        this.responsable = responsable;
    }

    /**
     * @return Returns the responsableService.
     */
    public Service getResponsableService() {
        return responsableService;
    }

    /**
     * @param responsableService
     *            The responsableService to set.
     */
    public void setResponsableService(Service responsableService) {
        this.responsableService = responsableService;
    }

    /**
     * @return Returns the typeActionJournalisee.
     */
    public TypeActionJournalisee getTypeActionJournalisee() {
        return typeActionJournalisee;
    }

    /**
     * @param typeActionJournalisee
     *            The typeActionJournalisee to set.
     */
    public void setTypeActionJournalisee(TypeActionJournalisee typeActionJournalisee) {
        this.typeActionJournalisee = typeActionJournalisee;
    }

    /**
     * @return Returns the complementInformatif.
     */
    public String getComplementInformatif() {
        return complementInformatif;
    }

    /**
     * @param complementInformatif
     *            The complementInformatif to set.
     */
    public void setComplementInformatif(String complementInformatif) {
        this.complementInformatif = complementInformatif;
    }

}
