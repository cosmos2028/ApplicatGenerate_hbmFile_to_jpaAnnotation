package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.gouv.justice.cassiopee.invariant.infraction.model.Infraction;
import fr.gouv.justice.cassiopee.invariant.infraction.model.LienPersonneInfraction;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.TypeReparationEnum;

public class DetailDommageInteretDi extends DetailDommageInteret {

    private static final long serialVersionUID = 1L;

    private Set<LienPersonneInfraction> lienDiPersonneInfraction = new HashSet<>();

    public DetailDommageInteretDi () {

    }

    public Set<LienPersonneInfraction> getLienDiPersonneInfraction() {
        return lienDiPersonneInfraction;
    }

    public void setLienDiPersonneInfraction(
            Set<LienPersonneInfraction> lienDiPersonneInfraction) {
        this.lienDiPersonneInfraction = lienDiPersonneInfraction;
    }

    public void addAuteurInfraction (LienPersonneInfraction lienPersonneInfraction) {

        lienDiPersonneInfraction.add(lienPersonneInfraction);
    }

    public void removeAuteurInfraction (LienPersonneInfraction lienPersonneInfraction) {

        lienDiPersonneInfraction.remove(lienPersonneInfraction);
    }

    public void addAuteurInfractions (List<LienPersonneInfraction> lienPersonneInfractions) {

        lienDiPersonneInfraction.addAll(lienPersonneInfractions);
    }

    public void removeAuteurInfractions (List<LienPersonneInfraction> lienPersonneInfractions) {

        lienDiPersonneInfraction.removeAll(lienPersonneInfractions);
    }


    @Override
    public TypeReparationEnum getTypeReparation() {
        return TypeReparationEnum.DI;
    }


    @Override
    public List<Personne> getAuteurs() {

        Set<Personne> listAuteurs = new HashSet<>();

        for (LienPersonneInfraction lienPersonneInfraction : lienDiPersonneInfraction ) {

            listAuteurs.add(lienPersonneInfraction.getPersonne());
        }

        return new ArrayList<>(listAuteurs);
    }


    @Override
    public List<Infraction> getInfractions() {

        Set<Infraction> listInfractions = new HashSet<>();

        for (LienPersonneInfraction lienPersonneInfraction : lienDiPersonneInfraction) {
            listInfractions.add(lienPersonneInfraction.getInfraction());
        }

        return new ArrayList<>(listInfractions);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime
                * result
                + ((lienDiPersonneInfraction == null) ? 0
                        : lienDiPersonneInfraction.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (obj == null || getClass() != obj.getClass())
            return false;
        DetailDommageInteretDi other = (DetailDommageInteretDi) obj;
        if (lienDiPersonneInfraction == null) {
            if (other.lienDiPersonneInfraction != null)
                return false;
        } else if (!lienDiPersonneInfraction
                .equals(other.lienDiPersonneInfraction))
            return false;
        return true;
    }
}
