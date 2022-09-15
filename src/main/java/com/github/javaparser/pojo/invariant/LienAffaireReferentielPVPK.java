package fr.gouv.justice.cassiopee.invariant.affaire.model;

import fr.gouv.justice.cassiopee.processus.ech.model.ReferentielPV;

public class LienAffaireReferentielPVPK implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;

    private Affaire affaire;
    
    private ReferentielPV referentielPV;
    
    // CONSTRUCTOR
    public LienAffaireReferentielPVPK() {
        // default constructor required by hibernate
    }
    public LienAffaireReferentielPVPK(Affaire affaire, ReferentielPV referentielPV) {
        this.affaire = affaire;
        this.referentielPV = referentielPV;
    }

    // GETTERS & SETTERS
    
    public Affaire getAffaire() {
        return affaire;
    }

    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
    }

    public ReferentielPV getReferentielPV() {
        return referentielPV;
    }

    public void setReferentielPV(ReferentielPV referentielPV) {
        this.referentielPV = referentielPV;
    }
}
