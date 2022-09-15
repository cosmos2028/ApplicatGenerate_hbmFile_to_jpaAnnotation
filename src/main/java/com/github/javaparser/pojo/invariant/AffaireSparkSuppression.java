package fr.gouv.justice.cassiopee.invariant.affaire.model;

public class AffaireSparkSuppression {
	
	
	private String type;
	private AffaireEiaSpark properties;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public AffaireEiaSpark getProperties() {
		return properties;
	}
	public void setProperties(AffaireEiaSpark properties) {
		this.properties = properties;
	}
	public AffaireSparkSuppression(String type, AffaireEiaSpark properties) {
		super();
		this.type = type;
		this.properties = properties;
	}
	public AffaireSparkSuppression() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}