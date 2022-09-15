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
 * Une affaire p�nale g�n�rale n'est pas une affaire p�nale cr��e par requ�te.
 */
public class AffairePenaleGenerale extends AffairePenale {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -1086554955532103232L;

    /**
     * Cet attribut indique s'il s'agit d'une affaire Traitement Temps R�el. Il est non modifiable et calcul�
     * automatique par le syst�me en fonction de la r�gle de gestion suivante: il est valoris� � OUI si, pour cette
     * affaire avec auteur connu, il existe un �v�nement d?orientation dans les 3 jours suivant les faits. (Si la date
     * des faits est incompl�te prendre la date d'infraction de la 1�re infraction).
     */
    private Boolean indicTtr;

    /**
     * Date de cr�ation du premier auteur sur l'affaire. Cette date est utilis�e pour mettre � jour l'indicateur
     * "auteur inconnu". Si l'auteur est cr�� plus de 72 heures apr�s la cr�ation de l'affaire, l'indicateur est
     * positionn� � VRAI.
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
     * @return La date de cr�ation du premier auteur sur l'affaire.
     */
    public Date getDateCreationPremierAuteur() {
        return dateCreationPremierAuteur;
    }

    /**
     * @param dateCreationAuteur
     *            La date de cr�ation du premier auteur sur l'affaire.
     */
    public void setDateCreationPremierAuteur(Date dateCreationAuteur) {
        this.dateCreationPremierAuteur = dateCreationAuteur;
    }

    /**
     * Retourne l'indicateur signalant l'absence d'auteur identifi�<br>
     * Cet indicateur signale l'absence d'auteur identifi� lorsque l'affaire est transmise au Parquet.<br>
     * Il n'est pas renseign� � la cr�ation de l'affaire (via vue g�n�rale Affaire p�nale) car on ne sait pas encore si
     * l'affaire � un auteur identifi�.<br>
     * Il est syst�matiquement renseign� � "Vrai" pour les affaires cr��es via la saisie des X avec classement Ab
     * initio.<br>
     * Pour les affaires cr�es par la vue Affaire p�nale g�n�rale, il est valoris�<br>
     * -� "VRAI" s'il n'existe aucune personne affaire avec le r�le Auteur enregistr�e dans les 3 jours apr�s la
     * cr�ation de l'affaire<br>
     * -� "FAUX", s'il existe une personne affaire avec le r�le Auteur.<br>
     * Cet indicateur n'est jamais affich�, il sert (en infocentre) � determiner le taux d'�lucidation des Affaires par
     * la Justice (nombre d'affaire avec indicateur � "Vrai" et existence de personne Auteur , par rapport au nombre
     * d'affaire avec indicateur � "Vrai")<br>
     * Remarque : La proc�dure auteur inconnu (aussi appel�e proc�dure contre X) concerne les affaires dont l'auteur
     * n'est pas connu et repr�sente plus de 60% des affaires p�nales
     *
     * @return l'indicateur signalant l'absence d'auteur identifi�
     */
    @RegleDeGestion(RG.AFFM101)
    public boolean getAuteurInconnu() {
        Personne premierAuteur = null;
        // TODO : Pour une question de performances, il vaudrait mieux utiliser le finder
        // PersoneFinderImpl.findPremierAuteur() plut�t que cette boucle.
        // A faire lorsque ce cera utilis� : par l'infocentre uniquement
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
        // V�rification de la date de cr�ation de l'auteur
        if (dateCreationPremierAuteur != null) {
            // d�termination si l'auteur a �t� cr�� dans les 3 jours
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
     * M�thode v�rifiant s'il existe d�j� un auteur sur l'affaire.<br>
     * Retourne true au premier auteur trouv�, sinon retourne false
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
     * retourne l'indicateur signalant qu'il s'agit d'une affaire Traitement Temps R�el
     *
     * @return l'indicateur signalant qu'il s'agit d'une affaire Traitement Temps R�el
     */
    public Boolean getIndicTtr() {
        return indicTtr;
    }

    /**
     * Affecte l'indicateur signalant qu'il s'agit d'une affaire Traitement Temps R�el
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
