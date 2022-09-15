package fr.gouv.justice.cassiopee.invariant.affaire.model;

import java.io.Serializable;

public class AffaireEiaSpark implements Serializable{

	private String code;
	private String tgiActeSaisine;
	private String tgiAffaireAccueil;
	private String tgiAffairesJointes;
	private String tgiAuteurs ;
	private String tgiCabinet;
	private String tgiDateActeSaisine;
	private String tgiDateDecisionDefinitiveCalculee;
	private String tgiDateDecisionDefinitiveVerifiee;
	private String tgiDateEnvoi;
	private String tgiDateNotification;
	private String tgiDateOrdonnance;
	private String tgiDateSignature;
	private String tgiDateSignification;
	private String tgiDelaiAction;
	private String tgiDelaiPeine;
	private String tgiEmetteur;
	private String tgiEvenements;
	private String tgiJuridiction;
	private String tgiMode;
	private String tgiNATAFF;
	private String tgiNATINF;
	private String tgiNatureArret;
	private String tgiNatureDecision;
	private String tgiNatureJugement;
	private String tgiNatureOrdonnance;
	private String tgiNbAffairesJointes;
	private String tgiNbVictime;
	private String tgiNombreAuteurs;
	private String tgiNumeroParquet;
	private String tgiProcedure;
	private String tgiTypeDecision;
	private String tgiTypeInfraction;
	private String tgiVictimes;
	private String tgiVoie;
	
	
	
	
	public AffaireEiaSpark(String code, String tgiJuridiction, String tgiNumeroParquet) {
		super();
		this.code = code;
		this.tgiJuridiction = tgiJuridiction;
		this.tgiNumeroParquet = tgiNumeroParquet;
	}

	public AffaireEiaSpark(
			String code,
			String tgiActeSaisine,
			String tgiAffaireAccueil,
			String tgiAffairesJointes,
			String tgiAuteurs,
			String tgiCabinet,
			String tgiDateActeSaisine,
			String tgiDateDecisionDefinitiveCalculee,
			String tgiDateDecisionDefinitiveVerifiee,
			String tgiDateEnvoi,
			String tgiDateNotification,
			String tgiDateOrdonnance,
			String tgiDateSignature,
			String tgiDateSignification,
			String tgiDelaiAction,
			String tgiDelaiPeine,
			String tgiEmetteur,
			String tgiEvenements,
			String tgiJuridiction,
			String tgiMode,
			String tgiNATAFF,
			String tgiNATINF,
			String tgiNatureArret,
			String tgiNatureDecision,
			String tgiNatureJugement,
			String tgiNatureOrdonnance,
			String tgiNbAffairesJointes,
			String tgiNbVictime,
			String tgiNombreAuteurs,
			String tgiNumeroParquet,
			String tgiProcedure,
			String tgiTypeDecision,
			String tgiTypeInfraction,
			String tgiVictimes,
			String tgiVoie) {
		
		super();
		this.code = code;
		this.tgiActeSaisine = tgiActeSaisine;
		this.tgiAffaireAccueil = tgiAffaireAccueil;
		this.tgiAffairesJointes = tgiAffairesJointes;
		this.tgiAuteurs = tgiAuteurs;
		this.tgiCabinet = tgiCabinet;
		this.tgiDateActeSaisine = tgiDateActeSaisine;
		this.tgiDateDecisionDefinitiveCalculee = tgiDateDecisionDefinitiveCalculee;
		this.tgiDateDecisionDefinitiveVerifiee = tgiDateDecisionDefinitiveVerifiee;
		this.tgiDateEnvoi = tgiDateEnvoi;
		this.tgiDateNotification = tgiDateNotification;
		this.tgiDateOrdonnance = tgiDateOrdonnance;
		this.tgiDateSignature = tgiDateSignature;
		this.tgiDateSignification = tgiDateSignification;
		this.tgiDelaiAction = tgiDelaiAction;
		this.tgiDelaiPeine = tgiDelaiPeine;
		this.tgiEmetteur = tgiEmetteur;
		this.tgiEvenements = tgiEvenements;
		this.tgiJuridiction = tgiJuridiction;
		this.tgiMode = tgiMode;
		this.tgiNATAFF = tgiNATAFF;
		this.tgiNATINF = tgiNATINF;
		this.tgiNatureArret = tgiNatureArret;
		this.tgiNatureDecision = tgiNatureDecision;
		this.tgiNatureJugement = tgiNatureJugement;
		this.tgiNatureOrdonnance = tgiNatureOrdonnance;
		this.tgiNbAffairesJointes = tgiNbAffairesJointes;
		this.tgiNbVictime = tgiNbVictime;
		this.tgiNombreAuteurs = tgiNombreAuteurs;
		this.tgiNumeroParquet = tgiNumeroParquet;
		this.tgiProcedure = tgiProcedure;
		this.tgiTypeDecision = tgiTypeDecision;
		this.tgiTypeInfraction = tgiTypeInfraction;
		this.tgiVictimes = tgiVictimes;
		this.tgiVoie = tgiVoie;
	}
	
