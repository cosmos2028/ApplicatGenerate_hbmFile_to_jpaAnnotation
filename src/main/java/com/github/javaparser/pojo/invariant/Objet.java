/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.scelle.service.enumeration.TypeObjetEnum;

/**
 * Objet en dépôt. Peut être:
 * <ul>
 * <li>un Scellé (les scellés sont stockés dans des locaux spécialisés du TGI)</li>
 * <li>un Objet en gardiennage (les objets placés en gardiennage sont placés en dehors du TGI chez des gardiens appelés
 * aussi parfois fourrières. Leur stockage occasionne des frais de gardiennage)</li>
 * </ul>
 */
public abstract class Objet implements java.io.Serializable {

    /** Identifiant de sérialisation */
    private static final long serialVersionUID = 1247896874789870578L;

    /** Identifiant de l'objet */
    private Long id;

    /** Etat de l'objet */
    private EtatObjet etatObjet;

    /** Depot contenant l'objet */
    private Depot depot;

    /** Emplacement de l'objet */
    private Emplacement emplacement;

    /** Date de l'arrêt définitif */
    private Date dateDefinitiveArret;

    /** Date de la prescrition définitive */
    private Date dateDefinitivePrescription;

    /** Identifiant utilisateur de l'objet */
    private String identifiantObjet;

    /** Indicateur valeur marchande */
    private Boolean indicateurValeurMarchande;

    /** L'ensemble des Evenements liés à cet Objet. */
    private Set<Evenement> evenements = new HashSet<Evenement>();

    /**
     * Discriminant Hibernate.
     */
    private String discriminant;

    public Objet() {
        //
    }

    /**
     * Retourne l'identifier objet
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Valorise l'identifier objet
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne l'etat de l'objet
     */
    public EtatObjet getEtatObjet() {
        return this.etatObjet;
    }

    /**
     * Valorise l'etat de l'objet
     *
     * @param etatObjet
     */
    public void setEtatObjet(EtatObjet etatObjet) {
        this.etatObjet = etatObjet;
    }

    /**
     * Retourne le dépot de l'objet
     */
    public Depot getDepot() {
        return this.depot;
    }

    /**
     * Valorise le dépot de l'objet
     *
     * @param fkSceDepObjDep
     */
    public void setDepot(Depot fkSceDepObjDep) {
        this.depot = fkSceDepObjDep;
    }

    /**
     * Retourne l'emplacement de l'objet
     */
    public Emplacement getEmplacement() {
        return this.emplacement;
    }

    /**
     * Valorise l'emplacement de l'objet
     *
     * @param fkSceObjSceEmp
     */
    public void setEmplacement(Emplacement fkSceObjSceEmp) {
        this.emplacement = fkSceObjSceEmp;
    }

    /**
     * Retourne la date définitive d'arrêt
     */
    public Date getDateDefinitiveArret() {
        return this.dateDefinitiveArret;
    }

    /**
     * Valorise la date définitive d'arrêt
     *
     * @param dateDefinitiveArret
     */
    public void setDateDefinitiveArret(Date dateDefinitiveArret) {
        this.dateDefinitiveArret = dateDefinitiveArret;
    }

    /**
     * Valorise la date définitive de prescription
     */
    public Date getDateDefinitivePrescription() {
        return this.dateDefinitivePrescription;
    }

    /**
     * Retourne la date définitive de prescription
     *
     * @param dateDefinitivePrescription
     */
    public void setDateDefinitivePrescription(Date dateDefinitivePrescription) {
        this.dateDefinitivePrescription = dateDefinitivePrescription;
    }

    /**
     * Retourne l'identifiant utilisateur de l'objet
     */
    public String getIdentifiantObjet() {
        return this.identifiantObjet;
    }

    /**
     * Valorise l'identifiant utilisateur de l'objet
     *
     * @param identifiantObjet
     */
    public void setIdentifiantObjet(String identifiantObjet) {
        this.identifiantObjet = identifiantObjet;
    }

    /**
     * Retourne l'indicateur de valeur marchande
     */
    public Boolean getIndicateurValeurMarchande() {
        return this.indicateurValeurMarchande;
    }

    /**
     * Valorise l'indicateur de valeur marchande
     *
     * @param indicateurValeurMarchande
     */
    public void setIndicateurValeurMarchande(Boolean indicateurValeurMarchande) {
        this.indicateurValeurMarchande = indicateurValeurMarchande;
    }

    /**
     * @return un TypeObjetEnum correspondant à la sous-classe d'Objet
     */
    public TypeObjetEnum getTypeObjetEnum() {
        return TypeObjetEnum.findTypeObjetEnumByCode(this.discriminant);
    }

    /**
     * Retourne l'ensemble des Evenements liés à cet Objet.
     *
     * @return Returns the evenements.
     */
    public Set<Evenement> getEvenements() {
        return evenements;
    }

    /**
     * Valorise l'ensemble des Evenements liés à cet Objet.
     *
     * @param evenements
     *            The evenements to set.
     */
    public void setEvenements(Set<Evenement> evenements) {
        this.evenements = evenements;
    }

    /**
     * @return the discriminant
     */
    public String getDiscriminant() {
        return discriminant;
    }

    /**
     * @param discriminant
     *            the discriminant to set
     */
    public void setDiscriminant(String discriminant) {
        this.discriminant = discriminant;
    }

    public abstract String getNumero();

}
