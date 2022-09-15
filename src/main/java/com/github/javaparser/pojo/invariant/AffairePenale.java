package fr.gouv.justice.cassiopee.invariant.affaire.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

import fr.gouv.justice.cassiopee.common.util.DateIncomplete;
import fr.gouv.justice.cassiopee.commons.metier.model.DateIncompleteAvecPrefixe;
import fr.gouv.justice.cassiopee.invariant.infraction.model.InfractionMetier;
import fr.gouv.justice.cassiopee.invariant.personne.model.TitrePaiement;
import fr.gouv.justice.cassiopee.referentiel.infraction.codification.model.MnemoTypeInfraction;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Commune;

/**
 * Cette classe est porteuse des informations sp�cifiques relatives aux affaires p�nales, c'est-�-dire: la date et lieu
 * des faits.
 */
public abstract class AffairePenale extends Affaire {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -2190319174621472801L;

    /*
     *
     */
    private Boolean recherche;

    /**
     * Type infraction Affaire penale
     */
    private MnemoTypeInfraction typeInfraction;

    /**
     *
     */
    private TypeContentieux typeContentieux;

    /**
     * Il s'agit du code INSEE de la commune du lieu de commission des faits. Cette donn�e est extraite pour
     * l'infocentre, elle permet l'analyse de la d�linquance par ressort g�ographique.
     */
    private Commune faitsCommune;

    /**
     * L'attribut Lieu des faits correspond � l'indication litt�rale du lieu de commission des faits : il permet de
     * saisir les lieux hors ressort du TGI, � l?�tranger, dans l'espace a�rien ou maritime.
     */
    private String faitsLieu;

    /**
     * Il s'agit de la date de commission des faits. Cette date peut �tre incompl�te et peut donc avoir les formats
     * suivants: jj/mm/aaaa, mm/aaaa, aaaa. Les d�lits continus :?d�tention de stup�fiants?, ?recel? ont parfois une
     * date de d�but de faits inconnue. Dans ce cas la date des faits correspond � la date du PV de constatation des
     * faits, point de d�part de la prescription de l?action publique.
     */
    private DateIncompleteAvecPrefixe faitsDateDebut;

    /**
     * Il s'agit de la date de fin de commission des faits dans le cas o� les faits s'�talent sur une p�riode.
     */
    private DateIncompleteAvecPrefixe faitsDateFin;

    /**
     * D�lai en mois dans lequel il est souhaitable que l'affaire soit trait�e et achev�e. Cet attribut ne concerne ni
     * les affaires commerciales ni les affaires civiles et administratives du Parquet.
     */
    private Byte delaiAchevement;

    /**
     * Indicateur permettant de savoir si l'affaire est trait�e dans le cadre de la comp�tence JIRS du TGI. Il n'est
     * saisissable que dans les TGI ayant une comp�tence JIRS. Il n'est affich� que si le TGI a une comp�tence JIRS.
     */
    private Boolean indicateurJirs;

    /**
     * C'est l'identifiant interminist�riel unique et non �quivoque sur l?ensemble du territoire assurant la tra�abilit�
     * d?une proc�dure depuis le service verbalisateur (police/gendarmerie) jusqu?au recouvrement de l?amende (tr�sor)
     * en passant par les cha�nes p�nales (justice) . Il est compos� de: - code + num�ro �l�ment de structure (8) �
     * l?origine de la proc�dure (par d�faut �metteur de l'�v�nement de saisine) - ann�e sur 2 chiffres (2) (par d�faut
     * ann�e en cours) - num�ro chronologique de cette proc�dure dans cette ann�e pour cet �l�ment de structure (8
     * chiffres).
     */
    private String idProcedure;

    /**
     * Cette classe permet de stocker les informations relatives � la d�couverte de ch�quiers, ch�ques ou cartes
     * bancaires pour lesquels on ne conna�t pas dans l'imm�diat ni les comptes associ�s, ni les victimes titulaires, ni
     * les auteurs. La suite de l'affaire permettra peut-�tre de retrouver et d'identifier le compte associ� � ces
     * titres.
     */
    private String titreDecouvertCommentaire;

    /**
     * Liste des titres de paiement rattach�s soit au compte, soit � la victime b�n�ficiaire.
     */
    private Set<TitrePaiement> titresDePaiement;

    /**
     * Infraction reli�es � une affaire penale
     */

    private Set<InfractionMetier> infractionsMetier = new HashSet<InfractionMetier>();

    /**
     * Dans le cas d'une affaire d'infraction aux moyens de paiement, cette classe r�f�rence et d�crit le (ou les)
     * compte(s) concern�(s), elle permet aussi d'acc�der au titulaire de ces comptes. Selon les cas, le titulaire peut
     * �tre victime ou auteur dans l'affaire. Peuvent �tre d�clar�s �galement d'autres titulaires du compte.
     */
    private Set<Compte> comptes = new HashSet<Compte>();

