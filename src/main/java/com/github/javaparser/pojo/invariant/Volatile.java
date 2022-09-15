/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

/**
 * gestion des donn�es volatiles d'un evenement
 */
public class Volatile implements java.io.Serializable {
    private static final long serialVersionUID = 6492386512808757898L;

    private Long evenementId;

    private MotivationOrdonnanceDeReglement motivationOR;

    private InformationDebat informationDebat;

    /**
     * @return retourne informationDebat.
     */
    public InformationDebat getInformationDebat() {
        return informationDebat;
    }

    /**
     * @param informationDebat
     *            affecte informationDebat
     */
    public void setInformationDebat(InformationDebat informationDebat) {
        this.informationDebat = informationDebat;
    }

    /**
     * @return retourne motivationOR.
     */
    public MotivationOrdonnanceDeReglement getMotivationOR() {
        return motivationOR;
    }

    /**
     * @param motivationOR
     *            affecte motivationOR
     */
    public void setMotivationOR(MotivationOrdonnanceDeReglement motivationOR) {
        this.motivationOR = motivationOR;
    }

    /**
     * @return Long
     */
    public Long getEvenementId() {
        return evenementId;
    }

    /**
     * @param evenementId
     */
    public void setEvenementId(Long evenementId) {
        this.evenementId = evenementId;
    }

}
