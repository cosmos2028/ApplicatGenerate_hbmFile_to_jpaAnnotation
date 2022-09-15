package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

/**
 * Valeur caractéristique de type chaine
 */
public class ValeurCaracteristiqueChaine extends ValeurCaracteristique<String> {

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