package fr.gouv.justice.cassiopee.invariant.affaire.model;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.affaire.service.enumeration.TypeAffaireEnum;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.invariant.personne.service.EtatPersonneAffaireEnum;
import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.RolePersonneEnum;
import fr.gouv.justice.cassiopee.processus.parquet.model.IdentificationParquet;

/**
 * Une affaire pénale générale n'est pas une affaire pénale créée par requête.
 */
public class AffairePenaleGenerale extends AffairePenale {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -1086554955532103232L;

    /**
     * Cet attribut indique s'il s'agit d'une affaire Traitement Temps Réel. Il est non modifiable et calculé
     * automatique par le système en fonction de la règle de gestion suivante: il est valorisé à OUI si, pour cette
     * affaire avec auteur connu, il existe un événement d?orientation dans les 3 jours suivant les faits. (Si la date
     * des faits est incomplète prendre la date d'infraction de la 1ère infraction).
     */
    private Boolean indicTtr;

    /**
     * Date de création du premier auteur sur l'affaire. Cette date est utilisée pour mettre à jour l'indicateur
     * "auteur inconnu". Si l'auteur est créé plus de 72 heures après la création de l'affaire, l'indicateur est
     * positionné à VRAI.
     */
    private Date dateCreationPremierAuteur;

    /**
     * Indicateur auteur inconnu
     */
    private Integer indicateurAuteurInconnu;

    /**
     * @return the indicateurAuteurInconnu
     */
    public Integer getIndicateurAuteurInconnu() {
        return indicateurAuteurInconnu;
    }

    /**
     * @param indicateurAuteurInconnu
     *            the indicateurAuteurInconnu to set
     */
    public void setIndicateurAuteurInconnu(Integer indicateurAuteurInconnu) {
        this.indicateurAuteurInconnu = indicateurAuteurInconnu;
    }

    /**
     * @return La date de création du premier auteur sur l'affaire.
     */
    public Date getDateCreationPremierAuteur() {
        return dateCreationPremierAuteur;
    }

    /**
     * @param dateCreationAuteur
     *            La date de création du premier auteur sur l'affaire.
     */
    public void setDateCreationPremierAuteur(Date dateCreationAuteur) {
        this.dateCreationPremierAuteur = dateCreationAuteur;
    }

    /**
     * Retourne l'indicateur signalant l'absence d'auteur identifié<br>
     * Cet indicateur signale l'absence d'auteur identifié lorsque l'affaire est transmise au Parquet.<br>
     * Il n'est pas renseigné à la création de l'affaire (via vue générale Affaire pénale) car on ne sait pas encore si
     * l'affaire à un auteur identifié.<br>
     * Il est systématiquement renseigné à "Vrai" pour les affaires créées via la saisie des X avec classement Ab
     * initio.<br>
     * Pour les affaires crées par la vue Affaire pénale générale, il est valorisé<br>
     * -à "VRAI" s'il n'existe aucune personne affaire avec le rôle Auteur enregistrée dans les 3 jours après la
     * création de l'affaire<br>
     * -à "FAUX", s'il existe une personne affaire avec le rôle Auteur.<br>
     * Cet indicateur n'est jamais affiché, il sert (en infocentre) à determiner le taux d'élucidation des Affaires par
     * la Justice (nombre d'affaire avec indicateur à "Vrai" et existence de personne Auteur , par rapport au nombre
     * d'affaire avec indicateur à "Vrai")<br>
     * Remarque : La procédure auteur inconnu (aussi appelée procédure contre X) concerne les affaires dont l'auteur
     * n'est pas connu et représente plus de 60% des affaires pénales
     *
     * @return l'indicateur signalant l'absence d'auteur identifié
     */
    @RegleDeGestion(RG.AFFM101)
    public boolean getAuteurInconnu() {
        Personne premierAuteur = null;
        // TODO : Pour une question de performances, il vaudrait mieux utiliser le finder
        // PersoneFinderImpl.findPremierAuteur() plutôt que cette boucle.
        // A faire lorsque ce cera utilisé : par l'infocentre uniquement
        for (Iterator<Personne> iter = getPersonnes().iterator(); iter.hasNext() && premierAuteur == null;) {
            Personne personne = iter.next();
            if (personne.getEtatLogique().getCode().equals(EtatPersonneAffaireEnum.ACTIVE.getCode())
                    && personne.getRolePersonne().getRolePersonneEnum() == RolePersonneEnum.AUTEUR) {
                premierAuteur = personne;
            }
        }
        if (premierAuteur == null) {
            return true;
        }
        // Sur quel parquet se baser ?
        IdentificationParquet parquet = getIdentificationParquets().iterator().next();
        // Vérification de la date de création de l'auteur
        if (dateCreationPremierAuteur != null) {
            // détermination si l'auteur a été créé dans les 3 jours
            if (parquet.getDateArriveeParquet() != null
                    && parquet.getDateEnregistrement().getTime() + 3 * 24 * 3600 * 1000
                    < dateCreationPremierAuteur.getTime()) {
                return true;
            }
            //return false;
        }
        return false;
    }

    /**
     * Méthode vérifiant s'il existe déjà un auteur sur l'affaire.<br>
     * Retourne true au premier auteur trouvé, sinon retourne false
     *
     * @return boolean
     */
    public boolean existePersonneAuteur() {

        final Set<Personne> personneAffaires = getPersonnes();
        for (Personne personne : personneAffaires) {
            if (RolePersonneEnum.AUTEUR.equals(personne.getRolePersonne().getRolePersonneEnum())) {
                return true;
            }
        }
        return false;
    }

    /**
     * retourne l'indicateur signalant qu'il s'agit d'une affaire Traitement Temps Réel
     *
     * @return l'indicateur signalant qu'il s'agit d'une affaire Traitement Temps Réel
     */
    public Boolean getIndicTtr() {
        return indicTtr;
    }

    /**
     * Affecte l'indicateur signalant qu'il s'agit d'une affaire Traitement Temps Réel
     *
     * @param penaleGeneraleIndicTtr
     */
    public void setIndicTtr(Boolean penaleGeneraleIndicTtr) {
        this.indicTtr = penaleGeneraleIndicTtr;
    }

    /**
     * @see fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire#getTypeAffaire()
     */
    @Override
    public TypeAffaireEnum getTypeAffaire() {
        return TypeAffaireEnum.PENALE_GENERALE;
    }

    /* (non-Javadoc)
     * @see fr.gouv.justice.cassiopee.invariant.affaire.model.AffairePenale#duplicationAffaireForDIM()
     */
    @Override
    public AffairePenaleGenerale duplicationAffaireForDIM() {
        return (AffairePenaleGenerale) super.duplicationAffaireForDIM();
    }
}
