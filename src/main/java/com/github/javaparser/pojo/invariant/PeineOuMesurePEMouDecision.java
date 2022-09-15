package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.DetailPeineOuMesureContexteEnum;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.EvenementCompletantMesure;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.EvenementType;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.helper.ModalitesExecutionHelper;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.PeineOuMesureType;
import fr.gouv.justice.cassiopee.referentiel.structure.model.TypeElementStructure;

/**
 * classe PeineOuMesurePEMouDecision pour PEM ou Decision
 */
public class PeineOuMesurePEMouDecision extends PeineOuMesureBase {

    /**
     *
     */
    private static final long serialVersionUID = -238445358695745829L;
    
    public static TypeElementStructure typeEls;

    /** evenement lié à la peine */
    private Evenement evenementCompletant;

    /** evenement type completant */
    private EvenementType evenementTypeCompletant;

    /** la personne */
    private Personne personne;

    // Ajout CRT au 26/04/06 : Date + Type emetteur
    /** Date de l'événement */
    private Date dateEvenement;

    /** Element de structure émetteur de l'événement */
    private TypeElementStructure typeElementStructureEmetteurEvenement;

    // Fin Ajout CRT au 26/04/06 : Date + Type emetteur

    /** une liste d'obligation PEM ou Decision liée à la mesure */
    private List<PeineOuMesureBase> listeObligation = new ArrayList<>();

    /**
     * liste des peines filles hors obligation
     */
    private List<PeineOuMesureBase> listPeinesFillesHorsObligation = new ArrayList<>();

    /** indicateur d'execution provisoire */
    private Boolean executionProvisoire;

    /**
     * liste des modalites d'execution possible pour reparation lien 11 avec la mère
     */
    private List<PeineOuMesureType> listeModaliteExecPossibleReparation = new ArrayList<>();

    /** modalite d'execution reparation accordée */
    private PeineOuMesure modaliteExecutionReparationAccordee;

    /**
     *
     */
    public PeineOuMesurePEMouDecision() {
    }

    /**
     * détail d'une peine peine ou mesure (en fonction du contexte detailPeineOuMesureContexteEnum): 1) cas classique
     * (operation P02_ de PEM:voir POP-PEM-01 ) la mesure possède au SR - des caractéristiques, - et/ou des motifs, -
     * et/ou des obligations, - et/ou un événement lié(completant la mesure), - et/ou bien commentaire (données
     * valorisées) 2) cas P13_ Publie les données possibles d'une mesure. Correspond à un affichage sur 2 niveaux
     * (mesure + obligations) (données non valorisées) utiliser sur le bouton Afficher dans décision
     *
     * @param peineOuMesureType
     * @param peineOuMesurePrononcee
     * @param indicateurObligatoire
     * @param personne
     * @param dateEvenement
     * @param typeElementStructureEmetteurEvenement
     * @param detailPeineOuMesureContexteEnum
     */
    public void publierUnePeineOuMesure(PeineOuMesureType peineOuMesureType, PeineOuMesure peineOuMesurePrononcee,
            boolean indicateurObligatoire, Personne personne, Date dateEvenement,
            TypeElementStructure typeElementStructureEmetteurEvenement,
            DetailPeineOuMesureContexteEnum detailPeineOuMesureContexteEnum, Boolean isDDSE) {

        // Dans le contexte de cette méthode, il faut afficher toutes les
        // mesures possibles, l'indicateur pourConsultation est donc à false
        boolean pourConsultation = false;

        /*
         * methode de publication commune pour PeineOuMesurePEMouDecision(evenementCompletant, listeObligation,
         * executionProvisoire + PeineOuMesureBase(caract. motifs., ...))
         */
        publierPeineOuMesurePEMouDecisionBase(peineOuMesureType, peineOuMesurePrononcee, indicateurObligatoire,
                personne, dateEvenement, typeElementStructureEmetteurEvenement, pourConsultation, isDDSE);
        /*
         * publication d'une peine dans le contexte dispo civil Renseigner en plus : listeModaliteExecPossibleReparation
         * et modaliteExecutionReparationAccordee
         */
        if (DetailPeineOuMesureContexteEnum.DETAIL_PEINE_OU_MESURE_CAS_DISPOSITIF_CIVIL.equals(detailPeineOuMesureContexteEnum)) {
            // RG.DIAI211
            // Les combo-box "Modalité d'exécution" de chaque réparation sont alimentées par les mesures liées à la réparation par le lien
            this.listeModaliteExecPossibleReparation = ModalitesExecutionHelper.getModalitesExecutionForReparation(this.peineOuMesure,
                    this.peineOuMesureType, dateEvenement);

            if (this.peineOuMesure != null) {
                // rechercher la peine MODALITE_EXECUTION_REPARATION_ACCORDEE
                // parmi les filles de la peine principale
                this.modaliteExecutionReparationAccordee = this.peineOuMesure
                        .rechercherPeineOuMesureFilleByType(TypePeineOuMesureEnum.MODALITE_EXECUTION_REPARATION_ACCORDEE);
            }
        }
    }

