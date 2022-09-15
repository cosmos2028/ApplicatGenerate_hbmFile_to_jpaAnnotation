package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.util.Date;

import fr.gouv.justice.cassiopee.administration.locale.model.GrandCompte;
import fr.gouv.justice.cassiopee.commons.metier.model.Adresse;
import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.TypePersonneEnum;
import fr.gouv.justice.cassiopee.referentiel.codification.model.CategoriePenale;
import fr.gouv.justice.cassiopee.referentiel.codification.model.StatutJuridique;

/**
 * Groupement qui se voit reconna�tre une existence juridique et qui, � ce titre, a des droits et des obligations, ex : soci�t�,
 * association� La personne morale est une entit� juridique d�finie par sa raison sociale et son num�ro de SIREN. Une personne physique lui
 * est syst�matiquement associ�e pour parler en son nom : selon le stade de l'affaire, ce peut �tre le repr�sentant l�gal ou le d�clarant.
 */
public class PersonneMorale extends Personne {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -8213269803979935118L;

    /**
     * La forme juridique est d�termin�e par les statuts et est propre � chaque soci�t�. Exemples : SA - SARL.
     */
    private StatutJuridique formeJuridique;

    /**
     * Raison Sociale ou D�nomination. Ce terme d�signe une entreprise et permet de la distinguer des entreprises
     * concurrentes (�l�ment du fonds de commerce).
     */
    private String raisonSociale;

    /**
     * L'attribut Raison sociale en majuscule est utilis� lors pour la recherche car, il peut exister des raison sociale
     * avec accent.
     */
    private String raisonSocialeMajuscule;

    /**
     * Nom sous lequel la soci�t� est connu du public. Marque distinctive d?un �tablissement.
     */
    private String enseigne;

    /**
     * L 'attribut enseigne en majuscule est utilis� pour la recherche car, il peut exister des enseigne avec accent.
     */
    private String enseigneMajuscule;

    /**
     * Abr�viation d'un terme complexe
     */
    private String sigle;

    /**
     * Abr�vation en majuscule, utilis�e pour la recherche
     */
    private String sigleMajuscule;

    /**
     * SIREN : Codification �tablie par l�INSEE. Il comporte 9 chiffres, dont le dernier est une cl� de contr�le, et est unique � chaque
     * immatriculation. Cette num�rotation unique est affect�e au si�ge et � tous les �tablissements d'une personne immatricul�e au registre
     * du commerce et des soci�t�s y compris les �tablissements hors ressort. C'est l'identifiant retenu par le Casier judiciaire des
     * personnes morales : zone obligatoire si personne morale auteur.
     */
    private String numeroSirenSiret;

    /**
     * Texte libre permettant de saisir le statut juridique d'une personne morale, de fa�on approximative.
     */
    private String prefixeStatutJuridique;

    /**
     * Num�ro d?enregistrement de la soci�t� au registre du commerce + ville.
     */
    private String numeroRcs;

    /**
     * Date � laquelle la soci�t� a �t� enregistr�e au RCS (registre du commerce).
     */
    private Date dateCreationSociete;

    /**
     * Lieu o� la soci�t� a �t� cr��e. Un libell� de ville est attendu.
     */
    private String lieuCreationSociete;

    /**
     * Date � laquelle la soci�t� n?a pu r�pondre de ses cr�ances.
     */
    private Date dateCessationPaiement;

    /**
     * Le "sexe" des personnes morales est un indicateur de f�minisation des courriers.
     */
    private Sexe sexeCourrier;

    /**
     * Adresse de la personne morale
     */
    private AdressePersonneMorale adresse;

    /**
     * Personne morale souvent saisie dans des affaires, en tant que victime ou personne li�e. Exemples de grand compte
     * victime : RATP - SNCF - grands magasins.
     */
    private GrandCompte grandCompte;

    private String telephone;

    /**
     * Email pour la plainte en ligne
     */
    private String emailPpel;

    /**
     * Retourne le grand compte.
     *
     * @return grandCompte.
     */
    public GrandCompte getGrandCompte() {
        return grandCompte;
    }

