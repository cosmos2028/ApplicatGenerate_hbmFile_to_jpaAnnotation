package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.administration.parametrage.model.FamilleEvenementTypeCassiopee;
import fr.gouv.justice.cassiopee.administration.parametrage.service.enumeration.FamilleEvenementTypeCassiopeeEnum;
import fr.gouv.justice.cassiopee.commons.codification.finder.CodificationFinder;
import fr.gouv.justice.cassiopee.commons.codification.model.Codification;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.infraction.model.Infraction;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.compare.CompareSursisParMnemoMesure;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.PersonneEvenement;
import fr.gouv.justice.cassiopee.processus.jugement.service.enumeration.DecisionEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.EvenementPPL;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.EvenementType;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.helper.ModalitesExecutionHelper;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.helper.PeineOuMesureTypeHelper;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.PeineOuMesureType;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.service.enumeration.LibelleBoutonEnum;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.service.enumeration.NaturePeineOuMesureEnum;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.service.enumeration.TypeGroupeEnum;
import fr.gouv.justice.cassiopee.referentiel.structure.model.TypeElementStructure;

/**
 * Classe "outil" Correspond � une peine prononc�e ou requise dans le cadre du DIA.
 * Il s'agit d'une PeineOuMesureDIABase fournissant une collection des SursisDIA
 * + une collection des PeinesSiInobservationPossibles
 * + une PeineSiInobservation prononc�e (de type PeineOuMesureDIABase)
 * + une collection des AutresModalitesExecutionPossibles
 * + une AutreModaliteExecution (de type PeineOuMesureDIABase)
 * + une collection d'�v�nements li�s � PPL
 */

public class PeinePrononceeOuRequiseDIA extends PeineOuMesureDIABase {

    private static final long serialVersionUID = -7373421795842243093L;

    /**
     * finder codification
     */
    private CodificationFinder codificationFinder;

    /**
     * liste des sursis
     */
    private List<PeineOuMesureDIABase> listeSursis = new ArrayList<>();

    /** liste des autres modalites d'execution possible */
    private List<PeineOuMesureType> listeAutreModaliteExecutionPossible = new ArrayList<>();

    /**
     * liste des peines si inobservation possible
     */
    private List<PeineOuMesureType> listePeineSiInobservationPossible = new ArrayList<>();

    /**
     * liste des titres possible de la peine: � renseigner uniquement dans le cas des complementaires
     */
    private List<Codification> listeTitresPossibles = new ArrayList<>();

    /** autre modalite execution de la peine */
    private PeineOuMesureDIABase autreModaliteExecutionPrononceeOuRequise;

    /** Peine si Inobservation de la peine mere en cours */
    private PeineOuMesureDIABase peineSiInobservationPrononceeOuRequise;

    /** indicateur de peine privative de libert� */
    private boolean indicateurPPL;

    /** libell� du bouton */
    private LibelleBoutonEnum libelleBoutonPPL;

    /** la liste des �v�nements accessibles par le bouton PPL */
    private List<EvenementPPL> evenementsPPL;

    /**
     * titre effectif de la peine ou mesure: ce titre est renseign� � partir de tin, dans le cadre des infraction civil,
     * ce code titre peut etre null et il devrait etre utilis� par le front pour le DTO de retour pour la maj: les
     * peines non pr�vu pour cette infraction sont enregistr�es avec un titre null)
     */
    private String codeTitrePeineOuMesure;

    /**
     * indique si la case � cocher "r�put�es communes aux contraventions" est pr�sente � l'�cran
     */
    private boolean caseReputeesCommunesPresente;

    /**
     * indique si la case � cocher "r�put�es communes aux contraventions" est coch�e
     */
    private boolean caseReputeesCommunesCochee;

    public PeinePrononceeOuRequiseDIA(CodificationFinder codificationFinder) {
        this.codificationFinder = codificationFinder;
    }

    /**
     * injecteur finder codification
     *
     * @param codificationFinder
     */
    public void setCodificationFinder(CodificationFinder codificationFinder) {
        this.codificationFinder = codificationFinder;
    }

    public List<EvenementPPL> getEvenementsPPL() {
        return evenementsPPL;
    }

    public void setEvenementsPPL(List<EvenementPPL> evenementsPPL) {
        this.evenementsPPL = evenementsPPL;
    }

    public boolean isIndicateurPPL() {
        return indicateurPPL;
    }

    public void setIndicateurPPL(boolean indicateurPPL) {
        this.indicateurPPL = indicateurPPL;
    }

    public LibelleBoutonEnum getLibelleBoutonPPL() {
        return libelleBoutonPPL;
    }

    public void setLibelleBoutonPPL(LibelleBoutonEnum libelleBoutonPPL) {
        this.libelleBoutonPPL = libelleBoutonPPL;
    }