    /**
     * Il s'agit d'identifier le v�hicule utile dans le cadre de l?affaire, pouvant avoir servi � la commission d'une
     * infraction dans le cadre d'une affaire.
     */
    private Set<Vehicule> vehicules = new LinkedHashSet<Vehicule>();

    /**
     * attribut technique (compteur) qui permet de g�rer le rang des infractions dans l'affaire
     */
    private Integer indexeInfractions;

    /**
     * Permet d'identifier une affaire import�e dans KCOP depuis les EIA
     */
    private String identifiantJustice;
    /**
     * indicateur � p�le de l?instruction � pour distinguer les affaires trait�es par ce p�le
     */
    private Boolean poleInstruction;

    private Boolean affaireEurojust;

    private String alerteDossier;

    public Set<InfractionMetier> getInfractionsMetier() {
        return infractionsMetier;
    }

    public void setInfractionsMetier(Set<InfractionMetier> infractionsMetier) {
        this.infractionsMetier = infractionsMetier;
    }

    public Commune getFaitsCommune() {
        return faitsCommune;
    }

    public void setFaitsCommune(Commune penaleCommuneFaits) {
        this.faitsCommune = penaleCommuneFaits;
    }

    public java.util.Set<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(java.util.Set<Compte> penaleComptes) {
        this.comptes = penaleComptes;
    }

    public Byte getDelaiAchevement() {
        return delaiAchevement;
    }

    public void setDelaiAchevement(Byte penaleDelaiAchevement) {
        this.delaiAchevement = penaleDelaiAchevement;
    }

    public String getFaitsLieu() {
        return faitsLieu;
    }

    public void setFaitsLieu(String penaleFaitsLieu) {
        this.faitsLieu = penaleFaitsLieu;
    }

    public String getIdProcedure() {
        return idProcedure;
    }

    public void setIdProcedure(String penaleIdProcedure) {
        this.idProcedure = penaleIdProcedure;
    }

    public Boolean getIndicateurJirs() {
        return indicateurJirs;
    }

    public void setIndicateurJirs(Boolean penaleIndicateurJirs) {
        this.indicateurJirs = penaleIndicateurJirs;
    }

    public MnemoTypeInfraction getTypeInfraction() {
        return typeInfraction;
    }

    public void setTypeInfraction(MnemoTypeInfraction penaleTypeInfraction) {
        this.typeInfraction = penaleTypeInfraction;
    }

    public java.util.Set<Vehicule> getVehicules() {
        return vehicules;
    }

