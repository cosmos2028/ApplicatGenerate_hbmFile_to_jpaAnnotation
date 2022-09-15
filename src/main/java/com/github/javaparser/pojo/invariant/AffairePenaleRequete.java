package fr.gouv.justice.cassiopee.invariant.affaire.model;

import org.apache.commons.lang.StringUtils;

import fr.gouv.justice.cassiopee.common.exception.TechnicalException;
import fr.gouv.justice.cassiopee.common.util.DateIncomplete;
import fr.gouv.justice.cassiopee.commons.codification.service.enumeration.LienJuridiqueEnum;
import fr.gouv.justice.cassiopee.invariant.affaire.service.enumeration.TypeAffaireEnum;
import fr.gouv.justice.cassiopee.invariant.personne.model.ComplementAuteurOuMineurSignale;
import fr.gouv.justice.cassiopee.invariant.personne.model.LienPersonnes;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.invariant.personne.model.PersonneIndeterminee;
import fr.gouv.justice.cassiopee.invariant.personne.model.PersonneMajeure;
import fr.gouv.justice.cassiopee.invariant.personne.model.PersonneMineure;
import fr.gouv.justice.cassiopee.invariant.personne.model.PersonneMorale;
import fr.gouv.justice.cassiopee.invariant.personne.model.PersonnePhysique;
import fr.gouv.justice.cassiopee.referentiel.codification.model.Civilite;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Commune;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Pays;

/**
 * Une affaire p�nale peut �tre cr��e par une requ�te. On parle alors d'affaire p�nale requ�te. Il s'agit des requ�tes
 * relatives au casier judiciaire, requ�tes en reconnaissance d'identit�, requ�tes relatives au rel�vement, requ�tes
 * relatives � l'ex�cution ou l'application des peines. La requ�te en rectification d'erreur mat�rielle ne cr�e pas
 * affaire : �v�nement dans l'affaire d'origine uniquement. Dans le cas des affaires requ�te en reconnaissance
 * d'identit�, il faut stocker, si elle est connue, l'identit� r�elle de l'auteur. Cet auteur peut �tre une personne
 * physique ou une personne morale.
 */
public class AffairePenaleRequete extends AffairePenale {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 5028215073756087942L;

    /**
     * Civilit� de la l'identit� r�elle de l'auteur. Civilit� de l'auteur physique ou du repr�sentant l�gal das le cas
     * d'une personne morale auteur.
     */
    private Civilite civilite;

    /**
     * Nom de naissance correspondant � l'identit� r�elle de l'auteur (personne physique ou repr�sentant l�gal d'une
     * personne morale).
     */
    private String nomNaissance;

    /**
     * Nom d'usage correspondant � l'identit� r�elle de l'auteur (personne physique uniquement).
     */
    private String nomUsage;

    /**
     * Pr�nom correspondant � l'identit� r�elle de l'auteur (personne physique ou repr�sentat l�gal de la personne
     * morale auteur).
     */
    private String prenom;

    /**
     * Date de naissance r�elle du v�ritable auteur (personne physique).
     */
    private DateIncomplete dateNaissance;

    /**
     * Cet indicateur pr�cise le caract�re mineure de l'identit� r�elle de l'auteur (personne physique).
     */
    private Boolean flagMineur;

    /**
     * pays de naissance correspondant � l'identit� r�elle de l'auteur (personne physique).
     */
    private Pays paysNaissance;

    /**
     * Commune de naissance (Francaise) correspondant � l'identit� r�elle de l'auteur (personne physique).
     */
    private Commune communeNaissance;

    /**
     * Libell� de la commune de naissance pour un pays �tranger correspondant � l'identit� r�elle de l'auteur (personne
     * physique).
     */
    private String libelleCommuneNaissanceEtrangere;

    /**
     * Raison sociale correspondant � l'identit� r�elle de l'auteur (personne morale uniquement).
     */
    private String codeRaisonSociale;

    /**
     * SIREN ou SIRET correspondant � l'identit� r�elle de l'auteur (personne morale uniquement).
     */
    private String sirenSiret;

