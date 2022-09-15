/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;

/**
 * Type de scell�. Plus "fin" que les diff�rentes sous-classes de l'objet Scell�.
 */
public class SousTypeScelle extends Codification implements java.io.Serializable {

    /**
     * Identifiant de s�rialisation
     */
    private static final long serialVersionUID = -4299687613359470387L;

    /**
     * Le type de scell� correspondant au sous type.
     */
    private TypeScelle typeScelle;

    /**
     * R�f�rence � un article de loi.
     */
    private String referenceTexte;

    /**
     * Description explicite du sous type
     */
    private String description;

    /**
     * Retourne le type de scell�
     *
     * @return Returns the typeScelle.
     */
    public TypeScelle getTypeScelle() {
        return typeScelle;
    }

    /**
     * Valorise le type de scell�
     *
     * @param codeTypeScelle
     *            The codeTypeScelle to set.
     */
    public void setTypeScelle(TypeScelle typeScelle) {
        this.typeScelle = typeScelle;
    }

    /**
     * Retourne la description du sous type de scell�
     *
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Valorise la description du sous type de scell�
     *
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retourne la r�f�rence de loi sur le sous type de scell�
     *
     * @return Returns the referenceTexte.
     */
    public String getReferenceTexte() {
        return referenceTexte;
    }

    /**
     * Valorise la r�f�rence de loi sur le sous type de scell�
     *
     * @param referenceTexte
     *            The referenceTexte to set.
     */
    public void setReferenceTexte(String referenceTexte) {
        this.referenceTexte = referenceTexte;
    }

}
