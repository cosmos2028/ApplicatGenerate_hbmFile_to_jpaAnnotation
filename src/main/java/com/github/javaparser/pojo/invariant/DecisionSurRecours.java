package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.PeineOuMesure;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.PeineOuMesureType;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.service.enumeration.PeineOuMesureTypeEnum;

/**
 * Représente les décisions sur recours.
 */
public abstract class DecisionSurRecours<T> {
    private Long id;
    private T decision;
    private Long codeMesure;
    private PeineOuMesureType peineOuMesureType;
    private Evenement evenementDecision;
    private Evenement arret;

    /**
     * Méthode exécutée avant la sauvegarde de la {@link DecisionSurRecours}.
     */
    public void beforeSave() {
        // Ne fait rien par défaut
        return;
    }

    /**
     * Peine ou mesure au range le plus haut donc hérite la décision sur recours
     */
    private PeineOuMesure peineOuMesureMere;

    public T getDecision() {
        return decision;
    }

    public void setDecision(T decision) {
        this.decision = decision;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodeMesure() {
        return codeMesure;
    }

    public void setCodeMesure(Long codeMesure) {
        this.codeMesure = codeMesure;
    }

    public Evenement getEvenementDecision() {
        return evenementDecision;
    }

    public void setEvenementDecision(Evenement evenementDecision) {
        this.evenementDecision = evenementDecision;
    }

    public Evenement getArret() {
        return arret;
    }

    public void setArret(Evenement arret) {
        this.arret = arret;
    }

    public PeineOuMesure getPeineOuMesureMere() {
        return peineOuMesureMere;
    }

    public void setPeineOuMesureMere(PeineOuMesure peineOuMesureMere) {
        this.peineOuMesureMere = peineOuMesureMere;
    }

    public PeineOuMesureType getPeineOuMesureType() {
        return peineOuMesureType;
    }

    public void setPeineOuMesureType(PeineOuMesureType peineOuMesureType) {
        this.peineOuMesureType = peineOuMesureType;
        this.codeMesure = peineOuMesureType.getCodeMesure();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codeMesure == null) ? 0 : codeMesure.hashCode());
        result = prime * result + ((decision == null) ? 0 : decision.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((evenementDecision == null) ? 0 : evenementDecision.hashCode());
        result = prime * result + ((arret == null) ? 0 : arret.hashCode());
        result = prime * result + ((peineOuMesureMere == null) ? 0 : peineOuMesureMere.hashCode());
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
        @SuppressWarnings("unchecked")
        DecisionSurRecours<T> other = (DecisionSurRecours<T>) obj;
        if (codeMesure == null) {
            if (other.codeMesure != null)
                return false;
        } else if (!codeMesure.equals(other.codeMesure))
            return false;
        if (decision == null) {
            if (other.decision != null)
                return false;
        } else if (!decision.equals(other.decision))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (evenementDecision == null) {
            if (other.evenementDecision != null)
                return false;
        } else if (!evenementDecision.equals(other.evenementDecision))
            return false;
        if (arret == null) {
            if (other.arret != null)
                return false;
        } else if (!arret.equals(other.arret))
            return false;
        if (peineOuMesureMere == null) {
            if (other.peineOuMesureMere != null)
                return false;
        } else if (!peineOuMesureMere.equals(other.peineOuMesureMere))
            return false;
        return true;
    }

    public boolean isConfirmation() {
        return PeineOuMesureTypeEnum.CONFIRMATION.getCode().equals(codeMesure);
    }

}
