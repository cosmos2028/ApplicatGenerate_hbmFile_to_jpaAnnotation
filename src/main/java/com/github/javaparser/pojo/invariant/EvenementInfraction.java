package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.infraction.model.Infraction;

/**
 * classe de lien entre evenement, infraction et les peines (les peines sans auteur)
 */
public class EvenementInfraction implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4187379272523883088L;

    /**
     *
     */

    private Long id;

    /**
     * les peines ou mesures liées à l'evenementInfraction (ce sont les peines sans auteurs)
     */
    private Set<PeineOuMesure> peineOuMesures = new HashSet<>();

    private Infraction infraction;

    private Evenement evenement;

    /**
     *
     */
    public EvenementInfraction() {
    }

    /**
     * @return Long
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Set<PeineOuMesure>
     */
    public Set<PeineOuMesure> getPeineOuMesures() {
        return this.peineOuMesures;
    }

    /**
     * @param peineOuMesures
     */
    public void setPeineOuMesures(Set<PeineOuMesure> peineOuMesures) {
        this.peineOuMesures = peineOuMesures;
    }

    /**
     * @return Infraction
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
     * @return l'evenement
     */
    public Evenement getEvenement() {
        return evenement;
    }

    /**
     * @param evenement
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

}
