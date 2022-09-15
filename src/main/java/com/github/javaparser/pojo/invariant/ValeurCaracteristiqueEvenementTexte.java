/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */

package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum;

/**
 * objet modele pour les caracteristique de type date
 *
 * @see fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum#TEXTE
 */
public class ValeurCaracteristiqueEvenementTexte extends ValeurCaracteristiqueEvenementExterieur<String> {

    private static final long serialVersionUID = -8684048943793856995L;

    private String valeurTexte;

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getFormat()
     */
    public FormatCaracteristiqueEnum getFormat() {
        return FormatCaracteristiqueEnum.TEXTE;
    }

    /**
     * réservé à hibernate
     */
    protected String getValeurTexte() {
        return valeurTexte;
    }

    /**
     * réservé à hibernate
     */
    protected void setValeurTexte(String value) {
        this.valeurTexte = value;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getValue()
     */
    @Override
    public String getValue() {
        return this.valeurTexte;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#setValue()
     */
    @Override
    public void setValue(String value) {
        this.valeurTexte = value;
    }
}
