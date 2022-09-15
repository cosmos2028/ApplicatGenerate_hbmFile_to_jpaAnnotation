package fr.gouv.justice.cassiopee.invariant.affaire.model;

/**
 * Il s'agit d'identifier le v�hicule utile dans le cadre de l?affaire, pouvant avoir servi � la commission d'une
 * infraction dans le cadre d'une affaire.
 */
public class Vehicule implements java.io.Serializable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -4329270272821885363L;

    /** Identifiant du v�hicule */
    private Long id;

    /** Rang du v�hicule parmis les autres v�hicules de l'affaire */
    private Integer rang;

    /**
     * Il s'agit du num�ro d'immatriculation fran�aise ou �trang�re.
     */
    private String numeroImmatriculation;

    /**
     * Il s?agit de d�finir le type de v�hicule (ex: ambulance, utilitaire,Voiture particuli�re..;)
     */
    private String type;

    /**
     * Il s?agit du constructeur (ex: Renault, Fiat...).
     */
    private String marque;

    /**
     * Cet attribut indique le mod�le du v�hicule dans la marque (ex: Laguna - Clio pour Renault).
     */
    private String modele;

    /**
     * Commentaire concernant le v�hicule
     */
    private String commentaire;

    /**
     * Il s'agit du code pays figurant sur la plaque d?immatriculation (pays d?immatriculation).
     */
    private String paysImmatriculation;

    /** Affaire � laquelle est rattach� le v�hicule */
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