    /**
     * Publie les donn�es n�cessaires � la vue d�ploy�e d'une mesure dans un dispositif p�nal (cas de peine li�e � un
     * groupe). construire l'hierarchie de l'objet
     *
     * @param peineOuMesureType
     * @param personneEvenement
     * @param groupe
     * @param listeModaliteExecutionParticuliere
     * @param codeTitrePeineMesure
     * @param indicateurPeineObligatoire
     * @param isPreCochagePossible
     * @param listeTitresPossibles
     *            (cette liste est utilis�e pour rechercher les peines prononc�es li�es � ces titres en fonction du contexte)
     */
    public void publierInfoMesureDeployeeDispo(PeineOuMesureType peineOuMesureType, PersonneEvenement personneEvenement, Groupe groupe,
            List<PeineOuMesureBase> listeModaliteExecutionParticuliere, String codeTitrePeineMesure, boolean indicateurPeineObligatoire,
            boolean isPreCochagePossible, List<Codification> listeTitresPossibles, boolean isDDSE) {

        this.codeTitrePeineOuMesure = codeTitrePeineMesure;
        this.listeTitresPossibles = listeTitresPossibles;
        // retrouver la peine prononcee (root) si elle existe pour le groupe
        PeineOuMesure peineOuMesurePrononcee = findPeineOuMesurePrononceeNiv1LieeAuGroupe(groupe, peineOuMesureType
                .getCodeMesure(), listeTitresPossibles);

        /*
         * donn�es de base communes � toutes les publications des peines (peines principales, alternatives,
         * complementaires)
         */
        publierInfoMesureBase(peineOuMesureType, personneEvenement, listeModaliteExecutionParticuliere,
                indicateurPeineObligatoire, peineOuMesurePrononcee, isPreCochagePossible, isDDSE);

        validateRGEvenementsPPL(personneEvenement, this.getPeineOuMesureType());

    }

    /**
     * Traite la r�gle de gestion concernant le libell� et les traitements du bouton associ� aux PPL
     *
     * @param personneEvenement
     * @param peineOuMesurePrononcee
     */
    @RegleDeGestion({ RG.DIAI123 })
    private void validateRGEvenementsPPL(PersonneEvenement personneEvenement, PeineOuMesureType peineOuMesureType) {
        if (peineOuMesureType.getNaturePeineOuMesure() != null
                && peineOuMesureType.getNaturePeineOuMesure().getCode() != null) {
            if (peineOuMesureType.getNaturePeineOuMesure().getCode().equals(NaturePeineOuMesureEnum.PPL.getCode())) {
                EvenementType evenementTypePorteur = personneEvenement.getEvenement().getEvenementType();

                if (DecisionEnum.appartientJugement(evenementTypePorteur.getCode())) {
                    this.setIndicateurPPL(true);
                    this.setLibelleBoutonPPL(LibelleBoutonEnum.RECHERCHE_DETENTION);
                    this.setEvenementsPPL(publierEvenementsPPL(
                            FamilleEvenementTypeCassiopeeEnum.EVENEMENT_DE_RECHERCHE_DETENTION, this, personneEvenement
                                    .getEvenement().getLienEmetteur().getTypeElementStructure()));
                }
                if (evenementTypePorteur
                        .isFamilleEvenementTypeCassiopee(FamilleEvenementTypeCassiopeeEnum.PV_CRPC_INITIAL)
                        || evenementTypePorteur
                                .isFamilleEvenementTypeCassiopee(FamilleEvenementTypeCassiopeeEnum.PV_CRPC_DIFFERE)) {
                    this.setIndicateurPPL(true);
                    this.setLibelleBoutonPPL(LibelleBoutonEnum.MODALITE_EXECUTION_CRPC);
                    this.setEvenementsPPL(publierEvenementsPPL(
                            FamilleEvenementTypeCassiopeeEnum.EVENEMENT_A_LIER_A_PPL_POUR_DIA_CRPC_, this,
                            personneEvenement.getEvenement().getLienEmetteur().getTypeElementStructure()));
                }
            }
        }
    }

    /**
     * Traite la r�gle de gestion concernant le libell� et les traitements du bouton associ� aux PPL uniquement en mode
     * consultation
     *
     * @param personneEvenement
     * @param peineOuMesurePrononcee
     */
    @RegleDeGestion({ RG.DIAI123 })
    private void validateRGEvenementsPPLConsult(PersonneEvenement personneEvenement, PeineOuMesureType peineOuMesureType) {
        if (peineOuMesureType.getNaturePeineOuMesure() != null
                && peineOuMesureType.getNaturePeineOuMesure().getCode() != null) {
            if (peineOuMesureType.getNaturePeineOuMesure().getCode().equals(NaturePeineOuMesureEnum.PPL.getCode())) {
                EvenementType evenementTypePorteur = personneEvenement.getEvenement().getEvenementType();

                if (DecisionEnum.appartientJugement(evenementTypePorteur.getCode())) {
                    this.setIndicateurPPL(true);
                    this.setLibelleBoutonPPL(LibelleBoutonEnum.RECHERCHE_DETENTION);
                    this.setEvenementsPPL(publierEvenementsPPLConsult(
                            FamilleEvenementTypeCassiopeeEnum.EVENEMENT_DE_RECHERCHE_DETENTION, this, personneEvenement
                                    .getEvenement().getLienEmetteur().getTypeElementStructure()));
                }
                if (evenementTypePorteur
                        .isFamilleEvenementTypeCassiopee(FamilleEvenementTypeCassiopeeEnum.PV_CRPC_INITIAL)
                        || evenementTypePorteur
                                .isFamilleEvenementTypeCassiopee(FamilleEvenementTypeCassiopeeEnum.PV_CRPC_DIFFERE)) {
                    this.setIndicateurPPL(true);
                    this.setLibelleBoutonPPL(LibelleBoutonEnum.MODALITE_EXECUTION_CRPC);
                    this.setEvenementsPPL(publierEvenementsPPLConsult(
                            FamilleEvenementTypeCassiopeeEnum.EVENEMENT_A_LIER_A_PPL_POUR_DIA_CRPC_, this,
                            personneEvenement.getEvenement().getLienEmetteur().getTypeElementStructure()));
                }
            }
        }
    }

