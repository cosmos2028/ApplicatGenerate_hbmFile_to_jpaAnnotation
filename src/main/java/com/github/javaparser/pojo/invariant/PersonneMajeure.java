package fr.gouv.justice.cassiopee.invariant.personne.model;

import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.TypePersonneEnum;

/**
 * Personne physique majeure (plus de 18 ans). Ne concerne pas les jeunes majeurs suivis par le Juge des Enfants, qui
 * sont pris en compte par la classe Personne mineure.
 */
public class PersonneMajeure extends PersonnePhysique {

    /** Identifiant de sérialisation */
    private static final long serialVersionUID = 2024152722282756047L;

    @Override
    public TypePersonneEnum getTypePersonne() {
        return TypePersonneEnum.MAJEURE;
    }
}
