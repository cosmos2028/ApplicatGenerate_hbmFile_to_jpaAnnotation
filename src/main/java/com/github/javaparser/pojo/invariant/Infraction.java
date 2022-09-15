package fr.gouv.justice.cassiopee.invariant.infraction.model;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.common.util.DateIncomplete;
import fr.gouv.justice.cassiopee.commons.metier.model.DateIncompleteAvecPrefixe;
import fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire;
import fr.gouv.justice.cassiopee.invariant.affaire.model.AffairePenale;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.EvenementInfraction;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.Groupe;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.PersonneEvenementInfraction;
import fr.gouv.justice.cassiopee.processus.ech.model.DoublonInfraction;
import fr.gouv.justice.cassiopee.processus.ech.model.InfractionTajEligible;
import fr.gouv.justice.cassiopee.processus.ech.model.ReferentielPV;
import fr.gouv.justice.cassiopee.processus.tdr.model.CommentaireRepris;
import fr.gouv.justice.cassiopee.processus.tdr.model.MessageReprise;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.EvenementTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.infraction.codification.model.CirconstanceAggravante;
import fr.gouv.justice.cassiopee.referentiel.infraction.codification.model.ModaliteParticipation;
import fr.gouv.justice.cassiopee.referentiel.infraction.codification.model.NatureAffaire;
import fr.gouv.justice.cassiopee.referentiel.infraction.service.enumeration.MnemoTypeInfractionEnum;
import fr.gouv.justice.cassiopee.referentiel.infraction.type.model.NatureInfractionType;
import fr.gouv.justice.cassiopee.referentiel.infraction.type.model.VersionNatureInfraction;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Commune;

/**
 * Une infraction est d�finie par: <br>
 * des �l�ments propres � l'affaire:
 * <ul>
 * <li>sa date ou sa p�riode,</li>
 * <li>son lieu,</li>
 * <li>des �l�ments factuels propres au cas d'esp�ce li�s � l'auteur (ex: taux alcool�mie, quantit� de drogue, vitesse)
 * explicit�s dans la qualification d�velopp�e,</li>
 * <li>ses liens avec la ou les victimes, le ou les auteurs,</li>
 * <li>les modalit�s de participation des auteurs dans le cadre de l'infraction.</li>
 * </ul>
 * des �l�ments issus du r�f�rentiel:
 * <ul>
 * <li>son code natinf,</li>
 * <li>sa qualification simplifi�e,</li>
 * <li>les textes d�finissants et r�primants nationaux,</li>
 * <li>les textes d�finissants locaux.</li>
 * </ul>
 * Dans le cas d'une infraction "Vitesse" , des informations sp�cifiques � un �quipement de terrain sont fournies
 * automatiquement � partir de l'application WINOMP ou "Contr�le automatis�".
 */
public class Infraction implements java.io.Serializable, Cloneable, Comparable<Infraction>, InfractionTajEligible {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -6990206461619912678L;

    /** Identifiant de l'objet Infraction */
    private Long id;

    /** Version de la nature de l'infraction associ�e � la date de l'infraction */
    private VersionNatureInfraction versionNatureInfraction;

    /** Affaire � laquelle cette infraction est rattach�e */
    private Affaire affaire;

    /** Commune du lieu de l'infraction */
    private Commune commune;

    /**
     * Texte local qui d�crit l'infraction dans le cas d'une infraction � un texte local
     */
    private TexteLocal texteLocal;

    /** Etat courant de l'infraction */
    private EtatInfraction etatCourant;

    /** Etat pr�cedent de l'infraction */
    private EtatInfraction etatPrecedent;

    /** Description du lieu de l'infraction (texte libre) */
    private String libelleLieuInfraction;

    /** Date de d�but de l'infraction */
    private DateIncompleteAvecPrefixe dateDebut;

    /** Date de fin de l'infraction */
    private DateIncompleteAvecPrefixe dateFin;

    /** Rang de l'infraction dans le dossier */
    private Integer rang;

