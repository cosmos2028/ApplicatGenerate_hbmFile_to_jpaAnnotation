package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.gouv.justice.cassiopee.administration.structure.model.Juridiction;
import fr.gouv.justice.cassiopee.administration.structure.model.Service;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.infraction.model.LienAuteurInfraction;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.Groupe;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.PeineOuMesure;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.PersonneEvenementInfraction;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.TypeJugementFrappeAppel;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypeDetailReparationEnum;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.invariant.personne.model.RefEtatAuteur;
import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.TypeReparationEnum;
import fr.gouv.justice.cassiopee.processus.execution.model.PieceExecution;
import fr.gouv.justice.cassiopee.processus.tdr.model.CommentaireRepris;
import fr.gouv.justice.cassiopee.processus.tdr.model.MessageReprise;
import fr.gouv.justice.cassiopee.processus.tdr.model.MesureIncorrecte;
import fr.gouv.justice.cassiopee.referentiel.codification.model.SuiviDecision;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.EnumerationValue;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Pays;
import fr.gouv.justice.cassiopee.service.accueil.model.EcheancierAJE;
import fr.gouv.justice.cassiopee.service.accueil.model.Transmission;

/**
 * Représente la personne concernée par l'événement. Un événement peut concerner plusieurs personnes voire toutes les
 * personnes de l'affaire. L'entité personne événement représente le dispositif si l'événement continent un dispositif.
 * Les peines et mesures (décisions, peines, mesures ...) sont directement associées à l'entité personne événement. La
 * collection de peines et mesures est associée à l'événement par la clé étrangère personne_evenement_id Les attributs
 * spécifiques un dispositif partie civil sont préfixés par DIS_PAR_CIV
 */
public class PersonneEvenement implements java.io.Serializable {

    private static final long serialVersionUID = -2596732591832512425L;

    private Long id;

    private Evenement evenement;

    private Personne personne;

    /**
     * Le tribunal constate : - la comparution de la personne - son mode de de représentation. Exemple : comparant,
     * comparant assisté, ...
     */
    private EnumerationValue modeComparution;

    /**
     * Le mode de saisie est une donnée contextuelle à la vue guidée Jugement. Par défaut, la valeur est « Normal », si
     * cette donnée est passée à « Rapide » par l?utilisateur, c?est seulement dans le cadre de la vue guidée. En cas de
     * fermeture puis réouverture de la vue guidée, la valeur repasse à « Normal ».
     */
    private ModeSaisie modeSaisie;

    /**
     * La nature du jugement dépend du mode de convocation, du mode de connaissance de cette convocation, et du mode de
     * comparution à l'audience. Elle permet de définir les voies de recours ouvertes et leurs délais d'exercice. Par
     * exemple : contradictoire, défaut, ...
     */
    private EnumerationValue natureJugement;

    /**
     * Date de l'envoi du droit fixe de procédure au trésor public
     */
    private Date dateEnvoiTresor;

    /**
     * Indique si, pour la personne concernée, il existe: - une erreur sur info jugement par personne, - une
     * rectification sur info jugement par personne, - aucune des deux (chaîne vide)
     */
    private Character errRectDisc;

    /**
     * Pour certaines infractions, le tribunal avise la PC à qui il accorde des DI qu'elle peut saisir la commission
     * d'indemnisation des victimes.
     */
    private Boolean disParCivDiAvisSaisineCi;

    private DispositifPenal dispositifPenal;

    private Set<Personne> personneReparations = new HashSet<>();

    private Set<RequetePev> requetes = new HashSet<>();

    private Set<PeineOuMesure> peineOuMesures = new HashSet<>();

    /**
     * permet d'avoir un ensemble de Groupe contenant un Set d'Infraction et un Set de PeineOuMesure et cela pour la
     * PersonneEvenement ce sont les peines partagées par plusieurs infractions
     */
    private Set<Groupe> groupes = new HashSet<>();