    /**
     * Publie les donn�es n�cessaires � la vue d�ploy�e d'une mesure dans un dispositif p�nal (cas de peine li�e �
     * l'infraction).
     *
     * @param peineOuMesureType
     * @param personneEvenement
     * @param infraction
     * @param listeModaliteExecutionParticuliere
     * @param codeTitrePeineMesureTIN
     * @param indicateurPeineObligatoire
     * @param isPreCochagePossible
     * @param listeTitresPossibles
     *            (cette liste est utilis�e pour rechercher les peines prononc�es li�es � ces titres en fonction du
     *            contexte)
     */
    public void publierInfoMesureDeployeeDispo(PeineOuMesureType peineOuMesureType,
            PersonneEvenement personneEvenement, Infraction infraction,
            List<PeineOuMesureBase> listeModaliteExecutionParticuliere, String codeTitrePeineMesureTIN,
            boolean indicateurPeineObligatoire, boolean isPreCochagePossible, List<Codification> listeTitresPossibles, boolean isDDSE) {

        this.codeTitrePeineOuMesure = codeTitrePeineMesureTIN;
        this.listeTitresPossibles = listeTitresPossibles;
        // retrouver la peine prononcee (root) si elle existe pour l'infraction
        PeineOuMesure peineOuMesurePrononcee = findPeineOuMesurePrononceeNiv1LieeInfraction(personneEvenement,
                infraction.getId(), peineOuMesureType.getCodeMesure(), listeTitresPossibles);

        /*
         * donn�es de base communes � toutes les publications des peines (peines principales, alternatives,
         * complementaires)
         */
        publierInfoMesureBase(peineOuMesureType, personneEvenement, listeModaliteExecutionParticuliere,
                indicateurPeineObligatoire, peineOuMesurePrononcee, isPreCochagePossible, isDDSE);

        validateRGEvenementsPPL(personneEvenement, this.getPeineOuMesureType());

    }

    /**
     * publier les info de base qui sont communes aux diff�rentes publication (peines principales, peines alternatives,
     * ...)
     *
     * @param peineOuMesureType
     * @param personneEvenement
     * @param listeModaliteExecutionParticuliere
     * @param indicateurPeineObligatoire
     * @param peineOuMesurePrononcee
     * @param isPreCochagePossible
     */
    private void publierInfoMesureBase(PeineOuMesureType peineOuMesureType, PersonneEvenement personneEvenement,
            List<PeineOuMesureBase> listeModaliteExecutionParticuliere, boolean indicateurPeineObligatoire,
            PeineOuMesure peineOuMesurePrononcee, boolean isPreCochagePossible, boolean isDDSE) {
        // indicateur Obligatoire precochage
        /*
         * Indicateur de precochage des peines encourures de facon obligatoire. Est ce que l'�v�nement portant le DIA
         * fait partie de la famille � Evt portant DIA ne tenant pas compte de la d�cision sur sanction � ? DIAI128
         */
        boolean indicPrecochePeineEncourueObligatoire = PeineOuMesureTypeHelper
                .estPrecocheePeineEncouruesObligatoire(personneEvenement.getEvenement());

        // Dans le contexte de cette m�thode, il faut afficher toutes les mesures possibles, l'indicateur pourConsultation est donc � false
        boolean pourConsultation = false;

        /*
         * initialise l'objet PeineOuMesureBase (this.peineOuMesureType, this.peineOuMesure,
         * this.indicateurObligatoireOuPrononcee ) Si je suis en creation et indicateur peine obligatoire et
         * precochePeineEncourueObligatoire, alors la peine sera coch�
         */

        initPeineOuMesureBase(peineOuMesureType, peineOuMesurePrononcee, isPreCochagePossible
                && indicateurPeineObligatoire && indicPrecochePeineEncourueObligatoire);

        /*
         * initialiser PeineOuMesureDIABase (seulement les attributs propres � PeineOuMesureDIABase (listeObligation,
         * executionProvisoire,... )
         */
        initPeineOuMesureDIABase(personneEvenement, getCodeTitrePeineOuMesure(), pourConsultation, isDDSE);

        // creer la liste des sursis
        this.listeSursis = creerListeSursisPeineOuMesureDIABase(personneEvenement, listeModaliteExecutionParticuliere,
                isPreCochagePossible, pourConsultation);


        /* la liste des autres modalite d'execution possibles */
        this.listeAutreModaliteExecutionPossible = creerListeAutreModaliteExecutionPossible(personneEvenement,
                listeModaliteExecutionParticuliere);

        /* la liste des peines si inobservation possible */
        this.listePeineSiInobservationPossible = creerListePeineSiInobservationPossible(personneEvenement,
                peineOuMesurePrononcee);

        // autre modalite execution
        this.autreModaliteExecutionPrononceeOuRequise = creerAutreModaliteExecutionPrononceeOuRequise(
                personneEvenement, pourConsultation,isDDSE);

        // Peine Si inobservation
        this.peineSiInobservationPrononceeOuRequise = creerPeineSiInobservationPrononceeOuRequise(personneEvenement,
                pourConsultation);
    }

