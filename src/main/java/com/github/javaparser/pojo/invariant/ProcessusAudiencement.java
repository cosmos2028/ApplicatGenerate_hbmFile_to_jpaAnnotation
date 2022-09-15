/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;

/**
 * Un processus audiencement est caractérisé par un ensemble d éditions-type. Cette structure permet de gérer des listes
 * d?éditions-type (paramétrage national), modifiable localement.<br>
 * Si un processus n''appartient pas à un TGI (multiplicité 0 sur l''association appartient a), le processus est
 * national. Si un processus appartient à un TGI (multiplicité 1 sur l''association "appartient à"), le processus est
 * local.<br>
 * Un processus local est créé à partir d''un processus national en retirant des éditions types présentes dans le
 * processus national.<br>
 * Le choix de la modélisation est de privilégier une édition type (classe maître édition type - prendre une édition
 * type déclenchée par un evt-type) au sein du processus audiencement et de lier le processus aux autres éditions le
 * composant (classe édition type groupée).<br>
 * Une édition type peut apparaître dans plusieurs processus<br>
 * ajout à la description fonctionnelle : techniquement une relation plusieurs à plusieurs entre les processus et les
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
