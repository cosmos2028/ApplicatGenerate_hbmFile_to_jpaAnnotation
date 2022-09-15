/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;

/**
 * Un processus audiencement est caract�ris� par un ensemble d �ditions-type. Cette structure permet de g�rer des listes
 * d?�ditions-type (param�trage national), modifiable localement.<br>
 * Si un processus n''appartient pas � un TGI (multiplicit� 0 sur l''association appartient a), le processus est
 * national. Si un processus appartient � un TGI (multiplicit� 1 sur l''association "appartient �"), le processus est
 * local.<br>
 * Un processus local est cr�� � partir d''un processus national en retirant des �ditions types pr�sentes dans le
 * processus national.<br>
 * Le choix de la mod�lisation est de privil�gier une �dition type (classe ma�tre �dition type - prendre une �dition
 * type d�clench�e par un evt-type) au sein du processus audiencement et de lier le processus aux autres �ditions le
 * composant (classe �dition type group�e).<br>
 * Une �dition type peut appara�tre dans plusieurs processus<br>
 * ajout � la description fonctionnelle : techniquement une relation plusieurs � plusieurs entre les processus et les
 * editions types et une restriction possible par tgi sur ces liens.<br>
 */
@SuppressWarnings("nls")
public class ProcessusAudiencement extends Codification {

    private static final long serialVersionUID = 1165486161486468946L;

    private Boolean indicateurActive;

    private String commentaire;

    private Set<EditionType> editions = new HashSet<EditionType>();

    private Set<LienEditionTypeProcessusAudiencement> editionTypeProcessusAudiencements;

    /**
     * @return retourne commentaire.
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * @param commentaire
     *            affecte commentaire
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * @return retourne indicateurActive.
     */
    public Boolean getIndicateurActive() {
        return indicateurActive;
    }

    /**
     * @param indicateurActive
     *            affecte indicateurActive
     */
    public void setIndicateurActive(Boolean indicateurActive) {
        this.indicateurActive = indicateurActive;
    }

    /**
     * @return Returns the editions.
     */
    public Set<EditionType> getEditions() {
        return editions;
    }

    /**
     * @param editions
     *            The editions to set.
     */
    public void setEditions(Set<EditionType> editions) {
        this.editions = editions;
    }

    /**
     * @return
     */
    public boolean isNational() {
        return this instanceof ProcessusAudiencementNational;
    }

}
