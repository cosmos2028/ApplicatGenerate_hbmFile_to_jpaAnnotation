package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.administration.structure.model.Service;
import fr.gouv.justice.cassiopee.common.util.DateIncomplete;
import fr.gouv.justice.cassiopee.commons.metier.model.Adresse;
import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.RolePersonneEnum;
import fr.gouv.justice.cassiopee.referentiel.codification.model.CategoriePenale;
import fr.gouv.justice.cassiopee.referentiel.codification.model.Civilite;
import fr.gouv.justice.cassiopee.referentiel.codification.model.LangueParlee;
import fr.gouv.justice.cassiopee.referentiel.codification.model.SituationFamille;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Commune;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Nationalite;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Pays;
import fr.gouv.justice.cassiopee.service.accueil.model.PlanningDetention;

/**
 * Classe d�crivant une Personne physique, c'est � dire un individu. Cette personne est une personne mineure, une
 * personne majeure, ou une personne ind�termin�e.
 */
public abstract class PersonnePhysique extends Personne {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 8452047104483727305L;

    /**
     * Constantes permettant d'impl�menter la r�gle de gestion PERI040
     */
    @RegleDeGestion(RG.PERI040)
    public static final String libelleXseDisant = "X se disant";

    public static final String libelleDCD = "DCD";

    public static final String blank = " ";

    /** Nom de naissance ou nom de famille ou nom de jeune-fille. */
    private String nomNaissance;

    /**
     * Attribut technique repr�sentant le nom de naissance mis en majuscule. Il est utilis� lors de la recherche, pour
     * �viter les probl�mes d'accents.
     */
    private String nomNaissanceMajuscule;

    /**
     * Le nom d�usage, nom qui ne r�sulte pas de la filiation, n�est pas mentionn� � l��tat civil, mais peut figurer sur des documents
     * administratifs (carte nationale d�identit�, passeport, etc.).
     */
    private String nomUsage;

    /**
     * Attribut technique repr�sentant le nom de naissance mis en majuscule. Il est utilis� lors de la recherche, pour
     * �viter les probl�mes d'accents.
     */
    private String nomUsageMajuscule;

    /**
     * Pr�nom ou premier pr�nom. Le pr�nom n'est pas obligatoire pour les personnes suivantes : - les personnes n�es
     * hors du territoire, - pour les auteurs "X se disant", - pour les auteurs sans identit� connue et condamn�s sous
     * X.
     */
    private String prenom;

    /**
     * Attribut technique repr�sentant le nom de naissance mis en majuscule. Il est utilis� lors de la recherche, pour
     * �viter les probl�mes d'accents.
     */
    private String prenomMajuscule;

    /** civilit� de la personne */
    private Civilite civilite;

    /** date de naissance de la personne */
    private DateIncomplete dateNaissance;

    /** pays de naissance */
    private Pays paysNaissance;

    /**
     * La commune de naissance est bas�e sur les communes INSEE. La gestion des communes de lieu de naissance inclut les
     * communes ferm�es � ce jour mais ouvertes � la date de naissance par une historisation des � mouvements � de codes
     * Insee.
     */
    private Commune communeNaissanceFrancaise;

    /**
     * Libell� de la commune de naissance lorsqu'il s'agit d'une ville �trang�re. Elle est saisie en texte libre.
     */
    private String communeNaissanceEtrangere;

    /**
     * Attribut d�signant si il y a eu une modification des antecedants judiciaires manuellement
     */
    private boolean antecedentJudiciaireManuel;

    /**
     * Profession, travail dont la personne tire ses moyens d�existence. Il s'agit d'un libell�, qui peut �tre saisi sous la forme d'une
     * concat�nation des codes NAF (nature d�activit� fran�aise) et CS-ESE (cat�gorie socio professionnelle des emplois des salari�s
     * d�entreprises).
     */
    private String profession;

    /** Fonction de parlementaire, de repr�sentant politique, etc.. */
    private String fonctionElective;

