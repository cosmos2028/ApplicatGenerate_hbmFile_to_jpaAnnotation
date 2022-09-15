/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
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
 * Le dispositif extérieur pénal est lié à une décision prise dans un cadre extérieur à celui de l'affaire dans lequel
 * il s'insère. Ce dispositif extérieur peut donc être lié à un jugement ou une ordonnance pénale, une ordonnance
 * d?homologation, un arrêt de cour d?assises, un arrêt de cour d?appel, un jugement du TP, etc? prononcé ou non au sein
 * du TGI mais dans le cadre d'une affaire extérieure. Il correspond aux informations lisibles sur un B1 portant
 * condamnation. Il contient
 * <ul>
 * <li>l'événement portant la condamnation (jugement, ordonnance pénale, ordonnance d'homologation de peine, ...), avec
 * sa date, la chambre et l'élément de structure émetteur, la nature du jugement et l'identité de l'auteur</li>
 * <li>l'ensemble des peines prononcées par le tribunal, relativement aux infractions pour lesquelles la personne auteur
 * a été déclarée coupable et est condamnée à une ou plusieurs peines (avec leurs caractéristiques, mais sans leur
 * motif)</li>
 * <li>l'ensemble des événements de la famille Recherche - détention (mesures de sûreté prises à l?instruction ou au
 * jugement (détentions provisoires par exemple)), ainsi que l'événement ayant rendu la décision définitive (événement
 * de signification ou notification lié à l'événement portant la condamnation (jugement, l'ordonnance pénale, ...) et
 * ses caractéristiques).</li> Le dispositif pénal extérieur d'un auteur peut être soit créé manuellement par
 * l'utilisateur, soit recopié par le système depuis un DIA d'une autre affaire. Aucun lien n'est conservé avec le DIA
 * initial.
 * </ul>
 */
public class DispositifExterieurAuteur extends DispositifExterieur implements java.io.Serializable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 4827708506490688507L;

    /**
     * Indique si l'auteur était mineur au moment des faits. Utilisé pour afficher les peines encourues par les mineurs.
     */
    private Boolean mineur = Boolean.FALSE;

    /**
     * Indique si le dispositif pénal extérieur auteur a été créé manuellement par l'utilisateur ou bien recopié à
     * partir d'un DIA. Dans ce dernier cas il n'est pas modifiable.
     */
    private Boolean recopie = Boolean.FALSE;

    /**
     * Dans le cadre d'une requête en dispense de révocation, indique si le dispositif pénal auteur est le révocant
     * (peine ferme qui fait tomber le sursis) ou le révoqué (le sursis). Ne sera pas renseigné dans les autres cas.
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
     * Indique si l'auteur était mineur au moment des faits. Utilisé pour afficher les peines encourues par les mineurs.
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
     * Renvoie l'événement de signification ou notification de ce dispositif extérieur. Renvoie le premier trouvé ou
     * null, sans vérifier l'existence ou l'unicité. En principe un dispositif extérieur doit toujours avoir un seul
     * événement de signification ou notification.
     *
     * @return le permier trouvé, ou null
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
     * Renvoie l'infraction du dispositif extérieur portant l'ID désirée. Renvoie la première trouvé ou null, sans
     * vérifier l'existence.
     *
     * @param id
     *            l' id désirée.
     * @return l' InfractionExterieur trouvé, ou null.
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
     * Renvoie les liens auteur-infraction de l'affaire liés à ce DIE.
     *
     * @return Returns the lienAuteurInfractions.
     */
    public Set<LienAuteurInfraction> getLienAuteurInfractions() {
        return lienAuteurInfractions;
    }

    /**
     * Valorise les liens auteur-infraction de l'affaire liés à ce DIE.
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
