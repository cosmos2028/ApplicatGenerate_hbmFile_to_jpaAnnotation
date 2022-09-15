package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import java.util.ArrayList;
import java.util.List;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.PersonneEvenement;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.PeineOuMesureType;

/**
 * Classe "outil" Correspond à une obligation telle que proposée dans le cadre du DIA. Une obligation dans DIA est une
 * PeineOuMesureBase contenant une collection de SousObligations de type PeineOuMesureBase
 */

public class ObligationDIA extends PeineOuMesureBase {

    /**
     *
     */
    private static final long serialVersionUID = -7923030699744431190L;

    private List<PeineOuMesureBase> listeSousObligationsDIA = new ArrayList<>();

    /**
     * @param peineOuMesureType
     * @param obligationPrononce
     * @param indPrecochagePeineObligatoire
     * @param personneEvenement
     * @param pourConsultation
     *            : Si à VRAI : on est en consultation seule on ne recherche que les mesures prononcées. Si à FAUX : on
     *            est en saisie, on recherche les possibles + prononcées
     */
    public ObligationDIA(PeineOuMesureType peineOuMesureType, PeineOuMesure obligationPrononce,
            boolean indPrecochagePeineObligatoire, PersonneEvenement personneEvenement, boolean pourConsultation,boolean isDDSE) {
        super(peineOuMesureType, obligationPrononce, indPrecochagePeineObligatoire);
        this.listeSousObligationsDIA = creerListeSousObligationPeineOuMesureBase(personneEvenement, pourConsultation, isDDSE);
    }

    /**
     * Fournit la liste des sous-obligations de l'obligation (meme regle que les obligations)
     *
     * @param personneEvenement
     * @param pourConsultation
     *            : Si à VRAI : on est en consultation seule on ne recherche que les mesures prononcées. Si à FAUX : on
     *            est en saisie, on recherche les possibles + prononcées
     * @return List<PeineOuMesureDIABase>
     */

    private List<PeineOuMesureBase> creerListeSousObligationPeineOuMesureBase(PersonneEvenement personneEvenement,
            boolean pourConsultation, boolean isDDSE) {
        // meme regle que la recuperation des obligations
        ObligationCreator obligationCreator = new ObligationCreator(this.peineOuMesureType, this.peineOuMesure,
                personneEvenement.getPersonne(), personneEvenement.getEvenement().getDateEvenement());
        return obligationCreator.publierObligationsPossiblesouPrononceesMesure(
                TypePeineOuMesureEnum.OBLIGATION_SUR_OBLIGATION, pourConsultation, isDDSE);

    }

    /**
     * @return listeSousObligationsDIA
     */
    public List<PeineOuMesureBase> getListeSousObligationsDIA() {
        return listeSousObligationsDIA;
    }

    /**
     * @param listeSousObligationsDIA
     */
    public void setListeSousObligationsDIA(List<PeineOuMesureBase> listeSousObligationsDIA) {
        this.listeSousObligationsDIA = listeSousObligationsDIA;
    }

}
