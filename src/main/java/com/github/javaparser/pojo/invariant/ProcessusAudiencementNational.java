/*
 * Ce document est la propri�t� d?Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.util.HashSet;
import java.util.Set;

/**
 * modelise un processus national
 */
public class ProcessusAudiencementNational extends ProcessusAudiencement {

    private static final long serialVersionUID = 3649353713059409055L;

    private Set<ProcessusAudiencementLocal> processusLocaux = new HashSet<ProcessusAudiencementLocal>();

    /**
     * @return Returns the processusLocaux.
     */
    public Set<ProcessusAudiencementLocal> getProcessusLocaux() {
        return processusLocaux;
    }

    /**
     * @param processusLocaux
     *            The processusLocaux to set.
     */
    public void setProcessusLocaux(Set<ProcessusAudiencementLocal> processusLocaux) {
        this.processusLocaux = processusLocaux;
    }

}
