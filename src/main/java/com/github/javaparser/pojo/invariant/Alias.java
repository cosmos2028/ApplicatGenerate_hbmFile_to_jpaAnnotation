package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;
import java.util.Date;

import fr.gouv.justice.cassiopee.common.util.DateIncomplete;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Commune;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Pays;

/**
 * Identit� usurp�e, falsifi�e ou imaginaire donn�e par un d�linquant pour dissimuler son identit� r�elle et cacher son
 * pass� judiciaire. Pour un alias aucun attribut obligatoire (pas m�me le nom de naissance : l?alias peut �tre un
 * sobriquet saisi en nom d?usage).
 */
public class Alias implements Serializable, Cloneable {

    private static final long serialVersionUID = 6506725533867011878L;

    private Long id;

    /** Personne correspondant � cette alias */
    private Personne personne;

    /** Pays de naissance de la personne */
    private Pays paysNaissance;

    /** commune de naissance si elle est francaise */
    private Commune communeNaissanceFrancaise;

    /**
     * Nom de naissance ou nom de famille ou nom de jeune-fille. Le nom de naissance tel qu?il figure � l?�tat civil est
     * celui qui a �t� transmis selon les r�gles propres � chaque filiation et qui r�sulte de son acte de naissance. Il
     * peut �tre simple ou compos�. Ce nom est celui que conna�t le r�pertoire national de l?identit� des personnes
     * physiques exploit� par le casier judiciaire. Dans la famille naturelle, l?enfant peut porter le nom du p�re et
     * pas celui de la m�re. Ce nom peut avoir fait l?objet d?un changement : - par d�cret ; - ou par d�cision
     * judiciaire (ex : changement de nom de l?enfant naturel, �tablissement ou modification d?une filiation ayant une
     * incidence sur le nom, ?) ; - ou suite � une d�claration conjointe devant le juge des tutelles (enfants naturels
     * mineurs). Surnom (port� dans l?acte de naissance). Selon l?article 2 de la loi du 6 Fructivor AII, il est d�fendu
     * d?ajouter � son nom propre, � moins qu?il n?ait servi jusqu?ici � distinguer les membres d?une m�me famille, sans
     * rappeler des qualifications f�odales ou nobiliaires. Remarque : nom de naissance, pr�nom, date et lieu de
     * naissance permettent d'identifier une personne physique. Ces attributs ne forment pourtant pas une cl� compos�e
     * pour la personne physique (plusieurs personnes physiques peuvent avoir exactement les m�mes nom de naissance,
     * pr�nom, date et lieu de naissance).
     */
    private String nomNaissance;

    private String nomNaissanceMajuscule;

    /**
     * Le nom d?usage, nom qui ne r�sulte pas de la filiation, n?est pas mentionn� � l?�tat civil, mais peut figurer sur
     * des documents administratifs (carte nationale d?identit�, passeport, etc.). Il est d�fini par l?article 43 de la
     * loi du 23 d�cembre 1985, par les circulaires du 26 juin 1986 et du 4 novembre 1987 du Premier Ministre. Selon
     * l?article 43, toute personne majeure peut ajouter � son nom, � titre d?usage, le nom de celui de ses parents qui
     * ne lui a pas transmis le sien. Le nom d?usage peut �galement s?�tablir comme suit : a) pour la femme mari�e ou
     * veuve, par adjonction ou par substitution � son patronyme du nom patronymique de son mari ou du nom dont il fait
     * usage (arr�t� du 26 juin 1986) ; b) pour l?homme mari� ou veuf, par adjonction � son patronyme de celui de sa
     * femme ou du nom dont elle fait usage (arr�t� du 26 juin 1986) ; c) pour la femme divorc�e, par le maintien du
     * droit � l?usage du nom de l?ex-�poux (surtout si celui-ci est utilis� pour des raisons professionnelles), soit de
     * plein droit en cas de divorce pour rupture de vie commune demand� par le mari, soit par convention avec
     * l?ex-�poux, soit par jugement. Toutefois, la femme divorc�e qui a conserv� l?usage du nom de son ex-conjoint, les
     * veufs ou les veuves perdent le droit d?user du patronyme ou du nom d?usage du pr�c�dent conjoint lorsqu?ils se
     * remarient et quel que soit le devenir de cette nouvelle union.
     */
    private String nomUsage;

    private String nomUsageMajuscule;

    /**
     * Pr�nom ou premier pr�nom. Il s?agit d?un nom vocable particulier joint � patronyme servant � distinguer les
     * diff�rentes personnes d?une m�me famille. Un pr�nom compos� doit �tre saisi avec un trait d?union. Il est non
     * obligatoire si la personne physique est n�e hors du territoire national. Les pr�noms doivent �tre enregistr�s
     * dans l?ordre de l?�tat civil. Remarque : nom de naissance, pr�nom, date et lieu de naissance permettent
     * d'identifier une personne physique. Ces attributs ne forment pourtant pas une cl� compos�e pour la personne
     * physique (plusieurs personnes physiques peuvent avoir exactement les m�mes nom de naissance, pr�nom, date et lieu
     * de naissance).
     */
    private String prenom;

