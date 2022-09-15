package fr.gouv.justice.cassiopee.invariant.affaire.model;

import java.io.Serializable;

public class EchAffaireEiaSpark implements Serializable, Cloneable {

	private Long idAffaire;
	private String affaireJson;
	private String mode;
	private Integer codeEtat;
	private String identifiantJustice;

	public EchAffaireEiaSpark() {
		super();
	}

	public EchAffaireEiaSpark(Long idAffaire, String affaireJson, String mode, Integer codeEtat, String identifiantJustice) {
		super();
		this.idAffaire = idAffaire;
		this.affaireJson = affaireJson;
		this.mode = mode;
		this.codeEtat = codeEtat;
		this.identifiantJustice = identifiantJustice;
	}

	public Long getIdAffaire() {
		return idAffaire;
	}

	public void setIdAffaire(Long idAffaire) {
		this.idAffaire = idAffaire;
	}

	public String getAffaireJson() {
		return affaireJson;
	}

	public void setAffaireJson(String affaireJson) {
		this.affaireJson = affaireJson;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Integer getCodeEtat() {
		return codeEtat;
	}

	public void setCodeEtat(Integer codeEtat) {
		this.codeEtat = codeEtat;
	}

	public String getIdentifiantJustice() {
		return identifiantJustice;
	}

	public void setIdentifiantJustice(String identifiantJustice) {
		this.identifiantJustice = identifiantJustice;
	}

}
