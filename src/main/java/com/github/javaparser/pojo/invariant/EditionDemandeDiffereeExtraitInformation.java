package fr.gouv.justice.cassiopee.invariant.edition.model;

import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;



/**
 * La classe demande d'edition represente une demande d'edition d'une liste d'ecran. Cet objet est persistant.
 *
 * @author afe
 */

public class EditionDemandeDiffereeExtraitInformation extends EditionDemandeDiffereeAvecDestinataire {

    /** personne associée à la demande d'édition */
    private Personne personne;

    @Override
    public EditionDemandeDiffereeEnum getEditionDemandeDiffereeEnum() {
        return EditionDemandeDiffereeEnum.EDITION_DEMANDE_DIFFEREE_SANS_EVENEMENT;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((personne == null) ? 0 : personne.hashCode());
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
        EditionDemandeDiffereeExtraitInformation other = (EditionDemandeDiffereeExtraitInformation) obj;
        if (personne == null) {
            if (other.personne != null)
                return false;
        } else if (!personne.equals(other.personne))
            return false;
        return true;
    }

}
