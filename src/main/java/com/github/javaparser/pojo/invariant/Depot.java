/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
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
 * Objet m�tier D�p�t. Un d�p�t se mat�rialise par un bordereau listant un ensemble de scell�s d'une affaire ou bien un
 * ensemble d'objets en gardiennage d'une affaire.
 */
public class Depot implements java.io.Serializable {

    /**
     * Identifiant de s�rialisation
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

    /** L'ensemble des Evenements li�s � ce D�p�t. */
    private Set<Evenement> evenements = new HashSet<Evenement>();

    /**
     * Constructeur vide. N�cessaire pour Hibernate.
     */
    public Depot() {
    }

    /**
     * Valorise l'id de l'objet (propri�t� Hibernate).
     *
     * @return un Long repr�sentant l'identifiant de cet objet Depot.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Valorise l'id de l'objet (propri�t� Hibernate).
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Valorise la personne qui d�pose le scell� dans une affaire p�nale, civile ou commerciale : cas d'une personne
     * externe, par exemple une personne qualifi�e (huissier).
     *
     * @return la personne qui d�pose le scell�.
     */
    public ActeurExterne getDeposantActeurExterne() {
        return this.deposantActeurExterne;
    }

    /**
     * Retourne la personne qui d�pose le scell� dans une affaire p�nale, civile ou commerciale : cas d'une personne
     * externe, par exemple une personne qualifi�e (huissier).
     *
     * @param deposant
     */
    public void setDeposantActeurExterne(ActeurExterne deposant) {
        this.deposantActeurExterne = deposant;
    }

    /**
     * Retourne le TGI en charge de la gestion du d�p�t.
     *
     * @return le Tgi en charge de la gestion du d�p�t.
     */
    public Tgi getTgi() {
        return this.tgi;
    }

    /**
     * Valorise le TGI en charge de la gestion du d�p�t.
     *
     * @param tgi
     */
    public void setTgi(Tgi tgi) {
        this.tgi = tgi;
    }

    /**
     * Retourne l'affaire sur laquelle porte le d�p�t. Il s'agit de l'affaire pour laquelle des pi�ces � conviction ou
     * objets de grande taille (objets plac�s en gardiennage) ont �t� saisis ou confisqu�s.
     *
     * @return l' Affaire sur laquelle porte le d�p�t.
     */
    public Affaire getAffaire() {
        return this.affaire;
    }

    /**
     * Valorise l'affaire sur laquelle porte le d�p�t. Il s'agit de l'affaire pour laquelle des pi�ces � conviction ou
     * objets de grande taille (objets plac�s en gardiennage) ont �t� saisis ou confisqu�s.
     *
     * @param affaire
     */
    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
    }

    /**
     * Retourne l'�l�ment de structure ayant effectu� la saisie (police ou gendarmerie).
     *
     * @return l'�l�ment de structure ayant effectu� la saisie.
     */
    public ElementStructure getElementStructureSaisissant() {
        return this.elementStructureSaisissant;
    }

    /**
     * Valorise l'�l�ment de structure ayant effectu� la saisie (police ou gendarmerie).
     *
     * @param elementStructure
     */
    public void setElementStructureSaisissant(ElementStructure elementStructure) {
        this.elementStructureSaisissant = elementStructure;
    }

    /**
     * Retourne l'agent ayant effectu� la saisie.
     *
     * @return l' Agent ayant effectu� la saisie.
     */
    public ActeurInterne getActeurInterneSaisissant() {
        return this.acteurInterneSaisissant;
    }

    /**
     * Valorise l'agent ayant effectu� la saisie.
     *
     * @param agent
     */
    public void setActeurInterneSaisissant(ActeurInterne agent) {
        this.acteurInterneSaisissant = agent;
    }

    /**
     * Retourne l'�tat du d�p�t.
     *
     * @return un EtatDepot correspondant � l'�tat courant du d�p�t
     */
    public EtatDepot getEtatDepot() {
        return this.etatDepot;
    }

    /**
     * Valorise l'�tat du d�p�t.
     *
     * @param etatDepot
     */
    public void setEtatDepot(EtatDepot etatDepot) {
        this.etatDepot = etatDepot;
    }

    /**
     * Retourne l'�l�ment de structure (police par exemple) qui d�pose le scell� dans une affaire p�nale, civile ou
     * commerciale.
     */
    public ElementStructure getElementStructureDeposant() {
        return this.elementStructureDeposant;
    }

    /**
     * Valorise l'�l�ment de structure (police par exemple) qui d�pose le scell� dans une affaire p�nale, civile ou
     * commerciale.
     */
    public void setElementStructureDeposant(ElementStructure elementStructure) {
        this.elementStructureDeposant = elementStructure;
    }

    /**
     * Retourne le num�ro du d�p�t.
     */
    public String getNumeroDepot() {
        return this.numeroDepot;
    }

    /**
     * Valorise le num�ro du d�p�t.
     *
     * @param numeroDepot
     */
    public void setNumeroDepot(String numeroDepot) {
        this.numeroDepot = numeroDepot;
    }

    /**
     * Retourne la date du d�p�t.
     */
    public Date getDateDepot() {
        return this.dateDepot;
    }

    /**
     * Valorise la date du d�p�t.
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
     * Indique le type d'objets accept�s en d�p�t (scell�s ou objets en gardiennages). Un d�p�t peut contenir soit des
     * scell�s, soit des objets en gardiennage, mais pas les deux.
     *
     * @return le type du d�p�t.
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
     * Retourne la collection d'Objets en d�p�t. Il s'agit soit de scell�s, soit d'objets en gardiennage.
     *
     * @return un Set d'Objets.
     */
    public java.util.Set<Objet> getObjets() {
        return this.objets;
    }

    /**
     * Valorise la collection d'Objets en d�p�t. Il s'agit soit de scell�s, soit d'objets en gardiennage.
     *
     * @param objets
     */
    public void setObjets(java.util.Set<Objet> objets) {
        this.objets = objets;
    }

    /**
     * Transferts dont ce d�p�t a fait l'objet.
     */
    public Set<Transfert> getTransferts() {
        return this.transferts;
    }

    public void setTransferts(Set<Transfert> transferts) {
        this.transferts = transferts;
    }

    /**
     * @return un Set contenant les transferts depuis ce d�p�t
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
     * Retourne l'ensemble des Ev�nements li�s � ce D�p�t.
     *
     * @return Returns the evenements.
     */
    public Set<Evenement> getEvenements() {
        return evenements;
    }

    /**
     * Valorise l'ensemble des Ev�nements li�s � ce D�p�t.
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
     * determine si le dep�t est liquidable ou non
     */
    @RegleDeGestion(RG.SCEM011)
    public boolean estLiquidable() {
        boolean hasAllSupprime = true;
        boolean hasOneSortieDefinitive = false;

        for (Objet objet : this.getObjets()) {
            // une sortie d�finitive permet de liquider le d�pot
            if (EtatObjetEnum.SORTI_DEFINITIVEMENT.is(objet.getEtatObjet().getCode())) {
                hasOneSortieDefinitive = true;
            } else {
                // un element n'est pas � l'�tat supprim�
                if (!EtatObjetEnum.SUPPRIME.is(objet.getEtatObjet().getCode())) {
                    hasAllSupprime = false;
                }
            }
        }

        // tous les elements sont � l'�tat supprim� et au moins un est sortie definitivement
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
