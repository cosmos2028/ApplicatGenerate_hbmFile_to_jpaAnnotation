package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.util.Date;

/**
 * Cette classe represente une trace de toutes les editions du BPN composées dans cassiopée.
 */
public class EditionBPN {

	/** Identifiant d'édition */
	private Long id;

	/** date de la demande */
	private Date dateDemande;

	/** date de traitement de la demande */
	private Date dateTraitement;

	/** etat du traitement de la demande */
	private String etatTraitement;

	/** nombre de tentative d'envoi */
	private Long nombreRejeux;

	/** flux Xml de l'édition */
	private String fluxXml;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public Date getDateTraitement() {
		return dateTraitement;
	}

	public void setDateTraitement(Date dateTraitement) {
		this.dateTraitement = dateTraitement;
	}

	public String getEtatTraitement() {
		return etatTraitement;
	}

	public void setEtatTraitement(String etatTraitement) {
		this.etatTraitement = etatTraitement;
	}

	public Long getNombreRejeux() {
		return nombreRejeux;
	}

	public void setNombreRejeux(Long nombreRejeux) {
		this.nombreRejeux = nombreRejeux;
	}

	public String getFluxXml() {
		return fluxXml;
	}

	public void setFluxXml(String fluxXml) {
		this.fluxXml = fluxXml;
	}

}
