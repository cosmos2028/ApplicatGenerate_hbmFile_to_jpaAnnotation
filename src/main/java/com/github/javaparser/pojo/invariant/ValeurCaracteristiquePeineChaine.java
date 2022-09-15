/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.peine;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.TypeValeurCaracteristiqueEnum;

/**
 * Valeur caractéristique de type chaine
 */
public class ValeurCaracteristiquePeineChaine extends ValeurCaracteristiquePeineExterieur<String> {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 3057482680007167423L;

    private String chaine;

    /**
     * réservé à hibernate
     */
    protected String getChaine() {
        return chaine;
    }

    /**
     * réservé à hibernate
     */
    protected void setChaine(String chaine) {
        this.chaine = chaine;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.exterieur.model.peine.ValeurCaracteristiquePeineExterieur#getValue()
     */
    @Override
    public String getValue() {
        return this.chaine;
    }

    @Override
    public void setValue(String value) {
        this.chaine = value;

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.gouv.justice.cassiopee.invariant.peineoumesure.model.ValeurCaracteristiquePeine#getTypeValeurCaracteristiqueEnum
     * ()
     */
    public TypeValeurCaracteristiqueEnum getTypeValeurCaracteristiqueEnum() {
        return TypeValeurCaracteristiqueEnum.CHAINE;
    }
}
