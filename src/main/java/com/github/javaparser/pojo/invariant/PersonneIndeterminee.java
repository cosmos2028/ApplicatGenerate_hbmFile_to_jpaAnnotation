package fr.gouv.justice.cassiopee.invariant.personne.model;

import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.TypePersonneEnum;

/**
 * Personne physique dont on ne sait si elle est d�clar�e mineure ou majeure, ou pour laquelle l'information n'est pas
 * int�ressante.
 */
public class PersonneIndeterminee extends PersonnePhysique {

    /**
     * Identifiant version pour la s�rialisation
     */
    private static final long serialVersionUID = -4333915526769223765L;

    @Override
    public TypePersonneEnum getTypePersonne() {
        return TypePersonneEnum.INDETERMINEE;
    }

}