    /**
     * creer la peine si inobservation (si elle existe)de la peine mere en cours
     *
     * @param personneEvenement
     * @param pourConsultation
     *            : Si � VRAI : on est en consultation seule on ne recherche que les mesures prononc�es. Si � FAUX : on
     *            est en saisie, on recherche les possibles + prononc�es
     * @return PeineOuMesureDIABase
     */
    private PeineOuMesureDIABase creerPeineSiInobservationPrononceeOuRequise(PersonneEvenement personneEvenement,
            boolean pourConsultation) {
        if (this.peineOuMesure != null) {
            for (PeineOuMesure peineOuMesure : this.peineOuMesure.getPeinesOuMesuresFilles()) {
                if (peineOuMesure.getTypePeineOuMesureEnum().getType().equals(
                        TypePeineOuMesureEnum.PEINE_SI_INOBSERVATION.getType())) {
                    return createPeineOuMesureDIABase(peineOuMesure
                            .getPeineOuMesureType(), peineOuMesure, personneEvenement, false, pourConsultation, false);
                }
            }
        }
        return null;
    }

    /**
     * permet de creer la peine AutreModaliteExecutionPrononceeOuRequise pour la peine mere en cours
     *
     * @param personneEvenement
     * @param pourConsultation
     *            : Si � VRAI : on est en consultation seule on ne recherche que les mesures prononc�es. Si � FAUX : on
     *            est en saisie, on recherche les possibles + prononc�es
     * @return PeineOuMesureDIABase
     * @return PeineOuMesureDIABase
     */
    private PeineOuMesureDIABase creerAutreModaliteExecutionPrononceeOuRequise(PersonneEvenement personneEvenement,
            boolean pourConsultation,boolean isDDSE) {
        if (this.peineOuMesure != null) {
            for (PeineOuMesure peineOuMesure : this.peineOuMesure.getPeinesOuMesuresFilles()) {
                if (peineOuMesure.getTypePeineOuMesureEnum().getType().equals(
                        TypePeineOuMesureEnum.AUTRE_MODALITE_EXECUTION.getType())) {

                    return createPeineOuMesureDIABase(
                            peineOuMesure.getPeineOuMesureType(), peineOuMesure, personneEvenement, false,
                            pourConsultation,isDDSE);
                }
            }
        }

        return null;
    }

    /**
     * permet de creer une liste des peines si Inobservation possibles
     *
     * @param personneEvenement
     * @return List<PeineOuMesureType>
     */
    private List<PeineOuMesureType> creerListePeineSiInobservationPossible(PersonneEvenement personneEvenement,
            PeineOuMesure peinePrononcee) {

        return ModalitesExecutionHelper
                .getPeinesSiInboservationType(this.peineOuMesureType, personneEvenement, peinePrononcee);

    }

    /**
     * Parmi toutes les modalites execution, recuperer les peines qui ne sont ni sursis, ni peinesSiInobservation
     *
     * @param personneEvenement
     * @param listeModaliteExecutionParticuliere
     * @return List<Codification>
     */
    private List<PeineOuMesureType> creerListeAutreModaliteExecutionPossible(PersonneEvenement personneEvenement,
            List<PeineOuMesureBase> listeModaliteExecutionParticuliere) {

        return ModalitesExecutionHelper
                .getAutresModalitesExecutionsType(this.peineOuMesureType, personneEvenement,
                        listeModaliteExecutionParticuliere, this.peineOuMesure);
    }