    /**
     * détail d'une peine peine ou mesure (en fonction du contexte detailPeineOuMesureContexteEnum): 1) cas classique
     * (operation P02_ de PEM:voir POP-PEM-01 ) la mesure possède au SR - des caractéristiques, - et/ou des motifs, -
     * et/ou des obligations, - et/ou un événement lié(completant la mesure), - et/ou bien commentaire (données
     * valorisées) 2) cas P13_ Publie les données possibles d'une mesure. Correspond à un affichage sur 2 niveaux
     * (mesure + obligations) (données non valorisées) utiliser sur le bouton Afficher dans décision
     *
     * @param peineOuMesureType
     * @param peineOuMesurePrononcee
     * @param indicateurObligatoire
     * @param personne
     * @param dateEvenement
     * @param typeElementStructureEmetteurEvenement
     * @param detailPeineOuMesureContexteEnum
     */
    public void publierUnePeineOuMesureCreationCJ(PeineOuMesureType peineOuMesureType, PeineOuMesure peineOuMesurePrononcee,
            boolean indicateurObligatoire, Personne personne, Date dateEvenement,
            TypeElementStructure typeElementStructureEmetteurEvenement, DetailPeineOuMesureContexteEnum detailPeineOuMesureContexteEnum,
            final boolean isMineur, final boolean isMineurAuMomentDesFaits) {

        // Dans le contexte de cette méthode, il faut afficher toutes les
        // mesures possibles, l'indicateur pourConsultation est donc à false
        boolean pourConsultation = false;

        /*
         * methode de publication commune pour PeineOuMesurePEMouDecision(evenementCompletant, listeObligation,
         * executionProvisoire + PeineOuMesureBase(caract. motifs., ...))
         */
        publierPeineOuMesurePEMouDecisionBaseCreationCJ(peineOuMesureType, peineOuMesurePrononcee, indicateurObligatoire, personne,
                dateEvenement, typeElementStructureEmetteurEvenement, pourConsultation, isMineur, isMineurAuMomentDesFaits);

        // publication d'une peine dans le contexte dispo civil Renseigner en plus :
        // listeModaliteExecPossibleReparation et modaliteExecutionReparationAccordee
        if (DetailPeineOuMesureContexteEnum.DETAIL_PEINE_OU_MESURE_CAS_DISPOSITIF_CIVIL.equals(detailPeineOuMesureContexteEnum)) {
            // RG.DIAI211
            // Les combo-box "Modalité d'exécution" de chaque réparation sont alimentées par les mesures liées à la réparation par le lien
            this.listeModaliteExecPossibleReparation = ModalitesExecutionHelper.getModalitesExecutionForReparation(this.peineOuMesure,
                    this.peineOuMesureType, dateEvenement);

            if (this.peineOuMesure != null) {
                // rechercher la peine MODALITE_EXECUTION_REPARATION_ACCORDEE
                // parmi les filles de la peine principale
                this.modaliteExecutionReparationAccordee = this.peineOuMesure
                        .rechercherPeineOuMesureFilleByType(TypePeineOuMesureEnum.MODALITE_EXECUTION_REPARATION_ACCORDEE);
            }
        }
    }

    public List<PeineOuMesureBase> findListePeineMesure(PeineOuMesure peineOuMesurePrononcee,
            PeineOuMesureType peineOuMesureType, boolean indicateurObligatoire, Personne personne, Date dateEvenement,
            TypeElementStructure typeElementStructureEmetteurEvenement, final boolean isMineur,
            final boolean isMineurAuMomentDesFaits) {

        return recupererListePeineMesureTotale(peineOuMesurePrononcee, peineOuMesureType, indicateurObligatoire,
                personne, dateEvenement, typeElementStructureEmetteurEvenement, false, isMineur, true);

    }

