package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

/**
 * Valeur caractéristique de type Nombre Entier
 */
public class ValeurCaracteristiqueNombreEntier extends ValeurCaracteristique<Long> {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 42841313825459580L;

    private Long nombre;

    /**
     * réservé à hibernate
     */
    protected Long getNombre() {
        return nombre;
    }

    /**
     * réservé à hibernate
     */
    protected void setNombre(Long nombre) {
        this.nombre = nombre;
    }

    @Override
    public Long getValue() {
        return this.nombre;
    }

    @Override
    public void setValue(Long value) {
        this.nombre = value;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.gouv.justice.cassiopee.invariant.peineoumesure.model.ValeurCaracteristiquePeine#getTypeValeurCaracteristiqueEnum
     * ()
     */
    public TypeValeurCaracteristiqueEnum getTypeValeurCaracteristiqueEnum() {
        return TypeValeurCaracteristiqueEnum.NOMBRE_ENTIER;
    }
}
