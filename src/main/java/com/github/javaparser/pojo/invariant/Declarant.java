/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import fr.gouv.justice.cassiopee.referentiel.codification.model.Civilite;

/**
 * Classe métier Declarant
 */
public class Declarant implements java.io.Serializable {

    private static final long serialVersionUID = -5949316350308999181L;

    private Civilite civilite;

    private String nom;

    private String prenom;

    private String situationSociete;

    /**
     * @return retourne civilite.
     */
    public Civilite getCivilite() {
        return civilite;
    }

    /**
     * @param civilite
     *            affecte civilite
     */
    public void setCivilite(Civilite civilite) {
        this.civilite = civilite;
    }

    /**
     * @return retourne nom.
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom
     *            affecte nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return retourne prenom.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom
     *            affecte prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return retourne situationSociete.
     */
    public String getSituationSociete() {
        return situationSociete;
    }

    /**
     * @param situationSociete
     *            affecte situationSociete
     */
    public void setSituationSociete(String situationSociete) {
        this.situationSociete = situationSociete;
    }
}