    private List<PeineOuMesureBase> recupererListePeineMesureTotale(PeineOuMesure peineOuMesurePrononcee,
            PeineOuMesureType peineOuMesureType, boolean indicateurObligatoire, Personne personne2,
            Date dateEvenement2, TypeElementStructure typeElementStructureEmetteurEvenement2, boolean pourConsultation,
            boolean isMineur, boolean isMineurAuMomentDesFaits) {
        /* initialisation de la super classe */
        initPeineOuMesureBase(peineOuMesureType, peineOuMesurePrononcee, indicateurObligatoire);

        this.personne = personne2;
        this.dateEvenement = dateEvenement2;
        this.typeElementStructureEmetteurEvenement = typeElementStructureEmetteurEvenement2;

        /*
         * dans TPM P10_ ... Alimenter éventuellement les informations de l'événement lié selon la classe EVENEMENT TYPE
         */
        this.evenementCompletant = findEvenementCompletant();

        // on ne récupère pas l'événement type complétant si on se trouve en
        // consultation
        if (this.evenementCompletant == null && !pourConsultation) {
            /*
             * l'evenement lié à la peine n'a pas été trouvé, rechercher donc le evenement type lié à la peine type s'il
             * existe
             */
            this.evenementTypeCompletant = findEvenementTypeCompletant();
        }

        return creerListeObligationPeineOuMesureBaseCreationCJ(pourConsultation, isMineur, isMineurAuMomentDesFaits);
    }

    /**
     * publier une peine ou mesure light (sans les obligations) juste la peine type(caract. motifs)
     *
     * @param peineOuMesureType
     * @param dateEvenement
     */
    public void publierUnePeineOuMesure(PeineOuMesureType peineOuMesureType, Date dateEvenement) {
        /*
         * methode de publication commune pour PeineOuMesurePEMouDecision(evenementCompletant, listeObligation,
         * executionProvisoire + PeineOuMesureBase(caract. motifs., ...))
         */
        publierPeineOuMesurePEMouDecisionBase(peineOuMesureType, null, false, null, dateEvenement, null, false, false);

    }

    /**
     * Permet de publier une peine existant d'une personne evenement
     *
     * @param peineOuMesurePrononcee
     *            peine portée par une personne evenement
     * @param indicateurObligatoireOuPrononcee
     * @param detailPeineOuMesureContexteEnum
     */
    public void publierPeinePortees(PeineOuMesure peineOuMesurePrononcee, boolean indicateurObligatoireOuPrononcee,
            DetailPeineOuMesureContexteEnum detailPeineOuMesureContexteEnum, Boolean isDDSE) {
        publierUnePeineOuMesure(peineOuMesurePrononcee.getPeineOuMesureType(), peineOuMesurePrononcee,
                indicateurObligatoireOuPrononcee, peineOuMesurePrononcee.getPersonneEvenement().getPersonne(),
                peineOuMesurePrononcee.getPersonneEvenement().getEvenement().getDateEvenement(), peineOuMesurePrononcee
                        .getPersonneEvenement().getEvenement().getLienEmetteur().getTypeElementStructure(),
                detailPeineOuMesureContexteEnum, isDDSE);
    }

