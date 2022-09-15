package fr.gouv.justice.cassiopee.invariant.affaire.model;

/**
 * Il s'agit d'identifier le véhicule utile dans le cadre de l?affaire, pouvant avoir servi à la commission d'une
 * infraction dans le cadre d'une affaire.
 */
public class Vehicule implements java.io.Serializable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -4329270272821885363L;

    /** Identifiant du véhicule */
    private Long id;

    /** Rang du véhicule parmis les autres véhicules de l'affaire */
    private Integer rang;

    /**
     * Il s'agit du numéro d'immatriculation française ou étrangère.
     */
    private String numeroImmatriculation;

    /**
     * Il s?agit de définir le type de véhicule (ex: ambulance, utilitaire,Voiture particulière..;)
     */
    private String type;

    /**
     * Il s?agit du constructeur (ex: Renault, Fiat...).
     */
    private String marque;

    /**
     * Cet attribut indique le modèle du véhicule dans la marque (ex: Laguna - Clio pour Renault).
     */
    private String modele;

    /**
     * Commentaire concernant le véhicule
     */
    private String commentaire;

    /**
     * Il s'agit du code pays figurant sur la plaque d?immatriculation (pays d?immatriculation).
     */
    private String paysImmatriculation;

    /** Affaire à laquelle est rattaché le véhicule */
    private Affaire affaire;

    public Affaire getAffaire() {
        return affaire;
    }

    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
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
    public Integer getRang() {
        return this.rang;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
    }

    /**
     *
     */
    public String getNumeroImmatriculation() {
        return this.numeroImmatriculation;
    }

    public void setNumeroImmatriculation(String numeroImmatriculation) {
        this.numeroImmatriculation = numeroImmatriculation;
    }

    /**
     *
     */
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     */
    public String getMarque() {
        return this.marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     *
     */
    public String getModele() {
        return this.modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    /**
     *
     */
    public String getCommentaire() {
        return this.commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getPaysImmatriculation() {
        return paysImmatriculation;
    }

    public void setPaysImmatriculation(String paysImmatrculation) {
        this.paysImmatriculation = paysImmatrculation;
    }

}