    /**
     * Lorsque l'identit� r�elle de l'auteur n'a pas �t� retrouv�e, cet indicateur sert � pr�ciser que l'identit� de
     * l'auteur dans l'affaire d'origine est de type "X se disant". Valable pour les requ�tes en reconnaissance
     * d'identit� portant sur une personne physique uniquement.
     */
    private Boolean flagSeDisantX;

    public String getCodeRaisonSociale() {
        return codeRaisonSociale;
    }

    public void setCodeRaisonSociale(String penaleReqCodeRaisonSociale) {
        this.codeRaisonSociale = penaleReqCodeRaisonSociale;
    }

    public DateIncomplete getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(DateIncomplete penaleReqDateNaissance) {
        this.dateNaissance = penaleReqDateNaissance;
    }

    public String getNomNaissance() {
        return nomNaissance;
    }

    public void setNomNaissance(String penaleReqNomNaissance) {
        this.nomNaissance = penaleReqNomNaissance;
    }

    public String getNomUsage() {
        return nomUsage;
    }

    public void setNomUsage(String penaleReqNomUsage) {
        this.nomUsage = penaleReqNomUsage;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String penaleReqPrenom) {
        this.prenom = penaleReqPrenom;
    }

    public Civilite getCivilite() {
        return civilite;
    }

    public void setCivilite(Civilite penaleRequeteCivilite) {
        this.civilite = penaleRequeteCivilite;
    }

    /**
     * @see fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire#getTypeAffaire()
     */
    @Override
    public TypeAffaireEnum getTypeAffaire() {
        return TypeAffaireEnum.PENALE_REQUETE;
    }

    /**
     * @return Returns the sirenSiret.
     */
    public String getSirenSiret() {
        return sirenSiret;
    }

    /**
     * @param sirenSiret
     *            The sirenSiret to set.
     */
    public void setSirenSiret(String sirenSiret) {
        this.sirenSiret = sirenSiret;
    }

    /**
     * @return Returns the communeNaissance.
     */
    public Commune getCommuneNaissance() {
        return communeNaissance;
    }

    /**
     * @param communeNaissance
     *            The communeNaissance to set.
     */
    public void setCommuneNaissance(Commune communeNaissance) {
        this.communeNaissance = communeNaissance;
    }

    /**
     * @return Returns the paysNaissance.
     */
    public Pays getPaysNaissance() {
        return paysNaissance;
    }

    /**
     * @param paysNaissance
     *            The paysNaissance to set.
     */
    public void setPaysNaissance(Pays paysNaissance) {
        this.paysNaissance = paysNaissance;
    }

    /**
     * @return Returns the flagMineur.
     */
    public Boolean getFlagMineur() {
        return flagMineur;
    }

    /**
     * @param flagMineur
     *            The flagMineur to set.
     */
    public void setFlagMineur(Boolean flagMineur) {
        this.flagMineur = flagMineur;
    }

    /**
     * @return Returns the flagSeDisantX.
     */
    public Boolean getFlagSeDisantX() {
        return flagSeDisantX;
    }

    /**
     * @param flagSeDisantX
     *            The flagSeDisantX to set.
     */
    public void setFlagSeDisantX(Boolean flagSeDisantX) {
        this.flagSeDisantX = flagSeDisantX;
    }

    /**
     * @return Returns the libelleCommuneNaissanceEtrangere.
     */
    public String getLibelleCommuneNaissanceEtrangere() {
        return libelleCommuneNaissanceEtrangere;
    }

    /**
     * @param libelleCommuneNaissanceEtrangere
     *            The libelleCommuneNaissanceEtrangere to set.
     */
    public void setLibelleCommuneNaissanceEtrangere(String libelleCommuneNaissanceEtrangere) {
        this.libelleCommuneNaissanceEtrangere = libelleCommuneNaissanceEtrangere;
    }

