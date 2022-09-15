/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.util.Date;

import fr.gouv.justice.cassiopee.administration.acteur.interne.model.ActeurInterne;
import fr.gouv.justice.cassiopee.administration.structure.model.Service;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.EnvoiMail;
import fr.gouv.justice.cassiopee.service.editiontype.service.enumeration.ModeImpressionEnum;

/**
 * La classe demande d'édition représente une demande d'édition différée. Cet objet est persistant. Les demandes
 * d'éditions peuvent être traitées en batch par le service de composition.
 */
public abstract class EditionDemandeDifferee {

    /** identifiant objet */
    private long id;

    /** état de la demande */
    private String codeEtat;

    /** priorité de la demande */
    private int priorite;

    /** date de la demande édition */
    private Date dateEdition;

    /** edition type */
    private EditionType editionType;

    /** l'acteur interne associé à l'utilisateur qui a demandé l'édition */
    private ActeurInterne acteurInterne;

    /** le service de connexion de l'utilisateur */
    private Service service;

    /** le mode d'impression */
    private String codeModeImpression;

    /** le nombre de copies */
    private Integer nbCopies;

    /** Serveur JBOSS en charge de l'edition */
    private String instanceServer;

    /** le nombre de retry sur le traitement de la demande d'édition */
    private Integer nombreRetry;

    /**
     * Definit si l edition doit être traite par le moteur Archimed
     */
    private boolean editionArchimed;

    /**
     * Definit si l edition doit être traite par le moteur d'export CSV
     */
    private boolean exportCsv;

    /**
     * EnvoiMail rattaché à l'édition
     */
    private EnvoiMail envoiMail;

    /**
     * @return the editionArchimed
     */
    public boolean isEditionArchimed() {
        return editionArchimed;
    }

    /**
     * @param editionArchimed
     *            the editionArchimed to set
     */
    public void setEditionArchimed(boolean editionArchimed) {
        this.editionArchimed = editionArchimed;
    }

    /**
     * @return the exportCsv
     */
    public boolean isExportCsv() {
        return exportCsv;
    }

    /**
     * @param exportCsv
     *            the exportCsv to set
     */
    public void setExportCsv(boolean exportCsv) {
        this.exportCsv = exportCsv;
    }

    /**
     * retourne une valeur énumérée qui indique le type de demande
     *
     * @return le type de demande
     */
    public abstract EditionDemandeDiffereeEnum getEditionDemandeDiffereeEnum();

    /**
     * retourne l'état de la demande
     *
     * @return l'état de la demande
     */
    public EtatDemandeDiffereeEnum getEtatDemandeDiffereeEnum() {
        return EtatDemandeDiffereeEnum.findEtatDemandeDiffereeByCode(getCodeEtat());
    }

    /**
     * renseigne l'état de la demande
     *
     * @return l'état de la demande
     */
    public void setEtatDemandeDiffereeEnum(EtatDemandeDiffereeEnum etatDemandeDiffereeEnum) {
        this.setCodeEtat(etatDemandeDiffereeEnum.getCode());
    }

    /**
     * Retourne la date de la demande d'édition
     *
     * @return la date de la demande d'édition
     */
    public Date getDateEdition() {
        return this.dateEdition;
    }

    /**
     * retourne le type de l'édition
     *
     * @return le type de l'édition.
     */
    public EditionType getEditionType() {
        return editionType;
    }

    /**
     * renseigne le type de l'édition.
     *
     * @param editionType
     *            le type de l'édition.
     */
    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    /**
     * retourne le mode d'impression
     *
     * @return le mode d'impression
     */
    public ModeImpressionEnum getModeImpressionEnum() {
        return ModeImpressionEnum.resolve(getCodeModeImpression());
    }

    /**
     * renseigne le mode d'impression
     *
     * @param modeImpressionEnum
     *            le mode d'impression
     */
    public void setModeImpressionEnum(ModeImpressionEnum modeImpressionEnum) {
        this.setCodeModeImpression(modeImpressionEnum.getCode());
    }

    /**
     * retourne le code du mode d'impression
     *
     * @return le code du mode d'impression
     */
    private String getCodeModeImpression() {
        return codeModeImpression;
    }

    /**
     * renseigne le code du mode d'impression
     *
     * @param codeModeImpression
     *            le code du mode d'impression
     */
    private void setCodeModeImpression(String codeModeImpression) {
        this.codeModeImpression = codeModeImpression;
    }

    /**
     * retourne le nombre de copies à imprimer
     *
     * @return le nombre de copies à imprimer
     */
    public Integer getNbCopies() {
        return nbCopies;
    }

    /**
     * retourne le nombre de copies à imprimer
     *
     * @return le nombre de copies à imprimer
     */
    public int getNombreCopies() {
        if (nbCopies != null) {
            return nbCopies.intValue();
        } else {
            return 0;
        }
    }

    /**
     * renseigne le nombre de copies à imprimer
     *
     * @param nbCopies
     *            le nombre de copies à imprimer
     */
    public void setNbCopies(Integer nbCopies) {
        this.nbCopies = nbCopies;
    }

    /**
     * retourne le service de connexion de l'utilisateur
     *
     * @return le service de connexion de l'utilisateur
     */
    public Service getService() {
        return service;
    }

    /**
     * renseigne le service de connexion de l'utilisateur
     *
     * @param service
     *            le service de connexion de l'utilisateur
     */
    public void setService(Service service) {
        this.service = service;
    }

