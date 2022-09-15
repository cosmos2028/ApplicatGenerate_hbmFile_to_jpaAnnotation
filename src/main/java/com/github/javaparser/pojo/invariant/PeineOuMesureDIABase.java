package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import java.util.ArrayList;
import java.util.List;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.PersonneEvenement;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.PeineOuMesureType;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.service.enumeration.PeineOuMesureTypeEnum;

/**
 * Classe "outil" Correspond à une peine ou mesure de base dans le cadre du DIA. Il s'agit d'une PeineOuMesureBase
 * fournissant une collection d'Obligations (de type PeineOuMesureBase) + un indicateur ExecutionProvisoire + un
 * indicateur PaiementParUnTiers
 */

public class PeineOuMesureDIABase extends PeineOuMesureBase {
    /**
     *
     */
    private static final long serialVersionUID = 8510557934741187969L;

    /** une liste d'obligation liée à la mesure */
    private List<ObligationDIA> listeObligation = new ArrayList<>();

    /** indicateur d'execution provisoire */
    private Boolean executionProvisoire;

    /**
     * constructeur vide
     */
    public PeineOuMesureDIABase() {

    }

    /**
     * constructeur
     *
     * @param peineOuMesureType
     * @param peineOuMesure
     * @param personneEvenement
     * @param codeTitrePeineMesure
     * @param indicateurObligatoire
     * @param pourConsultation
     *            : Si à VRAI : on est en consultation seule on ne recherche que les mesures prononcées. Si à FAUX : on
     *            est en saisie, on recherche les possibles + prononcées
     */
    public PeineOuMesureDIABase(PeineOuMesureType peineOuMesureType, PeineOuMesure peineOuMesure,
            PersonneEvenement personneEvenement, String codeTitrePeineMesure, boolean indicateurObligatoire,
            boolean pourConsultation, boolean isDDSE) {
        super(peineOuMesureType, peineOuMesure, indicateurObligatoire);

        // initialiser l'objet PeineOuMesureDIABase
        initPeineOuMesureDIABase(personneEvenement, codeTitrePeineMesure, pourConsultation,isDDSE);

    }

    /**
     * initialiser l'objet PeineOuMesureDIABase
     *
     * @param personneEvenement
     * @param codeTitrePeineMesure
     * @param pourConsultation
     *            : Si à VRAI : on est en consultation seule on ne recherche que les mesures prononcées. Si à FAUX : on
     *            est en saisie, on recherche les possibles + prononcées
     */
    protected void initPeineOuMesureDIABase(PersonneEvenement personneEvenement, String codeTitrePeineMesure,
            boolean pourConsultation, boolean isDDSE) {

        this.listeObligation = creerListeObligationsDIA(personneEvenement, pourConsultation,isDDSE);
        // indicateur execution provisoire
        this.executionProvisoire = publierIndicateurExecutionProvisoire(personneEvenement.getEvenement()
                .getDateEvenement(), codeTitrePeineMesure);
        // paiement par un tiers est recuperer directement dans
        // isPaiementParUnTiers
    }

    /**
     * creer une liste d'obligations
     *
     * @param personneEvenement
     * @param pourConsultation
     *            : Si à VRAI : on est en consultation seule on ne recherche que les mesures prononcées. Si à FAUX : on
     *            est en saisie, on recherche les possibles + prononcées
     * @return List<ObligationDIA>
     */
    private List<ObligationDIA> creerListeObligationsDIA(PersonneEvenement personneEvenement, boolean pourConsultation, boolean isDDSE) {
        List<ObligationDIA> listeObligation = new ArrayList<>();

        // objet de creation d'obligations
        ObligationCreator obligationCreator = new ObligationCreator(this.peineOuMesureType, this.peineOuMesure,
                personneEvenement.getPersonne(), personneEvenement.getEvenement().getDateEvenement());
        List<PeineOuMesureBase> listeObligationPeineOuMesureBase = obligationCreator
                .publierObligationsPossiblesouPrononceesMesure(TypePeineOuMesureEnum.OBLIGATION_SUR_PEINE,
                        pourConsultation, isDDSE);
        for (PeineOuMesureBase peineOuMesureBase : listeObligationPeineOuMesureBase) {
            /*
             * creation d'un objet ObligationDIA pour chaque obligation retournée
             */
            ObligationDIA obligationDIA = createObligationDIA(peineOuMesureBase.getPeineOuMesureType(),
                    peineOuMesureBase.getPeineOuMesure(), peineOuMesureBase.isObligatoire(), personneEvenement,
                    pourConsultation,isDDSE);

            // ajouter dans la liste des obligations
            listeObligation.add(obligationDIA);
        }

        return listeObligation;
    }

    /**
     * permet de créer un objet de type ObligationDIA
     *
     * @param peineOuMesureType
     * @param obligationPrononce
     * @param indPrecochagePeineObligatoire
     * @param personneEvenement
     * @param pourConsultation
     *            : Si à VRAI : on est en consultation seule on ne recherche que les mesures prononcées. Si à FAUX : on
     *            est en saisie, on recherche les possibles + prononcées
     * @return ObligationDIA
     */
    private ObligationDIA createObligationDIA(PeineOuMesureType peineOuMesureType, PeineOuMesure obligationPrononce,
            boolean indPrecochagePeineObligatoire, PersonneEvenement personneEvenement, boolean pourConsultation,boolean isDDSE) {
        /*
         * creation de l'objet ObligationDIA le fait d'initialiser l'objet ObligationDIA, les sous obligations seront
         * alimentées
         */
        return new ObligationDIA(peineOuMesureType, obligationPrononce,
                indPrecochagePeineObligatoire, personneEvenement, pourConsultation, isDDSE);
    }

    /**
     * Fournit l'indicateur ExécutionProvisoire
     *
     * @return boolean
     */
    public Boolean getExecutionProvisoire() {
        return executionProvisoire;
    }

    /**
     * Fournit l'indicateur Paiement par un Tiers
     *
     * @return Boolean
     */
    @RegleDeGestion(RG.DIAI126)
    public boolean isPaiementParUnTiers() {
        // si la peine est paiement à charge d'un tiers
        return peineOuMesureType.getCodeMesure().equals(PeineOuMesureTypeEnum.PAIEMENT_A_CHARGE_TIERS.getCode());

    }

    /**
     * Fournit le nombre d'obligations prononcées de la peine ou mesure
     *
     * @return int
     */
    public int getNbObligationPrononcee() {
        int count = 0;
        for (ObligationDIA obligationDIA : this.listeObligation) {
            if (obligationDIA.getPeineOuMesure() != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * @return int
     */
    public int getNbTotalObligation() {
        return this.listeObligation.size();
    }

    /**
     * Fournit la liste obligations de la peine ou mesure
     *
     * @return listeObligation
     */
    public List<ObligationDIA> getListeObligation() {
        return listeObligation;
    }

}
