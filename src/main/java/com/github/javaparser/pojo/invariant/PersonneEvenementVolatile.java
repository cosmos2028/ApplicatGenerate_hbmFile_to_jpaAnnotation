package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

import fr.gouv.justice.cassiopee.invariant.evenement.model.Volatile;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;

/**
 * sert a enregistrer les donnnees volatiles d'une personne d'un evenement
 *
 * @author gfiuser
 */
public class PersonneEvenementVolatile {

    private Long id;

    private Volatile evenementVolatile;

    private Personne personne;

    private String observationProcureur;

    public Long getId() {
        return id;
    }

    public Volatile getEvenementVolatile() {
        return evenementVolatile;
    }

    public Personne getPersonne() {
        return personne;
    }

    public String getObservationProcureur() {
        return observationProcureur;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEvenementVolatile(Volatile evenementVolatile) {
        this.evenementVolatile = evenementVolatile;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public void setObservationProcureur(String observationProcureur) {
        this.observationProcureur = observationProcureur;
    }

}