    private Amnistie amnistie;

    /** Vitesse dans le cas d'une infraction routi�re li�e � la vitesse */
    private Vitesse vitesse;

    /** Pr�sence d'alcool dans les poumons */
    private Alcoolemie alcoolemiePoumon;

    /** Pr�sence d'alcool dans le sang */
    private Alcoolemie alcoolemieSang;

    /**
     * Cet indicateur permet de d�terminer si cette infraction d�termin�e par sa NATINF peut �tre commise par une
     * personne morale.
     */
    private Boolean indicateurPersonneMorale;

    /**
     * Cet indicateur permet de d�terminer si cette infraction d�termin�e par sa NATINF est une r�cidive sp�ciale.
     */
    private Boolean indicateurNatinfEnRecidive;

    /** Cet attribut permet de conna�tre la date d'abrogation d'une infraction. */
    private Date dateAbrogation;

    /** nature infraction type */
    private NatureInfractionType natureInfractionType;

    private Set<InfractionRequete> requetes;

    /** liste des liens personne infraction */
    private Set<LienPersonneInfraction> liensPersonneInfraction = new LinkedHashSet<>();
    private Set<LienPersonneInfraction> liensPersonneInfractionRequalifies = new LinkedHashSet<>();

    /**
     * Liste des circonstances aggravantes (1 � 3) pour une infraction. La liste des circonstances aggravantes possibles
     * pour une Natinf est un attribut de la classe <code>NatureInfractionType</code>.
     */
    private Set<CirconstanceAggravante> circonstanceAggravantes = new LinkedHashSet<>();

    private Set<Drogue> drogues = new LinkedHashSet<>();

    /**
     * L'ensemble des �v�nements dont cette infraction est "infraction concern�e".
     */
    private Set<Evenement> evenementsSurInfraction;

    /**
     * modalit� de participation associ� � une infraction dont l'auteur est inconnu applicable � tous
     */

    private ModaliteParticipation modaliteParticipationAuteurInconnu;

    /**
     * infraction d'origine utilis� pour la disqual / requal : suite � la DE166 (infraction contre X), il �tait
     * impossible de retrouv� l'infration initiale
     */

    private Infraction infractionOrigine;

    /**
     * Ajout d'un attribut pour sp�cifier que la personne a �t� import�e par les EIA ou non
     */
    private ReferentielPV referentielPV;

    /**
     * Liste des groupes d'infractions
     */
    private Set<Groupe> groupeInfractions = new LinkedHashSet<>();

    private Boolean requisDisqualifie;

    /**
     * Infractions ayant remplac� celle-ci au cours d'une disqualification/requalification. Note: si cette collection
     * contient des �l�ments, alors l'objet Infraction courant doit �tre � l'�tat disqualifi�e.
     */
    private Set<Infraction> infractionsRemplacantes = new LinkedHashSet<>();

    /**
     * Qualification developpee pour Auteur inconnu
     */

    private QualificationDeveloppee qualificationDeveloppeeAuteurInconnu;

    /**
     * Les associations PersonneEvenement infraction pour PEM
     */
    private Set<PersonneEvenementInfraction> personnesEvenementInfraction = new LinkedHashSet<>();

    /**
     * Les associations Even�nement infraction pour PEM
     */
    private Set<EvenementInfraction> evenementsInfraction = new LinkedHashSet<>();

    /** liste des messages de reprise associ�s � l'infraction */
    private Set<MessageReprise> messageRepriseSet = new LinkedHashSet<>();

    /** liste des commentaires de reprise associ�s � l'infraction */
    private Set<CommentaireRepris> commentaireReprisSet = new LinkedHashSet<>();
    
    /**
     * Liste des doublons infractions
     */
    private Set<DoublonInfraction> doublons = new LinkedHashSet<>();

    /**
     * @return the requisDisqualifie
     */
    public Boolean getRequisDisqualifie() {
        return requisDisqualifie;
    }

