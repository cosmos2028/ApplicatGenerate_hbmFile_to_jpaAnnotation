package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.common.util.DureeAnMoisJour;

/**
 * Valeur caract�ristique de type Date
 */
public class ValeurCaracteristiqueDuree extends ValeurCaracteristique<DureeAnMoisJour> {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 1538539254156977956L;

    private DureeAnMoisJour duree;

    /**
     * r�serv� � hibernate
     */
    protected DureeAnMoisJour getDuree() {
        return duree;
    }

    /**
     * r�serv� � hibernate
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
