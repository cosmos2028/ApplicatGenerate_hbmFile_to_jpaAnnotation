package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.util.Date;

public class DateDecisionEpappel {

    private Long id;

    private Long appelId;

    private Date dateLimiteDecision;

    private Date dateDecisionRendue;

    public DateDecisionEpappel(){
    }

    public DateDecisionEpappel(Long appelId, Date dateLimiteDecision, Date dateDecisionRendue){
        this.appelId = appelId;
        this.dateLimiteDecision = dateLimiteDecision;
        this.dateDecisionRendue = dateDecisionRendue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppelId() {
        return appelId;
    }

    public void setAppelId(Long appelId) {
        this.appelId = appelId;
    }

    public Date getDateLimiteDecision() {
        return dateLimiteDecision;
    }

    public void setDateLimiteDecision(Date dateLimiteDecision) {
        this.dateLimiteDecision = dateLimiteDecision;
    }

    public Date getDateDecisionRendue() {
        return dateDecisionRendue;
    }

    public void setDateDecisionRendue(Date date) {
        this.dateDecisionRendue = date;
    }
}