    /**
     * classe commune aux differents type de publication (evenementCompletant, listeObligation, executionProvisoire +
     * PeineOuMesureBase(caract., motif. ...) )
     *
     * @param peineOuMesureType
     * @param peineOuMesurePrononcee
     * @param indicateurObligatoire
     * @param personne
     * @param dateEvenement
     * @param typeElementStructureEmetteurEvenement
     * @param pourConsultation
     */
    private void publierPeineOuMesurePEMouDecisionBase(PeineOuMesureType peineOuMesureType,
            PeineOuMesure peineOuMesurePrononcee, boolean indicateurObligatoire, Personne personne, Date dateEvenement,
            TypeElementStructure typeElementStructureEmetteurEvenement, boolean pourConsultation, Boolean isDDSE) {
        /* initialisation de la super classe */
        initPeineOuMesureBase(peineOuMesureType, peineOuMesurePrononcee, indicateurObligatoire);

        this.personne = personne;
        this.dateEvenement = dateEvenement;
        this.typeElementStructureEmetteurEvenement = typeElementStructureEmetteurEvenement;

        /*
         * dans TPM P10_ ... Alimenter éventuellement les informations de l'événement lié selon la classe EVENEMENT TYPE
         */
        this.evenementCompletant = findEvenementCompletant();

        // on ne récupère pas l'événement type complétant si on se trouve en
        // consultation
        if (this.evenementCompletant == null && !pourConsultation) {
            /*
             * l'evenement lié à la peine n'a pas été trouvé, rechercher donc le evenement type lié à la peine type s'il
             * existe
             */
            this.evenementTypeCompletant = findEvenementTypeCompletant();
        }

        /*
         * P10_ Rechercher les données de la mesure. Appeler l'opération P50_PublierCaractéristiqueMesure () Mettre à
         * jour les données de la mesure en sortie Alimenter éventuellement les informations de l'événement lié selon la
         * classe EVENEMENT TYPE
         */
        this.listeObligation = creerListeObligationPeineOuMesureBase(pourConsultation, isDDSE);

        this.setListPeinesFillesHorsObligation(creerListPeinesFillesPeineOuMesureBase(pourConsultation));

        // P04_publierIndicateurExécutionProvisoireSR
        this.executionProvisoire = publierIndicateurExecutionProvisoire(dateEvenement, null);
    }

    /**
     * classe commune aux differents type de publication (evenementCompletant, listeObligation, executionProvisoire +
     * PeineOuMesureBase(caract., motif. ...) )
     *
     * @param peineOuMesureType
     * @param peineOuMesurePrononcee
     * @param indicateurObligatoire
     * @param personne
     * @param dateEvenement
     * @param typeElementStructureEmetteurEvenement
     * @param pourConsultation
     */
    private void publierPeineOuMesurePEMouDecisionBaseCreationCJ(PeineOuMesureType peineOuMesureType,
            PeineOuMesure peineOuMesurePrononcee, boolean indicateurObligatoire, Personne personne, Date dateEvenement,
            TypeElementStructure typeElementStructureEmetteurEvenement, boolean pourConsultation, boolean isMineur,
            boolean isMineurAuMomentDesFaits) {
        /* initialisation de la super classe */
        initPeineOuMesureBase(peineOuMesureType, peineOuMesurePrononcee, indicateurObligatoire);

        this.personne = personne;
        this.dateEvenement = dateEvenement;
        this.typeElementStructureEmetteurEvenement = typeElementStructureEmetteurEvenement;

        /*
         * dans TPM P10_ ... Alimenter éventuellement les informations de l'événement lié selon la classe EVENEMENT TYPE
         */
        this.evenementCompletant = findEvenementCompletant();

        // on ne récupère pas l'événement type complétant si on se trouve en
        // consultation
        if (this.evenementCompletant == null && !pourConsultation) {
            /*
             * l'evenement lié à la peine n'a pas été trouvé, rechercher donc le evenement type lié à la peine type s'il
             * existe
             */
            this.evenementTypeCompletant = findEvenementTypeCompletant();
        }

        /*
         * P10_ Rechercher les données de la mesure. Appeler l'opération P50_PublierCaractéristiqueMesure () Mettre à
         * jour les données de la mesure en sortie Alimenter éventuellement les informations de l'événement lié selon la
         * classe EVENEMENT TYPE
         */
        this.listeObligation = creerListeObligationPeineOuMesureBaseCreationCJ(pourConsultation, isMineur,
                isMineurAuMomentDesFaits);

        // P04_publierIndicateurExécutionProvisoireSR
        this.executionProvisoire = publierIndicateurExecutionProvisoire(dateEvenement, null);
    }

    /**
     * retrouver l'evenement type completant
     *
     * @return EvenementType
     */
    private EvenementType findEvenementTypeCompletant() {
        EvenementCompletantMesure evenementCompletantMesure = this.peineOuMesureType.getEvenementCompletantMesure();
        Date dateEvenement = this.dateEvenement;

        // TODO
        if (verifEvenementCompletantMesureOK(evenementCompletantMesure, dateEvenement)) {
            return evenementCompletantMesure.getEvenementTypeCompletantMesure();
        }

        return null;
    }

    /**
     * @param evenementCompletantMesure
     * @param dateEvenement
     * @return boolean
     */
    private boolean verifEvenementCompletantMesureOK(EvenementCompletantMesure evenementCompletantMesure,
            Date dateEvenement) {
        if (evenementCompletantMesure == null) {
            return false;
        }
        // Verification de l'evenement type (date fermeture, ...) TODO
        return verifEvenementTypeOK(evenementCompletantMesure.getEvenementTypeCompletantMesure(), dateEvenement);

    }

