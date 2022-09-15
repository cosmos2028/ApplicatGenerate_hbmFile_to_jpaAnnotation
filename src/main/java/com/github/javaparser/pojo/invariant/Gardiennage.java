/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.util.Date;
import java.util.HashSet;

import fr.gouv.justice.cassiopee.administration.structure.model.Service;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;

/**
 * Gardiennage: repr�sente un tiers "�l�ment de structure" (local, par exemple une fourri�re) au sein duquel un objet a
 * �t� plac� en garde pour une limite de temps donn�e. L'objet m�tier "Gardiennage" est donc ce triplet (�l�ment de
 * structure, objet, limites dans le temps).
 */
public class Gardiennage implements java.io.Serializable {

    private static final long serialVersionUID = -4299687613359470387L;

    private Long id;

    private ObjetEnGardiennage objet;

    private ElementStructure elementStructure;

    private Gardiennage gardiennagePrecedent;

    private Service servicePayeur;

    private Date dateDebutPlacement;

    private Date dateFinPlacement;

    private String numeroObjetGardien;

    private String referenceGardien;

    private java.util.Set<Transfert> transferts = new HashSet<Transfert>();

    private java.util.Set<Gardiennage> gardiennageSuivants = new HashSet<Gardiennage>();

    private Memoire memoire;

    /**
     * Sous type de la PC contenu par le scell�.
     */
    private SousTypeScelle codeSousTypeScelle;

    /**
     * Libelle du sous type de lobjet, pour la saisie libre.
     */
    private String libelleSousTypeScelle;

    /**
     * Nature de lobjet
     */
    private String nature;

    /**
     * Nombre de lobjet
     */
    private Short nombre;

    /**
     * Date du retour de lobjet
     */
    private Date dateSuiteRetourPiece;

    /**
     * Valeur marchande des caract�ristiques d'un objet en gardiennage
     */
    private Boolean indicateurCaracValeurMarchande;

    /**
     * Commentaire suite � retour de lobjet
     */
    private String commentaireSuiteRetourPiece;

    private PieceObjetEnGardiennage pieceObjetEnGardiennage;

    private ArmeGardiennage armeGardiennage;

    private PerissableGardiennage perissableGardiennage;

    private MunitionGardiennage munitionGardiennage;

    private PieceMonetaireGardiennage pieceMonetaireGardiennage;

    /**
     * @return the memoire
     */
    public Memoire getMemoire() {
        return memoire;
    }

    /**
     * @param memoire
     *            memoire � affecter
     */
    public void setMemoire(Memoire memoire) {
        this.memoire = memoire;
    }

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
    public ObjetEnGardiennage getObjet() {
        return this.objet;
    }

    public void setObjet(ObjetEnGardiennage fkSceGarObj) {
        this.objet = fkSceGarObj;
    }

    /**
     * Renvoie le gardien de l'objet. C'est l'�l�ment structure qui a la garde de l'objet.
     *
     * @return l' ElementStructure qui a la garde de l'objet.
     */
    public ElementStructure getElementStructure() {
        return this.elementStructure;
    }

    /**
     * Valorise l'�l�ment de structure gardien de l'objet.
     *
     * @param elementStructure
     */
    public void setElementStructure(
            fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure elementStructure) {
        this.elementStructure = elementStructure;
    }

    /**
     * @return le Gardiennage pr�c�dent
     */
    public Gardiennage getGardiennagePrecedent() {
        return this.gardiennagePrecedent;
    }

    /**
     * Valorise le gardiennage pr�c�dent.
     *
     * @param gardiennagePrecedent
     */
    public void setGardiennagePrecedent(Gardiennage gardiennagePrecedent) {
        this.gardiennagePrecedent = gardiennagePrecedent;
    }

    /**
     *
     */
    public fr.gouv.justice.cassiopee.administration.structure.model.Service getServicePayeur() {
        return this.servicePayeur;
    }

    public void setServicePayeur(fr.gouv.justice.cassiopee.administration.structure.model.Service sceSceGarPaySerPay) {
        this.servicePayeur = sceSceGarPaySerPay;
    }

