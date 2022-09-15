/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.peine;

import java.math.BigDecimal;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.TypeValeurCaracteristiqueEnum;

/**
 * Valeur caractéristique de type Montant
 */
public class ValeurCaracteristiquePeineMontant extends ValeurCaracteristiquePeineExterieur<BigDecimal> {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -6624723603556809792L;

    private BigDecimal montant;

    /**
     * réservé à hibernate
     */
    protected BigDecimal getMontant() {
        return montant;
    }

    /**
     * réservé à hibernate
     */
    protected void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    @Override
    public BigDecimal getValue() {
        return this.montant;
    }

    @Override
    public void setValue(BigDecimal value) {
        this.montant = value;

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.gouv.justice.cassiopee.invariant.peineoumesure.model.ValeurCaracteristiquePeine#getTypeValeurCaracteristiqueEnum
     * ()
     */
    public TypeValeurCaracteristiqueEnum getTypeValeurCaracteristiqueEnum() {
        return TypeValeurCaracteristiqueEnum.MONTANT;
    }
}