    private String prenomMajuscule;

    /**
     * Date � laquelle est n�e la personne physique. Date incompl�te : personne n�e dans un pays ou les registres d?�tat
     * civil n?existent pas, ou dates tronqu�es r�cup�r�es de la reprise de donn�es. C'est pourquoi sont m�moris�s
     * ind�pendamment le jour, le mois, et l'ann�e de la date. Remarque : nom de naissance, pr�nom, date et lieu de
     * naissance permettent d'identifier une personne physique. Ces attributs ne forment pourtant pas une cl� compos�e
     * pour la personne physique (plusieurs personnes physiques peuvent avoir exactement les m�mes nom de naissance,
     * pr�nom, date et lieu de naissance).
     */
    private DateIncomplete dateNaissance;

    /**
     * Libell� de la commune de naissance lorsqu'il s'agit d'une ville �trang�re. Elle est saisie en texte libre.
     * Remarque : lorsque la personne est n�e en France, le libell� de sa commune de naissance est retrouv� par son code
     * INSEE (enregistr� dans l'attribut "commune naissance en France").
     */
    private String communeNaissanceEtrangere;

    /** Nom du p�re. */
    private String nomPere;

    /**
     * Les 3 pr�noms du p�re doivent �tre s�par�s par des espaces pour ne pas les confondre avec des pr�noms compos�s
     * (qui �tre saisis avec un trait d?union). Les pr�noms doivent �tre enregistr�s dans l?ordre de l?�tat civil. Ces 3
     * pr�noms sont tous saisis dans cet attribut.
     */
    private String prenomsPere;

    /** Nom de la m�re. */
    private String nomMere;

    /**
     * Les 3 pr�noms de la m�re doivent �tre s�par�s par des espaces pour ne pas les confondre avec des pr�noms compos�s
     * (qui doivent �tre saisis avec un trait d?union). Les pr�noms doivent �tre enregistr�s dans l?ordre de l?�tat
     * civil. Ces 3 pr�noms sont tous saisis dans cet attribut.
     */
    private String prenomsMere;

    /**
     * Date � laquelle l?identit� de la personne a pu �tre �tablie par l?identit� judiciaire.
     */
    private Date dateIdentificationJudiciaire;

    /** Rang de saisie de l'alias. Permet de trier la liste. */
    private Integer rang;

    /**
     * @return Retourne communeNaissanceFrancaise
     */
    public Commune getCommuneNaissanceFrancaise() {
        return communeNaissanceFrancaise;
    }

    /**
     * @param communeNaissanceFrancaise
     *            affecte communeNaissanceFrancaise.
     */
    public void setCommuneNaissanceFrancaise(Commune communeNaissanceFrancaise) {
        this.communeNaissanceFrancaise = communeNaissanceFrancaise;
    }

    /**
     * @return retourne communeNaissanceEtrangere.
     */
    public String getCommuneNaissanceEtrangere() {
        return communeNaissanceEtrangere;
    }

    /**
     * @param communeNaissanceEtrangere
     *            affecte communeNaissanceEtrangere
     */
    public void setCommuneNaissanceEtrangere(String communeNaissanceEtrangere) {
        this.communeNaissanceEtrangere = communeNaissanceEtrangere;
    }

    /**
     * @return retourne dateIdentificationJudiciaire.
     */
    public Date getDateIdentificationJudiciaire() {
        return dateIdentificationJudiciaire;
    }

    /**
     * @param dateIdentificationJudiciaire
     *            affecte dateIdentificationJudiciaire
     */
    public void setDateIdentificationJudiciaire(Date dateIdentificationJudiciaire) {
        this.dateIdentificationJudiciaire = dateIdentificationJudiciaire;
    }

    /**
     * @return retourne dateNaissance.
     */
    public DateIncomplete getDateNaissance() {
        return dateNaissance;
    }

