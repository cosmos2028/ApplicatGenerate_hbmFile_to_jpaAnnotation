package fr.gouv.justice.cassiopee.invariant.affaire.model;

import java.io.Serializable;
import java.util.Date;

public class RetourSpark implements Serializable{

	private Long idAffaire;
	private String idjustice;
	private int codeRetourSpark;
	private Date dateModification;
	private String numeroParquet;
	private String codeTgi;
	
	public RetourSpark(Long idAffaire, String idjustice, int codeRetourSpark, Date dateModification,
			String numeroParquet, String codeTgi) {
		super();
		this.idAffaire = idAffaire;
		this.idjustice = idjustice;
		this.codeRetourSpark = codeRetourSpark;
		this.dateModification = dateModification;
		this.numeroParquet = numeroParquet;
		this.codeTgi = codeTgi;
	}

	public RetourSpark() {
		super();
	}
	
	public Long getIdAffaire() {
		return idAffaire;
	}
	
	public void setIdAffaire(Long idAffaire) {
		this.idAffaire = idAffaire;
	}
	
	public String getIdjustice() {
		return idjustice;
	}
	
	public void setIdjustice(String idjustice) {
		this.idjustice = idjustice;
	}
	
	public int getCodeRetourSpark() {
		return codeRetourSpark;
	}
	
	public void setCodeRetourSpark(int codeRetourSpark) {
		this.codeRetourSpark = codeRetourSpark;
	}
	
	public Date getDateModification() {
		return dateModification;
	}
	
	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public String getNumeroParquet() {
		return numeroParquet;
	}

	public void setNumeroParquet(String numeroParquet) {
		this.numeroParquet = numeroParquet;
	}

	public String getCodeTgi() {
		return codeTgi;
	}

	public void setCodeTgi(String codeTgi) {
		this.codeTgi = codeTgi;
	}
	
	

}
