package fr.gouv.justice.cassiopee.invariant.infraction.model;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;

/**
 * Etat de l'infraction. Une infraction peut �tre en cours, disqualifi�e, supprim�e, amnisti�e.
 *
 * @see fr.gouv.justice.cassiopee.invariant.infraction.model.EtatInfractionEnum
 */
public class EtatInfraction extends Codification {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 260350505087490835L;


    public boolean is(EtatInfractionEnum ei) {
        return getCode().equals(ei.getCode());
    }

}