    /**
     * @param requisDisqualifie
     *            the requisDisqualifie to set
     */
    public void setRequisDisqualifie(Boolean requisDisqualifie) {
        this.requisDisqualifie = requisDisqualifie;
    }

    /**
     * Indique l'affaire p�nale � laquelle cette infraction est rattach�e
     *
     * @return l'affaire � laquelle cette infraction est rattach�e
     */
    @Override
    public AffairePenale getAffairePenale() {
        if(AffairePenale.class.isInstance(affaire)){
            return (AffairePenale) affaire;
        }
        return null;
    }

    /**
     * Renseigne l'affaire p�nale � laquelle cette infraction est rattach�e
     *
     * @param affairePenale
     *            l'affaire p�nale � laquelle cette infraction est rattach�e
     */
    public void setAffairePenale(AffairePenale affairePenale) {
        affaire = affairePenale;
    }

    /**
     * Retourne l'alcool�mie dans les poumons
     *
     * @return l' Alcoolemie dans les poumons
     */
    public Alcoolemie getAlcoolemiePoumon() {
        return alcoolemiePoumon;
    }

    /**
     * Renseigne l'alcool�mie dans les poumons
     *
     * @param alcoolemiePoumon
     *            l'alcool�mie dans les poumons
     */
    public void setAlcoolemiePoumon(Alcoolemie alcoolemiePoumon) {
        this.alcoolemiePoumon = alcoolemiePoumon;
    }

    /**
     * Retourne l'alcool�mie dans le sang
     *
     * @return l'alcool�mie dans le sang
     */
    public Alcoolemie getAlcoolemieSang() {
        return alcoolemieSang;
    }

    /**
     * Renseigne l'alcool�mie dans le sang
     *
     * @param alcoolemieSang
     *            l'alcool�mie dans le sang
     */
    public void setAlcoolemieSang(Alcoolemie alcoolemieSang) {
        this.alcoolemieSang = alcoolemieSang;
    }

    /**
     * @return
     */
    public Set<CirconstanceAggravante> getCirconstanceAggravantes() {
        return circonstanceAggravantes;
    }

    /**
     * @param circonstanceAggravantes
     */
    public void setCirconstanceAggravantes(Set<CirconstanceAggravante> circonstanceAggravantes) {
        this.circonstanceAggravantes = circonstanceAggravantes;
    }

    /**
     * Indique la commune du lieu de l'infraction
     *
     * @return la commune du lieu de l'infraction
     */
    public Commune getCommune() {
        return commune;
    }

    /**
     * Renseigne la commune du lieu de l'infraction
     *
     * @param commune
     *            la commune du lieu de l'infraction
     */
    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    /**
     * Indique la date de d�but de l'infraction
     *
     * @return la date de d�but de l'infraction
     */
    public DateIncompleteAvecPrefixe getDateDebut() {
        return dateDebut;
    }

