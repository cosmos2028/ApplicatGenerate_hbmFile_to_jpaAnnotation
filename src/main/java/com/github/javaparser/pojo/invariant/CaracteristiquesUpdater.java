/**
 *
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.referentiel.evenement.model.CaracteristiqueType;

/**
 * Composant permettant de prendre en compte toutes les caractéristiques d'un Evenement.
 *
 * @see GenericCaracteristiquesUpdater
 * @author cvb
 */
public class CaracteristiquesUpdater extends GenericCaracteristiquesUpdater<Evenement, Caracteristique> {

    /**
     * Constructeur de classe
     *
     * @param evenement
     */
    public CaracteristiquesUpdater(Evenement evenement) {
        super(evenement);
    }

    @Override
    protected Caracteristique constructCaracteristique(CaracteristiqueType caracteristiqueType, Evenement evenement) {
        Caracteristique caracteristique = caracteristiqueType.createCaracteristique();

        caracteristique.setCaracteristiqueType(caracteristiqueType);

        CaracteristiqueId caracteristiqueId = new CaracteristiqueId();
        caracteristiqueId.setCaracteristiqueType(caracteristiqueType);
        caracteristiqueId.setEvenement(evenement);
        caracteristique.setId(caracteristiqueId);

        caracteristique.setEvenement(evenement);

        return caracteristique;
    }

}
