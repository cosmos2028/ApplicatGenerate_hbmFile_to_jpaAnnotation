package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.administration.acteur.interne.model.ActeurInterne;
import fr.gouv.justice.cassiopee.administration.parametrage.model.FamilleEvenementTypeCassiopee;
import fr.gouv.justice.cassiopee.administration.parametrage.service.enumeration.FamilleEvenementTypeCassiopeeEnum;
import fr.gouv.justice.cassiopee.administration.structure.model.Service;
import fr.gouv.justice.cassiopee.common.identifier.Source;
import fr.gouv.justice.cassiopee.common.util.DateHeureUtils;
import fr.gouv.justice.cassiopee.commons.metier.model.Adresse;
import fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire;
import fr.gouv.justice.cassiopee.invariant.edition.model.DestinataireEdition;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Emetteur;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.exterieur.model.DispositifExterieur;
import fr.gouv.justice.cassiopee.invariant.infraction.model.LienPersonneInfraction;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.PeineOuMesure;
import fr.gouv.justice.cassiopee.invariant.personne.finder.PersonneFinderJdbcDao;
import fr.gouv.justice.cassiopee.invariant.personne.finder.PersonneReaderJdbcDao;
import fr.gouv.justice.cassiopee.invariant.personne.service.EtatPersonneAffaireEnum;
import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.EtatAuteurEnum;
import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.IndicateurPersonneEnum;
import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.RolePersonneEnum;
import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.TypePersonneEnum;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.DetailDommageInteret;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.PersonneEvenement;
import fr.gouv.justice.cassiopee.processus.ech.model.DoublonPersonne;
import fr.gouv.justice.cassiopee.processus.ech.model.ReferentielPV;
import fr.gouv.justice.cassiopee.referentiel.codification.model.CategoriePenale;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.CorrespondantTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.DateCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.EvenementTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.HeureCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.ServiceCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;
import fr.gouv.justice.cassiopee.service.audience.model.Audience;
import fr.gouv.justice.cassiopee.service.user.UserContextProvider;

/**
 * Classe décrivant une personne. Cette personne est une personne physique, une personne morale ou une personne élément
 * de structure. Elle représente la personne en tant qu'entité, et la personne à l'intérieure de l'affaire.
 */
@SuppressWarnings("nls")
public abstract class Personne implements Serializable, Emetteur<Long>, DestinataireEdition<Long> {

    private static final long serialVersionUID = 8907059588022703023L;
private String nomNaissance;
    /** Identifiant de la personne */
    private Long id;

    /** Affaire à laquelle est reliée la personne */
    private Affaire affaire;

    /** Ordre de saisie de la personne dans l'affaire */
    private Integer rang;

    /**
     * Etat de la personne de l'affaire. Permet d'enregistrer la suppression logique.
     */
    private EtatPersonneAffaire etatLogique;

    /** Etat de l'auteur courant, ainsi que tous les états historiques */
    private Set<EtatAuteur> etatsAuteur = new HashSet<>();

    /**
     * Rôle de la personne dans l'affaire : auteur, victime, administration, témoin, jeune, demandeur, défendeur...
     */
    private RolePersonne rolePersonne;

    /**
     * Attribut valable uniquement pour les personnes de rôle victime, administration ou lié. Etat de la personne dans
     * l'affaire : partie civile, représentant légal, civilement responsable ...
     */
    private EtatNonAuteur etatNonAuteur;

    /**
     * Attribut valable uniquement pour les personnes de rôle victime, administration ou lié. Etat de la personne dans
     * l'affaire : partie civile, représentant légal, civilement responsable ... par anticipation
     */
    private EtatNonAuteur etatNonAuteurAnticipe;

    /**
     * Attribut désignant, pour un auteur, son rôle courant. Il s'agit d'une duplication, car cet attribut peut être
     * déduit de l'historique des états. Mais celà permet une simplification des requêtes de recherche en base des
     * personnes selon un état courant spécifique.
     */
    private RefEtatAuteur etatCourantAuteur;

    /**
     * Attribut désignant, pour un auteur, son rôle courant. Il s'agit d'une duplication, car cet attribut peut être
     * déduit de l'historique des états. Mais celà permet une simplification des requêtes de recherche en base des
     * personnes selon un état courant spécifique. par anticipation
     */
    private RefEtatAuteur etatAuteurAnticipe;

    /**
     * Antécédent relatif aux condamnations de la personne auteur des faits. Exemples pour une personne physique : se
     * dit condamné, jamais condamné, déjà condamné ; Exemples pour une personne morale : contrôle judiciaire, mise en
     * liquidation.
     */
    private AntecedentJudiciaire antecedentJudiciaire;

    /** Information liée au suivi de dossier, par un correspondant extérieur. */
    private String reference;

    /**
     * A vrai si l'identité de la personne a été modifiée suite un jugement sur requête en usurpation d'identité.
     */
    private boolean identiteRectifiee = false;

    /**
     * Attribut désignant si il est nécessaire de posséder un interprète pour la personne
     */
    private boolean interpreteNecessaire;

    /**
     * Signale si la personne concernée bénéficie ou non d’une aide juridictionnelle pour lui permettre d’être assistée. Exemple : 12584/99.
     */
    private String numeroAideJuridictionnelle;

    /**
     * Le bureau d'aide juridictionnelle (BAJ) décide si la personne bénéficie ou non d’une aide juridictionnelle pour lui permettre d’être
     * assistée financièrement. Remarque : concerne les auteurs et les victimes.
     */
    private ElementStructure bureauAideJuridictionnelle;

    /**
     * Date d'accord du bureau d'aide juridictionnelle (BAJ). Remarque : concerne les auteurs et les victimes.
     */
    private Date dateAideJuridictionnelle;

    /**
     * Taux d'aide juridictionnelle décidé par le bureau d'aide juridictionnelle (BAJ).
     */
    private Float tauxAideJuridictionnelle;

    /** Indique si la victime est disparue */
    private Boolean victimeIndicateurDisparu;

    /**
     * Vaut Vrai si la personne liée est présente. Vaut Faux sinon Donnée utilisée pour l'homologation de RPC. Cette
     * donnée n'est pas volatile : on ne détruit pas les données d'une décision de justice après saisie.
     */
    private Boolean victimePresent;

    /** version de la personne */
    private Long version;

    /**
     * Indique si une Alerte redex est affiche pour la personne
     */
    private Boolean redexAlerte;

    /**
     * Pour une personne de rôle premier, ensemble des personnes de rôle "liée" qui lui sont juridiquement liées. Pour
     * un jeune, ensemble des jeunes qui lui sont socialement liés.
     */
    private Set<LienPersonnes> personnesLieesOuJeunesLiees = new HashSet<>();

    /**
     * Pour une personne de rôle "liée", ensemble des personnes de rôle premier qui lui sont liées. Dans certains cas,
     * cet ensemble peut contenir des personnes de rôle "liée". Pour un jeune, ensemble des jeunes qui lui sont
     * socialement liés.
     */
    private Set<LienPersonnes> personnesRolesPremiersLiees = new HashSet<>();

    private TypeIntervenant typeIntervenant; // Que devient ce champ depuis

    // la mise à jour du modèle ?

    private Service infosSuppressionService;

    private Date infosSuppressionDateSuppr;

    private ActeurInterne infosSuppressionActeurInterne;

