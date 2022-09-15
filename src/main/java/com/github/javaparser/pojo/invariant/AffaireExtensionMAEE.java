package fr.gouv.justice.cassiopee.invariant.affaire.model;

import fr.gouv.justice.cassiopee.administration.structure.model.Tgi;
import fr.gouv.justice.cassiopee.common.identifier.Identifier;

/**
 * Cette classe represente une trace de tout les liens existant entre les affaires qui ont des extensions de MAEE et les affaires 
 */
public class AffaireExtensionMAEE {

	private Long idLienMAEE;
	/** Identifiant d'édition */
	private String numeroParquetAffaireSource;

	/** date de la demande */
	private String numeroParquetAffaireEtendue;

	/** date de traitement de la demande */
	private Long idAffaireSource;

	/** etat du traitement de la demande */
	private Long idAffaireEtendue;

	/** nombre de tentative d'envoi */
	private String tgiAffaireSource;

	/** flux Xml de l'édition */
	private String tgiAffaireEtendue;

	private Long idEvenement;

	private Long idPersonne;

	public Long getIdLienMAEE() {
		return idLienMAEE;
	}

	public void setIdLienMAEE(Long idLienMAEE) {
		this.idLienMAEE = idLienMAEE;
	}

	public String getNumeroParquetAffaireSource() {
		return numeroParquetAffaireSource;
	}

	public void setNumeroParquetAffaireSource(String numeroParquetAffaireSource) {
		this.numeroParquetAffaireSource = numeroParquetAffaireSource;
	}

	public String getNumeroParquetAffaireEtendue() {
		return numeroParquetAffaireEtendue;
	}

	public void setNumeroParquetAffaireEtendue(String numeroParquetAffaireEtendue) {
		this.numeroParquetAffaireEtendue = numeroParquetAffaireEtendue;
	}

	public Long getIdAffaireSource() {
		return idAffaireSource;
	}

	public void setIdAffaireSource(Long idAffaireSource) {
		this.idAffaireSource = idAffaireSource;
	}

	public Long getIdAffaireEtendue() {
		return idAffaireEtendue;
	}

	public void setIdAffaireEtendue(Long idAffaireEtendue) {
		this.idAffaireEtendue = idAffaireEtendue;
	}

	public String getTgiAffaireSource() {
		return tgiAffaireSource;
	}

	public void setTgiAffaireSource(String tgiAffaireSource) {
		this.tgiAffaireSource = tgiAffaireSource;
	}

	public String getTgiAffaireEtendue() {
		return tgiAffaireEtendue;
	}

	public void setTgiAffaireEtendue(String tgiAffaireEtendue) {
		this.tgiAffaireEtendue = tgiAffaireEtendue;
	}

	public Long getIdEvenement() {
		return idEvenement;
	}

	public void setIdEvenement(Long idEvenement) {
		this.idEvenement = idEvenement;
	}

	public Long getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}

}