    /**
     * Verification de l'evenement Type:
     *
     * @param evenementTypeCompletantMesure
     * @param dateEvenement
     * @return boolean
     */
    private boolean verifEvenementTypeOK(EvenementType evenementTypeCompletantMesure, Date dateEvenement) {
        // TODO verification date
        return true;
    }

    /**
     * @return Evenement
     */
    public Evenement getEvenementCompletant() {
        return this.evenementCompletant;
    }

    /**
     * retourner l'evenement completant la mesure lié à la peine prononcée (util pour TPM P10_publierInfoMesure(appelé à
     * partir de P13_publierMEsureDeployee) Alimenter éventuellement les informations de l'événement lié selon la classe
     * EVENEMENT TYPE)
     *
     * @return evenement
     */
    private Evenement findEvenementCompletant() {
        if (this.peineOuMesure != null) {
            // evenement completant lié à la mesure
            return this.peineOuMesure.getEvenementCompletantMesure();
        }
        return null;
    }

    /**
     * raccourci
     *
     * @return List
     */
    public List<PeineOuMesureBase> getListeObligation() {
        return this.listeObligation;
    }

    /**
     * creer une liste d'obligations
     *
     * @param pourConsultation
     *            : Si à VRAI : on est en consultation seule on ne recherche que les mesures prononcées. Si à FAUX : on
     *            est en saisie, on recherche les possibles + prononcées
     * @return List<ObligationDIA>
     */
    private List<PeineOuMesureBase> creerListeObligationPeineOuMesureBase(boolean pourConsultation, Boolean isDDSE) {
        if (this.personne == null) {
            /*
             * si la personne n'est pas renseignée, on ne peut pas recuperer les obligations:cas affaire requete ou la
             * personne n'existe pas encore
             */
            return new ArrayList<>();
        }
        ObligationCreator obligationCreator = new ObligationCreator(this.peineOuMesureType, this.peineOuMesure,
                this.personne, this.dateEvenement);
        
        typeEls = this.typeElementStructureEmetteurEvenement;
        return obligationCreator.publierObligationsPossiblesouPrononceesMesure(
                TypePeineOuMesureEnum.OBLIGATION_SUR_PEINE, pourConsultation, isDDSE);
    }

    /**
     * Méthode permettant de creer la liste des peines filles de type PeineOuMesureBase (Objet ne contenant que les
     * informations principales d'une peine ou mesure)
     *
     * @param pourConsultation
     *            : vaut true si on ne peut pas modifier la peine ou mesure
     * @return : la peine ou mesure de type PeineOuMesureBase créée
     */
    private List<PeineOuMesureBase> creerListPeinesFillesPeineOuMesureBase(final boolean pourConsultation) {
        final List<PeineOuMesureBase> listPeinesFilles = new ArrayList<>();
        if (this.personne != null) {
            /*
             * si la personne n'est pas renseignée, on ne peut pas recuperer les obligations:cas affaire requete ou la
             * personne n'existe pas encore
             */
            if (this.peineOuMesure != null) {
                for (final PeineOuMesure peineOuMesureFille : this.peineOuMesure.getPeinesOuMesuresFilles()) {
                    if (!TypePeineOuMesureEnum.OBLIGATION_SUR_PEINE.equals(peineOuMesureFille
                            .getTypePeineOuMesureEnum())) {
                        listPeinesFilles.add(new PeineOuMesureBase(peineOuMesureFille.getPeineOuMesureType(),
                                peineOuMesureFille, false, peineOuMesureFille.getTypePeineOuMesureEnum()));
                    }
                }
            }
        }
        return listPeinesFilles;
    }

