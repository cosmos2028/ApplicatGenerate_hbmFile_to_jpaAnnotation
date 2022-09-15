package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.CompareToBuilder;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.common.exception.TechnicalException;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.exterieur.model.DispositifExterieur;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.PersonneEvenement;
import fr.gouv.justice.cassiopee.processus.execution.model.PieceExecution;
import fr.gouv.justice.cassiopee.processus.tdr.model.CommentaireRepris;
import fr.gouv.justice.cassiopee.processus.tdr.model.MessageReprise;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.MotifPeineOuMesure;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.PeineOuMesureType;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.TitrePeineOuMesure;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.TypeCaractPeineMesure;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.TypeCaracteristiquePossible;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.service.enumeration.TypeCaractPeineMesureEnum;

/**
 * Classe "outil", r�le vers Peine ou mesure utilis�e pour d�crire pr�cis�ment le dispositif p�nal. Concerne les peines
 * sans motifs et sans caract�ristiques.
 */
@SuppressWarnings("rawtypes")
public abstract class PeineOuMesure implements java.io.Serializable, Comparable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * Le rattachement d'un peine ou mesure � un �v�nement est r�alis� � la cr�ation de l'�v�nement. Une mesure
     * (concernant une personne) de premier niveau est forc�ment port�e par un Personne - Ev�nement
     */
    private PersonneEvenement personneEvenement;

    /**
     * Ev�nement portant la mesure, dans le cadre des mesures non li�es � une PERSONNE (mesure dite de l'affaire ou de
     * l'infraction dans le mod�le fonctionel EVT). .
     */
    private Evenement evenement;

    /**
     * peineOuMesureMere : Peine ou mesure m�re de la peine ou mesure courante. Cet attribut permet de cha�ner les
     * peines ou mesures filles qui ne sont pas rattach�es � l'�v�nement ou au dispositif.
     */
    private PeineOuMesure peineOuMesureMere;

    /**
     * peineOuMesurePr�cedente : Peine ou mesure pr�c�dente de la peine ou mesure. Est utilis�e dans le cadre des
     * �v�nements de modification d'obligations de CJ.
     */
    private PeineOuMesure peineOuMesurePrecedente;

    private EtatPeineOuMesure etatPeineOuMesure;

    private EtatPeineOuMesure etatPrecedent;

    private TitrePeineOuMesure titrePeineOuMesure;

    private Integer numeroOrdre;

    private Boolean indicateurGrise;

    private Boolean indicateurDIE;

    /**
     * DISCRIMINANT : permet de distinguer de regouper ou diff�rencier les peines et mesures pour les traitements. Les
     * valeurs possibles sont : Des classes sont d�finies gr�ce � ce discriminant.
     */
    private String discriminant;

    /**
     * Les caract�ristiques d�finies pour une peine ou mesure type, peuvent (ou doivent si l'indicateur de pr�sence vaut oui) �tre utilis�s
     * dans le cadre d'une affaire. Ils sont alors valoris�s. Rappel : il s'agit de pr�cisions impos�es par le l�gislateur et relatives � la
     * dur�e, au d�lai d�ex�cution, au lieu d�ex�cution ou au montant de la peine ou mesure. Ainsi, une peine ou mesure pr�sente un ensemble
     * de caract�ristiques. Celles-ci apparaissent dans le m�me ordre que les caract�ristiques types de la peine ou mesure type associ�e.
     */
    private java.util.Set<ValeurCaracteristique> valeursCaracteristiques = new HashSet<>();

    /**
     * Les liens entre Peines ou mesures doivent �tre d�roul�s, en utilisant la valeur du type de lien, pour obtenir une description
     * coh�rente des peines et des mesures appliqu�es � l'Auteur/Partie civile. Exemples de peine ou mesure li�e : modalit� d'ex�cution
     * (types de sursis, etc.), modalit� d�application (am�nagement de peine, etc.). Les liens entre peines et mesures sont construits �
     * partir des liens entre peines et mesures types. Ainsi, deux peines ou mesures li�es sont obligatoirement associ�es � des peines ou
     * mesures types li�es entre elles.
     */
    private java.util.Set<PeineOuMesure> peinesOuMesuresFilles = new HashSet<>();

    /**
     * l'ensemble des peines (suivantes) li�e � cette peine (this) qui est consid�r�e comme precedente
     */
    private Set<PeineOuMesure> peinesOuMesuresSuivantes = new HashSet<>();

    /**
     * Ev�nement cr�� par un agent charg� du suivi de la mesure, afin de suivre la mise � ex�cution de la mesure ou
     * lorsque les premi�res informations relatives au d�roulement de la mesure sont connues. Les classes concern�es par
     * cette classe r�le dans le composant EVT sont : EVENEMENT DE SUIVI DECISION MODIFIANT OU SUBSTITUANT MESURE
     */
    private Set<Evenement> evenementsSuiviPeineOuMesure = new HashSet<>();

    /**
     * Motifs d�finis pour la peine ou mesure.
     */
    private Set<MotifPeineOuMesure> motifs = new HashSet<>();

    /**
     * Dans l'�tat du droit actuel, une peine ou mesure est reli�e g�n�ralement � un groupe d'infraction, et peut �tre
     * reli�e � 2 groupes (cas des peines compl�mentaires du groupe des crimes et d�lits non sp�cifiques qui peuvent
     * �tre applicables aux contraventions (et donc li�es au groupe des contraventions)
     */
    private Set<Groupe> groupes = new HashSet<>();

    /**
     * Tiers payeurs
     */
    private Set<Personne> tiersPayeurs = new HashSet<>();

    /** dans le cas ou la peine est li�e � la personneEvenementInfraction */
    private PersonneEvenementInfraction personneEvenementInfraction;

    /**
     * dans le cas ou la peine est li�e � l'evenement infraction: pas de personneEvenement(c'est le cas des peines
     * contre X li�es � un evt et une infraction: infraction sans auteur)
     */
    private EvenementInfraction evenementInfraction;

    private String commentaire;

    private PeineOuMesureType peineOuMesureType;

    /** evenement completant la mesure */
    private Evenement evenementCompletantMesure;

    private Set<PieceExecution> pieceExecutionPortees = new HashSet<>();

    /**
     * les evenements li�s � la peine de nature PPL
     */
    private Set<Evenement> evenementsLiesPPL = new HashSet<>();

    /**
     * Dispositifs ext�rieurs li�s � cette peine/mesure.
     */
    private Set<DispositifExterieur> dispositifExterieurs = new HashSet<>();

    /** liste des messages de reprise associ�s � l'�v�nement */
    private Set<MessageReprise> messageRepriseSet = new LinkedHashSet<>();

    /** liste des commentaires de reprise associ�s � l'�v�nement */
    private Set<CommentaireRepris> commentaireReprisSet = new LinkedHashSet<>();

    private Set<Personne> lienPemPersonne = new HashSet<>();

    private Date dateReparationsCiviles;

    private SuiviPeineOuMesure suiviPeineOuMesure;

    /**
     * {@link PeineOuMesure} frapp�es par un {@link Evenement} APPEL
     */
    private Set<PeineOuMesureFrappeeAppel> peinesOuMesuresFrappeesAppel;

    /**
     *
     */
    public PeineOuMesure() {
        // nothing to do
    }

    /**
     * @return Long
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return PersonneEvenement
     */
    public PersonneEvenement getPersonneEvenement() {
        return this.personneEvenement;
    }

    /**
     * @param personneEvenement
     */
    public void setPersonneEvenement(PersonneEvenement personneEvenement) {
        this.personneEvenement = personneEvenement;
    }

    /**
     * @return PeineOuMesure
     */
    public PeineOuMesure getPeineOuMesureMere() {
        return this.peineOuMesureMere;
    }

    /**
     * @return PeineOuMesure
     */
    public PeineOuMesure getPeineOuMesureRoot() {
        PeineOuMesure pemRoot = this;
        PeineOuMesure pemMere;
        while (true) {
            pemMere = pemRoot.getPeineOuMesureMere();
            if (pemMere == null) {
                break;
            }
            pemRoot = pemMere;
        }
        return pemRoot;
    }


    /**
     * @param peineOuMesure
     */
    public void setPeineOuMesureMere(PeineOuMesure peineOuMesure) {
        this.peineOuMesureMere = peineOuMesure;
    }

    /**
     * @return Evenement
     */
    public Evenement getEvenement() {
        return this.evenement;
    }

    /**
     * @return EtatPeineOuMesure
     */
    public EtatPeineOuMesure getEtatPeineOuMesure() {
        return this.etatPeineOuMesure;
    }

    /**
     * @param evenement
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    /**
     * @param etatPeineOuMesure
     */
    public void setEtatPeineOuMesure(EtatPeineOuMesure etatPeineOuMesure) {
        this.etatPeineOuMesure = etatPeineOuMesure;
    }

    /**
     * @return EtatPeineOuMesure
     */
    public EtatPeineOuMesure getEtatPrecedent() {
        return this.etatPrecedent;
    }

    /**
     * @param etatPrecedent
     */
    public void setEtatPrecedent(EtatPeineOuMesure etatPrecedent) {
        this.etatPrecedent = etatPrecedent;
    }

    /**
     * @return TitrePeineOuMesure
     */
    public TitrePeineOuMesure getTitrePeineOuMesure() {
        return this.titrePeineOuMesure;
    }

    /**
     * @param titrePeineOuMesure
     */
    public void setTitrePeineOuMesure(TitrePeineOuMesure titrePeineOuMesure) {
        this.titrePeineOuMesure = titrePeineOuMesure;
    }

    /**
     * TODO / NOTE cvb : semble ne pas toujours fonctionner avec Hibernate !!!
     *
     * @return String
     */
    public String getDiscriminant() {
        return this.discriminant;
    }

    /**
     * @param discriminant
     */
    public void setDiscriminant(String discriminant) {
        this.discriminant = discriminant;
    }

    /**
     * @return Integer
     */
    public Integer getNumeroOrdre() {
        return this.numeroOrdre;
    }

    /**
     * @param numeroOrdre
     */
    public void setNumeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    /**
     * @return Set<ValeurCaracteristique>
     */
    public Set<ValeurCaracteristique> getValeursCaracteristiques() {
        return this.valeursCaracteristiques;
    }

    /**
     * @param valeursCaracteristiques
     */
    public void setValeursCaracteristiques(Set<ValeurCaracteristique> valeursCaracteristiques) {
        this.valeursCaracteristiques = valeursCaracteristiques;
    }

    /**
     * @return Set<Evenement>
     */
    public Set<Evenement> getEvenementsSuiviPeineOuMesure() {
        return this.evenementsSuiviPeineOuMesure;
    }

    /**
     * @return Set<PeineOuMesure>
     */
    public Set<PeineOuMesure> getPeinesOuMesuresFilles() {
        return this.peinesOuMesuresFilles;
    }

    /**
     * @param peineOuMesures
     */
    public void setPeinesOuMesuresFilles(Set<PeineOuMesure> peineOuMesures) {
        this.peinesOuMesuresFilles = peineOuMesures;
    }

    /**
     * @param evenementsSuiviPeineOuMesure
     */
    public void setEvenementsSuiviPeineOuMesure(Set<Evenement> evenementsSuiviPeineOuMesure) {
        this.evenementsSuiviPeineOuMesure = evenementsSuiviPeineOuMesure;
    }

    /**
     * @return Set<MotifPeineOuMesure>
     */
    public Set<MotifPeineOuMesure> getMotifs() {
        return motifs;
    }

    /**
     * @param motifs
     */
    public void setMotifs(Set<MotifPeineOuMesure> motifs) {
        this.motifs = motifs;
    }

    /**
     * @return PeineOuMesureType
     */
    public PeineOuMesureType getPeineOuMesureType() {
        return peineOuMesureType;
    }

    /**
     * @param peineOuMesureType
     */
    public void setPeineOuMesureType(PeineOuMesureType peineOuMesureType) {
        this.peineOuMesureType = peineOuMesureType;
    }

    /**
     * @return groupes
     */
    public Set<Groupe> getGroupes() {
        return groupes;
    }

    /**
     * @param groupes
     */
    public void setGroupes(Set<Groupe> groupes) {
        this.groupes = groupes;
    }

    /**
     * retourne le type de la peine ou mesure
     *
     * @return type de la peine
     */
    public abstract TypePeineOuMesureEnum getTypePeineOuMesureEnum();

    /**
     * @return indicateurGrise
     */
    public Boolean getIndicateurGrise() {
        return indicateurGrise;
    }

    /**
     * @param indicateurGrise
     */
    public void setIndicateurGrise(Boolean indicateurGrise) {
        this.indicateurGrise = indicateurGrise;
    }

    /**
     * ajouter la peine pass�e en param�tre � la collection des peines fille de la peine en question
     *
     * @param peineOuMesureFille
     */
    public void addFille(PeineOuMesure peineOuMesureFille) {
        this.getPeinesOuMesuresFilles().add(peineOuMesureFille);
        peineOuMesureFille.peineOuMesureMere = this;
    }

    /**
     * @return indicateurDIE
     */
    public Boolean getIndicateurDIE() {
        return indicateurDIE;
    }

    /**
     * @return personneEvenementInfraction
     */
    public PersonneEvenementInfraction getPersonneEvenementInfraction() {
        return personneEvenementInfraction;
    }

    /**
     * @return commentaire
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * @param commentaire
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * @param personneEvenementInfraction
     */
    public void setPersonneEvenementInfraction(PersonneEvenementInfraction personneEvenementInfraction) {
        this.personneEvenementInfraction = personneEvenementInfraction;
    }

    /**
     * @param indicateurDIE
     */
    public void setIndicateurDIE(Boolean indicateurDIE) {
        this.indicateurDIE = indicateurDIE;
    }

    /**
     * @return peineOuMesurePrecedente
     */
    public PeineOuMesure getPeineOuMesurePrecedente() {
        return peineOuMesurePrecedente;
    }

    /**
     * @param peineOuMesurePrecedente
     */
    public void setPeineOuMesurePrecedente(PeineOuMesure peineOuMesurePrecedente) {
        this.peineOuMesurePrecedente = peineOuMesurePrecedente;
    }

    /**
     * @return evenementCompletantMesure
     */
    public Evenement getEvenementCompletantMesure() {
        return evenementCompletantMesure;
    }

    /**
     * @param evenementCompletantMesure
     */
    public void setEvenementCompletantMesure(Evenement evenementCompletantMesure) {
        this.evenementCompletantMesure = evenementCompletantMesure;
    }

    /**
     * @return retourne pieceExecutionPortees.
     */
    public Set<PieceExecution> getPieceExecutionPortees() {
        return pieceExecutionPortees;
    }

    /**
     * @param pieceExecutionPortees
     *            affecte pieceExecutionPortees
     */
    public void setPieceExecutionPortees(Set<PieceExecution> pieceExecutionPortees) {
        this.pieceExecutionPortees = pieceExecutionPortees;
    }

    /**
     * @return evenementsLiesPPL
     */
    public Set<Evenement> getEvenementsLiesPPL() {
        return evenementsLiesPPL;
    }

    /**
     * @param evenementsLiesPPL
     */
    public void setEvenementsLiesPPL(Set<Evenement> evenementsLiesPPL) {
        this.evenementsLiesPPL = evenementsLiesPPL;
    }

    /**
     * ajouter un groupe � la peine en cours
     *
     * @param groupeLiePeine
     */
    public void addGroupe(Groupe groupeLiePeine) {
        this.getGroupes().add(groupeLiePeine);
        groupeLiePeine.getPeineOuMesures().add(this);
    }

    /**
     * rechercher une peineOuMesure fille par code mesure et type peine (parmi les filles de la peine m�re)
     *
     * @param codeMesureRecherchee
     * @param typePeineOuMesureEnumRecherche
     * @return PeineOuMesure
     */
    public PeineOuMesure rechercherPeineOuMesureFilleByCodeMesureEtType(Long codeMesureRecherchee,
            TypePeineOuMesureEnum typePeineOuMesureEnumRecherche) {
        for (PeineOuMesure peineOuMesure : this.peinesOuMesuresFilles) {
            if (peineOuMesure.getTypePeineOuMesureEnum().getType().equals(typePeineOuMesureEnumRecherche.getType())
                    && peineOuMesure.getPeineOuMesureType().getCodeMesure().equals(codeMesureRecherchee)) {
                return peineOuMesure;
            }
        }
        return null;
    }

    /**
     * rechercher la peineOuMesure fille par type de peine (parmi les peines filles)
     *
     * @param typePeineOuMesureEnumRecherche
     * @return peineOuMesure (ou null si c pas trouv�e)
     */
    public PeineOuMesure rechercherPeineOuMesureFilleByType(TypePeineOuMesureEnum typePeineOuMesureEnumRecherche) {
        for (PeineOuMesure peineOuMesure : this.peinesOuMesuresFilles) {
            if (peineOuMesure.getTypePeineOuMesureEnum().getType().equals(typePeineOuMesureEnumRecherche.getType())) {
                return peineOuMesure;
            }
        }
        return null;
    }

    /**
     * retourne vrai si cette peine est une obligation (obligationSurPeine)
     *
     * @return boolean
     */
    public boolean isObligation() {
        return TypePeineOuMesureEnum.OBLIGATION_SUR_PEINE == this.getTypePeineOuMesureEnum()
                || TypePeineOuMesureEnum.OBLIGATION_SUR_MODALITE_EXECUTION_OU_APPLICATION_OU_PERSONNALISATION == this.getTypePeineOuMesureEnum()
                || TypePeineOuMesureEnum.OBLIGATION_SUR_OBLIGATION == this.getTypePeineOuMesureEnum();
    }

    /**
    *
    * @return Vrai si la mesure a un sous-type qui correspond � une obligation
    */
   public boolean isObligationSurPeine() {
       return this.getPeineOuMesureType() != null && this.getPeineOuMesureType().getSousTypeAppartenance() != null && this.getPeineOuMesureType().getSousTypeAppartenance().getSousType() != null && 
    		   (this.getPeineOuMesureType().getSousTypeAppartenance().getSousType() >= 81 
    		   && this.getPeineOuMesureType().getSousTypeAppartenance().getSousType() <= 89);
          
//       else if (this.getTypePeineOuMesureEnum() != null && this.getTypePeineOuMesureEnum() == TypePeineOuMesureEnum.OBLIGATION_SUR_PEINE){
//           return true;
//       }
//       return false;
   }
   
    /**
     * Indique si cette peine ou mesure est une ex�cution provisoire.
     *
     * @return true s'il s'agit d'une ex�cution provisoire (qu'il s'agitsse d'une ex�cution provisoire sur mesure
     *         mineur, sur modalit� ou sur peine) ; false autrement.
     */
    public boolean isExecutionProvisoire() {
        return TypePeineOuMesureEnum.EXECUTION_PROVISOIRE_MESURE_MINEUR.equals(this.getTypePeineOuMesureEnum())
                || TypePeineOuMesureEnum.EXECUTION_PROVISOIRE_SUR_MODALITE.equals(this.getTypePeineOuMesureEnum())
                || TypePeineOuMesureEnum.EXECUTION_PROVISOIRE_SUR_PEINE.equals(this.getTypePeineOuMesureEnum());
    }

    /**
     * compter le nombre d'obligation li� � la peine (ce sont les peines filles li�es � la peine m�re et qui sont de
     * type obligationSurPeine sur
     *
     * @return le nombre d'obligation de la peine en cours
     */
    public int countObligation() {
        int count = 0;
        for (PeineOuMesure peineOuMesure : this.getPeinesOuMesuresFilles()) {
            // voir si cette peine est une obligation
            if (peineOuMesure.isObligation()) {
                count++;
            }
        }
        return count;

    }

    /**
     * retourne une map (cl�: codeTypeCaract, valeur: ValeurCaracteristique) de la peine
     *
     * @return map de valeur caracteristique de la peine
     */
    public Map<String, ValeurCaracteristique> getMapValeursCaracteristiques() {
        Map<String, ValeurCaracteristique> mapValeursCaractByCode = new HashMap<>();
        for (ValeurCaracteristique valeurCaracteristique : this.getValeursCaracteristiques()) {
            TypeCaractPeineMesure typeCaractPeineMesure = valeurCaracteristique.getTypeCaractPeineMesure();
            mapValeursCaractByCode.put(typeCaractPeineMesure.getCode(), valeurCaracteristique);
        }
        return mapValeursCaractByCode;
    }

    /**
     * retounre la valeur caracteristique qui correspond au code type caracteristique demand�
     *
     * @param typeCaractPeineMesureEnum
     * @return la valeur caracteristique qui correpond au code (null si pas trouv�e)
     */
    public ValeurCaracteristique getValeurCaracteristiqueByCode(TypeCaractPeineMesureEnum typeCaractPeineMesureEnum) {
        return getMapValeursCaracteristiques().get(typeCaractPeineMesureEnum.getCode());
    }

    /**
     * Retourne vrai si la valeur caract�ristique correspondant au code type caract�ristique est renseign�e avec une
     * valeur non nulle, faux sinon.
     *
     * @param typeCaractPeineMesureEnum
     * @return true si la caract�ristique est renseign�e avec une valeur non nulle, faux sinon.
     */
    public boolean hasValeurCaracteristiqueByCode(TypeCaractPeineMesureEnum typeCaractPeineMesureEnum) {
        return getMapValeursCaracteristiques().get(typeCaractPeineMesureEnum.getCode()) != null
                && getMapValeursCaracteristiques().get(typeCaractPeineMesureEnum.getCode()).getValue() != null;
    }

    /**
     * verifie si le titre pass� en param�tre est �gale au titre de la peine en cours
     *
     * @param codeTitre
     * @return true si le titre de la peine est = au titre pass� en param�tre
     */
    public boolean isTitrePeineEquals(String codeTitre) {
        TitrePeineOuMesure titrePeineOuMesure1 = this.getTitrePeineOuMesure();
        return titrePeineOuMesure1 != null && titrePeineOuMesure1.getCode().equals(codeTitre);
    }

    /**
     * Retourne les dispositifs ext�rieurs li�s � cette peine ou mesure.
     *
     * @return Returns the dispositifExterieurs.
     */
    public Set<DispositifExterieur> getDispositifExterieurs() {
        return dispositifExterieurs;
    }

    /**
     * Valorise les Dispositifs Ext�rieurs li�s � cette peine ou mesure.
     *
     * @param dispositifExterieurs
     *            The dispositifExterieurs to set.
     */
    public void setDispositifExterieurs(Set<DispositifExterieur> dispositifExterieurs) {
        this.dispositifExterieurs = dispositifExterieurs;
    }

    /**
     * @return ensemble de personne
     */
    public Set<Personne> getTiersPayeurs() {
        return tiersPayeurs;
    }

    /**
     * @param tiersPayeurs
     */
    public void setTiersPayeurs(Set<Personne> tiersPayeurs) {
        this.tiersPayeurs = tiersPayeurs;
    }

    /**
     * indique si peut avoir une date de fin
     *
     * @return true ou false
     */
    public boolean hasDateFin() {
        for (TypeCaracteristiquePossible t : getPeineOuMesureType().getTypesCaracteristiquesPossibles()) {
            if (TypeCaractPeineMesureEnum.DATE_FIN.is(t.getTypeCaractPeineMesure().getCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * retourne la date de fin
     *
     * @return date de fin
     */
    public Date getDateFin() {
        ValeurCaracteristique valeurCaracteristiqueByCode = getValeurCaracteristiqueByCode(TypeCaractPeineMesureEnum.DATE_FIN);
        if (valeurCaracteristiqueByCode != null) {
            return (Date) valeurCaracteristiqueByCode.getValue();
        }
        return null;
    }

    /**
     * @return la caract. date de debut si elle existe
     */
    public Date getDateDebut() {
        ValeurCaracteristique valeurCaracteristiqueByCode = getValeurCaracteristiqueByCode(TypeCaractPeineMesureEnum.DATE_DEBUT);
        if (valeurCaracteristiqueByCode != null) {
            return (Date) valeurCaracteristiqueByCode.getValue();
        }
        return null;
    }

    /**
     * affecte la date de fin
     *
     * @param date
     */
    @SuppressWarnings("unchecked")
    public void setDateFin(Date date) {
        ValeurCaracteristique valeurCaracteristiqueByCode = getValeurCaracteristiqueByCode(TypeCaractPeineMesureEnum.DATE_FIN);
        if (valeurCaracteristiqueByCode != null) {
            valeurCaracteristiqueByCode.setValue(date);
        }

    }

    /**
     * @return l'evenementInfraction
     */
    public EvenementInfraction getEvenementInfraction() {
        return evenementInfraction;
    }

    /**
     * @param evenementInfraction
     */
    public void setEvenementInfraction(EvenementInfraction evenementInfraction) {
        this.evenementInfraction = evenementInfraction;
    }

    /**
     * @return les peines suivantes (les peines qui ont this comme peine precedente)
     */
    public Set<PeineOuMesure> getPeinesOuMesuresSuivantes() {
        return peinesOuMesuresSuivantes;
    }

    /**
     * @param peinesOuMesuresSuivantes
     */
    public void setPeinesOuMesuresSuivantes(Set<PeineOuMesure> peinesOuMesuresSuivantes) {
        this.peinesOuMesuresSuivantes = peinesOuMesuresSuivantes;
    }

    /**
     * Ordre naturel
     */
    @Override
    public int compareTo(Object object) {
        PeineOuMesure myClass = (PeineOuMesure) object;
        return new CompareToBuilder().append(this.id, myClass.getId()).append(this.discriminant, myClass.getDiscriminant())
                .append(this.peineOuMesureType.getCodeMesure(), myClass.getPeineOuMesureType().getCodeMesure())
                .toComparison();
    }

    /**
     * permet de cr�er une nouvelle peine en recopiant de la peine actuelle,la peine type, l'etat, les caract., les
     * motifs, le commentaire, lien peine precedente). Le meme traitement est appliqu� si la peine poss�de des filles
     *
     * @return une peine dupliqu�e
     */
    /*
     * TODO pas certain que ce soit blind� � 100%. Nettoyer/refactoriser avec m�thode dupliquerMesureCR...(): utiliser
     * m�thode commune clone() qui recopie propri�t�s de base mais aucune association
     */
    public PeineOuMesure dupliquerPeineOuMesure() {
        PeineOuMesure peineOuMesureDuplicate = this.dupliquerPeineOuMesureSansFilles();

        // les liens mere filles
        for (PeineOuMesure peineOuMesureFille : this.getPeinesOuMesuresFilles()) {
            /*
             * dupliquer aussi les peines filles(en appliquant le meme traitement:traitement recursive) et les rattacher
             * � la peine mere dupliqu�e
             */
            peineOuMesureDuplicate.addFille(peineOuMesureFille.dupliquerPeineOuMesure());
        }
        return peineOuMesureDuplicate;
    }

    /**
     * Permet de creer une noubelle peine en recopiant les valeurs de la peine actuelle sans recopier ses filles
     * @return
     */
    public PeineOuMesure dupliquerPeineOuMesureSansFilles(){
        PeineOuMesure peineOuMesureDuplicate;
        try {
            peineOuMesureDuplicate = this.getClass().newInstance();
        } catch (InstantiationException e) {
            throw new TechnicalException("Instantiation Exception", e);
        } catch (IllegalAccessException e) {
            throw new TechnicalException("IllegalAccessException", e);
        }

        fillPeineOuMesureDuplicate(peineOuMesureDuplicate);

        return peineOuMesureDuplicate;
    }

    public PeineOuMesure dupliquerEnAutreTypeSansFilles(TitrePeineOuMesure titre){
        PeineOuMesure peineOuMesureDuplicate = new MesureMineur();
        fillPeineOuMesureDuplicate(peineOuMesureDuplicate);
        peineOuMesureDuplicate.setTitrePeineOuMesure(titre);
        return peineOuMesureDuplicate;
    }
    
    private void fillPeineOuMesureDuplicate(PeineOuMesure peineOuMesureDuplicate) {
        peineOuMesureDuplicate.setId(null);
        // la peine type
        peineOuMesureDuplicate.setPeineOuMesureType(this.getPeineOuMesureType());

        // etat : il ne peut pas �tre null
        peineOuMesureDuplicate.setEtatPeineOuMesure(this.getEtatPeineOuMesure());

        // dupliquer les valeurs caract.
        dupliquerCaract(peineOuMesureDuplicate);

        // les motifs
        dupliquerMotifs(peineOuMesureDuplicate);

        // par defaut la peine est li�e � personne evenement
        peineOuMesureDuplicate.setPersonneEvenement(this.getPersonneEvenement());
        // commentaire
        peineOuMesureDuplicate.setCommentaire(this.getCommentaire());

        // recr�er le lien avec la peine ou mesure precedente
        peineOuMesureDuplicate.setPeineOuMesurePrecedente(this.getPeineOuMesurePrecedente());
    }

    /**
     * Cr�e une nouvelle peine, copie de celle-ci.
     *
     * @return une peine dupliqu�e
     */
    public PeineOuMesure dupliquerMesureCRetCivilesRL() {
        PeineOuMesure newPeineOuMesure = this.dupliquerPeineOuMesure();
        // On supprime tous les liens Personne, PersonneEvenement,
        // PersonneEvenementInfraction, Peine m�re/filles, Peine
        // pr�c�dente/suivantes, Groupes
        newPeineOuMesure.setPersonneEvenement(null);
        newPeineOuMesure.setPersonneEvenementInfraction(null);
        newPeineOuMesure.setEvenement(null);
        newPeineOuMesure.setPeineOuMesureMere(null);
        newPeineOuMesure.setPeinesOuMesuresFilles(new HashSet<PeineOuMesure>());
        newPeineOuMesure.setPeineOuMesurePrecedente(null);
        newPeineOuMesure.setPeinesOuMesuresSuivantes(new HashSet<PeineOuMesure>());
        newPeineOuMesure.setEvenementInfraction(null);
        newPeineOuMesure.setEvenementCompletantMesure(null);

        newPeineOuMesure.setGroupes(new HashSet<Groupe>());

        newPeineOuMesure.setTiersPayeurs(new HashSet<Personne>());

        // Titre: une Codification
        newPeineOuMesure.setTitrePeineOuMesure(this.getTitrePeineOuMesure());

        // Ordre: � valoriser ult�rieurement avec
        // PeineOuMesureFinder.findMaxNumeroOrdreByPersEvt(...)+1
        newPeineOuMesure.setNumeroOrdre(null);

        newPeineOuMesure.setIndicateurGrise(this.getIndicateurGrise());

        // TODO �tudier risque d'incoh�rence si on ne recopie pas les DIE li�s ?
        newPeineOuMesure.setIndicateurDIE(this.getIndicateurDIE());

        newPeineOuMesure.setEvenementsSuiviPeineOuMesure(new HashSet<Evenement>());
        newPeineOuMesure.setLienPemPersonne(new HashSet<Personne>(this.getLienPemPersonne()));

        return newPeineOuMesure;
    }

    /**
     * duplique les motifs peine ou mesure(creer les liens motif et peine )
     *
     * @param peineOuMesureDuplicate
     */
    private void dupliquerMotifs(PeineOuMesure peineOuMesureDuplicate) {
        for (MotifPeineOuMesure motifPeineOuMesure : this.getMotifs()) {
            peineOuMesureDuplicate.motifs.add(motifPeineOuMesure);
        }
    }

    /**
     * dupliquer les caract.
     *
     * @param peineOuMesureDuplicate
     */
    private void dupliquerCaract(PeineOuMesure peineOuMesureDuplicate) {
        for (ValeurCaracteristique valeurCaracteristique : this.getValeursCaracteristiques()) {
            /*
             * dupliquer la caract. (il y a besoin de la peineOuMesureDuplicate pour construire une
             * valeurCaracteristiqueId de la nouvelle caract. cr�ee)
             */
            ValeurCaracteristique valeurCaractDuplicate = valeurCaracteristique.dupliquerCaract(peineOuMesureDuplicate);
            // ajouter la caract. dupliqu�e � la liste des caract.
            peineOuMesureDuplicate.valeursCaracteristiques.add(valeurCaractDuplicate);

        }
    }

    /**
     * indique si une mesure est supprimable
     *
     * @return true ou false
     */
    @RegleDeGestion({ RG.PEMM010, RG.PEMM012 })
    public boolean estSupprimable() {
        if (!getEvenementsSuiviPeineOuMesure().isEmpty() || !getPeinesOuMesuresSuivantes().isEmpty()
                || !getPieceExecutionPortees().isEmpty()) {
            return false;
        }

        for (PeineOuMesure mesure : getPeinesOuMesuresFilles()) {
            if (!mesure.estSupprimable()) {
                return false;
            }
        }
        return true;
    }

    /**
     * retourne la liste des commentaires de reprise associ�s la peine ou mesure
     *
     * @return la liste des commentaires de reprise associ�s la peine ou mesure
     */
    public Set<CommentaireRepris> getCommentaireReprisSet() {
        return commentaireReprisSet;
    }

    /**
     * renseigne la liste des commentaires de reprise associ�s � la peine ou mesure
     *
     * @param commentaireReprisSet
     *            la liste des commentaires de reprise associ�s � la peine ou mesure
     */
    public void setCommentaireReprisSet(Set<CommentaireRepris> commentaireReprisSet) {
        this.commentaireReprisSet = commentaireReprisSet;
    }

    /**
     * retourne la liste des messages de reprise associ�s � la peine ou mesure
     *
     * @return la liste des messages de reprise associ�s � la peine ou mesure
     */
    public Set<MessageReprise> getMessageRepriseSet() {
        return messageRepriseSet;
    }

    /**
     * renseigne la liste des messages de reprise associ�s � la peine ou mesure
     *
     * @param messageRepriseSet
     *            la liste des messages de reprise associ�s � la peine ou mesure
     */
    public void setMessageRepriseSet(Set<MessageReprise> messageRepriseSet) {
        this.messageRepriseSet = messageRepriseSet;
    }


    public Set<Personne> getLienPemPersonne() {
        return lienPemPersonne;
    }

    public void setLienPemPersonne(Set<Personne> lienPemPersonne) {
        this.lienPemPersonne = lienPemPersonne;
    }

    public Date getDateReparationsCiviles() {
        return dateReparationsCiviles;
    }

    public void setDateReparationsCiviles(Date dateReparationsCiviles) {
        this.dateReparationsCiviles = dateReparationsCiviles;
    }

    public SuiviPeineOuMesure getSuiviPeineOuMesure() {
        return suiviPeineOuMesure;
    }

    public void setSuiviPeineOuMesure(SuiviPeineOuMesure suiviPeineOuMesure) {
        this.suiviPeineOuMesure = suiviPeineOuMesure;
    }

    public Set<PeineOuMesureFrappeeAppel> getPeinesOuMesuresFrappeesAppel() {
        return peinesOuMesuresFrappeesAppel;
    }

    /**
     * Retourne la {@link PeineOuMesureFrappeeAppel} correspondant � l'ID �v�nement pass�.
     *
     * @param idEvenement
     * @return
     */
    public PeineOuMesureFrappeeAppel getPeineOuMesureFrappeeAppel(Long idEvenement) {
        return peinesOuMesuresFrappeesAppel.stream()
            .filter(x -> x.getEvenementAppel().getId().equals(idEvenement))
            .findFirst().orElse(null);
    }

    public void setPeinesOuMesuresFrappeesAppel(Set<PeineOuMesureFrappeeAppel> peinesOuMesuresFrappeesAppel) {
        this.peinesOuMesuresFrappeesAppel = peinesOuMesuresFrappeesAppel;
    }
}
