/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.affaire.model;

/**
 * Dans le cas d'une affaire d'infraction aux moyens de paiement, cette classe référence et décrit le (ou les) carte(s)
 * bancaire(s) concernée(s), elle permet aussi d'accéder au titulaire de ces comptes. Selon les cas, le titulaire peut
 * être victime ou auteur dans l'affaire. La carte bancaire peut être à l'origine de paiements frauduleux.
 */
public class CarteBancaire implements java.io.Serializable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 4054658773068737948L;

    /**
     * identifiant Carte banquaire
     */
    private Long id;

    /**
     * le compte lié à la carte bancaire
     */
    private Compte compte;

    /**
     * il s'agit du numéro de la carte bancaire inscrit sur celle-ci. Numéro sur 16 positions (sans la clé).
     */
    private String numeroCarte;

    /**
     * Il s'agit du nom du titulaire inscrit sur la carte.
     */
    private String libelleTitulaire;

    /**
     * retourne le compte lié à la carte bancaire
     *
     * @return le compte lié à la carte bancaire
     */
    public Compte getCompte() {
        return compte;
    }

    /**
     * affecte le compte lié à la carte bancaire
     *
     * @param compte
     */
    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    /**
     * Retourne l'identifiant de la carte bancaire
     *
     * @return l'identifiant de la carte bancaire
     */
    public Long getId() {
        return id;
    }

    /**
     * Affecte l'identifiant de la carte bancaire
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le nom du titulaire de la carte bancaire
     *
     * @return le nom du titulaire de la carte bancaire
     */
    public String getLibelleTitulaire() {
        return libelleTitulaire;
    }

    /**
     * Affecte le nom du titulaire de la carte bancaire
     *
     * @param libelleTitulaire
     */
    public void setLibelleTitulaire(String libelleTitulaire) {
        this.libelleTitulaire = libelleTitulaire;
    }

    /**
     * Retourne le numéro de la carte bancaire
     *
     * @return le numéro de la carte bancaire
     */
    public String getNumeroCarte() {
        return numeroCarte;
    }

    /**
     * Affecte le numéro de la carte bancaire
     *
     * @param numeroCarte
     */
    public void setNumeroCarte(String numeroCarte) {
        this.numeroCarte = numeroCarte;
    }

}
