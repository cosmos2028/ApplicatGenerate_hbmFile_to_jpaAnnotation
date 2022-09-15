package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import fr.gouv.justice.cassiopee.commons.metier.model.IdLibelle;
import fr.gouv.justice.cassiopee.invariant.edition.model.DestinataireEdition;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Emetteur;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.referentiel.codification.model.Civilite;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.CorrespondantTypeEnum;

/**
 * AvocatPersonne de la personne de l'affaire.
 */
public class AvocatAffaire implements Serializable, Emetteur<Long>, IdLibelle, DestinataireEdition<Long> {

    private static final long serialVersionUID = 3476889349762083587L;

    private Long id;

    /**
     * Civilité de l'acteur, au sens de l'état civil. Cet attribut ne propose pas les dénominations honorifiques (par
     * exemple "maître" pour les avocats).
     */
    private Civilite civilite;

    /**
     * Personne défendue par l'avocat
     */
    private Personne personne;

    /** Nom de l'avocat. */
    private String nom;

    /** Prénom de l'avocat. */
    private String prenom;

    /**
     * Numéro administratif de l'avocat, qui lui est attribué par le barreau auquel il appartient. Par exemple : numéro
     * de toque ou numéro de case. alphanumérique(6)
     */
    private String adresseInterne;

    /**
     * Nom du barreau auquel l?avocat est rattaché. Saisi en texte libre. Dans la plupart des cas, l'avocat appelé dans
     * une affaire est rattaché au barreau de la compétence géographique du TGI qui gère l'affaire. C'est pourquoi on
     * considère, dans Cassiopée, que l'acteur externe "avocat" est rattaché à un seul barreau. Si l'on a besoin d'un
     * avocat au titre de son rattachement à un autre barreau, on créera alors un autre acteur externe "avocat".
     */
    private String nomBarreau;

    /** Nom de la Société Civile Professionnelle dont l'avocat est membre. */
    private String nomScp;

    /**
     * Numéro d'affiliation à la Caisse nationale des barreaux français (caisse de retraite de 95% des avocats français
     * ; également caisse de prévoyance).
     */
    private String numeroCNBF;

    /**
     * Indicateur signalant si l?avocat a été commis d?office pour assister le prévenu à l?audience.
     */
    private boolean commisOffice = false;

    /**
     * Si la personne de l'affaire a des avocats, il y a un et un seul avocat qui reçoit les courriers pour l'affaire.
     */
    private Boolean destinataireCourriers;

    private Boolean defenseSpecialisee;

    /**
     * Un avocat est désigné s'il l'a été nommément par une personne de l'affaire. Un avocat est désigné à partir du
     * moment ou il est saisi dans Cassiopée pour une personne de l'affaire. Cette date est à la date à laquelle la
     * personne a désigné l'avocat.
     */
    private Date dateDesignation;

    /**
     * la date de fin d'intervention d'un avocat
     */
    private Date dateFinDesignation;

    /**
     * Vaut Vrai si l'avocat est présent. Vaut Faux sinon.<br>
     * Si le booléen est à faux, l'avocat peut avoir un avocat substituant.
     */
    private Boolean presencePv1ereComparution;

    /**
     * L'avocat qui avait été sollicité, est absent et substitué par un avocat disponible ce jour-là. L'identité de cet
     * avocat substituant est conservé pour édition. Cette donnée n'est renseignée que si l'avocat n'est pas présent.
     * Donnée utilisée pour les PV de 1e comparution, de débat contradictoire, et de jugement. Dans le cas de la 1ère
     * comparution et du débat contradictoire, elle est réellement volatile : elle sera purgée une fois l'acte saisi.
     * Par contre, sur un jugement, elle n'est pas volatile : on ne détruit pas les données d'une décision de justice
     * après saisie.
     */
    private String nomAvocatSubstituant;

    /**
     * L'avocat qui avait été sollicité, est absent et substitué par un avocat disponible ce jour-là. L'identité de cet
     * avocat substituant est conservé pour édition. Cette donnée n'est renseignée que si l'avocat n'est pas présent.
     * Donnée utilisée pour les PV de 1e comparution, de débat contradictoire, et de jugement. Dans le cas de la 1ère
     * comparution et du débat contradictoire, elle est réellement volatile : elle sera purgée une fois l'acte saisi.
     * Par contre, sur un jugement, elle n'est pas volatile : on ne détruit pas les données d'une décision de justice
     * après saisie.
     */
    private String prenomAvocatSubstituant;

