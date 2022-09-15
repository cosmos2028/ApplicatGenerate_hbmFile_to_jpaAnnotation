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
 * Repr�sente la personne concern�e par l'�v�nement. Un �v�nement peut concerner plusieurs personnes voire toutes les
 * personnes de l'affaire. L'entit� personne �v�nement repr�sente le dispositif si l'�v�nement continent un dispositif.
 * Les peines et mesures (d�cisions, peines, mesures ...) sont directement associ�es � l'entit� personne �v�nement. La
 * collection de peines et mesures est associ�e � l'�v�nement par la cl� �trang�re personne_evenement_id Les attributs
 * sp�cifiques un dispositif partie civil sont pr�fix�s par DIS_PAR_CIV
 */
public class PersonneEvenement implements java.io.Serializable {

    private static final long serialVersionUID = -2596732591832512425L;

    private Long id;

    private Evenement evenement;

    private Personne personne;

    /**
     * Le tribunal constate : - la comparution de la personne - son mode de de repr�sentation. Exemple : comparant,
     * comparant assist�, ...
     */
    private EnumerationValue modeComparution;

    /**
     * Le mode de saisie est une donn�e contextuelle � la vue guid�e Jugement. Par d�faut, la valeur est � Normal �, si
     * cette donn�e est pass�e � � Rapide � par l?utilisateur, c?est seulement dans le cadre de la vue guid�e. En cas de
     * fermeture puis r�ouverture de la vue guid�e, la valeur repasse � � Normal �.
     */
    private ModeSaisie modeSaisie;

    /**
     * La nature du jugement d�pend du mode de convocation, du mode de connaissance de cette convocation, et du mode de
     * comparution � l'audience. Elle permet de d�finir les voies de recours ouvertes et leurs d�lais d'exercice. Par
     * exemple : contradictoire, d�faut, ...
     */
    private EnumerationValue natureJugement;

    /**
     * Date de l'envoi du droit fixe de proc�dure au tr�sor public
     */
    private Date dateEnvoiTresor;

    /**
     * Indique si, pour la personne concern�e, il existe: - une erreur sur info jugement par personne, - une
     * rectification sur info jugement par personne, - aucune des deux (cha�ne vide)
     */
    private Character errRectDisc;

    /**
     * Pour certaines infractions, le tribunal avise la PC � qui il accorde des DI qu'elle peut saisir la commission
     * d'indemnisation des victimes.
     */
    private Boolean disParCivDiAvisSaisineCi;

    private DispositifPenal dispositifPenal;

    private Set<Personne> personneReparations = new HashSet<>();

    private Set<RequetePev> requetes = new HashSet<>();

    private Set<PeineOuMesure> peineOuMesures = new HashSet<>();

    /**
     * permet d'avoir un ensemble de Groupe contenant un Set d'Infraction et un Set de PeineOuMesure et cela pour la
     * PersonneEvenement ce sont les peines partag�es par plusieurs infractions
     */
    private Set<Groupe> groupes = new HashSet<>();

    private Set<PieceExecution> pieceExecutionPortees = new HashSet<>();

    /**
     * permet d'avoir un ensemble de PersonneEvenementInfractions contenant une Infraction et un Set de PeineOuMesure et
     * cela pour la PersonneEvenement
     */
    private Set<PersonneEvenementInfraction> personneEvenementInfractions = new HashSet<>();

    /**
     * Auteurs-infractions li�s � cet �v�nement pour cette personne.
     */
    private Set<LienAuteurInfraction> auteurInfractions = new HashSet<>();

    /**
     * transmission li�e � ce personnne evenement
     */
    private Transmission transmission;

    /**
     * transmissions pour lequelles ce personnne evenement est la proc�dure
     */
    private Set<Transmission> procedureDeTransmissions = new HashSet<>();

    /**
     * echeancier AJE li� � ce personne �v�nement
     */
    private Set<EcheancierAJE> echeancierAJEs = new HashSet<>();

    /**
     * D�normalisation des caracteristique des evenements de la famille convocation pour la recherche des convocation
     * dans le jugement Date de la convocation
     */
    private Date convocationDate;

