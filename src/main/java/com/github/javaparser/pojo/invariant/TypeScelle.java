/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;
import fr.gouv.justice.cassiopee.invariant.scelle.service.enumeration.TypePieceConvictionEnum;

/**
 * Type de scellé. Plus "fin" que les différentes sous-classes de l'objet Scellé.
 */
public class TypeScelle extends Codification implements java.io.Serializable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -4299687613359470387L;

    /** catégorie du type de scellé */
    private String specialisationPiece;

    /** Identifiant version pour la sérialisation */
    private Set<SousTypeScelle> sousTypeScelles = new HashSet<SousTypeScelle>();

    /** liste des sous type de scellé correspondant au type de scellé */
    public TypePieceConvictionEnum getPieceConvictionEnum() {
        return TypePieceConvictionEnum.findTypePieceConvictionEnumByCode(specialisationPiece);
    }

    /**
     * Retourne la liste des sousTypeScelle associés au type de Scellé
     *
     * @return Returns the sousTypeScelles.
     */
    public Set<SousTypeScelle> getSousTypeScelles() {
        return sousTypeScelles;
    }

    /**
     * Valorise la liste des sousTypeScelle associés au type de Scellé
     *
     * @param sousTypeScelles
     *            The sousTypeScelles to set.
     */
    public void setSousTypeScelles(Set<SousTypeScelle> sousTypeScelles) {
        this.sousTypeScelles = sousTypeScelles;
    }

}
