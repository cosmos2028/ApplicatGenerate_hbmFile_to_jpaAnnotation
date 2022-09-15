/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */

package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import fr.gouv.justice.cassiopee.common.util.DureeAnMoisJour;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum;

/**
 * objet modele pour les caracteristique de type date
 *
 * @see fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum#DUREE
 */
public class ValeurCaracteristiqueEvenementDuree extends ValeurCaracteristiqueEvenementExterieur<DureeAnMoisJour> {

    private static final long serialVersionUID = -4770676327422623963L;

    private DureeAnMoisJour valeurDuree;

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getFormat()
     */
    public FormatCaracteristiqueEnum getFormat() {
        return FormatCaracteristiqueEnum.DUREE;
    }

    /**
     * réservé à hibernate
     */
    protected DureeAnMoisJour getValeurDuree() {
        return valeurDuree;
    }

    /**
     * réservé à hibernate
     */
    protected void setValeurDuree(DureeAnMoisJour value) {
        this.valeurDuree = value;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getValue()
     */
    @Override
    public DureeAnMoisJour getValue() {
        return this.valeurDuree;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#setValue()
     */
    @Override
    public void setValue(DureeAnMoisJour value) {
        this.valeurDuree = value;
    }
}