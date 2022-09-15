package fr.gouv.justice.cassiopee.invariant.evenement.model;

/**
 * Repr�sente une association �v�nement ant�rieur / post�rieur. Entit� cr��e pour contourner l'association many-to-many.
 */
public class LienEvenementAnterieur {

    private LienEvenementAnterieurPK pk;

    /**
     * @return Returns the pk.
     */
    public LienEvenementAnterieurPK getPk() {
        return pk;
    }

    /**
     * @param pk
     *            The pk to set.
     */
    public void setPk(LienEvenementAnterieurPK pk) {
        this.pk = pk;
    }

}
