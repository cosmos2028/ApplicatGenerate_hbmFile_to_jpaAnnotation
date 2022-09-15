/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.io.Serializable;
import java.util.Set;

import fr.gouv.justice.cassiopee.administration.structure.model.Tgi;

/**
 * lien entre le processus d'audiencement et les éditions type, l'ensemble des tgi indique s'il y a des modifications
 * locals du processus.
 */
@SuppressWarnings("nls")
public class LienEditionTypeProcessusAudiencement implements Serializable {

    private static final long serialVersionUID = -546431861311L;

    private LienEditionTypeProcessusAudiencementPK pk;

    private Set<Tgi> tgis;

    private EditionType editionType;

    private ProcessusAudiencement processusAudiencement;

    /**
     * @return retourne editionType.
     */
    public EditionType getEditionType() {
        return editionType;
    }

    /**
     * @param editionType
     *            affecte editionType
     */
    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    /**
     * @return retourne pk.
     */
    public LienEditionTypeProcessusAudiencementPK getPk() {
        return pk;
    }

    /**
     * @param pk
     *            affecte pk
     */
    public void setPk(LienEditionTypeProcessusAudiencementPK pk) {
        this.pk = pk;
    }

    /**
     * @return retourne processusAudiencement.
     */
    public ProcessusAudiencement getProcessusAudiencement() {
        return processusAudiencement;
    }

    /**
     * @param processusAudiencement
     *            affecte processusAudiencement
     */
    public void setProcessusAudiencement(ProcessusAudiencement processusAudiencement) {
        this.processusAudiencement = processusAudiencement;
    }

    /**
     * @return retourne tgi.
     */
    public Set<Tgi> getTgis() {
        return tgis;
    }

    /**
     * @param tgi
     *            affecte tgi
     */
    public void setTgis(Set<Tgi> tgi) {
        this.tgis = tgi;
    }

}