    /**
     * Positionne le grand compte .
     *
     * @param grandCompte
     *            .
     */
    public void setGrandCompte(GrandCompte grandCompte) {
        this.grandCompte = grandCompte;
    }

    public AdressePersonneMorale getAdresse() {
        return adresse;
    }

    public void setAdresse(AdressePersonneMorale adresse) {
        this.adresse = adresse;
    }

    public Date getDateCessationPaiement() {
        return dateCessationPaiement;
    }

    public void setDateCessationPaiement(Date moraleDateCessationPaiemen) {
        this.dateCessationPaiement = moraleDateCessationPaiemen;
    }

    public Date getDateCreationSociete() {
        return dateCreationSociete;
    }

    public void setDateCreationSociete(Date moraleDateCreationSociete) {
        this.dateCreationSociete = moraleDateCreationSociete;
    }

    public String getEnseigne() {
        return enseigne;
    }

    public void setEnseigne(String moraleEnseigne) {
        this.enseigne = moraleEnseigne;
    }

    public String getLieuCreationSociete() {
        return lieuCreationSociete;
    }

    public void setLieuCreationSociete(String moraleLieuCreationSociete) {
        this.lieuCreationSociete = moraleLieuCreationSociete;
    }

    public String getNumeroRcs() {
        return numeroRcs;
    }

    public void setNumeroRcs(String moraleNumeroRcs) {
        this.numeroRcs = moraleNumeroRcs;
    }

    public String getPrefixeStatutJuridique() {
        return prefixeStatutJuridique;
    }

    public void setPrefixeStatutJuridique(String moralePrefixeStatutJuridiq) {
        this.prefixeStatutJuridique = moralePrefixeStatutJuridiq;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String moraleRaisonSociale) {
        this.raisonSociale = moraleRaisonSociale;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String moraleSigle) {
        this.sigle = moraleSigle;
    }

    public String getNumeroSirenSiret() {
        return numeroSirenSiret;
    }

    public void setNumeroSirenSiret(String moraleSirenSiret) {
        this.numeroSirenSiret = moraleSirenSiret;
    }

    public fr.gouv.justice.cassiopee.referentiel.codification.model.StatutJuridique getFormeJuridique() {
        return formeJuridique;
    }

    public void setFormeJuridique(
            fr.gouv.justice.cassiopee.referentiel.codification.model.StatutJuridique statutJuridique) {
        this.formeJuridique = statutJuridique;
    }

    @Override
    public String calculeLibelle() {
        return raisonSociale;
    }

    public Sexe getSexeCourrier() {
        return sexeCourrier;
    }

    public void setSexeCourrier(Sexe sexe) {
        this.sexeCourrier = sexe;
    }

    public String getRaisonSocialeMajuscule() {
        return raisonSocialeMajuscule;
    }

    public void setRaisonSocialeMajuscule(String raisonSocialeMajuscule) {
        this.raisonSocialeMajuscule = raisonSocialeMajuscule;
    }

    public String getEnseigneMajuscule() {
        return enseigneMajuscule;
    }

    public void setEnseigneMajuscule(String enseigneMajuscule) {
        this.enseigneMajuscule = enseigneMajuscule;
    }

    public String getSigleMajuscule() {
        return sigleMajuscule;
    }

    public void setSigleMajuscule(String sigleMajuscule) {
        this.sigleMajuscule = sigleMajuscule;
    }

    @Override
    public TypePersonneEnum getTypePersonne() {
        return TypePersonneEnum.MORALE;
    }

    @Override
    public boolean isGenreFeminin() {
        if (getSexeCourrier() == null) {
            return false;
        }
        return Sexe.FEMININ.equalsIgnoreCase(this.getSexeCourrier().getCode());
    }

    @Override
    public CategoriePenale getCategoriePenale() {
        // la personne morale n'a pas de cat�gorie p�nale, on renvoie null
        return null;
    }

    @Override
    public Adresse getAdresseResidence() {
        return this.getAdresse();
    }

    @Override
    public String getLibellePersonnePourNoeudSynthese() {
        return raisonSociale;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     *            the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmailPpel() {
        return emailPpel;
    }

    public void setEmailPpel(String emailPpel) {
        this.emailPpel = emailPpel;
    }


}
