package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import java.util.List;
import java.util.stream.Collectors;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.infraction.model.Infraction;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.DecisionFrappeeAppel;

/**
 * Voir {@link DecisionFrappeeAppel}
 */
public class EvenementFrappeAppel extends DecisionFrappeeAppel<Evenement> {

    private static final HashCodeEqualsHelper<EvenementFrappeAppel> HE_HELPER = HashCodeEqualsHelper.of(EvenementFrappeAppel.class, EvenementFrappeAppel::getId, EvenementFrappeAppel::getEvenement);

    private Evenement evenement;

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    @Override
    public List<Infraction> getInfractions() {
        return evenement.getMesurePPLs().stream()
                .flatMap(mesure -> mesure.getGroupes().stream())
                .flatMap(groupe -> groupe.getInfractions().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public int hashCode() {
        return HE_HELPER.getHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return HE_HELPER.areEqual(this, obj);
    }

    @Override
    public Evenement getDecision() {
        return evenement;
    }

    @Override
    public void setDecision(Evenement decision) {
        this.evenement = decision;
    }

    @Override
    public Long getDecisionId() {
        return evenement.getId();
    }

}
