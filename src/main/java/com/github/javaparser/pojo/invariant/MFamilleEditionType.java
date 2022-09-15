/*
 * Ce document est la propriété d?Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
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
