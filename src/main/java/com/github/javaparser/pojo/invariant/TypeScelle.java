/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;
import fr.gouv.justice.cassiopee.invariant.scelle.service.enumeration.TypePieceConvictionEnum;

/**
 * Type de scell�. Plus "fin" que les diff�rentes sous-classes de l'objet Scell�.
 */
public class TypeScelle extends Codification implements java.io.Serializable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -4299687613359470387L;

    /** cat�gorie du type de scell� */
    private String specialisationPiece;

    /** Identifiant version pour la s�rialisation */
    private Set<SousTypeScelle> sousTypeScelles = new HashSet<SousTypeScelle>();

    /** liste des sous type de scell� correspondant au type de scell� */
    public TypePieceConvictionEnum getPieceConvictionEnum() {
        return TypePieceConvictionEnum.findTypePieceConvictionEnumByCode(specialisationPiece);
    }

    /**
     * Retourne la liste des sousTypeScelle associ�s au type de Scell�
     *
     * @return Returns the sousTypeScelles.
     */
    public Set<SousTypeScelle> getSousTypeScelles() {
        return sousTypeScelles;
    }

    /**
     * Valorise la liste des sousTypeScelle associ�s au type de Scell�
     *
     * @param sousTypeScelles
     *            The sousTypeScelles to set.
     */
    public void setSousTypeScelles(Set<SousTypeScelle> sousTypeScelles) {
        this.sousTypeScelles = sousTypeScelles;
    }

}
