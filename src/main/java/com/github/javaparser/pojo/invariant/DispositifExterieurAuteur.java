/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement.EvenementExterieur;
import fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement.EvenementExterieurSignificationOuNotification;
import fr.gouv.justice.cassiopee.invariant.exterieur.model.infraction.InfractionExterieur;
import fr.gouv.justice.cassiopee.invariant.exterieur.model.peine.PeineExterieur;
import fr.gouv.justice.cassiopee.invariant.exterieur.service.enumeration.TypeDispositifExterieurEnum;
import fr.gouv.justice.cassiopee.invariant.infraction.model.LienAuteurInfraction;

/**
 * Le dispositif ext�rieur p�nal est li� � une d�cision prise dans un cadre ext�rieur � celui de l'affaire dans lequel
 * il s'ins�re. Ce dispositif ext�rieur peut donc �tre li� � un jugement ou une ordonnance p�nale, une ordonnance
 * d?homologation, un arr�t de cour d?assises, un arr�t de cour d?appel, un jugement du TP, etc? prononc� ou non au sein
 * du TGI mais dans le cadre d'une affaire ext�rieure. Il correspond aux informations lisibles sur un B1 portant
 * condamnation. Il contient
 * <ul>
 * <li>l'�v�nement portant la condamnation (jugement, ordonnance p�nale, ordonnance d'homologation de peine, ...), avec
 * sa date, la chambre et l'�l�ment de structure �metteur, la nature du jugement et l'identit� de l'auteur</li>
 * <li>l'ensemble des peines prononc�es par le tribunal, relativement aux infractions pour lesquelles la personne auteur
 * a �t� d�clar�e coupable et est condamn�e � une ou plusieurs peines (avec leurs caract�ristiques, mais sans leur
 * motif)</li>
 * <li>l'ensemble des �v�nements de la famille Recherche - d�tention (mesures de s�ret� prises � l?instruction ou au
 * jugement (d�tentions provisoires par exemple)), ainsi que l'�v�nement ayant rendu la d�cision d�finitive (�v�nement
 * de signification ou notification li� � l'�v�nement portant la condamnation (jugement, l'ordonnance p�nale, ...) et
 * ses caract�ristiques).</li> Le dispositif p�nal ext�rieur d'un auteur peut �tre soit cr�� manuellement par
 * l'utilisateur, soit recopi� par le syst�me depuis un DIA d'une autre affaire. Aucun lien n'est conserv� avec le DIA
 * initial.
 * </ul>
 */
public class DispositifExterieurAuteur extends DispositifExterieur implements java.io.Serializable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 4827708506490688507L;

    /**
     * Indique si l'auteur �tait mineur au moment des faits. Utilis� pour afficher les peines encourues par les mineurs.
     */
    private Boolean mineur = Boolean.FALSE;

    /**
     * Indique si le dispositif p�nal ext�rieur auteur a �t� cr�� manuellement par l'utilisateur ou bien recopi� �
     * partir d'un DIA. Dans ce dernier cas il n'est pas modifiable.
     */
    private Boolean recopie = Boolean.FALSE;

    /**
     * Dans le cadre d'une requ�te en dispense de r�vocation, indique si le dispositif p�nal auteur est le r�vocant
     * (peine ferme qui fait tomber le sursis) ou le r�voqu� (le sursis). Ne sera pas renseign� dans les autres cas.
     */
    private Boolean revocante;

    private Set<InfractionExterieur> infractions = new LinkedHashSet<InfractionExterieur>();

    private Set<PeineExterieur> peinesNiveau1 = new LinkedHashSet<PeineExterieur>();

    private Set<PeineExterieur> peinesNiveauIdentique = new LinkedHashSet<PeineExterieur>();

    private Set<LienAuteurInfraction> lienAuteurInfractions = new LinkedHashSet<LienAuteurInfraction>();

    private Date dateDecisionDefinitive;

    public Set<InfractionExterieur> getInfractions() {
        return infractions;
    }

    public void setInfractions(Set<InfractionExterieur> infractions) {
        this.infractions = infractions;
    }

    public Set<PeineExterieur> getPeinesNiveau1() {
        return peinesNiveau1;
    }

    public void setPeinesNiveau1(Set<PeineExterieur> peines) {
        this.peinesNiveau1 = peines;
    }

    public Set<PeineExterieur> getPeinesNiveauIdentique() {
        return peinesNiveauIdentique;
    }

    public void setPeinesNiveauIdentique(Set<PeineExterieur> peinesNiveauIdentique) {
        this.peinesNiveauIdentique = peinesNiveauIdentique;
    }

    /**
     * Indique si l'auteur �tait mineur au moment des faits. Utilis� pour afficher les peines encourues par les mineurs.
     */
    public Boolean getMineur() {
        return mineur;
    }

    public void setMineur(Boolean mineur) {
        this.mineur = mineur;
    }

    public Boolean getRecopie() {
        return recopie;
    }

    public void setRecopie(Boolean recopie) {
        this.recopie = recopie;
    }

    public Boolean getRevocante() {
        return revocante;
    }

    public void setRevocante(Boolean revocante) {
        this.revocante = revocante;
    }

    /**
     * Renvoie l'�v�nement de signification ou notification de ce dispositif ext�rieur. Renvoie le premier trouv� ou
     * null, sans v�rifier l'existence ou l'unicit�. En principe un dispositif ext�rieur doit toujours avoir un seul
     * �v�nement de signification ou notification.
     *
     * @return le permier trouv�, ou null
     */
    public EvenementExterieurSignificationOuNotification getEvenementSignificationOuNotification() {
        EvenementExterieurSignificationOuNotification evtSignificationOuNotification = null;
        for (EvenementExterieur evt : this.getEvenements()) {
            if (evt instanceof EvenementExterieurSignificationOuNotification) {
                evtSignificationOuNotification = (EvenementExterieurSignificationOuNotification) evt;
            }
        }
        return evtSignificationOuNotification;
    }

    /**
     * Renvoie l'infraction du dispositif ext�rieur portant l'ID d�sir�e. Renvoie la premi�re trouv� ou null, sans
     * v�rifier l'existence.
     *
     * @param id
     *            l' id d�sir�e.
     * @return l' InfractionExterieur trouv�, ou null.
     */
    public InfractionExterieur getInfractionById(Long id) {
        if (id == null) {
            return null;
        }
        for (InfractionExterieur inf : this.getInfractions()) {
            if (id.equals(inf.getId())) {
                return inf;
            }
        }
        return null;
    }

    /**
     * Renvoie les liens auteur-infraction de l'affaire li�s � ce DIE.
     *
     * @return Returns the lienAuteurInfractions.
     */
    public Set<LienAuteurInfraction> getLienAuteurInfractions() {
        return lienAuteurInfractions;
    }

    /**
     * Valorise les liens auteur-infraction de l'affaire li�s � ce DIE.
     *
     * @param lienAuteurInfractions
     *            The lienAuteurInfractions to set.
     */
    public void setLienAuteurInfractions(Set<LienAuteurInfraction> lienAuteurInfractions) {
        this.lienAuteurInfractions = lienAuteurInfractions;
    }

    @Override
    public TypeDispositifExterieurEnum getTypeDispositifExterieurEnum() {
        return TypeDispositifExterieurEnum.AUTEUR;
    }

    public Date getDateDecisionDefinitive() {
        return dateDecisionDefinitive;
    }

    public void setDateDecisionDefinitive(Date dateDecisionDefinitive) {
        this.dateDecisionDefinitive = dateDecisionDefinitive;
    }

}