    private Set<PieceExecution> pieceExecutionPortees = new HashSet<>();

    /**
     * permet d'avoir un ensemble de PersonneEvenementInfractions contenant une Infraction et un Set de PeineOuMesure et
     * cela pour la PersonneEvenement
     */
    private Set<PersonneEvenementInfraction> personneEvenementInfractions = new HashSet<>();

    /**
     * Auteurs-infractions liés à cet événement pour cette personne.
     */
    private Set<LienAuteurInfraction> auteurInfractions = new HashSet<>();

    /**
     * transmission liée à ce personnne evenement
     */
    private Transmission transmission;

    /**
     * transmissions pour lequelles ce personnne evenement est la procédure
     */
    private Set<Transmission> procedureDeTransmissions = new HashSet<>();

    /**
     * echeancier AJE lié à ce personne événement
     */
    private Set<EcheancierAJE> echeancierAJEs = new HashSet<>();

    /**
     * Dénormalisation des caracteristique des evenements de la famille convocation pour la recherche des convocation
     * dans le jugement Date de la convocation
     */
    private Date convocationDate;

    /**
     * Dénormalisation des caracteristique des evenements de la famille convocation pour la recherche des convocation
     * dans le jugement Service de la convocation
     */
    private Service convocationRattachementService;

    /**
     * Dénormalisation des caracteristique des evenements de la famille convocation pour la recherche des convocation
     * dans le jugement Juridiction de la convocation
     */
    private Juridiction convocationJuridictionJugement;

    /**
     * liste des messages de reprise associés à l'association personne événement
     */
    private Set<MessageReprise> messageRepriseSet = new LinkedHashSet<>();

    /**
     * liste des commentaires de reprise associés à l'association personne événement
     */
    private Set<CommentaireRepris> commentaireReprisSet = new LinkedHashSet<>();

    /** liste des mesures incorrectes reliées au pv */
    private Set<MesureIncorrecte> mesureIncorrecteRepriseSet = new LinkedHashSet<>();

    /**
     * La modification de décision n'est affiché que pour le jugement sur opposition. Indicateur sur la modification de
     * la décision.
     */

    private Boolean indicateurModificationDecision;

    private Integer indexeMesures;

    private RefEtatAuteur etatAuteurAvantOrdonnance;

    /**
     * La liste des pays de l'union européenne qui sont interconnectés ou non avec le CJN associés à la personne et
     * l'événement
     */
    private Set<Pays> paysUECJN = new LinkedHashSet<>();

    /** Suivi de décision */
    private SuiviDecision suiviDecision;

    /**
     * Liste représentant l'historique des envois de mail de suivi de peine en ligne
     */
    private Set<EnvoiMail> envoisPPEL = new LinkedHashSet<>();

    private Set<PersonneEvenementFrappeAppel> personnesEvenementFrappeesAppel = new HashSet<>();

    private Set<TypeJugementFrappeAppel> typesJugementFrappesAppel = new HashSet<>();

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return evenement
     */
    public Evenement getEvenement() {
        return evenement;
    }

    /**
     * @param evenement
     */
    public void setEvenement(final Evenement evenement) {
        this.evenement = evenement;
    }

    /**
     * @return personne
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * @param personne
     */
    public void setPersonne(final Personne personne) {
        this.personne = personne;
    }

    /**
     * @return modeComparution
     */
    public EnumerationValue getModeComparution() {
        return modeComparution;
    }

    /**
     * @param modeComparution
     */
    public void setModeComparution(final EnumerationValue modeComparution) {
        this.modeComparution = modeComparution;
    }

    /**
     * @return natureJugement
     */
    public EnumerationValue getNatureJugement() {
        return natureJugement;
    }

    /**
     * @param natureJugement
     */
    public void setNatureJugement(final EnumerationValue natureJugement) {
        this.natureJugement = natureJugement;
    }

    /**
     * @return dateEnvoiTresor
     */
    public Date getDateEnvoiTresor() {
        return dateEnvoiTresor;
    }