    /**
     *
     */
    public Date getDateDebutPlacement() {
        return this.dateDebutPlacement;
    }

    public void setDateDebutPlacement(Date dateDebutPlacement) {
        this.dateDebutPlacement = dateDebutPlacement;
    }

    /**
     *
     */
    public Date getDateFinPlacement() {
        return this.dateFinPlacement;
    }

    public void setDateFinPlacement(Date dateFinPlacement) {
        this.dateFinPlacement = dateFinPlacement;
    }

    public String getNumeroObjetGardien() {
        return numeroObjetGardien;
    }

    public void setNumeroObjetGardien(String numeroObjetGardien) {
        this.numeroObjetGardien = numeroObjetGardien;
    }

    /**
     *
     */
    public java.util.Set<fr.gouv.justice.cassiopee.invariant.scelle.model.Transfert> getTransferts() {
        return this.transferts;
    }

    public void setTransferts(java.util.Set<fr.gouv.justice.cassiopee.invariant.scelle.model.Transfert> transferts) {
        this.transferts = transferts;
    }

    /**
     *
     */
    public java.util.Set<fr.gouv.justice.cassiopee.invariant.scelle.model.Gardiennage> getGardiennageSuivants() {
        return this.gardiennageSuivants;
    }

    public void setGardiennageSuivants(
            java.util.Set<fr.gouv.justice.cassiopee.invariant.scelle.model.Gardiennage> gardiennages) {
        this.gardiennageSuivants = gardiennages;
    }

    /**
     * @return the referenceGardien
     */
    public String getReferenceGardien() {
        return referenceGardien;
    }

    /**
     * @param referenceGardien
     *            referenceGardien � affecter
     */
    public void setReferenceGardien(String referenceGardien) {
        this.referenceGardien = referenceGardien;
    }

    /**
     * Accesseur du sous type de l'objet
     *
     * @return Sous type de l'objet
     */
    public SousTypeScelle getCodeSousTypeScelle() {
        return codeSousTypeScelle;
    }

    /**
     * Modification du sous type de l'objet
     *
     * @param codeSousTypeScelle
     *            Nouveau sous type
     */
    public void setCodeSousTypeScelle(SousTypeScelle codeSousTypeScelle) {
        this.codeSousTypeScelle = codeSousTypeScelle;
    }

    /**
     * Accesseur du libell� du sous type de l'objet
     *
     * @return Libell� du sous type de l'objet
     */
    public String getLibelleSousTypeScelle() {
        return libelleSousTypeScelle;
    }

    /**
     * Modification du sous type de l'objet
     *
     * @param libelleSousTypeScelle
     *            Nouveau libell� du sous type de l'objet
     */
    public void setLibelleSousTypeScelle(String libelleSousTypeScelle) {
        this.libelleSousTypeScelle = libelleSousTypeScelle;
    }

    /**
     * Accesseur de la nature de l'objet
     *
     * @return Nature de l'objet
     */
    public String getNature() {
        return nature;
    }

    /**
     * Modification de la nature de l'objet
     *
     * @param nature
     *            Nouvelle nature de l'objet
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /**
     * Accesseur du nombre d'objet
     *
     * @return Nombre d'objet
     */
    public Short getNombre() {
        return nombre;
    }

    /**
     * Modification du nombre d'objet
     *
     * @param nombre
     *            Nouveau nombre d'objet
     */
    public void setNombre(Short nombre) {
        this.nombre = nombre;
    }

    /**
     * Accesseur de la date suite � un retour de l'objet
     *
     * @return Date de retour de l'objet
     */
    public Date getDateSuiteRetourPiece() {
        return dateSuiteRetourPiece;
    }

    /**
     * Modification de la date de retour de l'objet
     *
     * @param dateSuiteRetourPiece
     *            Nouvelle date de retour
     */
    public void setDateSuiteRetourPiece(Date dateSuiteRetourPiece) {
        this.dateSuiteRetourPiece = dateSuiteRetourPiece;
    }

