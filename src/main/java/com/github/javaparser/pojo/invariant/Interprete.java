package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;

import fr.gouv.justice.cassiopee.administration.acteur.externe.service.enumeration.PrestationSermentPersonneQualifieeEnum;
import fr.gouv.justice.cassiopee.invariant.edition.model.DestinataireEdition;
import fr.gouv.justice.cassiopee.referentiel.codification.model.Civilite;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;

/**
 * Interprète de la personne de l'affaire : personne qualifiée chargée de la traduction entre la langue de la personne
 * et le français. Seul un interprète par personne est enregistré dans Cassiopée. La prestation de serment d'un
 * interprète est déterminée comme suit : près le TGI : expert TGI = Vrai, pas de cour d'appel ou de cassation près la
 * cour d'appel : expert TGI = faux, cour d'appel ou de cassation renseignée, expert national = Vrai près la cour de
 * cassation : expert TGI = faux, cour d'appel ou de cassation renseignée, expert national = Faux non inscrit : expert
 * TGI = faux, pas de cour d'appel ou de cassation
 */
public class Interprete implements DestinataireEdition<Long>, Serializable {

    private static final long serialVersionUID = -8263088354330563985L;

    private Long id;

    /**
     * Civilité de l'acteur, au sens de l'état civil. Cet attribut ne propose pas les dénominations honorifiques (par
     * exemple "maître" pour les avocats).
     */
    private Civilite civilite;

    /**
     * Personne que l'interprête traduit.
     */
    private Personne personne;

    /**
     * si la Prestation de serment est "près la cour d'appel" on peut renseigné la cour d'appel
     */
    private ElementStructure courAppel;

    /** Nom de l'interprète. */
    private String nom;

    /** Prénom de l'interprète. */
    private String prenom;

    /**
     * Prestation de serment avec 4 valeurs possibles près TGI,près la cour d'appel,près la cour de cassation,non
     * inscrit
     */
    private String codePrestationSerment;

    /**
     * Vaut Vrai si l'interprète est présent. Vaut Faux sinon.<br>
     * Donnée utilisée pour les PV de 1e comparution et de débat contradictoire.
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
     * Retourne Vrai si l'interprète est présent. Vaut Faux sinon.<br>
     * Donnée utilisée pour les PV de 1e comparution et de débat contradictoire.<br>
     * Attention : un NullPointerException est envoyé si l'attribut est nul.
     *
     * @return Vrai si l'interprète est présent. Vaut Faux sinon.
     */
    public Boolean getPresencePv1ereComparution() {
        return presencePv1ereComparution;
    }

    /**
     * Retourne Vrai si l'interprète est présent. Vaut Faux si absent ou non renseigné.<br>
     * Donnée utilisée pour les PV de 1e comparution et de débat contradictoire.
     *
     * @return Vrai si la présence de l'interprète est renseignée et vrai. Vaut Faux sinon.
     */
    public boolean isPresentPv1ereComparution() {
        return presencePv1ereComparution != null && presencePv1ereComparution.booleanValue();
    }

    /**
     * Postionne Vrai si l'interprète est présent. Vaut Faux sinon.<br>
     * Donnée utilisée pour les PV de 1e comparution et de débat contradictoire.
     *
     * @param present
     *            Vrai si l'interprète est présent. Vaut Faux sinon.
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
