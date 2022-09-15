package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.time.DateUtils;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.administration.acteur.externe.model.PersonneQualifiee;
import fr.gouv.justice.cassiopee.administration.acteur.interne.UserContext;
import fr.gouv.justice.cassiopee.administration.acteur.interne.model.Agent;
import fr.gouv.justice.cassiopee.administration.parametrage.service.enumeration.FamilleEvenementTypeCassiopeeEnum;
import fr.gouv.justice.cassiopee.administration.parametrage.service.enumeration.FamilleTypeElementStructureEnum;
import fr.gouv.justice.cassiopee.administration.structure.model.Service;
import fr.gouv.justice.cassiopee.common.exception.TechnicalException;
import fr.gouv.justice.cassiopee.common.util.DateHeureUtils;
import fr.gouv.justice.cassiopee.common.util.DureeAnMoisJour;
import fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire;
import fr.gouv.justice.cassiopee.invariant.edition.model.Edition;
import fr.gouv.justice.cassiopee.invariant.eurojust.model.ece.ECE;
import fr.gouv.justice.cassiopee.invariant.evenement.model.interfaces.IEvenementCaracs;
import fr.gouv.justice.cassiopee.invariant.evenement.service.enumeration.EtatEvenementEnum;
import fr.gouv.justice.cassiopee.invariant.evenement.service.enumeration.TypeEvenementEnum;
import fr.gouv.justice.cassiopee.invariant.infraction.model.Infraction;
import fr.gouv.justice.cassiopee.invariant.infraction.model.LienAuteurInfraction;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.EvenementFrappeAppel;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.EvenementInfraction;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.MesureContreX;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.PeineOuMesure;
import fr.gouv.justice.cassiopee.invariant.personne.model.AvocatPersonneAffaire;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.DecisionFrappeeAppel;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.DetailDommageInteret;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.LienAppelArret;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.PersonneEvenement;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.PersonneEvenementVolatile;
import fr.gouv.justice.cassiopee.invariant.scelle.model.Depot;
import fr.gouv.justice.cassiopee.invariant.scelle.model.Objet;
import fr.gouv.justice.cassiopee.invariant.scelle.model.PieceConviction;
import fr.gouv.justice.cassiopee.processus.ech.model.Demande;
import fr.gouv.justice.cassiopee.processus.ech.model.SupervisionEnvoiDecision;
import fr.gouv.justice.cassiopee.processus.execution.model.PieceExecution;
import fr.gouv.justice.cassiopee.processus.parquet.model.IdentificationParquet;
import fr.gouv.justice.cassiopee.processus.tdr.model.CommentaireRepris;
import fr.gouv.justice.cassiopee.processus.tdr.model.MessageReprise;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.EnumerationValue;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.EvenementType;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.MotifEvenementType;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.AvocatCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.DateCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.DureeCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.ElementStructureCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.EnumerationCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.HeureCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.MotifEvenementTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.NombreCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.PaysCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.PersonneQualifieeCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.ServiceCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.TexteCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;
import fr.gouv.justice.cassiopee.referentiel.structure.model.TypeElementStructure;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Pays;
import fr.gouv.justice.cassiopee.service.accueil.model.AlerteAppel;
import fr.gouv.justice.cassiopee.service.accueil.model.TacheAFaire;
import fr.gouv.justice.cassiopee.service.audience.model.AffaireFixee;
import fr.gouv.justice.cassiopee.service.audience.model.Audience;

/**
 * Les événements sont :
 * <ul>
 * <li>l'ensemble des actes judiciaires établis par tout magistrat du siège et du parquet, répertoriés dans le code de
 * procédure pénale&nbsp;;</li>
 * <li>les réponses faites par les personnes justiciables à ces actes judiciaires&nbsp;;</li>
 * <li>des décisions extérieures de certains partenaires.</li>
 * </ul>
 */
@SuppressWarnings("nls")
@RegleDeGestion(RG.EVTM002)
public abstract class Evenement implements IEvenementCaracs<Caracteristique>, java.io.Serializable, Comparable {

    private static final long serialVersionUID = -4299687613359470387L;

    private Long id;

    private Affaire affaire;

    private LienEmetteur lienEmetteur;

    private String codeEtat = EtatEvenementEnum.ACTIF.getCode();

    private EtatEvenement etat;

    private String codeEtatPrecedent;

    private EtatEvenement etatPrecedent;

    private Evenement evenementFederateur;

    private EvenementType evenementType;

    /**
     * Evenement erroné que cet événement remplace.
     */
    private Evenement evenementErrone;

    /**
     * Evenements remplaçant cet événement erroné.
     */
    private Set<Evenement> evenementCorrecteurs = new HashSet<>();

    private Date dateEvenement;

    private Date dateSaisieEvenement;

    private String commentaire;

    private Date datePurge;

    /**
     * Date d'alerte
     */
    private Date dateAlerte;

    private Boolean evenementExterieur;

    private String compoApresRectification;

    private InfoSuppression infoSuppression;

    private Boolean indicateurPublic;

    private Boolean indicateurResumePeine;

    private Boolean indicateurTermine;

    private Boolean vueGuidee;

    /**
     * données volatile des evenements
     */
    private Set<Volatile> donneesVolatile = new HashSet<>();

    /**
     * données volatile de personneEvenement PEV_PERSONNE_EVT_VOLATILE
     */
    private Set<PersonneEvenementVolatile> donneesPersonneEvenementVolatile = new HashSet<>();

    private Set<Relance> relances = new HashSet<>();

    private Set<MotifEvenementType> evenementMotifs = new HashSet<>();

    private Set<Evenement> evenementAnterieurs = new HashSet<>();

    private Set<EvtEvenementAnticipe> evenementsAnticipes = new HashSet<>();

    private Set<ECE> evenementEce = new HashSet<>();

    /**
     * Ensemble des événements dont cet événement est événement antérieur.
     */
    private Set<Evenement> evenementPosterieurs = new HashSet<>();

    private Set<EvenementRequete> requetes = new HashSet<>();

    private Set<Evenement> evenementFederes = new HashSet<>();

    private Set<EvenementInfraction> infractionEvenements = new HashSet<>();

    private Map<String, Caracteristique> caracteristiquesPersistante = new LinkedHashMap<>();

    private Set<LienDestinataire> lienDestinataires = new HashSet<>();

    /** Map sur l'id de la personne */
    private Map<Long, PersonneEvenement> personneEvenements = new HashMap<>();

    /**
     * Infractions directement liées à cet événement.
     */
    private Set<Infraction> infractions = new HashSet<>();

    /** LienEvenementAnterieurs liés à cet événement: antérieurs */
    private Set<LienEvenementAnterieur> lienEvenementAnterieurs = new HashSet<>();

    /** LienEvenementsAnterieurs liés à cet événement: postérieurs */
    private Set<LienEvenementAnterieur> lienEvenementPosterieurs = new HashSet<>();

    /** Lien avec les acteurs internes liés à cet événement */
    private Set<LienEvenementActeurInterne> lienEvenementActeurInterne = new HashSet<>();

    private Set<MesureContreX> mesureContreXPortees = new HashSet<>();

    private Agent agentCreateur;

    private Service serviceCreateur;

    /**
     * Identifications parquets de l'affaire liés à cet événement. Uniquement pour les événemtns acte de saisine.
     */
    private Set<IdentificationParquet> identificationParquets = new HashSet<>();

    private Set<PieceExecution> pieceExecutionSuivies = new HashSet<>();

    private Set<PeineOuMesure> mesuresSuivies = new HashSet<>();

    private Set<PeineOuMesure> mesurePPLs = new HashSet<>();

    private Set<PeineOuMesure> mesureCompletants = new HashSet<>();

    /** liste des messages de reprise associés à l'événement */
    private Set<MessageReprise> messageRepriseSet = new LinkedHashSet<>();

    /** liste des messages de reprise associés à l'événement */
    private Set<Demande> demandeSet = new LinkedHashSet<>();

    /** liste des taches à faire associées à l'événement */
    private Set<TacheAFaire> tacheAFaireSet = new LinkedHashSet<>();

    /** liste des commentaires de reprise associés à l'événement */
    private Set<CommentaireRepris> commentaireReprisSet = new LinkedHashSet<>();

    private Depot depot;

    private Objet objet;

    private PieceConviction pieceConviction;

    /**
     * résolution bug MJ_13580 attribut technique : utilisé pour inverser la relation many-to-one entre une édition et
     * l'evenement qui en est l'origine </br> ne corespond pas a un besoin fonctionnel : les setters/getters sont
     * declarés protected
     */
    private Set<Edition> editionDeclencheeEtComposeeSet;

    /**
     * L'affaire fixée associée
     */
    private AffaireFixee affaireFixee;

    /** Audience de plaidoirie d'un evenement de decision apres renvoi */
    private Set<Audience> listAudiencePlaidoirie;

