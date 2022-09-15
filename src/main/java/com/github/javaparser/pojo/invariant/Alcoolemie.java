/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.infraction.model;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;

/**
 * Pr�sence d'alcool dans le sang ou dans les poumons. Le taux d'alcool�mie indique le r�sultat de la mesure de
 * l'alcool�mie. L'unit� est fonction de la mesure mg/litre d'air ou g/litre de sang.
 */
public class Alcoolemie implements java.io.Serializable, Cloneable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 6211794983999897563L;

    private static final HashCodeEqualsHelper<Alcoolemie> HE_HELPER = HashCodeEqualsHelper.of(Alcoolemie.class, Alcoolemie::getTaux, Alcoolemie::getUnite);


    /** Unit� de la mesure mg/litre d'air ou g/litre de sang * */
    private UniteTauxAlcoolemie unite;

    /** Taux : valeur de la mesure de l'alcool�mie */
    private Float taux;

    /**
     * Indique le taux d'alool�mie
     *
     * @return le taux d'alcool�mie
     */
    public Float getTaux() {
        return taux;
    }

    /**
     * Renseigne le taux d'alcool�mie
     *
     * @param taux
     *            taux d'alcool�mie
     */
    public void setTaux(Float taux) {
        this.taux = taux;
    }

    /**
     * Indique l'unit� de la mesure de l'alool�mie
     *
     * @return l'unit� de la mesure
     */
    public UniteTauxAlcoolemie getUnite() {
        return unite;
    }

    /**
     * Renseigne l'unit� de la mesure de l'alcool�mie
     *
     * @param unite
     *            unit� de la mesure
     */
    public void setUnite(UniteTauxAlcoolemie unite) {
        this.unite = unite;
    }

    @Override
    public String toString() {
        return toFormattedString();
    }

    /**
     * retourne une cha�ne de caract�res de la forme taux unit�)
     *
     * @return une cha�ne de caract�res de la forme taux unit�)
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
