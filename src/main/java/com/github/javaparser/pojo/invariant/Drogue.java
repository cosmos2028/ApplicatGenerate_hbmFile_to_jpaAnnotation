/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.infraction.model;

/**
 * L'objet Drogue est associé à une infraction concernant la Drogue.
 */
public class Drogue implements java.io.Serializable, Cloneable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -5985736992287214322L;

    /** Identifiant de l'objet Drogue */
    private Long id;

    /** type de drogue : cocaïne, canabis */
    private TypeDrogue typeDrogue;

    /** Unité quantité de drogue saisie : gramme, kilogramme... */
    private UniteQuantiteDrogue uniteQuantiteDrogue;

    /** Infraction associée */
    private Infraction infraction;

    /** Quantité (poids) de drogue saisie */
    private Float valeurQuantiteDrogue;

    /**
     * Indique l'identifiant de l'objet Drogue
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Renseigne l'identifiant de l'objet Drogue
     *
     * @param id
     *            l'identifiant de l'objet Drogue
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Indique le type de drogue
     */
    public TypeDrogue getTypeDrogue() {
        return this.typeDrogue;
    }

    /**
     * Renseigne le type de drogue
     *
     * @param typeDrogue
     *            le type de drogue
     */
    public void setTypeDrogue(TypeDrogue typeDrogue) {
        this.typeDrogue = typeDrogue;
    }

    /**
     * Indique l'unité associée à la quantité de drogue: gramme, kilo...
     */
    public UniteQuantiteDrogue getUniteQuantiteDrogue() {
        return this.uniteQuantiteDrogue;
    }

    /**
     * Renseigne l'unité associée à la quantité de drogue: gramme, kilo...
     *
     * @param uniteQuantiteDrogue
     *            l'unité de la quantité de drogue
     */
    public void setUniteQuantiteDrogue(UniteQuantiteDrogue uniteQuantiteDrogue) {
        this.uniteQuantiteDrogue = uniteQuantiteDrogue;
    }

    /**
     * Indique l'infraction pour laquelle la drogue est saisie
     */
    public Infraction getInfraction() {
        return this.infraction;
    }

    /**
     * Renseigne l'infraction pour laquelle la drogue a été saisie
     *
     * @param infraction
     *            l'infraction associée
     */
    public void setInfraction(Infraction infraction) {
        this.infraction = infraction;
    }

    /**
     * Indique la valeur de la quantité de drogue
     *
     * @return la valeur de la quantité de drogue
     */
    public Float getValeurQuantiteDrogue() {
        return this.valeurQuantiteDrogue;
    }

    /**
     * Renseigne la valeur de la quantité de drogue
     *
     * @param valeurQuantiteDrogue
     *            la valeur de la quantité de drogue
     */
    public void setValeurQuantiteDrogue(Float valeurQuantiteDrogue) {
        this.valeurQuantiteDrogue = valeurQuantiteDrogue;
    }

    @Override
    public String toString() {
        return toFormattedString();
    }

    /**
     * retourne une chaîne de caractères de la forme quantité unité type de drogue
     *
     * @return une chaîne de caractères de la forme quantité unité type de drogue
     */
    public String toFormattedString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" ");
        builder.append(valeurQuantiteDrogue);
        builder.append(" ");
        builder.append(uniteQuantiteDrogue.getLibelle());
        builder.append(" de ");
        builder.append(typeDrogue.getLibelle());
        return builder.toString();
    }

    /**
     * Retourne une copie non sauvée en base de cet objet Drogue. On supprime aussi l'association sur l'infraction.
     *
     * @throws CloneNotSupportedException
     */
    @Override
    public Drogue clone() throws CloneNotSupportedException {
        Drogue clone = (Drogue) super.clone();
        clone.setId(null); // surtout pas d'Id !
        clone.setInfraction(null);
        return clone;
    }
}
