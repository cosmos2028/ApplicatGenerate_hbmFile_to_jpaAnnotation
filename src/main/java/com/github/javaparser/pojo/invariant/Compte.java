package fr.gouv.justice.cassiopee.invariant.affaire.model;

import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.common.util.ISOLatin1AccentFilter;
import fr.gouv.justice.cassiopee.invariant.personne.model.LienComptePersonne;
import fr.gouv.justice.cassiopee.invariant.personne.model.TitrePaiement;

/**
 * Dans le cas d'une affaire d'infraction aux moyens de paiement, cette classe r�f�rence et d�crit le (ou les) compte(s)
 * concern�(s), elle permet aussi d'acc�der au titulaire de ces comptes. Selon les cas, le titulaire peut �tre victime
 * ou auteur dans l'affaire. Peuvent �tre d�clar�s �galement d'autres titulaires du compte.
 */
public class Compte implements java.io.Serializable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -7474882563873164467L;

    /**
     * Identifiant Compte.
     */
    private Long id;

    /**
     * On utilise la codification bancaire des Banques.
     */
    private EtablissementBancaire etablissementBancaire;

    /**
     * liste des cartes bancaires du compte
     */
    private Set<CarteBancaire> cartesBancaires = new HashSet<CarteBancaire>();

    /**
     * Il s'agit du rang de saisie du compte (permet d'afficher la liste des comptes tri�s par ordre de saisie).
     */
    private Integer rang;

    /**
     * Les comptes bancaires sont identifi�s par un num�ro ou par des lettres.
     */
    private String numeroCompte;

    /**
     * Il s'agit du code du guichet qui g�re le compte.
     */
    private String codeGuichet;

    /**
     * Il s'agit du nom de l'agence � laquelle appartient le compte.
     */
    private String agenceNom;

    /**
     * Il s'ait du code postal de l'agence bancaire.
     */
    private String agenceCodePostal;

    /**
     * Cette zone de commentaires permet de saisir une liste de num�ros de ch�quiers ou un premier num�ro de ch�que et
     * un dernier num�ro de ch�que (du n� X au n� Y par exemple) dans le cas de ch�quier vol� ou retrourv�, sans
     * �mission de ch�ques.
     */
    private String commentaire;

    /**
     * Il s'agit du libell� ou intitul� du compte (Me et Mme XXX, Mr ou Mme ....).
     */
    private String libelleTitulaire;

    /**
     * Il s'agit du libell� ou intitul� du compte (Me et Mme XXX, Mr ou Mme ....). en Majuscule sans accent
     */
    private String libelleTitulaireMajuscule;

    /**
     * Commentaire sur le titre vole
     */
    private String titreVoleCommentaire;

    /** Affaire p�nale � laquelle le compte est li� */
    private Affaire affaire;

    /**
     * Liste des titulaires du compte, pouvant �tre les auteurs titulaires ou les victimes titulaires.
     */
    private Set<LienComptePersonne> titulaires = new HashSet<LienComptePersonne>();

    /**
     * Liste des titres de paiement rattach�s au compte.
     */
    private Set<TitrePaiement> titresDePaiement = new HashSet<TitrePaiement>();

    public String getAgenceCodePostal() {
        return agenceCodePostal;
    }

    public void setAgenceCodePostal(String agenceCodePostal) {
        this.agenceCodePostal = agenceCodePostal;
    }

    public String getAgenceNom() {
        return agenceNom;
    }

    public void setAgenceNom(String agenceNom) {
        this.agenceNom = agenceNom;
    }

    public Set<CarteBancaire> getCartesBancaires() {
        return cartesBancaires;
    }

    public void setCartesBancaires(Set<CarteBancaire> cartesBancaires) {
        this.cartesBancaires = cartesBancaires;
    }

    public String getCodeGuichet() {
        return codeGuichet;
    }

    public void setCodeGuichet(String codeGuichet) {
        this.codeGuichet = codeGuichet;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public EtablissementBancaire getEtablissementBancaire() {
        return etablissementBancaire;
    }

    public void setEtablissementBancaire(EtablissementBancaire etablissementBancaire) {
        this.etablissementBancaire = etablissementBancaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleTitulaire() {
        return libelleTitulaire;
    }

    public void assignLibelleTitulaire(String libelleTitulaire) {
        setLibelleTitulaire(libelleTitulaire);
        setLibelleTitulaireMajuscule(ISOLatin1AccentFilter.toUpperCaseNotAccentuated(libelleTitulaire));
    }

    public void setLibelleTitulaire(String libelleTitulaire) {
        this.libelleTitulaire = libelleTitulaire;
    }

    /**
     * @return the libelleTitulaireMajuscule
     */
    public String getLibelleTitulaireMajuscule() {
        return libelleTitulaireMajuscule;
    }

    /**
     * @param libelleTitulaireMajuscule
     *            the libelleTitulaireMajuscule to set
     */
    public void setLibelleTitulaireMajuscule(String libelleTitulaireMajuscule) {
        this.libelleTitulaireMajuscule = libelleTitulaireMajuscule;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public Integer getRang() {
        return rang;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
    }

    public Affaire getAffaire() {
        return affaire;
    }

    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
    }

    public Set<LienComptePersonne> getTitulaires() {
        return titulaires;
    }

    public void setTitulaires(Set<LienComptePersonne> titulaires) {
        this.titulaires = titulaires;
    }

    public Set<TitrePaiement> getTitresDePaiement() {
        return titresDePaiement;
    }

    public void setTitresDePaiement(Set<TitrePaiement> titresDePaiement) {
        this.titresDePaiement = titresDePaiement;
    }

    public String getTitreVoleCommentaire() {
        return titreVoleCommentaire;
    }

    public void setTitreVoleCommentaire(String titreVoleCommentaire) {
        this.titreVoleCommentaire = titreVoleCommentaire;
    }

}
