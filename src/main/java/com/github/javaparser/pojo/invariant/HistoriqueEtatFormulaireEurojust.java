package fr.gouv.justice.cassiopee.invariant.eurojust.model.formulaire;

import java.util.Date;

import fr.gouv.justice.cassiopee.administration.acteur.interne.model.ActeurInterne;
import fr.gouv.justice.cassiopee.administration.structure.model.Juridiction;
import fr.gouv.justice.cassiopee.administration.structure.model.Service;
import fr.gouv.justice.cassiopee.invariant.eurojust.enumeration.EtatFormulaireEnum;

public class HistoriqueEtatFormulaireEurojust {
    private Long id;
    private Long formulaireId;
    private Date date;
    private String etatCode;
    private Service service;
    private Juridiction juridiction;
    private ActeurInterne acteurInterne;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFormulaireId() {
        return formulaireId;
    }

    public void setFormulaireId(Long formulaireId) {
        this.formulaireId = formulaireId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEtatCode() {
        return etatCode;
    }

    public void setEtatCode(String etatCode) {
        this.etatCode = etatCode;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Juridiction getJuridiction() {
        return juridiction;
    }

    public void setJuridiction(Juridiction juridiction) {
        this.juridiction = juridiction;
    }

    public ActeurInterne getActeurInterne() {
        return acteurInterne;
    }

    public void setActeurInterne(ActeurInterne acteurInterne) {
        this.acteurInterne = acteurInterne;
    }

    public EtatFormulaireEnum getEtatFormulaireEnum() {
        return EtatFormulaireEnum.valueOf(etatCode);
    }
}
