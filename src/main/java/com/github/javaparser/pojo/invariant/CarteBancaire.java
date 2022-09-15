/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.affaire.model;

/**
 * Dans le cas d'une affaire d'infraction aux moyens de paiement, cette classe r�f�rence et d�crit le (ou les) carte(s)
 * bancaire(s) concern�e(s), elle permet aussi d'acc�der au titulaire de ces comptes. Selon les cas, le titulaire peut
 * �tre victime ou auteur dans l'affaire. La carte bancaire peut �tre � l'origine de paiements frauduleux.
 */
public class CarteBancaire implements java.io.Serializable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 4054658773068737948L;

    /**
     * identifiant Carte banquaire
     */
    private Long id;

    /**
     * le compte li� � la carte bancaire
     */
    private Compte compte;

    /**
     * il s'agit du num�ro de la carte bancaire inscrit sur celle-ci. Num�ro sur 16 positions (sans la cl�).
     */
    private String numeroCarte;

    /**
     * Il s'agit du nom du titulaire inscrit sur la carte.
     */
    private String libelleTitulaire;

    /**
     * retourne le compte li� � la carte bancaire
     *
     * @return le compte li� � la carte bancaire
     */
    public Compte getCompte() {
        return compte;
    }

    /**
     * affecte le compte li� � la carte bancaire
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
     * Retourne le num�ro de la carte bancaire
     *
     * @return le num�ro de la carte bancaire
     */
    public String getNumeroCarte() {
        return numeroCarte;
    }

    /**
     * Affecte le num�ro de la carte bancaire
     *
     * @param numeroCarte
     */
    public void setNumeroCarte(String numeroCarte) {
        this.numeroCarte = numeroCarte;
    }

}
