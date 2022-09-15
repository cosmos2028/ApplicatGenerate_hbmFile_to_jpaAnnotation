/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;

/**
 * Type de scellé. Plus "fin" que les différentes sous-classes de l'objet Scellé.
 */
public class SousTypeScelle extends Codification implements java.io.Serializable {

    /**
     * Identifiant de sérialisation
     */
    private static final long serialVersionUID = -4299687613359470387L;

    /**
     * Le type de scellé correspondant au sous type.
     */
    private TypeScelle typeScelle;

    /**
     * Référence à un article de loi.
     */
    private String referenceTexte;

    /**
     * Description explicite du sous type
     */
    private String description;

    /**
     * Retourne le type de scellé
     *
     * @return Returns the typeScelle.
     */
    public TypeScelle getTypeScelle() {
        return typeScelle;
    }

    /**
     * Valorise le type de scellé
     *
     * @param codeTypeScelle
     *            The codeTypeScelle to set.
     */
    public void setTypeScelle(TypeScelle typeScelle) {
        this.typeScelle = typeScelle;
    }

    /**
     * Retourne la description du sous type de scellé
     *
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Valorise la description du sous type de scellé
     *
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retourne la référence de loi sur le sous type de scellé
     *
     * @return Returns the referenceTexte.
     */
    public String getReferenceTexte() {
        return referenceTexte;
    }

    /**
     * Valorise la référence de loi sur le sous type de scellé
     *
     * @param referenceTexte
     *            The referenceTexte to set.
     */
    public void setReferenceTexte(String referenceTexte) {
        this.referenceTexte = referenceTexte;
    }

}
