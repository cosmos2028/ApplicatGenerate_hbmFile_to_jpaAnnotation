package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.io.Serializable;

public class EditionPortalis implements Serializable {

    private static final long serialVersionUID = -50698761392725850L;

    private Long editionId;

    private Long idArchimed;

    private String etat;

    private String instance;

    private String rapportMessageErreur;

    public Long getEditionId() {
        return editionId;
    }

    public void setEditionId(Long editionId) {
        this.editionId = editionId;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Long getIdArchimed() {
        return idArchimed;
    }

    public void setIdArchimed(Long idArchimed) {
        this.idArchimed = idArchimed;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getRapportMessageErreur() {
        return rapportMessageErreur;
    }

    public void setRapportMessageErreur(String rapportMessageErreur) {
        this.rapportMessageErreur = rapportMessageErreur;
    }

}
