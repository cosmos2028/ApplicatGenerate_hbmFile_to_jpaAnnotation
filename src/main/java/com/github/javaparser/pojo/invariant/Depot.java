/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.administration.acteur.externe.model.ActeurExterne;
import fr.gouv.justice.cassiopee.administration.acteur.interne.model.ActeurInterne;
import fr.gouv.justice.cassiopee.administration.structure.model.Tgi;
import fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.scelle.service.enumeration.EtatObjetEnum;
import fr.gouv.justice.cassiopee.invariant.scelle.service.enumeration.TypeObjetEnum;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;

/**
 * Objet métier Dépôt. Un dépôt se matérialise par un bordereau listant un ensemble de scellés d'une affaire ou bien un
 * ensemble d'objets en gardiennage d'une affaire.
 */
public class Depot implements java.io.Serializable {

    /**
     * Identifiant de sérialisation
     */
    private static final long serialVersionUID = 1714852369658854712L;

    private Long id;

    private ActeurExterne deposantActeurExterne;

    private Tgi tgi;

    private Tgi ancienTgi;

    private Affaire affaire;

    private ElementStructure elementStructureSaisissant;

    private ActeurInterne acteurInterneSaisissant;

    private EtatDepot etatDepot;

    private ElementStructure elementStructureDeposant;

    private String numeroDepot;

    private String ancienNumeroDepot;

    private Date dateDepot;

    private Integer compteurNumero;

    private String saisi;

    private String typeDepot;

    private Set<Objet> objets = new HashSet<Objet>();

    private Set<Transfert> transferts = new HashSet<Transfert>();

    private Set<Transfert> transfertsDontCeDepotEstOrigine = new HashSet<Transfert>();

    /** L'ensemble des Evenements liés à ce Dépôt. */
    private Set<Evenement> evenements = new HashSet<Evenement>();

    /**
     * Constructeur vide. Nécessaire pour Hibernate.
     */
    public Depot() {
    }

    /**
     * Valorise l'id de l'objet (propriété Hibernate).
     *
     * @return un Long représentant l'identifiant de cet objet Depot.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Valorise l'id de l'objet (propriété Hibernate).
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Valorise la personne qui dépose le scellé dans une affaire pénale, civile ou commerciale : cas d'une personne
     * externe, par exemple une personne qualifiée (huissier).
     *
     * @return la personne qui dépose le scellé.
     */
    public ActeurExterne getDeposantActeurExterne() {
        return this.deposantActeurExterne;
    }

    /**
     * Retourne la personne qui dépose le scellé dans une affaire pénale, civile ou commerciale : cas d'une personne
     * externe, par exemple une personne qualifiée (huissier).
     *
     * @param deposant
     */
    public void setDeposantActeurExterne(ActeurExterne deposant) {
        this.deposantActeurExterne = deposant;
    }

    /**
     * Retourne le TGI en charge de la gestion du dépôt.
     *
     * @return le Tgi en charge de la gestion du dépôt.
     */
    public Tgi getTgi() {
        return this.tgi;
    }

    /**
     * Valorise le TGI en charge de la gestion du dépôt.
     *
     * @param tgi
     */
    public void setTgi(Tgi tgi) {
        this.tgi = tgi;
    }

    /**
     * Retourne l'affaire sur laquelle porte le dépôt. Il s'agit de l'affaire pour laquelle des pièces à conviction ou
     * objets de grande taille (objets placés en gardiennage) ont été saisis ou confisqués.
     *
     * @return l' Affaire sur laquelle porte le dépôt.
     */
    public Affaire getAffaire() {
        return this.affaire;
    }

