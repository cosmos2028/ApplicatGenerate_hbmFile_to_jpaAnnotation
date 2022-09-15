package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.TypeCaractPeineMesure;

/**
 * modèle générique d'une valeur de caractéristique de peine et mesure
 *
 * @param <E>
 *            type de valeur
 */
public interface ValeurCaracteristiquePeine<E> extends java.io.Serializable {

    /**
     * méthode pour recupérer de façon générique la valeur de la caracteristique
     *
     * @return E
     */
    E getValue();

    /**
     * méthode pour affecter de façon générique la valeur de la caracteristique
     *
     * @param value
     */
    void setValue(E value);

    /**
     * retourne le type de caractéristique
     *
     * @return le type de caractéristique
     */
    TypeCaractPeineMesure getTypeCaractPeineMesure();

    /**
     * renseigne le le type de caractéristique
     *
     * @param typeCaractPeineMesure
     *            le type de caractéristique
     */
    void setTypeCaractPeineMesure(TypeCaractPeineMesure typeCaractPeineMesure);

    /**
     * @return TypeValeurCaracteristiqueEnum
     */
    TypeValeurCaracteristiqueEnum getTypeValeurCaracteristiqueEnum();
}