    /**
     * Retourne une instance de Personne, contenant les informations mises � jour, donc non erronn�es. Cette instance
     * n'est pas reli�e � l'objet Affaire, il ne doit pas �tre persist�.
     *
     * @return la personne rectifi�e
     */
    public Personne getPersonneRectifiee() {
        // il n'existe qu'une personne dans une affaire p�nale requ�te
        Personne personneAvantRectification = getPersonnes().iterator().next();

        Personne personneRectifiee;
        if (personneAvantRectification.isPersonneMineure()) {
            personneRectifiee = new PersonneMineure();
        } else if (personneAvantRectification.isPersonneMajeure()) {
            personneRectifiee = new PersonneMajeure();
        } else if (personneAvantRectification.isPersonneIndeterminee()) {
            personneRectifiee = new PersonneIndeterminee();
        } else if (personneAvantRectification.isPersonneMorale()) {
            personneRectifiee = new PersonneMorale();
        } else
            throw new TechnicalException("personne de type non attendu");

        if (personneAvantRectification.isPersonnePhysique()) {
            PersonnePhysique personnePhysiqueAvantRectification = (PersonnePhysique) personneAvantRectification;
            PersonnePhysique personnePhysiqueRectifiee = (PersonnePhysique) personneRectifiee;

            // ajout attributs physiques
            addPP(personnePhysiqueAvantRectification, personnePhysiqueRectifiee);

        } else {
            PersonneMorale personneMoraleAvantRectification = (PersonneMorale) personneAvantRectification;
            PersonneMorale personneMoraleRectifiee = (PersonneMorale) personneRectifiee;

            addPM(personneMoraleAvantRectification, personneMoraleRectifiee);

        }

        return personneRectifiee;
    }

    /**
     * @param personneMoraleAvantRectification
     * @param personneMoraleRectifiee
     */
    private void addPM(PersonneMorale personneMoraleAvantRectification, PersonneMorale personneMoraleRectifiee) {

        // recuperation du RL si RL physique
        LienPersonnes lienAvant = getPersonneRL(personneMoraleAvantRectification);
        if (lienAvant != null && lienAvant.getPersonneLieeOuJeune().isPersonnePhysique()) {

            PersonnePhysique personnePhysiqueAvantRectificationRl = (PersonnePhysique) lienAvant
                    .getPersonneLieeOuJeune();

            // cr�ation RL rectifi�e
            Personne personneRectifieeRL;

            if (personnePhysiqueAvantRectificationRl.isPersonneMineure()) {
                personneRectifieeRL = new PersonneMineure();
            } else if (personnePhysiqueAvantRectificationRl.isPersonneMajeure()) {
                personneRectifieeRL = new PersonneMajeure();
            } else if (personnePhysiqueAvantRectificationRl.isPersonneIndeterminee()) {
                personneRectifieeRL = new PersonneIndeterminee();

            } else
                throw new TechnicalException("personne de type non attendu");

            PersonnePhysique personnePhysiqueRectifieeRL = (PersonnePhysique) personneRectifieeRL;

            // ajout attributs physiques pour le RL
            addPP(personnePhysiqueAvantRectificationRl, personnePhysiqueRectifieeRL);

            LienPersonnes newLien = new LienPersonnes();

            newLien.setPersonneRolePremierOuLiee(personneMoraleRectifiee);
            // Attention : ne pas persister le code suivant !

            newLien.setPersonneLieeOuJeune(personnePhysiqueRectifieeRL);
            newLien.setDateDuLien(lienAvant.getDateDuLien());
            newLien.setLienJuridique(lienAvant.getLienJuridique());
            personneMoraleRectifiee.getPersonnesLieesOuJeunesLiees().add(newLien);

        }

        // raison sociale
        personneMoraleRectifiee
                .setRaisonSociale(StringUtils.isBlank(getCodeRaisonSociale()) ? personneMoraleAvantRectification
                        .getRaisonSociale() : getCodeRaisonSociale());

        // siret
        personneMoraleRectifiee
                .setNumeroSirenSiret(StringUtils.isBlank(getSirenSiret()) ? personneMoraleAvantRectification
                        .getNumeroSirenSiret() : getSirenSiret());

        // peu pas etre modifi�
        // sigle
        if (StringUtils.isNotBlank(personneMoraleAvantRectification.getSigle())) {

            personneMoraleRectifiee.setSigle(personneMoraleAvantRectification.getSigle());
        }

        // enseigne
        if (StringUtils.isNotBlank(personneMoraleAvantRectification.getEnseigne())) {

            personneMoraleRectifiee.setEnseigne(personneMoraleAvantRectification.getEnseigne());
        }

        // forme
        if (personneMoraleAvantRectification.getFormeJuridique() != null) {

            personneMoraleRectifiee.setFormeJuridique(personneMoraleAvantRectification.getFormeJuridique());
        }

        // sigle
        if (StringUtils.isNotBlank(personneMoraleAvantRectification.getNumeroRcs())) {

            personneMoraleRectifiee.setNumeroRcs(personneMoraleAvantRectification.getNumeroRcs());
        }

        // Sexe
        if (personneMoraleAvantRectification.getSexeCourrier() != null) {

            personneMoraleRectifiee.setSexeCourrier(personneMoraleAvantRectification.getSexeCourrier());
        }

        // EtatLogique
        if (personneMoraleAvantRectification.getEtatLogique() != null) {

            personneMoraleRectifiee.setEtatLogique(personneMoraleAvantRectification.getEtatLogique());
        }

        // rolePersonne
        if (personneMoraleAvantRectification.getRolePersonne() != null) {

            personneMoraleRectifiee.setRolePersonne(personneMoraleAvantRectification.getRolePersonne());
        }

    }

