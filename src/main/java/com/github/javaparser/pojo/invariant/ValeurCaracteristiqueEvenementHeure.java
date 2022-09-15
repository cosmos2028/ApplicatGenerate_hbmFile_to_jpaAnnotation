/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */

package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import java.sql.Time;

import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum;

/**
 * objet modele pour les caracteristique de type date
 *
 * @see fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum#HEURE
 */
public class ValeurCaracteristiqueEvenementHeure extends ValeurCaracteristiqueEvenementExterieur<Time> {

    private static final long serialVersionUID = 1435164134308784127L;

    private Time valeurHeure;

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getFormat()
     */
    public FormatCaracteristiqueEnum getFormat() {
        return FormatCaracteristiqueEnum.HEURE;
    }

    /**
     * réservé à hibernate
     */
    protected Time getValeurHeure() {
        return valeurHeure;
    }

    /**
     * réservé à hibernate
     */
    protected void setValeurHeure(Time value) {
        this.valeurHeure = value;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getValue()
     */
    @Override
    public Time getValue() {
        return this.valeurHeure;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#setValue()
     */
    @Override
    public void setValue(Time value) {
        this.valeurHeure = value;
    }
}
