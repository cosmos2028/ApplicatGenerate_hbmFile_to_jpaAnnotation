package fr.gouv.justice.cassiopee.invariant.affaire.model;

import java.util.Date;

public class CreationJsonSpark {

	private Long idAffaire;
	private String identifiantJustice;
	private String mode;
	private Date dateDemande;
	private String codeTgi;
	private String numeroParquet;
	// AMI 383
	private Integer statutAffaire;
	
	public CreationJsonSpark() {
	}
	
	public CreationJsonSpark(Long idAffaire, String identifiantJustice, String mode, String codeTgi,
			String numeroParquet, Integer statutAffaire) {
		super();
		this.idAffaire = idAffaire;
		this.identifiantJustice = identifiantJustice;
		this.mode = mode;
		this.codeTgi = codeTgi;
		this.numeroParquet = numeroParquet;
		this.statutAffaire = statutAffaire;
	}
	
	public CreationJsonSpark(Long idAffaire, String identifiantJustice, String mode, Date dateDemande, String codeTgi,
			String numeroParquet, Integer statutAffaire) {
		super();
		this.idAffaire = idAffaire;
		this.identifiantJustice = identifiantJustice;
		this.mode = mode;
		this.dateDemande = dateDemande;
		this.codeTgi = codeTgi;
		this.numeroParquet = numeroParquet;
		this.statutAffaire = statutAffaire;
	}

	public Long getIdAffaire() {
		return idAffaire;
	}

	public void setIdAffaire(Long idAffaire) {
		this.idAffaire = idAffaire;
	}

	public String getIdentifiantJustice() {
		return identifiantJustice;
	}

	public void setIdentifiantJustice(String identifiantJustice) {
		this.identifiantJustice = identifiantJustice;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Date getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getCodeTgi() {
		return codeTgi;
	}

	public void setCodeTgi(String codeTgi) {
		this.codeTgi = codeTgi;
	}

	public String getNumeroParquet() {
		return numeroParquet;
	}

	public void setNumeroParquet(String numeroParquet) {
		this.numeroParquet = numeroParquet;
	}

	public Integer getStatutAffaire() {
		return statutAffaire;
	}

	public void setStatutAffaire(Integer statutAffaire) {
		this.statutAffaire = statutAffaire;
	}

}
