package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;

import fr.gouv.justice.cassiopee.administration.acteur.externe.service.enumeration.PrestationSermentPersonneQualifieeEnum;
import fr.gouv.justice.cassiopee.invariant.edition.model.DestinataireEdition;
import fr.gouv.justice.cassiopee.referentiel.codification.model.Civilite;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;

/**
 * Interpr�te de la personne de l'affaire : personne qualifi�e charg�e de la traduction entre la langue de la personne
 * et le fran�ais. Seul un interpr�te par personne est enregistr� dans Cassiop�e. La prestation de serment d'un
 * interpr�te est d�termin�e comme suit : pr�s le TGI : expert TGI = Vrai, pas de cour d'appel ou de cassation pr�s la
 * cour d'appel : expert TGI = faux, cour d'appel ou de cassation renseign�e, expert national = Vrai pr�s la cour de
 * cassation : expert TGI = faux, cour d'appel ou de cassation renseign�e, expert national = Faux non inscrit : expert
 * TGI = faux, pas de cour d'appel ou de cassation
 */
public class Interprete implements DestinataireEdition<Long>, Serializable {

    private static final long serialVersionUID = -8263088354330563985L;

    private Long id;

    /**
     * Civilit� de l'acteur, au sens de l'�tat civil. Cet attribut ne propose pas les d�nominations honorifiques (par
     * exemple "ma�tre" pour les avocats).
     */
    private Civilite civilite;

    /**
     * Personne que l'interpr�te traduit.
     */
    private Personne personne;

    /**
     * si la Prestation de serment est "pr�s la cour d'appel" on peut renseign� la cour d'appel
     */
    private ElementStructure courAppel;

    /** Nom de l'interpr�te. */
    private String nom;

    /** Pr�nom de l'interpr�te. */
    private String prenom;

    /**
     * Prestation de serment avec 4 valeurs possibles pr�s TGI,pr�s la cour d'appel,pr�s la cour de cassation,non
     * inscrit
     */
    private String codePrestationSerment;

    /**
     * Vaut Vrai si l'interpr�te est pr�sent. Vaut Faux sinon.<br>
     * Donn�e utilis�e pour les PV de 1e comparution et de d�bat contradictoire.
     */
    private Boolean presencePv1ereComparution;

    private AdresseInterprete adresse;

    /**
     * @return retourne adresse.
     */
    public AdresseInterprete getAdresse() {
        return adresse;
    }

    /**
     * @param adresse
     *            affecte adresse
     */
    public void setAdresse(AdresseInterprete adresse) {
        this.adresse = adresse;
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
     * @return retourne courAppel
     */
    public ElementStructure getCourAppel() {
        return courAppel;
    }

    /**
     * @param courAppel
     *            affecte courAppel
     */
    public void setCourAppel(ElementStructure courAppel) {
        this.courAppel = courAppel;

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
     * @return retourne personne (qui est obligatoirement une personne physique).
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * @return retourne la personne (qui est obligatoirement une personne physique).
     */
    public PersonnePhysique getPersonnePhysique() {
        return (PersonnePhysique) personne;
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
     * Retourne Vrai si l'interpr�te est pr�sent. Vaut Faux sinon.<br>
     * Donn�e utilis�e pour les PV de 1e comparution et de d�bat contradictoire.<br>
     * Attention : un NullPointerException est envoy� si l'attribut est nul.
     *
     * @return Vrai si l'interpr�te est pr�sent. Vaut Faux sinon.
     */
    public Boolean getPresencePv1ereComparution() {
        return presencePv1ereComparution;
    }

    /**
     * Retourne Vrai si l'interpr�te est pr�sent. Vaut Faux si absent ou non renseign�.<br>
     * Donn�e utilis�e pour les PV de 1e comparution et de d�bat contradictoire.
     *
     * @return Vrai si la pr�sence de l'interpr�te est renseign�e et vrai. Vaut Faux sinon.
     */
    public boolean isPresentPv1ereComparution() {
        return presencePv1ereComparution != null && presencePv1ereComparution.booleanValue();
    }

    /**
     * Postionne Vrai si l'interpr�te est pr�sent. Vaut Faux sinon.<br>
     * Donn�e utilis�e pour les PV de 1e comparution et de d�bat contradictoire.
     *
     * @param present
     *            Vrai si l'interpr�te est pr�sent. Vaut Faux sinon.
     */
    public void setPresencePv1ereComparution(Boolean present) {
        this.presencePv1ereComparution = present;
    }

    /**
     * @return Returns the codePrestationSerment.
     */
    public String getCodePrestationSerment() {
        return codePrestationSerment;
    }

    /**
     * @param codePrestationSerment
     *            The codePrestationSerment to set.
     */
    public void setCodePrestationSerment(String codePrestationSerment) {
        this.codePrestationSerment = codePrestationSerment;
    }

    /**
     * @return retourne prestationSermentPersonneQualifieeEnum.
     */
    public PrestationSermentPersonneQualifieeEnum getPrestationSermentPersonneQualifieeEnum() {
        return PrestationSermentPersonneQualifieeEnum.resolve(this.codePrestationSerment);
    }

    /**
     * @param prestationSermentPersonneQualifieeEnum
     */
    public void setPrestationSermentPersonneQualifieeEnum(
            PrestationSermentPersonneQualifieeEnum prestationSermentPersonneQualifieeEnum) {
        this.codePrestationSerment = prestationSermentPersonneQualifieeEnum.getCode();
    }

    public Long getDestinataireEditionId() {
        return id;
    }

    /**
     * @return le libelle de l'interprepte
     */
    public String getLibelle() {
        return nom + " " + prenom;
    }

}
