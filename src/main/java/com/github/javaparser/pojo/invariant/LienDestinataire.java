/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.administration.acteur.interne.model.Agent;
import fr.gouv.justice.cassiopee.administration.structure.model.Service;
import fr.gouv.justice.cassiopee.common.exception.TechnicalException;

/**
 * Représente le lien entre un événement et un destinataire. Pour des raisons pratiques (gestion des relances) le nombre
 * de destinataires d'un événement semble avoir été limité à 1. Mais cela n'est pas inhérent au métier. Pour cette
 * raison il est plus prudent de conserver la relation 0..n. Les destinataires peuvent être: Personne, élément de
 * structure, personne qualifiée, service, agent. D'où les sous-classes.
 */
public class LienDestinataire extends AbstractLienCorrespondant<Destinataire> implements Cloneable {

    private static final long serialVersionUID = -8200749977855106361L;

    private Long id;

    private Evenement evenement;

    /**
     * Note d'implémentation: petite bizarrerie dans le mapping
     *
     * @return Returns the service.
     */
    public Service getService() {
        if (getAgent() == null)
            return null;
        return getAgent().getService();
    }

    /**
     * Note d'implémentation: petite bizarrerie dans le mapping
     *
     * @param service
     *            The service to set.
     */
    public void setService(Service service) {
        if (this.getAgent() == null) {
            setAgent(new Agent());
        }
        getAgent().setService(service);

    }

    /**
     * @return retourne id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            affecte id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return retourne evenement.
     */
    public Evenement getEvenement() {
        return evenement;
    }

    /**
     * @param evenement
     *            affecte evenement
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    /**
     * ! Indique si le LienDestinataire pointe sur un Service. Méthode nommée avec un article pour éviter toute
     * confusion avec getter.
     *
     * @return true si le lien point sur un service
     */
    public boolean estUnService() {
        return (getService() != null) && (getAgent().getUtilisateur() == null);
    }

    /**
     * retourne le destinataire, méthode de convenance générique
     *
     * @return destinataire
     */
    @Override
    public Destinataire getCorrespondant() {
        if (estUnService())
            return this.getService();
        return super.getCorrespondant();
    }

    /**
     * Valorise le destinataire, méthode de convenance générique NOTE: implémentation catche une exception de la méthode
     * du supertype car on ne veut pas remettre à blanc.
     *
     * @return destinataire
     */
    @Override
    public void setCorrespondant(Destinataire destinataire) {
        try {
            super.setCorrespondant(destinataire);
        } catch (TechnicalException e) {
            // Ce pourrait être un Service que la méthode du supertype n'a pas
            // reconnue!
            if (destinataire instanceof Service) {
                this.setService((Service) destinataire);
            } else {
                throw e;
            }
        }
    }

    @Override
    public boolean estUnTypeElementStructure() {
        if (estUnService())
            return false;

        return super.estUnTypeElementStructure();
    }

    /**
     * Effectue une shallow copy de toutes les propriétés sauf l'id et l'événement.
     *
     * @throws CloneNotSupportedException
     */
    public LienDestinataire clone() throws CloneNotSupportedException {
        LienDestinataire cloneLienDestinataire = (LienDestinataire) super.clone();
        cloneLienDestinataire.setAgent(this.getAgent());
        cloneLienDestinataire.setElementStructure(this.getElementStructure());
        cloneLienDestinataire.setPersonneQualifiee(this.getPersonneQualifiee());
        cloneLienDestinataire.setService(this.getService());
        cloneLienDestinataire.setTypeElementStructure(this.getTypeElementStructure());
        return cloneLienDestinataire;
    }

}
