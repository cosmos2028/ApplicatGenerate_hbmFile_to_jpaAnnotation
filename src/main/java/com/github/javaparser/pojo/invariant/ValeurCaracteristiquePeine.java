package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.TypeCaractPeineMesure;

/**
 * mod�le g�n�rique d'une valeur de caract�ristique de peine et mesure
 *
 * @param <E>
 *            type de valeur
 */
public interface ValeurCaracteristiquePeine<E> extends java.io.Serializable {

    /**
     * m�thode pour recup�rer de fa�on g�n�rique la valeur de la caracteristique
     *
     * @return E
     */
    E getValue();

    /**
     * m�thode pour affecter de fa�on g�n�rique la valeur de la caracteristique
     *
     * @param value
     */
    void setValue(E value);

    /**
     * retourne le type de caract�ristique
     *
     * @return le type de caract�ristique
     */
    TypeCaractPeineMesure getTypeCaractPeineMesure();

    /**
     * renseigne le le type de caract�ristique
     *
     * @param typeCaractPeineMesure
     *            le type de caract�ristique
     */
    void setTypeCaractPeineMesure(TypeCaractPeineMesure typeCaractPeineMesure);

    /**
     * @return TypeValeurCaracteristiqueEnum
     */
    TypeValeurCaracteristiqueEnum getTypeValeurCaracteristiqueEnum();
}