    /**
     * @param dateEnvoiTresor
     */
    public void setDateEnvoiTresor(final Date dateEnvoiTresor) {
        this.dateEnvoiTresor = dateEnvoiTresor;
    }

    /**
     * @return errRectDisc
     */
    public Character getErrRectDisc() {
        return errRectDisc;
    }

    /**
     * @param errRectDisc
     */
    public void setErrRectDisc(final Character errRectDisc) {
        this.errRectDisc = errRectDisc;
    }

    /**
     * @return personneReparations
     */
    public Set<Personne> getPersonneReparations() {
        return personneReparations;
    }

    /**
     * @param personneReparations
     */
    public void setPersonneReparations(final Set<Personne> personneReparations) {
        this.personneReparations = personneReparations;
    }

    /**
     * @return requetes
     */
    public Set<RequetePev> getRequetes() {
        return requetes;
    }

    /**
     * @param requetes
     */
    public void setRequetes(final Set<RequetePev> requetes) {
        this.requetes = requetes;
    }

    /**
     * @return dispositifPenal
     */
    public DispositifPenal getDispositifPenal() {
        return dispositifPenal;
    }

    /**
     * @param dispositifPenal
     */
    public void setDispositifPenal(final DispositifPenal dispositifPenal) {
        this.dispositifPenal = dispositifPenal;
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
    public void setGroupes(final Set<Groupe> groupes) {
        this.groupes = groupes;
    }

    /**
     * @return
     */
    /**
     * @return personneEvenementInfractions
     */
    public Set<PersonneEvenementInfraction> getPersonneEvenementInfractions() {
        return personneEvenementInfractions;
    }

    /**
     * @param personneEvenementInfractions
     */
    public void setPersonneEvenementInfractions(final Set<PersonneEvenementInfraction> personneEvenementInfractions) {
        this.personneEvenementInfractions = personneEvenementInfractions;
    }

    /**
     * @return Returns the peineOuMesures.
     */
    public Set<PeineOuMesure> getPeineOuMesures() {
        return peineOuMesures;
    }

    /**
     * echeancier AJE lié à ce personne événement
     *
     * @param echeancierAJEs
     */
    public void setEcheancierAJEs(final Set<EcheancierAJE> echeancierAJEs) {
        this.echeancierAJEs = echeancierAJEs;
    }

    /**
     * echeancier AJE lié à ce personne événement
     *
     * @return echeancierAJEs
     */
    public Set<EcheancierAJE> getEcheancierAJEs() {
        return echeancierAJEs;
    }

    /**
     * @param peineOuMesures
     *            The peineOuMesures to set.
     */
    public void setPeineOuMesures(final Set<PeineOuMesure> peineOuMesures) {
        this.peineOuMesures = peineOuMesures;
    }

    /**
     * ajouter une peine ou mesure à une personne evenement
     *
     * @param peineOuMesure
     */
    public void addPeineOuMesure(final PeineOuMesure peineOuMesure) {
        getPeineOuMesures().add(peineOuMesure);
        peineOuMesure.setPersonneEvenement(this);
    }

    /**
     * permet de chercher la personneEvenementInfraction pour l'infraction passé en paramètre
     *
     * @param infractionId
     * @return PersonneEvenementInfraction
     */
    public PersonneEvenementInfraction getPersonneEvenementInfractionByInfractionId(final Long infractionId) {
        for (final PersonneEvenementInfraction personneEvenementInfraction : personneEvenementInfractions) {
            if (personneEvenementInfraction.getInfraction().getId().equals(infractionId)) {
                return personneEvenementInfraction;
            }
        }
        return null;
    }

    /**
     * Retourne la liste des PersonneEvenementInfraction liés a une infraction donnée.
     *
     * @param infractionId
     * @return
     */
    public List<PersonneEvenementInfraction> getPersonneEvenementInfractionListByInfractionId(final Long infractionId) {
        final List<PersonneEvenementInfraction> persEvtInfList = new ArrayList<>();
        for (final PersonneEvenementInfraction personneEvenementInfraction : personneEvenementInfractions) {
            if (personneEvenementInfraction.getInfraction().getId().equals(infractionId)) {
                persEvtInfList.add(personneEvenementInfraction);
            }
        }
        return persEvtInfList;
    }

    /**
     * Supprime tout les PersonneEvenementInfraction liés a une infraction donnée
     *
     * @param infractionId
     * @return
     */
    public Boolean deletePersonneEvenementInfractionByInfractionId(final Long infractionId) {
        for (final PersonneEvenementInfraction personneEvenementInfraction : personneEvenementInfractions) {
            if (personneEvenementInfraction.getInfraction().getId().equals(infractionId)) {
                personneEvenementInfractions.remove(personneEvenementInfraction);
                return true;
            }
        }
        return false;
    }

    /**
     * enlever les peines en filtrant sur leur type passé en paramètre
     *
     * @param typePeineOuMesureEnumAEnlever
     */
    public void removePeines(final TypePeineOuMesureEnum typePeineOuMesureEnumAEnlever) {
        final Set<PeineOuMesure> peineOuMesureSetToRemove = new HashSet<>();

        for (final PeineOuMesure peineOuMesure : getPeineOuMesures()) {
            if (peineOuMesure.getTypePeineOuMesureEnum().getType().equals(typePeineOuMesureEnumAEnlever.getType())) {
                peineOuMesureSetToRemove.add(peineOuMesure);
            }
        }
        getPeineOuMesures().removeAll(peineOuMesureSetToRemove);

    }

    /**
     * enlever les peines de la collection de personneEvenement suivant l'infraction et le type de peine
     *
     * @param typePeineOuMesureEnumAEnlever
     * @param infractionId
     */
    public void removePeinesByTypeForInfraction(final TypePeineOuMesureEnum typePeineOuMesureEnumAEnlever,
            final Long infractionId) {
        // rechercher la personneEvenementInfraction qui correspond à
        // l'infraction passé en paramètre
        final PersonneEvenementInfraction personneEvenementInfraction = getPersonneEvenementInfractionByInfractionId(infractionId);
        if (personneEvenementInfraction != null) {
            // enlever de la collection les peines liées à cette
            // personneEvenementInfraction suivant le TypePeineOuMesure
            getPeineOuMesures().removeAll(
                    personneEvenementInfraction.findPeinesOuMesureByType(typePeineOuMesureEnumAEnlever));
        }
    }

    /**
     * enlever les peines de la collection dont les identifiants passés en paramètre
     *
     * @param peinesToRemoveIdSet
     */
    public void removePeines(final Set<Long> peinesToRemoveIdSet) {
        final Set<PeineOuMesure> peineOuMesureSetToRemove = new HashSet<>();
        for (final PeineOuMesure peineOuMesure : getPeineOuMesures()) {
            if (peinesToRemoveIdSet.contains(peineOuMesure.getId())) {
                peineOuMesureSetToRemove.add(peineOuMesure);
            }
        }
        getPeineOuMesures().removeAll(peineOuMesureSetToRemove);
    }

    /**
     * retorune Mode saisie du jugement de personne.
     *
     * @return Returns the modeSaisie.
     */
    public ModeSaisie getModeSaisie() {
        return modeSaisie;
    }

    /**
     * Indique Mode saisie du jugement de personne.
     *
     * @param modeSaisie
     *            The modeSaisie to set.
     */
    public void setModeSaisie(final ModeSaisie modeSaisie) {
        this.modeSaisie = modeSaisie;
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
    public void setPieceExecutionPortees(final Set<PieceExecution> pieceExecutionPortees) {
        this.pieceExecutionPortees = pieceExecutionPortees;
    }

    /**
     * @return Returns the auteurInfractions.
     */
    public Set<LienAuteurInfraction> getAuteurInfractions() {
        return auteurInfractions;
    }

    /**
     * @param auteurInfractions
     *            The auteurInfractions to set.
     */
    public void setAuteurInfractions(final Set<LienAuteurInfraction> auteurInfractions) {
        this.auteurInfractions = auteurInfractions;
    }

    /**
     * @return disParCivDiAvisSaisineCi
     */
    public Boolean getDisParCivDiAvisSaisineCi() {
        return disParCivDiAvisSaisineCi;
    }

    /**
     * @param disParCivDiAvisSaisineCi
     */
    public void setDisParCivDiAvisSaisineCi(final Boolean disParCivDiAvisSaisineCi) {
        this.disParCivDiAvisSaisineCi = disParCivDiAvisSaisineCi;
    }

    /**
     * rechercher les peines ou mesure liées à la personne evenement et dont le type appartient à filtreTypePeineOuMesureEnum
     *
     * @param filtreTypePeineOuMesureEnum
     * @return Set
     */
    public Set<PeineOuMesure> findPeinesOuMesureByTypePeineOuMesure(final Set<TypePeineOuMesureEnum> filtreTypePeineOuMesureEnum) {
        final Set<PeineOuMesure> peineOuMesureSet = new HashSet<>();
        for (final PeineOuMesure peineOuMesure : peineOuMesures) {
            if (filtreTypePeineOuMesureEnum.contains(peineOuMesure.getTypePeineOuMesureEnum())) {
                peineOuMesureSet.add(peineOuMesure);
            }
        }
        return peineOuMesureSet;
    }

    /**
     * rechercher les peine ou mesure par rapport à son type
     *
     * @param typePeineOuMesureEnum
     * @return PeineOuMesure
     */
    public List<PeineOuMesure> findPeineOuMesureByTypePeineOuMesure(final TypePeineOuMesureEnum typePeineOuMesureEnum) {
        List<PeineOuMesure> result = new ArrayList<>();
        for (final PeineOuMesure peineOuMesure : peineOuMesures) {
            if (typePeineOuMesureEnum == peineOuMesure.getTypePeineOuMesureEnum()) {
                result.add(peineOuMesure);
            }
        }
        return result;
    }

    /**
     * retourner une mam (codeTypeDetail,detailDiProvisionPrononce)en fonction du type de reparation
     *
     * @param typeReparationEnum
     * @return liste detail
     */
    public Map<String, DetailDommageInteret> findMapDetailsDiProvByTypeReparation(
            final TypeReparationEnum typeReparationEnum) {
        final Map<String, DetailDommageInteret> mapDetail = new HashMap<>();
        for (final DetailDommageInteret detailDiProvisionPrononce : getPersonne().getDetailsDiProvisions(false, null)) {
            if (detailDiProvisionPrononce.getTypeReparation().getType().equals(typeReparationEnum.getType())) {
                if (TypeReparationEnum.ARTICLE4751.equals(typeReparationEnum)) {
                    mapDetail.put(TypeDetailReparationEnum.ARTICLE_475CPP.getCode(), detailDiProvisionPrononce);
                } else {
                    mapDetail.put(detailDiProvisionPrononce.getTypeDetailReparation().getCode(), detailDiProvisionPrononce);
                }
            }
        }
        return mapDetail;
    }

    /**
     * transmissions pour lequelles ce personnne evenement est la procédure
     *
     * @return retourne procedureDeTransmissions.
     */
    public Set<Transmission> getProcedureDeTransmissions() {
        return procedureDeTransmissions;
    }

    /**
     * transmissions pour lequelles ce personnne evenement est la procédure
     *
     * @param procedureDeTransmissions
     *            affecte procedureDeTransmissions
     */
    public void setProcedureDeTransmissions(final Set<Transmission> procedureDeTransmissions) {
        this.procedureDeTransmissions = procedureDeTransmissions;
    }

    /**
     * transmission liée à ce personnne evenement
     *
     * @return retourne transmission.
     */
    public Transmission getTransmission() {
        return transmission;
    }

    /**
     * transmission lié à ce personnne evenement
     *
     * @param transmission
     *            affecte transmission
     */
    public void setTransmission(final Transmission transmission) {
        this.transmission = transmission;
    }

    /**
     * @return Returns the convocationDate.
     */
    public Date getConvocationDate() {
        return convocationDate;
    }

    /**
     * @param convocationDate
     *            The convocationDate to set.
     */
    public void setConvocationDate(final Date convocationDate) {
        this.convocationDate = convocationDate;
    }

    /**
     * @return Returns the convocationJuridictionJugement.
     */
    public Juridiction getConvocationJuridictionJugement() {
        return convocationJuridictionJugement;
    }

    /**
     * @param convocationJuridictionJugement
     *            The convocationJuridictionJugement to set.
     */
    public void setConvocationJuridictionJugement(final Juridiction convocationJuridictionJugement) {
        this.convocationJuridictionJugement = convocationJuridictionJugement;
    }

    /**
     * @return Returns the convocationRattachementService.
     */
    public Service getConvocationRattachementService() {
        return convocationRattachementService;
    }

    /**
     * @param convocationRattachementService
     *            The convocationRattachementService to set.
     */
    public void setConvocationRattachementService(final Service convocationRattachementService) {
        this.convocationRattachementService = convocationRattachementService;
    }

    /**
     * retourne la liste des commentaires de reprise associés à l'association personne événement
     *
     * @return la liste des commentaires de reprise associés à l'association personne événement
     */
    public Set<CommentaireRepris> getCommentaireReprisSet() {
        return commentaireReprisSet;
    }

    /**
     * renseigne la liste des commentaires de reprise associés à l'association personne événement
     *
     * @param commentaireReprisSet
     *            la liste des commentaires de reprise associés à l'association personne événement
     */
    public void setCommentaireReprisSet(final Set<CommentaireRepris> commentaireReprisSet) {
        this.commentaireReprisSet = commentaireReprisSet;
    }

    /**
     * retourne la liste des messages de reprise associés à l'association personne événement
     *
     * @return la liste des messages de reprise associés à l'association personne événement
     */
    public Set<MessageReprise> getMessageRepriseSet() {
        return messageRepriseSet;
    }

    /**
     * renseigne la liste des messages de reprise associés à l'association personne événement
     *
     * @param messageRepriseSet
     *            la liste des messages de reprise associés à l'association personne événement
     */
    public void setMessageRepriseSet(final Set<MessageReprise> messageRepriseSet) {
        this.messageRepriseSet = messageRepriseSet;
    }

    /**
     * retourne la liste des messages de reprise associés à l'association personne événement
     *
     * @return la liste des messages de reprise associés à l'association personne événement
     */
    public Set<MesureIncorrecte> getMesureIncorrecteRepriseSet() {
        return mesureIncorrecteRepriseSet;
    }

    /**
     * renseigne la liste des messages de reprise associés à l'association personne événement
     *
     * @param messageRepriseSet
     *            la liste des messages de reprise associés à l'association personne événement
     */
    public void setMesureIncorrecteRepriseSet(final Set<MesureIncorrecte> mesureIncorrecteSet) {
        mesureIncorrecteRepriseSet = mesureIncorrecteSet;
    }

    /**
     * retourne l'indicateur de modification de décision.
     *
     * @return l'indicateur de modification de décision
     */
    public Boolean getIndicateurModificationDecision() {
        return indicateurModificationDecision;
    }

    /**
     * renseigne l'indicateur de modification de décision.
     *
     * @param indicateurModificationDecision
     */
    public void setIndicateurModificationDecision(final Boolean indicateurModificationDecision) {
        this.indicateurModificationDecision = indicateurModificationDecision;
    }

    /**
     * @return l'ordre maximal des mesures.
     */
    public Integer getIndexeMesures() {
        return indexeMesures;
    }

    /**
     * Incrémente et retourne l'index de mesures.
     *
     * @return 1 si l'ancienne valeur était null, indexeMesure +1 sinon.
     */
    public int incrementeIndexeMesure() {
        int index = indexeMesures == null ? 1 : indexeMesures.intValue() +1;
        indexeMesures = Integer.valueOf(index);
        return index;
    }

    /**
     * @param indexeMesures
     *            l'ordre maximal des mesures.
     */
    public void setIndexeMesures(final Integer indexeMesures) {
        this.indexeMesures = indexeMesures;
    }

    /**
     * Accesseur des pays de l'union européenne interconnectés ou non avec le CJN
     *
     * @return la liste des pays de l'union européenne interconnectés ou non avec le CJN
     */
    public Set<Pays> getPaysUECJN() {
        return paysUECJN;
    }

    /**
     * Mutateur des pays de l'union européenne interconnectés ou non avec le CJN
     *
     * @param paysUECJN
     *            la liste des pays de l'union européenne interconnectés ou non avec le CJN
     */
    public void setPaysUECJN(final Set<Pays> paysUECJN) {
        this.paysUECJN = paysUECJN;
    }

    /**
     * retourne l'état de l'auteur avant la création d'une ordonnance
     *
     * @return l'état de l'auteur avant la création d'une ordonnance
     */
    public RefEtatAuteur getEtatAuteurAvantOrdonnance() {
        return etatAuteurAvantOrdonnance;
    }

    /**
     * Renseigne l'état de l'auteur avant la création d'une ordonnance
     *
     * @param etatAuteurAvantOrdonnance : état de l'auteur avant la création d'une ordonnance
     */
    public void setEtatAuteurAvantOrdonnance(final RefEtatAuteur etatAuteurAvantOrdonnance) {
        this.etatAuteurAvantOrdonnance = etatAuteurAvantOrdonnance;
    }

    /**
     * Retourne l'historique des envois de mail de suivi de peine en ligne
     *
     * @return l'historique des envois de mail de suivi de peine en ligne
     */
    public Set<EnvoiMail> getEnvoisPPEL() {
        return envoisPPEL;
    }

    /**
     * Renseigne l'historique des envois de mail de suivi de peine en ligne
     *
     * @param envoisPPEL : l'historique des envois de mail de suivi de peine en ligne
     */
    public void setEnvoisPPEL(Set<EnvoiMail> envoisPPEL) {
        this.envoisPPEL = envoisPPEL;
    }

    public Set<PersonneEvenementFrappeAppel> getPersonnesEvenementFrappeesAppel() {
        return personnesEvenementFrappeesAppel;
    }

    public void setPersonnesEvenementFrappeesAppel(Set<PersonneEvenementFrappeAppel> personnesEvenementFrappeesAppel) {
        this.personnesEvenementFrappeesAppel = personnesEvenementFrappeesAppel;
    }

    public Set<TypeJugementFrappeAppel> getTypesJugementFrappesAppel() {
        return typesJugementFrappesAppel;
    }

    public void setTypesJugementFrappesAppel(Set<TypeJugementFrappeAppel> typesJugementFrappesAppel) {
        this.typesJugementFrappesAppel = typesJugementFrappesAppel;
    }

    /**
     * @return SuiviDecision suivi de décision
     */
    public SuiviDecision getSuiviDecision() {
        return suiviDecision;
    }

    /**
     * @param suiviDecision
     *            suivi de Décision
     */
    public void setSuiviDecision(SuiviDecision suiviDecision) {
        this.suiviDecision = suiviDecision;
    }

    public PersonneEvenementFrappeAppel getPersonnesEvenementFrappeesAppel(Long id) {
        return personnesEvenementFrappeesAppel.stream()
                .filter(x -> x.getEvenementAppel().getId().equals(id))
                .findFirst().orElse(null);
    }
}
