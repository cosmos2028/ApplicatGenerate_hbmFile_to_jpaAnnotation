package fr.gouv.justice.cassiopee.invariant.eurojust.model.partenaire;

import fr.gouv.justice.cassiopee.referentiel.territoire.model.Pays;

public class PartenairePays extends Partenaire {
    private Pays pays;
    private Boolean isUE;

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Boolean getIsUE() {
        return isUE;
    }

    public void setIsUE(Boolean isUE) {
        this.isUE = isUE;
    }

    @Override
    public String getName() {
        return pays.getLibelle();
    }
}
