/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */

package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import fr.gouv.justice.cassiopee.referentiel.evenement.model.EnumerationValue;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum;

/**
 * objet modele pour les caracteristiques de type enumeration
 *
 * @see fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum#ENUMERATION
 */
public class ValeurCaracteristiqueEvenementEnumeration extends
        ValeurCaracteristiqueEvenementExterieur<EnumerationValue> {

    private static final long serialVersionUID = 1435164134308784127L;

    private EnumerationValue valeurEnumeration;

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getFormat()
     */
    public FormatCaracteristiqueEnum getFormat() {
        return FormatCaracteristiqueEnum.ENUMERATION;
    }

    /**
     * réservé à hibernate
     */
    protected EnumerationValue getValeurEnumeration() {
        return valeurEnumeration;
    }

    /**
     * réservé à hibernate
     */
    protected void setValeurEnumeration(EnumerationValue valeurEnumeration) {
        this.valeurEnumeration = valeurEnumeration;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getValue()
     */
    @Override
    public EnumerationValue getValue() {
        return this.valeurEnumeration;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#setValue()
     */
    @Override
    public void setValue(EnumerationValue value) {
        this.valeurEnumeration = value;
    }
}