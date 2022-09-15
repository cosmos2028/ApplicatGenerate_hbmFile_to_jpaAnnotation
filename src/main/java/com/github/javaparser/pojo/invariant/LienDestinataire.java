/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.administration.acteur.interne.model.Agent;
import fr.gouv.justice.cassiopee.administration.structure.model.Service;
import fr.gouv.justice.cassiopee.common.exception.TechnicalException;

/**
 * Repr�sente le lien entre un �v�nement et un destinataire. Pour des raisons pratiques (gestion des relances) le nombre
 * de destinataires d'un �v�nement semble avoir �t� limit� � 1. Mais cela n'est pas inh�rent au m�tier. Pour cette
 * raison il est plus prudent de conserver la relation 0..n. Les destinataires peuvent �tre: Personne, �l�ment de
 * structure, personne qualifi�e, service, agent. D'o� les sous-classes.
 */
public class LienDestinataire extends AbstractLienCorrespondant<Destinataire> implements Cloneable {

    private static final long serialVersionUID = -8200749977855106361L;

    private Long id;

    private Evenement evenement;

    /**
     * Note d'impl�mentation: petite bizarrerie dans le mapping
     *
     * @return Returns the service.
     */
    public Service getService() {
        if (getAgent() == null)
            return null;
        return getAgent().getService();
    }

    /**
     * Note d'impl�mentation: petite bizarrerie dans le mapping
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
     * ! Indique si le LienDestinataire pointe sur un Service. M�thode nomm�e avec un article pour �viter toute
     * confusion avec getter.
     *
     * @return true si le lien point sur un service
     */
    public boolean estUnService() {
        return (getService() != null) && (getAgent().getUtilisateur() == null);
    }

    /**
     * retourne le destinataire, m�thode de convenance g�n�rique
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
     * Valorise le destinataire, m�thode de convenance g�n�rique NOTE: impl�mentation catche une exception de la m�thode
     * du supertype car on ne veut pas remettre � blanc.
     *
     * @return destinataire
     */
    @Override
    public void setCorrespondant(Destinataire destinataire) {
        try {
            super.setCorrespondant(destinataire);
        } catch (TechnicalException e) {
            // Ce pourrait �tre un Service que la m�thode du supertype n'a pas
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
     * Effectue une shallow copy de toutes les propri�t�s sauf l'id et l'�v�nement.
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
