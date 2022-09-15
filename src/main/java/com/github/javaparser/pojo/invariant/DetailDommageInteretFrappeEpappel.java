package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import java.util.List;

import fr.gouv.justice.cassiopee.invariant.infraction.model.Infraction;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.DecisionFrappeeEpappel;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.DetailDommageInteret;

/**
 * Voir {@link DecisionFrappeeEpappel}.
 */
public class DetailDommageInteretFrappeEpappel extends DecisionFrappeeEpappel<DetailDommageInteret> {

    private DetailDommageInteret detailDommageInteret;

    public DetailDommageInteret getDetailDommageInteret() {
        return detailDommageInteret;
    }

    public void setDetailDommageInteret(DetailDommageInteret detailDommageInteret) {
        this.detailDommageInteret = detailDommageInteret;
    }

    @Override
    public List<Infraction> getInfractions() {
        return detailDommageInteret.getInfractions();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((detailDommageInteret == null) ? 0 : detailDommageInteret.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        DetailDommageInteretFrappeEpappel other = (DetailDommageInteretFrappeEpappel) obj;
        if (detailDommageInteret == null) {
            if (other.detailDommageInteret != null)
                return false;
        } else if (!detailDommageInteret.equals(other.detailDommageInteret))
            return false;
        return true;
    }

    @Override
    public DetailDommageInteret getDecision() {
        return detailDommageInteret;
    }

    @Override
    public void setDecision(DetailDommageInteret decision) {
        this.detailDommageInteret = decision;
    }

    @Override
    public Long getDecisionId() {
        return detailDommageInteret.getId();
    }

}