    /**
     * Accesseur du commentaire suite � un retour
     *
     * @return Commentaire suite � un retour
     */
    public String getCommentaireSuiteRetourPiece() {
        return commentaireSuiteRetourPiece;
    }

    /**
     * Modification du commentaire suite � un retour de l'objet
     *
     * @param commentaireSuiteRetourPiece
     *            Nouveau commentaire
     */
    public void setCommentaireSuiteRetourPiece(String commentaireSuiteRetourPiece) {
        this.commentaireSuiteRetourPiece = commentaireSuiteRetourPiece;
    }

    /**
     * Accesseur de l'attribut pieceObjetEnGardiennage
     *
     * @return Objet de gardiennage de type pi�ce objet
     */
    public PieceObjetEnGardiennage getPieceObjetEnGardiennage() {
        return this.pieceObjetEnGardiennage;
    }

    /**
     * Modification de la pi�ce objet
     *
     * @param pieceObjetEnGardiennage
     *            Nouvelle pi�ce objet
     */
    public void setPieceObjetEnGardiennage(PieceObjetEnGardiennage pieceObjetEnGardiennage) {
        this.pieceObjetEnGardiennage = pieceObjetEnGardiennage;
    }

    /**
     * Accesseur de l'objet en gardiennage de type Arme
     *
     * @return Arme mis en gardiennage
     */
    public ArmeGardiennage getArmeGardiennage() {
        return armeGardiennage;
    }

    /**
     * Modification de l'objet en gardiennage de type Arme
     *
     * @param armeGardiennage
     *            Nouvelle arme
     */
    public void setArmeGardiennage(ArmeGardiennage armeGardiennage) {
        this.armeGardiennage = armeGardiennage;
    }

    /**
     * Accesseur de l'objet de type P�rissable
     *
     * @return Objet p�rissable
     */
    public PerissableGardiennage getPerissableGardiennage() {
        return perissableGardiennage;
    }

    /**
     * Modification de l'objet p�rissable
     *
     * @param perissableGardiennage
     *            Nouvel objet p�rissable
     */
    public void setPerissableGardiennage(PerissableGardiennage perissableGardiennage) {
        this.perissableGardiennage = perissableGardiennage;
    }

    /**
     * Accesseur des informations sur les munitions
     *
     * @return Informations sur les munitions
     */
    public MunitionGardiennage getMunitionGardiennage() {
        return munitionGardiennage;
    }

    /**
     * Modification des informations sur les munitions
     *
     * @param munitionGardiennage
     *            Nouvelles informations sur les munitions
     */
    public void setMunitionGardiennage(MunitionGardiennage munitionGardiennage) {
        this.munitionGardiennage = munitionGardiennage;
    }

    /**
     * Accesseur de l'objet de type Pi�ce mon�taire
     *
     * @return Pi�ce mon�taire
     */
    public PieceMonetaireGardiennage getPieceMonetaireGardiennage() {
        return pieceMonetaireGardiennage;
    }

    /**
     * Modification de l'objet de type pi�ce mon�taire
     *
     * @param Nouvelle
     *            pi�ce mon�taire
     */
    public void setPieceMonetaireGardiennage(PieceMonetaireGardiennage pieceMonetaireGardiennage) {
        this.pieceMonetaireGardiennage = pieceMonetaireGardiennage;
    }

    /**
     * Accesseur de la valeur marchande de l'objet en gardiennage
     *
     * @return Valeur marchande de l'objet
     */
    public Boolean getIndicateurCaracValeurMarchande() {
        return indicateurCaracValeurMarchande;
    }

    /**
     * Modification de la valeur marchande d'un objet en gardiennage
     *
     * @param indicateurCaracValeurMarchande
     *            Nouvelle valeur marchande
     */
    public void setIndicateurCaracValeurMarchande(Boolean indicateurCaracValeurMarchande) {
        this.indicateurCaracValeurMarchande = indicateurCaracValeurMarchande;
    }
}
