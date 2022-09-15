package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.exterieur.model.DispositifExterieur;

/**
 * Représente une association peineOuMesure / dispositifExterieur. Entité créée pour contourner l'association
 * many-to-many.
 */
public class LienPeineDispoExterieur {
    private LienPeineDispoExterieurPK pk;

    public LienPeineDispoExterieur() {
        // default
    }

    public LienPeineDispoExterieur(DispositifExterieur dispositifExterieur, PeineOuMesure peineOuMesure) {
        this.pk = new LienPeineDispoExterieurPK(dispositifExterieur, peineOuMesure);
    }

    /**
     * @return Returns the pk.
     */
    public LienPeineDispoExterieurPK getPk() {
        return pk;
    }

    /**
     * @param pk
     *            The pk to set.
     */
    public void setPk(LienPeineDispoExterieurPK pk) {
        this.pk = pk;
    }

}
