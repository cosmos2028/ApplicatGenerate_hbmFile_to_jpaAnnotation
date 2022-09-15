/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.administration.parametrage.model.EditionTypeDeclencheur;
import fr.gouv.justice.cassiopee.administration.parametrage.model.VersionLocaleTexteElementaire;
import fr.gouv.justice.cassiopee.commons.codification.model.Codification;
import fr.gouv.justice.cassiopee.service.editiontype.service.enumeration.EditionTypeEnum;
import fr.gouv.justice.cassiopee.service.editiontype.service.enumeration.ModeImpressionEnum;

/**
 * Edition Type
 */
public class EditionType<T extends EditionTypeDeclencheur> extends Codification {

    private static final long serialVersionUID = 3135520523076023513L;

    // code du mode d'impression. C'est cet attribut qui est mappé dans
    // Hibernate
    private String modeImpressionEnumCode;

    private Long nombreCopies;

    private MFamilleEditionType familleEditionType;

    private boolean indicateurActif;

    private Boolean indicateurAtypique;

    private Boolean indicateurAsynchrone;

    private int priorite;

    private Set<T> editionTypeDeclencheurs = new HashSet<T>();

    private String type;

    private Boolean archimed;

    private Long limiteToleree;

    private boolean indicateurActifTGI;

    private boolean indicateurActifCA;

    private boolean indicateurActifTGICASS;

    private int rang;

    private boolean eligibleBPN;

    private Set<VersionLocaleTexteElementaire> versionLocaleTexteElementaireSet;

    public Set<VersionLocaleTexteElementaire> getVersionLocaleTexteElementaireSet() {
        return versionLocaleTexteElementaireSet;
    }

    public void setVersionLocaleTexteElementaireSet(Set<VersionLocaleTexteElementaire> versionLocaleTexteElementaireSet) {
        this.versionLocaleTexteElementaireSet = versionLocaleTexteElementaireSet;
    }

    public MFamilleEditionType getFamilleEditionType() {
        return familleEditionType;
    }

    public void setFamilleEditionType(MFamilleEditionType familleEditionType) {
        this.familleEditionType = familleEditionType;
    }

    public EditionType() {
        super();
    }

    public ModeImpressionEnum getModeImpressionEnum() {
        return ModeImpressionEnum.resolve(this.modeImpressionEnumCode);
    }

    public void setModeImpressionEnum(ModeImpressionEnum modeImpressionEnum) {
        this.modeImpressionEnumCode = modeImpressionEnum.getCode();
    }

    public Long getNombreCopies() {
        return nombreCopies;
    }

    public void setNombreCopies(Long nombreCopies) {
        this.nombreCopies = nombreCopies;
    }

    /**
     * @return Returns the editionTypeDeclancheurs.
     */
    protected Set<T> getEditionTypeDeclencheurs() {
        return editionTypeDeclencheurs;
    }

    /**
     * @param editionTypeDeclencheurs
     *            The editionTypeDeclancheurs to set.
     */
    protected void setEditionTypeDeclencheurs(Set<T> editionTypeDeclencheurs) {
        this.editionTypeDeclencheurs = editionTypeDeclencheurs;
    }

    protected String getModeImpressionEnumCode() {
        return modeImpressionEnumCode;
    }

    protected void setModeImpressionEnumCode(String modeImpressionEnumCode) {
        this.modeImpressionEnumCode = modeImpressionEnumCode;
    }

    /**
     * @return indicateurActif
     */
    public boolean getIndicateurActif() {
        return this.indicateurActif;
    }

    public void setIndicateurActif(boolean indicateurActif) {
        this.indicateurActif = indicateurActif;
    }

    public boolean isIndicateurActifCA() {
        return indicateurActifCA;
    }

    public void setIndicateurActifCA(boolean indicateurActifCA) {
        this.indicateurActifCA = indicateurActifCA;
    }

    public boolean isIndicateurActifTGICASS() {
        return indicateurActifTGICASS;
    }

    public void setIndicateurActifTGICASS(boolean indicateurActifTGICASS) {
        this.indicateurActifTGICASS = indicateurActifTGICASS;
    }

    public EditionTypeEnum getEditionTypeEnum() {
        return EditionTypeEnum.revolve(this.type);
    }

    public Boolean getIndicateurAtypique() {
        return indicateurAtypique;
    }

    public void setIndicateurAtypique(Boolean indicateurAtypique) {
        this.indicateurAtypique = indicateurAtypique;
    }

    /**
     * @return Returns the indicateurAsynchrone.
     */
    public Boolean getIndicateurAsynchrone() {
        return indicateurAsynchrone;
    }

    /**
     * @param indicateurAsynchrone
     *            The indicateurAsynchrone to set.
     */
    public void setIndicateurAsynchrone(Boolean indicateurAsynchrone) {
        this.indicateurAsynchrone = indicateurAsynchrone;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public Boolean isArchimed() {
        return archimed;
    }

    public void setArchimed(Boolean archimed) {
        this.archimed = archimed;
    }

    public boolean isIndicateurActifTGI() {
        return indicateurActifTGI;
    }

    public void setIndicateurActifTGI(boolean indicateurActifTGI) {
        this.indicateurActifTGI = indicateurActifTGI;
    }

    public void setLimiteToleree(Long limiteToleree) {
        this.limiteToleree = limiteToleree;
    }

    public Long getLimiteToleree() {
        return limiteToleree;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public int getRang() {
        return rang;
    }

	public boolean isEligibleBPN() {
		return eligibleBPN;
	}

	public void setEligibleBPN(boolean eligibleBPN) {
		this.eligibleBPN = eligibleBPN;
	}

}
