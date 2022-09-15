package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import java.io.Serializable;
import java.util.List;

import fr.gouv.justice.cassiopee.invariant.infraction.model.Infraction;

/**
 * @author Classe "outil" Correspond à une infraction et ses peines associées
 */

public class InfractionEtPeinesAssocieesDIA implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7373421795842243093L;

    private Infraction infraction;

    private List<PeinePrononceeOuRequiseDIA> listePeinesOuMesuresAssociees;

    /**
     * @param infraction
     * @param listePeinesOuMesuresAssociees
     */
    public InfractionEtPeinesAssocieesDIA(Infraction infraction,
            List<PeinePrononceeOuRequiseDIA> listePeinesOuMesuresAssociees) {
        this.infraction = infraction;
        this.listePeinesOuMesuresAssociees = listePeinesOuMesuresAssociees;
    }

    /**
     * @return infraction
     */
    public Infraction getInfraction() {
        return infraction;
    }

    /**
     * @param infraction
     */
    public void setInfraction(Infraction infraction) {
        this.infraction = infraction;
    }

    /**
     * @return listePeinesOuMesuresAssociees
     */
    public List<PeinePrononceeOuRequiseDIA> getListePeinesOuMesuresAssociees() {
        return listePeinesOuMesuresAssociees;
    }

    /**
     * @param listePeinesOuMesuresAssociees
     */
    public void setListePeinesOuMesuresAssociees(List<PeinePrononceeOuRequiseDIA> listePeinesOuMesuresAssociees) {
        this.listePeinesOuMesuresAssociees = listePeinesOuMesuresAssociees;
    }

}
