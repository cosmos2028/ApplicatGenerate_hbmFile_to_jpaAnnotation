/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.CaracteristiqueType;

/**
 * La valeur d'une caractéristique de l'événement. Exemple événement/caractéristique/valeur de caractéristique : Un
 * événement de "signification" a comme caractéristique "mode de signification" et comme valeur "à personne". Dans DIE,
 * les caractéristiques ne sont valorisées que pour un événement de signification ou de notification. Pour l'événement
 * portant dispositif ou pour les événements de recherche et détention, elles ne sont pas valorisées.
 *
 * @param <E>
 */
public abstract class ValeurCaracteristiqueEvenementExterieur<E> implements CaracteristiqueEvenement<E> {

    private int rang;

    private ValeurCaracteristiqueEvenementExterieurId id;

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public CaracteristiqueType getCaracteristiqueType() {
        if (id != null) {
            return id.getCaracteristiqueType();
        } else {
            return null;
        }
    }

    public void setCaracteristiqueType(CaracteristiqueType caracteristiqueType) {
        if (id == null) {
            id = new ValeurCaracteristiqueEvenementExterieurId();
        }
        id.setCaracteristiqueType(caracteristiqueType);
    }

    public ValeurCaracteristiqueEvenementExterieurId getId() {
        return id;
    }

    public void setId(ValeurCaracteristiqueEvenementExterieurId id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getValue()
     */
    public abstract E getValue();

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#setValue(null)
     */
    public abstract void setValue(E value);

}