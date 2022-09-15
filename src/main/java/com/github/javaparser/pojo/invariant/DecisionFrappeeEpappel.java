package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

import java.util.Collections;
import java.util.List;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.infraction.model.Infraction;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.DetailDommageInteretFrappeEpappel;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.EvenementFrappeEpappel;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.PeineOuMesureFrappeeEpappel;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.TypeJugementFrappeEpappel;

/**
 * Représente les décisions frappées lors d'un événement appel.<br>
 * <br>
 * Plusieurs types de décisions peuvent être frappées :<br>
 *  - {@link PersonneEvenementFrappeEpappel}<br>
 *  - {@link PeineOuMesureFrappeeEpappel}<br>
 *  - {@link DetailDommageInteretFrappeEpappel}<br>
 *  - {@link EvenementFrappeEpappel}
 *  - {@link TypeJugementFrappeEpappel}
 */
public abstract class DecisionFrappeeEpappel<A> {


    private static final HashCodeEqualsHelper<DecisionFrappeeEpappel> HE_HELPER = HashCodeEqualsHelper.of(DecisionFrappeeEpappel.class, DecisionFrappeeEpappel::getId, DecisionFrappeeEpappel::getEvenementAppel);

    private Long id;
    private Evenement evenementAppel;

    public abstract A getDecision();
    public abstract void setDecision(A decision);
    public abstract Long getDecisionId();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Evenement getEvenementAppel() {
        return evenementAppel;
    }

    public void setEvenementAppel(Evenement evenementAppel) {
        this.evenementAppel = evenementAppel;
    }

    public List<Infraction> getInfractions() {
        return Collections.emptyList();
    }

    /**
     * Retourne la liste des {@link Infraction} requalifiées liées à cette {@link DecisionFrappeeEpappel}.
     *
     * @return
     */
    public List<Infraction> getInfractionsRequalifiees() {
        return Collections.emptyList();
    }

    @Override
    public int hashCode() {
        return HE_HELPER.getHashCode(this);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean equals(Object obj) {
        return HE_HELPER.areEqual(this, obj);
    }
}