	public AffaireEiaSpark() {
		super();
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getTgiActeSaisine() {
		return tgiActeSaisine;
	}
	
	public void setTgiActeSaisine(String tgiActeSaisine) {
		this.tgiActeSaisine = tgiActeSaisine;
	}
	
	public String getTgiAffaireAccueil() {
		return tgiAffaireAccueil;
	}
	
	public void setTgiAffaireAccueil(String tgiAffaireAccueil) {
		this.tgiAffaireAccueil = tgiAffaireAccueil;
	}
	
	public String getTgiAffairesJointes() {
		return tgiAffairesJointes;
	}
	
	public void setTgiAffairesJointes(String tgiAffairesJointes) {
		this.tgiAffairesJointes = tgiAffairesJointes;
	}
	
	public String getTgiAuteurs() {
		return tgiAuteurs;
	}
	
	public void setTgiAuteurs(String tgiAuteurs) {
		this.tgiAuteurs = tgiAuteurs;
	}
	
	public String getTgiCabinet() {
		return tgiCabinet;
	}
	
	public void setTgiCabinet(String tgiCabinet) {
		this.tgiCabinet = tgiCabinet;
	}
	
	public String getTgiDateActeSaisine() {
		return tgiDateActeSaisine;
	}
	
	public void setTgiDateActeSaisine(String tgiDateActeSaisine) {
		this.tgiDateActeSaisine = tgiDateActeSaisine;
	}
	
	public String getTgiDateDecisionDefinitiveCalculee() {
		return tgiDateDecisionDefinitiveCalculee;
	}
	
	public void setTgiDateDecisionDefinitiveCalculee(String tgiDateDecisionDefinitiveCalculee) {
		this.tgiDateDecisionDefinitiveCalculee = tgiDateDecisionDefinitiveCalculee;
	}
	
	public String getTgiDateDecisionDefinitiveVerifiee() {
		return tgiDateDecisionDefinitiveVerifiee;
	}
	
	public void setTgiDateDecisionDefinitiveVerifiee(String tgiDateDecisionDefinitiveVerifiee) {
		this.tgiDateDecisionDefinitiveVerifiee = tgiDateDecisionDefinitiveVerifiee;
	}
	
	public String getTgiDateEnvoi() {
		return tgiDateEnvoi;
	}
	
	public void setTgiDateEnvoi(String tgiDateEnvoi) {
		this.tgiDateEnvoi = tgiDateEnvoi;
	}
	
	public String getTgiDateNotification() {
		return tgiDateNotification;
	}
	
	public void setTgiDateNotification(String tgiDateNotification) {
		this.tgiDateNotification = tgiDateNotification;
	}
	
	public String getTgiDateOrdonnance() {
		return tgiDateOrdonnance;
	}
	
	public void setTgiDateOrdonnance(String tgiDateOrdonnance) {
		this.tgiDateOrdonnance = tgiDateOrdonnance;
	}
	
	public String getTgiDateSignature() {
		return tgiDateSignature;
	}
	
	public void setTgiDateSignature(String tgiDateSignature) {
		this.tgiDateSignature = tgiDateSignature;
	}
	
	public String getTgiDateSignification() {
		return tgiDateSignification;
	}
	
	public void setTgiDateSignification(String tgiDateSignification) {
		this.tgiDateSignification = tgiDateSignification;
	}
	
	public String getTgiDelaiAction() {
		return tgiDelaiAction;
	}
	
	public void setTgiDelaiAction(String tgiDelaiAction) {
		this.tgiDelaiAction = tgiDelaiAction;
	}
	
	public String getTgiDelaiPeine() {
		return tgiDelaiPeine;
	}
	
	public void setTgiDelaiPeine(String tgiDelaiPeine) {
		this.tgiDelaiPeine = tgiDelaiPeine;
	}
	
	public String getTgiEmetteur() {
		return tgiEmetteur;
	}
	
	public void setTgiEmetteur(String tgiEmetteur) {
		this.tgiEmetteur = tgiEmetteur;
	}
	
	public String getTgiEvenements() {
		return tgiEvenements;
	}
	
	public void setTgiEvenements(String tgiEvenements) {
		this.tgiEvenements = tgiEvenements;
	}
	
	public String getTgiJuridiction() {
		return tgiJuridiction;
	}
	
	public void setTgiJuridiction(String tgiJuridiction) {
		this.tgiJuridiction = tgiJuridiction;
	}
	
	public String getTgiMode() {
		return tgiMode;
	}
	
	public void setTgiMode(String tgiMode) {
		this.tgiMode = tgiMode;
	}
	
	public String getTgiNATAFF() {
		return tgiNATAFF;
	}
	
	public void setTgiNATAFF(String tgiNATAFF) {
		this.tgiNATAFF = tgiNATAFF;
	}
	
	public String getTgiNATINF() {
		return tgiNATINF;
	}
	
	public void setTgiNATINF(String tgiNATINF) {
		this.tgiNATINF = tgiNATINF;
	}
	
	public String getTgiNatureArret() {
		return tgiNatureArret;
	}
	
	public void setTgiNatureArret(String tgiNatureArret) {
		this.tgiNatureArret = tgiNatureArret;
	}
	
	public String getTgiNatureDecision() {
		return tgiNatureDecision;
	}
	
	public void setTgiNatureDecision(String tgiNatureDecision) {
		this.tgiNatureDecision = tgiNatureDecision;
	}
	
	public String getTgiNatureJugement() {
		return tgiNatureJugement;
	}
	
	public void setTgiNatureJugement(String tgiNatureJugement) {
		this.tgiNatureJugement = tgiNatureJugement;
	}
	
	public String getTgiNatureOrdonnance() {
		return tgiNatureOrdonnance;
	}
	
	public void setTgiNatureOrdonnance(String tgiNatureOrdonnance) {
		this.tgiNatureOrdonnance = tgiNatureOrdonnance;
	}
	
	public String getTgiNbAffairesJointes() {
		return tgiNbAffairesJointes;
	}
	
	public void setTgiNbAffairesJointes(String tgiNbAffairesJointes) {
		this.tgiNbAffairesJointes = tgiNbAffairesJointes;
	}
	
	public String getTgiNbVictime() {
		return tgiNbVictime;
	}
	
	public void setTgiNbVictime(String tgiNbVictime) {
		this.tgiNbVictime = tgiNbVictime;
	}
	
	public String getTgiNombreAuteurs() {
		return tgiNombreAuteurs;
	}
	
	public void setTgiNombreAuteurs(String tgiNombreAuteurs) {
		this.tgiNombreAuteurs = tgiNombreAuteurs;
	}
	
	public String getTgiNumeroParquet() {
		return tgiNumeroParquet;
	}
	
	public void setTgiNumeroParquet(String tgiNumeroParquet) {
		this.tgiNumeroParquet = tgiNumeroParquet;
	}
	
	public String getTgiProcedure() {
		return tgiProcedure;
	}
	
	public void setTgiProcedure(String tgiProcedure) {
		this.tgiProcedure = tgiProcedure;
	}
	
	public String getTgiTypeDecision() {
		return tgiTypeDecision;
	}
	
	public void setTgiTypeDecision(String tgiTypeDecision) {
		this.tgiTypeDecision = tgiTypeDecision;
	}
	
	public String getTgiTypeInfraction() {
		return tgiTypeInfraction;
	}
	
	public void setTgiTypeInfraction(String tgiTypeInfraction) {
		this.tgiTypeInfraction = tgiTypeInfraction;
	}
	
	public String getTgiVictimes() {
		return tgiVictimes;
	}
	
	public void setTgiVictimes(String tgiVictimes) {
		this.tgiVictimes = tgiVictimes;
	}
	
	public String getTgiVoie() {
		return tgiVoie;
	}
	
	public void setTgiVoie(String tgiVoie) {
		this.tgiVoie = tgiVoie;
	}
}