    /**
     * Valorise l'affaire sur laquelle porte le dépôt. Il s'agit de l'affaire pour laquelle des pièces à conviction ou
     * objets de grande taille (objets placés en gardiennage) ont été saisis ou confisqués.
     *
     * @param affaire
     */
    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
    }

    /**
     * Retourne l'élément de structure ayant effectué la saisie (police ou gendarmerie).
     *
     * @return l'élément de structure ayant effectué la saisie.
     */
    public ElementStructure getElementStructureSaisissant() {
        return this.elementStructureSaisissant;
    }

    /**
     * Valorise l'élément de structure ayant effectué la saisie (police ou gendarmerie).
     *
     * @param elementStructure
     */
    public void setElementStructureSaisissant(ElementStructure elementStructure) {
        this.elementStructureSaisissant = elementStructure;
    }

    /**
     * Retourne l'agent ayant effectué la saisie.
     *
     * @return l' Agent ayant effectué la saisie.
     */
    public ActeurInterne getActeurInterneSaisissant() {
        return this.acteurInterneSaisissant;
    }

    /**
     * Valorise l'agent ayant effectué la saisie.
     *
     * @param agent
     */
    public void setActeurInterneSaisissant(ActeurInterne agent) {
        this.acteurInterneSaisissant = agent;
    }

    /**
     * Retourne l'état du dépôt.
     *
     * @return un EtatDepot correspondant à l'état courant du dépôt
     */
    public EtatDepot getEtatDepot() {
        return this.etatDepot;
    }

    /**
     * Valorise l'état du dépôt.
     *
     * @param etatDepot
     */
    public void setEtatDepot(EtatDepot etatDepot) {
        this.etatDepot = etatDepot;
    }

    /**
     * Retourne l'élément de structure (police par exemple) qui dépose le scellé dans une affaire pénale, civile ou
     * commerciale.
     */
    public ElementStructure getElementStructureDeposant() {
        return this.elementStructureDeposant;
    }

    /**
     * Valorise l'élément de structure (police par exemple) qui dépose le scellé dans une affaire pénale, civile ou
     * commerciale.
     */
    public void setElementStructureDeposant(ElementStructure elementStructure) {
        this.elementStructureDeposant = elementStructure;
    }

    /**
     * Retourne le numéro du dépôt.
     */
    public String getNumeroDepot() {
        return this.numeroDepot;
    }

    /**
     * Valorise le numéro du dépôt.
     *
     * @param numeroDepot
     */
    public void setNumeroDepot(String numeroDepot) {
        this.numeroDepot = numeroDepot;
    }

    /**
     * Retourne la date du dépôt.
     */
    public Date getDateDepot() {
        return this.dateDepot;
    }

    /**
     * Valorise la date du dépôt.
     *
     * @param dateDepot
     */
    public void setDateDepot(Date dateDepot) {
        this.dateDepot = dateDepot;
    }

    /**
     * @return
     */
    public String getSaisi() {
        return this.saisi;
    }

    /**
     * @param saisi
     */
    public void setSaisi(String saisi) {
        this.saisi = saisi;
    }

    /**
     * Indique le type d'objets acceptés en dépôt (scellés ou objets en gardiennages). Un dépôt peut contenir soit des
     * scellés, soit des objets en gardiennage, mais pas les deux.
     *
     * @return le type du dépôt.
     */
    public TypeObjetEnum getTypeDepot() {
        return TypeObjetEnum.findTypeObjetEnumByCode(this.typeDepot);
    }

    /**
     * @param typeDepot
     *            (discriminant)
     */
    public void setTypeDepot(TypeObjetEnum typeDepot) {
        this.typeDepot = typeDepot.getType();
    }

    /**
     * Retourne la collection d'Objets en dépôt. Il s'agit soit de scellés, soit d'objets en gardiennage.
     *
     * @return un Set d'Objets.
     */
    public java.util.Set<Objet> getObjets() {
        return this.objets;
    }

    /**
     * Valorise la collection d'Objets en dépôt. Il s'agit soit de scellés, soit d'objets en gardiennage.
     *
     * @param objets
     */
    public void setObjets(java.util.Set<Objet> objets) {
        this.objets = objets;
    }

    /**
     * Transferts dont ce dépôt a fait l'objet.
     */
    public Set<Transfert> getTransferts() {
        return this.transferts;
    }

    public void setTransferts(Set<Transfert> transferts) {
        this.transferts = transferts;
    }

    /**
     * @return un Set contenant les transferts depuis ce dépôt
     */
    public Set<Transfert> getTransfertsDontCeDepotEstOrigine() {
        return this.transfertsDontCeDepotEstOrigine;
    }

    /**
     * @param transferts_1
     */
    public void setTransfertsDontCeDepotEstOrigine(Set<Transfert> transferts_1) {
        this.transfertsDontCeDepotEstOrigine = transferts_1;
    }

    /**
     * Retourne l'ensemble des Evénements liés à ce Dépôt.
     *
     * @return Returns the evenements.
     */
    public Set<Evenement> getEvenements() {
        return evenements;
    }

    /**
     * Valorise l'ensemble des Evénements liés à ce Dépôt.
     *
     * @param evenements
     *            The evenements to set.
     */
    public void setEvenements(Set<Evenement> evenements) {
        this.evenements = evenements;
    }

    /**
     * retourne le prochain numero pour les scelles et les objets en gardiennage
     *
     * @return compteur+1
     */
    public String getNextNumero() {
        this.compteurNumero = this.compteurNumero + 1;

        return this.compteurNumero.toString();
    }

    /**
     * @return the ancienTgi
     */
    public Tgi getAncienTgi() {
        return ancienTgi;
    }

    /**
     * @param ancienTgi
     *            the ancienTgi to set
     */
    public void setAncienTgi(Tgi ancienTgi) {
        this.ancienTgi = ancienTgi;
    }

    /**
     * @return the ancienNumeroDepot
     */
    public String getAncienNumeroDepot() {
        return ancienNumeroDepot;
    }

    /**
     * @param ancienNumeroDepot
     *            the ancienNumeroDepot to set
     */
    public void setAncienNumeroDepot(String ancienNumeroDepot) {
        this.ancienNumeroDepot = ancienNumeroDepot;
    }

    /**
     * determine si le depôt est liquidable ou non
     */
    @RegleDeGestion(RG.SCEM011)
    public boolean estLiquidable() {
        boolean hasAllSupprime = true;
        boolean hasOneSortieDefinitive = false;

        for (Objet objet : this.getObjets()) {
            // une sortie définitive permet de liquider le dépot
            if (EtatObjetEnum.SORTI_DEFINITIVEMENT.is(objet.getEtatObjet().getCode())) {
                hasOneSortieDefinitive = true;
            } else {
                // un element n'est pas à l'état supprimé
                if (!EtatObjetEnum.SUPPRIME.is(objet.getEtatObjet().getCode())) {
                    hasAllSupprime = false;
                }
            }
        }

        // tous les elements sont à l'état supprimé et au moins un est sortie definitivement
        return (hasAllSupprime && hasOneSortieDefinitive);
    }

    /**
     * recherche si un objet ou scelle ou pac est sorti
     *
     * @return true ou false
     */
    public boolean hasElementsSortie() {
        for (Objet objet : this.getObjets()) {
            if (objet instanceof Scelle && ((Scelle) objet).estSortie()) {
                return true;
            } else if (objet instanceof ObjetEnGardiennage && ((ObjetEnGardiennage) objet).estSorti()) {
                return true;
            }
        }

        return false;

    }
}