    /**
     * @param dateNaissance
     *            affecte dateNaissance
     */
    public void setDateNaissance(DateIncomplete dateNaissance) {
        this.dateNaissance = dateNaissance;
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
     * @return retourne nomMere.
     */
    public String getNomMere() {
        return nomMere;
    }

    /**
     * @param nomMere
     *            affecte nomMere
     */
    public void setNomMere(String nomMere) {
        this.nomMere = nomMere;
    }

    /**
     * @return retourne nomNaissance.
     */
    public String getNomNaissance() {
        return nomNaissance;
    }

    /**
     * @param nomNaissance
     *            affecte nomNaissance
     */
    public void setNomNaissance(String nomNaissance) {
        this.nomNaissance = nomNaissance;
    }

    /**
     * @return retourne nomPere.
     */
    public String getNomPere() {
        return nomPere;
    }

    /**
     * @param nomPere
     *            affecte nomPere
     */
    public void setNomPere(String nomPere) {
        this.nomPere = nomPere;
    }

    /**
     * @return retourne nomUsage.
     */
    public String getNomUsage() {
        return nomUsage;
    }

    /**
     * @param nomUsage
     *            affecte nomUsage
     */
    public void setNomUsage(String nomUsage) {
        this.nomUsage = nomUsage;
    }

    /**
     * @return retourne paysNaissance.
     */
    public Pays getPaysNaissance() {
        return paysNaissance;
    }

    /**
     * @param paysNaissance
     *            affecte paysNaissance
     */
    public void setPaysNaissance(Pays paysNaissance) {
        this.paysNaissance = paysNaissance;
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
     * @return retourne personnePhysique.
     */
    public PersonnePhysique getPersonnePhysique() {
        return (PersonnePhysique) personne;
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
     * @return retourne prenomsMere.
     */
    public String getPrenomsMere() {
        return prenomsMere;
    }

    /**
     * @param prenomsMere
     *            affecte prenomsMere
     */
    public void setPrenomsMere(String prenomsMere) {
        this.prenomsMere = prenomsMere;
    }

    /**
     * @return retourne prenomsPere.
     */
    public String getPrenomsPere() {
        return prenomsPere;
    }

    /**
     * @param prenomsPere
     *            affecte prenomsPere
     */
    public void setPrenomsPere(String prenomsPere) {
        this.prenomsPere = prenomsPere;
    }

    /**
     * @return retourne rang.
     */
    public Integer getRang() {
        return rang;
    }

    /**
     * @param rang
     *            affecte rang
     */
    public void setRang(Integer rang) {
        this.rang = rang;
    }

    /**
     * Retourne le nom de naissance en majuscule. Ce champ est utilis� lors de la recherche, pour �viter les probl�mes
     * d'accents.
     *
     * @return le nom de naissance en majuscule.
     */
    public String getNomNaissanceMajuscule() {
        return nomNaissanceMajuscule;
    }

    /**
     * Positionne le nom de naissance en majuscule.
     *
     * @param nomNaissanceMajuscule
     *            le nom de naissance en majuscule.
     */
    public void setNomNaissanceMajuscule(String nomNaissanceMajuscule) {
        this.nomNaissanceMajuscule = nomNaissanceMajuscule;
    }

    /**
     * Retourne le nom d'usage en majuscule. Ce champ est utilis� lors de la recherche, pour �viter les probl�mes
     * d'accents.
     *
     * @return le nom d'usage en majuscule.
     */
    public String getNomUsageMajuscule() {
        return nomUsageMajuscule;
    }

    /**
     * Positionne le nom d'usage en majuscule.
     *
     * @param nomUsageMajuscule
     *            le nom d'usage en majuscule.
     */
    public void setNomUsageMajuscule(String nomUsageMajuscule) {
        this.nomUsageMajuscule = nomUsageMajuscule;
    }

    /**
     * Retourne le pr�nom en majuscule. Ce champ est utilis� lors de la recherche, pour �viter les probl�mes d'accents.
     *
     * @return le pr�nom en majuscule.
     */
    public String getPrenomMajuscule() {
        return prenomMajuscule;
    }

    /**
     * Positionne le nom d'usage en majuscule.
     *
     * @param prenomMajuscule
     *            le pr�nom en majuscule.
     */
    public void setPrenomMajuscule(String prenomMajuscule) {
        this.prenomMajuscule = prenomMajuscule;
    }

    /**
     * Retourne le libell� de l'alias affich� dans les listes de recherche.
     *
     * @return le libell� de l'alias affich� dans les listes de recherche.
     */
    public String getLibelleSpecifiqueRecherche() {
        return (new StringBuilder(nomNaissance)).append(" ").append(nomUsage).append(" ").append(prenom).toString();
    }

    /**
     * Duplique l'alias
     */
    public Object clone() throws CloneNotSupportedException {
        Alias newAlias = (Alias) super.clone();
        newAlias.setId(null);
        newAlias.setPersonne(null);
        if (dateNaissance != null) {
            newAlias.setDateNaissance((DateIncomplete) dateNaissance.clone());
        }
        if (dateIdentificationJudiciaire != null) {
            newAlias.setDateIdentificationJudiciaire((Date) dateIdentificationJudiciaire.clone());
        }
        if (rang != null) {
            newAlias.setRang(Integer.valueOf(rang));
        }
        return newAlias;
    }
}