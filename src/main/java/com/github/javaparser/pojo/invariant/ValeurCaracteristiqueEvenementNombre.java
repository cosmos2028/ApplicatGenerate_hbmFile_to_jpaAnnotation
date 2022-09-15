/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */

package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum;

/**
 * objet modele pour les caracteristique de type nombre
 *
 * @see fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum#NOMBRE
 */
public class ValeurCaracteristiqueEvenementNombre extends ValeurCaracteristiqueEvenementExterieur<Double> {

    private static final long serialVersionUID = -8985460385942713747L;

    private Double valeurNombre;

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getFormat()
     */
    public FormatCaracteristiqueEnum getFormat() {
        return FormatCaracteristiqueEnum.NOMBRE;
    }

    /**
     * r�serv� � hibernate
     */
    protected Double getValeurNombre() {
        return valeurNombre;
    }

    /**
     * r�serv� � hibernate
     */
    protected void setValeurNombre(Double valeurNombre) {
        this.valeurNombre = valeurNombre;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getValue()
     */
    @Override
    public Double getValue() {
        return this.valeurNombre;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#setValue()
     */
    @Override
    public void setValue(Double value) {
        this.valeurNombre = value;
    }
}