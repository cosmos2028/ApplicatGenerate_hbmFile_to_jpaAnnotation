/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.io.Serializable;

/**
 * Permet de conserver sous forme textuelle les motivations de l'ordonnance de r�glement.<br>
 * <b>ces donn�es sont volatiles</b>
 */
public class MotivationOrdonnanceDeReglement implements Serializable, Cloneable {

    private static final long serialVersionUID = -8380990829160492450L;

    private String motivationNonLieu;

    // motivation TC
    private String motivationRenvoi;

    // motivation TE
    private String motivationRenvoiTE;

    private String motivationRequalification;

    private String motivationMiseEnAccusation;

    private String motivationAutres;

    /**
     * @return retourne motivationAutres.
     */
    public String getMotivationAutres() {
        return motivationAutres;
    }

    /**
     * @param motivationAutres
     *            affecte motivationAutres
     */
    public void setMotivationAutres(String motivationAutres) {
        this.motivationAutres = motivationAutres;
    }

    /**
     * @return retourne motivationMiseEnAccusation.
     */
    public String getMotivationMiseEnAccusation() {
        return motivationMiseEnAccusation;
    }

    /**
     * @param motivationMiseEnAccusation
     *            affecte motivationMiseEnAccusation
     */
    public void setMotivationMiseEnAccusation(String motivationMiseEnAccusation) {
        this.motivationMiseEnAccusation = motivationMiseEnAccusation;
    }

    /**
     * @return retourne motivationNonLieu.
     */
    public String getMotivationNonLieu() {
        return motivationNonLieu;
    }

    /**
     * @param motivationNonLieu
     *            affecte motivationNonLieu
     */
    public void setMotivationNonLieu(String motivationNonLieu) {
        this.motivationNonLieu = motivationNonLieu;
    }

    /**
     * @return retourne motivationRenvoi.
     */
    public String getMotivationRenvoi() {
        return motivationRenvoi;
    }

    /**
     * @param motivationRenvoi
     *            affecte motivationRenvoi
     */
    public void setMotivationRenvoi(String motivationRenvoi) {
        this.motivationRenvoi = motivationRenvoi;
    }

    /**
     * @return retourne motivationRequalification.
     */
    public String getMotivationRequalification() {
        return motivationRequalification;
    }

    /**
     * @param motivationRequalification
     *            affecte motivationRequalification
     */
    public void setMotivationRequalification(String motivationRequalification) {
        this.motivationRequalification = motivationRequalification;
    }

    /**
     * @return the motivationRenvoiTE
     */
    public String getMotivationRenvoiTE() {
        return motivationRenvoiTE;
    }

    /**
     * @param motivationRenvoiTE
     *            motivationRenvoiTE � affecter
     */
    public void setMotivationRenvoiTE(String motivationRenvoiTE) {
        this.motivationRenvoiTE = motivationRenvoiTE;
    }

    /**
     * Renvoie un clone de l'objet
     *
     * @throws CloneNotSupportedException
     */
    public MotivationOrdonnanceDeReglement clone() throws CloneNotSupportedException {
        // Objet simple, on le clone tel quel
        return (MotivationOrdonnanceDeReglement) super.clone();
    }

}
