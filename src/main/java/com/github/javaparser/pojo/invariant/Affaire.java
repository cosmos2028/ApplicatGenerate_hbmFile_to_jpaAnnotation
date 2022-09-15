package fr.gouv.justice.cassiopee.invariant.affaire.model;

import java.io.Serializable;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.administration.acteur.interne.UserContext;
import fr.gouv.justice.cassiopee.common.exception.TechnicalException;
import fr.gouv.justice.cassiopee.commons.collectors.SingletonCollector;
import fr.gouv.justice.cassiopee.invariant.affaire.service.enumeration.EtatAffaireEnum;
import fr.gouv.justice.cassiopee.invariant.affaire.service.enumeration.OrigineAffaireEnum;
import fr.gouv.justice.cassiopee.invariant.affaire.service.enumeration.TypeAffaireEnum;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.infraction.model.Infraction;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.invariant.scelle.model.Depot;
import fr.gouv.justice.cassiopee.processus.ech.model.ReferentielPV;
import fr.gouv.justice.cassiopee.processus.parquet.model.IdentificationParquet;
import fr.gouv.justice.cassiopee.processus.parquet.model.IdentificationService;
import fr.gouv.justice.cassiopee.processus.parquet.service.enumeration.EtatIdentParquetEnum;
import fr.gouv.justice.cassiopee.service.audience.model.AffaireFixee;
import fr.gouv.justice.cassiopee.service.user.UserContextProvider;

/**
 * Cette classe est porteuse: - des caractéristiques d'une affaire telles que: - le type et sous type de dossier, - lieu
 * et date des faits, type d'infraction, identifiants de la procédure (affaire pénale). - d'informations spécifiques
 * Cassiopée: - origine, - date de reprise et indicateur de dossier complet dans le cadre de la reprise de données, -
 * typologie d'archivage, - état et état précédent de l'affaire.
 */