    /**
     * permet de creer la liste des sursis (PeineOuMesureDIABase) Fournit la liste des sursis de la peine prononc�e ou
     * requise. La liste des sursis contient les possibles ceux d�j� prononc�s seulement si l'indicateur
     * pourConsultation est � FAUX
     *
     * @param personneEvenement
     * @param listeModaliteExecutionParticuliere
     * @param isPreCochagePossible
     * @param pourConsultation
     *            : Si � VRAI : on est en consultation seule on ne recherche que les mesures prononc�es. Si � FAUX : on
     *            est en saisie, on recherche les possibles + prononc�es
     * @return List<PeineOuMesureDIABase>
     */
    private List<PeineOuMesureDIABase> creerListeSursisPeineOuMesureDIABase(PersonneEvenement personneEvenement,
            List<PeineOuMesureBase> listeModaliteExecutionParticuliere, boolean isPreCochagePossible,
            boolean pourConsultation) {
        List<PeineOuMesureDIABase> listeSursis = new ArrayList<>();

        /*
         * Si l'indicateur pourConsultation est � faux, il faut rechercher les possibles + prononc�es
         */

        if (!pourConsultation) {
            // Rechercher les sursis type
            HashMap<String, List<PeineOuMesureBase>> listeSursisBase = (HashMap<String, List<PeineOuMesureBase>>) ModalitesExecutionHelper
                    .getSursisType(this.peineOuMesureType, personneEvenement, listeModaliteExecutionParticuliere,
                            this.peineOuMesure);
            // pour chaque sursis type
            for (PeineOuMesureBase sursisBase : listeSursisBase.get(ModalitesExecutionHelper.LIST_LIEN_VALIDE)) {
                PeineOuMesure sursisPrononce = null;
                if (this.peineOuMesure != null) {
                    // rechercher la peine prononcee sursi
                    sursisPrononce = this.peineOuMesure.rechercherPeineOuMesureFilleByCodeMesureEtType(sursisBase
                            .getCodeMesure(), TypePeineOuMesureEnum.SURSIS);
                }

                /*
                 * creation d'un objet PeineOuMesureDIABase pour chaque sursis la peine sera coch�e Si: 1) je suis en
                 * creation (aucune peine n'est prononcee) ET indicateurObligatoire 2) la peine est prononc�e
                 */
                PeineOuMesureDIABase peineOuMesureDIABaseForSursis = createPeineOuMesureDIABase(sursisBase
                        .getPeineOuMesureType(), sursisPrononce, personneEvenement, isPreCochagePossible
                        && sursisBase.isObligatoire(), pourConsultation,false);
                listeSursis.add(peineOuMesureDIABaseForSursis);
            }

            // on ajoute egalement des sursis prononcee meme si le lien n'est
            // plus valide
            for (PeineOuMesureBase sursisBase : listeSursisBase.get(ModalitesExecutionHelper.LIST_LIEN_NON_VALIDE)) {
                PeineOuMesure sursisPrononce = null;
                if (this.peineOuMesure != null) {
                    // rechercher la peine prononcee sursi
                    sursisPrononce = this.peineOuMesure.rechercherPeineOuMesureFilleByCodeMesureEtType(sursisBase
                            .getCodeMesure(), TypePeineOuMesureEnum.SURSIS);
                }

                if (sursisPrononce != null) {
                    PeineOuMesureDIABase peineOuMesureDIABaseForSursis = createPeineOuMesureDIABase(sursisBase
                            .getPeineOuMesureType(), sursisPrononce, personneEvenement, isPreCochagePossible
                            && sursisBase.isObligatoire(), pourConsultation,false);
                    listeSursis.add(peineOuMesureDIABaseForSursis);
                }
            }

        } else {
            /*
             * Si l'indicateur pourConsultation est � vrai, il faut rechercher seulement les sursis prononc�s
             */

            // Parcours de toutes les filles de la peine prononcee ou requise
            for (PeineOuMesure peineOuMesureFille : this.peineOuMesure.getPeinesOuMesuresFilles()) {

                // Seule les peines filles de type SURSIS sont prises en compte
                if (peineOuMesureFille.getTypePeineOuMesureEnum().equals(TypePeineOuMesureEnum.SURSIS)) {

                    PeineOuMesureDIABase peineOuMesureDIABaseForSursis = createPeineOuMesureDIABase(peineOuMesureFille
                            .getPeineOuMesureType(), peineOuMesureFille, personneEvenement, true, pourConsultation,false);

                    listeSursis.add(peineOuMesureDIABaseForSursis);

                }
            }

        }

        /*
         * Trier les sursis selon les crit�res suivants : - les sursis n'ayant pas d'obligation, puis ceux en ayant -
         * ordre alphab�tique d�croissant du mn�mo de la mesure
         */
        sortSursis(listeSursis);
        return listeSursis;
    }

    /**
     * permet de trier les sursiselon les crit�res suivants : - les sursis n'ayant pas d'obligation, puis ceux en ayant
     * - ordre alphab�tique d�croissant du mn�mo de la mesure (pour les 2 cas)
     *
     * @param listeSursis
     */
    private void sortSursis(List<PeineOuMesureDIABase> listeSursis) {
        List<PeineOuMesureDIABase> lesSursisSansObligation = new ArrayList<>();
        List<PeineOuMesureDIABase> lesSursisAvecObligation = new ArrayList<>();
        for (PeineOuMesureDIABase sursis : listeSursis) {
            if (sursis.getNbTotalObligation() != 0) {
                // les sursis avec obligations
                lesSursisAvecObligation.add(sursis);
            } else {
                // les sursis sans obligations
                lesSursisSansObligation.add(sursis);
            }
        }
        // trier les 2 listes separement par mnemo
        Collections.sort(lesSursisAvecObligation, new CompareSursisParMnemoMesure());

        Collections.sort(lesSursisSansObligation, new CompareSursisParMnemoMesure());
        listeSursis.clear();

        listeSursis.addAll(lesSursisSansObligation);
        listeSursis.addAll(lesSursisAvecObligation);
    }

