package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import java.util.Date;

/**
 * Valeur caract�ristique de type Date
 */
public class ValeurCaracteristiqueDate extends ValeurCaracteristique<Date> {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -8555816065513884828L;

    private Date date;

    /**
     * r�serv� � hibernate
     */
    protected Date getDate() {
        return date;
    }

    /**
     * r�serv� � hibernate
     */
    protected void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Date getValue() {
        return this.date;
    }

    @Override
    public void setValue(Date value) {
        this.date = value;

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.gouv.justice.cassiopee.invariant.peineoumesure.model.ValeurCaracteristiquePeine#getTypeValeurCaracteristiqueEnum
     * ()
     */
    public TypeValeurCaracteristiqueEnum getTypeValeurCaracteristiqueEnum() {
        return TypeValeurCaracteristiqueEnum.DATE;
    }

}
