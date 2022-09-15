package fr.gouv.justice.cassiopee.invariant.evenement.model;



public class ActeSaisineEvent {
	/**
	 * Mnémonique de l’événement d’acte de saisine de l’affaire
	 */
	private String acteSaisineEvtMnemonique;

	/**
	 * Code et libellé court de l’élément de structure émetteur de l’acte de saisine
	 * de l’affaire
	 */
	private String acteSaisineEvtEmetteur;

	/**
	 * Date de l’événement d’acte de saisine de l’affaire
	 */
	private String dateActeSaisine;

	public ActeSaisineEvent() {
		super();
	}

	public ActeSaisineEvent(String acteSaisineEvtMnemonique, String acteSaisineEvtEmetteur, String dateActeSaisine) {
		super();
		this.acteSaisineEvtMnemonique = acteSaisineEvtMnemonique;
		this.acteSaisineEvtEmetteur = acteSaisineEvtEmetteur;
		this.dateActeSaisine = dateActeSaisine;
	}

	public String getActeSaisineEvtMnemonique() {
		return acteSaisineEvtMnemonique;
	}

	public void setActeSaisineEvtMnemonique(String acteSaisineEvtMnemonique) {
		this.acteSaisineEvtMnemonique = acteSaisineEvtMnemonique;
	}

	public String getActeSaisineEvtEmetteur() {
		return acteSaisineEvtEmetteur;
	}

	public void setActeSaisineEvtEmetteur(String acteSaisineEvtEmetteur) {
		this.acteSaisineEvtEmetteur = acteSaisineEvtEmetteur;
	}

	public String getDateActeSaisine() {
		return dateActeSaisine;
	}

	public void setDateActeSaisine(String dateActeSaisine) {
		this.dateActeSaisine = dateActeSaisine;
	}

}