    /**
     * permet de creer un objet de type PeineOuMesureDIABase
     *
     * @param peineOuMesureType
     * @param peineOuMesurePrononce
     * @param personneEvenement
     * @param indicateurObligatoire
     * @param pourConsultation
     *            : Si � VRAI : on est en consultation seule on ne recherche que les mesures prononc�es. Si � FAUX : on
     *            est en saisie, on recherche les possibles + prononc�es
     * @return PeineOuMesureDIABase
     */
    protected PeineOuMesureDIABase createPeineOuMesureDIABase(PeineOuMesureType peineOuMesureType,
            PeineOuMesure peineOuMesurePrononce, PersonneEvenement personneEvenement, boolean indicateurObligatoire,
            boolean pourConsultation, boolean isDDSE) {

        /*
         * creation de l'objet peineOuMesureDIABase � la creation de l'objet, la liste des obligations sera cree� (dans
         * le constructeur)
         */
        return new PeineOuMesureDIABase(peineOuMesureType, peineOuMesurePrononce,
                personneEvenement, getCodeTitrePeineOuMesure(), indicateurObligatoire, pourConsultation,isDDSE);
    }

    /**
     * Fournit la modalit� d'ex�cution autre, �ventuellement prononc�e ou requise
     *
     * @return PeineOuMesureDiaBase
     */
    public PeineOuMesureDIABase getAutreModaliteExecutionPrononceeOuRequise() {
        return this.autreModaliteExecutionPrononceeOuRequise;

    }

    /**
     * Fournit la peine si inobservation �ventuellement prononc�e ou requise
     *
     * @return PeineOuMesureDiaBase
     */
    public PeineOuMesureDIABase getPeineSiInobservationPrononceeOuRequise() {
        return this.peineSiInobservationPrononceeOuRequise;
    }

    /**
     * @return listeSursis
     */
    public List<PeineOuMesureDIABase> getListeSursis() {
        return listeSursis;
    }

    /**
     * @param listeSursis
     */
    public void setListeSursis(List<PeineOuMesureDIABase> listeSursis) {
        this.listeSursis = listeSursis;
    }

    /**
     * @return listeAutreModaliteExecutionPossible
     */
    public List<PeineOuMesureType> getListeAutreModaliteExecutionPossible() {
        return listeAutreModaliteExecutionPossible;
    }

    /**
     * @return listePeineSiInobservationPossible
     */
    public List<PeineOuMesureType> getListePeineSiInobservationPossible() {
        return listePeineSiInobservationPossible;
    }

    /**
     * @return listeTitrePossible
     */
    public List<Codification> getListeTitresPossibles() {
        return listeTitresPossibles;
    }

    /**
     * @param listeTitrePossible
     */
    public void setListeTitresPossibles(List<Codification> listeTitrePossible) {
        this.listeTitresPossibles = listeTitrePossible;
    }

    /**
     * @return le code titre effectif de la peine:si la peine est prononc�e retourne celui de la peine prononc�e sinon
     *         retourne le code titre de tin
     */
    public String getCodeTitrePeineOuMesure() {
        if (peineOuMesure != null) {
            // peine prononcee
            if (peineOuMesure.getTitrePeineOuMesure() != null) {
                // code titre de la peine
                return peineOuMesure.getTitrePeineOuMesure().getCode();
            }
            return null;
        }
        return codeTitrePeineOuMesure;
    }

    public boolean isCaseReputeesCommunesCochee() {
        return caseReputeesCommunesCochee;
    }

    public void setCaseReputeesCommunesCochee(boolean caseReputeesCommunesCochee) {
        this.caseReputeesCommunesCochee = caseReputeesCommunesCochee;
    }

    public boolean isCaseReputeesCommunesPresente() {
        return caseReputeesCommunesPresente;
    }

    public void setCaseReputeesCommunesPresente(boolean caseReputeesCommunesPresente) {
        this.caseReputeesCommunesPresente = caseReputeesCommunesPresente;
    }