    /**
     * creer une liste d'obligations
     *
     * @param pourConsultation
     *            : Si à VRAI : on est en consultation seule on ne recherche que les mesures prononcées. Si à FAUX : on
     *            est en saisie, on recherche les possibles + prononcées
     * @return List<ObligationDIA>
     */
    private List<PeineOuMesureBase> creerListeObligationPeineOuMesureBaseCreationCJ(boolean pourConsultation,
            final boolean isMineur, final boolean isMineurAuMomentDesFaits) {
        if (this.personne == null) {
            /*
             * si la personne n'est pas renseignée, on ne peut pas recuperer les obligations:cas affaire requete ou la
             * personne n'existe pas encore
             */
            return new ArrayList<>();
        }
        ObligationCreator obligationCreator = new ObligationCreator(this.peineOuMesureType, this.peineOuMesure,
                this.personne, this.dateEvenement);
        return obligationCreator.publierObligationsPossiblesouPrononceesMesureCreationCJ(
                TypePeineOuMesureEnum.OBLIGATION_SUR_PEINE, pourConsultation, isMineur, isMineurAuMomentDesFaits);
    }

    private List<PeineOuMesureBase> creerListeObligationPeineModifObligCJCreation(PeineOuMesure peinePrecedente,
            final boolean isMineur, final boolean isMineurAuMomentDesFaits) {
        ObligationCreator obligationCreator = new ObligationCreator(this.peineOuMesureType, this.peineOuMesure,
                this.personne, this.dateEvenement);
        return obligationCreator.publierObligationsPossiblesModifObligCJCreation(
                TypePeineOuMesureEnum.OBLIGATION_SUR_PEINE, peinePrecedente, isMineur, isMineurAuMomentDesFaits);
    }

    private List<PeineOuMesureBase> creerListeObligationPeineModifObligCJModification(PeineOuMesure peinePrecedente,
            final boolean isMineur, final boolean isMineurAuMomentDesFaits) {
        ObligationCreator obligationCreator = new ObligationCreator(this.peineOuMesureType, this.peineOuMesure,
                this.personne, this.dateEvenement);
        return obligationCreator.publierObligationsPossiblesModifObligCJModification(
                TypePeineOuMesureEnum.OBLIGATION_SUR_PEINE, peinePrecedente, isMineur, isMineurAuMomentDesFaits);
    }

    /**
     * Publie une peine ou mesure en mode consultation
     *
     * @param peineOuMesureType
     * @param peineOuMesurePrononcee
     * @param indicateurObligatoireOuPrononcee
     * @param personne
     * @param dateEvenement
     * @param typeElementStructureEmetteurEvenement
     * @param detailPeineOuMesureContexteEnum
     */
    public void publierPeineOuMesurePEMouDecisionConsult(PeineOuMesureType peineOuMesureType,
            PeineOuMesure peineOuMesurePrononcee, boolean indicateurObligatoireOuPrononcee, Personne personne,
            Date dateEvenement, TypeElementStructure typeElementStructureEmetteurEvenement,
            DetailPeineOuMesureContexteEnum detailPeineOuMesureContexteEnum) {

        /*
         * Dans le contexte de cette méthode, seules les peines prononcées sont à publier et l'indicateur
         * pourConsultation est à TRUE
         */
        boolean pourConsultation = true;

        /*
         * methode de publication commune pour PeineOuMesurePEMouDecision(evenementCompletant, listeObligation,
         * executionProvisoire + PeineOuMesureBase(caract. motifs., ...))
         */
        publierPeineOuMesurePEMouDecisionBase(peineOuMesureType, peineOuMesurePrononcee,
                indicateurObligatoireOuPrononcee, personne, dateEvenement, typeElementStructureEmetteurEvenement,
                pourConsultation, false);
        /*
         * publication d'une peine dans le contexte dispo civil Renseigner en plus : modaliteExecutionReparationAccordee
         */
        switch (detailPeineOuMesureContexteEnum) {
            case DETAIL_PEINE_OU_MESURE_CAS_DISPOSITIF_CIVIL:

                // rechercher la peine MODALITE_EXECUTION_REPARATION_ACCORDEE
                // parmi les filles de la peine principale
                this.modaliteExecutionReparationAccordee = this.peineOuMesure
                        .rechercherPeineOuMesureFilleByType(TypePeineOuMesureEnum.MODALITE_EXECUTION_REPARATION_ACCORDEE);

                break;
            default:
                break;
        }
    }

