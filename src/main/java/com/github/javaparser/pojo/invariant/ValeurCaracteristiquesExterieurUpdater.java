/**
 *
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import fr.gouv.justice.cassiopee.invariant.evenement.model.GenericCaracteristiquesUpdater;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.CaracteristiqueType;

/**
 * @author cvb
 */
public class ValeurCaracteristiquesExterieurUpdater extends
        GenericCaracteristiquesUpdater<EvenementExterieur, ValeurCaracteristiqueEvenementExterieur> {

    /**
     * Constructeur de classe
     *
     * @param evenement
     */
    public ValeurCaracteristiquesExterieurUpdater(EvenementExterieur evenement) {
        super(evenement);
    }

    /**
     * Constructeur de classe
     *
     * @param evenement
     * @param updateEvenementCaracs
     *            preciser si on fait des update base ou pas (false dans le cas des editions)
     */
    public ValeurCaracteristiquesExterieurUpdater(EvenementExterieur evenement, boolean updateEvenementCaracs) {
        super(evenement);
        setUpdateEvenementCaracs(updateEvenementCaracs);
    }

    @Override
    protected ValeurCaracteristiqueEvenementExterieur constructCaracteristique(CaracteristiqueType caracteristiqueType,
            EvenementExterieur evenement) {
        ValeurCaracteristiqueEvenementExterieur caracteristique = ValeurCaracteristiqueEvenementExterieurFactory
                .createValeurCaracteristiqueEvenementExterieur(caracteristiqueType.getFormat());

        caracteristique.setCaracteristiqueType(caracteristiqueType);
        if (isUpdateEvenementCaracs()) {
            ValeurCaracteristiqueEvenementExterieurId caracteristiqueId = new ValeurCaracteristiqueEvenementExterieurId();
            caracteristiqueId.setCaracteristiqueType(caracteristiqueType);
            caracteristiqueId.setEvenementExterieur(evenement);
            caracteristique.setId(caracteristiqueId);
        }
        return caracteristique;
    }

}