    /**
     * retrouver la peine prononcee de la personne evenement concernant l'infraction infractionId � titre
     * titrePeineMesurEnum
     *
     * @param personneEvenement
     * @param infractionId
     * @param codeMesureRecherche
     * @param listeTitresPossibles
     * @return PeineOuMesure
     */
    private PeineOuMesure findPeineOuMesurePrononceeNiv1LieeInfraction(PersonneEvenement personneEvenement,
            Long infractionId, Long codeMesureRecherche, List<Codification> listeTitresPossibles) {

        PersonneEvenementInfraction personneEvenementInfraction = personneEvenement
                .getPersonneEvenementInfractionByInfractionId(infractionId);

        if (personneEvenementInfraction != null) {
            for (PeineOuMesure peineOuMesure : personneEvenementInfraction.getPeineOuMesures()) {
                if (peineOuMesure.getPeineOuMesureType().getCodeMesure().equals(codeMesureRecherche)
                        && peineOuMesure.getTypePeineOuMesureEnum().getType().equals(
                                TypePeineOuMesureEnum.PEINE_PRONONCEE_OU_REQUISE.getType())) {
                    /*
                     * code mesure recherch� est trouv� et la peine est prononc�e
                     */
                    if (personneEvenementInfraction.getInfraction().getVersionNatureInfraction().isInfractionCivile()) {
                        /*
                         * dans le cas des infractions civiles, pas de test sur les titres possibles(tous les titres
                         * sont possibles meme le null)
                         */
                        return peineOuMesure;
                    }
                    
                    //EVO 1011
                    TypeGroupeEnum typeGroupeEnum = personneEvenementInfraction.getInfraction().getVersionNatureInfraction().getTypeGroupeAssocie();
                    if (typeGroupeEnum!=null && typeGroupeEnum.getCode()==TypeGroupeEnum.INFRACTION_ADMINISTRATIVE.getCode() ) {
                        /*
                         * dans le cas des infractions administratives, pas de test sur les titres possibles(tous les titres
                         * sont possibles meme le null)
                         */
                        return peineOuMesure;
                    }
                    /*
                     * verifie s'il est dans la liste des titres possibles
                     */
                    List<String> listeCodeTitresPossible = toListeCode(listeTitresPossibles);
                    if (peineOuMesure.getTitrePeineOuMesure() != null
                            && listeCodeTitresPossible.contains(peineOuMesure.getTitrePeineOuMesure().getCode())) {
                        return peineOuMesure;
                    }
                }
            }
        }
        // 0113905: (CA/CASS) Peine AMENDE DOUANIERE non recopi�e dans l'ARRET si confirm�e - ARTM005 = KO
        for (PeineOuMesure peineOuMesure2 : personneEvenement.getPeineOuMesures()) {
        	if (codeMesureRecherche == 10040 && peineOuMesure2.getPeineOuMesureType().getCodeMesure().equals(codeMesureRecherche)
        			&& peineOuMesure2.getTypePeineOuMesureEnum().getType().equals(
        					TypePeineOuMesureEnum.PEINE_PRONONCEE_OU_REQUISE.getType())) {

        		// Debut : 0140523: [EDI] probl�me fusion pi�ces d'ex�cution li�e a la saisie d'amendes douani�res
        		if (infractionId != null && peineOuMesure2 != null && peineOuMesure2.getPersonneEvenementInfraction() != null) {
        			if (peineOuMesure2.getPersonneEvenementInfraction().getInfraction().getId() == infractionId) {
        				return peineOuMesure2;
        			}
        		}
        		// Fin : 0140523: [EDI] probl�me fusion pi�ces d'ex�cution li�e a la saisie d'amendes douani�res

        	}
        }
        // fin0113905
        return null;
    }

    /**
     * transformer une liste d'objet Codification en une liste de code
     *
     * @param listeCodification
     * @return liste de code codification
     */
    private List<String> toListeCode(List<Codification> listeCodification) {
        List<String> listeCode = new ArrayList<>();
        for (Codification codification : listeCodification) {
            listeCode.add(codification == null ? null : codification.getCode());
        }
        return listeCode;
    }

    /**
     * retrouver la peine prononcee recherch�e parmi les peines prononc�es li�es au groupe pass� en param�tre et � titre
     * titrePeineMesureEnum
     *
     * @param groupe
     * @param codeMesureRecherche
     * @param listeTitresPossibles
     * @return PeineOuMesure
     */
    private PeineOuMesure findPeineOuMesurePrononceeNiv1LieeAuGroupe(Groupe groupe, Long codeMesureRecherche,
            List<Codification> listeTitresPossibles) {

        for (PeineOuMesure peineOuMesure : groupe.getPeineOuMesures()) {
            if (peineOuMesure.getPeineOuMesureType().getCodeMesure().equals(codeMesureRecherche)
                    && peineOuMesure.getTypePeineOuMesureEnum().getType().equals(
                            TypePeineOuMesureEnum.PEINE_PRONONCEE_OU_REQUISE.getType())
                    && peineOuMesure.getTitrePeineOuMesure() != null
                    && toListeCode(listeTitresPossibles).contains(peineOuMesure.getTitrePeineOuMesure().getCode())) {
                return peineOuMesure;
            }

        }
        return null;
    }

    /**
     * Cette m�thode permet de publier la liste des �v�nements PPL
     *
     * @param familleEvenementTypeCassiopeeEnum
     * @param peinePrononceeOuRequiseDIA
     * @param typeElementStruture
     * @return la liste des �v�nements PPL
     */
    private List<EvenementPPL> publierEvenementsPPL(
            FamilleEvenementTypeCassiopeeEnum familleEvenementTypeCassiopeeEnum,
            PeinePrononceeOuRequiseDIA peinePrononceeOuRequiseDIA, TypeElementStructure typeElementStruture) {
        List<EvenementPPL> listeEvenementsPPL = new ArrayList<>();

        FamilleEvenementTypeCassiopee familleEvenementTypeCassiopee = codificationFinder
                .findCodificationByCode(FamilleEvenementTypeCassiopee.class, familleEvenementTypeCassiopeeEnum.getCode());

        // r�cup�ration de tous les �v�nements de la famille (�v�nements
        // possibles)
        for (EvenementType evenementType : familleEvenementTypeCassiopee.findEvenementTypes()) {

            EvenementPPL evenementPPL = new EvenementPPL();
            evenementPPL.setEvenementType(evenementType);
            evenementPPL.setPeinePrononceeOuRequiseDIA(peinePrononceeOuRequiseDIA);
            evenementPPL.setTypeElementStructureEmetteur(typeElementStruture);

            // si la peine est prononc�e on indique si l'�v�nement est coch�
            if (peinePrononceeOuRequiseDIA.getPeineOuMesure() != null) {
                // parcours des �v�nements li�s � PPL cr��s en base
                for (Evenement evenementPPLRattachePeine : peinePrononceeOuRequiseDIA.getPeineOuMesure()
                        .getEvenementsLiesPPL()) {
                    if (evenementType.getCode().equals(evenementPPLRattachePeine.getEvenementType().getCode())) {
                        evenementPPL.setIndicateurCoche(true);
                        break;
                    }
                }
            }
            listeEvenementsPPL.add(evenementPPL);
        }
        return listeEvenementsPPL;
    }

