package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.gouv.justice.cassiopee.invariant.infraction.model.Infraction;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.invariant.personne.model.RolePersonne;
import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.TypeReparationEnum;

public class DetailDommageInteretArticle4751 extends DetailDommageInteret {

    private static final long serialVersionUID = 1L;


    @Override
    public TypeReparationEnum getTypeReparation() {
        return TypeReparationEnum.ARTICLE4751;
    }

    public void removeAuteur (Personne auteur) {
        lienDiPersonne.remove(auteur);
    }

    public void removeAuteurs (List<Personne> listAuteurs) {
        lienDiPersonne.removeAll(listAuteurs);
    }

    @Override
    public List<Personne> getAuteurs() {

        Set<Personne> listAuteurs = new HashSet<>();

        for (Personne personne : lienDiPersonne) {

            if(RolePersonne.AUTEUR.getCode().equals(personne.getRolePersonne().getCode())) {
                listAuteurs.add(personne);
            }
        }

        return new ArrayList<>(listAuteurs);
    }


    @Override
    public List<Infraction> getInfractions() {
        return new ArrayList<>();
    }
}
