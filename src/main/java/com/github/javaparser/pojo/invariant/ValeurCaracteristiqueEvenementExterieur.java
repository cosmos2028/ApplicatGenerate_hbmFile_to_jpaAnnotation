/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.CaracteristiqueType;

/**
 * La valeur d'une caract�ristique de l'�v�nement. Exemple �v�nement/caract�ristique/valeur de caract�ristique : Un
 * �v�nement de "signification" a comme caract�ristique "mode de signification" et comme valeur "� personne". Dans DIE,
 * les caract�ristiques ne sont valoris�es que pour un �v�nement de signification ou de notification. Pour l'�v�nement
 * portant dispositif ou pour les �v�nements de recherche et d�tention, elles ne sont pas valoris�es.
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