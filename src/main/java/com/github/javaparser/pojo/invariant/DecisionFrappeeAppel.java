package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

import java.util.Collections;
import java.util.List;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.infraction.model.Infraction;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.DetailDommageInteretFrappeAppel;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.EvenementFrappeAppel;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.PeineOuMesureFrappeeAppel;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.TypeJugementFrappeAppel;

/**
 * Représente les décisions frappées lors d'un événement appel.<br>
 * <br>
 * Plusieurs types de décisions peuvent être frappées :<br>
 *  - {@link PersonneEvenementFrappeAppel}<br>
 *  - {@link PeineOuMesureFrappeeAppel}<br>
 *  - {@link DetailDommageInteretFrappeAppel}<br>
 *  - {@link EvenementFrappeAppel}
 *  - {@link TypeJugementFrappeAppel}
 */
public abstract class DecisionFrappeeAppel<T> {


    private static final HashCodeEqualsHelper<DecisionFrappeeAppel> HE_HELPER = HashCodeEqualsHelper.of(DecisionFrappeeAppel.class, DecisionFrappeeAppel::getId, DecisionFrappeeAppel::getEvenementAppel);

    private Long id;
    private Evenement evenementAppel;

    public abstract T getDecision();
    public abstract void setDecision(T decision);
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
     * Retourne la liste des {@link Infraction} requalifiées liées à cette {@link DecisionFrappeeAppel}.
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