    /**
     * Dur�e d'incapacit� temporaire de travail (ITT). Cet attribut est valoris� seulement pour une personne victime et
     * partie civile.
     */
    private String dureeItt;

    /**
     * Taux d'invalidit�. Cet attribut est valoris� seulement pour une personne victime et partie civile.
     */
    private Float tauxInvalidite;

    /**
     * La mort de la personne physique auteur entra�ne l�extinction de l�action publique et justifie du classement de la proc�dure. Vaut
     * Vrai si la personne est d�c�d�e. Vaut Faux sinon.
     */
    private Boolean indicateurDeces;

    /**
     * Permet de saisir une langue parl�e par la personne, lorsqu'elle n'existe pas dans la table de r�f�rence des
     * langues parl�es (issue du SR).
     */
    private String dialecte;

    /** adresses de la personne */
    private Set<AdressePersonnePhysique> adresses = new LinkedHashSet<>();

    /** �tat d�une personne par rapport � la famille. */
    private SituationFamille situationFamille;

    /**
     * Nationalit� ou premi�re nationalit�. Lien juridique qui unit une personne � un Etat d�termin� qui exerce une souverainet� sur une
     * entit� g�ographique. Il peut �tre diff�rent du code pays Iso. Exemples : Iles Fero�, Iles Falkland (Malvinas), Gro�nland,�
     */
    private Nationalite nationalite1;

    /** Deuxi�me nationalit�. */
    private Nationalite nationalite2;

    private Set<ErreurMaterielle> erreursMaterielles = new HashSet<>();

    /** Langue parl�e de la personne physique. */
    private LangueParlee langueParlee;

    private ComplementAuteurOuMineurSignale complementAuteurOuMineurSignale;

    /**
     * A saisir et non � calculer : au p�nal : uniquement pour infraction continue, et date de naissance incertaine. Donn�e utile au juge
     * des enfants en mati�re civile pour les statistiques par tranche d��ge. Exclusif avec �ge au moment des faits pour la victime.
     */
    private AgeMomentFaitsAuteur ageMomentFaitsAuteur;

    /**
     * A saisir et non � calculer : au p�nal : uniquement pour infraction continue, et date de naissance incertaine. Donn�e utile au juge
     * des enfants en mati�re civile pour les statistiques par tranche d��ge. Exclusif avec �ge au moment des faits pour l'auteur.
     */
    private AgeMomentFaitsVictime ageMomentFaitsVictime;

    /**
     * Utilis�s seulement pour les personnes li�es. Ce ne devrait th�oriquement m�me �tre que pour les personnes li�es �
     * un mineur. Quartier du domicile de la personne. Rappel : pour un mineur, on appelle "domicile" l'adresse postale
     * de ses parents, et "r�sidence" l'adresse postale du mineur s'il est plac� hors du foyer parental.
     */
    private String quartierDomicilePhysique;

    /**
     * Utilis�s seulement pour les personnes li�es. Ce ne devrait th�oriquement m�me �tre que pour les personnes li�es �
     * un mineur. T�l�phone du domicile de la personne. Rappel : pour un mineur, on appelle "domicile" l'adresse postale
     * de ses parents, et "r�sidence" l'adresse postale du mineur s'il est plac� hors du foyer parental.
     */
    private String telephoneDomicilePhysique;

    /**
     * Utilis�s seulement pour les personnes li�es. Ce ne devrait th�oriquement m�me �tre que pour les personnes li�es �
     * un mineur. Quartier de la r�sidence de la personne. Rappel : pour un mineur, on appelle "domicile" l'adresse
     * postale de ses parents, et "r�sidence" l'adresse postale du mineur s'il est plac� hors du foyer parental.
     */
    private String quartierResidencePhysique;

    /**
     * Utilis�s seulement pour les personnes li�es. Ce ne devrait th�oriquement m�me �tre que pour les personnes li�es �
     * un mineur. T�l�phone de la r�sidence de la personne. Rappel : pour un mineur, on appelle "domicile" l'adresse
     * postale de ses parents, et "r�sidence" l'adresse postale du mineur s'il est plac� hors du foyer parental.
     */
    private String telephoneResidencePhysique;

