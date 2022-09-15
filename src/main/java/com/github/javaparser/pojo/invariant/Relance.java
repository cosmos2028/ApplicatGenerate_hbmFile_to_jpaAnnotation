/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import fr.gouv.justice.cassiopee.administration.structure.model.Service;
import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;

/**
 * Représente une relance liée à un événement.
 */
public class Relance implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3539520049536761160L;

    private static final HashCodeEqualsHelper<Relance> HE_HELPER = HashCodeEqualsHelper.of(Relance.class, Relance::getId, Relance::getEvenement);


    private Long id;

    private Evenement evenement;

    private RelanceType type;

    private Date dateRelance;

    private Date dateRetourSouhaitee;

    private Boolean indicateurPieceOuCopie;

    private Long numeroOrdre;

    private Service createurService;

    private Service emetteurService;

    /**
     * @return retourne dateRelance.
     */
    public Date getDateRelance() {
        return dateRelance;
    }

    /**
     * @param dateRelance
     *            affecte dateRelance.
     */
    public void setDateRelance(Date dateRelance) {
        this.dateRelance = dateRelance;
    }

    /**
     * @return retourne evenement.
     */
    public Evenement getEvenement() {
        return evenement;
    }

    /**
     * @param evenement
     *            affecte evenement.
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    /**
     * @return retourne id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            affecte id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return retourne numeroOrdre.
     */
    public Long getNumeroOrdre() {
        return numeroOrdre;
    }

    /**
     * @param numeroOrdre
     *            affecte numeroOrdre.
     */
    public void setNumeroOrdre(Long numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    /**
     * @return retourne type.
     */
    public RelanceType getType() {
        return type;
    }

    /**
     * @param type
     *            affecte type.
     */
    public void setType(RelanceType type) {
        this.type = type;
    }

    /**
     * @return retourne dateRetourSouhaitee.
     */
    public Date getDateRetourSouhaitee() {
        return dateRetourSouhaitee;
    }

    /**
     * @param dateRetourSouhaitee
     *            affecte dateRetourSouhaitee.
     */
    public void setDateRetourSouhaitee(Date dateRetourSouhaitee) {
        this.dateRetourSouhaitee = dateRetourSouhaitee;
    }

    /**
     * @return retourne indicateurPieceOuCopie.
     */
    public Boolean getIndicateurPieceOuCopie() {
        return indicateurPieceOuCopie;
    }

    /**
     * @param indicateurPieceOuCopie
     *            affecte indicateurPieceOuCopie.
     */
    public void setIndicateurPieceOuCopie(Boolean indicateurPieceOuCopie) {
        this.indicateurPieceOuCopie = indicateurPieceOuCopie;
    }

    public Service getCreateurService() {
        return createurService;
    }

    public Service getEmetteurService() {
        return emetteurService;
    }

    public void setCreateurService(Service service) {
        this.createurService = service;
    }

    public void setEmetteurService(Service service) {
        this.emetteurService = service;
    }

    /**
     *
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("evenement", this.evenement).append("id", this.id).append(
                "numeroOrdre", this.numeroOrdre).append("type", this.type).append("dateRelance", this.dateRelance)
                .toString();
    }

    @Override
    public int hashCode() {
        return HE_HELPER.getHashCode(this);
    }

    @Override
    public boolean equals(final Object other) {
        return HE_HELPER.areEqual(this, other);
    }

}