    public void setVehicules(java.util.Set<Vehicule> penaleVehicules) {
        this.vehicules = penaleVehicules;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString()).toString();
    }

    public TypeContentieux getTypeContentieux() {
        return typeContentieux;
    }

    public void setTypeContentieux(TypeContentieux typeContentieux) {
        this.typeContentieux = typeContentieux;
    }

    public DateIncompleteAvecPrefixe getFaitsDateDebut() {
        return faitsDateDebut;
    }

    public void setFaitsDateDebut(DateIncompleteAvecPrefixe faitsDateDebut) {
        this.faitsDateDebut = faitsDateDebut;
    }

    public DateIncompleteAvecPrefixe getFaitsDateFin() {
        return faitsDateFin;
    }

    public void setFaitsDateFin(DateIncompleteAvecPrefixe faitsDateFin) {
        this.faitsDateFin = faitsDateFin;
    }

    /**
     * Retourne la date des faits par rapport au pr�fixe de la date de d�but et de fin (RG: AFMXX2)
     *
     * @return
     */
    public DateIncomplete getDateFaits() {
        return DateIncompleteAvecPrefixe.getDateFaits(faitsDateDebut, faitsDateFin);
    }

    public boolean hasVehicules() {
        return !getVehicules().isEmpty();
    }

    /**
     * Retourne la valeur du compteur qui permet de g�rer le rang des infractions dans l'affaire
     *
     * @return la valeur du compteur qui permet de g�rer le rang des infractions dans l'affaire
     */
    public Integer getIndexeInfractions() {
        return indexeInfractions;
    }

    /**
     * Met � jour la valeur du compteur qui permet de g�rer le rang des infractions dans l'affaire
     *
     * @param indexeInfractions
     *            la valeur du compteur qui permet de g�rer le rang des infractions dans l'affaire
     */
    public void setIndexeInfractions(Integer indexeInfractions) {
        this.indexeInfractions = indexeInfractions;
    }

    /**
     * Recherche du rang de la prochaine infraction
     *
     * @return le rang de la prochaine infraction
     */
    public Integer getRangProchaineInfraction() {
        Integer rang = this.getIndexeInfractions();
        if (rang == null) {
            rang = Integer.valueOf(1);
        } else {
            rang = Integer.valueOf(rang.intValue() + 1);
        }
        return rang;
    }

    /**
     * Recherche du rang max des infractions
     *
     * @return le rang rang max des infractions
     */
    public Integer getRangMaxInfraction() {
        Integer rang = this.getIndexeInfractions();
        if (rang == null) {
            rang = Integer.valueOf(0);
        }
        return rang;
    }

    public Set<TitrePaiement> getTitresDePaiement() {
        return titresDePaiement;
    }

    public void setTitresDePaiement(Set<TitrePaiement> titresDePaiement) {
        this.titresDePaiement = titresDePaiement;
    }

    public AffairePenale duplicationAffaireForDIM() {
        AffairePenale affaireDuplicate = (AffairePenale) super.duplicationAffaireForDIM();

        if (this.getTypeInfraction() != null) {
            MnemoTypeInfraction newTypeInfraction = new MnemoTypeInfraction();
            newTypeInfraction.setCode(this.getTypeInfraction().getCode());
            newTypeInfraction.setLibelle(this.getTypeInfraction().getLibelle());
            affaireDuplicate.setTypeInfraction(newTypeInfraction);
        }

        if (this.getTypeContentieux() != null) {
            TypeContentieux newTypeContentieux = new TypeContentieux();
            newTypeContentieux.setCode(this.getTypeContentieux().getCode());
            newTypeContentieux.setLibelle(this.getTypeContentieux().getLibelle());
            newTypeContentieux.setDateApparition(this.getTypeContentieux().getDateApparition());
            newTypeContentieux.setDateFermeture(this.getTypeContentieux().getDateFermeture());
            affaireDuplicate.setTypeContentieux(newTypeContentieux);
        }

        if (this.getIndicateurJirs() != null) {
            affaireDuplicate.setIndicateurJirs(this.getIndicateurJirs());
        } else {
            affaireDuplicate.setIndicateurJirs(Boolean.FALSE);
        }

        if (this.getPoleInstruction() != null) {
            affaireDuplicate.setPoleInstruction(this.getIndicateurJirs());
        } else {
            affaireDuplicate.setPoleInstruction(Boolean.FALSE);
        }

        // -- Les faits
        affaireDuplicate.setFaitsCommune(this.getFaitsCommune());
        affaireDuplicate.setFaitsDateDebut(this.getFaitsDateDebut());
        affaireDuplicate.setFaitsDateFin(this.getFaitsDateFin());
        affaireDuplicate.setFaitsLieu(this.getFaitsLieu());
        affaireDuplicate.setDelaiAchevement(this.getDelaiAchevement());

        affaireDuplicate.setIdProcedure(this.getIdProcedure());

        // -- Les titres decouverts (cf AJEM012)
        // NB: suivant modele actuellement sur l'affaire
        affaireDuplicate.setTitreDecouvertCommentaire(this.getTitreDecouvertCommentaire());

        // les relations avec autres entit�s (cf comptes/titre de
        // paiement/infraction) sont dupliqu�es le cas �ch�ant en SQL via des
        // procedures stock�es

        return affaireDuplicate;

    }

    /**
     * @return Returns the titreDecouvertCommentaire.
     */
    public String getTitreDecouvertCommentaire() {
        return titreDecouvertCommentaire;
    }

    /**
     * @param titreDecouvertCommentaire
     *            The titreDecouvertCommentaire to set.
     */
    public void setTitreDecouvertCommentaire(String titreDecouvertCommentaire) {
        this.titreDecouvertCommentaire = titreDecouvertCommentaire;
    }

    public Boolean getRecherche() {
        return recherche;
    }

    public void setRecherche(Boolean recherche) {
        this.recherche = recherche;
    }

    public Boolean getPoleInstruction() {
        return poleInstruction;
    }

    public void setPoleInstruction(Boolean poleInstruction) {
        this.poleInstruction = poleInstruction;
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

    /**
     * @return the identifiantJustice
     */
    public String getIdentifiantJustice() {
        return identifiantJustice;
    }

    /**
     * @param identifiantJustice
     *            the identifiantJustice to set
     */
    public void setIdentifiantJustice(String identifiantJustice) {
        this.identifiantJustice = identifiantJustice;
    }

    public Boolean getAffaireEurojust() {
        return affaireEurojust;
    }

    public void setAffaireEurojust(Boolean affaireEurojust) {
        this.affaireEurojust = affaireEurojust;
    }
}