    private Set<AvocatPersonneAffaire> avocats = new HashSet<>();

    /**
     * Pour une victime, liste des faux titres de paiements reçus.
     */
    private Set<TitrePaiement> titresPaiementVictimeBeneficiaire = new HashSet<>();

    private Set<LienComptePersonne> liensComptePersonne = new HashSet<>();

    /** la liste des liens entre la personne et des infractions */
    private Set<LienPersonneInfraction> liensPersonneInfraction = new HashSet<>();

    /**
     * evenement emis par cette personne
     */
    private Set<Evenement> evenementEmis = new HashSet<>();

    /** Personnes-Evenements pointant sur cette personne */
    private Set<PersonneEvenement> personneEvenements = new HashSet<>();

    private Set<EtatRecoursPersonne> etatsRecours = new HashSet<>();

    /**
     * Attribut valable uniquement pour une personne liée.<br>
     * A vrai si le jugement est opposable à un intervenant.<br>
     * Cet attribut ne peut être à vrai que si la personne liée à un lien juridique "Intervenant". Il n'est pas calculé,
     * mais saisi au jugement.<br>
     * Si le jugement est opposable, l'intervenant peut faire opposition même s'il était absent lors du jugement (c'est
     * le cas par exemple pour un "assureur") ou non (par exemple pour une "association").
     */
    private Boolean jugementOpposableIntervenant;



    private Boolean arretOpposableIntervenant;

    /**
     * Attribut valable uniquement pour une personne liée.<br>
     * Vaut Vrai si la personne liée est présente. Vaut Faux sinon. S'applique seulement aux Représentants légaux.<br>
     * Donnée utilisée pour les PV de 1e comparution et de débat contradictoire.
     */
    private Boolean presenceRepresentantLegalPv1ereComparution;

    /**
     * Libellé de la personne, tel qu'il doit être affiché dans toute l'application.<br>
     * Attribut persisté pour améliorer les performances.
     */
    @RegleDeGestion(RG.PERI040)
    private String libellePersonne;

    /**
     * Les dispositifs extérieurs liés à cette personne.
     */
    private Set<DispositifExterieur> dispositifExterieurs = new HashSet<>();

    private String alertePersonne;

    // RG.RASI102
    private Boolean rasi102 = Boolean.FALSE;

    /**
     * Ajout d'un attribut pour spécifier que la personne a été importée par les EIA ou non
     */

    private String importeViaFlux;

    /**
     * Demande de provision article 475-1
     */
    private Double demandeDispoCivilProv475_1;

    /**
     * Description du bien
     */
    private String descriptionBien;

    /**
     * Localisation du bien
     */
    private String localisationBien;

    /**
     * Description des sources de revenus
     */
    private String descriptionSourceRevenu;

    /**
     * Description des localisations de revenus
     */
    private String localisationSourceRevenu;

    /**
     * Source de la donnée
     */
    private Source source;

    /**
     * Si le suivi de la plainte en ligne a été demandée
     */
    private boolean ppelDemandee;


    private Set<PeineOuMesure> personneSurPemReparation = new HashSet<>();

    /**
     * Liste représentant les details dommages et interet associé à une personne et un evenement
     */
    private Set<DetailDommageInteret> detailDi = new HashSet<>();

    private Set<DetailDommageInteret> lienDiPersonne = new HashSet<>();

    private ActeurInterne personneEnChargeActeurInterne;

    private ElementStructure personneEnChargeElementStructure;

    private ReferentielPV referentielPV;

    private boolean consentementPortail;
    
    private boolean consentementAvantJonction;

    private Date dateModificationConsentement;

    private String numCabinetAPPI;

    private String nomResponsableJAP;

    private String numDossierAPPI;

    private Set<IndicateurPersonneEnum> indicateurs = new HashSet<>();
    
    private Set<DoublonPersonne> doublons = new LinkedHashSet<>();
    
    private Personne personneJonction;

    /**
     * Téléphone portable
     */
    private String telephonePortable;

    public String getImporteViaFlux() {
        return importeViaFlux;
    }

    public void setImporteViaFlux(String importeViaFlux) {
        this.importeViaFlux = importeViaFlux;
    }

    public boolean isImporte() {
        return importeViaFlux != null;
    }

    /**
     * indique si la personne est de genre féminin
     *
     * @return vrai si la personne est de genre féminin
     */
    public abstract boolean isGenreFeminin();

    /**
     * Retourne le libellé de la personne, tel qu'il doit être affiché dans le noeud synthèse de l'arbre de navigation.
     */
    @RegleDeGestion(RG.PERI003)
    public abstract String getLibellePersonnePourNoeudSynthese();

    /**
     * Retourne le libellé de la personne, tel qu'il doit être affiché dans toute l'application.
     *
     * @return le libellé de la personne.
     */
    @RegleDeGestion(RG.PERI040)
    public String getLibellePersonne() {
        return libellePersonne;
    }

    /**
     * Positionne le libellé de la personne, tel qu'il doit être affiché dans toute l'application.
     *
     * @param libellePersonne
     *            le libellé de la personne
     */
    public void setLibellePersonne(String libellePersonne) {
        this.libellePersonne = libellePersonne;
    }

    public Service getInfosSuppressionService() {
        return infosSuppressionService;
    }

    public void setInfosSuppressionService(Service infosSuppressionService) {
        this.infosSuppressionService = infosSuppressionService;
    }

