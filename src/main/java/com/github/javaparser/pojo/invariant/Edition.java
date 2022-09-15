/*
 * Ce document est la propriété d’Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import fr.gouv.justice.cassiopee.administration.acteur.externe.model.PersonneQualifiee;
import fr.gouv.justice.cassiopee.administration.acteur.interne.model.ActeurInterne;
import fr.gouv.justice.cassiopee.administration.acteur.interne.model.ClasseurPersonnel;
import fr.gouv.justice.cassiopee.administration.structure.model.Service;
import fr.gouv.justice.cassiopee.invariant.edition.service.enumeration.ModeConservationEnum;
import fr.gouv.justice.cassiopee.invariant.edition.service.enumeration.TypeOrigineEditionEnum;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.personne.model.AvocatPersonneAffaire;
import fr.gouv.justice.cassiopee.invariant.personne.model.Interprete;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.EnvoiMail;
import fr.gouv.justice.cassiopee.processus.parquet.model.IdentificationParquet;
import fr.gouv.justice.cassiopee.referentiel.codification.model.SuiviPEEcrou;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;
import fr.gouv.justice.cassiopee.service.audience.model.Audience;
import fr.gouv.justice.cassiopee.service.editiontype.service.enumeration.DestinataireEditionEnum;

/**
 * Cette classe represente une trace de toutes les editions composées dans cassiopée. ainsi que les éditions édité en
 * mode trace : les pièces d'execution éditeées en mode trace.
 */
