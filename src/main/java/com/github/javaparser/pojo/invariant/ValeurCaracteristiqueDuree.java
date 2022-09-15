package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.common.util.DureeAnMoisJour;

/**
 * Valeur caractéristique de type Date
 */
public class ValeurCaracteristiqueDuree extends ValeurCaracteristique<DureeAnMoisJour> {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 1538539254156977956L;

    private DureeAnMoisJour duree;

    /**
     * réservé à hibernate
     */
    protected DureeAnMoisJour getDuree() {
        return duree;
    }

    /**
     * réservé à hibernate
     */
    protected void setDuree(DureeAnMoisJour duree) {
        this.duree = duree;
    }

    @Override
    public DureeAnMoisJour getValue() {
        return this.duree;
    }

    @Override
    public void setValue(DureeAnMoisJour value) {
        this.duree = value;

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.gouv.justice.cassiopee.invariant.peineoumesure.model.ValeurCaracteristiquePeine#getTypeValeurCaracteristiqueEnum
     * ()
     */
    public TypeValeurCaracteristiqueEnum getTypeValeurCaracteristiqueEnum() {
        return TypeValeurCaracteristiqueEnum.DUREE;
    }
}
