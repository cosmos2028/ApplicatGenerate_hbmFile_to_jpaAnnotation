package fr.gouv.justice.cassiopee.invariant.evenement.model;

/**
 * Représente une association événement antérieur / postérieur. Entité créée pour contourner l'association many-to-many.
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
