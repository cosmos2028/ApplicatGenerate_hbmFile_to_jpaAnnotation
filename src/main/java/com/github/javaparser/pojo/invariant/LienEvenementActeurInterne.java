package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.administration.acteur.interne.model.ActeurInterne;

/**
 * Le lien evenement acteur interne associe un evenement a un acteur interne a partir de sa caracteristique.
 */
public class LienEvenementActeurInterne implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6774356834839214856L;

    /** Identifiant de l'objet LienPersonneInfraction */
    private Long id;

    /** Personne associée */
    private ActeurInterne acteurInterne;

    /** Evenement associé */
    private Evenement evenement;

    /** Caracteristique concernee */
    private String codeCaracteristiqueType;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the acteurInterne
     */
    public ActeurInterne getActeurInterne() {
        return acteurInterne;
    }

    /**
     * @param acteurInterne
     *            the acteurInterne to set
     */
    public void setActeurInterne(ActeurInterne acteurInterne) {
        this.acteurInterne = acteurInterne;
    }

    /**
     * @return the evenement
     */
    public Evenement getEvenement() {
        return evenement;
    }

    /**
     * @param evenement
     *            the evenement to set
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    /**
     * @return the codeCaracteristiqueType
     */
    public String getCodeCaracteristiqueType() {
        return codeCaracteristiqueType;
    }

    /**
     * @param codeCaracteristiqueType
     *            the codeCaracteristiqueType to set
     */
    public void setCodeCaracteristiqueType(String codeCaracteristiqueType) {
        this.codeCaracteristiqueType = codeCaracteristiqueType;
    }

}
