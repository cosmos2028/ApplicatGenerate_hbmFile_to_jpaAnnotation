package com.github.javaparser.pojoAnnote;

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
import javax.persistence.*;

/**
 * Cette classe est porteuse: - des caract�ristiques d'une affaire telles que: - le type et sous type de dossier, - lieu
 * et date des faits, type d'infraction, identifiants de la proc�dure (affaire p�nale). - d'informations sp�cifiques
 * Cassiop�e: - origine, - date de reprise et indicateur de dossier complet dans le cadre de la reprise de donn�es, -
 * typologie d'archivage, - �tat et �tat pr�c�dent de l'affaire.
 */
@Entity
@Table(name = "AFF_AFFAIRE")
public abstract class Affaire implements Serializable, Cloneable {

    /**
     * Identifiant version pour la s�rialisation
     */
    private static final long serialVersionUID = 3361193498935336249L;

    private static final EnumSet<EtatIdentParquetEnum> ETAT_IDENTIFICATION_PARQUET = EnumSet.of(EtatIdentParquetEnum.ACTIF_PLEIN_DROIT, EtatIdentParquetEnum.ACTIF_DROIT_LIMITE, EtatIdentParquetEnum.JOINTE, EtatIdentParquetEnum.FIN_DE_DROITS, EtatIdentParquetEnum.DESSAISI, EtatIdentParquetEnum.ABSORBE);

    /**
     * identifiant dossier
     */
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;

    private Long version;

    @Column(name = "DISC_CODE_TYPE_AFFAIRE", length = 255)
    protected String typeAffaire;

    private ReferentielPV referencePVOrigine;

    /**
     * Cet attribut permet de g�rer l'�tat d'une affaire.
     */
    private EtatAffaire etatAffaire;

    /**
     * Cet attribut permet de d�terminer l'application d'origine de l'affaire. Les valeurs possibles sont Cassiop�e ou
     * Reprise de donn�es.
     */
    private OrigineAffaire origineAffaire;

    /**
     */
    private TypeContentieux penaleTypeContentieux;

    /**
     * Cet attribut est attribu� automatiquement par le syst�me, il permet de d�terminer l'appartenance � une typologie
     * d'archivage de l'affaire en vue d'un transfert de celle-ci aux AD.
     */
    private TypologieArchivage typologieArchivage;

    /**
     * Il s'agit de conserver l'�tat pr�c�dent avant changement d'�tat de l'affaire. Cette information est notamment
     * utilis�e lors du r�veil d'une affaire archiv�e. Cet attribut est valoris� automatiquement par le syst�me,
     * transparent pour l'utilisateur.
     */
    private EtatAffaire etatPrecedentAffaire;

    /**
     * Il s'agit de la date � laquelle a �t� cr��e l'affaire dans le cadre de la reprise de donn�es.
     */
    @Column(name = "DATE_REPRISE", length = 7)
    private Date dateReprise;

    /**
     * Cet indicateur permet d'identifier les affaires consid�r�es comme incompl�tes suite � la reprise de donn�es. Il
     * est positionn� automatiquement par le syst�me, initialis� lors du traitement de reprise de donn�es et est non
     * modifiable.
     */
    @Column(name = "INDIC_DOSSIER_COMPLET", precision = 1, scale = 0)
    private Boolean dossierComplet;

    /**
     * Date � laquelle la suppression a �t� effectu�e.
     */
    private Date dateSuppression;

    /**
     * La notion d'affaire li�e permet de g�rer les liens entre affaires. Elle permet par exemple : - de conna�tre les
     * affaires jointes sur une affaire d'accueil, - de conna�tre les affaires requ�te d'une affaire d'origine associ�e,
     * - de conna�tre les affaires cr��es par duplication d'une affaire d'origine dans le cadre d'une instance
     * modificative
     */
    private Set<AffaireLiee> affairesOrigineLiees = new HashSet<>();

    private Set<AffaireLiee> affairesDestinationLiees = new HashSet<>();

    /**
     * Ensemble des personnes reli�es au dossier.
     */
    private Set<Personne> personnes = new HashSet<>();

    /**
     * Ensemble des evenement associ�s a l'affaire
     */
    private Set<Evenement> evenements = new HashSet<>();

    /**
     * Info sur le suppression d'une affaire
     */
    private InfoSuppression infoSuppression;

    /**
     * Cette classe regroupe les indicateurs caract�risant une affaire. Ces indicateurs seront positionn�s par
     * l'utilisateur.
     */
    private Indicateur indicateur;

    /**
     * Contient le commentaire associ� � une affaire avant sa cr�ation dans Cassiop�e. Ce commentaire est g�n�r� dans
     * Cassiop�e lors du traitement de reprise des donn�es. Ce commentaire est consultable depuis le r�sum� de
     * l'affaire.
     */
    @Column(name = "COMMENTAIRE")
    private String commentaire;

    /**
     * Il s'agit des informations sp�cifiques renseign�es par le parquet, rattach�es � l'affaire pour une juridiction.
     * Rappel: Si plusieurs juridictions interviennent sur une m�me affaire, chaque juridiction concern�e ne conna�t que
     * le num�ro de parquet et la NATAFF qu'elle a allou�s � l'affaire .
     */
    private Set<IdentificationParquet> identificationParquets = new HashSet<>();

    private Set<UtilisateurAffaireFavorite> utilisateurAffaireFavorites = new HashSet<>();

    /**
     * Champ technique g�rant l'incr�mentation du rang lors de l'ajout d'une personne � l'affaire.
     */
    @Column(name = "INDEXE_PERSONNES")
    private Integer indexePersonnes;

    /**
     * ensemble des affaires fix�es, donc les audiences auxquelles est fix�e l'affaire.
     */
    private Set<AffaireFixee> fixations;