    /**
     * renseigne la date de la demande d'édition
     *
     * @param dateEdition
     *            la date de la demande d'édition
     */
    public void setDateEdition(Date dateEdition) {
        this.dateEdition = dateEdition;
    }

    /**
     * retourne l'acteur interne associé à l'utilisateur qui a demandé l'édition
     *
     * @return l'acteur interne associé à l'utilisateur qui a demandé l'édition
     */
    public ActeurInterne getActeurInterne() {
        return acteurInterne;
    }

    /**
     * renseigne l'acteur interne associé à l'utilisateur qui a demandé l'édition
     *
     * @param acteurInterne
     *            l'acteur interne associé à l'utilisateur qui a demandé l'édition
     */
    public void setActeurInterne(ActeurInterne acteurInterne) {
        this.acteurInterne = acteurInterne;
    }

    /**
     * retourne le code de l'état de la demande
     *
     * @return le code l'état de la demande
     */
    private String getCodeEtat() {
        return codeEtat;
    }

    /**
     * renseigne le code de l'état de la demande
     *
     * @param codeEtat
     *            le code de l'état de la demande
     */
    private void setCodeEtat(String codeEtat) {
        this.codeEtat = codeEtat;
    }

    /**
     * retourne l'identifiant objet
     *
     * @return l'identifiant objet
     */
    public long getId() {
        return id;
    }

    /**
     * renseigne l'identifiant objet
     *
     * @param id
     *            l'identifiant objet
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * retourne la priorité de la demande d'édition
     *
     * @return la priorité de la demande d'édition
     */
    public int getPriorite() {
        return priorite;
    }

    /**
     * renseigne la priorité de la demande d'édition
     *
     * @param priorite
     *            la priorité de la demande d'édition
     */
    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public String getInstanceServer() {
        return this.instanceServer;
    }

    public void setInstanceServer(String instanceServer) {
        this.instanceServer = instanceServer;
    }

    /**
     * @return Returns the nombreRetry.
     */
    public Integer getNombreRetry() {
        return nombreRetry;
    }

    /**
     * @param nombreRetry
     *            The nombreRetry to set.
     */
    public void setNombreRetry(Integer nombreRetry) {
        this.nombreRetry = nombreRetry;
    }

    public EnvoiMail getEnvoiMail() {
        return envoiMail;
    }

    public void setEnvoiMail(EnvoiMail envoiMail) {
        this.envoiMail = envoiMail;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((acteurInterne == null) ? 0 : acteurInterne.hashCode());
        result = prime * result
                + ((codeEtat == null) ? 0 : codeEtat.hashCode());
        result = prime
                * result
                + ((codeModeImpression == null) ? 0 : codeModeImpression
                        .hashCode());
        result = prime * result
                + ((dateEdition == null) ? 0 : dateEdition.hashCode());
        result = prime * result + (editionArchimed ? 1231 : 1237);
        result = prime * result
                + ((editionType == null) ? 0 : editionType.hashCode());
        result = prime * result
                + ((envoiMail == null) ? 0 : envoiMail.hashCode());
        result = prime * result + (exportCsv ? 1231 : 1237);
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result
                + ((instanceServer == null) ? 0 : instanceServer.hashCode());
        result = prime * result
                + ((nbCopies == null) ? 0 : nbCopies.hashCode());
        result = prime * result
                + ((nombreRetry == null) ? 0 : nombreRetry.hashCode());
        result = prime * result + priorite;
        result = prime * result + ((service == null) ? 0 : service.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EditionDemandeDifferee other = (EditionDemandeDifferee) obj;
        if (acteurInterne == null) {
            if (other.acteurInterne != null)
                return false;
        } else if (!acteurInterne.equals(other.acteurInterne))
            return false;
        if (codeEtat == null) {
            if (other.codeEtat != null)
                return false;
        } else if (!codeEtat.equals(other.codeEtat))
            return false;
        if (codeModeImpression == null) {
            if (other.codeModeImpression != null)
                return false;
        } else if (!codeModeImpression.equals(other.codeModeImpression))
            return false;
        if (dateEdition == null) {
            if (other.dateEdition != null)
                return false;
        } else if (!dateEdition.equals(other.dateEdition))
            return false;
        if (editionArchimed != other.editionArchimed)
            return false;
        if (editionType == null) {
            if (other.editionType != null)
                return false;
        } else if (!editionType.equals(other.editionType))
            return false;
        if (envoiMail == null) {
            if (other.envoiMail != null)
                return false;
        } else if (!envoiMail.equals(other.envoiMail))
            return false;
        if (exportCsv != other.exportCsv)
            return false;
        if (id != other.id)
            return false;
        if (instanceServer == null) {
            if (other.instanceServer != null)
                return false;
        } else if (!instanceServer.equals(other.instanceServer))
            return false;
        if (nbCopies == null) {
            if (other.nbCopies != null)
                return false;
        } else if (!nbCopies.equals(other.nbCopies))
            return false;
        if (nombreRetry == null) {
            if (other.nombreRetry != null)
                return false;
        } else if (!nombreRetry.equals(other.nombreRetry))
            return false;
        if (priorite != other.priorite)
            return false;
        if (service == null) {
            if (other.service != null)
                return false;
        } else if (!service.equals(other.service))
            return false;
        return true;
    }

}