    /**
     * D�normalisation des caracteristique des evenements de la famille convocation pour la recherche des convocation
     * dans le jugement Service de la convocation
     */
    private Service convocationRattachementService;

    /**
     * D�normalisation des caracteristique des evenements de la famille convocation pour la recherche des convocation
     * dans le jugement Juridiction de la convocation
     */
    private Juridiction convocationJuridictionJugement;

    /**
     * liste des messages de reprise associ�s � l'association personne �v�nement
     */
    private Set<MessageReprise> messageRepriseSet = new LinkedHashSet<>();

    /**
     * liste des commentaires de reprise associ�s � l'association personne �v�nement
     */
    private Set<CommentaireRepris> commentaireReprisSet = new LinkedHashSet<>();

    /** liste des mesures incorrectes reli�es au pv */
    private Set<MesureIncorrecte> mesureIncorrecteRepriseSet = new LinkedHashSet<>();

    /**
     * La modification de d�cision n'est affich� que pour le jugement sur opposition. Indicateur sur la modification de
     * la d�cision.
     */

    private Boolean indicateurModificationDecision;

    private Integer indexeMesures;

    private RefEtatAuteur etatAuteurAvantOrdonnance;

    /**
     * La liste des pays de l'union europ�enne qui sont interconnect�s ou non avec le CJN associ�s � la personne et
     * l'�v�nement
     */
    private Set<Pays> paysUECJN = new LinkedHashSet<>();

    /** Suivi de d�cision */
    private SuiviDecision suiviDecision;

    /**
     * Liste repr�sentant l'historique des envois de mail de suivi de peine en ligne
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
     * echeancier AJE li� � ce personne �v�nement
     *
     * @param echeancierAJEs
     */
    public void setEcheancierAJEs(final Set<EcheancierAJE> echeancierAJEs) {
        this.echeancierAJEs = echeancierAJEs;
    }

    /**
     * echeancier AJE li� � ce personne �v�nement
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
     * ajouter une peine ou mesure � une personne evenement
     *
     * @param peineOuMesure
     */
    public void addPeineOuMesure(final PeineOuMesure peineOuMesure) {
        getPeineOuMesures().add(peineOuMesure);
        peineOuMesure.setPersonneEvenement(this);
    }

    /**
     * permet de chercher la personneEvenementInfraction pour l'infraction pass� en param�tre
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
     * Retourne la liste des PersonneEvenementInfraction li�s a une infraction donn�e.
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
     * Supprime tout les PersonneEvenementInfraction li�s a une infraction donn�e
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
     * enlever les peines en filtrant sur leur type pass� en param�tre
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
        // rechercher la personneEvenementInfraction qui correspond �
        // l'infraction pass� en param�tre
        final PersonneEvenementInfraction personneEvenementInfraction = getPersonneEvenementInfractionByInfractionId(infractionId);
        if (personneEvenementInfraction != null) {
            // enlever de la collection les peines li�es � cette
            // personneEvenementInfraction suivant le TypePeineOuMesure
            getPeineOuMesures().removeAll(
                    personneEvenementInfraction.findPeinesOuMesureByType(typePeineOuMesureEnumAEnlever));
        }
    }

    /**
     * enlever les peines de la collection dont les identifiants pass�s en param�tre
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
     * rechercher les peines ou mesure li�es � la personne evenement et dont le type appartient � filtreTypePeineOuMesureEnum
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
     * rechercher les peine ou mesure par rapport � son type
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
     * transmissions pour lequelles ce personnne evenement est la proc�dure
     *
     * @return retourne procedureDeTransmissions.
     */
    public Set<Transmission> getProcedureDeTransmissions() {
        return procedureDeTransmissions;
    }

    /**
     * transmissions pour lequelles ce personnne evenement est la proc�dure
     *
     * @param procedureDeTransmissions
     *            affecte procedureDeTransmissions
     */
    public void setProcedureDeTransmissions(final Set<Transmission> procedureDeTransmissions) {
        this.procedureDeTransmissions = procedureDeTransmissions;
    }

