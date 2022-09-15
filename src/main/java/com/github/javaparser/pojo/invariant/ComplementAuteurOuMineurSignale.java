package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * Classe regroupant des attributs de la personne physique qui ne sont
 * pertinents que si celle-ci est auteur (d'infraction) dans une affaire pénale,
 * ou mineur dans une affaire civile du JE (Jeune).
 */
public class ComplementAuteurOuMineurSignale
		implements java.io.Serializable, Comparable<ComplementAuteurOuMineurSignale> {

	/** Identifiant version pour la sérialisation */
	private static final long serialVersionUID = 6522882409095217757L;

	private Personne personne;

	/**
	 * Deuxième prénom de l'auteur ou du mineur signalé. Les prénoms doivent être
	 * enregistrés dans l’ordre de l’état civil.
	 */
	private String prenom2;

	/**
	 * Troisième prénom de l'auteur ou du mineur signalé. Les prénoms doivent être
	 * enregistrés dans l’ordre de l’état civil.
	 */
	private String prenom3;

	/**
	 * Personne pour laquelle l’identité n’a pu être établie au vu des éléments et
	 * qui fournit une identité usurpée ou imaginaire.
	 */
	private Boolean seDisantX;

	/**
	 * Vrai lorsque la personne physique a fait l'objet d'un deferement
	 */
	private Boolean defere;

	/**
	 * Vrai lorsque la personne physique est en contact habituel avec des mineurs
	 */
	private boolean enContactAvecMineurs;

	/**
	 * Correspond à la date de deferement si l'utilisateur est defere. Dans le cas
	 * contraire, cette date est positionné
	 */
	private Date dateDeferement;

	/** Nom de la mère. */
	private String nomPere;

	/**
	 * Les 3 prénoms du père doivent être séparés par des espaces pour ne pas les
	 * confondre avec des prénoms composés (qui être saisis avec un trait d’union).
	 * Les prénoms doivent être enregistrés dans l’ordre de l’état civil. Ces 3
	 * prénoms sont tous saisis dans cet attribut.
	 */
	private String prenomsPere;

	/** Nom de la mère. */
	private String nomMere;

	/**
	 * Les 3 prénoms de la mère doivent être séparés par des espaces pour ne pas les
	 * confondre avec des prénoms composés (qui doivent être saisis avec un trait
	 * d’union). Les prénoms doivent être enregistrés dans l’ordre de l’état civil.
	 * Ces 3 prénoms sont tous saisis dans cet attribut.
	 */
	private String prenomsMere;

	/**
	 * Information concernant un auteur, considérée au même titre que son identité
	 * (présentée dans le même écran), si un prélèvement génétique a été demandé,
	 * effectué ou enregistré au FNAEG (fichier national automatisé des empreintes
	 * génétiques).
	 */
	private String codeBarreFNAEG;

	/**
	 * Rang du mineur dans sa fratrie c'est-à-dire position, en terme d'âge, par
	 * rapport à l'ensemble de ses (demis) frères et soeurs.
	 */
	private Integer rangDansFratrie;

	/** Nombre d'enfants de la fratrie. */
	private Integer tailleFratrie;

	/** Date à laquelle est décédée la personne physique. */
	private Date dateDeces;

	/**
	 * Le délinquant interpellé sans papier s'est prétendu "mineur" et a été
	 * enregistré comme tel. Par la suite, une expertise médicale peut être
	 * effectuée et conduire à établir sa majorité. L'attribut vaut alors Vrai.
	 */
	private Boolean majeurSelonExpertise;

	private Set<Alias> aliases = new HashSet<>();

	private Set<PieceAdministrative> pieceAdministratives = new HashSet<>();

	/**
	 * Indicateur de l?absence d?adresse de la personne physique : sans domicile
	 * connu ou sans domicile fixe. Il peut y avoir une adresse ancienne conservée
	 * au dossier.
	 */
	private SansDomicile sansDomicile;

	private SituationPenale situationPenale;

	private SituationSociale situationSociale;

	private Date dateExpertiseOsseuse;

	private String ageOsseux;

	private Boolean redexAlerteEnregistrement;

	/**
	 * @return retourne aliases.
	 */
	public Set<Alias> getAliases() {
		return aliases;
	}

	/**
	 * @param aliases
	 *            affecte aliases
	 */
	public void setAliases(Set<Alias> aliases) {
		this.aliases = aliases;
	}

	/**
	 * @return retourne codeBarreFNAEG.
	 */
	public String getCodeBarreFNAEG() {
		return codeBarreFNAEG;
	}

	/**
	 * @param codeBarreFNAEG
	 *            affecte codeBarreFNAEG
	 */
	public void setCodeBarreFNAEG(String codeBarreFNAEG) {
		this.codeBarreFNAEG = codeBarreFNAEG;
	}

	/**
	 * @return retourne dateDeces.
	 */
	public Date getDateDeces() {
		return dateDeces;
	}

	/**
	 * @param dateDeces
	 *            affecte dateDeces
	 */
	public void setDateDeces(Date dateDeces) {
		this.dateDeces = dateDeces;
	}

	/**
	 * @return retourne majeurSelonExpertise.
	 */
	public Boolean getMajeurSelonExpertise() {
		return majeurSelonExpertise;
	}

	/**
	 * @param majeurSelonExpertise
	 *            affecte majeurSelonExpertise
	 */
	public void setMajeurSelonExpertise(Boolean majeurSelonExpertise) {
		this.majeurSelonExpertise = majeurSelonExpertise;
	}

	/**
	 * @return retourne nomMere.
	 */
	public String getNomMere() {
		return nomMere;
	}

	/**
	 * @param nomMere
	 *            affecte nomMere
	 */
	public void setNomMere(String nomMere) {
		this.nomMere = nomMere;
	}

	/**
	 * @return retourne nomPere.
	 */
	public String getNomPere() {
		return nomPere;
	}

	/**
	 * @param nomPere
	 *            affecte nomPere
	 */
	public void setNomPere(String nomPere) {
		this.nomPere = nomPere;
	}

	/**
	 * @return retourne pieceAdministratives.
	 */
	public Set<PieceAdministrative> getPieceAdministratives() {
		return pieceAdministratives;
	}

	/**
	 * @param pieceAdministratives
	 *            affecte pieceAdministratives
	 */
	public void setPieceAdministratives(Set<PieceAdministrative> pieceAdministratives) {
		this.pieceAdministratives = pieceAdministratives;
	}

	/**
	 * @return retourne prenom2.
	 */
	public String getPrenom2() {
		return prenom2;
	}

	/**
	 * @param prenom2
	 *            affecte prenom2
	 */
	public void setPrenom2(String prenom2) {
		this.prenom2 = prenom2;
	}

	/**
	 * @return retourne prenom3.
	 */
	public String getPrenom3() {
		return prenom3;
	}

	/**
	 * @param prenom3
	 *            affecte prenom3
	 */
	public void setPrenom3(String prenom3) {
		this.prenom3 = prenom3;
	}

	/**
	 * @return retourne prenomsMere.
	 */
	public String getPrenomsMere() {
		return prenomsMere;
	}

	/**
	 * @param prenomsMere
	 *            affecte prenomsMere
	 */
	public void setPrenomsMere(String prenomsMere) {
		this.prenomsMere = prenomsMere;
	}

	/**
	 * @return retourne prenomsPere.
	 */
	public String getPrenomsPere() {
		return prenomsPere;
	}

	/**
	 * @param prenomsPere
	 *            affecte prenomsPere
	 */
	public void setPrenomsPere(String prenomsPere) {
		this.prenomsPere = prenomsPere;
	}

	/**
	 * @return retourne rangDansFratrie.
	 */
	public Integer getRangDansFratrie() {
		return rangDansFratrie;
	}

	/**
	 * @param rangDansFratrie
	 *            affecte rangDansFratrie
	 */
	public void setRangDansFratrie(Integer rangDansFratrie) {
		this.rangDansFratrie = rangDansFratrie;
	}

	/**
	 * @return retourne sansDomicile.
	 */
	public SansDomicile getSansDomicile() {
		return sansDomicile;
	}

	/**
	 * @param sansDomicile
	 *            affecte sansDomicile
	 */
	public void setSansDomicile(SansDomicile sansDomicile) {
		this.sansDomicile = sansDomicile;
	}

	/**
	 * @return retourne seDisantX.
	 */
	public boolean getSeDisantX() {
		return this.seDisantX != null ? this.seDisantX : false;
	}

	/**
	 * @param seDisantX
	 *            affecte seDisantX
	 */
	public void setSeDisantX(Boolean seDisantX) {
		this.seDisantX = seDisantX;
	}

	/**
	 * @return retourne situationPenale.
	 */
	public SituationPenale getSituationPenale() {
		return situationPenale;
	}

	/**
	 * @param situationPenale
	 *            affecte situationPenale
	 */
	public void setSituationPenale(SituationPenale situationPenale) {
		this.situationPenale = situationPenale;
	}

	/**
	 * @return retourne situationSociale.
	 */
	public SituationSociale getSituationSociale() {
		return situationSociale;
	}

	/**
	 * @param situationSociale
	 *            affecte situationSociale
	 */
	public void setSituationSociale(SituationSociale situationSociale) {
		this.situationSociale = situationSociale;
	}

	/**
	 * @return retourne tailleFratrie.
	 */
	public Integer getTailleFratrie() {
		return tailleFratrie;
	}

	/**
	 * @param tailleFratrie
	 *            affecte tailleFratrie
	 */
	public void setTailleFratrie(Integer tailleFratrie) {
		this.tailleFratrie = tailleFratrie;
	}

	/**
	 * @return Returns the personne.
	 */
	public Personne getPersonne() {
		return personne;
	}

	/**
	 * @param personne
	 *            The personne to set.
	 */
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	/**
	 * @return the defere
	 */
	public final Boolean getDefere() {
		return defere;
	}

	/**
	 * @param defere
	 *            the defere to set
	 */
	public final void setDefere(final Boolean defere) {
		this.defere = defere;
	}

	/**
	 * @return the dateDeferement
	 */
	public final Date getDateDeferement() {
		return dateDeferement;
	}

	/**
	 * @param dateDeferement
	 *            the dateDeferement to set
	 */
	public final void setDateDeferement(Date dateDeferement) {
		this.dateDeferement = dateDeferement;
	}

	public final Date getDateExpertiseOsseuse() {
		return dateExpertiseOsseuse;
	}

	public final void setDateExpertiseOsseuse(Date dateExpertiseOsseuse) {
		this.dateExpertiseOsseuse = dateExpertiseOsseuse;
	}

	/**
	 * @return the ageOsseux
	 */
	public final String getAgeOsseux() {
		return ageOsseux;
	}

	/**
	 * @param ageOsseux
	 *            the ageOsseux to set
	 */
	public final void setAgeOsseux(String ageOsseux) {
		this.ageOsseux = ageOsseux;
	}

	/**
	 * @return
	 */
	public Boolean getRedexAlerteEnregistrement() {
		return redexAlerteEnregistrement;
	}

	/**
	 * @param redexAlerteEnregistrement
	 */
	public void setRedexAlerteEnregistrement(Boolean redexAlerteEnregistrement) {
		this.redexAlerteEnregistrement = redexAlerteEnregistrement;
	}

	public boolean isEnContactAvecMineurs() {
		return enContactAvecMineurs;
	}

	public void setEnContactAvecMineurs(boolean enContactAvecMineurs) {
		this.enContactAvecMineurs = enContactAvecMineurs;
	}

	@Override
	public int compareTo(ComplementAuteurOuMineurSignale o) {
		// On reprend la construction des nom prénom père d'après l'assembler
		// buildPersonneExtraitDTO de la class PersonneAssemblerHelper
		String thisNomPrenomsMerePere = "";
		if (this != null) {
			String thisNomPrenomsMere = "";
			String thisNomPrenomsPere = "";

			if (this.getNomPere() != null) {
				thisNomPrenomsPere = this.getNomPere();
			}
			if (this.getPrenomsPere() != null) {
				thisNomPrenomsPere = thisNomPrenomsPere + " " + this.getPrenomsPere();
			}

			if (this.getNomMere() != null) {
				thisNomPrenomsMere = this.getNomMere();
			}

			if (this.getPrenomsMere() != null) {
				thisNomPrenomsMere = thisNomPrenomsMere + " " + this.getPrenomsMere();

				if (thisNomPrenomsPere != null && thisNomPrenomsMere != null) {
					thisNomPrenomsMere += " ";
				}
			}
			if (StringUtils.isNotEmpty(thisNomPrenomsMere) && StringUtils.isNotEmpty(thisNomPrenomsPere)) {
				thisNomPrenomsMerePere = thisNomPrenomsMere + " " + thisNomPrenomsPere;
			} else {
				thisNomPrenomsMerePere = thisNomPrenomsMere + thisNomPrenomsPere;
			}
		}
		String oNomPrenomsMerePere = "";
		if (o != null) {
			String oNomPrenomsMere = "";
			String oNomPrenomsPere = "";

			if (o.getNomPere() != null) {
				oNomPrenomsPere = o.getNomPere();
			}
			if (o.getPrenomsPere() != null) {
				oNomPrenomsPere = oNomPrenomsPere + " " + o.getPrenomsPere();
			}

			if (o.getNomMere() != null) {
				oNomPrenomsMere = o.getNomMere();
			}

			if (o.getPrenomsMere() != null) {
				oNomPrenomsMere = oNomPrenomsMere + " " + o.getPrenomsMere();
			}
			if (StringUtils.isNotEmpty(oNomPrenomsMere) && StringUtils.isNotEmpty(oNomPrenomsPere)) {
				oNomPrenomsMerePere = oNomPrenomsMere + " " + oNomPrenomsPere;
			} else {
				oNomPrenomsMerePere = oNomPrenomsMere + oNomPrenomsPere;
			}
		}
		// Si les deux sont vides
		// correction de la mantis 0126402: RAS - Extrait Information - Exception
		// technique lors du 2ieme clic du tri sur filiation
		if (StringUtils.isBlank(oNomPrenomsMerePere) == StringUtils.isBlank(thisNomPrenomsMerePere)) {
			return 0;
		} else {
			// Si les deux ne sont pas vide
			if (StringUtils.isNotEmpty(thisNomPrenomsMerePere) && StringUtils.isNotEmpty(oNomPrenomsMerePere)) {
				// on réalise le compare
				return oNomPrenomsMerePere.compareTo(thisNomPrenomsMerePere);

			} else if (StringUtils.isNotEmpty(thisNomPrenomsMerePere)) {
				// Si le this est valorisé alors que l'autre non, on renvoie d'abord celui-ci
				return 1;
			} else if (StringUtils.isNotEmpty(oNomPrenomsMerePere)) {
				// Si le o est valorisé alors que l'autre non, on renvoie d'abord celui-ci
				return -1;
			}
			return 0;
		}
	}

}
