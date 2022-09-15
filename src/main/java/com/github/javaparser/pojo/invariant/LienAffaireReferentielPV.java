package fr.gouv.justice.cassiopee.invariant.affaire.model;

import java.util.Date;

import fr.gouv.justice.cassiopee.processus.ech.enumeration.LienAffaireReferentielPVTypeCreationEnum;
import fr.gouv.justice.cassiopee.processus.ech.model.ReferentielPV;

public class LienAffaireReferentielPV {

    private LienAffaireReferentielPVPK pk;
    
    private LienAffaireReferentielPVTypeCreationEnum typeCreation;
    
    private Date dateCreationLien;
    
    private String idjDuPv;

    // CONSTRUCTOR
    public LienAffaireReferentielPV() {
        // default constructor required by hibernate
    }

    public LienAffaireReferentielPV(Affaire affaire, ReferentielPV referentielPV, LienAffaireReferentielPVTypeCreationEnum typeCreation) {
        this.pk = new LienAffaireReferentielPVPK(affaire, referentielPV);
        this.typeCreation = typeCreation;
        this.dateCreationLien = new Date();
    }
    
    public LienAffaireReferentielPV(Affaire affaire, ReferentielPV referentielPV, LienAffaireReferentielPVTypeCreationEnum typeCreation,String idjDuPv) {
        this.pk = new LienAffaireReferentielPVPK(affaire, referentielPV);
        this.typeCreation = typeCreation;
        this.dateCreationLien = new Date();
        this.idjDuPv = idjDuPv;
    }
    
    //GETTERS & SETTERS
    public LienAffaireReferentielPVPK getPk() {
        return pk;
    }

    public void setPk(LienAffaireReferentielPVPK pk) {
        this.pk = pk;
    }

    public LienAffaireReferentielPVTypeCreationEnum getTypeCreation() {
        return typeCreation;
    }

    public void setTypeCreation(LienAffaireReferentielPVTypeCreationEnum typeCreation) {
        this.typeCreation = typeCreation;
    }

    public Date getDateCreationLien() {
        return dateCreationLien;
    }

    public void setDateCreationLien(Date dateCreationLien) {
        this.dateCreationLien = dateCreationLien;
    }

	public String getIdjDuPv() {
		return idjDuPv;
	}

	public void setIdjDuPv(String idjDuPv) {
		this.idjDuPv = idjDuPv;
	}
    
    
}