    public Affaire getAffaire() {
        return affaire;
    }

    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
    }

    public boolean isIdentiteRectifiee() {
        return identiteRectifiee;
    }

    public void setEtatAuteurAnticipe(RefEtatAuteur etatAuteurAnticipe) {
        this.etatAuteurAnticipe = etatAuteurAnticipe;
    }

    public RefEtatAuteur getEtatAuteurAnticipe() {
        return etatAuteurAnticipe;
    }

    public void setEtatNonAuteurAnticipe(EtatNonAuteur etatNonAuteurAnticipe) {
        this.etatNonAuteurAnticipe = etatNonAuteurAnticipe;
    }

    public EtatNonAuteur getEtatNonAuteurAnticipe() {
        return etatNonAuteurAnticipe;
    }

    public void setIdentiteRectifiee(boolean identiteRectifiee) {
        this.identiteRectifiee = identiteRectifiee;
    }

    public boolean isInterpreteNecessaire() {
        return interpreteNecessaire;
    }

    public void setInterpreteNecessaire(boolean interpreteNecessaire) {
        this.interpreteNecessaire = interpreteNecessaire;
    }

    public AntecedentJudiciaire getAntecedentJudiciaire() {
        return antecedentJudiciaire;
    }

    public void setAntecedentJudiciaire(AntecedentJudiciaire antecedentJudiciaire) {
        this.antecedentJudiciaire = antecedentJudiciaire;
    }

    public Set<AvocatPersonneAffaire> getAvocats() {
        return avocats;
    }

    public void setAvocats(Set<AvocatPersonneAffaire> avocats) {
        this.avocats = avocats;
    }

    public ElementStructure getBureauAideJuridictionnelle() {
        return bureauAideJuridictionnelle;
    }

    public void setBureauAideJuridictionnelle(ElementStructure bureauAideJuridictionnelle) {
        this.bureauAideJuridictionnelle = bureauAideJuridictionnelle;
    }

    public Date getDateAideJuridictionnelle() {
        return dateAideJuridictionnelle;
    }

    public void setDateAideJuridictionnelle(Date dateAideJuridictionnelle) {
        this.dateAideJuridictionnelle = dateAideJuridictionnelle;
    }

    public Set<EtatAuteur> getEtatsAuteur() {
        return etatsAuteur;
    }

    public void setEtatsAuteur(Set<EtatAuteur> etatAuteurs) {
        this.etatsAuteur = etatAuteurs;
    }

    /**
     * Retourne l'état de la personne de l'affaire : active ou supprimée.
     */
    public EtatPersonneAffaire getEtatLogique() {
        return etatLogique;
    }

    /**
     * Positionne l'état de la personne de l'affaire : active ou supprimée.
     */
    public void setEtatLogique(EtatPersonneAffaire etatPersonneAffaire) {
        this.etatLogique = etatPersonneAffaire;
    }

    /**
     * Retourne l'état de la personne de rôle victime, administration ou personne liée.<br>
     * Il peut être partie civile, représentant légal, civilement responsable ...
     *
     * @return l'état de la personne de rôle victime, administration ou personne liée.
     */
    public EtatNonAuteur getEtatNonAuteur() {
        return etatNonAuteur;
    }

    /**
     * Positionne l'état de la personne de rôle victime, administration ou personne liée.<br>
     * Il peut être partie civile, représentant légal, civilement responsable ...
     *
     * @param etatRole
     *            l'état de la personne de rôle victime, administration ou personne liée.
     */
    public void setEtatNonAuteur(EtatNonAuteur etatRole) {
        this.etatNonAuteur = etatRole;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInfosSuppressionDateSuppr() {
        return infosSuppressionDateSuppr;
    }

    public void setInfosSuppressionDateSuppr(Date infosSuppressionDateSuppr) {
        this.infosSuppressionDateSuppr = infosSuppressionDateSuppr;
    }

    /**
     * Retourne, pour une personne de rôle premier, l'ensemble des personnes de rôle "liée" qui lui sont juridiquement
     * liées.<br>
     * Pour un jeune, ensemble des jeunes qui lui sont socialement liés.<br>
     * Lien de gauche à droite.
     *
     * @return pour une personne de rôle premier, l'ensemble des personnes de rôle "liée" qui lui sont juridiquement
     *         liées.
     */
    public Set<LienPersonnes> getPersonnesLieesOuJeunesLiees() {
        return personnesLieesOuJeunesLiees;
    }

    /**
     * Retourne les liens des personnes liées, de la même manière que getPersonnesLieesOuJeunesLiees(), mais seules les
     * liens des personnes non supprimées sont retournées.
     *
     * @return les liens valides des personnes liées.
     */
    public Set<LienPersonnes> getPersonnesLieesOuJeunesLieesExistantes() {
        Set<LienPersonnes> result = new HashSet<>();
        for (LienPersonnes lienPers : personnesLieesOuJeunesLiees) {
            if (EtatPersonneAffaireEnum.ACTIVE.is(lienPers.getPersonneLieeOuJeune().getEtatLogique().getCode())) {
                result.add(lienPers);
            }
        }
        return result;
    }

    public void setPersonnesLieesOuJeunesLiees(Set<LienPersonnes> lienPersonnes) {
        this.personnesLieesOuJeunesLiees = lienPersonnes;
    }

    /**
     * Retourne, pour une personne de rôle "liée", l'ensemble des personnes de rôle premier qui lui sont liées.<br>
     * Dans certains cas, cet ensemble peut contenir des personnes de rôle "liée".<br>
     * Pour un jeune, ensemble des jeunes qui lui sont socialement liés.<br>
     * Lien de droite à gauche.
     *
     * @return pour une personne de rôle "liée", l'ensemble des personnes de rôle premier qui lui sont liées.
     */
    public Set<LienPersonnes> getPersonnesRolesPremiersLiees() {
        return personnesRolesPremiersLiees;
    }

    public void setPersonnesRolesPremiersLiees(Set<LienPersonnes> personnesRolesPremiersLiees) {
        this.personnesRolesPremiersLiees = personnesRolesPremiersLiees;
    }

    public String getNumeroAideJuridictionnelle() {
        return numeroAideJuridictionnelle;
    }

    public void setNumeroAideJuridictionnelle(String numeroAideJuridictionnelle) {
        this.numeroAideJuridictionnelle = numeroAideJuridictionnelle;
    }

    public Set<LienComptePersonne> getLiensComptePersonne() {
        return liensComptePersonne;
    }

    public void setLiensComptePersonne(Set<LienComptePersonne> personneAffComptes) {
        this.liensComptePersonne = personneAffComptes;
    }

    public Integer getRang() {
        return rang;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public RolePersonne getRolePersonne() {
        return rolePersonne;
    }

    public void setRolePersonne(RolePersonne rolePersonne) {
        this.rolePersonne = rolePersonne;
    }

    public Float getTauxAideJuridictionnelle() {
        return tauxAideJuridictionnelle;
    }

    public void setTauxAideJuridictionnelle(Float tauxAideJuridictionnelle) {
        this.tauxAideJuridictionnelle = tauxAideJuridictionnelle;
    }

    public java.util.Set<TitrePaiement> getTitresPaiementVictimeBeneficiaire() {
        return titresPaiementVictimeBeneficiaire;
    }

    public void setTitresPaiementVictimeBeneficiaire(java.util.Set<TitrePaiement> titrePaiements) {
        this.titresPaiementVictimeBeneficiaire = titrePaiements;
    }

    public TypeIntervenant getTypeIntervenant() {
        return typeIntervenant;
    }

    public void setTypeIntervenant(TypeIntervenant typeIntervenant) {
        this.typeIntervenant = typeIntervenant;
    }

    public Boolean getVictimeIndicateurDisparu() {
        return victimeIndicateurDisparu;
    }

    public void setVictimeIndicateurDisparu(Boolean victimeIndicateurDisparu) {
        this.victimeIndicateurDisparu = victimeIndicateurDisparu;
    }

    public Boolean getVictimePresent() {
        return victimePresent;
    }

    public void setVictimePresent(Boolean victimePresent) {
        this.victimePresent = victimePresent;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    /**
     * Retourne le jugement opposable pour une personne liée intervenant. Attribut valable uniquement pour une personne
     * liée.<br>
     * A vrai si le jugement est opposable à un intervenant.<br>
     * Cet attribut ne peut être à vrai que si la personne liée à un lien juridique "Intervenant". Il n'est pas calculé,
     * mais saisi au jugement.<br>
     * Si le jugement est opposable, l'intervenant peut faire opposition même s'il était absent lors du jugement (c'est
     * le cas par exemple pour un "assureur") ou non (par exemple pour une "association").
     *
     * @return le jugement opposable pour une personne liée intervenant.
     */
    public Boolean getJugementOpposableIntervenant() {
        return jugementOpposableIntervenant;
    }

    /**
     * Positionne le jugement opposable pour une personne liée intervenant. Attribut valable uniquement pour une
     * personne liée.<br>
     * A vrai si le jugement est opposable à un intervenant.<br>
     * Cet attribut ne peut être à vrai que si la personne liée à un lien juridique "Intervenant". Il n'est pas calculé,
     * mais saisi au jugement.<br>
     * Si le jugement est opposable, l'intervenant peut faire opposition même s'il était absent lors du jugement (c'est
     * le cas par exemple pour un "assureur") ou non (par exemple pour une "association").
     *
     * @param jugementOpposableIntervenant
     *            le jugement opposable pour une personne liée intervenant.
     */
    public void setJugementOpposableIntervenant(Boolean jugementOpposableIntervenant) {
        this.jugementOpposableIntervenant = jugementOpposableIntervenant;
    }

    /**
     * Retourne la présence de la personne liée représentant légal. Attribut valable uniquement pour une personne liée.<br>
     * Vaut Vrai si la personne liée est présente. Vaut Faux sinon. S'applique seulement aux Représentants légaux.<br>
     * Donnée utilisée pour les PV de 1e comparution et de débat contradictoire.
     *
     * @return la présence de la personne liée représentant légal.
     */
    public Boolean getPresenceRepresentantLegalPv1ereComparution() {
        return presenceRepresentantLegalPv1ereComparution;
    }

    /**
     * Positionne la présence de la personne liée représentant légal. Attribut valable uniquement pour une personne
     * liée.<br>
     * Vaut Vrai si la personne liée est présente. Vaut Faux sinon. S'applique seulement aux Représentants légaux.<br>
     * Donnée utilisée pour les PV de 1e comparution et de débat contradictoire.
     *
     * @param presenceRepresentantLegal
     *            la présence de la personne liée représentant légal.
     */
    public void setPresenceRepresentantLegalPv1ereComparution(Boolean presenceRepresentantLegal) {
        this.presenceRepresentantLegalPv1ereComparution = presenceRepresentantLegal;
    }

    /**
     * Valable pour une personne de rôle Administration uniquement. Détermine si l'administration est poursuivante ou partie jointe dans les
     * affaires de citation par partie poursuivante. Administration poursuivante : il n’y a que des infractions douanières sur l’affaire.
     * Administration partie jointe : Il y a d’autres infractions en plus des infractions douanières (délits de droit commun,..).
     *
     * @return Vrai si l'administration est poursuivante, Faux si elle est partie jointe.
     * @deprecated utiliser à la place PersonneFinderJdbcDao.isAdministrationPoursuivante()
     */
    @Deprecated
    public boolean isPoursuivante() {
        return false;
    }

    /**
     * Retourne vrai si la personne est administration poursuivante, faux sinon.
     *
     * @param source
     *            source de données
     * @param personneFinderJdbcDao
     *            implémentation du finder
     * @return vrai si la personne est administration poursuivante, faux sinon.
     */
    @RegleDeGestion(RG.PERM086)
    public boolean isAdministrationPoursuivante(Source source, PersonneFinderJdbcDao personneFinderJdbcDao) {
        return personneFinderJdbcDao.isAdministrationPoursuivante(source, getId());
    }

    /**
     * Retourne vrai si la personne est partie civile poursuivante, faux sinon.
     *
     * @param source
     *            source de données
     * @param personneReaderJdbcDao
     *            implémentation du finder
     * @return vrai si la personne est partie civile poursuivante, faux sinon.
     */
    @RegleDeGestion(RG.PERM089)
    public boolean isPartieCivilePoursuivante(Source source, PersonneReaderJdbcDao personneReaderJdbcDao) {
        return personneReaderJdbcDao.isPartieCivilePoursuivante(source, getId());
    }

    /**
     * Renvoie le type de personne
     *
     * @return le type de personne
     */
    public abstract TypePersonneEnum getTypePersonne();

    /**
     * Précise si la personne est majeure
     *
     * @return Vrai s'il s'agit d'une personne majeure, faux sinon.
     */
    public boolean isPersonneMajeure() {
        return getTypePersonne().equals(TypePersonneEnum.MAJEURE);
    }

    /**
     * Précise si la personne est mineure
     *
     * @return Vrai s'il s'agit d'une personne mineure, faux sinon.
     */
    public boolean isPersonneMineure() {
        return getTypePersonne().equals(TypePersonneEnum.MINEURE);
    }

    /**
     * Précise si la personne est indéterminée.
     *
     * @return Vrai s'il s'agit d'une personne indéterminée, faux sinon.
     */
    public boolean isPersonneIndeterminee() {
        return getTypePersonne().equals(TypePersonneEnum.INDETERMINEE);
    }

    /**
     * Précise si la personne est de rôle personne Liée.
     *
     * @return Vrai si la personne est de rôle personne Liée, faux sinon.
     */
    public boolean isPersonneLiee() {
        return getRolePersonne().getCode().equals(RolePersonneEnum.PERSONNE_LIEE.getCode());
    }

    /**
     * Précise si la personne est de rôle Jeune.
     *
     * @return Vrai si la personne est de rôle Jeune, faux sinon.
     */
    public boolean isPersonneJeune() {
        return getRolePersonne().getCode().equals(RolePersonneEnum.JEUNE.getCode());
    }

    /**
     * Précise si la personne est morale
     *
     * @return Vrai s'il s'agit d'une personne morale, faux sinon.
     */
    public boolean isPersonneMorale() {
        return getTypePersonne().equals(TypePersonneEnum.MORALE);
    }

    /**
     * Précise si la personne est un élément de structure
     *
     * @return Vrai si la personne représente un élément de structure, faux sinon.
     */
    public boolean isPersonneElementStructure() {
        return getTypePersonne().equals(TypePersonneEnum.ELEMENT_STRUCTURE);
    }

    /**
     * Précise si la personne est physique
     *
     * @return Vrai s'il s'agit d'une personne majeure, mineure ou indéterminée, faux sinon.
     */
    public boolean isPersonnePhysique() {
        return isPersonneMajeure() || isPersonneMineure() || isPersonneIndeterminee();
    }

    /**
     * Précise si la personne bénéficie de l'aide juridictionnelle. Implémente la règle PERM045
     *
     * @return True si la personne bénéficie d'une aide juridictionnelle, false sinon.
     */
    @RegleDeGestion(RG.PERM045)
    public Boolean getBeneficiareAideJuridictionnelle() {
        if(!getIndicateurs().contains(IndicateurPersonneEnum.AIDE_JURIDICTIONNELLE_PROVISOIRE)){
            boolean isTauxJuridictionnelle = getTauxAideJuridictionnelle() != null && Float.compare(getTauxAideJuridictionnelle().floatValue(), 0f) > 0;
        return Boolean.valueOf(getBureauAideJuridictionnelle() != null 
                || getDateAideJuridictionnelle() != null
                || (isTauxJuridictionnelle)
                || getNumeroAideJuridictionnelle() != null);
        }else {
            return false;
        }
    }

    /**
     * Met à jour l'état de l'auteur courant, et historise le précédent état.
     *
     * @param userContext
     *            context utilisateur
     * @param newEtatAuteur
     *            le nouvel état de l'auteur.
     */
    @RegleDeGestion({ RG.PERM083, RG.RASI102 })
    public void updateEtatAuteur(RefEtatAuteur newEtatAuteur) {

        if (!newEtatAuteur.equals(getEtatCourantAuteur())) {
            EtatAuteur etat = new EtatAuteur();
            etat.setRefEtatAuteur(newEtatAuteur);
            setEtatCourantAuteur(newEtatAuteur);
            etat.setPersonne(this);
            try {
            	etat.setDateFraicheur(UserContextProvider.getUserContext().getDateDuJour());
            } catch (Exception e) {
            	etat.setDateFraicheur(new Date());
			}
            etatsAuteur.add(etat);

            // RG.RASI102
            boolean etatJugeOuJugeOpposantOuPrevenuOppasant = false;
            boolean etatPrevenu = false;
            boolean evtFamilleJ27 = false;
            boolean evt70009 = false;
            boolean evt70261 = false;

            if (EtatAuteurEnum.JUGE.getCode().equals(newEtatAuteur.getCode())
                    || EtatAuteurEnum.JUGE_OPPOSANT.getCode().equals(newEtatAuteur.getCode())
                    || EtatAuteurEnum.PREVENU_OPPOSANT.getCode().equals(newEtatAuteur.getCode())) {
                etatJugeOuJugeOpposantOuPrevenuOppasant = true;
            } else if (EtatAuteurEnum.PREVENU.getCode().equals(newEtatAuteur.getCode())) {
                etatPrevenu = true;
            }

            for (PersonneEvenement pev : this.getPersonneEvenements()) {
                if (pev.getEvenement() != null
                        && pev.getEvenement().getEvenementType() != null) {
                    if (EvenementTypeEnum.MANDAT_AMENER.getCode().equals(
                            pev.getEvenement().getEvenementType().getCode())) {
                        evt70009 = true;
                    }
                    if (EvenementTypeEnum.EVENEMENT_TYPE_RENVOI.getCode()
                            .equals(pev.getEvenement().getEvenementType()
                                    .getCode())) {
                        evt70261 = true;
                    }
                    for (FamilleEvenementTypeCassiopee fetc : pev
                            .getEvenement().getEvenementType()
                            .getFamilleEvenementTypeCassiopees()) {
                        if (FamilleEvenementTypeCassiopeeEnum.EVENEMENTS_ALIMENTANT_A_RECHERCHE_DISPO
                                .getCode().equals(fetc.getCode())) {
                            evtFamilleJ27 = true;
                        }
                    }
                }
            }

            if ((etatJugeOuJugeOpposantOuPrevenuOppasant || (etatPrevenu && evt70009 && evt70261)) && evtFamilleJ27) {
                this.setRasi102(Boolean.TRUE);
            } else {
                this.setRasi102(Boolean.FALSE);
            }
        }
    }

    /**
     * Retourne l'état courant de l'auteur, sous forme d'objet de codification.<br>
     * Cet attribut est persisté, donc cette méthode est plus performante que getEtatAuteurCourant().
     *
     * @return l'état courant de l'auteur
     */
    public RefEtatAuteur getEtatCourantAuteur() {
        return etatCourantAuteur;
    }

    /**
     * Positionne l'état courant de l'auteur. Cette méthode est private, car la mise à jour de l'état doit se faire par
     * la méthode updateEtatAuteur(). Ce comportement est modifié puisque la DEI 206 permet à l'utilisateur de
     * renseigner l'état de l'auteur via une liste déroulante
     *
     * @param etatCourantAuteur
     *            l'état courant de l'auteur
     */
    public void setEtatCourantAuteur(RefEtatAuteur etatCourantAuteur) {
        this.etatCourantAuteur = etatCourantAuteur;
    }

    /**
     * Retourne l'état courant de l'auteur, c'est à dire le plus récent.<br>
     * Cet attribut est calculé à chaque lecture, donc moins performant que getEtatCourantAuteur().<br>
     * Le calcul se base sur l'identifiant de l'état : en effet, il est généré à partir d'une séquence Oracle, donc
     * incrémenté à chaque nouvelle valeur. Pour calculer en se basant sur la date de fraîcheur, il faut changer le
     * mapping pour utiliser un TimeStamp à la place d'une Date, car la date ne prend pas en compte les heures et
     * minutes, ce qui provoque un résultat aléatoire lorsque l'état est modifié plusieurs fois dans la même journée.
     * Modif Pierric : la date prend bien en compte l'heure, il vaut donc mieux utiliser la date que l'id. L'id le plus
     * grand n'est pas forcément le plus récent bizarrement.
     *
     * @return l'état courant de l'auteur
     */
    public EtatAuteur getEtatAuteurCourant() {
        EtatAuteur result = null;
        Date timePlusGrand = null;
        for (EtatAuteur etatAuteur : etatsAuteur) {
            if (timePlusGrand == null || timePlusGrand.before(etatAuteur.getDateFraicheur())) {
                result = etatAuteur;
                timePlusGrand = etatAuteur.getDateFraicheur();
            }
        }
        return result;
    }

    /**
     * Retourne le libellé de l'état du rôle de la personne. Ex : Prévenu pour un auteur, Partie civile pour une
     * victime.
     *
     * @return le libellé de l'état du rôle de la personne.
     */
    public String getLibelleEtatRolePersonne() {
        switch (rolePersonne.getRolePersonneEnum()) {
            case AUTEUR:
                if (etatCourantAuteur != null) {
                    return etatCourantAuteur.getLibelle();
                }
                break;
            default:
                if (etatNonAuteur != null) {
                    return etatNonAuteur.getLibelle();
                }
                break;
        }
        return "";
    }

    public String getLibelleEtatOuRolePersonne() {
        switch (rolePersonne.getRolePersonneEnum()) {
            case AUTEUR:
                if (etatCourantAuteur != null) {
                    return etatCourantAuteur.getLibelle();
                }
                break;
            default:
                if (etatNonAuteur != null) {
                    return etatNonAuteur.getLibelle();
                } else if (rolePersonne != null) {
                    return rolePersonne.getLibelle();
                }
                break;
        }
        return "";
    }

    /**
     * Retourne le code de l'état du rôle de la personne. Ex : code Prévenu pour un auteur, code Partie civile pour une
     * victime.
     *
     * @return le code de l'état du rôle de la personne.
     */
    public String getCodeEtatRolePersonne() {
        switch (rolePersonne.getRolePersonneEnum()) {
            case AUTEUR:
                if (etatCourantAuteur != null) {
                    return etatCourantAuteur.getCode();
                }
                break;
            default:
                if (etatNonAuteur != null) {
                    return etatNonAuteur.getCode();
                }
                break;
        }
        return "";
    }

    @Override
    public Long getCorrespondantId() {
        return this.id;
    }

    @Override
    public CorrespondantTypeEnum getCorrespondantType() {
        return CorrespondantTypeEnum.Personne;
    }

    @Override
    public String getCorrespondantLibelle() {
        return getLibelle();
    }

    /**
     * Retourne le libellé de la personne, tel qu'il est en base, c'est à dire selon la PERI040.
     *
     * @return le libellé de la personne
     */
    @Override
    @RegleDeGestion(RG.PERI040)
    public String getLibelle() {
        return libellePersonne;
    }

    /**
     * Calcule le libellé de la personne : <br/>
     * - nom + prénom pour une personne physique <br/>
     * - raison sociale pour une personne morale <br/>
     * - libellé pour un élément de structure
     *
     * @return le libellé de la personne
     */
    @RegleDeGestion(RG.PERI040)
    abstract public String calculeLibelle();

    /**
     * Retourne la liste des liens entre la personne et des infractions
     *
     * @return la liste des liens entre la personne et des infractions
     */
    public Set<LienPersonneInfraction> getLiensPersonneInfraction() {
        return liensPersonneInfraction;
    }

    /**
     * Renseigne la liste des liens entre la personne et des infractions
     *
     * @param liensPersonneInfraction
     *            la liste des liens entre la personne et des infractions
     */
    public void setLiensPersonneInfraction(Set<LienPersonneInfraction> liensPersonneInfraction) {
        this.liensPersonneInfraction = liensPersonneInfraction;
    }

    /**
     * @return Returns the personneEvenements.
     */
    public Set<PersonneEvenement> getPersonneEvenements() {
        return personneEvenements;
    }

    /**
     * @param personneEvenements
     *            The personneEvenements to set.
     */
    public void setPersonneEvenements(Set<PersonneEvenement> personneEvenements) {
        this.personneEvenements = personneEvenements;
    }

    /**
     * Retourne la version de la personne
     *
     * @return version de la personne
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Indique la version de la personne
     *
     * @param version
     *            de la personne
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * evenement emis par cette personne
     */
    @Override
    public Set<Evenement> getEvenementEmis() {
        return evenementEmis;
    }

    /**
     * evenement emis par cette personne
     */
    public void setEvenementEmis(Set<Evenement> evenementEmis) {
        this.evenementEmis = evenementEmis;
    }

    /**
     * Renvoie les DispositifExterieur liés à cette Personne.
     *
     * @return Returns the dispositifExterieurs.
     */
    public Set<DispositifExterieur> getDispositifExterieurs() {
        return dispositifExterieurs;
    }

    public void setDispositifExterieurs(Set<DispositifExterieur> dispositifExterieurs) {
        this.dispositifExterieurs = dispositifExterieurs;
    }

    /**
     * retourne la catégorie pénale
     *
     * @return la catégorie pénale ou null si pas de catégorie pénale
     */
    public abstract CategoriePenale getCategoriePenale();

    /**
     * retourne l'adresse dite de résidence
     *
     * @return commune de résidence
     */
    public abstract Adresse getAdresseResidence();

    /**
     * permet de rechercher un evenement parmi les evenements emis de la personne en fonction de l'evenement type
     *
     * @param evenementTypeEnum
     * @return evenement emmis
     */
    public Evenement findEvenementEmisParEvenementType(EvenementTypeEnum evenementTypeEnum) {
        for (Evenement evenement : this.evenementEmis) {
            if (evenement.getEvenementType().getCode().equals(evenementTypeEnum.getCode())) {
                return evenement;
            }
        }
        return null;
    }

    /**
     * Retourne l'Acteur Interne à l'origine de la suppression
     *
     * @return ActeurInterne
     */
    public ActeurInterne getInfosSuppressionActeurInterne() {
        return infosSuppressionActeurInterne;
    }

    /**
     * Affecte l'Acteur Interne à l'origigne de la suppression
     *
     * @param infosSuppressionActeurInterne
     */
    public void setInfosSuppressionActeurInterne(ActeurInterne infosSuppressionActeurInterne) {
        this.infosSuppressionActeurInterne = infosSuppressionActeurInterne;
    }

    /*
     * (non-Javadoc)
     *
     * @seefr.gouv.justice.cassiopee.invariant.edition.DestinataireEdition# getDestinataireEditionId()
     */
    @Override
    public Long getDestinataireEditionId() {
        return id;
    }

    /**
     * @return Returns the list of evenements De Convocation d'une personne pour un evenement bien précis.
     */
    public List<Long> findEvenementDeConvocationAudienceForEvtType(String codeJuridictionAudience,
            Date dateDebutAudience, Long serviceIdAudience, String codeEvt) {
        List<Long> evtsConvocationId = new ArrayList<>();
        Date dateAudience = Audience.truncateDateDebut(dateDebutAudience);

        for (PersonneEvenement perEvt : personneEvenements) {
            if(perEvt.getEvenement() != null) {
                if (perEvt.getEvenement().getEvenementType().getCode().equals(codeEvt)) {
                    Evenement evtConvoc = perEvt.getEvenement();
                    Date dateAudienceEvtConvocation = buildDateConvocation(evtConvoc);
                    Long srvAudEvtConvocId = null;
                    String codeJuridiction = null;
                    if (evtConvoc.getServiceCaracteristique(ServiceCaracteristiqueTypeEnum.SERV) != null) {
                        srvAudEvtConvocId = evtConvoc.getServiceCaracteristique(ServiceCaracteristiqueTypeEnum.SERV)
                                .getId();
                    }

                    if (evtConvoc.getJuridictionAudienceDeduite() != null) {
                        codeJuridiction = evtConvoc.getJuridictionAudienceDeduite().getCode();
                    }

                    if (StringUtils.equals(codeJuridiction, codeJuridictionAudience)
                            && dateAudience.equals(dateAudienceEvtConvocation)
                            && ObjectUtils.equals(srvAudEvtConvocId, serviceIdAudience)) {
                        evtsConvocationId.add(evtConvoc.getId());
                    }

                }
            }
        }

        return evtsConvocationId;
    }

    /**
     * @return Returns the evenement De Convocation d'une personne.
     */
    public List<Long> findEvenementDeConvocationAudience(String codeJuridictionAudience, Date dateDebutAudience,
            Long serviceIdAudience) {
        List<Long> evtConvocationIds = new ArrayList<>();
        Date dateAudience = Audience.truncateDateDebut(dateDebutAudience);

        for (PersonneEvenement perEvt : personneEvenements) {
            if(perEvt.getEvenement() != null){
                if (perEvt.getEvenement().getEvenementType().isFamilleEvenementTypeCassiopee(
                        FamilleEvenementTypeCassiopeeEnum.EVENEMENT_DE_CONVOCATION_DEVANT_LA_JURIDICTION_DE_JUGEMENT)) {
                    Evenement evtConvoc = perEvt.getEvenement();
                    Date dateAudienceEvtConvocation = buildDateConvocation(evtConvoc);
                    Long srvAudEvtConvocId = null;
                    String codeJuridiction = null;
                    if (evtConvoc.getServiceCaracteristique(ServiceCaracteristiqueTypeEnum.SERV) != null) {
                        srvAudEvtConvocId = evtConvoc.getServiceCaracteristique(ServiceCaracteristiqueTypeEnum.SERV)
                                .getId();
                    }

                    if (evtConvoc.getJuridictionAudienceDeduite() != null) {
                        codeJuridiction = evtConvoc.getJuridictionAudienceDeduite().getCode();
                    }

                    if (StringUtils.equals(codeJuridiction, codeJuridictionAudience)
                            && dateAudience.equals(dateAudienceEvtConvocation)
                            && ObjectUtils.equals(srvAudEvtConvocId, serviceIdAudience)) {
                        evtConvocationIds.add(evtConvoc.getId());
                    }
                }
            }
        }

        return evtConvocationIds;
    }

    /**
     * Recuperation evenement d'opposition dont la personne est emettrice ou personne concernée et qui a un evenement
     * anterieur
     *
     * @param idJugementAnterieur
     *            evenement anterieur a l'evenement
     */
    public Long findEvenementOpposition(Long idJugementAnterieur) {

        for (Evenement evtEmis : evenementEmis) {
            if (evtEmis.getEvenementType().isFamilleEvenementTypeCassiopee(FamilleEvenementTypeCassiopeeEnum.EVENEMENT_OPPOSITION)) {
                // Une fois que l'on est sur l'événement OPPO, on parcourt ses événements antérieurs
                for (Evenement evtAnt : evtEmis.getEvenementAnterieurs()) {
                    // Si un événement antérieur est celui du jugement passé en paramètre
                    if (ObjectUtils.equals(evtAnt.getId(), idJugementAnterieur)) {
                        // Alors on retourne l'id de l'événement OPPO
                        return evtEmis.getId();
                    }
                }
            }
        }

        for (PersonneEvenement personneEvenement : personneEvenements) {
            Evenement evt = personneEvenement.getEvenement();
            if (evt.getEvenementType().isFamilleEvenementTypeCassiopee(FamilleEvenementTypeCassiopeeEnum.EVENEMENT_OPPOSITION)) {

                for (Evenement evtAnt : evt.getEvenementAnterieurs()) {
                    if (ObjectUtils.equals(evtAnt.getId(), idJugementAnterieur)) {
                        return evt.getId();
                    }
                }
            }
        }

        return null;
    }

    /**
     * Renvoi la date de l'evenement de convocation
     *
     * @param evtConvoc
     * @return Date
     */
    private Date buildDateConvocation(Evenement evtConvoc) {
        Date dateAudienceEvtCarac = getDateCarac(evtConvoc);
        Time heureAudienceEvtCarac = evtConvoc.getHeureCaracteristique(HeureCaracteristiqueTypeEnum.HEUR);

        return DateHeureUtils.getDateFromDateAndTime(dateAudienceEvtCarac,
                heureAudienceEvtCarac);
    }

    /**
     * Retourne une caracteristique Date d'une evenement
     *
     * @param evtConvoc
     *            Model evenement
     * @return Date
     */
    private Date getDateCarac(Evenement evtConvoc) {
        Date dateAudienceEvtCarac = evtConvoc.getDateCaracteristique(DateCaracteristiqueTypeEnum.DEB);
        if (dateAudienceEvtCarac == null) {
            dateAudienceEvtCarac = evtConvoc.getDateCaracteristique(DateCaracteristiqueTypeEnum.CONV);
        }

        if (dateAudienceEvtCarac == null) {
            dateAudienceEvtCarac = evtConvoc.getDateCaracteristique(DateCaracteristiqueTypeEnum.DAUDN);
        }

        return dateAudienceEvtCarac;
    }

    /**
     * @return retourne alertePersonne.
     */
    public String getAlertePersonne() {
        return alertePersonne;
    }

    /**
     * @param alertePersonne
     *            affecte le champs alertePersonne.
     */
    public void setAlertePersonne(String alertePersonne) {
        this.alertePersonne = alertePersonne;
    }

    /**
     * @return Returns the rasi102.
     */
    public Boolean getRasi102() {
        return rasi102;
    }

    /**
     * @param rasi102
     *            The rasi102 to set.
     */
    public void setRasi102(Boolean rasi102) {
        this.rasi102 = rasi102;
    }

    /**
     * @return the demandeDispoCivilProv475_1
     */
    public final Double getDemandeDispoCivilProv475_1() {
        return demandeDispoCivilProv475_1;
    }

    /**
     * @param demandeDispoCivilProv475_1
     *            the demandeDispoCivilProv475_1 to set
     */
    public final void setDemandeDispoCivilProv475_1(Double demandeDispoCivilProv475_1) {
        this.demandeDispoCivilProv475_1 = demandeDispoCivilProv475_1;
    }

    /**
     * @return descriptionBien
     */
    public String getDescriptionBien() {
        return descriptionBien;
    }

    /**
     * @param descriptionBien
     */
    public void setDescriptionBien(String descriptionBien) {
        this.descriptionBien = descriptionBien;
    }

    /**
     * @return localisationBien
     */
    public String getLocalisationBien() {
        return localisationBien;
    }

    /**
     * @param localisationBien
     */
    public void setLocalisationBien(String localisationBien) {
        this.localisationBien = localisationBien;
    }

    /**
     * @return descriptionSourceRevenu
     */
    public String getDescriptionSourceRevenu() {
        return descriptionSourceRevenu;
    }

    /**
     * @param descriptionSourceRevenu
     */
    public void setDescriptionSourceRevenu(String descriptionSourceRevenu) {
        this.descriptionSourceRevenu = descriptionSourceRevenu;
    }

    /**
     * @return localisationSourceRevenu
     */
    public String getLocalisationSourceRevenu() {
        return localisationSourceRevenu;
    }

    /**
     * @param localisationSourceRevenu
     */
    public void setLocalisationSourceRevenu(String localisationSourceRevenu) {
        this.localisationSourceRevenu = localisationSourceRevenu;
    }

    public String getLibelleComparable() {
        if (TypePersonneEnum.ELEMENT_STRUCTURE.getCode().equals(this.getTypePersonne().getCode())) {
            PersonneElementStructure persEltStruct = (PersonneElementStructure) this;
            return persEltStruct.getPersonneElementStructure().getLibelle();
        } else if (TypePersonneEnum.MORALE.getCode().equals(this.getTypePersonne().getCode())) {
            PersonneMorale personneMorale = (PersonneMorale) this;
            return personneMorale.getRaisonSociale();
        } else {
            PersonnePhysique personnePhysique = (PersonnePhysique) this;
            return personnePhysique.getNomUsage() + " " + personnePhysique.getPrenom();

        }
    }

    /**
     * Comparaison des elements obligatoires selon le type duune personne
     *
     * @return Chaine de comparaison
     */
    public String getElementsComparable() {
        String resultat = null;
        if (TypePersonneEnum.ELEMENT_STRUCTURE.getCode().equals(this.getTypePersonne().getCode())) {
            PersonneElementStructure persEltStruct = (PersonneElementStructure) this;
            resultat = persEltStruct.getPersonneElementStructure().getLibelle();
        } else if (TypePersonneEnum.MORALE.getCode().equals(this.getTypePersonne().getCode())) {
            PersonneMorale personneMorale = (PersonneMorale) this;
            if (personneMorale.getRaisonSociale() != null) {
                resultat = personneMorale.getRaisonSociale();
            } else {
                resultat = personneMorale.getEnseigne();
            }
        } else {
            PersonnePhysique personnePhysique = (PersonnePhysique) this;
            final StringBuilder elementComparable = new StringBuilder();
            if (personnePhysique.getNomNaissance() != null) {
                elementComparable.append(personnePhysique.getNomNaissance().toLowerCase());
            }
            elementComparable.append("");
            if (personnePhysique.getPrenom() != null) {
                elementComparable.append(personnePhysique.getPrenom().toLowerCase());
            }
            resultat = elementComparable.toString();
        }

        return resultat;
    }

    public Boolean getRedexAlerte() {
        return redexAlerte;
    }

    public void setRedexAlerte(Boolean redexAlerte) {
        this.redexAlerte = redexAlerte;
    }

    public boolean isPpelDemandee() {
        return ppelDemandee;
    }

    public void setPpelDemandee(boolean ppelDemandee) {
        this.ppelDemandee = ppelDemandee;
    }

    public Set<PeineOuMesure> getPersonneSurPemReparation() {
        return personneSurPemReparation;
    }

    public void setPersonneSurPemReparation(
            Set<PeineOuMesure> personneSurPemReparation) {
        this.personneSurPemReparation = personneSurPemReparation;
    }

    /**
     * retourne la liste des dommage et interet pour une personne et un evenement
     *
     * @return liste des dommages et interets
     */
    public Set<DetailDommageInteret> getDetailDi() {
        return detailDi;
    }

    /**
     * Renseigne la liste des dommages et interets pour une personne et un evenement
     *
     * @param detailDi : liste des dommages et interets
     */
    public void setDetailDi(Set<DetailDommageInteret> detailDi) {
        this.detailDi = detailDi;
    }

    public Set<DetailDommageInteret> getLienDiPersonne() {
        return lienDiPersonne;
    }

    public void setLienDiPersonne(Set<DetailDommageInteret> lienDiPersonne) {
        this.lienDiPersonne = lienDiPersonne;
    }

    public Collection<DetailDommageInteret> getDetailsDiProvisions(Boolean demande, Boolean indicateurRejet) {

        Collection<DetailDommageInteret> detailDiDemandeOuPrononce = new HashSet<>();
        boolean diARajouter;

        for (DetailDommageInteret unDetailDi : this.detailDi) {

            diARajouter = false;

            if (demande) {
                if (unDetailDi.getMontantDemande() != null) {
                    diARajouter = true;
                }
            } else {
                if (unDetailDi.getMontantAccorde() != null) {
                    diARajouter = true;
                }
            }

            if (diARajouter) {

                if (indicateurRejet == null)
                    detailDiDemandeOuPrononce.add(unDetailDi);
                else {
                    if (unDetailDi.isIndicateurRejet() == indicateurRejet) {
                        detailDiDemandeOuPrononce.add(unDetailDi);
                    }
                }

            }
        }

        return detailDiDemandeOuPrononce;
    }

    public Set<Personne> getPersonnesConcerneesParLesReparation() {

        Set<DetailDommageInteret> tousLesDetailsDI = this.getDetailDi();

        Set<Personne> persConcernees = new HashSet<>();

        for (DetailDommageInteret unDetailDI : tousLesDetailsDI) {

            persConcernees.addAll(unDetailDI.getAuteurs());
        }

        return persConcernees;
    }

    public ActeurInterne getPersonneEnChargeActeurInterne() {
        return personneEnChargeActeurInterne;
    }

    public void setPersonneEnChargeActeurInterne(ActeurInterne personneEnChargeActeurInterne) {
        this.personneEnChargeActeurInterne = personneEnChargeActeurInterne;
    }

    public ElementStructure getPersonneEnChargeElementStructure() {
        return personneEnChargeElementStructure;
    }

    public void setPersonneEnChargeElementStructure(ElementStructure personneEnChargeElementStructure) {
        this.personneEnChargeElementStructure = personneEnChargeElementStructure;
    }

    public ReferentielPV getReferentielPV() {
        return referentielPV;
    }

    public void setReferentielPV(ReferentielPV referentielPV) {
        this.referentielPV = referentielPV;
    }

    /**
     * @return vrai si la personne a le rôle Auteur.
     */
    public boolean hasRoleAuteur() {
        return rolePersonne != null && rolePersonne.isAuteur();
    }

    /**
     * @return vrai si la personne n'a pas de rôle Auteur.
     */
    public boolean hasNoRoleAuteur() {
        return !hasRoleAuteur();
    }

    public String getTelephonePortable() {
        return telephonePortable;
    }

    public void setTelephonePortable(String telephonePortable) {
        this.telephonePortable = telephonePortable;
    }


    public boolean isConsentementPortail() {
        return consentementPortail;
    }

    public void setConsentementPortail(boolean consentementPortail) {
        this.consentementPortail = consentementPortail;
    }

    public String getNomResponsableJAP() {
        return nomResponsableJAP;
    }

    public void setNomResponsableJAP(String nomResponsableJAP) {
        this.nomResponsableJAP = nomResponsableJAP;
    }

    public String getNumDossierAPPI() {
        return numDossierAPPI;
    }

    public void setNumDossierAPPI(String numDossierAPPI) {
        this.numDossierAPPI = numDossierAPPI;
    }

    public String getNumCabinetAPPI() {
        return numCabinetAPPI;
    }

    public void setNumCabinetAPPI(String numCabinetAPPI) {
        this.numCabinetAPPI = numCabinetAPPI;
    }

    public Set<EtatRecoursPersonne> getEtatsRecours() {
        return etatsRecours;
    }

    public Date getDateModificationConsentement() {
        return dateModificationConsentement;
    }

    public void setEtatsRecours(Set<EtatRecoursPersonne> etatsRecours) {
        this.etatsRecours = etatsRecours;
    }

    public EtatRecoursPersonne getEtatRecoursActuel() {
        return etatsRecours.stream()
                .sorted((e1, e2) -> e2.getDateFraicheur().compareTo(e1.getDateFraicheur()))
                .findFirst().orElse(null);
    }

    public void setDateModificationConsentement(Date dateModificationConsentement) {
        this.dateModificationConsentement = dateModificationConsentement;
    }

    public Boolean getArretOpposableIntervenant() {
        return arretOpposableIntervenant;
    }

    public void setArretOpposableIntervenant(Boolean arretOpposableIntervenant) {
        this.arretOpposableIntervenant = arretOpposableIntervenant;
    }

    public Set<IndicateurPersonneEnum> getIndicateurs() {
        return indicateurs;
    }

    public void setIndicateurs(Set<IndicateurPersonneEnum> indicateurs) {
        this.indicateurs = indicateurs;
    }

    public boolean hasIndicateurEnContactHabituelAvecMineurs() {
        boolean isEnContactAvecMineur = false;
        for (IndicateurPersonneEnum indicateur : this.indicateurs) {
            if (indicateur.equals(IndicateurPersonneEnum.EN_CONTACT_AVEC_MINEURS)) {
                isEnContactAvecMineur = true;
            }
        }
        return isEnContactAvecMineur;
    }
    /**
     * Regarder si la personne possède un évènement type passé en paramètre
     * @param evenementTypeEnum
     * @return
     */
    public boolean hasEvtType(EvenementTypeEnum evenementTypeEnum) {
        return this.getPersonneEvenements().stream().anyMatch(personneEvenement -> personneEvenement.getEvenement().getEvenementType().getCode().equals(evenementTypeEnum.getCode()));
}

    public Set<DoublonPersonne> getDoublons() {
        return doublons;
    }

    public void setDoublons(Set<DoublonPersonne> doublons) {
        this.doublons = doublons;
    }
    
    public boolean hasDoublonEligibleTaj() {
        if (CollectionUtils.isNotEmpty(doublons)) {
            return doublons.stream().anyMatch(doublon -> doublon.getReferentielPV().isEligibleTAJ());
        }
        return false;
    }

	public Personne getPersonneJonction() {
		return personneJonction;
	}

	public void setPersonneJonction(Personne personneJonction) {
		this.personneJonction = personneJonction;
	}

	public boolean isConsentementAvantJonction() {
		return consentementAvantJonction;
	}

	public void setConsentementAvantJonction(boolean consentementAvantJonction) {
		this.consentementAvantJonction = consentementAvantJonction;
	}

	public String getNomNaissance() {
		return nomNaissance;
	}

	public void setNomNaissance(String nomNaissance) {
		this.nomNaissance = nomNaissance;
	}
	
}
