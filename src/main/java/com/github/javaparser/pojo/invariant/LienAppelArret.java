package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.PeineOuMesure;

/**
 * Représente le lien entre un appel et un arrêt.
 */
public class LienAppelArret {
    private Long id;
    private Evenement appel;
    private Evenement arret;
    private PeineOuMesure peineOuMesure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Evenement getAppel() {
        return appel;
    }

    public void setAppel(Evenement appel) {
        this.appel = appel;
    }

    public Evenement getArret() {
        return arret;
    }

    public void setArret(Evenement arret) {
        this.arret = arret;
    }

    public PeineOuMesure getPeineOuMesure() {
        return peineOuMesure;
    }

    public void setPeineOuMesure(PeineOuMesure peineOuMesure) {
        this.peineOuMesure = peineOuMesure;
    }
}
