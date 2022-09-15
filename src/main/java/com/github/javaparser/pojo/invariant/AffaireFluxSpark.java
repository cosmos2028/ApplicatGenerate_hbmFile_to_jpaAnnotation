package fr.gouv.justice.cassiopee.invariant.affaire.model;

import java.io.Serializable;

public class AffaireFluxSpark implements Serializable {
	private Long idaffaire;
	private String identifiantJustice;
	private String affairejson;
	private String mode;

	public String getIdentifiantJustice() {
		return identifiantJustice;
	}

	public void setIdentifiantJustice(String identifiantJustice) {
		this.identifiantJustice = identifiantJustice;
	}

	public Long getIdaffaire() {
		return idaffaire;
	}

	public void setIdaffaire(Long idaffaire) {
		this.idaffaire = idaffaire;
	}

	public String getAffairejson() {
		return affairejson;
	}

	public void setAffairejson(String affairejson) {
		this.affairejson = affairejson;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((affairejson == null) ? 0 : affairejson.hashCode());
		result = prime * result + ((idaffaire == null) ? 0 : idaffaire.hashCode());
		result = prime * result + ((identifiantJustice == null) ? 0 : identifiantJustice.hashCode());
		result = prime * result + ((mode == null) ? 0 : mode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AffaireFluxSpark other = (AffaireFluxSpark) obj;
		if (affairejson == null) {
			if (other.affairejson != null)
				return false;
		} else if (!affairejson.equals(other.affairejson))
			return false;
		if (idaffaire == null) {
			if (other.idaffaire != null)
				return false;
		} else if (!idaffaire.equals(other.idaffaire))
			return false;
		if (identifiantJustice == null) {
			if (other.identifiantJustice != null)
				return false;
		} else if (!identifiantJustice.equals(other.identifiantJustice))
			return false;
		if (mode == null) {
			if (other.mode != null)
				return false;
		} else if (!mode.equals(other.mode))
			return false;
		return true;
	}

	public AffaireFluxSpark(Long idaffaire, String affairejson, String mode, String identifiantJustice) {
		super();
		this.idaffaire = idaffaire;
		this.affairejson = affairejson;
		this.mode = mode;
		this.identifiantJustice = identifiantJustice;
	}

	public AffaireFluxSpark() {
		super();
	}

}
