package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.util.Date;

import fr.gouv.justice.cassiopee.administration.acteur.interne.model.ActeurInterne;
import fr.gouv.justice.cassiopee.administration.structure.model.Service;
import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;

/**
 * Informations concernant la suppression d'un événement: date et agent qui a supprimé l'événement.
 *
 * @author cvb
 */
public class InfoSuppression {

    private static final HashCodeEqualsHelper<InfoSuppression> HE_HELPER = HashCodeEqualsHelper.of(InfoSuppression.class, InfoSuppression::getDate, InfoSuppression::getService, InfoSuppression::getUtilisateur);


    private ActeurInterne utilisateur;

    private Service service;

    private Date date;

    /**
     * @return Returns the service.
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service
     *            The service to set.
     */
    public void setService(Service service) {
        this.service = service;
    }

    /**
     * @return Returns the utilisateur.
     */
    public ActeurInterne getUtilisateur() {
        return utilisateur;
    }

    /**
     * @param utilisateur
     *            The utilisateur to set.
     */
    public void setUtilisateur(ActeurInterne utilisateur) {
        this.utilisateur = utilisateur;
    }

    /**
     * Renvoie la date de suppression.
     *
     * @return la date de suppression
     */
    public Date getDate() {
        return date;
    }

    /**
     * Valorise la date de suppression.
     *
     * @param date
     *            la date de suppression
     */
    public void setDate(Date date) {
        this.date = date;
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
