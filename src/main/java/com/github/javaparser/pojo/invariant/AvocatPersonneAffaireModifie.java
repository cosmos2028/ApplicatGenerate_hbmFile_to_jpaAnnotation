package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;

import fr.gouv.justice.cassiopee.administration.acteur.externe.model.ActeurExterne;
import fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire;

/**
 * AvocatPersonne de la personne de l'affaire.
 */
public class AvocatPersonneAffaireModifie implements Serializable {

    private static final long serialVersionUID = 3476889349762083587L;

    private Long id;

    /**
     * R�f�rence de AvocatPersonneAffaireModifie vers ActeurExterne
     */
    private ActeurExterne avocat;

    /**
     * R�f�rence de AvocatPersonneAffaireModifie vers l'Affaire
     */
    private Affaire affaire;

    private String champsModifies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActeurExterne getAvocat() {
        return avocat;
    }

    public void setAvocat(ActeurExterne avocat) {
        this.avocat = avocat;
    }

    public Affaire getAffaire() {
        return affaire;
    }

    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
    }

    public String getChampsModifies() {
        return champsModifies;
    }

    public void setChampsModifies(String champsModifies) {
        this.champsModifies = champsModifies;
    }
}