    /**
     * Cette m�thode permet de publier la liste des �v�nements PPL en mode consultation
     *
     * @param familleEvenementTypeCassiopeeEnum
     * @param peinePrononceeOuRequiseDIA
     * @param typeElementStruture
     * @return la liste des �v�nements PPL
     */
    private List<EvenementPPL> publierEvenementsPPLConsult(
            FamilleEvenementTypeCassiopeeEnum familleEvenementTypeCassiopeeEnum,
            PeinePrononceeOuRequiseDIA peinePrononceeOuRequiseDIA, TypeElementStructure typeElementStruture) {
        List<EvenementPPL> listeEvenementsPPL = new ArrayList<>();

        FamilleEvenementTypeCassiopee familleEvenementTypeCassiopee = codificationFinder
                .findCodificationByCode(FamilleEvenementTypeCassiopee.class, familleEvenementTypeCassiopeeEnum.getCode());

        // r�cup�ration de tous les �v�nements de la famille (�v�nements
        // possibles)
        for (EvenementType evenementType : familleEvenementTypeCassiopee.findEvenementTypes()) {

            EvenementPPL evenementPPL = new EvenementPPL();
            evenementPPL.setEvenementType(evenementType);
            evenementPPL.setPeinePrononceeOuRequiseDIA(peinePrononceeOuRequiseDIA);
            evenementPPL.setTypeElementStructureEmetteur(typeElementStruture);

            // si la peine est prononc�e on indique si l'�v�nement est coch�
            if (peinePrononceeOuRequiseDIA.getPeineOuMesure() != null) {
                // parcours des �v�nements li�s � PPL cr��s en base
                for (Evenement evenementPPLRattachePeine : peinePrononceeOuRequiseDIA.getPeineOuMesure()
                        .getEvenementsLiesPPL()) {
                    if (evenementType.getCode().equals(evenementPPLRattachePeine.getEvenementType().getCode())) {
                        evenementPPL.setIndicateurCoche(true);
                        // on ne r�cup�re que les �v�nements PPL instanci�s
                        listeEvenementsPPL.add(evenementPPL);
                        break;
                    }
                }
            }
        }
        return listeEvenementsPPL;
    }

    /**
     * publier les infos d'une peine prononc�e ou requise DIA dans le cadre de la consultation Ne fournit que les infos
     * prononc�es
     *
     * @param peineOuMesureType
     * @param personneEvenement
     * @param listeModaliteExecutionParticuliere
     * @param indicateurPeineObligatoire
     * @param peineOuMesurePrononcee
     * @param isPreCochagePossible
     */
    public void publierPeinePrononceeOuRequiseConsult(PersonneEvenement personneEvenement,
            PeineOuMesure peineOuMesurePrononcee) {

        /**
         * Dans le contexte de cette m�thode, seules les peines prononc�es sont � publier et l'indicateur
         * pourConsultation est � TRUE
         */
        boolean pourConsultation = true;
        boolean isDDSE = true;
    	

        /*
         * initialiser PeineOuMesureDIABase (seulement les attributs propres � PeineOuMesureDIABase (listeObligation,
         * executionProvisoire,... )
         */
        initPeineOuMesureBase(peineOuMesurePrononcee.getPeineOuMesureType(), peineOuMesurePrononcee, true);

        /*
         * initialiser PeineOuMesureDIABase (seulement les attributs propres � PeineOuMesureDIABase (listeObligation,
         * executionProvisoire,... )
         */
        initPeineOuMesureDIABase(personneEvenement, getCodeTitrePeineOuMesure(), pourConsultation,isDDSE);

        // creer la liste des sursis
        this.listeSursis = creerListeSursisPeineOuMesureDIABase(personneEvenement, null, false, pourConsultation);

        // autre modalite execution
        this.autreModaliteExecutionPrononceeOuRequise = creerAutreModaliteExecutionPrononceeOuRequise(
                personneEvenement, pourConsultation, isDDSE);

        // Peine Si inobservation
        this.peineSiInobservationPrononceeOuRequise = creerPeineSiInobservationPrononceeOuRequise(personneEvenement,
                pourConsultation);

        // r�cup�ration des �v�nements PPL
        validateRGEvenementsPPLConsult(personneEvenement, this.getPeineOuMesureType());
    }

}