    /**
     * Indique si l'avocat a été choisi pour le débat.
     */
    private Boolean choisiPourDebat;

    /**
     * Indique si l'avocat est actif ou inactif.
     */
    private Boolean actif = true;

    /**
     * Adresse de l'interprête
     */
    private AdresseAvocatPersonneAffaire adresse;

	//0129007: [Oracle]Requêtes qui font beaucoup d'I/O
    /** Nom de l'avocat en minusclue. */
    private String nomMin;

    /** Prénom de l'avocatminusclue. */
    private String prenomMin;

    private Set<Evenement> evenementEmis = new HashSet<>();

    /**
     * @return retourne adresse.
     */
    public AdresseAvocatPersonneAffaire getAdresse() {
        return adresse;
    }

    /**
     * @param adresse
     *            affecte adresse
     */
    public void setAdresse(AdresseAvocatPersonneAffaire adresse) {
        this.adresse = adresse;
    }

    /**
     * @return retourne adresseInterne.
     */
    public String getAdresseInterne() {
        return adresseInterne;
    }

    /**
     * @param adresseInterne
     *            affecte adresseInterne
     */
    public void setAdresseInterne(String adresseInterne) {
        this.adresseInterne = adresseInterne;
    }

    /**
     * @return retourne civilite.
     */
    public Civilite getCivilite() {
        return civilite;
    }

    /**
     * @param civilite
     *            affecte civilite
     */
    public void setCivilite(Civilite civilite) {
        this.civilite = civilite;
    }

    /**
     * @return retourne commisOffice.
     */
    public boolean getCommisOffice() {
        return commisOffice;
    }

    /**
     * @param commisOffice
     *            affecte commisOffice
     */
    public void setCommisOffice(boolean commisOffice) {
        this.commisOffice = commisOffice;
    }

    /**
     * @return retourne dateDesignation.
     */
    public Date getDateDesignation() {
        return dateDesignation;
    }

    /**
     * @param dateDesignation
     *            affecte dateDesignation
     */
    public void setDateDesignation(Date dateDesignation) {
        this.dateDesignation = dateDesignation;
    }


    public Date getDateFinDesignation() {
        return dateFinDesignation;
    }

    public void setDateFinDesignation(Date dateFinDesignation) {
        this.dateFinDesignation = dateFinDesignation;
    }

    /**
     * @return retourne destinataireCourriers.
     */
    public Boolean getDestinataireCourriers() {
        return Boolean.TRUE.equals(destinataireCourriers);
    }

    /**
     * @param destinataireCourriers
     *            affecte destinataireCourriers
     */
    public void setDestinataireCourriers(Boolean destinataireCourriers) {
        this.destinataireCourriers = destinataireCourriers;
    }

    /**
     * @return retourne id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            affecte id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return retourne nom.
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom
     *            affecte nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return retourne nomAvocatSubstituant.
     */
    public String getNomAvocatSubstituant() {
        return nomAvocatSubstituant;
    }

    /**
     * @param nomAvocatSubstituant
     *            affecte nomAvocatSubstituant
     */
    public void setNomAvocatSubstituant(String nomAvocatSubstituant) {
        this.nomAvocatSubstituant = nomAvocatSubstituant;
    }

    /**
     * @return retourne nomBarreau.
     */
    public String getNomBarreau() {
        return nomBarreau;
    }

    /**
     * @param nomBarreau
     *            affecte nomBarreau
     */
    public void setNomBarreau(String nomBarreau) {
        this.nomBarreau = nomBarreau;
    }

    /**
     * @return retourne nomScp.
     */
    public String getNomScp() {
        return nomScp;
    }

    /**
     * @param nomScp
     *            affecte nomScp
     */
    public void setNomScp(String nomScp) {
        this.nomScp = nomScp;
    }

    /**
     * @return retourne numeroCNBF.
     */
    public String getNumeroCNBF() {
        return numeroCNBF;
    }

    /**
     * @param numeroCNBF
     *            affecte numeroCNBF
     */
    public void setNumeroCNBF(String numeroCNBF) {
        this.numeroCNBF = numeroCNBF;
    }

