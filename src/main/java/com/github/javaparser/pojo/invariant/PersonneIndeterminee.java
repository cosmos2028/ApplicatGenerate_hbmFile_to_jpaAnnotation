package fr.gouv.justice.cassiopee.invariant.personne.model;

import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.TypePersonneEnum;

/**
 * Personne physique dont on ne sait si elle est déclarée mineure ou majeure, ou pour laquelle l'information n'est pas
 * intéressante.
 */
public class PersonneIndeterminee extends PersonnePhysique {

    /**
     * Identifiant version pour la sérialisation
     */
    private static final long serialVersionUID = -4333915526769223765L;

    @Override
    public TypePersonneEnum getTypePersonne() {
        return TypePersonneEnum.INDETERMINEE;
    }

}
