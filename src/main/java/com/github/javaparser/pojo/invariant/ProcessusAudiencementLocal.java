/*
 * Ce document est la propriété d?Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import fr.gouv.justice.cassiopee.administration.structure.model.Tgi;

/**
 * classe modelisant le processus d'audiencement local au TGI
 */
public class ProcessusAudiencementLocal extends ProcessusAudiencement {

    private static final long serialVersionUID = -1549954458536589833L;

    private ProcessusAudiencementNational processusAudiencementNational;

    private Tgi tgi;

    /**
     * @return Returns the tgi.
     */
    public Tgi getTgi() {
        return tgi;
    }

    /**
     * @param tgi
     *            The tgi to set.
     */
    public void setTgi(Tgi tgi) {
        this.tgi = tgi;
    }

    /**
     * @param processusAudiencementNational
     */
    public void setProcessusAudiencement(ProcessusAudiencementNational processusAudiencementNational) {
        this.processusAudiencementNational = processusAudiencementNational;
    }

    /**
     * @return Returns the processusAudiencement.
     */
    public ProcessusAudiencementNational getProcessusAudiencement() {
        return processusAudiencementNational;
    }
}