    /**
     * Renseigne la date d�but de l'infraction
     *
     * @param dateDebut
     *            la date de d�but de l'infraction
     */
    public void setDateDebut(DateIncompleteAvecPrefixe dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Indique la date de fin de l'infraction
     *
     * @return la date de fin de l'infraction
     */
    public DateIncompleteAvecPrefixe getDateFin() {
        return dateFin;
    }

    /**
     * Renseigne la date de fin de l'infraction
     *
     * @param dateFin
     *            la date de fin de l'infraction
     */
    public void setDateFin(DateIncompleteAvecPrefixe dateFin) {
        this.dateFin = dateFin;
    }

    public Set<fr.gouv.justice.cassiopee.invariant.infraction.model.Drogue> getDrogues() {
        return drogues;
    }

    public void setDrogues(Set<fr.gouv.justice.cassiopee.invariant.infraction.model.Drogue> drogues) {
        this.drogues = drogues;
    }

    /**
     * Indique l'�tat de l'infraction.
     *
     * @return l'�tat de l'infraction
     */
    public EtatInfraction getEtatCourant() {
        return etatCourant;
    }

    /**
     * Met � jour l'�tat courant
     *
     * @param etatCourant
     *            l'�tat de l'infraction
     */
    public void setEtatCourant(EtatInfraction etatCourant) {
        this.etatCourant = etatCourant;
    }

    /**
     * Indique l'�tat pr�cedent de l'infraction
     *
     * @return l'�tat pr�cedent de l'infraction
     */
    public EtatInfraction getEtatPrecedent() {
        return etatPrecedent;
    }

    /**
     * Met � jour l'�tat pr�c�dent de l'infraction
     *
     * @param etatPrecedent
     *            �tat pr�cedent de l'infraction
     */
    public void setEtatPrecedent(EtatInfraction etatPrecedent) {
        this.etatPrecedent = etatPrecedent;
    }

    /**
     * Retourne l'identifiant de l'objet Infraction
     *
     * @return l'identifiant de l'objet Infraction
     */
    public Long getId() {
        return id;
    }

    /**
     * Renseigne l'identifiant de l'objet Infraction
     *
     * @param id
     *            l'identifiant de l'objet Infraction
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Indique le lieu de l'infraction
     *
     * @return le lieu de l'infraction
     */
    public String getLibelleLieuInfraction() {
        return libelleLieuInfraction;
    }

    /**
     * Renseigne le lieu de l'infraction
     *
     * @param libelleLieuInfraction
     *            le lieu de l'infraction
     */
    public void setLibelleLieuInfraction(String libelleLieuInfraction) {
        this.libelleLieuInfraction = libelleLieuInfraction;
    }

    public Set<LienPersonneInfraction> getLiensPersonneInfraction() {
        return liensPersonneInfraction;
    }

    public void setLiensPersonneInfraction(
            Set<LienPersonneInfraction> liensPersonneInfraction) {
        this.liensPersonneInfraction = liensPersonneInfraction;
    }

    public Set<LienPersonneInfraction> getLiensPersonneInfractionRequalifies() {
        return liensPersonneInfractionRequalifies;
    }

    public void setLiensPersonneInfractionRequalifies(Set<LienPersonneInfraction> liensPersonneInfractionRequalifies) {
        this.liensPersonneInfractionRequalifies = liensPersonneInfractionRequalifies;
    }

    /** Indique le rang de l'infraction dans le dossier */
    @Override
    public Integer getRang() {
        return rang;
    }

    /**
     * Renseigne le rang de l'infraction dans le dossier
     *
     * @param rang
     *            le rang de l'infraction dans le dossier
     */
    public void setRang(Integer rang) {
        this.rang = rang;
    }

    public Set<InfractionRequete> getRequetes() {
        return requetes;
    }

    public void setRequetes(Set<InfractionRequete> requetes) {
        this.requetes = requetes;
    }

    /**
     * Indique le texte local qui d�crit l'infraction dans le cas d'une infraction � un texte local
     */
    public TexteLocal getTexteLocal() {
        return texteLocal;
    }

    /**
     * Renseigne le texte local qui d�crit l'infraction
     *
     * @param texteLocal
     *            le texte local
     */
    public void setTexteLocal(TexteLocal texteLocal) {
        this.texteLocal = texteLocal;
    }

    /**
     * Indique la version de la nature de l'infraction associ�e � la date de l'infraction
     */
    public VersionNatureInfraction getVersionNatureInfraction() {
        return versionNatureInfraction;
    }

    /**
     * Renseigne la version de la nature de l'infraction associ�e � la date de l'infraction
     *
     * @param versionNatureInfraction
     *            la version de la nature de l'infraction associ�e � la date de l'infraction
     */
    public void setVersionNatureInfraction(VersionNatureInfraction versionNatureInfraction) {
        this.versionNatureInfraction = versionNatureInfraction;
    }

    /**
     * Indique la vitesse dans le cas d'une infraction routi�re li�e � la vitesse
     */
    public Vitesse getVitesse() {
        return vitesse;
    }

    /**
     * Renseigne la vitesse dans le cas d'une infraction routi�re li�e � la vitesse
     *
     * @param vitesse
     *            la vitesee dans le cas d'une infraction routi�re
     */
    public void setVitesse(Vitesse vitesse) {
        this.vitesse = vitesse;
    }

    public Amnistie getAmnistie() {
        return amnistie;
    }

    public void setAmnistie(Amnistie amnistie) {
        this.amnistie = amnistie;
    }

    public Date getDateAbrogation() {
        return dateAbrogation;
    }

    public void setDateAbrogation(Date dateAbrogation) {
        this.dateAbrogation = dateAbrogation;
    }

    public boolean isNatinfEnRecidive() {
        if (indicateurNatinfEnRecidive == null)
            return false;
        return indicateurNatinfEnRecidive.booleanValue();
    }

    public boolean isPersonneMorale() {
        if (indicateurPersonneMorale == null)
            return false;
        return indicateurPersonneMorale.booleanValue();
    }

    public Boolean getIndicateurNatinfEnRecidive() {
        return indicateurNatinfEnRecidive;
    }

    public void setIndicateurNatinfEnRecidive(Boolean indicateurNatinfEnRecidive) {
        this.indicateurNatinfEnRecidive = indicateurNatinfEnRecidive;
    }

    public Boolean getIndicateurPersonneMorale() {
        return indicateurPersonneMorale;
    }

    public void setIndicateurPersonneMorale(Boolean indicateurPersonneMorale) {
        this.indicateurPersonneMorale = indicateurPersonneMorale;
    }

    /**
     * initialise les dates de d�but et de fin et le lieu avec les donn�es de l'affaire
     */
    @RegleDeGestion({ RG.INFI001, RG.INFI002 })
    public void initializeDatesEtLieuAvecDonneesAffaire() {
        this.setDateDebut(this.getAffairePenale().getFaitsDateDebut());
        this.setDateFin(this.getAffairePenale().getFaitsDateFin());
        this.setLibelleLieuInfraction(this.getAffairePenale().getFaitsLieu());
        this.setCommune(this.getAffairePenale().getFaitsCommune());
    }

    /**
     * Retourne la date de l'infraction pour effectuer un calcul. La date est la date de fin si elle est compl�te ou la
     * date de d�but si la date de fin est incompl�te
     *
     * @return la date de l'infraction pour effectuer un calcul.
     */
    public Date getDateInfraction() {
        return computeDateRechercheVersionNatinf(getDateDebut(), getDateFin());
    }

    public Date getDateFaits() {
        return computeDateRechercheVersionNatinf(getAffairePenale().getFaitsDateDebut(), getAffairePenale().getFaitsDateFin());
    }

    /**
     * Indique la nature infraction type
     *
     * @return la nature infraction type
     */
    public NatureInfractionType getNatureInfractionType() {
        return natureInfractionType;
    }

    /**
     * Renseigne la nature infraction type
     *
     * @param natureInfractionType
     *            � renseigner
     */
    public void setNatureInfractionType(NatureInfractionType natureInfractionType) {
        this.natureInfractionType = natureInfractionType;
    }

    /**
     * Calcul de la date � prendre en compte pour la recherche de version
     *
     * @param dateDebut
     *            date de d�but
     * @param dateFin
     *            date de fin
     * @return la date � prendre en compte pour la recherche de version
     */
    public static Date computeDateRechercheVersionNatinf(DateIncomplete dateDebut, DateIncomplete dateFin) {
        Date date = null;
        if (dateFin != null && dateFin.isComplete()) {
            // date de fin renseign�e et complete
            date = dateFin.getAverageDate();
        } else if (dateDebut != null) {
            // date d�but
            date = dateDebut.getAverageDate();
        }
        return date;
    }

    /**
     * @return Returns the evenementsSurInfraction.
     */
    public Set<Evenement> getEvenementsSurInfraction() {
        return evenementsSurInfraction;
    }

    /**
     * @param evenementsSurInfraction
     *            The evenementsSurInfraction to set.
     */
    public void setEvenementsSurInfraction(Set<Evenement> evenementsSurInfraction) {
        this.evenementsSurInfraction = evenementsSurInfraction;
    }

    /**
     * retourne les associations PersonneEvenement infraction
     *
     * @return les associations PersonneEvenement infraction
     */
    public Set<PersonneEvenementInfraction> getPersonnesEvenementInfraction() {
        return personnesEvenementInfraction;
    }

    /**
     * renseigne les associations PersonneEvenement infraction
     *
     * @param personnesEvenementInfraction
     *            les associations PersonneEvenement
     */
    public void setPersonnesEvenementInfraction(Set<PersonneEvenementInfraction> personnesEvenementInfraction) {
        this.personnesEvenementInfraction = personnesEvenementInfraction;
    }

    /**
     * retourne les associations PersonneEvenement infraction
     *
     * @return les associations PersonneEvenement infraction
     */
    public Set<EvenementInfraction> getEvenementsInfraction() {
        return evenementsInfraction;
    }

    /**
     * renseigne les associations PersonneEvenement infraction
     *
     * @param evenementsInfraction
     *            les associations PersonneEvenement infraction
     */
    public void setEvenementsInfraction(Set<EvenementInfraction> evenementsInfraction) {
        this.evenementsInfraction = evenementsInfraction;
    }

    /**
     * retourne la liste des commentaires de reprise associ�s � l'infraction
     *
     * @return la liste des commentaires de reprise associ�s � l'infraction
     */
    public Set<CommentaireRepris> getCommentaireReprisSet() {
        return commentaireReprisSet;
    }

    /**
     * renseigne la liste des commentaires de reprise associ�s � l'infraction
     *
     * @param commentaireReprisSet
     *            la liste des commentaires de reprise associ�s � l'infraction
     */
    public void setCommentaireReprisSet(Set<CommentaireRepris> commentaireReprisSet) {
        this.commentaireReprisSet = commentaireReprisSet;
    }

    /**
     * retourne la liste des messages de reprise associ�s � l'infraction
     *
     * @return la liste des messages de reprise associ�s � l'infraction
     */
    public Set<MessageReprise> getMessageRepriseSet() {
        return messageRepriseSet;
    }

    /**
     * renseigne la liste des messages de reprise associ�s � l'infraction
     *
     * @param messageRepriseSet
     *            la liste des messages de reprise associ�s � l'infraction
     */
    public void setMessageRepriseSet(Set<MessageReprise> messageRepriseSet) {
        this.messageRepriseSet = messageRepriseSet;
    }

    /**
     * Retourne une copie de cette infraction, non enregistr�e en base. L'affaire est la m�me, les �v�nements sur
     * infraction sont vid�s ainsi que les liens personne-infractions.
     *
     * @return une copie de cette Infraction.
     * @throws CloneNotSupportedException
     */
    @Override
    @RegleDeGestion(RG.INFM074)
    public Infraction clone() throws CloneNotSupportedException {
        Infraction clone = (Infraction) super.clone();

        clone.setAffaire(this.affaire);
        if (this.alcoolemiePoumon != null) {
            clone.setAlcoolemiePoumon(this.alcoolemiePoumon.clone());
        }
        if (this.alcoolemieSang != null) {
            clone.setAlcoolemieSang(this.alcoolemieSang.clone());
        }
        if (this.amnistie != null) {
            clone.setAmnistie(this.amnistie.clone());
        }
        if (this.circonstanceAggravantes != null) {
            clone.setCirconstanceAggravantes(new HashSet<CirconstanceAggravante>());
            for (CirconstanceAggravante circonstanceAggravante : this.getCirconstanceAggravantes()) {
                clone.getCirconstanceAggravantes().add(circonstanceAggravante);
            }
        }
        // Cr�er une nouvelle instance de groupeInfraction
        clone.setGroupeInfractions(new HashSet<Groupe>());
        
        clone.setDoublons(new HashSet<DoublonInfraction>());

        // Commune : pas de probl�me

        if (this.dateAbrogation != null) {
            clone.setDateAbrogation((Date) this.dateAbrogation.clone());
        }

        if (this.dateDebut != null) {
            clone.setDateDebut(this.dateDebut.clone());
        }

        if (this.dateFin != null) {
            clone.setDateFin(this.dateFin.clone());
        }

        if (this.drogues != null) {
            clone.setDrogues(new HashSet<Drogue>());
            for (Drogue drogue : this.drogues) {
                Drogue cloneDrogue = drogue.clone();
                cloneDrogue.setInfraction(clone);
                clone.getDrogues().add(cloneDrogue);
            }

        }

        // Etat courant, Etat pr�c�dent: sont des codifications donc pas de
        // probl�me

        // Evenements sur infraction: vider
        clone.setEvenementsSurInfraction(new HashSet<Evenement>());

        // Ne pas conserver l'Id surtout !
        clone.setId(null);

        // Liens personne-infraction: vider
        clone.setLiensPersonneInfraction(new HashSet<LienPersonneInfraction>());
        clone.setLiensPersonneInfractionRequalifies(new HashSet<LienPersonneInfraction>());

        // De m�me on vide les associations personne-evt-infraction
        clone.setPersonnesEvenementInfraction(new HashSet<PersonneEvenementInfraction>());
        clone.setEvenementsInfraction(new HashSet<EvenementInfraction>());

        // pas de recopie des informations de reprise
        clone.setMessageRepriseSet(new HashSet<MessageReprise>());
        clone.setCommentaireReprisSet(new HashSet<CommentaireRepris>());

        // Modalit� participation : codification, pas de probl�me

        // Nature infraction: OK r�f�rence partag�e

        // Pr�diction du rang
        // TODO probl�me potentiel !!
        clone.setRang(getAffairePenale().getRangProchaineInfraction());

        if (this.requetes != null)
            clone.setRequetes(new HashSet<InfractionRequete>());
        for (InfractionRequete requete : this.requetes) {
            // XXX : PYD permet de setter correctement la requete sur infraction
            // utilit� de cloner cette partie ??
            InfractionRequete clone2 = requete.clone();
            clone.getRequetes().add(clone2);
            clone2.setInfraction(clone);
        }

        if (this.texteLocal != null) {
            clone.setTexteLocal(this.texteLocal.clone());
        }

        // Version nature infraction: OK r�f�rence partag�e

        if (this.vitesse != null) {
            clone.setVitesse(this.vitesse.clone());
        }

        // infractions rempla�antes : vider
        if (this.infractionsRemplacantes != null) {
            clone.setInfractionsRemplacantes(new HashSet<Infraction>());
        }

        return clone;
    }

    /** Retourne vrai si l'infraction est un crime */
    public boolean isCrime() {
    	//si infraction civile
    	   	if (getVersionNatureInfraction().getTypePrimaire().getMnemoTypeInfraction() != null) {
    	         return getVersionNatureInfraction().getTypePrimaire().getMnemoTypeInfraction().getCode()
    	                 .equals(MnemoTypeInfractionEnum.CRIME.getCode());
    	  	}else
    	    		return false;
    }

    /** Retourne vrai si l'infraction est un d�lit */
    public boolean isDelit() {
    	//si infraction civile
    	if (getVersionNatureInfraction().getTypePrimaire().getMnemoTypeInfraction() != null) {
    	  return getVersionNatureInfraction().getTypePrimaire().getMnemoTypeInfraction().getCode()
    	                .equals(MnemoTypeInfractionEnum.DELIT.getCode());
    	}else
    	return false;
    }

    /** Retourne vrai si l'infraction est un d�lit */
    public boolean isContravention() {
    	//si infraction civile
    	if (getVersionNatureInfraction().getTypePrimaire().getMnemoTypeInfraction() != null) {
    		return getVersionNatureInfraction().getTypePrimaire().getMnemoTypeInfraction().getCode()
    	          .equals(MnemoTypeInfractionEnum.CONTRAVENTION.getCode());
    	}else
    	return false;
    }

    // GETTER & SETTER
    public Infraction getInfractionOrigine() {
        return infractionOrigine;
    }

    public void setInfractionOrigine(Infraction infractionOrigine) {
        this.infractionOrigine = infractionOrigine;
    }

    public ModaliteParticipation getModaliteParticipationAuteurInconnu() {
        return modaliteParticipationAuteurInconnu;
    }

    public void setModaliteParticipationAuteurInconnu(ModaliteParticipation modaliteParticipationAuteurInconnu) {
        this.modaliteParticipationAuteurInconnu = modaliteParticipationAuteurInconnu;
    }

    public QualificationDeveloppee getQualificationDeveloppeeAuteurInconnu() {
        return qualificationDeveloppeeAuteurInconnu;
    }

    public void setQualificationDeveloppeeAuteurInconnu(QualificationDeveloppee qualificationDeveloppeeAuteurInconnu) {
        this.qualificationDeveloppeeAuteurInconnu = qualificationDeveloppeeAuteurInconnu;
    }

    public Set<Infraction> getInfractionsRemplacantes() {
        return infractionsRemplacantes;
    }

    public void setInfractionsRemplacantes(Set<Infraction> infractionsRemplacantes) {
        this.infractionsRemplacantes = infractionsRemplacantes;
    }

    /**
     * @return
     */
    public Set<Groupe> getGroupeInfractions() {
        return groupeInfractions;
    }

    /**
     * @param groupeInfractions
     */
    public void setGroupeInfractions(Set<Groupe> groupeInfractions) {
        this.groupeInfractions = groupeInfractions;
    }

    @Override
    public int compareTo(Infraction o) {
        return this.getRang() - o.getRang();
    }

    public ReferentielPV getReferentielPV() {
        return referentielPV;
    }

    public void setReferentielPV(ReferentielPV referentielPV) {
        this.referentielPV = referentielPV;
    }

    @Override
    public Long getNumeroNatinf() {
       return natureInfractionType == null ? null : natureInfractionType.getNumeroNatinf();
    }

    @Override
    public String getQualificationSimplifiee() {
        return versionNatureInfraction == null ? null : versionNatureInfraction.getQualificationSimplifiee();
    }
	@Override
	public String getCodeType() {
		if (versionNatureInfraction != null && versionNatureInfraction.getMnemoTypeInfraction() != null
				&& versionNatureInfraction.getMnemoTypeInfraction().getCode() != null)
			return versionNatureInfraction.getMnemoTypeInfraction().getCode();
		else
			return "";
	}

    @Override
    public boolean isEnCours() {
        return etatCourant.getCode().equals(EtatInfractionEnum.ENCOURS.getCode());
    }

    @Override
    public NatureAffaire getNatureAffaire() {
        return natureInfractionType.getNatureAffaire();
    }

    public Affaire getAffaire() {
        return affaire;
    }

    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
    }

    /**
     * Retourne <code>true</code> quand l'infraction a �t� disqualifi�e lors d'un �v�nement {@link EvenementTypeEnum#DISQUAL_REQUAL}.
     *
     * @return
     */
    public boolean isDisqualifiee() {
        return EtatInfractionEnum.DISQUALIFIEE.getCode().equals(etatCourant.getCode())
                && etatPrecedent != null
                && EtatInfractionEnum.ENCOURS.getCode().equals(etatPrecedent.getCode());
    }

    public Set<DoublonInfraction> getDoublons() {
        return doublons;
    }

    public void setDoublons(Set<DoublonInfraction> doublons) {
        this.doublons = doublons;
    }
    
    @Override
    public boolean hasDoublonEligibleTaj() {
        if (CollectionUtils.isNotEmpty(doublons)) {
            return doublons.stream().anyMatch(doublon -> doublon.getReferentielPV().isEligibleTAJ());
        }
        return false;
    }
}