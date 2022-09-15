/*
 * Ce document est la propri�t� d?Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;

/**
 * Objet du modele representant une famille des editions types
 */
public class MFamilleEditionType extends Codification {

    private static final long serialVersionUID = 8940629267989918805L;

    private Set<EditionType> editionTypes = new HashSet<EditionType>();

    /**
     * @return editionTypes
     */
    public Set<EditionType> getEditionTypes() {
        return editionTypes;
    }

    /**
     * @param editionTypes
     */
    public void setEditionTypes(Set<EditionType> editionTypes) {
        this.editionTypes = editionTypes;
    }

}