    /**
     * Date de majorite
     */
    private Date dateMajorite;

    /**
     * Utilis�s seulement pour les personnes li�es. Ce ne devrait th�oriquement m�me �tre que pour les personnes li�es �
     * un mineur.T�l�phone professionnel de la personne.
     */
    private String telephoneTravail;

    /**
     * Utilis�s seulement pour les personnes li�es. Ce ne devrait th�oriquement m�me �tre que pour les personnes li�es �
     * un mineur.Raison sociale de l'employeur de la personne
     */
    private String raisonSocialEmployeur;

    /** Sert pour le Rang de saisie de l'alias. Permet de trier la liste. */
    private Integer indexeAlias;

    /**
     * Interpr�te de la personne physique. Il n'existe qu'un seul interpr�te par personne. Une collection est utilis�e
     * pour faciliter le mapping.
     */
    private Set<Interprete> interpretes = new HashSet<>();

    /**
     * enregistrement dans le planning de detention, Il n'existe qu'un seul enregistrement par personne. Une collection
     * est utilis�e pour faciliter le mapping.
     */
    private Set<PlanningDetention> planningDetentions = new HashSet<>();

    /**
     * Adresse email de la personne physiqye.
     */
    private String email;

    /**
     * M�thode utilis�e par hibernate.
     *
     * @return Returns the interpretes.
     */
    public Set<Interprete> getInterpretes() {
        return interpretes;
    }

    /**
     * M�thode utilis�e par hibernate.
     *
     * @param interpretes
     *            The interpretes to set.
     */
    public void setInterpretes(Set<Interprete> interpretes) {
        this.interpretes = interpretes;
    }

    /**
     * Retourne le seul interprete possible de la personne.
     *
     * @return le seul interprete possible de la personne.
     */
    public Interprete getInterprete() {
        return interpretes.isEmpty() ? null : interpretes.iterator().next();
    }

    /**
     * Positionne l'interpr�te de la personne.
     *
     * @param interprete
     *            l'interpr�te de la personne.
     */
    public void setInterprete(Interprete interprete) {
        interpretes.clear();
        interpretes.add(interprete);
    }

    public Set<AdressePersonnePhysique> getAdresses() {
        return adresses;
    }

    public void setAdresses(Set<AdressePersonnePhysique> adresses) {
        this.adresses = adresses;
    }

    public Civilite getCivilite() {
        return civilite;
    }

    public void setCivilite(Civilite civilite) {
        this.civilite = civilite;
    }

    public Commune getCommuneNaissanceFrancaise() {
        return communeNaissanceFrancaise;
    }

    public void setCommuneNaissanceFrancaise(Commune communeNaissance) {
        this.communeNaissanceFrancaise = communeNaissance;
    }

    public String getCommuneNaissanceEtrangere() {
        return communeNaissanceEtrangere;
    }

    public void setCommuneNaissanceEtrangere(String communeNaissanceEtrangere) {
        this.communeNaissanceEtrangere = communeNaissanceEtrangere;
    }

    public ComplementAuteurOuMineurSignale getComplementAuteurOuMineurSignale() {
        return complementAuteurOuMineurSignale;
    }

    public void setComplementAuteurOuMineurSignale(ComplementAuteurOuMineurSignale complementAuteurOuMineurSignale) {
        this.complementAuteurOuMineurSignale = complementAuteurOuMineurSignale;
    }

