/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.administration.parametrage.model.TypeObjetGardiennage;
import fr.gouv.justice.cassiopee.invariant.scelle.service.enumeration.EtatObjetEnum;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;

/**
 * Objet placé en gardiennage dans une fourrière.
 */
/**
 * @author gfiuser
 */
public class ObjetEnGardiennage extends Objet {

    private static final long serialVersionUID = -4299687613359479999L;

    private Set<Gardiennage> gardiennages = new HashSet<Gardiennage>();

    private ElementStructure dernierGardien;

    private String numeroObjetGardien;

    private String numero;

    @SuppressWarnings("unused")
    private String numeroObjetEnGardiennage;

    private String marque;

    private String numeroSerie;

    private String immatriculation;

    private String descriptif;

    private Inventaire inventaire;

    private TypeObjetGardiennage typeObjetGardiennage;

    private Boolean indicateurStock;

    /**
     * Le rang de l'objet en gardiennage
     */
    private Integer rang;

    /**
     * @return
     */
    public Integer getRang() {
        return rang;
    }

    /**
     * @param rang
     */
    public void setRang(Integer rang) {
        this.rang = rang;
    }

    /**
     * Retourne les gardiennages (gardiens, fourrières) qui ont la garde de cet objet.
     *
     * @return un Set de Gardiennages
     */
    public Set<Gardiennage> getGardiennages() {
        return this.gardiennages;
    }

    /**
     * Valorise les gardiennages (gardiens, fourrières) qui ont la garde de cet objet.
     *
     * @param gardiennages
     */
    public void setGardiennages(Set<Gardiennage> gardiennages) {
        this.gardiennages = gardiennages;
    }

    /**
     *
     */
    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String objectNumero) {
        this.numero = objectNumero;
    }

    /**
     *
     */
    public String getMarque() {
        return this.marque;
    }

    public void setMarque(String objectMarque) {
        this.marque = objectMarque;
    }

    /**
     *
     */
    public String getNumeroSerie() {
        return this.numeroSerie;
    }

    public void setNumeroSerie(String objectNumeroSerie) {
        this.numeroSerie = objectNumeroSerie;
    }

    /**
     *
     */
    public String getImmatriculation() {
        return this.immatriculation;
    }

    public void setImmatriculation(String objectImmatriculation) {
        this.immatriculation = objectImmatriculation;
    }

    /**
     *
     */
    public String getDescriptif() {
        return this.descriptif;
    }

    public void setDescriptif(String objectDescriptif) {
        this.descriptif = objectDescriptif;
    }

    /**
     *
     */
    public Inventaire getInventaire() {
        return this.inventaire;
    }

    public void setInventaire(Inventaire fkSceObjObjInv) {
        this.inventaire = fkSceObjObjInv;
    }

    public TypeObjetGardiennage getTypeObjetGardiennage() {
        return typeObjetGardiennage;
    }

    public void setTypeObjetGardiennage(TypeObjetGardiennage typeObjetGardiennage) {
        this.typeObjetGardiennage = typeObjetGardiennage;
    }

    /**
     * @return the indicateurStock
     */
    public Boolean getIndicateurStock() {
        return indicateurStock;
    }

    /**
     * @param indicateurStock
     *            indicateurStock à affecter
     */
    public void setIndicateurStock(Boolean indicateurStock) {
        this.indicateurStock = indicateurStock;
    }

    /**
     * permet d'ajouter un nouvel gardiennage
     *
     * @return le gardiennage initilisé
     */
    public Gardiennage addGardiennage() {
        // création du nouveau gardiennage
        Gardiennage gardiennage = new Gardiennage();

        // lie avec l'objet
        gardiennage.setObjet(this);

        // pas de mémoire
        gardiennage.setMemoire(null);

        // lie avec l'ancien gardiennage
        if (!this.getGardiennages().isEmpty()) {
            // l'ensemble est triée par id decroisant, le dernier gardiennage
            // est le premier de l'ensemble
            Gardiennage precedent = this.getGardiennages().iterator().next();
            gardiennage.setGardiennagePrecedent(precedent);

            // même gardien
            gardiennage.setElementStructure(precedent.getElementStructure());

        }

        // ajout dans la liste
        this.getGardiennages().add(gardiennage);

        return gardiennage;
    }

    /**
     * permet de trouver le dernier gardiennage
     *
     * @return le dernier gardiennage
     */
    public Gardiennage findOrAddLastGardiennage() {
        if (this.getGardiennages().isEmpty())
            return addGardiennage();

        return findLastGardiennage();
    }

    /**
     * permet de trouver le dernier gardiennage
     *
     * @return le dernier gardiennage ou null
     */
    public Gardiennage findLastGardiennage() {
        {
            if (!this.getGardiennages().isEmpty())
                return this.getGardiennages().iterator().next();

            return null;
        }
    }

    /**
     * indique que le objet est sorti ou non
     *
     * @return true ou false
     */
    public boolean estSorti() {
        return EtatObjetEnum.SORTI_DEFINITIVEMENT.is(this.getEtatObjet().getCode())
                || EtatObjetEnum.SORTI_PROVISOIREMENT.is(this.getEtatObjet().getCode());
    }

    /**
     * @return Returns the dernierGardien.
     */
    public ElementStructure getDernierGardien() {
        return dernierGardien;
    }

    /**
     * @param dernierGardien
     *            The dernierGardien to set.
     */
    public void setDernierGardien(ElementStructure dernierGardien) {
        this.dernierGardien = dernierGardien;
    }

    /**
     * @return Returns the numeroObjetGardien.
     */
    public String getNumeroObjetGardien() {
        return numeroObjetGardien;
    }

    /**
     * @param numeroObjetGardien
     *            The numeroObjetGardien to set.
     */
    public void setNumeroObjetGardien(String numeroObjetGardien) {
        this.numeroObjetGardien = numeroObjetGardien;
    }
}