    /**
     * ensemble des d�pots de l'affaire
     */
    private Set<Depot> depots = new HashSet<>();

    /**
     * La vue � l'origine de la cr�ation de l'affaire. Cr�� initialement par la DE_0039 (VGU TTR en modification) par la
     * RG AFFI029.
     */
    @Column(name = "CODE_VUE_CREATION")
    private String codeVueCreation;

    /**
     * commentaire sur le stock
     */
    @Column(name = "COMMENTAIRE_STOCK")
    private String commentaireStock;

    /**
     * indicateur Scell�s a l'AGRASC
     */
    @Column(name = "INDIC_SCELLE_AGRASC", precision = 1, scale = 0)
    private Boolean indicateurScelleAGRASC;

    private Set<IdentificationService> identificationsServices = new HashSet<>();

    private Set<Infraction> infractions = new HashSet<>();

    @Column(name = "DATE_ARCHIVAGE", length = 7)
    private Date dateArchivage;

    @Column(name = "ALERTE_AFFAIRE")
    private String alerteDossier;

    // DEI_1342
    /**
     * Indicateur si l'affaire est �ligible Portalis
     */
    @Column(name = "ELIGIBLE_PORTALIS", precision = 1, scale = 0)
    private boolean eligiblePortalis;

    /**
     * indicateur si l'affaire a deja fait l'objet d'une extraction par le batch DB Extract en mode Portalis DELTA
     */
    @Column(name = "PASSAGE_DB_EXTRACT", precision = 1, scale = 0)
    private boolean passageDbExtract;

    /**
     * @return les d�pots de l'affaire.
     */
    public Set<Depot> getDepots() {
        return depots;
    }

    /**
     * @param depots
     *            les d�pots de l'affaire.
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
        // 0111295: SCE / impossible d'obtenir �dition CSV d'un inventaire des scell�s en �tat Echec dans le classeur
        if (user != null && user.isCaCass()) {
            return this.getIdentificationParquets().stream().filter(idp -> ETAT_IDENTIFICATION_PARQUET.contains(EtatIdentParquetEnum.resolve(idp.getEtatIdentificationParquet().getCode()))).sorted((idp1, idp2) -> idp1.getEtatIdentificationParquet().getCode().compareTo(idp2.getEtatIdentificationParquet().getCode())).findFirst().orElse(null);
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
        return identificationParquets.stream().filter(idParquet -> idParquet.getNumeroParquetAAfficher().equals(numeroParquetCourt) && idParquet.getTgi().getCode().equals(codeTgi)).collect(SingletonCollector.collector());
    }

    /**
     * Retourne le parquet dont le num�ro d'�tat est le plus petit
     * Autrement dit soit le parquet actif soit un autre parquet
     * @return
     */
    public IdentificationParquet getLastActifIdentificationParquet() {
        IdentificationParquet currentIdParquet = null;
        for (IdentificationParquet idParquet : getIdentificationParquets()) {
            if (currentIdParquet == null || getEtat(idParquet) < getEtat(currentIdParquet)) {
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
     * Met � jour l'�tat de l'affaire. L'ancien �tat est historis� dans etatAffairePrecedent.
     *
     * @param etat
     *            nouvel �tat de l'affaire.
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
        return // $NON-NLS-1$
        new ToStringBuilder(this).append("id", getId()).toString();
    }

    /**
     * Retourne les indicateurs caract�risant une affaire
     *
     * @return les indicateurs caract�risant une affaire
     */
    public Indicateur getIndicateur() {
        return indicateur;
    }

    /**
     * Indique les indicateurs caract�risant une affaire
     *
     * @param indicateur
     *            les indicateurs caract�risant une affaire
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
     * Retourne les affaires fix�es, donc les audiences auxquelles est fix�e l'affaire.
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
     * @return les liens permettant d'acc�der aux affaires de destination, sauf celles d'affaires courrier ext�rieur.
     */
    public Set<AffaireLiee> getAffairesDestinationLiees() {
        return affairesDestinationLiees;
    }

    /**
     * @param affairesDestinationLiees
     *            les liens permettant d'acc�der aux affaires de destination, sauf celles d'affaires courrier ext�rieur.
     */
    public void setAffairesDestinationLiees(Set<AffaireLiee> affairesDestinationLiees) {
        this.affairesDestinationLiees = affairesDestinationLiees;
    }

    /**
     * @return les liens permettant d'acc�der aux affaires d'origine, ainsi que les liens d�crivant les affaires
     *         courrier ext�rieur de destination.
     */
    public Set<AffaireLiee> getAffairesOrigineLiees() {
        return affairesOrigineLiees;
    }

    /**
     * @return les liens permettant d'acc�der aux affaires d'origine, ainsi que les liens d�crivant les affaires
     *         courrier ext�rieur de destination.
     */
    public Set<AffaireLiee> getAffairesOrigineLieesParJonction() {
        return affairesOrigineLiees.stream().filter(affaireOrigineLiee -> affaireOrigineLiee.getTgiAffaireAccueil() != null && affaireOrigineLiee.getTgiAffaireJointe() != null).collect(Collectors.toSet());
    }

    /**
     * @param affairesOrigineLiees
     *            les liens permettant d'acc�der aux affaires d'origine, ainsi que les liens d�crivant les affaires
     *            courrier ext�rieur de destination.
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
        indicateur.setAffaireMAEE_E(this.getIndicateur() != null ? this.getIndicateur().getAffaireMAEE_E() : Boolean.FALSE);
        indicateur.setAffaireMAEE_S(this.getIndicateur() != null ? this.getIndicateur().getAffaireMAEE_S() : Boolean.FALSE);
        // [DEI-041] EIA-Spark : � Scell�s dans l'affaire �
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

    // DEI_1342
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
