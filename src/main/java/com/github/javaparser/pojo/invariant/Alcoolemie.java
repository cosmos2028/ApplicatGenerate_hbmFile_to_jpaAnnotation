/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.infraction.model;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;

/**
 * Présence d'alcool dans le sang ou dans les poumons. Le taux d'alcoolémie indique le résultat de la mesure de
 * l'alcoolémie. L'unité est fonction de la mesure mg/litre d'air ou g/litre de sang.
 */
public class Alcoolemie implements java.io.Serializable, Cloneable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 6211794983999897563L;

    private static final HashCodeEqualsHelper<Alcoolemie> HE_HELPER = HashCodeEqualsHelper.of(Alcoolemie.class, Alcoolemie::getTaux, Alcoolemie::getUnite);


    /** Unité de la mesure mg/litre d'air ou g/litre de sang * */
    private UniteTauxAlcoolemie unite;

    /** Taux : valeur de la mesure de l'alcoolémie */
    private Float taux;

    /**
     * Indique le taux d'aloolémie
     *
     * @return le taux d'alcoolémie
     */
    public Float getTaux() {
        return taux;
    }

    /**
     * Renseigne le taux d'alcoolémie
     *
     * @param taux
     *            taux d'alcoolémie
     */
    public void setTaux(Float taux) {
        this.taux = taux;
    }

    /**
     * Indique l'unité de la mesure de l'aloolémie
     *
     * @return l'unité de la mesure
     */
    public UniteTauxAlcoolemie getUnite() {
        return unite;
    }

    /**
     * Renseigne l'unité de la mesure de l'alcoolémie
     *
     * @param unite
     *            unité de la mesure
     */
    public void setUnite(UniteTauxAlcoolemie unite) {
        this.unite = unite;
    }

    @Override
    public String toString() {
        return toFormattedString();
    }

    /**
     * retourne une chaîne de caractères de la forme taux unité)
     *
     * @return une chaîne de caractères de la forme taux unité)
     */
    public String toFormattedString() {
        StringBuilder builder = new StringBuilder();
        builder.append(taux);
        builder.append(" ");
        //Mantis 0101745: Erreur Java sur la page "outil gestion infraction"
        builder.append(unite == null ? null : unite.getLibelle());
        return builder.toString();
    }

    @Override
    public Alcoolemie clone() throws CloneNotSupportedException {
        Alcoolemie clone = (Alcoolemie) super.clone();
        clone.taux = new Float(this.taux.doubleValue());
        return clone;
    }


    @Override
    public boolean equals(Object obj){
        return HE_HELPER.areEqual(this, obj);
    }

    @Override
    public int hashCode() {
        return HE_HELPER.getHashCode(this);
    }

}
