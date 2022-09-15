/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

/**
 * gestion des données volatiles d'un evenement
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
