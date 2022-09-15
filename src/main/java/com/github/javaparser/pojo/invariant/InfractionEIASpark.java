package fr.gouv.justice.cassiopee.invariant.infraction.model;

import java.util.Date;

import fr.gouv.justice.cassiopee.invariant.infraction.enumeration.Level;

public class InfractionEIASpark implements Comparable<InfractionEIASpark> {
	private Long idInfraction;
	private String code;
	private String libelle;
	private String nataff;
	private Level degree;
	private Date dateNatInf;
	private int rang;

	public InfractionEIASpark() {
		super();
	}

	public InfractionEIASpark(Long idInfraction, String code, String libelle, String nataff, Level degree, Date dateNatInf, int rang) {
		super();
		this.idInfraction = idInfraction;
		this.code = code;
		this.libelle = libelle;
		this.nataff = nataff;
		this.degree = degree;
		this.dateNatInf = dateNatInf;
		this.rang = rang; 
	}

	public Long getIdInfraction() {
		return idInfraction;
	}

	public void setIdInfraction(Long idInfraction) {
		this.idInfraction = idInfraction;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getNataff() {
		return nataff;
	}

	public void setNataff(String nataff) {
		this.nataff = nataff;
	}

	public Level getDegree() {
		return degree;
	}

	public void setDegree(Level degree) {
		this.degree = degree;
	}
	
	public Date getDateNatInf() {
		return dateNatInf;
	}

	public void setDateNatInf(Date dateNatInf) {
		this.dateNatInf = dateNatInf;
	}

	
	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	@Override
	public int compareTo(InfractionEIASpark o) {
		if (o.getDegree() != null && this.getDegree() != null) {
			if (this.getDegree().getLevelCode() > o.getDegree().getLevelCode()) {
				return -1;
			} else {
				if (this.getDegree().getLevelCode() == o.getDegree().getLevelCode()) {
					if (this.getDateNatInf().before(o.getDateNatInf())) {
						return -1;
					} else {
						if (this.getDateNatInf().equals(o.getDateNatInf())) {
							if (this.getRang() < o.getRang())
								return -1;
							} else {
								return 1;
						}
						return 1;
					}
				}
				return 1;
			}
		} else {
			if (o.getDegree() != null) {
				return -1;
			}
			return 1;
		}
	}
}
