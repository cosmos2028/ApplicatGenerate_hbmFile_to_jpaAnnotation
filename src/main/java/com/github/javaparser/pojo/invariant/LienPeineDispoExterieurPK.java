package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import java.io.Serializable;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;
import fr.gouv.justice.cassiopee.invariant.exterieur.model.DispositifExterieur;

/**
 * Clé primaire pour LienPeineDispoExterieurPK.
 */
public class LienPeineDispoExterieurPK implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8079853939077568950L;

    private static final HashCodeEqualsHelper<LienPeineDispoExterieurPK> HE_HELPER = HashCodeEqualsHelper.of(LienPeineDispoExterieurPK.class, LienPeineDispoExterieurPK::getDispositifExterieur,
            LienPeineDispoExterieurPK::getPeineOuMesure);


    private PeineOuMesure peineOuMesure;

    private DispositifExterieur dispositifExterieur;

    /**
     * Constructor for LienPeineDispoExterieurPK
     */
    public LienPeineDispoExterieurPK() {
        // default
    }

    public LienPeineDispoExterieurPK(DispositifExterieur dispositifExterieur, PeineOuMesure peineOuMesure) {
        this.dispositifExterieur = dispositifExterieur;
        this.peineOuMesure = peineOuMesure;
    }

    /**
     * @return la PeineOuMesure
     */
    public PeineOuMesure getPeineOuMesure() {
        return peineOuMesure;
    }

    /**
     * @param peineOuMesure
     */
    public void setPeineOuMesure(PeineOuMesure peineOuMesure) {
        this.peineOuMesure = peineOuMesure;
    }

    /**
     * @return le DispositifExterieur
     */
    public DispositifExterieur getDispositifExterieur() {
        return dispositifExterieur;
    }

    /**
     * @param dispo
     */
    public void setDispositifExterieur(DispositifExterieur dispo) {
        this.dispositifExterieur = dispo;
    }

    @Override
    public boolean equals(final Object other) {
        return HE_HELPER.areEqual(this, other);
    }


    @Override
    public int hashCode() {
        return HE_HELPER.getHashCode(this);
    }

}
