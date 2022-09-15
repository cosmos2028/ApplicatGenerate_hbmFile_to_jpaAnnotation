/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
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
     * r�serv� � hibernate
     */
    protected Time getValeurHeure() {
        return valeurHeure;
    }

    /**
     * r�serv� � hibernate
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