    /**
     * @param personnePhysiqueAvantRectification
     * @param personnePhysiqueRectifiee
     */
    private void addPP(PersonnePhysique personnePhysiqueAvantRectification, PersonnePhysique personnePhysiqueRectifiee) {

        // EtatLogique
        if (personnePhysiqueAvantRectification.getEtatLogique() != null) {

            personnePhysiqueRectifiee.setEtatLogique(personnePhysiqueAvantRectification.getEtatLogique());
        }

        // rolePersonne
        if (personnePhysiqueAvantRectification.getRolePersonne() != null) {

            personnePhysiqueRectifiee.setRolePersonne(personnePhysiqueAvantRectification.getRolePersonne());
        }

        // nonNaissance
        personnePhysiqueRectifiee
                .setNomNaissance(StringUtils.isBlank(getNomNaissance()) ? personnePhysiqueAvantRectification
                        .getNomNaissance() : getNomNaissance());
        // nonUsage
        personnePhysiqueRectifiee.setNomUsage(StringUtils.isBlank(getNomUsage()) ? personnePhysiqueAvantRectification
                .getNomUsage() : getNomUsage());

        // prenom
        personnePhysiqueRectifiee.setPrenom(StringUtils.isBlank(getPrenom()) ? personnePhysiqueAvantRectification
                .getPrenom() : getPrenom());
        // civilite
        personnePhysiqueRectifiee.setCivilite((getCivilite() != null) ? getCivilite()
                : personnePhysiqueAvantRectification.getCivilite());

        // dateNaissance
        personnePhysiqueRectifiee.setDateNaissance((getDateNaissance() != null) ? getDateNaissance()
                : personnePhysiqueAvantRectification.getDateNaissance());

        // paysNaissance
        personnePhysiqueRectifiee.setPaysNaissance((getPaysNaissance() != null) ? getPaysNaissance()
                : personnePhysiqueAvantRectification.getPaysNaissance());

        // setCommuneNaissanceFrancaise
        personnePhysiqueRectifiee.setCommuneNaissanceFrancaise((getCommuneNaissance() != null) ? getCommuneNaissance()
                : personnePhysiqueAvantRectification.getCommuneNaissanceFrancaise());

        // peu pas etre modifi�
        // complement sauf XseDisant
        if (personnePhysiqueAvantRectification.getComplementAuteurOuMineurSignale() != null) {

            if (getFlagSeDisantX() != null && getFlagSeDisantX()) {
                ComplementAuteurOuMineurSignale complement = new ComplementAuteurOuMineurSignale();

                complement.setSeDisantX(true);
                // prenom2
                if (StringUtils.isNotBlank(personnePhysiqueAvantRectification.getComplementAuteurOuMineurSignale()
                        .getPrenom2())) {

                    complement.setPrenom2(personnePhysiqueAvantRectification.getComplementAuteurOuMineurSignale()
                            .getPrenom2());
                }
                // prenom3
                if (StringUtils.isNotBlank(personnePhysiqueAvantRectification.getComplementAuteurOuMineurSignale()
                        .getPrenom3())) {

                    complement.setPrenom3(personnePhysiqueAvantRectification.getComplementAuteurOuMineurSignale()
                            .getPrenom3());
                }
                // nom pere
                if (StringUtils.isNotBlank(personnePhysiqueAvantRectification.getComplementAuteurOuMineurSignale()
                        .getNomPere())) {

                    complement.setNomPere(personnePhysiqueAvantRectification.getComplementAuteurOuMineurSignale()
                            .getNomPere());
                }

                // prenom pere
                if (StringUtils.isNotBlank(personnePhysiqueAvantRectification.getComplementAuteurOuMineurSignale()
                        .getPrenomsPere())) {

                    complement.setPrenomsPere(personnePhysiqueAvantRectification.getComplementAuteurOuMineurSignale()
                            .getPrenomsPere());
                }

                // nom mere
                if (StringUtils.isNotBlank(personnePhysiqueAvantRectification.getComplementAuteurOuMineurSignale()
                        .getNomMere())) {

                    complement.setNomMere(personnePhysiqueAvantRectification.getComplementAuteurOuMineurSignale()
                            .getNomMere());
                }

                // prenom mere
                if (StringUtils.isNotBlank(personnePhysiqueAvantRectification.getComplementAuteurOuMineurSignale()
                        .getPrenomsMere())) {

                    complement.setPrenomsMere(personnePhysiqueAvantRectification.getComplementAuteurOuMineurSignale()
                            .getPrenomsMere());
                }

                // sans domicile
                if (personnePhysiqueAvantRectification.getComplementAuteurOuMineurSignale().getSansDomicile() != null) {

                    complement.setSansDomicile(personnePhysiqueAvantRectification.getComplementAuteurOuMineurSignale()
                            .getSansDomicile());
                }

                // situation sociale
                if (personnePhysiqueAvantRectification.getComplementAuteurOuMineurSignale().getSituationSociale() != null) {

                    complement.setSituationSociale(personnePhysiqueAvantRectification
                            .getComplementAuteurOuMineurSignale().getSituationSociale());
                }

                personnePhysiqueRectifiee.setComplementAuteurOuMineurSignale(complement);

            }

            else {
                personnePhysiqueRectifiee.setComplementAuteurOuMineurSignale(personnePhysiqueAvantRectification
                        .getComplementAuteurOuMineurSignale());

            }
        }

        else if (getFlagSeDisantX() != null && getFlagSeDisantX()) {
            ComplementAuteurOuMineurSignale complement2 = new ComplementAuteurOuMineurSignale();

            complement2.setSeDisantX(true);
            personnePhysiqueRectifiee.setComplementAuteurOuMineurSignale(complement2);
        }

        // role
        personnePhysiqueRectifiee.setRolePersonne(personnePhysiqueAvantRectification.getRolePersonne());

        // situationFamille
        if (personnePhysiqueAvantRectification.getSituationFamille() != null) {
            personnePhysiqueRectifiee.setSituationFamille(personnePhysiqueAvantRectification.getSituationFamille());
        }

        // profession
        if (personnePhysiqueAvantRectification.getProfession() != null) {
            personnePhysiqueRectifiee.setProfession(personnePhysiqueAvantRectification.getProfession());

        }

        // nationalite1
        if (personnePhysiqueAvantRectification.getNationalite1() != null) {
            personnePhysiqueRectifiee.setNationalite1(personnePhysiqueAvantRectification.getNationalite1());

        }
        // nationalite2
        if (personnePhysiqueAvantRectification.getNationalite2() != null) {
            personnePhysiqueRectifiee.setNationalite1(personnePhysiqueAvantRectification.getNationalite2());

        }

    }

    private LienPersonnes getPersonneRL(PersonneMorale personneMorale) {
        for (LienPersonnes lien : personneMorale.getPersonnesLieesOuJeunesLieesExistantes()) {
            if (lien.getLienJuridique().getCode().equals(LienJuridiqueEnum.REPRESENTANT_LEGAL.getCode())) {
                return lien;
            }

        }
        return null;

    }
}