    /**
     * transmission li�e � ce personnne evenement
     *
     * @return retourne transmission.
     */
    public Transmission getTransmission() {
        return transmission;
    }

    /**
     * transmission li� � ce personnne evenement
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
     * retourne la liste des commentaires de reprise associ�s � l'association personne �v�nement
     *
     * @return la liste des commentaires de reprise associ�s � l'association personne �v�nement
     */
    public Set<CommentaireRepris> getCommentaireReprisSet() {
        return commentaireReprisSet;
    }

    /**
     * renseigne la liste des commentaires de reprise associ�s � l'association personne �v�nement
     *
     * @param commentaireReprisSet
     *            la liste des commentaires de reprise associ�s � l'association personne �v�nement
     */
    public void setCommentaireReprisSet(final Set<CommentaireRepris> commentaireReprisSet) {
        this.commentaireReprisSet = commentaireReprisSet;
    }

    /**
     * retourne la liste des messages de reprise associ�s � l'association personne �v�nement
     *
     * @return la liste des messages de reprise associ�s � l'association personne �v�nement
     */
    public Set<MessageReprise> getMessageRepriseSet() {
        return messageRepriseSet;
    }

    /**
     * renseigne la liste des messages de reprise associ�s � l'association personne �v�nement
     *
     * @param messageRepriseSet
     *            la liste des messages de reprise associ�s � l'association personne �v�nement
     */
    public void setMessageRepriseSet(final Set<MessageReprise> messageRepriseSet) {
        this.messageRepriseSet = messageRepriseSet;
    }

    /**
     * retourne la liste des messages de reprise associ�s � l'association personne �v�nement
     *
     * @return la liste des messages de reprise associ�s � l'association personne �v�nement
     */
    public Set<MesureIncorrecte> getMesureIncorrecteRepriseSet() {
        return mesureIncorrecteRepriseSet;
    }

    /**
     * renseigne la liste des messages de reprise associ�s � l'association personne �v�nement
     *
     * @param messageRepriseSet
     *            la liste des messages de reprise associ�s � l'association personne �v�nement
     */
    public void setMesureIncorrecteRepriseSet(final Set<MesureIncorrecte> mesureIncorrecteSet) {
        mesureIncorrecteRepriseSet = mesureIncorrecteSet;
    }

    /**
     * retourne l'indicateur de modification de d�cision.
     *
     * @return l'indicateur de modification de d�cision
     */
    public Boolean getIndicateurModificationDecision() {
        return indicateurModificationDecision;
    }

    /**
     * renseigne l'indicateur de modification de d�cision.
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
     * Incr�mente et retourne l'index de mesures.
     *
     * @return 1 si l'ancienne valeur �tait null, indexeMesure +1 sinon.
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
     * Accesseur des pays de l'union europ�enne interconnect�s ou non avec le CJN
     *
     * @return la liste des pays de l'union europ�enne interconnect�s ou non avec le CJN
     */
    public Set<Pays> getPaysUECJN() {
        return paysUECJN;
    }

    /**
     * Mutateur des pays de l'union europ�enne interconnect�s ou non avec le CJN
     *
     * @param paysUECJN
     *            la liste des pays de l'union europ�enne interconnect�s ou non avec le CJN
     */
    public void setPaysUECJN(final Set<Pays> paysUECJN) {
        this.paysUECJN = paysUECJN;
    }

    /**
     * retourne l'�tat de l'auteur avant la cr�ation d'une ordonnance
     *
     * @return l'�tat de l'auteur avant la cr�ation d'une ordonnance
     */
    public RefEtatAuteur getEtatAuteurAvantOrdonnance() {
        return etatAuteurAvantOrdonnance;
    }

    /**
     * Renseigne l'�tat de l'auteur avant la cr�ation d'une ordonnance
     *
     * @param etatAuteurAvantOrdonnance : �tat de l'auteur avant la cr�ation d'une ordonnance
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
     * @return SuiviDecision suivi de d�cision
     */
    public SuiviDecision getSuiviDecision() {
        return suiviDecision;
    }

    /**
     * @param suiviDecision
     *            suivi de D�cision
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