public abstract class Affaire implements Serializable, Cloneable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 3361193498935336249L;
    
    private static final EnumSet<EtatIdentParquetEnum> ETAT_IDENTIFICATION_PARQUET = EnumSet.of(EtatIdentParquetEnum.ACTIF_PLEIN_DROIT,
            EtatIdentParquetEnum.ACTIF_DROIT_LIMITE,
            EtatIdentParquetEnum.JOINTE,
            EtatIdentParquetEnum.FIN_DE_DROITS,
            EtatIdentParquetEnum.DESSAISI,
            EtatIdentParquetEnum.ABSORBE
            );

    /**
     * identifiant dossier
     */
    private Long id;

    private Long version;

    protected String typeAffaire;

    private ReferentielPV referencePVOrigine;

    /**
     * Cet attribut permet de gérer l'état d'une affaire.
     */
    private EtatAffaire etatAffaire;

    /**
     * Cet attribut permet de déterminer l'application d'origine de l'affaire. Les valeurs possibles sont Cassiopée ou
     * Reprise de données.
     */
    private OrigineAffaire origineAffaire;

    /**
     *
     */
    private TypeContentieux penaleTypeContentieux;

    /**
     * Cet attribut est attribué automatiquement par le système, il permet de déterminer l'appartenance à une typologie
     * d'archivage de l'affaire en vue d'un transfert de celle-ci aux AD.
     */
    private TypologieArchivage typologieArchivage;

    /**
     * Il s'agit de conserver l'état précédent avant changement d'état de l'affaire. Cette information est notamment
     * utilisée lors du réveil d'une affaire archivée. Cet attribut est valorisé automatiquement par le système,
     * transparent pour l'utilisateur.
     */
    private EtatAffaire etatPrecedentAffaire;

    /**
     * Il s'agit de la date à laquelle a été créée l'affaire dans le cadre de la reprise de données.
     */
    private Date dateReprise;

    /**
     * Cet indicateur permet d'identifier les affaires considérées comme incomplètes suite à la reprise de données. Il
     * est positionné automatiquement par le système, initialisé lors du traitement de reprise de données et est non
     * modifiable.
     */
    private Boolean dossierComplet;

    /**
     * Date à laquelle la suppression a été effectuée.
     */
    private Date dateSuppression;

    /**
     * La notion d'affaire liée permet de gérer les liens entre affaires. Elle permet par exemple : - de connaître les
     * affaires jointes sur une affaire d'accueil, - de connaître les affaires requête d'une affaire d'origine associée,
     * - de connaître les affaires créées par duplication d'une affaire d'origine dans le cadre d'une instance
     * modificative
     */
    private Set<AffaireLiee> affairesOrigineLiees = new HashSet<>();
    private Set<AffaireLiee> affairesDestinationLiees = new HashSet<>();

    /**
     * Ensemble des personnes reliées au dossier.
     */
    private Set<Personne> personnes = new HashSet<>();

    /**
     * Ensemble des evenement associés a l'affaire
     */
    private Set<Evenement> evenements = new HashSet<>();

    /**
     * Info sur le suppression d'une affaire
     */
    private InfoSuppression infoSuppression;

    /**
     * Cette classe regroupe les indicateurs caractérisant une affaire. Ces indicateurs seront positionnés par
     * l'utilisateur.
     */
    private Indicateur indicateur;

    /**
     * Contient le commentaire associé à une affaire avant sa création dans Cassiopée. Ce commentaire est généré dans
     * Cassiopée lors du traitement de reprise des données. Ce commentaire est consultable depuis le résumé de
     * l'affaire.
     */
    private String commentaire;

    /**
     * Il s'agit des informations spécifiques renseignées par le parquet, rattachées à l'affaire pour une juridiction.
     * Rappel: Si plusieurs juridictions interviennent sur une même affaire, chaque juridiction concernée ne connaît que
     * le numéro de parquet et la NATAFF qu'elle a alloués à l'affaire .
     */
    private Set<IdentificationParquet> identificationParquets = new HashSet<>();

    private Set<UtilisateurAffaireFavorite> utilisateurAffaireFavorites = new HashSet<>();

    /**
     * Champ technique gérant l'incrémentation du rang lors de l'ajout d'une personne à l'affaire.
     */
    private Integer indexePersonnes;

    /**
     * ensemble des affaires fixées, donc les audiences auxquelles est fixée l'affaire.
     */
    private Set<AffaireFixee> fixations;

    /**
     * ensemble des dépots de l'affaire
     */
    private Set<Depot> depots = new HashSet<>();
    /**
     * La vue à l'origine de la création de l'affaire. Créé initialement par la DE_0039 (VGU TTR en modification) par la
     * RG AFFI029.
     */
    private String codeVueCreation;

    /**
     * commentaire sur le stock
     */
    private String commentaireStock;

    /**
     * indicateur Scellés a l'AGRASC
     */
    private Boolean indicateurScelleAGRASC;

    private Set<IdentificationService> identificationsServices = new HashSet<>();

    private Set<Infraction> infractions = new HashSet<>();

    private Date dateArchivage;

    private String alerteDossier;
    
    //DEI_1342
    /**
     * Indicateur si l'affaire est éligible Portalis
     */
    private boolean eligiblePortalis;
    
	/**
     * indicateur si l'affaire a deja fait l'objet d'une extraction par le batch DB Extract en mode Portalis DELTA
     */
    private boolean passageDbExtract;

    /**
     * @return les dépots de l'affaire.
     */
    public Set<Depot> getDepots() {
        return depots;
    }

    /**
     * @param depots
     *            les dépots de l'affaire.
     */
    public void setDepots(Set<Depot> depots) {
        this.depots = depots;
    }

    public Set<IdentificationParquet> getIdentificationParquets() {
        return identificationParquets;
    }

    public IdentificationParquet getParquetByTgi(String codeTgi) {
       UserContext user = UserContextProvider.getUserContext();
       return getParquetByTgi(codeTgi, user);
    }

    public IdentificationParquet getParquetByTgi(String codeTgi, UserContext user) {
    	// 0111295: SCE / impossible d'obtenir édition CSV d'un inventaire des scellés en état Echec dans le classeur
    	if (user != null && user.isCaCass()) {
            return this.getIdentificationParquets().stream()
                    .filter(idp ->  ETAT_IDENTIFICATION_PARQUET.contains(EtatIdentParquetEnum.resolve(idp.getEtatIdentificationParquet().getCode())))
             .sorted((idp1, idp2) -> idp1.getEtatIdentificationParquet().getCode().compareTo(idp2.getEtatIdentificationParquet().getCode()))
             .findFirst().orElse(null);
        } else {
         for (IdentificationParquet identificationParquet : getIdentificationParquets()) {
             if (ObjectUtils.equals(identificationParquet.getTgi().getCode(), codeTgi)) {
                 return identificationParquet;
             }
         }
        }
        throw new IllegalStateException("Pas de parquet pour le tgi " + codeTgi + " sur l'affaire d'id " + id);
     }

    public IdentificationParquet getIdentificationParquet(String codeTgi, String numeroParquetCourt) {
        return identificationParquets.stream()
            .filter(idParquet -> idParquet.getNumeroParquetAAfficher().equals(numeroParquetCourt)
                        && idParquet.getTgi().getCode().equals(codeTgi))
            .collect(SingletonCollector.collector());
    }

    /**
     * Retourne le parquet dont le numéro d'état est le plus petit
     * Autrement dit soit le parquet actif soit un autre parquet
     * @return
     */
    public IdentificationParquet getLastActifIdentificationParquet() {
        IdentificationParquet currentIdParquet = null;
        for (IdentificationParquet idParquet : getIdentificationParquets()) {
            if (currentIdParquet==null || getEtat(idParquet) < getEtat(currentIdParquet) ) {
                currentIdParquet = idParquet;
            }
        }
        return currentIdParquet;
    }

    private int getEtat(IdentificationParquet parquet) {
        return Integer.parseInt(parquet.getEtatIdentificationParquet().getCode());
    }

    public void setIdentificationParquets(Set<IdentificationParquet> identificationParquets) {
        this.identificationParquets = identificationParquets;
    }

    public Set<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(Set<Evenement> evenements) {
        this.evenements = evenements;
    }

    public Set<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(Set<Personne> personnes) {
        this.personnes = personnes;
    }

    public Date getDateReprise() {
        return dateReprise;
    }

    public void setDateReprise(Date dateReprise) {
        this.dateReprise = dateReprise;
    }

    /**
     * Met à jour l'état de l'affaire. L'ancien état est historisé dans etatAffairePrecedent.
     *
     * @param etat
     *            nouvel état de l'affaire.
     */
    @RegleDeGestion({ RG.AFFI064 })
    public void updateEtatAffaire(EtatAffaire etat) {
        this.etatPrecedentAffaire = this.etatAffaire;
        this.etatAffaire = etat;
    }

    public EtatAffaire getEtatAffaire() {
        return etatAffaire;
    }

    public void setEtatAffaire(EtatAffaire etatAffaire) {
        this.etatAffaire = etatAffaire;
    }

    public EtatAffaire getEtatPrecedentAffaire() {
        return etatPrecedentAffaire;
    }

    public void setEtatPrecedentAffaire(EtatAffaire etatPrecedentAffaire) {
        this.etatPrecedentAffaire = etatPrecedentAffaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDossierComplet() {
        return dossierComplet;
    }

    public void setDossierComplet(Boolean indicDossierComplet) {
        this.dossierComplet = indicDossierComplet;
    }

    public Date getDateSuppression() {
        return dateSuppression;
    }

    public void setDateSuppression(Date infoSuppDateSuppression) {
        this.dateSuppression = infoSuppDateSuppression;
    }

    public OrigineAffaire getOrigineAffaire() {
        return origineAffaire;
    }

    public void setOrigineAffaire(OrigineAffaire origineAffaire) {
        this.origineAffaire = origineAffaire;
    }

    public TypeContentieux getPenaleTypeContentieux() {
        return penaleTypeContentieux;
    }

    public void setPenaleTypeContentieux(TypeContentieux penaleTypeContentieux) {
        this.penaleTypeContentieux = penaleTypeContentieux;
    }

    public TypologieArchivage getTypologieArchivage() {
        return typologieArchivage;
    }

    public void setTypologieArchivage(TypologieArchivage typologieArchivage) {
        this.typologieArchivage = typologieArchivage;
    }

    /**
     * Retourne les informations de suppression
     *
     * @return les informations de suppression
     */
    public InfoSuppression getInfoSuppression() {
        return infoSuppression;
    }

    /**
     * Indique les informations de suppression
     *
     * @param infoSuppression
     *            les informations de suppression
     */
    public void setInfoSuppression(InfoSuppression infoSuppression) {
        this.infoSuppression = infoSuppression;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()). //$NON-NLS-1$
                toString();
    }

    /**
     * Retourne les indicateurs caractérisant une affaire
     *
     * @return les indicateurs caractérisant une affaire
     */
    public Indicateur getIndicateur() {
        return indicateur;
    }

    /**
     * Indique les indicateurs caractérisant une affaire
     *
     * @param indicateur
     *            les indicateurs caractérisant une affaire
     */
    public void setIndicateur(Indicateur indicateur) {
        this.indicateur = indicateur;
    }

    /**
     * Renvoie le type affaire
     *
     * @return Type affaire
     */
    public abstract TypeAffaireEnum getTypeAffaire();

    /**
     * Retourne le commentaire de l'affaire
     *
     * @return String commentaire de l'affaire
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Indique le commentaire d'une affaire
     *
     * @param commentaire
     *            commentaire d'une affaire
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * Retourne l'index de la personne
     *
     * @return Interger index de la personne
     */
    public Integer getIndexePersonnes() {
        return indexePersonnes;
    }

    /**
     * indique l'index de la personne
     *
     * @param indexePersonnes
     */
    public void setIndexePersonnes(Integer indexePersonnes) {
        this.indexePersonnes = indexePersonnes;
    }

    /**
     * Retourne la version de la l'affaire.
     *
     * @return version de l'affaire
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Indique la version de l'affaire
     *
     * @param version
     *            de l'affaire
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * Retourne les affaires fixées, donc les audiences auxquelles est fixée l'affaire.
     *
     * @return retourne fixations.
     */
    public Set<AffaireFixee> getFixations() {
        return fixations;
    }

    /**
     * @param fixations
     *            affecte fixations
     */
    public void setFixations(Set<AffaireFixee> fixations) {
        this.fixations = fixations;
    }

    /**
     * @return Returns the utilisateurAffaireFavorites.
     */
    public Set<UtilisateurAffaireFavorite> getUtilisateurAffaireFavorites() {
        return utilisateurAffaireFavorites;
    }

    /**
     * @param utilisateurAffaireFavorites
     *            The utilisateurAffaireFavorites to set.
     */
    public void setUtilisateurAffaireFavorites(Set<UtilisateurAffaireFavorite> utilisateurAffaireFavorites) {
        this.utilisateurAffaireFavorites = utilisateurAffaireFavorites;
    }

    /**
     * @return les liens permettant d'accéder aux affaires de destination, sauf celles d'affaires courrier extérieur.
     */
    public Set<AffaireLiee> getAffairesDestinationLiees() {
        return affairesDestinationLiees;
    }

    /**
     * @param affairesDestinationLiees
     *            les liens permettant d'accéder aux affaires de destination, sauf celles d'affaires courrier extérieur.
     */
    public void setAffairesDestinationLiees(Set<AffaireLiee> affairesDestinationLiees) {
        this.affairesDestinationLiees = affairesDestinationLiees;
    }

    /**
     * @return les liens permettant d'accéder aux affaires d'origine, ainsi que les liens décrivant les affaires
     *         courrier extérieur de destination.
     */
    public Set<AffaireLiee> getAffairesOrigineLiees() {
        return affairesOrigineLiees;
    }

    /**
     * @return les liens permettant d'accéder aux affaires d'origine, ainsi que les liens décrivant les affaires
     *         courrier extérieur de destination.
     */
    public Set<AffaireLiee> getAffairesOrigineLieesParJonction() {
        return affairesOrigineLiees.stream().filter(affaireOrigineLiee -> affaireOrigineLiee.getTgiAffaireAccueil() != null && affaireOrigineLiee.getTgiAffaireJointe() != null).collect(Collectors.toSet());
    }

    /**
     * @param affairesOrigineLiees
     *            les liens permettant d'accéder aux affaires d'origine, ainsi que les liens décrivant les affaires
     *            courrier extérieur de destination.
     */
    public void setAffairesOrigineLiees(Set<AffaireLiee> affairesOrigineLiees) {
        this.affairesOrigineLiees = affairesOrigineLiees;
    }

    public Affaire duplicationAffaireForDIM() {
        Affaire affaireDuplicate;
        try {
            affaireDuplicate = this.getClass().newInstance();
        } catch (InstantiationException e) {
            throw new TechnicalException("Instantiation Exception", e);
        } catch (IllegalAccessException e) {
            throw new TechnicalException("IllegalAccessException", e);
        }

        affaireDuplicate.setId(null);

        EtatAffaire etatAffaire = new EtatAffaire();
        etatAffaire.setCode(EtatAffaireEnum.ACTIVE.getCode());
        affaireDuplicate.updateEtatAffaire(etatAffaire);
        OrigineAffaire origineAffaire = new OrigineAffaire();
        origineAffaire.setCode(OrigineAffaireEnum.CASSIOPEE.getCode());
        affaireDuplicate.setOrigineAffaire(origineAffaire);

        Indicateur indicateur = new Indicateur();
        indicateur.setScellePv(Boolean.FALSE);
        indicateur.setGardiennage(Boolean.FALSE);
        indicateur.setAffaireSignalee(Boolean.FALSE);
        indicateur.setAffaireCachee(Boolean.FALSE);
        indicateur.setInteretHistorique(Boolean.FALSE);
        indicateur.setScellesDansModuleScellesOuSpark(Integer.valueOf(0));
        
        indicateur.setAffaireMAEE_E(this.getIndicateur() != null? this.getIndicateur().getAffaireMAEE_E(): Boolean.FALSE);
        indicateur.setAffaireMAEE_S(this.getIndicateur() != null? this.getIndicateur().getAffaireMAEE_S(): Boolean.FALSE);
        //[DEI-041] EIA-Spark : « Scellés dans l'affaire »
        indicateur.setPresenceScellesDansAffaire(Boolean.FALSE);
        affaireDuplicate.setIndicateur(indicateur);

        affaireDuplicate.setCommentaire(this.getCommentaire());
        affaireDuplicate.setCodeVueCreation(this.getCodeVueCreation());
        affaireDuplicate.setReferencePVOrigine(this.referencePVOrigine);
        return affaireDuplicate;
    }

    public String getCodeVueCreation() {
        return codeVueCreation;
    }

    public void setCodeVueCreation(String codeVueCreation) {
        this.codeVueCreation = codeVueCreation;
    }

    public String getCommentaireStock() {
        return this.commentaireStock;
    }

    public void setCommentaireStock(String commentaireStock) {
        this.commentaireStock = commentaireStock;
    }

    public Boolean getIndicateurScelleAGRASC() {
        return indicateurScelleAGRASC;
    }

    public void setIndicateurScelleAGRASC(Boolean indicateurScelleAGRASC) {
        this.indicateurScelleAGRASC = indicateurScelleAGRASC;
    }

    public Set<IdentificationService> getIdentificationsServices() {
        return identificationsServices;
    }

    public void setIdentificationsServices(Set<IdentificationService> identificationsServices) {
        this.identificationsServices = identificationsServices;
    }

    public ReferentielPV getReferencePVOrigine() {
        return referencePVOrigine;
    }

    public void setReferencePVOrigine(ReferentielPV referencePVOrigine) {
        this.referencePVOrigine = referencePVOrigine;
    }

    public Set<Infraction> getInfractions() {
        return infractions;
    }

    public void setInfractions(Set<Infraction> infractions) {
        this.infractions = infractions;
    }

    public Date getDateArchivage() {
        return dateArchivage;
    }

    public void setDateArchivage(Date dateArchivage) {
        this.dateArchivage = dateArchivage;
    }
    
    /**
     * @return retourne alerteDossier.
     */
    public String getAlerteDossier() {
        return alerteDossier;
    }

    /**
     * @param alerteDossier
     *            affecte le champs alerteDossier.
     */
    public void setAlerteDossier(String alerteDossier) {
        this.alerteDossier = alerteDossier;
    }

    //DEI_1342
    public boolean isEligiblePortalis() {
		return eligiblePortalis;
	}

	public void setEligiblePortalis(boolean eligiblePortalis) {
		this.eligiblePortalis = eligiblePortalis;
	}

	public boolean isPassageDbExtract() {
		return passageDbExtract;
	}

	public void setPassageDbExtract(boolean passageDbExtract) {
		this.passageDbExtract = passageDbExtract;
	}
    
    
}
