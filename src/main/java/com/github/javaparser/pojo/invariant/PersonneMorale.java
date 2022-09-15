package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.util.Date;

import fr.gouv.justice.cassiopee.administration.locale.model.GrandCompte;
import fr.gouv.justice.cassiopee.commons.metier.model.Adresse;
import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.TypePersonneEnum;
import fr.gouv.justice.cassiopee.referentiel.codification.model.CategoriePenale;
import fr.gouv.justice.cassiopee.referentiel.codification.model.StatutJuridique;

/**
 * Groupement qui se voit reconnaître une existence juridique et qui, à ce titre, a des droits et des obligations, ex : société,
 * association… La personne morale est une entité juridique définie par sa raison sociale et son numéro de SIREN. Une personne physique lui
 * est systématiquement associée pour parler en son nom : selon le stade de l'affaire, ce peut être le représentant légal ou le déclarant.
 */
public class PersonneMorale extends Personne {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -8213269803979935118L;

    /**
     * La forme juridique est déterminée par les statuts et est propre à chaque société. Exemples : SA - SARL.
     */
    private StatutJuridique formeJuridique;

    /**
     * Raison Sociale ou Dénomination. Ce terme désigne une entreprise et permet de la distinguer des entreprises
     * concurrentes (élément du fonds de commerce).
     */
    private String raisonSociale;

    /**
     * L'attribut Raison sociale en majuscule est utilisé lors pour la recherche car, il peut exister des raison sociale
     * avec accent.
     */
    private String raisonSocialeMajuscule;

    /**
     * Nom sous lequel la société est connu du public. Marque distinctive d?un établissement.
     */
    private String enseigne;

    /**
     * L 'attribut enseigne en majuscule est utilisé pour la recherche car, il peut exister des enseigne avec accent.
     */
    private String enseigneMajuscule;

    /**
     * Abréviation d'un terme complexe
     */
    private String sigle;

    /**
     * Abrévation en majuscule, utilisée pour la recherche
     */
    private String sigleMajuscule;

    /**
     * SIREN : Codification établie par l’INSEE. Il comporte 9 chiffres, dont le dernier est une clé de contrôle, et est unique à chaque
     * immatriculation. Cette numérotation unique est affectée au siège et à tous les établissements d'une personne immatriculée au registre
     * du commerce et des sociétés y compris les établissements hors ressort. C'est l'identifiant retenu par le Casier judiciaire des
     * personnes morales : zone obligatoire si personne morale auteur.
     */
    private String numeroSirenSiret;

    /**
     * Texte libre permettant de saisir le statut juridique d'une personne morale, de façon approximative.
     */
    private String prefixeStatutJuridique;

    /**
     * Numéro d?enregistrement de la société au registre du commerce + ville.
     */
    private String numeroRcs;

    /**
     * Date à laquelle la société a été enregistrée au RCS (registre du commerce).
     */
    private Date dateCreationSociete;

    /**
     * Lieu où la société a été créée. Un libellé de ville est attendu.
     */
    private String lieuCreationSociete;

    /**
     * Date à laquelle la société n?a pu répondre de ses créances.
     */
    private Date dateCessationPaiement;

    /**
     * Le "sexe" des personnes morales est un indicateur de féminisation des courriers.
     */
    private Sexe sexeCourrier;

    /**
     * Adresse de la personne morale
     */
    private AdressePersonneMorale adresse;

    /**
     * Personne morale souvent saisie dans des affaires, en tant que victime ou personne liée. Exemples de grand compte
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
        // la personne morale n'a pas de catégorie pénale, on renvoie null
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