    /**
     * @return retourne personne.
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * @param personne
     *            affecte personne
     */

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    /**
     * @return retourne prenom.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom
     *            affecte prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return retourne prenomAvocatSubstituant.
     */
    public String getPrenomAvocatSubstituant() {
        return prenomAvocatSubstituant;
    }

    /**
     * @param prenomAvocatSubstituant
     *            affecte prenomAvocatSubstituant
     */
    public void setPrenomAvocatSubstituant(String prenomAvocatSubstituant) {
        this.prenomAvocatSubstituant = prenomAvocatSubstituant;
    }

    /**
     * Retourne Vrai si l'avocat est présent. Vaut Faux sinon.<br>
     * Si le booléen est à faux, l'avocat peut avoir un avocat substituant.
     *
     * @return Vrai si l'avocat est présent. Vaut Faux sinon.
     */
    public Boolean getPresencePv1ereComparution() {
        return Boolean.TRUE.equals(presencePv1ereComparution);
    }

    /**
     * Positionne Vrai si l'avocat est présent. Vaut Faux sinon.
     *
     * @param present
     *            Vrai si l'avocat est présent. Vaut Faux sinon.
     */
    public void setPresencePv1ereComparution(Boolean present) {
        this.presencePv1ereComparution = present;
    }

    public Long getCorrespondantId() {
        return this.id;
    }

    public CorrespondantTypeEnum getCorrespondantType() {
        return CorrespondantTypeEnum.Avocat;
    }

    @SuppressWarnings("nls")
    public String getCorrespondantLibelle() {
        return this.nom + " " + this.prenom;
    }

    public Set<Evenement> getEvenementEmis() {
        return evenementEmis;
    }

    /**
     * @param evenementEmis
     *            affecte evenementEmis
     */
    public void setEvenementEmis(Set<Evenement> evenementEmis) {
        this.evenementEmis = evenementEmis;
    }

    /**
     * Indique si l'avocat a été choisi pour le débat.
     *
     * @return vrai si l'avocat a été choisi pour le débat, faux sinon.
     */
    public Boolean getChoisiPourDebat() {
        return Boolean.TRUE.equals(choisiPourDebat);
    }

    /**
     * Indique si l'avocat a été choisi pour le débat.
     *
     * @param choisiPourDebat
     *            vrai si l'avocat a été choisi pour le débat, faux sinon.
     */
    public void setChoisiPourDebat(Boolean choisiPourDebat) {
        this.choisiPourDebat = choisiPourDebat;
    }

    /**
     * Indique si l'avocat est actif ou inactif.
     *
     * @return vrai si l'avocat est actif, faux s'il est inactif.
     */
    public Boolean isActif() {
        return Boolean.TRUE.equals(actif);
    }

    /**
     * Indique si l'avocat est actif ou inactif.
     *
     * @param actif
     *            vrai si l'avocat est actif, faux s'il est inactif.
     */
    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    /**
     * Détermine si l'avocat est substitué lors des débats de l'instruction.<br>
     * Opération décrite dans SP_0036_05 - V01_calculerIndicateurSubstitué
     *
     * @return Vrai s'il existe un avocat substituant, faux sinon.
     */
    public boolean isSubstitueDebatInstruction() {
        if (StringUtils.isBlank(nomAvocatSubstituant)) {
            return false;
        }
        return true;
    }

    public String getLibelle() {
        return this.getCorrespondantLibelle();
    }

    public Long getDestinataireEditionId() {
        return id;
    }

    /**
     * @return the defenseSpecialisee
     */
    public Boolean getDefenseSpecialisee() {
        return Boolean.TRUE.equals(defenseSpecialisee);
    }

    /**
     * @param defenseSpecialisee
     *            the defenseSpecialisee to set
     */
    public void setDefenseSpecialisee(Boolean defenseSpecialisee) {
        this.defenseSpecialisee = defenseSpecialisee;
    }

	/**
	 * @return the nomMin
	 */
	public String getNomMin() {
		return nomMin;
	}

	/**
	 * @param nomMin the nomMin to set
	 */
	public void setNomMin(String nomMin) {
		this.nomMin = nomMin;
	}

	/**
	 * @return the prenomMin
	 */
	public String getPrenomMin() {
		return prenomMin;
	}

	/**
	 * @param prenomMin the prenomMin to set
	 */
	public void setPrenomMin(String prenomMin) {
		this.prenomMin = prenomMin;
	}
}
