package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;
import java.util.Date;

import fr.gouv.justice.cassiopee.common.util.DateIncomplete;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Commune;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Pays;

/**
 * Identité usurpée, falsifiée ou imaginaire donnée par un délinquant pour dissimuler son identité réelle et cacher son
 * passé judiciaire. Pour un alias aucun attribut obligatoire (pas même le nom de naissance : l?alias peut être un
 * sobriquet saisi en nom d?usage).
 */
public class Alias implements Serializable, Cloneable {

    private static final long serialVersionUID = 6506725533867011878L;

    private Long id;

    /** Personne correspondant à cette alias */
    private Personne personne;

    /** Pays de naissance de la personne */
    private Pays paysNaissance;

    /** commune de naissance si elle est francaise */
    private Commune communeNaissanceFrancaise;

    /**
     * Nom de naissance ou nom de famille ou nom de jeune-fille. Le nom de naissance tel qu?il figure à l?état civil est
     * celui qui a été transmis selon les règles propres à chaque filiation et qui résulte de son acte de naissance. Il
     * peut être simple ou composé. Ce nom est celui que connaît le répertoire national de l?identité des personnes
     * physiques exploité par le casier judiciaire. Dans la famille naturelle, l?enfant peut porter le nom du père et
     * pas celui de la mère. Ce nom peut avoir fait l?objet d?un changement : - par décret ; - ou par décision
     * judiciaire (ex : changement de nom de l?enfant naturel, établissement ou modification d?une filiation ayant une
     * incidence sur le nom, ?) ; - ou suite à une déclaration conjointe devant le juge des tutelles (enfants naturels
     * mineurs). Surnom (porté dans l?acte de naissance). Selon l?article 2 de la loi du 6 Fructivor AII, il est défendu
     * d?ajouter à son nom propre, à moins qu?il n?ait servi jusqu?ici à distinguer les membres d?une même famille, sans
     * rappeler des qualifications féodales ou nobiliaires. Remarque : nom de naissance, prénom, date et lieu de
     * naissance permettent d'identifier une personne physique. Ces attributs ne forment pourtant pas une clé composée
     * pour la personne physique (plusieurs personnes physiques peuvent avoir exactement les mêmes nom de naissance,
     * prénom, date et lieu de naissance).
     */
    private String nomNaissance;

    private String nomNaissanceMajuscule;

    /**
     * Le nom d?usage, nom qui ne résulte pas de la filiation, n?est pas mentionné à l?état civil, mais peut figurer sur
     * des documents administratifs (carte nationale d?identité, passeport, etc.). Il est défini par l?article 43 de la
     * loi du 23 décembre 1985, par les circulaires du 26 juin 1986 et du 4 novembre 1987 du Premier Ministre. Selon
     * l?article 43, toute personne majeure peut ajouter à son nom, à titre d?usage, le nom de celui de ses parents qui
     * ne lui a pas transmis le sien. Le nom d?usage peut également s?établir comme suit : a) pour la femme mariée ou
     * veuve, par adjonction ou par substitution à son patronyme du nom patronymique de son mari ou du nom dont il fait
     * usage (arrêté du 26 juin 1986) ; b) pour l?homme marié ou veuf, par adjonction à son patronyme de celui de sa
     * femme ou du nom dont elle fait usage (arrêté du 26 juin 1986) ; c) pour la femme divorcée, par le maintien du
     * droit à l?usage du nom de l?ex-époux (surtout si celui-ci est utilisé pour des raisons professionnelles), soit de
     * plein droit en cas de divorce pour rupture de vie commune demandé par le mari, soit par convention avec
     * l?ex-époux, soit par jugement. Toutefois, la femme divorcée qui a conservé l?usage du nom de son ex-conjoint, les
     * veufs ou les veuves perdent le droit d?user du patronyme ou du nom d?usage du précédent conjoint lorsqu?ils se
     * remarient et quel que soit le devenir de cette nouvelle union.
     */
    private String nomUsage;

    private String nomUsageMajuscule;

    /**
     * Prénom ou premier prénom. Il s?agit d?un nom vocable particulier joint à patronyme servant à distinguer les
     * différentes personnes d?une même famille. Un prénom composé doit être saisi avec un trait d?union. Il est non
     * obligatoire si la personne physique est née hors du territoire national. Les prénoms doivent être enregistrés
     * dans l?ordre de l?état civil. Remarque : nom de naissance, prénom, date et lieu de naissance permettent
     * d'identifier une personne physique. Ces attributs ne forment pourtant pas une clé composée pour la personne
     * physique (plusieurs personnes physiques peuvent avoir exactement les mêmes nom de naissance, prénom, date et lieu
     * de naissance).
     */
    private String prenom;

    private String prenomMajuscule;

    /**
     * Date à laquelle est née la personne physique. Date incomplète : personne née dans un pays ou les registres d?état
     * civil n?existent pas, ou dates tronquées récupérées de la reprise de données. C'est pourquoi sont mémorisés
     * indépendamment le jour, le mois, et l'année de la date. Remarque : nom de naissance, prénom, date et lieu de
     * naissance permettent d'identifier une personne physique. Ces attributs ne forment pourtant pas une clé composée
     * pour la personne physique (plusieurs personnes physiques peuvent avoir exactement les mêmes nom de naissance,
     * prénom, date et lieu de naissance).
     */
    private DateIncomplete dateNaissance;

    /**
     * Libellé de la commune de naissance lorsqu'il s'agit d'une ville étrangère. Elle est saisie en texte libre.
     * Remarque : lorsque la personne est née en France, le libellé de sa commune de naissance est retrouvé par son code
     * INSEE (enregistré dans l'attribut "commune naissance en France").
     */
    private String communeNaissanceEtrangere;

    /** Nom du père. */
    private String nomPere;

    /**
     * Les 3 prénoms du père doivent être séparés par des espaces pour ne pas les confondre avec des prénoms composés
     * (qui être saisis avec un trait d?union). Les prénoms doivent être enregistrés dans l?ordre de l?état civil. Ces 3
     * prénoms sont tous saisis dans cet attribut.
     */
    private String prenomsPere;

    /** Nom de la mère. */
    private String nomMere;

    /**
     * Les 3 prénoms de la mère doivent être séparés par des espaces pour ne pas les confondre avec des prénoms composés
     * (qui doivent être saisis avec un trait d?union). Les prénoms doivent être enregistrés dans l?ordre de l?état
     * civil. Ces 3 prénoms sont tous saisis dans cet attribut.
     */
    private String prenomsMere;

    /**
     * Date à laquelle l?identité de la personne a pu être établie par l?identité judiciaire.
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
     * Retourne le nom de naissance en majuscule. Ce champ est utilisé lors de la recherche, pour éviter les problèmes
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
     * Retourne le nom d'usage en majuscule. Ce champ est utilisé lors de la recherche, pour éviter les problèmes
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
     * Retourne le prénom en majuscule. Ce champ est utilisé lors de la recherche, pour éviter les problèmes d'accents.
     *
     * @return le prénom en majuscule.
     */
    public String getPrenomMajuscule() {
        return prenomMajuscule;
    }

    /**
     * Positionne le nom d'usage en majuscule.
     *
     * @param prenomMajuscule
     *            le prénom en majuscule.
     */
    public void setPrenomMajuscule(String prenomMajuscule) {
        this.prenomMajuscule = prenomMajuscule;
    }

    /**
     * Retourne le libellé de l'alias affiché dans les listes de recherche.
     *
     * @return le libellé de l'alias affiché dans les listes de recherche.
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