    public DateIncomplete getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(DateIncomplete dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getDialecte() {
        return dialecte;
    }

    public void setDialecte(String dialecte) {
        this.dialecte = dialecte;
    }

    public String getDureeItt() {
        return dureeItt;
    }

    public void setDureeItt(String dureeItt) {
        this.dureeItt = dureeItt;
    }

    public Set<ErreurMaterielle> getErreursMaterielles() {
        return erreursMaterielles;
    }

    public void setErreursMaterielles(Set<ErreurMaterielle> erreursMaterielles) {
        this.erreursMaterielles = erreursMaterielles;
    }

    public String getFonctionElective() {
        return fonctionElective;
    }

    public void setFonctionElective(String fonctionElective) {
        this.fonctionElective = fonctionElective;
    }

    public Boolean getIndicateurDeces() {
        return indicateurDeces;
    }

    public void setIndicateurDeces(Boolean indicateurDeces) {
        this.indicateurDeces = indicateurDeces;
    }

    public LangueParlee getLangueParlee() {
        return langueParlee;
    }

    public void setLangueParlee(LangueParlee langueParlee) {
        this.langueParlee = langueParlee;
    }

    public Nationalite getNationalite1() {
        return nationalite1;
    }

    public void setNationalite1(Nationalite nationalite1) {
        this.nationalite1 = nationalite1;
    }

    public Nationalite getNationalite2() {
        return nationalite2;
    }

    public void setNationalite2(Nationalite nationalite2) {
        this.nationalite2 = nationalite2;
    }

    /**
     * Retourne Nom de naissance ou nom de famille ou nom de jeune-fille. Remarque : toute personne pour laquelle l�attribut � X se disant �
     * vaut vrai, doit �tre affich�e avec la mention � X se disant � pr�c�dent son nom. (PERI040) Donc utiliser de pr�f�rence la m�thode
     * getLibelle()
     *
     * @return le nom de naissance ou nom de famille ou nom de jeune-fille.
     */
    public String getNomNaissance() {
        return nomNaissance;
    }

    public void setNomNaissance(String nomNaissance) {
        this.nomNaissance = nomNaissance;
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
     * Retourne le nom d�usage, nom qui ne r�sulte pas de la filiation, n�est pas mentionn� � l��tat civil, mais peut figurer sur des
     * documents administratifs (carte nationale d�identit�, passeport, etc.). Remarque : Le nom � afficher est toujours le nom de
     * naissance. (PERI040) Donc utiliser de pr�f�rence la m�thode getLibelle()
     *
     * @return le nom d'usage
     */
    public String getNomUsage() {
        return nomUsage;
    }

    public void setNomUsage(String nomUsage) {
        this.nomUsage = nomUsage;
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

    public Pays getPaysNaissance() {
        return paysNaissance;
    }

    public void setPaysNaissance(Pays paysNaissance) {
        this.paysNaissance = paysNaissance;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public SituationFamille getSituationFamille() {
        return situationFamille;
    }

    public void setSituationFamille(SituationFamille situationFamille) {
        this.situationFamille = situationFamille;
    }

    public Float getTauxInvalidite() {
        return tauxInvalidite;
    }

    public void setTauxInvalidite(Float tauxInvalidite) {
        this.tauxInvalidite = tauxInvalidite;
    }

    public AgeMomentFaitsAuteur getAgeMomentFaitsAuteur() {
        return ageMomentFaitsAuteur;
    }

    public void setAgeMomentFaitsAuteur(AgeMomentFaitsAuteur ageMomentFaits) {
        this.ageMomentFaitsAuteur = ageMomentFaits;
    }

    public AgeMomentFaitsVictime getAgeMomentFaitsVictime() {
        return ageMomentFaitsVictime;
    }

    public void setAgeMomentFaitsVictime(AgeMomentFaitsVictime ageMomentFaitsVictime) {
        this.ageMomentFaitsVictime = ageMomentFaitsVictime;
    }

    /**
     * @return retourne planningDetentions.
     */
    public Set<PlanningDetention> getPlanningDetentions() {
        return planningDetentions;
    }

    /**
     * @param planningDetentions
     *            affecte planningDetentions
     */
    public void setPlanningDetentions(Set<PlanningDetention> planningDetentions) {
        this.planningDetentions = planningDetentions;
    }

    /**
     * retourne le planning de detention associ� � cette personne physique
     *
     * @return null si la personne n'est pas detenue
     */
    public PlanningDetention getPlanningDetention() {
        if (getPlanningDetentions().isEmpty()) {
            return null;
        }

        return getPlanningDetentions().iterator().next();
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * mise en detention de cette personne
     *
     * @param service
     * @param dateFinDetention
     *            date
     */
    @RegleDeGestion(RG.PACM032)
    public void assignPlanningDetention(Service service, Date dateFinDetention) {

        PlanningDetention planningDetention = null;

        if (this.getPlanningDetentions().isEmpty()) {
            planningDetention = new PlanningDetention();
            this.getPlanningDetentions().add(planningDetention);
            planningDetention.setId(this.getId());
        } else {
            planningDetention = this.getPlanningDetentions().iterator().next();
        }

        planningDetention.setPersonne(this);
        planningDetention.setDateFinDetention(dateFinDetention);
        planningDetention.setService(service);
    }

    /**
     * mise en libert� de cette personne
     */
    @RegleDeGestion(RG.PACM035)
    public void miseEnLiberte() {
        this.getPlanningDetentions().clear();
    }

    /**
     * Retourne le sexe de la personne.
     *
     * @return 'M' ou 'F', selon le sexe de la personne.
     */
    @RegleDeGestion(RG.PERM051)
    public String getSexe() {
        return civilite.getSexe();
    }

    @Override
    public boolean isGenreFeminin() {
        return (Sexe.FEMININ.equalsIgnoreCase(this.getSexe()));
    }

    /**
     * Indique si une personne est d�c�d�e. Une personne est d�c�d�e si elle a - l'indicateur d�c�s � Oui OU - la date
     * de d�c�s renseign�e
     *
     * @return Vrai si une personne est d�c�d�e, faux sinon.
     */
    public boolean isDecedee() {
        return indicateurDeces.booleanValue()
                || (complementAuteurOuMineurSignale != null && complementAuteurOuMineurSignale.getDateDeces() != null);
    }

    @Override
    @RegleDeGestion(RG.PERI040)
    public String calculeLibelle() {
        StringBuilder result = new StringBuilder();
        if (isDecedee()) {
            result.append(libelleDCD).append(blank);
        }
        if (complementAuteurOuMineurSignale != null && complementAuteurOuMineurSignale.getSeDisantX()) {
            result.append(libelleXseDisant).append(blank);
        }
        if (getRolePersonne().getRolePersonneEnum() == RolePersonneEnum.AUTEUR || StringUtils.isEmpty(nomUsage)) {
            result.append(nomNaissance);
        } else {
            result.append(nomUsage);
        }
        if (prenom != null) {
            result.append(blank).append(prenom);
        }

        return result.toString();
    }

    /**
     * Retourne l'adresse la plus r�cente, selon la date de d�claration.
     *
     * @return l'adresse la plus r�cente
     */
    public AdressePersonnePhysique getAdressePlusRecente() {
        AdressePersonnePhysique adressePlusRecente = null;
        for (AdressePersonnePhysique adresse : adresses) {
            if (adressePlusRecente == null
                    || adressePlusRecente.getDateDeclaration().before(adresse.getDateDeclaration())) {
                adressePlusRecente = adresse;
            }

            // Si deux adresses ont la m�me date de d�claration on prend celle
            // cr�� en dernier
            else if (adressePlusRecente.getDateDeclaration().compareTo(adresse.getDateDeclaration()) == 0
                    && (adressePlusRecente.getId() < adresse.getId())) {
                adressePlusRecente = adresse;
            }
        }
        return adressePlusRecente;
    }

    /**
     * Retourne l'adresse d�clar�e la plus r�cente, selon la date de d�claration.
     *
     * @return l'adresse d�clar�e la plus r�cente
     */
    @RegleDeGestion(RG.PERM015)
    public AdressePersonnePhysique getAdressePlusRecenteDeclaree() {
        AdressePersonnePhysique adressePlusRecenteDeclaree = null;
        for (AdressePersonnePhysique adresse : adresses) {
            if (adresse.isAdresseDeclaree()
                    && (adressePlusRecenteDeclaree == null || adressePlusRecenteDeclaree.getDateDeclaration().before(
                            adresse.getDateDeclaration()))) {
                adressePlusRecenteDeclaree = adresse;
            }
        }
        return adressePlusRecenteDeclaree;
    }

    /**
     * Retourne l'adresse ant�rieure la plus r�cente apr�s l'adresse d�clar�e la plus r�cente, selon la date de
     * d�claration.
     *
     * @return l'adresse d�clar�e la plus r�cente
     */
    public AdressePersonnePhysique getAdresseAnterieure() {
        AdressePersonnePhysique adresseAnterieure = null;
        Set<AdressePersonnePhysique> adressesAnt = new TreeSet<>(new Comparator<AdressePersonnePhysique>() {
            public int compare(AdressePersonnePhysique adr1, AdressePersonnePhysique adr2) {
                int comparaison = adr1.getDateDeclaration().compareTo(adr2.getDateDeclaration());
                if (comparaison == 0) {
                    comparaison = adr1.getId().compareTo(adr2.getId());
                }
                return -comparaison;
            }
        });

        adressesAnt.addAll(adresses);
        AdressePersonnePhysique adressePlusRecenteDeclaree = getAdressePlusRecenteDeclaree();
        if (adressePlusRecenteDeclaree == null) {
            adressePlusRecenteDeclaree = getAdressePlusRecente();
        }
        //Sauf dans le cas o� la Personne est Sans Domicile Fixe/Connu, on enl�ve l'adresse d�clar�e la plus r�cente de la liste.
        if (adressePlusRecenteDeclaree != null) {
            if (complementAuteurOuMineurSignale != null &&
                    (complementAuteurOuMineurSignale.getSansDomicile() == null ||
                            StringUtils.isEmpty(complementAuteurOuMineurSignale.getSansDomicile().getCode()))) {
                adressesAnt.remove(adressePlusRecenteDeclaree);
                adresseAnterieure = adressesAnt.isEmpty() ? adresseAnterieure : adressesAnt.iterator().next(); // Mantis 0189571 - correctif : recupere l'adresse ant�rieure la plus r�cente
            }
        }
        // Mantis 0189571 - correctif : recupere l'adresse ant�rieure la plus r�cente
        if (!adressesAnt.isEmpty() && adresseAnterieure == null) {
            adresseAnterieure = adressesAnt.stream().skip(1).findFirst().orElse(adresseAnterieure);
        }
        return adresseAnterieure;
    }

    /**
     * Parcours et retourne les adresses de la personne physique Si Auteur alors renvoit 3 adresses sinon 2.
     *
     * @return les adresses.
     */
    @RegleDeGestion({ RG.PERI024, RG.PERI025 })
    public Collection<AdressePersonnePhysique> getListeLimiteeAdresses() {
        int compteur = 0;
        Collection<AdressePersonnePhysique> listeAdresses = new LinkedHashSet<>();
        for (AdressePersonnePhysique adresse : adresses) {
            // R�cup�ration au maximum de 3 adresses pour les personnes
            // physiques Auteurs
            if (adresse.getPersonne().getRolePersonne().equals(RolePersonne.AUTEUR)) {

                if (compteur < 3) {
                    listeAdresses.add(adresse);
                    compteur++;
                }
            }
            // R�cup�ration au maximum de 2 adresses pour les personnes
            // physiques non Auteurs
            else {
                if (compteur < 2) {
                    listeAdresses.add(adresse);
                    compteur++;
                }
            }
        }
        return listeAdresses;
    }

    /**
     * @return Retourne quartierDomicilePhysique
     */
    public String getQuartierDomicilePhysique() {
        return quartierDomicilePhysique;
    }

    /**
     * @param quartierDomicilePhysique
     *            affecte quartierDomicilePhysique
     */
    public void setQuartierDomicilePhysique(String quartierDomicilePhysique) {
        this.quartierDomicilePhysique = quartierDomicilePhysique;
    }

    /**
     * @return Retourne quartierResidencePhysique
     */
    public String getQuartierResidencePhysique() {
        return quartierResidencePhysique;
    }

    /**
     * @param quartierResidencePhysique
     *            affecte quartierResidencePhysique
     */
    public void setQuartierResidencePhysique(String quartierResidencePhysique) {
        this.quartierResidencePhysique = quartierResidencePhysique;
    }

    /**
     * @return Retourne raisonSocialEmployeur
     */
    public String getRaisonSocialEmployeur() {
        return raisonSocialEmployeur;
    }

    /**
     * @param raisonSocialEmployeur
     *            affecte raisonSocialEmployeur
     */
    public void setRaisonSocialEmployeur(String raisonSocialEmployeur) {
        this.raisonSocialEmployeur = raisonSocialEmployeur;
    }

    /**
     * @return Retourne telephoneDomicile
     */
    public String getTelephoneDomicilePhysique() {
        return telephoneDomicilePhysique;
    }

    /**
     * @param telephoneDomicilePhysique
     *            affecte telephoneDomicile
     */
    public void setTelephoneDomicilePhysique(String telephoneDomicilePhysique) {
        this.telephoneDomicilePhysique = telephoneDomicilePhysique;
    }

    /**
     * @return Retourne telephoneResidencePhysique
     */
    public String getTelephoneResidencePhysique() {
        return telephoneResidencePhysique;
    }

    /**
     * @param telephoneResidencePhysique
     *            affecte telephoneResidence
     */
    public void setTelephoneResidencePhysique(String telephoneResidencePhysique) {
        this.telephoneResidencePhysique = telephoneResidencePhysique;
    }

    /**
     * @return Retourne telephoneTravail
     */
    public String getTelephoneTravail() {
        return telephoneTravail;
    }

    /**
     * @param telephoneTravail
     *            affecte telephoneTravail
     */
    public void setTelephoneTravail(String telephoneTravail) {
        this.telephoneTravail = telephoneTravail;
    }

    @Override
    public CategoriePenale getCategoriePenale() {
        if (this.complementAuteurOuMineurSignale == null)
            return null;

        if (this.complementAuteurOuMineurSignale.getSituationPenale() == null)
            return null;

        return this.complementAuteurOuMineurSignale.getSituationPenale().getCategoriePenale();
    }

    @Override
    public Adresse getAdresseResidence() {
        return getAdressePlusRecente();
    }

    /**
     * Sert pour le Rang de saisie de l'alias. Permet de trier la liste.
     *
     * @return Retourne indexeAlias
     */
    public Integer getIndexeAlias() {
        return indexeAlias;
    }

    /**
     * Sert pour le Rang de saisie de l'alias. Permet de trier la liste.
     *
     * @param indexeAlias
     *            affecte indexeAlias
     */
    public void setIndexeAlias(Integer indexeAlias) {
        this.indexeAlias = indexeAlias;
    }

    @Override
    public String getLibellePersonnePourNoeudSynthese() {
        StringBuffer libellePers = new StringBuffer();
        // -- PERI003
        if (prenom != null) {
            libellePers.append(prenom).append(blank);
        }
        if (RolePersonneEnum.AUTEUR.getCode().equals(getRolePersonne().getCode()) || StringUtils.isEmpty(nomUsage)) {
            libellePers.append(nomNaissance);
        } else {
            libellePers.append(nomUsage);
        }
        return libellePers.toString();
    }

    public void setDateMajorite(Date dateMajorite) {
        this.dateMajorite = dateMajorite;
    }

    public Date getDateMajorite() {
        return dateMajorite;
    }

    /**
     * @return the antecedentJudiciaireManuel
     */
    public final boolean isAntecedentJudiciaireManuel() {
        return antecedentJudiciaireManuel;
    }

    /**
     * @param antecedentJudiciaireManuel
     *            the antecedentJudiciaireManuel to set
     */
    public final void setAntecedentJudiciaireManuel(boolean antecedentJudiciaireManuel) {
        this.antecedentJudiciaireManuel = antecedentJudiciaireManuel;
    }

    public String getLibelleCommuneNaissanceAncienOrNull() {
        if (communeNaissanceFrancaise == null) {
            return null;
        }

        return communeNaissanceFrancaise.getLibelleCommuneByDateOrNull(dateNaissance);
    }

}
