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
 * Les �v�nements sont :
 * <ul>
 * <li>l'ensemble des actes judiciaires �tablis par tout magistrat du si�ge et du parquet, r�pertori�s dans le code de
 * proc�dure p�nale&nbsp;;</li>
 * <li>les r�ponses faites par les personnes justiciables � ces actes judiciaires&nbsp;;</li>
 * <li>des d�cisions ext�rieures de certains partenaires.</li>
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
     * Evenement erron� que cet �v�nement remplace.
     */
    private Evenement evenementErrone;

    /**
     * Evenements rempla�ant cet �v�nement erron�.
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
     * donn�es volatile des evenements
     */
    private Set<Volatile> donneesVolatile = new HashSet<>();

    /**
     * donn�es volatile de personneEvenement PEV_PERSONNE_EVT_VOLATILE
     */
    private Set<PersonneEvenementVolatile> donneesPersonneEvenementVolatile = new HashSet<>();

    private Set<Relance> relances = new HashSet<>();

    private Set<MotifEvenementType> evenementMotifs = new HashSet<>();

    private Set<Evenement> evenementAnterieurs = new HashSet<>();

    private Set<EvtEvenementAnticipe> evenementsAnticipes = new HashSet<>();

    private Set<ECE> evenementEce = new HashSet<>();

    /**
     * Ensemble des �v�nements dont cet �v�nement est �v�nement ant�rieur.
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
     * Infractions directement li�es � cet �v�nement.
     */
    private Set<Infraction> infractions = new HashSet<>();

    /** LienEvenementAnterieurs li�s � cet �v�nement: ant�rieurs */
    private Set<LienEvenementAnterieur> lienEvenementAnterieurs = new HashSet<>();

    /** LienEvenementsAnterieurs li�s � cet �v�nement: post�rieurs */
    private Set<LienEvenementAnterieur> lienEvenementPosterieurs = new HashSet<>();

    /** Lien avec les acteurs internes li�s � cet �v�nement */
    private Set<LienEvenementActeurInterne> lienEvenementActeurInterne = new HashSet<>();

    private Set<MesureContreX> mesureContreXPortees = new HashSet<>();

    private Agent agentCreateur;

    private Service serviceCreateur;

    /**
     * Identifications parquets de l'affaire li�s � cet �v�nement. Uniquement pour les �v�nemtns acte de saisine.
     */
    private Set<IdentificationParquet> identificationParquets = new HashSet<>();

    private Set<PieceExecution> pieceExecutionSuivies = new HashSet<>();

    private Set<PeineOuMesure> mesuresSuivies = new HashSet<>();

    private Set<PeineOuMesure> mesurePPLs = new HashSet<>();

    private Set<PeineOuMesure> mesureCompletants = new HashSet<>();

    /** liste des messages de reprise associ�s � l'�v�nement */
    private Set<MessageReprise> messageRepriseSet = new LinkedHashSet<>();

    /** liste des messages de reprise associ�s � l'�v�nement */
    private Set<Demande> demandeSet = new LinkedHashSet<>();

    /** liste des taches � faire associ�es � l'�v�nement */
    private Set<TacheAFaire> tacheAFaireSet = new LinkedHashSet<>();

    /** liste des commentaires de reprise associ�s � l'�v�nement */
    private Set<CommentaireRepris> commentaireReprisSet = new LinkedHashSet<>();

    private Depot depot;

    private Objet objet;

    private PieceConviction pieceConviction;

    /**
     * r�solution bug MJ_13580 attribut technique : utilis� pour inverser la relation many-to-one entre une �dition et
     * l'evenement qui en est l'origine </br> ne corespond pas a un besoin fonctionnel : les setters/getters sont
     * declar�s protected
     */
    private Set<Edition> editionDeclencheeEtComposeeSet;

    /**
     * L'affaire fix�e associ�e
     */
    private AffaireFixee affaireFixee;

    /** Audience de plaidoirie d'un evenement de decision apres renvoi */
    private Set<Audience> listAudiencePlaidoirie;

    /**
     * Num�ro de parquet de jonction s'il l'�v�nement a �t� joint (information remplie au moment de la duplication des
     * �v�nements pendant la jonction)
     */
    private String numeroParquetJonction;

    /**
     * Code TGI de jonction s'il l'�v�nement a �t� joint (information remplie au moment de la duplication des �v�nements
     * pendant la jonction)
     */
    private String codeTgiJonction;

    /**
     * Num�ro de parquet de creation s'il l'�v�nement a �t� joint (information remplie au moment de la duplication des
     * �v�nements pendant la jonction)
     */
    private String numeroParquetCreationEvt;

    /**
     * Code TGI de creation s'il l'�v�nement a �t� joint (information remplie au moment de la duplication des �v�nements
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
     * Cr�e un �v�nement.
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
     * Renvoie l'affaire dans laquelle prend place cet �v�nement.
     *
     * @return l'affaire dans laquelle prend place cet �v�nement.
     */
    public Affaire getAffaire() {
        return affaire;
    }

    /**
     * Valorise l'affaire dans laquelle prend place cet �v�nement.
     *
     * @param affaire
     */
    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
    }

    /**
     * Renvoie les caract�ristiques de l'�v�nement.
     *
     * @return un Set<Caracteristique> contenant les caract�ristiques de l'�v�nement.
     */
    public CaracteristiquesUpdater getCaracteristiques() {
        return new CaracteristiquesUpdater(this);
    }

    /**
     * @return les caract�ristiques persistantes (= ayant r�ellement des valeurs dans la base)
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
     * Valorise les caract�ristiques de l'�v�nement.
     *
     * @param caracteristiques
     *            un Set contenant les caract�ristiques de l'�v�nement.
     */
    public void setCaracteristiques(Map<String, Caracteristique> caracteristiques) {
        this.caracteristiquesPersistante = caracteristiques;
    }

    /**
     * Renvoie le commentaire li� � l'�v�nement consid�r�.
     *
     * @return String repr�sentant le commentaire de l'�v�nement.
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Valorise le commentaire li� � l'�v�nement consid�r�.
     *
     * @param commentaire
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * Renvoie un simple texte indiquant des corrections �ventuelles dans la composition de l'�v�nement.
     *
     * @return String d�crivant les corrections dans la composition.
     */
    public String getCompoApresRectification() {
        return compoApresRectification;
    }

    /**
     * Valorise le texte indiquant des corrections �ventuelles dans la composition de l'�v�nement.
     *
     * @param compoApresRectification
     *            un String d�crivant les corrections dans la composition.
     */
    public void setCompoApresRectification(String compoApresRectification) {
        this.compoApresRectification = compoApresRectification;
    }

    /**
     * Renvoie la date de l'�v�nement.
     *
     * @return la Date de l'�v�nement.
     */
    @Override
    public Date getDateEvenement() {
        return dateEvenement;
    }

    /**
     * Valorise la date de l'�v�nement.
     *
     * @param dateEvenement
     */
    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    /**
     * Renvoie la date de purge de l'�v�nement. Il s'agit de la date � laquelle la purge c'est � dire la suppression
     * physique de l'�v�nement purg� doit avoir lieu. Cette date est renseign�e notamment par les �v�nements de gestion.
     *
     * @return la Date de purge de l'�v�nement.
     */
    public Date getDatePurge() {
        return datePurge;
    }

    /**
     * Valorise la date de purge de l'�v�nement. Il s'agit de la date � laquelle la purge c'est � dire la suppression
     * physique de l'�v�nement purg� doit avoir lieu. Cette date est renseign�e notamment par les �v�nements de gestion.
     *
     * @param datePurge
     */
    public void setDatePurge(Date datePurge) {
        this.datePurge = datePurge;
    }

    /**
     * Renvoie l'�tat actuel de l'�v�nement. L'�tat permet entre autre de d�terminer la liste des �v�nements
     * "supprim�s".
     *
     * @see #getEtatPrecedent() pour le libelle de l'�tat
     * @return l' Etat actuel de cet �v�nement.
     */
    public EtatEvenementEnum getEtatEvenementEnum() {
        return EtatEvenementEnum.resolve(codeEtat);
    }

    /**
     * Valorise l'�tat courant de l'�v�nement. L'�tat permet entre autre de d�terminer la liste des �v�nements
     * "supprim�s". Cette m�thode appelle #setEtatEvenementEnumPrecedent(Etat) pour affecter l'�tat precedent
     *
     * @see #setEtatEvenementEnumPrecedent
     * @param etatEvt
     */
    public void assignEtatEvenementEnum(EtatEvenementEnum etatEvt) {
        setEtatEvenementEnumPrecedent(this.getEtatEvenementEnum());
        this.codeEtat = etatEvt.getCode();
    }

    /**
     * Renvoie l'�tat pr�c�dent de l'�v�nement. Permet entre autre de d�terminer l'�tat de l'�v�nement avant sa
     * suppression lors de sa r�activation.
     *
     * @see #getEtatPrecedent() pour le libelle de l'�tat
     * @return l' Etat pr�c�dent de cet �v�nement
     */
    public EtatEvenementEnum getEtatEvenementEnumPrecedent() {
        return EtatEvenementEnum.resolve(codeEtatPrecedent);
    }

    /**
     * Valorise l'�tat pr�c�dent de l'�v�nement. Utilis� lors d'un changement d'�tat
     *
     * @param etat
     */
    public void setEtatEvenementEnumPrecedent(EtatEvenementEnum etat) {
        this.codeEtatPrecedent = etat.getCode();
    }

    /**
     * Renvoie l'�tat actuel de l'�v�nement. L'�tat permet entre autre de d�terminer la liste des �v�nements
     * "supprim�s".
     *
     * @return l' Etat actuel de cet �v�nement.
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
     * Renvoie l'�tat pr�c�dent de l'�v�nement. Permet entre autre de d�terminer l'�tat de l'�v�nement avant sa
     * suppression lors de sa r�activation.
     *
     * @return l' Etat pr�c�dent de cet �v�nement
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
     * Renvoie les �v�nements ant�rieurs li�s � cet �v�nement.
     *
     * @return les �v�nements ant�rieurs li�s � cet �v�nement
     */
    public Set<Evenement> getEvenementAnterieurs() {
        return evenementAnterieurs;
    }

    /**
     * D�finit les �v�nements ant�rieurs li�s � cet �v�nement. Permet de lier un �v�nement � un �v�nement qui l'a
     * pr�c�d� dans la m�me affaire comme explicit� dans les exemples ci-dessous.
     * <ul>
     * <li>lier un �v�nement d?appel � l?�v�nement jugement</li>
     * <li>lier un �v�nement d?opposition au jugement</li>
     * <li>d�finir comme ant�rieurs les �v�nements de demandes auxquelles r�pond une ordonnance.</li>
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
     * Renvoie les �v�nements correcteurs de cet �v�nement. Cette m�thode est la "r�ciproque" de getEvenementErrone().
     *
     * @return un Set contenant les �v�nements qui corrigent cet �v�nement
     */
    public Set<Evenement> getEvenementCorrecteurs() {
        return evenementCorrecteurs;
    }

    /**
     * Valorise les �v�nements correcteurs de cet �v�nement.
     *
     * @param evenementCorrecteurs
     */
    public void setEvenementCorrecteurs(Set<Evenement> evenementCorrecteurs) {
        this.evenementCorrecteurs = evenementCorrecteurs;
    }

    /**
     * Renvoie l'�v�nement erron� que cet �v�nement corrige.
     *
     * @return l' Evenement erron� corrig� par cet �v�nement.
     */
    public Evenement getEvenementErrone() {
        return evenementErrone;
    }

    /**
     * Valorise l'�v�nement erron� que cet �v�nement corrige.
     *
     * @param evenementErrone
     */
    public void setEvenementErrone(Evenement evenementErrone) {
        this.evenementErrone = evenementErrone;
    }

    /**
     * Indique si l'�v�nement est ext�rieur ou non. La propri�t� "evenementExterieur" sera positionn�e � vrai s'il
     * s'agit d'un �v�nement de l'ex�cution des peines, cr��s et donc visibles que sur la synth�se auteur apr�s
     * jugement.
     *
     * @return true si l'�v�nement est ext�rieur, false sinon.
     */
    public Boolean getEvenementExterieur() {
        return evenementExterieur;
    }

    /**
     * Valorise l'indicateur permettant de d�terminer si l'�v�nement est ext�rieur ou non. Il sera positionn� � vrai
     * s'il s'agit d'un �v�nement de l'ex�cution des peines, cr��s et donc visibles que sur la synth�se auteur apr�s
     * jugement.
     *
     * @param evenementExterieur
     */
    public void setEvenementExterieur(Boolean evenementExterieur) {
        this.evenementExterieur = evenementExterieur;
    }

    /**
     * Renvoie l'�v�nement f�d�rateur chapeautant cet �v�nement. Exemples d'�v�nements li�s par une relation
     * f�d�rateur/f�d�r�:
     * <ul>
     * <li>l?�v�nement jugement au cours duquel est pris l'�v�nement de d�tention provisoire ou de disqualification.</li>
     * <li>le PV de 1�re comparution au cours duquel est pris l'�v�nement de saisine du JLD</li>
     * </ul>
     *
     * @return l' Evenement f�d�rateur de cet �v�nement.
     */
    public Evenement getEvenementFederateur() {
        return evenementFederateur;
    }

    /**
     * Valorise l'�v�nement f�d�rateur chapeautant cet �v�nement. Exemples d'�v�nements li�s par une relation
     * f�d�rateur/f�d�r�:
     * <ul>
     * <li>l?�v�nement jugement au cours duquel est pris l'�v�nement de d�tention provisoire ou de disqualification.</li>
     * <li>le PV de 1�re comparution au cours duquel est pris l'�v�nement de saisine du JLD</li>
     * </ul>
     *
     * @param evenementFederateur
     */
    public void setEvenementFederateur(Evenement evenementFederateur) {
        this.evenementFederateur = evenementFederateur;
    }

    /**
     * Renvoie les �v�nements f�d�r�s par cet �v�nement.
     *
     * @return un Set contenant les �v�nements f�d�r�s par cet �v�nement
     * @see #getEvenementFederateur()
     */
    public Set<Evenement> getEvenementFederes() {
        return evenementFederes;
    }

    /**
     * Valorise les �v�nements f�d�r�s par cet �v�nement.
     *
     * @param evenementFederes
     * @see #setEvenementFederateur(Evenement)
     */
    public void setEvenementFederes(Set<Evenement> evenementFederes) {
        this.evenementFederes = evenementFederes;
    }

    /**
     * Renvoie les motifs de cet �v�nement.
     *
     * @return un Set contenant les motifs de cet �v�nement.
     */
    public Set<MotifEvenementType> getEvenementMotifs() {
        return evenementMotifs;
    }

    /**
     * Valorise les motifs de cet �v�nement.
     *
     * @param evenementMotifs
     */
    public void setEvenementMotifs(Set<MotifEvenementType> evenementMotifs) {
        this.evenementMotifs = evenementMotifs;
    }

    /**
     * Renvoie les �v�nements dont cet �v�nement est �v�nement ant�rieur. Cette m�thode est la "r�ciproque" de
     * #getEvenementAnterieurs()
     *
     * @see #getEvenementAnterieurs()
     * @return un Set contenant les �v�nements post�rieurs � cet �v�nement.
     */
    public Set<Evenement> getEvenementPosterieurs() {
        return evenementPosterieurs;
    }

    /**
     * Valorise les �v�nements dont cet �v�nement est �v�nement ant�rieur. Cette m�thode est la "r�ciproque" de
     * #setEvenementAnterieurs()
     *
     * @param evenementPosterieurs
     * @see #setEvenementAnterieurs(Set)
     */
    public void setEvenementPosterieurs(Set<Evenement> evenementPosterieurs) {
        this.evenementPosterieurs = evenementPosterieurs;
    }

    /**
     * Renvoie le type de cet �v�nement.
     *
     * @return l' EvenementType type de cet �v�nement.
     */
    public EvenementType getEvenementType() {
        return evenementType;
    }

    /**
     * Valorise le type de cet �v�nement.
     *
     * @param evenementType
     */
    public void setEvenementType(EvenementType evenementType) {
        this.evenementType = evenementType;
    }

    /**
     * Renvoie l'identifiant technique de l'�v�nement. Cet identifiant unique est utilis� par Hibernate, le programme et
     * la base de donn�es.
     *
     * @return un Long correspondant � l'identifiant technique de l'�v�nement.
     */
    public Long getId() {
        return id;
    }

    /**
     * Valorise l'identifiant technique de l'�v�nement. Cet identifiant unique est utilis� par Hibernate, le programme
     * et la base de donn�es.
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return InfoSuppression correspondant � cet �v�nement.
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
     * @return un Set contenant les LiensDestinataires dont chacun pointe vers un destinataire de l'�v�nement.
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
     * @return LienEmetteur vers l'�metteur de cet �v�nement.
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
     * @return Set<Relance> contenant les relances de cet �v�nement.
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
     * @return Set<EvenementRequete> contenant les requ�tes en correction de cet �v�nement, valid�es ou non.
     */
    public Set<EvenementRequete> getRequetes() {
        return requetes;
    }

    /**
     * @param requetes
     *            Set contenant les requ�tes en correction de cet �v�nement, valid�es ou non.
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
     * retourne une valeur de caract�ristique de type date par un code
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
     * permet d'affecter une date � une caracteristique de type Date
     *
     * @param caracteristiqueTypeEnum
     * @param date
     * @return true si la caracteristique existe et est bien affect�e
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
     * retourne une valeur de caract�ristique de type Duree par un code
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
     * permet d'affecter une duree � une caracteristique de type Duree
     *
     * @param caracteristiqueTypeEnum
     * @param dureeMoisJour
     * @return true si la caracteristique existe et est bien affect�e
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
     * retourne une valeur de caract�ristique de type heure par un code
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
     * permet d'affecter une heure � une caracteristique de type Heure
     *
     * @param caracteristiqueTypeEnum
     * @param time
     * @return true si la caracteristique existe et est bien affect�e
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
     * retourne une valeur de caract�ristique de type nombre par un code
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
     * permet d'affecter une nombre � une caracteristique de type Nombre
     *
     * @param caracteristiqueTypeEnum
     * @param d
     * @return true si la caracteristique existe et est bien affect�e
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
     * retourne une valeur de caract�ristique de type texte par un code
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
     * permet d'affecter une Enumeration � une caracteristique de type Enumeration
     *
     * @param caracteristiqueTypeEnum
     * @param d
     * @return true si la caracteristique existe et est bien affect�e
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
     * retourne une valeur de caract�ristique de type texte par un code
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
     * permet d'affecter une Texte � une caracteristique de type Texte
     *
     * @param caracteristiqueTypeEnum
     * @param texte
     * @return true si la caracteristique existe et est bien affect�e
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
     * retourne une valeur de caract�ristique de type pays par un code
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
     * permet d'affecter un pays � une caracteristique de type Pays
     *
     * @param caracteristiqueTypeEnum
     * @param pays
     * @return true si la caracteristique existe et est bien affect�e
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
     * retourne une valeur de caract�ristique de type element de structure par un code
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
     * permet d'affecter un ElementStructure � une caracteristique de type ElementStructure
     *
     * @param caracteristiqueTypeEnum
     * @param elementStructure
     * @return true si la caracteristique existe et est bien affect�e
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
     * retourne une valeur de caract�ristique de type Service par un code
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
     * permet d'affecter un Service � une caracteristique de type Service
     *
     * @param caracteristiqueTypeEnum
     * @param service
     * @return true si la caracteristique existe et est bien affect�e
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
     * retourne une valeur de caract�ristique de Avocat par un code
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
     * permet d'affecter un Avocat � une caracteristique de type Avocat
     *
     * @param caracteristiqueTypeEnum
     * @param avocat
     * @return true si la caracteristique existe et est bien affect�e
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
     * Retourne une valeur de caract�ristique de Personne Qualifiee par un code
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
     * Permet d'affecter ue Personne Qualifi�e � une caracteristique de type Personne Qualifi�e
     *
     * @param caracteristiqueTypeEnum
     * @param personneQualifiee
     * @return true si la caracteristique existe et est bien affect�e
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
     * Les personnes-�v�nements, sous la forme d'un Map sur l'id de la personne.
     *
     * @return Returns the personneEvenements.
     */
    public Map<Long, PersonneEvenement> getPersonneEvenements() {
        return personneEvenements;
    }

    /**
     * Les personnes-�v�nements, sous la forme d'un Map sur l'id de la personne.
     *
     * @param personneEvenements
     *            The personneEvenements to set.
     */
    public void setPersonneEvenements(Map<Long, PersonneEvenement> personneEvenements) {
        this.personneEvenements = personneEvenements;
    }

    /**
     * Renvoie les infractions directement li�es � cet �v�nement.
     *
     * @see #findAllAuteurInfractions()
     * @return un Set<Infractions> contenant les infractions directement li�es � cet �v�nement.
     */
    public Set<Infraction> getInfractions() {
        return infractions;
    }

    /**
     * Valorise les infractions directement li�es � cet �v�nement.
     *
     * @param infractions
     * @see PersonneEvenement#setAuteurInfractions(Set)
     */
    public void setInfractions(Set<Infraction> infractions) {
        this.infractions = infractions;
    }

    /**
     * Renvoie les couples auteur/infraction li�s � cet �v�nement.
     *
     * @return un Set<LienAuteurInfraction> contenant les liens auteur/infractions reli�s � cet �v�nement.
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
     * @return un Set<MesureContreX> contenant les mesures port�es par cet �v�nement.
     */
    public Set<MesureContreX> getMesureContreXPortees() {
        return mesureContreXPortees;
    }

    /**
     * Valorise l'ensemble des mesures port�es par cet �v�nement.
     *
     * @param mesurePortees
     *            un Set<MesureContreX> contenant les mesures voulues.
     */
    public void setMesureContreXPortees(Set<MesureContreX> mesurePortees) {
        this.mesureContreXPortees = mesurePortees;
    }

    /**
     * Identifications parquets de l'affaire li�s � cet �v�nement. Uniquement pour les �v�nements acte de saisine.
     *
     * @return Returns the identificationParquets.
     */
    public Set<IdentificationParquet> getIdentificationParquets() {
        return identificationParquets;
    }

    /**
     * Identifications parquets de l'affaire li�s � cet �v�nement. Uniquement pour les �v�nements acte de saisine.
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
     * @return une PeineOuMesure repr�sentant la mesure suivie.
     */
    public Set<PeineOuMesure> getMesuresSuivies() {
        return mesuresSuivies;
    }

    /**
     * @param mesures
     *            une PeineOuMesure repr�sentant la mesure suivie.
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
     * Cr�e un clone de l'�v�nement � l'int�rieur d'une affaire. Copie les propri�t�s de base ainsi que l'�metteur, le
     * destinataire, les caract�ristiques, les liens personnes concern�es, les liens auteurs-infractions concern�s. On
     * ne copie pas les liens suivants car ils sont de type one-to-many: mesures port�es, �v�nements f�d�r�s,
     * identifications parquets. Par souci de coh�rence, on ne copie pas les liens suivants: �v�nements post�rieurs. On
     * ne copie pas les relances ni les donn�es volatiles (Motivation OR...) On ne copie pas non plus les d�tails des
     * personnes-�v�nements, au-del� du lien avec la personne. On ne copie donc pas les dispositifs port�s.
     *
     * @return un nouvel �v�nement non sauv� en base avec un id null.
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
            // nouveau lien destinataire sera cr�er
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
        // On s'abstient soigneusement de mettre des �v�nements f�d�r�s (ou
        // post�rieurs)

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

            // On s'arr�te l� pour le moment, on ne pr�cise pas les propri�t�s
            // des personnes-�v�nements, en particulier on ne copie pas les
            // Mesures.
            newEvenement.getPersonneEvenements().put(newPersonneEvenement.getPersonne().getId(), newPersonneEvenement);
        }

        newEvenement.setCaracteristiques(new HashMap<String, Caracteristique>());
        /*
         * NOTE // TODO: les caract�ristiques sont difficiles � copier
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
     * retourne la juridiction d'audience d�duite des caracteristiques de cet evenement.
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
     * retourne le service d'audience d�duit des caracteristiques de cet evenement.
     *
     * @return service
     * @see fr.gouv.justice.cassiopee.service.audience.model.Audience#getRattachementService()
     * @see ServiceCaracteristiqueTypeEnum#SERV
     */
    public Service getServiceAudienceDeduit() {
        return getServiceCaracteristique(ServiceCaracteristiqueTypeEnum.SERV);
    }

    /**
     * retourne la date de l'audience d�duite des caracteristiques de cet evenement.
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
     * indicateur anticip�
     *
     * @param userContext
     * @return true si anticip� et false sinon
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
     * Retourne VRAI si l'affaire contient un �v�nement d'orientation (Un �v�nement d'orientation est un �v�nement
     * appartenant � l'une des familles A06 (Orientation TTR), B03 (Evt Orientation sur infraction) ou L09 (Orientation
     * hors poursuite))
     *
     * @return VRAI si l'affaire contient un �v�nement d'orientation
     */
    public boolean affaireContientEvtOrientation() {

        if (this.getAffaire() != null) {

            for (Evenement evt : this.getAffaire().getEvenements()) {

                // Un �v�nement d'orientation est un �v�nement appartenant � l'une des familles :
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
     * Retourne VRAI si l'�v�nement est du parquet<br>
     * <br>
     * un �v�nement du parquet est un �v�nement dont l'�metteur est de type PROTG ou bien un �v�nement dont l'agent
     * �metteur appartient � un service de type PROTG
     *
     * @return VRAI si l'�v�nement est du parquet
     */
    public boolean estDuParquet() {

        // un �v�nement du parquet est un �v�nement dont l'�metteur appartient � la famille PQT ou bien
        // un �v�nement dont l'agent �metteur appartient � un service de la famille PQT

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
     * indique si l'�venement est par anticipation, non anticip� et non termine
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
     * retourne la liste des commentaires de reprise associ�s � l'�v�nement
     *
     * @return la liste des commentaires de reprise associ�s � l'�v�nement
     */
    public Set<CommentaireRepris> getCommentaireReprisSet() {
        return commentaireReprisSet;
    }

    /**
     * renseigne la liste des commentaires de reprise associ�s � l'�v�nement
     *
     * @param commentaireReprisSet
     *            la liste des commentaires de reprise associ�s � l'�v�nement
     */
    public void setCommentaireReprisSet(Set<CommentaireRepris> commentaireReprisSet) {
        this.commentaireReprisSet = commentaireReprisSet;
    }

    /**
     * retourne la liste des messages de reprise associ�s � l'�v�nement
     *
     * @return la liste des commentaires de reprise associ�s � l'�v�nement
     */
    public Set<MessageReprise> getMessageRepriseSet() {
        return messageRepriseSet;
    }

    /**
     * renseigne la liste des messages de reprise associ�s � l'�v�nement
     *
     * @param messageRepriseSet
     *            la liste des messages de reprise associ�s � l'�v�nement
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
     *            depot � affecter
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
     *            objet � affecter
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
     *            pieceConviction � affecter
     */
    public void setPieceConviction(PieceConviction pieceConviction) {
        this.pieceConviction = pieceConviction;
    }

    public void assignPieceConviction(PieceConviction pc) {
        setPieceConviction(pc);
        assignObjet(pc.getScelle());
    }

    /**
     * Indique si l'�v�nement porte le motif recherch�.
     *
     * @param codeMotifRecherche
     * @return true si l'�v�nement a le motif recherch�.
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
     * getter de la date de saisie de l'evenement correspond � la date de cr�ation
     *
     * @return
     */
    public Date getDateSaisieEvenement() {
        return dateSaisieEvenement;
    }

    /**
     * set la date de saisie de l'evenement correspod � la date de cr�ation
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
     * Retourne la {@link EvenementFrappeAppel} correspondant � l'ID �v�nement pass�.
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
     * Retourne VRAI si l'eve�nement est le premier PVSAI de l'affaire
     * @return VRAI si l'eve�nement est le premier PVSAI de l'affaire
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
     * Transient - utilis� pour editer par lot
     */
    private boolean coche;

	public boolean isCoche() {
		return coche;
	}


	public void setCoche(boolean coche) {
		this.coche = coche;
	}
    
    
}