public class Edition implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2488584788506996865L;

    /** Identifiant d'édition */
    private Long id;

    /** edition type */
    private EditionType editionType;

    /** la date de composition */
    private Date dateComposition;

    /** code u mode de conservation */
    private String codeModeConservation;

    /** l'evenement auquel est attaché l'edition si le mode de conservation est EVT_ATTACHE */
    private Evenement evenementAttache;

    /** le classeur auquel est attaché l'édition si le mode de conservation est CLASS_PERSO */
    private ClasseurPersonnel classeurPersonnel;

    /** Le type de destinataire */
    private String codeTypeDestinataire;

    /** Le libelle du destinataire */
    private String libelleDestinataire;

    /** Log de l'envoi de mail */
    private EnvoiMail envoiMail;

    /** la perssone affire destinataire */
    private Personne personneAffaireDestinataire;

    /** l'element de structure destinataire */
    private ElementStructure elementStructureDestinataire;

    /** Le service destinataire de l'édition */
    private Service serviceDestinataire;

    /** personne qualifie destinataire de l'édition */

    private PersonneQualifiee personneQualifieeDestinataire;

    /** L'avocat personne Affaire destinataire de l'édition */
    private AvocatPersonneAffaire avocatPersonneAffaireDestinataire;

    /** L'interprete destinataire de l'édition */
    private Interprete interpreteDestinataire;

    /** L'acteur interne destinataire de l'édition */
    private ActeurInterne acteurInterneDestinataire;

    /** La personne concernée de l'édition */
    private Personne personneConcernee;

    /** type d'origine de l'venement */
    private String codeTypeOrigineEdition;

    /** libelle Origine */
    private String libelleOrigine;

    /** l'evenement origine */
    private Evenement evenementOrigine;

    /** identification parquet de l'affaire origine si l'origine est une affaire */
    private IdentificationParquet idParquetAffaireOrigine;

    /**
     * identification parquet de l'affaire de l'evenement origine si l'origine est un evenement
     */
    private IdentificationParquet idParquetAffEvenementOrigine;

    /** l'audience origine */
    private Audience audienceOrigine;

    /** le contenue de l'edition */
    private Document document;

    /** Rapport d'erreur de l'edition */
    private RapportErreurEdition rapportErreurEdition;

    /**Indicateur d'echec : vrai si l'edition a echoue faux si elle a reussi */
    private Boolean echec;

    /** Edition differee correspondante dans la file d'attente */
    private EditionDemandeDifferee editionDifferee;

    /** pre-imprime fourni par la Poste */
    private Recommande recommande;

    private SuiviPEEcrou suiviPEEcrou;

    /** Indicateur d'export CSV partiel */
    private Boolean indicateurExportCsvPartiel;

    /** Nomdu fichier de l'export CSV **/
    private String nomFichierExportCsv;
    
    private boolean isEligiblePortalis;

    private boolean isEligibleBPN;

	public RapportErreurEdition getRapportErreurEdition() {
        return rapportErreurEdition;
    }

    public void setRapportErreurEdition(RapportErreurEdition rapportErreurEdition) {
        this.rapportErreurEdition = rapportErreurEdition;
    }

    public Boolean getEchec() {
        return echec;
    }

    public void setEchec(Boolean echec) {
        this.echec = echec;
    }

    public EditionDemandeDifferee getEditionDifferee() {
        return editionDifferee;
    }

    public void setEditionDifferee(EditionDemandeDifferee editionDifferee) {
        this.editionDifferee = editionDifferee;
    }

    /**
     * @return Returns the audienceOrigine.
     */
    public Audience getAudienceOrigine() {
        return audienceOrigine;
    }

    /**
     * @param audienceOrigine
     *            The audienceOrigine to set.
     */
    public void setAudienceOrigine(Audience audienceOrigine) {
        this.audienceOrigine = audienceOrigine;
    }

    /**
     * @return Returns the evenementOrigine.
     */
    public Evenement getEvenementOrigine() {
        return evenementOrigine;
    }

    /**
     * @param evenementOrigine
     *            The evenementOrigine to set.
     */
    public void setEvenementOrigine(Evenement evenementOrigine) {
        this.evenementOrigine = evenementOrigine;
    }

    /**
     * @return Returns the typeOrigineEditionEnum.
     */
    public TypeOrigineEditionEnum getTypeOrigineEditionEnum() {
        return TypeOrigineEditionEnum.findTypeOrigineEditionEnumByCode(codeTypeOrigineEdition);
    }

    /**
     * @param typeOrigineEditionEnum
     *            The typeOrigineEditionEnum to set.
     */
    public void setTypeOrigineEditionEnum(TypeOrigineEditionEnum typeOrigineEditionEnum) {
        this.codeTypeOrigineEdition = typeOrigineEditionEnum.getCode();
    }

    /**
     * @return Returns the avocatPersonneAffaireDestinataire.
     */
    public AvocatPersonneAffaire getAvocatPersonneAffaireDestinataire() {
        return avocatPersonneAffaireDestinataire;
    }

    /**
     * @param avocatPersonneAffaireDestinataire
     *            The avocatPersonneAffaireDestinataire to set.
     */
    public void setAvocatPersonneAffaireDestinataire(AvocatPersonneAffaire avocatPersonneAffaireDestinataire) {
        this.avocatPersonneAffaireDestinataire = avocatPersonneAffaireDestinataire;
    }

    /**
     * @return Returns the classeurPersonnel.
     */
    public ClasseurPersonnel getClasseurPersonnel() {
        return classeurPersonnel;
    }

    /**
     * @param classeurPersonnel
     *            The classeurPersonnel to set.
     */
    public void setClasseurPersonnel(ClasseurPersonnel classeurPersonnel) {
        this.classeurPersonnel = classeurPersonnel;
    }

    /**
     * @return Returns the dateComposition.
     */
    public Date getDateComposition() {
        return dateComposition;
    }

    /**
     * @param dateComposition
     *            The dateComposition to set.
     */
    public void setDateComposition(Date dateComposition) {
        this.dateComposition = dateComposition;
    }

    /**
     * @return Returns the editionType.
     */
    public EditionType getEditionType() {
        return editionType;
    }

    /**
     * @param editionType
     *            The editionType to set.
     */
    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    /**
     * @return Returns the elementStructureDestinataire.
     */
    public ElementStructure getElementStructureDestinataire() {
        return elementStructureDestinataire;
    }

    /**
     * @param elementStructureDestinataire
     *            The elementStructureDestinataire to set.
     */
    public void setElementStructureDestinataire(ElementStructure elementStructureDestinataire) {
        this.elementStructureDestinataire = elementStructureDestinataire;
    }

    /**
     * @return Returns the evenementAttache.
     */
    public Evenement getEvenementAttache() {
        return evenementAttache;
    }

    /**
     * @param evenementAttache
     *            The evenementAttache to set.
     */
    public void setEvenementAttache(Evenement evenementAttache) {
        this.evenementAttache = evenementAttache;
    }

    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Returns the interpreteDestinataire.
     */
    public Interprete getInterpreteDestinataire() {
        return interpreteDestinataire;
    }

    /**
     * @param interpreteDestinataire
     *            The interpreteDestinataire to set.
     */
    public void setInterpreteDestinataire(Interprete interpreteDestinataire) {
        this.interpreteDestinataire = interpreteDestinataire;
    }

    /**
     * @return Returns the acteurInterneDestinataire.
     */
    public ActeurInterne getActeurInterneDestinataire() {
        return acteurInterneDestinataire;
    }

    /**
     * @param acteurInterneDestinataire
     *            The acteurInterneDestinataire to set.
     */
    public void setActeurInterneDestinataire(ActeurInterne acteurInterneDestinataire) {
        this.acteurInterneDestinataire = acteurInterneDestinataire;
    }

    /**
     * @return Returns the modeConservation.
     */
    public ModeConservationEnum getModeConservation() {
        return ModeConservationEnum.findModeConservationEnumByCode(codeModeConservation);
    }

    /**
     * @param modeConservation
     *            The modeConservation to set.
     */
    public void setModeConservation(ModeConservationEnum modeConservation) {
        this.codeModeConservation = StringUtils.substring(modeConservation.getCode(), 0, 255);
    }

    /**
     * @return Returns the personneAffaireDestinataire.
     */
    public Personne getPersonneAffaireDestinataire() {
        return personneAffaireDestinataire;
    }

    /**
     * @param personneAffaireDestinataire
     *            The personneAffaireDestinataire to set.
     */
    public void setPersonneAffaireDestinataire(Personne personneAffaireDestinataire) {
        this.personneAffaireDestinataire = personneAffaireDestinataire;
    }

    /**
     * @return Returns the serviceDestinataire.
     */
    public Service getServiceDestinataire() {
        return serviceDestinataire;
    }

    /**
     * @param serviceDestinataire
     *            The serviceDestinataire to set.
     */
    public void setServiceDestinataire(Service serviceDestinataire) {
        this.serviceDestinataire = serviceDestinataire;
    }

    /**
     * @return Returns the typeDestinataire.
     */
    public DestinataireEditionEnum getTypeDestinataire() {
        return DestinataireEditionEnum.valueOf(codeTypeDestinataire);
    }

    /**
     * @param typeDestinataire
     *            The typeDestinataire to set.
     */
    public void setTypeDestinataire(DestinataireEditionEnum typeDestinataire) {
        this.codeTypeDestinataire = typeDestinataire.getCode();
    }

    /**
     * @return Returns the personneQualifieeDestinataire.
     */
    public PersonneQualifiee getPersonneQualifieeDestinataire() {
        return personneQualifieeDestinataire;
    }

    /**
     * @param personneQualifieeDestinataire
     *            The personneQualifieeDestinataire to set.
     */
    public void setPersonneQualifieeDestinataire(PersonneQualifiee personneQualifieeDestinataire) {
        this.personneQualifieeDestinataire = personneQualifieeDestinataire;
    }

    /**
     * @return Returns the codeModeConservation.
     */
    public String getCodeModeConservation() {
        return codeModeConservation;
    }

    /**
     * @param codeModeConservation
     *            The codeModeConservation to set.
     */
    public void setCodeModeConservation(String codeModeConservation) {
        this.codeModeConservation = codeModeConservation;
    }

    /**
     * @return Returns the codeTypeDestinataire.
     */
    public String getCodeTypeDestinataire() {
        return codeTypeDestinataire;
    }

    /**
     * @param codeTypeDestinataire
     *            The codeTypeDestinataire to set.
     */
    public void setCodeTypeDestinataire(String codeTypeDestinataire) {
        this.codeTypeDestinataire = codeTypeDestinataire;
    }

    /**
     * @return Returns the codeTypeOrigineEdition.
     */
    public String getCodeTypeOrigineEdition() {
        return codeTypeOrigineEdition;
    }

    /**
     * @param codeTypeOrigineEdition
     *            The codeTypeOrigineEdition to set.
     */
    public void setCodeTypeOrigineEdition(String codeTypeOrigineEdition) {
        this.codeTypeOrigineEdition = codeTypeOrigineEdition;
    }

    /**
     * @return Returns the libelleDestinataire.
     */
    public String getLibelleDestinataire() {
        return libelleDestinataire;
    }

    /**
     * @param libelleDestinataire
     *            The libelleDestinataire to set.
     */
    public void setLibelleDestinataire(String libelleDestinataire) {
        this.libelleDestinataire = StringUtils.substring(libelleDestinataire, 0, 255);
    }

    /**
     * @return Returns the libelleOrigine.
     */
    public String getLibelleOrigine() {
        return libelleOrigine;
    }

    /**
     * Positionne le libellé l'origine, et réduit sa taille si celle-ci dépasse la taille du champ en base.<br>
     *
     * @param libelleOrigine
     *            libellé de l'origine
     */
    public void setAndTruncLibelleOrigine(String libelleOrigine) {
        setLibelleOrigine(StringUtils.substring(libelleOrigine, 0, 255));
    }

    /**
     * Positionne le libellé l'origine.<br>
     * Méthode réservée à Hibernate.
     *
     * @param libelleOrigine
     *            libellé de l'origine
     */
    private void setLibelleOrigine(String libelleOrigine) {
        this.libelleOrigine = libelleOrigine;
    }

    public void setDestinataire(DestinataireEdition destinataireEdition) {

        if (destinataireEdition instanceof AvocatPersonneAffaire) {
            this.codeTypeDestinataire = DestinataireEditionEnum.AvocatPersonneAffaire.getCode();
            this.setAvocatPersonneAffaireDestinataire((AvocatPersonneAffaire) destinataireEdition);
        }
        if (destinataireEdition instanceof ElementStructure) {
            this.codeTypeDestinataire = DestinataireEditionEnum.ElementStructure.getCode();
            this.setElementStructureDestinataire((ElementStructure) destinataireEdition);
        }
        if (destinataireEdition instanceof PersonneQualifiee) {
            this.codeTypeDestinataire = DestinataireEditionEnum.PersonneQualifiee.getCode();
            this.setPersonneQualifieeDestinataire((PersonneQualifiee) destinataireEdition);
        }
        if (destinataireEdition instanceof Personne) {
            this.codeTypeDestinataire = DestinataireEditionEnum.PersonneAffaire.getCode();
            this.setPersonneAffaireDestinataire((Personne) destinataireEdition);
        }
        if (destinataireEdition instanceof Service) {
            this.codeTypeDestinataire = DestinataireEditionEnum.Service.getCode();
            this.setServiceDestinataire((Service) destinataireEdition);
        }

        if (destinataireEdition instanceof Interprete) {
            this.codeTypeDestinataire = DestinataireEditionEnum.Interprete.getCode();
            this.setInterpreteDestinataire((Interprete) destinataireEdition);
        }

        if (destinataireEdition instanceof ActeurInterne) {
            this.codeTypeDestinataire = DestinataireEditionEnum.ActeurInterne.getCode();
            this.setActeurInterneDestinataire((ActeurInterne) destinataireEdition);
        }

    }

    /**
     * @return le contenue
     */
    public Document getDocument() {
        return document;
    }

    /**
     * @param document
     *            l'objet document
     */
    public void setDocument(Document document) {
        this.document = document;
    }

    public IdentificationParquet getIdParquetAffaireOrigine() {
        return idParquetAffaireOrigine;
    }

    public void setIdParquetAffaireOrigine(IdentificationParquet idParquetAffaireOrigine) {
        this.idParquetAffaireOrigine = idParquetAffaireOrigine;
    }

    public IdentificationParquet getIdParquetAffEvenementOrigine() {
        return idParquetAffEvenementOrigine;
    }

    public void setIdParquetAffEvenementOrigine(IdentificationParquet idParquetAffEvenementOrigine) {
        this.idParquetAffEvenementOrigine = idParquetAffEvenementOrigine;
    }

    public Personne getPersonneConcernee() {
        return personneConcernee;
    }

    public void setPersonneConcernee(Personne personneConcernee) {
        this.personneConcernee = personneConcernee;
    }

    /**
     * @return Returns the recommande.
     */
    public Recommande getRecommande() {
        return recommande;
    }

    /**
     * @param recommande
     *            The recommande to set.
     */
    public void setRecommande(Recommande recommande) {
        this.recommande = recommande;
    }

    public SuiviPEEcrou getSuiviPEEcrou() {
        return suiviPEEcrou;
    }

    public void setSuiviPEEcrou(SuiviPEEcrou suiviPEEcrou) {
        this.suiviPEEcrou = suiviPEEcrou;
    }

    /**
     * @return the indicateurExportCsvPartiel
     */
    public Boolean getIndicateurExportCsvPartiel() {
        return indicateurExportCsvPartiel;
    }

    /**
     * @param indicateurExportCsvPartiel
     *            the indicateurExportCsvPartiel to set
     */
    public void setIndicateurExportCsvPartiel(Boolean indicateurExportCsvPartiel) {
        this.indicateurExportCsvPartiel = indicateurExportCsvPartiel;
    }

    /**
     * @return the nomFichierExportCsv
     */
    public String getNomFichierExportCsv() {
        return nomFichierExportCsv;
    }

    /**
     * @param nomFichierExportCsv
     *            the nomFichierExportCsv to set
     */
    public void setNomFichierExportCsv(String nomFichierExportCsv) {
        this.nomFichierExportCsv = nomFichierExportCsv;
    }

    public EnvoiMail getEnvoiMail() {
        return envoiMail;
    }

    public void setEnvoiMail(EnvoiMail envoiMail) {
        this.envoiMail = envoiMail;
    }

    public boolean isEligiblePortalis() {
        return isEligiblePortalis;
    }

    public void setEligiblePortalis(boolean isEligiblePortalis) {
        this.isEligiblePortalis = isEligiblePortalis;
    }

	public boolean isEligibleBPN() {
		return isEligibleBPN;
	}

	public void setEligibleBPN(boolean isEligibleBPN) {
		this.isEligibleBPN = isEligibleBPN;
	}
}
