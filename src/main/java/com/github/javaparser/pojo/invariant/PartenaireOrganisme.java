package fr.gouv.justice.cassiopee.invariant.eurojust.model.partenaire;

import fr.gouv.justice.cassiopee.invariant.eurojust.enumeration.OrganismeEnum;

public class PartenaireOrganisme extends Partenaire {
    private OrganismeEnum organisme;

    public OrganismeEnum getOrganisme() {
        return organisme;
    }

    public void setOrganisme(OrganismeEnum organisme) {
        this.organisme = organisme;
    }

    @Override
    public String getName() {
        return organisme.getLibelle();
    }
}
