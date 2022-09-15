package fr.gouv.justice.cassiopee.invariant.evenement.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.CaracteristiqueType;

/**
 * Représente la valeur d'une caractéristique de l'événement. Exemple événement/caractéristique/valeur de
 * caractéristique : un événement de "citation" a comme caractéristique "mode de citation" et comme valeur "à personne"
 *
 * @param <E>
 *            type de la valeur
 */
@SuppressWarnings("nls")
public abstract class Caracteristique<E> implements CaracteristiqueEvenement<E> {
    private static final long serialVersionUID = 4271985771495888995L;

    private int rang;

    private CaracteristiqueId id;

    private Evenement evenement;

    private CaracteristiqueType caracteristiqueType;

    /**
     * @return retourne evenement.
     */
    public Evenement getEvenement() {
        return evenement;
    }

    /**
     * @param evenement
     *            affecte evenement
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    /**
     * Rang
     *
     * @return rang
     */
    public int getRang() {
        return rang;
    }

    /**
     * Rang
     *
     * @param rang
     *            le rang
     */
    public void setRang(int rang) {
        this.rang = rang;
    }

    /**
     * @return retourne caracteristiqueType.
     */
    public CaracteristiqueType getCaracteristiqueType() {
        return caracteristiqueType;
    }

    /**
     * @param caracteristiqueType
     *            affecte caracteristiqueType
     */
    public void setCaracteristiqueType(CaracteristiqueType caracteristiqueType) {
        this.caracteristiqueType = caracteristiqueType;
    }

    /**
     * @return retourne id.
     */
    public CaracteristiqueId getId() {
        return id;
    }

    /**
     * @param id
     *            affecte id
     */
    public void setId(CaracteristiqueId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("type", getFormat()).append("value",
                getValue()).toString();
    }
    
    public abstract Caracteristique<E> dupliquer();
}