    /**
     * Numéro de parquet de jonction s'il l'évènement a été joint (information remplie au moment de la duplication des
     * évènements pendant la jonction)
     */
    private String numeroParquetJonction;

    /**
     * Code TGI de jonction s'il l'évènement a été joint (information remplie au moment de la duplication des évènements
     * pendant la jonction)
     */
    private String codeTgiJonction;

    /**
     * Numéro de parquet de creation s'il l'évènement a été joint (information remplie au moment de la duplication des
     * évènements pendant la jonction)
     */
    private String numeroParquetCreationEvt;

    /**
     * Code TGI de creation s'il l'évènement a été joint (information remplie au moment de la duplication des évènements
     * pendant la jonction)
     */
    private String codeTgiCreationEvt;

    /**
     * Supervision des demandes d'envoi
     */
    private Set<SupervisionEnvoiDecision> supervisionEnvoiDecisions;

    private Set<DetailDommageInteret> detailDi = new HashSet<>();

    private Set<DecisionFrappeeAppel> decisionsFrappeesAppel = new HashSet<>();

    private Set<EvenementFrappeAppel> evenementsFrappesAppel = new HashSet<>();

    private Set<LienAppelArret> liensArret = new HashSet<>();

    private Set<LienAppelArret> liensAppel = new HashSet<>();

    private String indicateurArretRendu;

    private Set<DateDecisionAppel> dateDecisionAppel;

    private Set<AlerteAppel> alerteAppel;

    /**
     * Crée un événement.
     */
    public Evenement() {
        //
    }


    /**
     * @return Returns the tacheAFaireSet.
     */
    public Set<TacheAFaire> getTacheAFaireSet() {
        return tacheAFaireSet;
    }

    /**
     * @param tacheAFaireSet
     *            The tacheAFaireSet to set.
     */
    public void setTacheAFaireSet(Set<TacheAFaire> tacheAFaireSet) {
        this.tacheAFaireSet = tacheAFaireSet;
    }

    /**
     * @return Returns the demandeB1Set.
     */
    public Set<Demande> getDemandeSet() {
        return demandeSet;
    }

    /**
     * @param demandeB1Set
     *            The demandeB1Set to set.
     */
    public void setDemandeSet(Set<Demande> demandeSet) {
        this.demandeSet = demandeSet;
    }

    /**
     * @return Returns the serviceEmetteur.
     */
    public Service findServiceEmetteur() {
        if (!getLienEmetteur().estUnAgent()) {
            return null;
        }
        return getLienEmetteur().getAgent().getService();
    }

    /**
     * @return Returns the serviceCreateur.
     */
    public Service getServiceCreateur() {
        return serviceCreateur;
    }

    /**
     * @param serviceCreateur
     *            The serviceCreateur to set.
     */
    public void setServiceCreateur(Service serviceCreateur) {
        this.serviceCreateur = serviceCreateur;
    }

    /**
     * @return Returns the utilisateurCreateur.
     */
    public Agent getAgentCreateur() {
        return agentCreateur;
    }

    /**
     * @param agentCreateur
     *            The utilisateurCreateur to set.
     */
    public void setAgentCreateur(Agent agentCreateur) {
        this.agentCreateur = agentCreateur;
    }

    /**
     * Renvoie l'affaire dans laquelle prend place cet événement.
     *
     * @return l'affaire dans laquelle prend place cet événement.
     */
    public Affaire getAffaire() {
        return affaire;
    }