    public void publierUnePeineOuMesureModifObligCJCreation(PeineOuMesure peinePrecedente,
            PeineOuMesureType peineOuMesureType, PeineOuMesure peineOuMesurePrononcee,
            boolean indicateurObligatoireOuPrononcee, Personne personne, Date dateEvenement,
            TypeElementStructure typeElementStructureEmetteurEvenement, final boolean isMineur,
            final boolean isMineurAuMomentDesFaits) {

        /* initialisation de la super classe */
        initPeineOuMesureBase(peineOuMesureType, peineOuMesurePrononcee, indicateurObligatoireOuPrononcee);

        this.personne = personne;
        this.dateEvenement = dateEvenement;
        this.typeElementStructureEmetteurEvenement = typeElementStructureEmetteurEvenement;

        /*
         * creation des obligations pour PEMouDecision: P01_publierObligationsPossiblesMesure
         */
        this.listeObligation = creerListeObligationPeineModifObligCJCreation(peinePrecedente, isMineur,
                isMineurAuMomentDesFaits);
    }

    public void publierUnePeineOuMesureModifObligCJModification(PeineOuMesure peinePrecedente,
            PeineOuMesureType peineOuMesureType, PeineOuMesure peineOuMesurePrononcee,
            boolean indicateurObligatoireOuPrononcee, Personne personne, Date dateEvenement,
            TypeElementStructure typeElementStructureEmetteurEvenement, final boolean isMineur,
            final boolean isMineurAuMomentDesFaits) {

        /* initialisation de la super classe */
        initPeineOuMesureBase(peineOuMesureType, peineOuMesurePrononcee, indicateurObligatoireOuPrononcee);

        this.personne = personne;
        this.dateEvenement = dateEvenement;
        this.typeElementStructureEmetteurEvenement = typeElementStructureEmetteurEvenement;

        /*
         * creation des obligations pour PEMouDecision: P01_publierObligationsPossiblesMesure
         */
        this.listeObligation = creerListeObligationPeineModifObligCJModification(peinePrecedente, isMineur,
                isMineurAuMomentDesFaits);
    }

    /**
     * @return Boolean
     */
    public Boolean getExecutionProvisoire() {
        return executionProvisoire;
    }

    /**
     * @return evenementCompletantType
     */
    public EvenementType getEvenementTypeCompletant() {
        return evenementTypeCompletant;
    }

    /**
     * @return listeModaliteExecPossibleReparation
     */
    public List<PeineOuMesureType> getListeModaliteExecPossibleReparation() {
        return listeModaliteExecPossibleReparation;
    }

    /**
     * @return modaliteExecutionReparationAccordee
     */
    public PeineOuMesure getModaliteExecutionReparationAccordee() {
        return modaliteExecutionReparationAccordee;
    }

    /**
     * @return personne
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * retourne le commentaire de la peine mère
     *
     * @return string si trouvée (null sinon)
     */
    public String getCommentaire() {
        if (this.peineOuMesure != null) {
            return this.peineOuMesure.getCommentaire();
        }
        return null;
    }

    /**
     * retourne le code mesure de la modalite d'execution reparation accordée liée à la peine mère
     *
     * @return Long si la peineOuMesure existe (null sinon)
     */
    public Long getCodeMesureModaliteExecutionReparationAccordee() {
        if (this.modaliteExecutionReparationAccordee != null) {
            return this.modaliteExecutionReparationAccordee.getPeineOuMesureType().getCodeMesure();
        }
        return null;
    }

    /**
     * @return dateEvenement
     */
    public Date getDateEvenement() {
        return dateEvenement;
    }

    /**
     * @param dateEvenement
     */
    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    /**
     * @return typeElementStructureEmetteurEvenement
     */
    public TypeElementStructure getTypeElementStructureEmetteurEvenement() {
        return typeElementStructureEmetteurEvenement;
    }

    /**
     * @param typeElementStructureEmetteurEvenement
     */
    public void setTypeElementStructureEmetteurEvenement(TypeElementStructure typeElementStructureEmetteurEvenement) {
        this.typeElementStructureEmetteurEvenement = typeElementStructureEmetteurEvenement;
    }

    /**
     * Assesseur de la liste des peines filles qui ne sont pas des obligations
     *
     * @return la liste des peines filles hors obligation
     */
    public List<PeineOuMesureBase> getListPeinesFillesHorsObligation() {
        return listPeinesFillesHorsObligation;
    }

    /**
     * Setter de la liste des peines filles qui ne sont pas des obligations
     *
     * @param listPeinesFillesHorsObligation
     */
    public void setListPeinesFillesHorsObligation(List<PeineOuMesureBase> listPeinesFillesHorsObligation) {
        this.listPeinesFillesHorsObligation = listPeinesFillesHorsObligation;
    }
}
