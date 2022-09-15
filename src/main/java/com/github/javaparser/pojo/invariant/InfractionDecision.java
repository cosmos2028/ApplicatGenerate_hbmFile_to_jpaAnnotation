package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.infraction.model.Infraction;

/**
 * classe regroupant Une infraction et les decisions prevention et sanction elle est utile dans l'ecran de decision
 */
public class InfractionDecision implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4752865559867337429L;

    private Infraction infraction;

    private String modaliteParticipation;

    private DecisionSurPrevention decisionSurPrevention;

    private DecisionSurSanction decisionSurSanction;

    private ModulationDePeine modulationDePeine;

    // ligne selectionnée
    private boolean select;

    /**
     * @param infraction
     * @param decisionPrevention
     * @param decisionSanction
     * @param select
     */
    public InfractionDecision(Infraction infraction, DecisionSurPrevention decisionPrevention,
            DecisionSurSanction decisionSanction, ModulationDePeine modulationDePeine, boolean select) {
        this.infraction = infraction;
        this.decisionSurPrevention = decisionPrevention;
        this.decisionSurSanction = decisionSanction;
        this.modulationDePeine = modulationDePeine;
        this.select = select;
    }

    /**
     * @param infraction
     * @param decisionPrevention
     * @param decisionSanction
     * @param select
     */
    public InfractionDecision(Infraction infraction, DecisionSurPrevention decisionPrevention,
            DecisionSurSanction decisionSanction, ModulationDePeine modulationDePeine, String modaliteParticipation,
            boolean select) {
        this.infraction = infraction;
        this.decisionSurPrevention = decisionPrevention;
        this.decisionSurSanction = decisionSanction;
        this.modulationDePeine = modulationDePeine;
        this.select = select;
        this.modaliteParticipation = modaliteParticipation;
    }

    /**
     * @return decisionPrevention
     */
    public DecisionSurPrevention getDecisionSurPrevention() {
        return decisionSurPrevention;
    }

    /**
     * @param decisionPrevention
     */
    public void setDecisionSurPrevention(DecisionSurPrevention decisionPrevention) {
        this.decisionSurPrevention = decisionPrevention;
    }

    /**
     * @return decisionSanction
     */
    public DecisionSurSanction getDecisionSurSanction() {
        return decisionSurSanction;
    }

    /**
     * @param decisionSanction
     */
    public void setDecisionSurSanction(DecisionSurSanction decisionSanction) {
        this.decisionSurSanction = decisionSanction;
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
     * @return Returns the modulationDePeine.
     */
    public ModulationDePeine getModulationDePeine() {
        return modulationDePeine;
    }

    /**
     * @param modulationDePeine
     *            The modulationDePeine to set.
     */
    public void setModulationDePeine(ModulationDePeine modulationDePeine) {
        this.modulationDePeine = modulationDePeine;
    }

    /**
     * @return retourne la modalite de participation
     */
    public String getModaliteParticipation() {
        return modaliteParticipation;
    }

    /**
     * @param modaliteParticipation
     */
    public void setModaliteParticipation(String modaliteParticipation) {
        this.modaliteParticipation = modaliteParticipation;
    }

    /**
     * @return select
     */
    public boolean isSelect() {
        return select;
    }

    /**
     * @param select
     */
    public void setSelect(boolean select) {
        this.select = select;
    }

}