    /**
     * Valorise l'affaire dans laquelle prend place cet événement.
     *
     * @param affaire
     */
    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
    }

    /**
     * Renvoie les caractéristiques de l'événement.
     *
     * @return un Set<Caracteristique> contenant les caractéristiques de l'événement.
     */
    public CaracteristiquesUpdater getCaracteristiques() {
        return new CaracteristiquesUpdater(this);
    }

    /**
     * @return les caractéristiques persistantes (= ayant réellement des valeurs dans la base)
     */
    @Override
    public Map<String, Caracteristique> getCaracteristiquesPersistante() {
        return caracteristiquesPersistante;
    }

    /**
     * Valorise les caracteristiques persistantes
     *
     * @param caracteristiquesPersistante
     */
    @Override
    public void setCaracteristiquesPersistante(Map<String, Caracteristique> caracteristiquesPersistante) {
        this.caracteristiquesPersistante = caracteristiquesPersistante;
    }

    /**
     * Valorise les caractéristiques de l'événement.
     *
     * @param caracteristiques
     *            un Set contenant les caractéristiques de l'événement.
     */
    public void setCaracteristiques(Map<String, Caracteristique> caracteristiques) {
        this.caracteristiquesPersistante = caracteristiques;
    }

    /**
     * Renvoie le commentaire lié à l'événement considéré.
     *
     * @return String représentant le commentaire de l'événement.
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Valorise le commentaire lié à l'événement considéré.
     *
     * @param commentaire
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * Renvoie un simple texte indiquant des corrections éventuelles dans la composition de l'événement.
     *
     * @return String décrivant les corrections dans la composition.
     */
    public String getCompoApresRectification() {
        return compoApresRectification;
    }

    /**
     * Valorise le texte indiquant des corrections éventuelles dans la composition de l'événement.
     *
     * @param compoApresRectification
     *            un String décrivant les corrections dans la composition.
     */
    public void setCompoApresRectification(String compoApresRectification) {
        this.compoApresRectification = compoApresRectification;
    }

    /**
     * Renvoie la date de l'événement.
     *
     * @return la Date de l'événement.
     */
    @Override
    public Date getDateEvenement() {
        return dateEvenement;
    }

    /**
     * Valorise la date de l'événement.
     *
     * @param dateEvenement
     */
    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    /**
     * Renvoie la date de purge de l'événement. Il s'agit de la date à laquelle la purge c'est à dire la suppression
     * physique de l'événement purgé doit avoir lieu. Cette date est renseignée notamment par les événements de gestion.
     *
     * @return la Date de purge de l'événement.
     */
    public Date getDatePurge() {
        return datePurge;
    }

    /**
     * Valorise la date de purge de l'événement. Il s'agit de la date à laquelle la purge c'est à dire la suppression
     * physique de l'événement purgé doit avoir lieu. Cette date est renseignée notamment par les événements de gestion.
     *
     * @param datePurge
     */
    public void setDatePurge(Date datePurge) {
        this.datePurge = datePurge;
    }

    /**
     * Renvoie l'état actuel de l'événement. L'état permet entre autre de déterminer la liste des événements
     * "supprimés".
     *
     * @see #getEtatPrecedent() pour le libelle de l'état
     * @return l' Etat actuel de cet événement.
     */
    public EtatEvenementEnum getEtatEvenementEnum() {
        return EtatEvenementEnum.resolve(codeEtat);
    }

    /**
     * Valorise l'état courant de l'événement. L'état permet entre autre de déterminer la liste des événements
     * "supprimés". Cette méthode appelle #setEtatEvenementEnumPrecedent(Etat) pour affecter l'état precedent
     *
     * @see #setEtatEvenementEnumPrecedent
     * @param etatEvt
     */
    public void assignEtatEvenementEnum(EtatEvenementEnum etatEvt) {
        setEtatEvenementEnumPrecedent(this.getEtatEvenementEnum());
        this.codeEtat = etatEvt.getCode();
    }

    /**
     * Renvoie l'état précédent de l'événement. Permet entre autre de déterminer l'état de l'événement avant sa
     * suppression lors de sa réactivation.
     *
     * @see #getEtatPrecedent() pour le libelle de l'état
     * @return l' Etat précédent de cet événement
     */
    public EtatEvenementEnum getEtatEvenementEnumPrecedent() {
        return EtatEvenementEnum.resolve(codeEtatPrecedent);
    }

    /**
     * Valorise l'état précédent de l'événement. Utilisé lors d'un changement d'état
     *
     * @param etat
     */
    public void setEtatEvenementEnumPrecedent(EtatEvenementEnum etat) {
        this.codeEtatPrecedent = etat.getCode();
    }

    /**
     * Renvoie l'état actuel de l'événement. L'état permet entre autre de déterminer la liste des événements
     * "supprimés".
     *
     * @return l' Etat actuel de cet événement.
     */
    public EtatEvenement getEtat() {
        return etat;
    }

    /**
     * utiliser
     *
     * @see #assignEtatEvenementEnum(EtatEvenementEnum)
     * @param etat
     */
    public void setEtat(EtatEvenement etat) {
        this.etat = etat;
    }

    /**
     * Renvoie l'état précédent de l'événement. Permet entre autre de déterminer l'état de l'événement avant sa
     * suppression lors de sa réactivation.
     *
     * @return l' Etat précédent de cet événement
     */
    public EtatEvenement getEtatPrecedent() {
        return etatPrecedent;
    }

    protected void setEtatPrecedent(EtatEvenement etatPrecedent) {
        this.etatPrecedent = etatPrecedent;
    }

    public Set<ECE> getEvenementEce() {
        return evenementEce;
    }

    public void setEvenementEce(Set<ECE> evenementEce) {
        this.evenementEce = evenementEce;
    }

    /**
     * Renvoie les événements antérieurs liés à cet événement.
     *
     * @return les événements antérieurs liés à cet événement
     */
    public Set<Evenement> getEvenementAnterieurs() {
        return evenementAnterieurs;
    }

    /**
     * Définit les événements antérieurs liés à cet événement. Permet de lier un événement à un événement qui l'a
     * précédé dans la même affaire comme explicité dans les exemples ci-dessous.
     * <ul>
     * <li>lier un événement d?appel à l?événement jugement</li>
     * <li>lier un événement d?opposition au jugement</li>
     * <li>définir comme antérieurs les événements de demandes auxquelles répond une ordonnance.</li>
     * </ul>
     *
     * @param evenementAnterieurs
     */
    public void setEvenementAnterieurs(Set<Evenement> evenementAnterieurs) {
        this.evenementAnterieurs = evenementAnterieurs;
    }

    public Set<EvtEvenementAnticipe> getEvenementsAnticipes() {
        return evenementsAnticipes;
    }

    public void setEvenementsAnticipes(Set<EvtEvenementAnticipe> evenementsAnticipes) {
        this.evenementsAnticipes = evenementsAnticipes;
    }

    /**
     * Renvoie les événements correcteurs de cet événement. Cette méthode est la "réciproque" de getEvenementErrone().
     *
     * @return un Set contenant les événements qui corrigent cet événement
     */
    public Set<Evenement> getEvenementCorrecteurs() {
        return evenementCorrecteurs;
    }

    /**
     * Valorise les événements correcteurs de cet événement.
     *
     * @param evenementCorrecteurs
     */
    public void setEvenementCorrecteurs(Set<Evenement> evenementCorrecteurs) {
        this.evenementCorrecteurs = evenementCorrecteurs;
    }

    /**
     * Renvoie l'événement erroné que cet événement corrige.
     *
     * @return l' Evenement erroné corrigé par cet événement.
     */
    public Evenement getEvenementErrone() {
        return evenementErrone;
    }

    /**
     * Valorise l'événement erroné que cet événement corrige.
     *
     * @param evenementErrone
     */
    public void setEvenementErrone(Evenement evenementErrone) {
        this.evenementErrone = evenementErrone;
    }

    /**
     * Indique si l'événement est extérieur ou non. La propriété "evenementExterieur" sera positionnée à vrai s'il
     * s'agit d'un événement de l'exécution des peines, créés et donc visibles que sur la synthèse auteur après
     * jugement.
     *
     * @return true si l'événement est extérieur, false sinon.
     */
    public Boolean getEvenementExterieur() {
        return evenementExterieur;
    }

    /**
     * Valorise l'indicateur permettant de déterminer si l'événement est extérieur ou non. Il sera positionné à vrai
     * s'il s'agit d'un événement de l'exécution des peines, créés et donc visibles que sur la synthèse auteur après
     * jugement.
     *
     * @param evenementExterieur
     */
    public void setEvenementExterieur(Boolean evenementExterieur) {
        this.evenementExterieur = evenementExterieur;
    }

    /**
     * Renvoie l'événement fédérateur chapeautant cet événement. Exemples d'événements liés par une relation
     * fédérateur/fédéré:
     * <ul>
     * <li>l?événement jugement au cours duquel est pris l'événement de détention provisoire ou de disqualification.</li>
     * <li>le PV de 1ère comparution au cours duquel est pris l'événement de saisine du JLD</li>
     * </ul>
     *
     * @return l' Evenement fédérateur de cet événement.
     */
    public Evenement getEvenementFederateur() {
        return evenementFederateur;
    }

    /**
     * Valorise l'événement fédérateur chapeautant cet événement. Exemples d'événements liés par une relation
     * fédérateur/fédéré:
     * <ul>
     * <li>l?événement jugement au cours duquel est pris l'événement de détention provisoire ou de disqualification.</li>
     * <li>le PV de 1ère comparution au cours duquel est pris l'événement de saisine du JLD</li>
     * </ul>
     *
     * @param evenementFederateur
     */
    public void setEvenementFederateur(Evenement evenementFederateur) {
        this.evenementFederateur = evenementFederateur;
    }

    /**
     * Renvoie les événements fédérés par cet événement.
     *
     * @return un Set contenant les événements fédérés par cet événement
     * @see #getEvenementFederateur()
     */
    public Set<Evenement> getEvenementFederes() {
        return evenementFederes;
    }

    /**
     * Valorise les événements fédérés par cet événement.
     *
     * @param evenementFederes
     * @see #setEvenementFederateur(Evenement)
     */
    public void setEvenementFederes(Set<Evenement> evenementFederes) {
        this.evenementFederes = evenementFederes;
    }

    /**
     * Renvoie les motifs de cet événement.
     *
     * @return un Set contenant les motifs de cet événement.
     */
    public Set<MotifEvenementType> getEvenementMotifs() {
        return evenementMotifs;
    }

    /**
     * Valorise les motifs de cet événement.
     *
     * @param evenementMotifs
     */
    public void setEvenementMotifs(Set<MotifEvenementType> evenementMotifs) {
        this.evenementMotifs = evenementMotifs;
    }

    /**
     * Renvoie les événements dont cet événement est événement antérieur. Cette méthode est la "réciproque" de
     * #getEvenementAnterieurs()
     *
     * @see #getEvenementAnterieurs()
     * @return un Set contenant les événements postérieurs à cet événement.
     */
    public Set<Evenement> getEvenementPosterieurs() {
        return evenementPosterieurs;
    }

    /**
     * Valorise les événements dont cet événement est événement antérieur. Cette méthode est la "réciproque" de
     * #setEvenementAnterieurs()
     *
     * @param evenementPosterieurs
     * @see #setEvenementAnterieurs(Set)
     */
    public void setEvenementPosterieurs(Set<Evenement> evenementPosterieurs) {
        this.evenementPosterieurs = evenementPosterieurs;
    }

    /**
     * Renvoie le type de cet événement.
     *
     * @return l' EvenementType type de cet événement.
     */
    public EvenementType getEvenementType() {
        return evenementType;
    }

    /**
     * Valorise le type de cet événement.
     *
     * @param evenementType
     */
    public void setEvenementType(EvenementType evenementType) {
        this.evenementType = evenementType;
    }

    /**
     * Renvoie l'identifiant technique de l'événement. Cet identifiant unique est utilisé par Hibernate, le programme et
     * la base de données.
     *
     * @return un Long correspondant à l'identifiant technique de l'événement.
     */
    public Long getId() {
        return id;
    }

    /**
     * Valorise l'identifiant technique de l'événement. Cet identifiant unique est utilisé par Hibernate, le programme
     * et la base de données.
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return InfoSuppression correspondant à cet événement.
     */
    public InfoSuppression getInfoSuppression() {
        return infoSuppression;
    }

    /**
     * @param infoSuppression
     */
    public void setInfoSuppression(InfoSuppression infoSuppression) {
        this.infoSuppression = infoSuppression;
    }

    /**
     * @return un Set contenant les LiensDestinataires dont chacun pointe vers un destinataire de l'événement.
     */
    public Set<LienDestinataire> getLienDestinataires() {
        return lienDestinataires;
    }

    /**
     * @param lienDestinataires
     */
    public void setLienDestinataires(Set<LienDestinataire> lienDestinataires) {
        this.lienDestinataires = lienDestinataires;
    }

    /**
     * @return LienEmetteur vers l'émetteur de cet événement.
     */
    public LienEmetteur getLienEmetteur() {
        return lienEmetteur;
    }

    /**
     * @param lienEmetteur
     */
    public void setLienEmetteur(LienEmetteur lienEmetteur) {
        this.lienEmetteur = lienEmetteur;
    }

    /**
     * @return Set<Relance> contenant les relances de cet événement.
     */
    public Set<Relance> getRelances() {
        return relances;
    }

    /**
     * @param relances
     */
    public void setRelances(Set<Relance> relances) {
        this.relances = relances;
    }

    /**
     * @return Set<EvenementRequete> contenant les requêtes en correction de cet événement, validées ou non.
     */
    public Set<EvenementRequete> getRequetes() {
        return requetes;
    }

    /**
     * @param requetes
     *            Set contenant les requêtes en correction de cet événement, validées ou non.
     */
    public void setRequetes(Set<EvenementRequete> requetes) {
        this.requetes = requetes;
    }

    /**
     * retourne le type de l'evenement
     *
     * @return type de l'evenement
     */
    public abstract TypeEvenementEnum getTypeEvenementEnum();

    /**
     * retourne une valeur de caractéristique de type date par un code
     *
     * @param caracteristiqueTypeEnum
     * @return date ou null
     */
    public Date getDateCaracteristique(DateCaracteristiqueTypeEnum caracteristiqueTypeEnum) {
        Caracteristique caracteristique = getCaracteristiquesPersistante().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null)
            return (Date) caracteristique.getValue();
        return null;
    }

    /**
     * permet d'affecter une date à une caracteristique de type Date
     *
     * @param caracteristiqueTypeEnum
     * @param date
     * @return true si la caracteristique existe et est bien affectée
     */
    @SuppressWarnings("unchecked")
    public boolean setDateCaracteristique(DateCaracteristiqueTypeEnum caracteristiqueTypeEnum, Date date) {
        Caracteristique caracteristique = getCaracteristiques().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null) {
            caracteristique.setValue(date);
            return true;
        }
        return false;
    }

    /**
     * retourne une valeur de caractéristique de type Duree par un code
     *
     * @param caracteristiqueTypeEnum
     * @return duree ou null
     */
    public DureeAnMoisJour getDureeCaracteristique(DureeCaracteristiqueTypeEnum caracteristiqueTypeEnum) {
        Caracteristique caracteristique = getCaracteristiquesPersistante().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null)
            return (DureeAnMoisJour) caracteristique.getValue();
        return null;
    }

    /**
     * permet d'affecter une duree à une caracteristique de type Duree
     *
     * @param caracteristiqueTypeEnum
     * @param dureeMoisJour
     * @return true si la caracteristique existe et est bien affectée
     */
    @SuppressWarnings("unchecked")
    public boolean setDureeCaracteristique(DureeCaracteristiqueTypeEnum caracteristiqueTypeEnum,
            DureeAnMoisJour dureeMoisJour) {
        Caracteristique caracteristique = getCaracteristiques().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null) {
            caracteristique.setValue(dureeMoisJour);
            return true;
        }
        return false;
    }

    /**
     * retourne une valeur de caractéristique de type heure par un code
     *
     * @param caracteristiqueTypeEnum
     * @return time ou null
     */
    public Time getHeureCaracteristique(HeureCaracteristiqueTypeEnum caracteristiqueTypeEnum) {
        Caracteristique caracteristique = getCaracteristiquesPersistante().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null)
            return (Time) caracteristique.getValue();
        return null;
    }

    /**
     * permet d'affecter une heure à une caracteristique de type Heure
     *
     * @param caracteristiqueTypeEnum
     * @param time
     * @return true si la caracteristique existe et est bien affectée
     */
    @SuppressWarnings("unchecked")
    public boolean setHeureCaracteristique(HeureCaracteristiqueTypeEnum caracteristiqueTypeEnum, Time time) {
        Caracteristique caracteristique = getCaracteristiques().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null) {
            caracteristique.setValue(time);
            return true;
        }
        return false;
    }

    /**
     * retourne une valeur de caractéristique de type nombre par un code
     *
     * @param caracteristiqueTypeEnum
     * @return double ou null
     */
    public Double getNombreCaracteristique(NombreCaracteristiqueTypeEnum caracteristiqueTypeEnum) {
        Caracteristique caracteristique = getCaracteristiquesPersistante().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null) {
            if (caracteristique.getValue() instanceof fr.gouv.justice.cassiopee.common.util.Montant) {
                fr.gouv.justice.cassiopee.common.util.Montant montant = (fr.gouv.justice.cassiopee.common.util.Montant) caracteristique.getValue();
                return montant.getValeurMontant();
            } else {
                return (Double) caracteristique.getValue();
            }
        }
        return null;
    }

    /**
     * permet d'affecter une nombre à une caracteristique de type Nombre
     *
     * @param caracteristiqueTypeEnum
     * @param d
     * @return true si la caracteristique existe et est bien affectée
     */
    @SuppressWarnings("unchecked")
    public boolean setHeureCaracteristique(NombreCaracteristiqueTypeEnum caracteristiqueTypeEnum, Double d) {
        Caracteristique caracteristique = getCaracteristiques().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null) {
            caracteristique.setValue(d);
            return true;
        }
        return false;
    }

    /**
     * retourne une valeur de caractéristique de type texte par un code
     *
     * @param caracteristiqueTypeEnum
     * @return string ou null
     */
    public EnumerationValue getEnumerationCaracteristique(EnumerationCaracteristiqueTypeEnum caracteristiqueTypeEnum) {
        Caracteristique caracteristique = getCaracteristiquesPersistante().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null)
            return (EnumerationValue) caracteristique.getValue();
        return null;
    }

    /**
     * permet d'affecter une Enumeration à une caracteristique de type Enumeration
     *
     * @param caracteristiqueTypeEnum
     * @param d
     * @return true si la caracteristique existe et est bien affectée
     */
    @SuppressWarnings("unchecked")
    public boolean setEnumerationCaracteristique(EnumerationCaracteristiqueTypeEnum caracteristiqueTypeEnum,
            EnumerationValue d) {
        Caracteristique caracteristique = getCaracteristiques().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null) {
            caracteristique.setValue(d);
            return true;
        }
        return false;
    }

    /**
     * retourne une valeur de caractéristique de type texte par un code
     *
     * @param caracteristiqueTypeEnum
     * @return string ou null
     */
    public String getTexteCaracteristique(TexteCaracteristiqueTypeEnum caracteristiqueTypeEnum) {
        Caracteristique caracteristique = getCaracteristiquesPersistante().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null)
            return (String) caracteristique.getValue();
        return null;
    }

    /**
     * permet d'affecter une Texte à une caracteristique de type Texte
     *
     * @param caracteristiqueTypeEnum
     * @param texte
     * @return true si la caracteristique existe et est bien affectée
     */
    @SuppressWarnings("unchecked")
    public boolean setTexteCaracteristique(TexteCaracteristiqueTypeEnum caracteristiqueTypeEnum, String texte) {
        Caracteristique caracteristique = getCaracteristiques().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null) {
            caracteristique.setValue(texte);
            return true;
        }
        return false;
    }

    /**
     * retourne une valeur de caractéristique de type pays par un code
     *
     * @param caracteristiqueTypeEnum
     * @return pays ou null
     */
    public Pays getPaysCaracteristique(PaysCaracteristiqueTypeEnum caracteristiqueTypeEnum) {
        Caracteristique caracteristique = getCaracteristiquesPersistante().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null)
            return (Pays) caracteristique.getValue();
        return null;
    }

    /**
     * permet d'affecter un pays à une caracteristique de type Pays
     *
     * @param caracteristiqueTypeEnum
     * @param pays
     * @return true si la caracteristique existe et est bien affectée
     */
    @SuppressWarnings("unchecked")
    public boolean setPaysCaracteristique(PaysCaracteristiqueTypeEnum caracteristiqueTypeEnum, Pays pays) {
        Caracteristique caracteristique = getCaracteristiques().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null) {
            caracteristique.setValue(pays);
            return true;
        }
        return false;
    }

    /**
     * retourne une valeur de caractéristique de type element de structure par un code
     *
     * @param caracteristiqueTypeEnum
     * @return element de structure ou null
     */
    public ElementStructure getElementStructureCaracteristique(
            ElementStructureCaracteristiqueTypeEnum caracteristiqueTypeEnum) {
        Caracteristique caracteristique = getCaracteristiquesPersistante().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null)
            return (ElementStructure) caracteristique.getValue();
        return null;
    }

    /**
     * permet d'affecter un ElementStructure à une caracteristique de type ElementStructure
     *
     * @param caracteristiqueTypeEnum
     * @param elementStructure
     * @return true si la caracteristique existe et est bien affectée
     */
    @SuppressWarnings("unchecked")
    public boolean setElementStructureCaracteristique(ElementStructureCaracteristiqueTypeEnum caracteristiqueTypeEnum,
            ElementStructure elementStructure) {
        Caracteristique caracteristique = getCaracteristiques().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null) {
            caracteristique.setValue(elementStructure);
            return true;
        }
        return false;
    }

    /**
     * retourne une valeur de caractéristique de type Service par un code
     *
     * @param caracteristiqueTypeEnum
     * @return service ou null
     */
    public Service getServiceCaracteristique(ServiceCaracteristiqueTypeEnum caracteristiqueTypeEnum) {
        Caracteristique caracteristique = getCaracteristiquesPersistante().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null)
            return (Service) caracteristique.getValue();
        return null;
    }

    /**
     * permet d'affecter un Service à une caracteristique de type Service
     *
     * @param caracteristiqueTypeEnum
     * @param service
     * @return true si la caracteristique existe et est bien affectée
     */
    @SuppressWarnings("unchecked")
    public boolean setServiceCaracteristique(ServiceCaracteristiqueTypeEnum caracteristiqueTypeEnum, Service service) {
        Caracteristique caracteristique = getCaracteristiques().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null) {
            caracteristique.setValue(service);
            return true;
        }
        return false;
    }

    /**
     * retourne une valeur de caractéristique de Avocat par un code
     *
     * @param caracteristiqueTypeEnum
     * @return element de structure ou null
     */
    public AvocatPersonneAffaire getAvocatCaracteristique(AvocatCaracteristiqueTypeEnum caracteristiqueTypeEnum) {
        Caracteristique caracteristique = getCaracteristiquesPersistante().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null)
            return (AvocatPersonneAffaire) caracteristique.getValue();
        return null;
    }

    /**
     * permet d'affecter un Avocat à une caracteristique de type Avocat
     *
     * @param caracteristiqueTypeEnum
     * @param avocat
     * @return true si la caracteristique existe et est bien affectée
     */
    @SuppressWarnings("unchecked")
    public boolean setAvocatCaracteristique(AvocatCaracteristiqueTypeEnum caracteristiqueTypeEnum,
            AvocatPersonneAffaire avocat) {
        Caracteristique caracteristique = getCaracteristiques().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null) {
            caracteristique.setValue(avocat);
            return true;
        }
        return false;
    }

    /**
     * Retourne une valeur de caractéristique de Personne Qualifiee par un code
     *
     * @param caracteristiqueTypeEnum
     * @return PersonneQualifiee
     */
    public PersonneQualifiee getPersonneQualifieeCaracteristique(
            PersonneQualifieeCaracteristiqueTypeEnum caracteristiqueTypeEnum) {
        Caracteristique caracteristique = getCaracteristiquesPersistante().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null)
            return (PersonneQualifiee) caracteristique.getValue();
        return null;
    }

    /**
     * Permet d'affecter ue Personne Qualifiée à une caracteristique de type Personne Qualifiée
     *
     * @param caracteristiqueTypeEnum
     * @param personneQualifiee
     * @return true si la caracteristique existe et est bien affectée
     */
    @SuppressWarnings("unchecked")
    public boolean setPersonneQualifieeCaracteristique(
            PersonneQualifieeCaracteristiqueTypeEnum caracteristiqueTypeEnum, PersonneQualifiee personneQualifiee) {
        Caracteristique caracteristique = getCaracteristiques().get(caracteristiqueTypeEnum.getCode());
        if (caracteristique != null) {
            caracteristique.setValue(personneQualifiee);
            return true;
        }
        return false;
    }

    /**
     * Les personnes-événements, sous la forme d'un Map sur l'id de la personne.
     *
     * @return Returns the personneEvenements.
     */
    public Map<Long, PersonneEvenement> getPersonneEvenements() {
        return personneEvenements;
    }

    /**
     * Les personnes-événements, sous la forme d'un Map sur l'id de la personne.
     *
     * @param personneEvenements
     *            The personneEvenements to set.
     */
    public void setPersonneEvenements(Map<Long, PersonneEvenement> personneEvenements) {
        this.personneEvenements = personneEvenements;
    }

    /**
     * Renvoie les infractions directement liées à cet événement.
     *
     * @see #findAllAuteurInfractions()
     * @return un Set<Infractions> contenant les infractions directement liées à cet événement.
     */
    public Set<Infraction> getInfractions() {
        return infractions;
    }

    /**
     * Valorise les infractions directement liées à cet événement.
     *
     * @param infractions
     * @see PersonneEvenement#setAuteurInfractions(Set)
     */
    public void setInfractions(Set<Infraction> infractions) {
        this.infractions = infractions;
    }

    /**
     * Renvoie les couples auteur/infraction liés à cet événement.
     *
     * @return un Set<LienAuteurInfraction> contenant les liens auteur/infractions reliés à cet événement.
     */
    public Set<LienAuteurInfraction> findAllAuteurInfractions() {
        Set<LienAuteurInfraction> auteurInfractions = new HashSet<>();
        for (PersonneEvenement personneEvenement : this.getPersonneEvenements().values()) {
            auteurInfractions.addAll(personneEvenement.getAuteurInfractions());
        }
        return auteurInfractions;
    }

    /**
     * @return Returns the lienEvenementAnterieurs.
     */
    public Set<LienEvenementAnterieur> getLienEvenementAnterieurs() {
        return lienEvenementAnterieurs;
    }

    /**
     * @param lienEvenementAnterieurs
     *            The lienEvenementAnterieurs to set.
     */
    public void setLienEvenementAnterieurs(Set<LienEvenementAnterieur> lienEvenementAnterieurs) {
        this.lienEvenementAnterieurs = lienEvenementAnterieurs;
    }

    /**
     * @return Returns the lienEvenementPosterieurs.
     */
    public Set<LienEvenementAnterieur> getLienEvenementPosterieurs() {
        return lienEvenementPosterieurs;
    }

    /**
     * @param lienEvenementPosterieurs
     *            The lienEvenementPosterieurs to set.
     */
    public void setLienEvenementPosterieurs(Set<LienEvenementAnterieur> lienEvenementPosterieurs) {
        this.lienEvenementPosterieurs = lienEvenementPosterieurs;
    }

    /**
     * @return the lienEvenementActeurInterne
     */
    public Set<LienEvenementActeurInterne> getLienEvenementActeurInterne() {
        return lienEvenementActeurInterne;
    }

    /**
     * @param lienEvenementActeurInterne
     *            the lienEvenementActeurInterne to set
     */
    public void setLienEvenementActeurInterne(Set<LienEvenementActeurInterne> lienEvenementActeurInterne) {
        this.lienEvenementActeurInterne = lienEvenementActeurInterne;
    }

    /**
     * @return retourne indicateurPublic.
     */
    public Boolean getIndicateurPublic() {
        return indicateurPublic;
    }

    /**
     * @param indicateurPublic
     *            affecte indicateurPublic
     */
    public void setIndicateurPublic(Boolean indicateurPublic) {
        this.indicateurPublic = indicateurPublic;
    }

    /**
     * @return retourne indicateurResumePeine.
     */
    public Boolean getIndicateurResumePeine() {
        return indicateurResumePeine;
    }

    /**
     * @param indicateurResumePeine
     *            affecte indicateurResumePeine
     */
    public void setIndicateurResumePeine(Boolean indicateurResumePeine) {
        this.indicateurResumePeine = indicateurResumePeine;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).toString();
    }

    /**
     * retourne le lien destinataire de l'evenement
     *
     * @return lien destinataire
     */
    public LienDestinataire getLienDestinataire() {
        if (this.getLienDestinataires().isEmpty())
            return null;

        return this.lienDestinataires.iterator().next();
    }

    /**
     * @return un Set<MesureContreX> contenant les mesures portées par cet événement.
     */
    public Set<MesureContreX> getMesureContreXPortees() {
        return mesureContreXPortees;
    }

    /**
     * Valorise l'ensemble des mesures portées par cet événement.
     *
     * @param mesurePortees
     *            un Set<MesureContreX> contenant les mesures voulues.
     */
    public void setMesureContreXPortees(Set<MesureContreX> mesurePortees) {
        this.mesureContreXPortees = mesurePortees;
    }

    /**
     * Identifications parquets de l'affaire liés à cet événement. Uniquement pour les événements acte de saisine.
     *
     * @return Returns the identificationParquets.
     */
    public Set<IdentificationParquet> getIdentificationParquets() {
        return identificationParquets;
    }

    /**
     * Identifications parquets de l'affaire liés à cet événement. Uniquement pour les événements acte de saisine.
     *
     * @param identificationParquets
     *            The identificationParquets to set.
     */
    public void setIdentificationParquets(Set<IdentificationParquet> identificationParquets) {
        this.identificationParquets = identificationParquets;
    }

    /**
     * @return retourne pieceExecutionSuivies.
     */
    public Set<PieceExecution> getPieceExecutionSuivies() {
        return pieceExecutionSuivies;
    }

    /**
     * @param pieceExecutionSuivies
     *            affecte pieceExecutionSuivies
     */
    public void setPieceExecutionSuivies(Set<PieceExecution> pieceExecutionSuivies) {
        this.pieceExecutionSuivies = pieceExecutionSuivies;
    }

    /**
     * @return une PeineOuMesure représentant la mesure suivie.
     */
    public Set<PeineOuMesure> getMesuresSuivies() {
        return mesuresSuivies;
    }

    /**
     * @param mesures
     *            une PeineOuMesure représentant la mesure suivie.
     */
    public void setMesuresSuivies(Set<PeineOuMesure> mesures) {
        this.mesuresSuivies = mesures;
    }

    /**
     * @return retourne mesurePPLs.
     */
    public Set<PeineOuMesure> getMesurePPLs() {
        return mesurePPLs;
    }

    /**
     * @param mesurePPLs
     *            affecte mesurePPLs
     */
    public void setMesurePPLs(Set<PeineOuMesure> mesurePPLs) {
        this.mesurePPLs = mesurePPLs;
    }


    public String getIndicateurArretRendu() {
        return indicateurArretRendu;
    }


    public void setIndicateurArretRendu(String indicateurArretRendu) {
        this.indicateurArretRendu = indicateurArretRendu;
    }


    /**
     * Crée un clone de l'événement à l'intérieur d'une affaire. Copie les propriétés de base ainsi que l'émetteur, le
     * destinataire, les caractéristiques, les liens personnes concernées, les liens auteurs-infractions concernés. On
     * ne copie pas les liens suivants car ils sont de type one-to-many: mesures portées, événements fédérés,
     * identifications parquets. Par souci de cohérence, on ne copie pas les liens suivants: événements postérieurs. On
     * ne copie pas les relances ni les données volatiles (Motivation OR...) On ne copie pas non plus les détails des
     * personnes-événements, au-delà du lien avec la personne. On ne copie donc pas les dispositifs portés.
     *
     * @return un nouvel événement non sauvé en base avec un id null.
     * @throws CloneNotSupportedException
     */
    @Override
    public Evenement clone() throws CloneNotSupportedException {

        Evenement newEvenement;
        try {
            newEvenement = this.getClass().newInstance();
        } catch (InstantiationException e) {
            throw new TechnicalException("Instantiation Exception", e);
        } catch (IllegalAccessException e) {
            throw new TechnicalException("IllegalAccessException", e);
        }

        newEvenement.setId(null);
        newEvenement.setAffaire(this.affaire);
        newEvenement.setEvenementType(this.evenementType);
        newEvenement.setAgentCreateur(this.agentCreateur);
        newEvenement.setEtat(this.etat);
        newEvenement.setEtatPrecedent(this.etatPrecedent);
        newEvenement.setCommentaire(this.commentaire);
        newEvenement.setCompoApresRectification(this.compoApresRectification);
        newEvenement.setDateEvenement(this.dateEvenement);
        newEvenement.setDateSaisieEvenement(this.dateSaisieEvenement);
        newEvenement.setDatePurge(this.datePurge);
        newEvenement.setIndicateurPublic(this.indicateurPublic);
        newEvenement.setIndicateurResumePeine(this.indicateurResumePeine);

        for (EvenementRequete oldRequete : this.getRequetes()) {
            newEvenement.getRequetes().add(oldRequete.clone());
        }

        newEvenement.setLienEmetteur(this.getLienEmetteur());

        newEvenement.setLienDestinataires(new HashSet<LienDestinataire>());
        for (LienDestinataire oldLienDest : this.getLienDestinataires()) {
            LienDestinataire newLienDest = oldLienDest.clone();
            // nouveau lien destinataire sera créer
            newLienDest.setId(null);
            newLienDest.setEvenement(newEvenement);
            newEvenement.getLienDestinataires().add(newLienDest);
        }

        newEvenement.setEvenementAnterieurs(new HashSet<Evenement>());
        for (Evenement evenementAnterieur : this.getEvenementAnterieurs()) {
            newEvenement.getEvenementAnterieurs().add(evenementAnterieur);
        }

        newEvenement.setEvenementCorrecteurs(new HashSet<Evenement>());
        for (Evenement evenementCorrecteur : this.getEvenementCorrecteurs()) {
            newEvenement.getEvenementCorrecteurs().add(evenementCorrecteur);
        }

        newEvenement.setEvenementErrone(this.getEvenementErrone());
        newEvenement.setEvenementExterieur(this.getEvenementExterieur());
        newEvenement.setEvenementFederateur(this.getEvenementFederateur());
        // On s'abstient soigneusement de mettre des événements fédérés (ou
        // postérieurs)

        newEvenement.setInfractionEvenements(new HashSet<EvenementInfraction>());

        newEvenement.setEvenementMotifs(new HashSet<MotifEvenementType>());
        for (MotifEvenementType motif : this.getEvenementMotifs()) {
            newEvenement.getEvenementMotifs().add(motif);
        }

        newEvenement.setInfractions(new HashSet<Infraction>());
        for (Infraction infraction : this.getInfractions()) {
            newEvenement.getInfractions().add(infraction);
        }

        newEvenement.setPersonneEvenements(new HashMap<Long, PersonneEvenement>());
        for (PersonneEvenement personneEvenement : this.getPersonneEvenements().values()) {
            PersonneEvenement newPersonneEvenement = new PersonneEvenement();
            newPersonneEvenement.setPersonne(personneEvenement.getPersonne());
            newPersonneEvenement.setEvenement(newEvenement);

            // On relie les liens auteurs-infractions
            for (LienAuteurInfraction lienAuteurInfraction : personneEvenement.getAuteurInfractions()) {
                newPersonneEvenement.getAuteurInfractions().add(lienAuteurInfraction);
            }

            // On s'arrête là pour le moment, on ne précise pas les propriétés
            // des personnes-événements, en particulier on ne copie pas les
            // Mesures.
            newEvenement.getPersonneEvenements().put(newPersonneEvenement.getPersonne().getId(), newPersonneEvenement);
        }

        newEvenement.setCaracteristiques(new HashMap<String, Caracteristique>());
        /*
         * NOTE // TODO: les caractéristiques sont difficiles à copier
         */

        // pas de recopie des informations de reprise
        newEvenement.setMessageRepriseSet(new HashSet<MessageReprise>());
        newEvenement.setCommentaireReprisSet(new HashSet<CommentaireRepris>());

        return newEvenement;

    }

    /**
     * @return ParametrageRetourSouhaite
     */
    public ParametrageRetourSouhaiteCriteria getParametrageRetourSouhaite() {

        Long serviceId = null;
        String codeElementStructure = null;
        String codeTypeElementStructure = null;

        if (getLienEmetteur().estUnAgent()) {

            serviceId = getLienEmetteur().getAgent().getService().getId();

        } else if (getLienDestinataire() != null) {

            if (getLienDestinataire().estUnElementStructure()) {

                codeElementStructure = getLienDestinataire().getElementStructure().getCode();
            }

            codeTypeElementStructure = getLienDestinataire().getTypeElementStructure().getCode();
        }

        return new ParametrageRetourSouhaiteCriteria(serviceId, codeElementStructure, codeTypeElementStructure);
    }

    /**
     * @return Set<Volatile>
     */
    public Set<Volatile> getDonneesVolatile() {
        return donneesVolatile;
    }

    /**
     * @param donneesVolatile
     */
    public void setDonneesVolatile(Set<Volatile> donneesVolatile) {
        this.donneesVolatile = donneesVolatile;
    }

    public Set<Audience> getListAudiencePlaidoirie() {
        return listAudiencePlaidoirie;
    }


    public void setListAudiencePlaidoirie(Set<Audience> listAudiencePlaidoirie) {
        this.listAudiencePlaidoirie = listAudiencePlaidoirie;
    }

    /**
     * retourne la juridiction d'audience déduite des caracteristiques de cet evenement.
     *
     * @return juridiction
     * @see fr.gouv.justice.cassiopee.service.audience.model.Audience#getJuridictionJugement()
     * @see ElementStructureCaracteristiqueTypeEnum#EL_ST
     * @see ElementStructureCaracteristiqueTypeEnum#JURID
     */
    public ElementStructure getJuridictionAudienceDeduite() {
        ElementStructure elementStructure = getElementStructureCaracteristique(ElementStructureCaracteristiqueTypeEnum.JURID);

        if (elementStructure == null) {
            // code les reste du SR 03/08/05
            elementStructure = getElementStructureCaracteristique(ElementStructureCaracteristiqueTypeEnum.EL_ST);
        }

        return elementStructure;
    }

    /**
     * retourne le service d'audience déduit des caracteristiques de cet evenement.
     *
     * @return service
     * @see fr.gouv.justice.cassiopee.service.audience.model.Audience#getRattachementService()
     * @see ServiceCaracteristiqueTypeEnum#SERV
     */
    public Service getServiceAudienceDeduit() {
        return getServiceCaracteristique(ServiceCaracteristiqueTypeEnum.SERV);
    }

    /**
     * retourne la date de l'audience déduite des caracteristiques de cet evenement.
     *
     * @return date
     * @see fr.gouv.justice.cassiopee.service.audience.model.Audience#getDateDebut()
     * @see fr.gouv.justice.cassiopee.service.audience.model.Audience#getDateFin()
     * @see fr.gouv.justice.cassiopee.service.audience.model.Audience#getDateFinPrevue()
     * @see DateCaracteristiqueTypeEnum#DEB
     * @see DateCaracteristiqueTypeEnum#DAUDN
     * @see DateCaracteristiqueTypeEnum#CONV
     * @see HeureCaracteristiqueTypeEnum#HEUR
     */
    public Date getDateAudienceDeduite() {
        Time heureCaracteristique = getHeureCaracteristique(HeureCaracteristiqueTypeEnum.HEUR);
        Date dateCaracteristique = getDatePossibles();

        // preparation d'un objet date + heure
        return DateHeureUtils.getDateFromDateAndTime(dateCaracteristique, heureCaracteristique);
    }

    /**
     * est-ce que l'audience matche avec les caracteristique de cet evenement
     *
     * @param audience
     * @return boolean
     */
    public boolean matchAudience(Audience audience) {
        if (getJuridictionAudienceDeduite() == null
                || (audience != null && !audience.getJuridictionJugement().getCode().equals(
                        getJuridictionAudienceDeduite().getCode()))) {
            return false;
        }
        if (getDateAudienceDeduite() == null
                || (audience != null && (audience.getDateDebut().after(getDateAudienceDeduite()) || audience
                        .getDateFinPrevue().before(getDateAudienceDeduite())))) {
            return false;
        }
        if (getServiceAudienceDeduit() != null
                && (audience != null && !audience.getRattachementService().getId().equals(
                        getServiceAudienceDeduit().getId()))) {
            return false;
        }
        return true;
    }

    private Date getDatePossibles() {
        for (DateCaracteristiqueTypeEnum dateCaracteristiqueTypeEnum : DateCaracteristiqueTypeEnum.DATES_AUDIENCE_DEDUITES) {
            Date dateCaracteristique = getDateCaracteristique(dateCaracteristiqueTypeEnum);

            if (dateCaracteristique != null) {
                return dateCaracteristique;
            }
        }
        return null;
    }

    /**
     * retourne la date d'alerte
     *
     * @return la date d'alerte
     */
    public Date getDateAlerte() {
        return dateAlerte;
    }

    /**
     * affecte la date d'alerte
     *
     * @param dateAlerte
     */
    public void setDateAlerte(Date dateAlerte) {
        this.dateAlerte = dateAlerte;
    }

    @Override
    public EvenementType getEvenementTypePourCaracs() {
        return evenementType;
    }

    /**
     * @param mesureCompletants
     */
    public void setMesureCompletants(Set<PeineOuMesure> mesureCompletants) {
        this.mesureCompletants = mesureCompletants;
    }

    /**
     * @return mesureCompletants
     */
    public Set<PeineOuMesure> getMesureCompletants() {
        return mesureCompletants;
    }

    /**
     * recherche et retourne la date de la caracteristique portant l'indicateur alerte
     *
     * @return date ou null
     */
    public Date getDatePortantIndicateurAlerte() {
        for (DateCaracteristiqueTypeEnum dateCaracteristiqueTypeEnum : DateCaracteristiqueTypeEnum.DATES_AVEC_INDICATEUR_ALERTE) {
            Date dateCaracteristique = getDateCaracteristique(dateCaracteristiqueTypeEnum);

            if (dateCaracteristique != null) {
                return dateCaracteristique;
            }
        }
        return null;
    }

    public boolean estPublic() {
        return this.getIndicateurPublic() != null && this.getIndicateurPublic();
    }

    /**
     * indicateur anticipé
     *
     * @param userContext
     * @return true si anticipé et false sinon
     */
    @RegleDeGestion({RG.EVTM105,RG.EDNM012})
    public boolean estAnticipe(UserContext userContext) {
        return !DateUtils.isSameDay(getDateEvenement(), userContext.getDateDuJour())
                && getDateEvenement().after(userContext.getDateDuJour());
    }

    /**
     * determine si l'evenement est visible ou non pour l'utilisateur suivant la AUTM050
     *
     * @param userContext
     * @return true ou false
     */
    @RegleDeGestion(RG.AUTM050)
    public boolean estVisible(UserContext userContext) {
        return !estAnticipe(userContext) || sameEmetteurOrCreateur(userContext);
    }



    public boolean sameEmetteurOrCreateur(UserContext userContext) {
        return userContext.getServiceIdentifier() != null && (getLienEmetteur().estUnAgent()
                && getLienEmetteur().getAgent().getService() != null
                && getLienEmetteur().getAgent().getService().getId().equals(userContext.getServiceIdentifier().getId()) || (getAgentCreateur() != null
                && getAgentCreateur().getService() != null && getAgentCreateur().getService().getId().equals(
                userContext.getServiceIdentifier().getId())));
    }



    /**
     * Retourne VRAI si l'affaire contient un évènement d'orientation (Un événement d'orientation est un événement
     * appartenant à l'une des familles A06 (Orientation TTR), B03 (Evt Orientation sur infraction) ou L09 (Orientation
     * hors poursuite))
     *
     * @return VRAI si l'affaire contient un évènement d'orientation
     */
    public boolean affaireContientEvtOrientation() {

        if (this.getAffaire() != null) {

            for (Evenement evt : this.getAffaire().getEvenements()) {

                // Un événement d'orientation est un événement appartenant à l'une des familles :
                // A06 (Orientation TTR),
                // B03 (Evt Orientation sur infraction) ou
                // L09 (Orientation hors poursuite)
                EvenementType typeEvenement = evt.getEvenementType();
                if (typeEvenement.isFamilleEvenementTypeCassiopee(FamilleEvenementTypeCassiopeeEnum.ORIENTATION_TTR) ||
                    typeEvenement.isFamilleEvenementTypeCassiopee(FamilleEvenementTypeCassiopeeEnum.ORIENTATION_HORS_POURSUITE) ||
                    typeEvenement.isFamilleEvenementTypeCassiopee(FamilleEvenementTypeCassiopeeEnum.EVENEMENT_ORIENTATION_SUR_INFRACTION)) {

                    return true;

                }
            }
        }
        return false;
    }

    /**
     * Retourne VRAI si l'événement est du parquet<br>
     * <br>
     * un événement du parquet est un événement dont l'émetteur est de type PROTG ou bien un événement dont l'agent
     * émetteur appartient à un service de type PROTG
     *
     * @return VRAI si l'événement est du parquet
     */
    public boolean estDuParquet() {

        // un événement du parquet est un événement dont l'émetteur appartient à la famille PQT ou bien
        // un événement dont l'agent émetteur appartient à un service de la famille PQT

        if (lienEmetteur == null) return false;
        TypeElementStructure tesEmetteur = lienEmetteur.getTypeElementStructure();
        if (tesEmetteur != null && tesEmetteur.isFamille(FamilleTypeElementStructureEnum.PQT.getCode())) {
            return true;
        }

        if (lienEmetteur.estUnAgent() && lienEmetteur.getAgent().getService() != null) {
            TypeElementStructure tesAgentEmetteur = lienEmetteur.getAgent().getService().getTypeElementStructure();
            if (tesAgentEmetteur.isFamille(FamilleTypeElementStructureEnum.PQT.getCode())) {
                return true;
             }
        }
        return false;
    }







    /**
     * indique si l'évenement est par anticipation, non anticipé et non termine
     *
     * @param userContext
     * @return true ou false
     */
    public boolean estParAnticipationNonAnticipeEtNonTemine(UserContext userContext) {
        return getEvenementType().isFamilleEvenementTypeCassiopee(FamilleEvenementTypeCassiopeeEnum.PAR_ANTICIPATION)
                && getDateSaisieEvenement().before(getDateEvenement()) && !estAnticipe(userContext)
                && !getIndicateurTermine().booleanValue();
    }

    /**
     * @return indicateurTermine
     */
    public Boolean getIndicateurTermine() {
        if (indicateurTermine == null) {
            return Boolean.FALSE;
        }
        return indicateurTermine;
    }

    /**
     * @param indicateurTermine
     */
    public void setIndicateurTermine(Boolean indicateurTermine) {
        this.indicateurTermine = indicateurTermine;
    }

    /**
     * @return retourne vueGuidee.
     */
    public Boolean getVueGuidee() {
        return vueGuidee;
    }

    /**
     * @param vueGuidee
     *            affecte vueGuidee
     */
    public void setVueGuidee(Boolean vueGuidee) {
        this.vueGuidee = vueGuidee;
    }

    /**
     * @return infractionEvenements
     */
    public Set<EvenementInfraction> getInfractionEvenements() {
        return infractionEvenements;
    }

    /**
     * @param infractionEvenements
     */
    public void setInfractionEvenements(Set<EvenementInfraction> infractionEvenements) {
        this.infractionEvenements = infractionEvenements;
    }

    /**
     * retourne la liste des commentaires de reprise associés à l'événement
     *
     * @return la liste des commentaires de reprise associés à l'événement
     */
    public Set<CommentaireRepris> getCommentaireReprisSet() {
        return commentaireReprisSet;
    }

    /**
     * renseigne la liste des commentaires de reprise associés à l'événement
     *
     * @param commentaireReprisSet
     *            la liste des commentaires de reprise associés à l'événement
     */
    public void setCommentaireReprisSet(Set<CommentaireRepris> commentaireReprisSet) {
        this.commentaireReprisSet = commentaireReprisSet;
    }

    /**
     * retourne la liste des messages de reprise associés à l'événement
     *
     * @return la liste des commentaires de reprise associés à l'événement
     */
    public Set<MessageReprise> getMessageRepriseSet() {
        return messageRepriseSet;
    }

    /**
     * renseigne la liste des messages de reprise associés à l'événement
     *
     * @param messageRepriseSet
     *            la liste des messages de reprise associés à l'événement
     */
    public void setMessageRepriseSet(Set<MessageReprise> messageRepriseSet) {
        this.messageRepriseSet = messageRepriseSet;
    }

    /**
     * @return the depot
     */
    public Depot getDepot() {
        return depot;
    }

    /**
     * @param depot
     *            depot à affecter
     */
    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    /**
     * @return the objet
     */
    public Objet getObjet() {
        return objet;
    }

    /**
     * @param objet
     *            objet à affecter
     */
    public void setObjet(Objet objet) {
        this.objet = objet;
    }

    /**
     * assign un scelle ou objet en gardiennage
     *
     * @param obj
     */
    public void assignObjet(Objet obj) {
        setObjet(obj);
        setDepot(obj.getDepot());
    }

    /**
     * @return the pieceConviction
     */
    public PieceConviction getPieceConviction() {
        return pieceConviction;
    }

    /**
     * @param pieceConviction
     *            pieceConviction à affecter
     */
    public void setPieceConviction(PieceConviction pieceConviction) {
        this.pieceConviction = pieceConviction;
    }

    public void assignPieceConviction(PieceConviction pc) {
        setPieceConviction(pc);
        assignObjet(pc.getScelle());
    }

    /**
     * Indique si l'événement porte le motif recherché.
     *
     * @param codeMotifRecherche
     * @return true si l'événement a le motif recherché.
     */
    public boolean hasMotif(String codeMotifRecherche) {
        for (MotifEvenementType motif : this.getEvenementMotifs()) {
            if (motif.getCode().equals(codeMotifRecherche)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasMotif(MotifEvenementTypeEnum motifEvenementTypeEnum) {
        return hasMotif(motifEvenementTypeEnum.getCode());
    }



    /**
     * getter technique "Hibernate-only"
     *
     * @return
     */
    protected Set<Edition> getEditionDeclencheeEtComposeeSet() {
        return editionDeclencheeEtComposeeSet;
    }

    /**
     * setter technique "Hibernate-only"
     *
     * @param editionDeclencheeEtComposeeSet
     */
    protected void setEditionDeclencheeEtComposeeSet(Set<Edition> editionDeclencheeEtComposeeSet) {
        this.editionDeclencheeEtComposeeSet = editionDeclencheeEtComposeeSet;
    }

    /**
     * getter de la date de saisie de l'evenement correspond à la date de création
     *
     * @return
     */
    public Date getDateSaisieEvenement() {
        return dateSaisieEvenement;
    }

    /**
     * set la date de saisie de l'evenement correspod à la date de création
     *
     * @param dateSaisieEvenement
     */
    public void setDateSaisieEvenement(Date dateSaisieEvenement) {
        this.dateSaisieEvenement = dateSaisieEvenement;
    }

    /**
     * getter technique "Hibernate-only"
     *
     * @return Returns the affaireFixee.
     */
    public AffaireFixee getAffaireFixee() {
        return this.affaireFixee;
    }

    /**
     * setter technique "Hibernate-only"
     *
     * @param affaireFixee
     *            The affaireFixee to set.
     */
    public void setAffaireFixee(AffaireFixee affaireFixee) {
        this.affaireFixee = affaireFixee;
    }

    @Override
    public int compareTo(Object object) {

        Evenement myClass = (Evenement) object;

        return new CompareToBuilder().append(this.id, myClass.id).append(this.dateSaisieEvenement,
                myClass.getDateSaisieEvenement()).toComparison();
    }

    /**
     * @return the numeroParquetJonction
     */
    public String getNumeroParquetJonction() {
        return numeroParquetJonction;
    }

    /**
     * @param numeroParquetJonction
     *            the numeroParquetJonction to set
     */
    public void setNumeroParquetJonction(String numeroParquetJonction) {
        this.numeroParquetJonction = numeroParquetJonction;
    }

    /**
     * @return the codeTgiJonction
     */
    public String getCodeTgiJonction() {
        return codeTgiJonction;
    }

    /**
     * @param codeTgiJonction
     *            the codeTgiJonction to set
     */
    public void setCodeTgiJonction(String codeTgiJonction) {
        this.codeTgiJonction = codeTgiJonction;
    }

    /**
     * @return the numeroParquetCreationEvt
     */
    public String getNumeroParquetCreationEvt() {
        return numeroParquetCreationEvt;
    }

    /**
     * @param numeroParquetCreationEvt
     *            the numeroParquetCreationEvt to set
     */
    public void setNumeroParquetCreationEvt(final String numeroParquetCreationEvt) {
        this.numeroParquetCreationEvt = numeroParquetCreationEvt;
    }

    /**
     * @return the codeTgiCreationEvt
     */
    public String getCodeTgiCreationEvt() {
        return codeTgiCreationEvt;
    }

    /**
     * @param codeTgiCreationEvt
     *            the codeTgiCreationEvt to set
     */
    public void setCodeTgiCreationEvt(final String codeTgiCreationEvt) {
        this.codeTgiCreationEvt = codeTgiCreationEvt;
    }

    public Set<PersonneEvenementVolatile> getDonneesPersonneEvenementVolatile() {
        return donneesPersonneEvenementVolatile;
    }

    public void setDonneesPersonneEvenementVolatile(
            Set<PersonneEvenementVolatile> donneesPersonneEvenementVolatile) {
        this.donneesPersonneEvenementVolatile = donneesPersonneEvenementVolatile;
    }

    public Set<SupervisionEnvoiDecision> getSupervisionEnvoiDecisions() {
        return supervisionEnvoiDecisions;
    }

    public void setSupervisionEnvoiDecisions(Set<SupervisionEnvoiDecision> supervisionEnvoiDecisions) {
        this.supervisionEnvoiDecisions = supervisionEnvoiDecisions;
    }

    public Set<DetailDommageInteret> getDetailDi() {
        return detailDi;
    }

    public void setDetailDi(Set<DetailDommageInteret> detailDi) {
        this.detailDi = detailDi;
    }

    public Set<DecisionFrappeeAppel> getDecisionsFrappeesAppel() {
        return decisionsFrappeesAppel;
    }

    public void setDecisionsFrappeesAppel(Set<DecisionFrappeeAppel> decisionsFrappeesAppel) {
        this.decisionsFrappeesAppel = decisionsFrappeesAppel;
    }


    public Set<EvenementFrappeAppel> getEvenementsFrappesAppel() {
        return evenementsFrappesAppel;
    }

    public void setEvenementsFrappesAppel(Set<EvenementFrappeAppel> evenementsFrappesAppel) {
        this.evenementsFrappesAppel = evenementsFrappesAppel;
    }

     public Set<LienAppelArret> getLiensArret() {
        return liensArret;
    }

    public void setLiensArret(Set<LienAppelArret> liensArret) {
        this.liensArret = liensArret;
    }

    public Set<LienAppelArret> getLiensAppel() {
        return liensAppel;
    }

    public void setLiensAppel(Set<LienAppelArret> liensAppel) {
        this.liensAppel = liensAppel;
    }

    public Set<DateDecisionAppel> getDateDecisionAppel() {
        return dateDecisionAppel;
    }


    public void setDateDecisionAppel(Set<DateDecisionAppel> dateDecisionAppel) {
        this.dateDecisionAppel = dateDecisionAppel;
    }

    public Set<AlerteAppel> getAlerteAppel() {
        return alerteAppel;
    }

    public void setAlerteAppel(Set<AlerteAppel> alerteAppel) {
        this.alerteAppel = alerteAppel;
    }


    /**
     * Retourne la {@link EvenementFrappeAppel} correspondant à l'ID événement passé.
     *
     * @param idEvenement
     * @return
     */
    public EvenementFrappeAppel getEvenementFrappeAppel(Long idEvenement) {
        return evenementsFrappesAppel.stream()
            .filter(x -> x.getEvenementAppel().getId().equals(idEvenement))
            .findFirst().orElse(null);
    }
    /**
     * Retourne VRAI si l'evenement est le premier PVSAI de l'affaire
     * @return VRAI si l'evenement est le premier PVSAI de l'affaire
     */
    @SuppressWarnings("unlikely-arg-type")
	public boolean isFirstPVSAI() {
        if (this.getAffaire() != null) {
            for (Evenement evt : this.getAffaire().getEvenements()) {
            	String typeEvenement = evt.getEvenementType().getMnemonique();
                if (typeEvenement.equals("PVSAI")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Transient - utilisé pour editer par lot
     */
    private boolean coche;

	public boolean isCoche() {
		return coche;
	}


	public void setCoche(boolean coche) {
		this.coche = coche;
	}
    
    
}
