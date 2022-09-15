package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.util.ArrayList;
import java.util.List;

import fr.gouv.justice.cassiopee.administration.acteur.externe.model.PersonneQualifiee;
import fr.gouv.justice.cassiopee.administration.acteur.interne.model.ActeurInterne;
import fr.gouv.justice.cassiopee.administration.structure.model.Service;
import fr.gouv.justice.cassiopee.common.exception.TechnicalException;
import fr.gouv.justice.cassiopee.invariant.personne.model.AvocatPersonneAffaire;
import fr.gouv.justice.cassiopee.invariant.personne.model.Interprete;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;
import fr.gouv.justice.cassiopee.service.editiontype.service.enumeration.DestinataireEditionEnum;

/**
 * La classe demande d'édition représente une demande d'édition avec destinataire. Cet objet est persistant.
 */
public abstract class EditionDemandeDiffereeAvecDestinataire extends EditionDemandeDifferee {

    /** Le type de destinataire */
    private String codeTypeDestinataire;

    /** la personne destinataire */
    private Personne destinatairePersonne;

    /** l'element de structure destinataire */
    private ElementStructure destinataireElementStructure;

    /** Le service destinataire de l'édition */
    private Service destinataireService;

    /** personne qualifie destinataire de l'édition */
    private PersonneQualifiee destinatairePersonneQualifiee;

    /** L'avocat personne Affaire destinataire de l'édition */
    private AvocatPersonneAffaire destinataireAvocatPersonneAffaire;

    /** L'interprete destinataire de l'édition */
    private Interprete destinataireInterprete;

    /** l'acteur interne destinataire de l'édition */
    private ActeurInterne destinataireActeurInterne;

    /** ensemble de criteres utlises pour certains demandes (recommandes, ...) */
    private String criteres;

    public void setDestinataireEdition(DestinataireEdition destinataireEdition) {
        if (destinataireEdition instanceof AvocatPersonneAffaire) {
            this.codeTypeDestinataire = DestinataireEditionEnum.AvocatPersonneAffaire.getCode();
            setDestinataireAvocatPersonneAffaire((AvocatPersonneAffaire) destinataireEdition);
        } else if (destinataireEdition instanceof ElementStructure) {
            this.codeTypeDestinataire = DestinataireEditionEnum.ElementStructure.getCode();
            setDestinataireElementStructure((ElementStructure) destinataireEdition);
        } else if (destinataireEdition instanceof PersonneQualifiee) {
            this.codeTypeDestinataire = DestinataireEditionEnum.PersonneQualifiee.getCode();
            setDestinatairePersonneQualifiee((PersonneQualifiee) destinataireEdition);
        } else if (destinataireEdition instanceof Personne) {
            this.codeTypeDestinataire = DestinataireEditionEnum.PersonneAffaire.getCode();
            setDestinatairePersonne((Personne) destinataireEdition);
        } else if (destinataireEdition instanceof Service) {
            this.codeTypeDestinataire = DestinataireEditionEnum.Service.getCode();
            setDestinataireService((Service) destinataireEdition);
        } else if (destinataireEdition instanceof Interprete) {
            this.codeTypeDestinataire = DestinataireEditionEnum.Interprete.getCode();
            setDestinataireInterprete((Interprete) destinataireEdition);
        } else if (destinataireEdition instanceof ActeurInterne) {
            this.codeTypeDestinataire = DestinataireEditionEnum.ActeurInterne.getCode();
            setDestinataireActeurInterne((ActeurInterne) destinataireEdition);
        } else {
            throw new TechnicalException("destinaire édition non peristant");
        }
    }

    /**
     * retourne le destinataire d'édition. La réference de l'objet destinataire utilisée est fonction du type de
     * destinataire.
     *
     * @return le destinataire d'édition
     */
    public DestinataireEdition getDestinataireEdition() {
        DestinataireEdition destinataireEdition = null;

        if (codeTypeDestinataire != null) {
            if (codeTypeDestinataire.equals(DestinataireEditionEnum.PersonneAffaire.getCode())) {
                destinataireEdition = getDestinatairePersonne();
            } else if (codeTypeDestinataire.equals(DestinataireEditionEnum.ElementStructure.getCode())) {
                destinataireEdition = getDestinataireElementStructure();
            } else if (codeTypeDestinataire.equals(DestinataireEditionEnum.Service.getCode())) {
                destinataireEdition = getDestinataireService();
            } else if (codeTypeDestinataire.equals(DestinataireEditionEnum.PersonneQualifiee.getCode())) {
                destinataireEdition = getDestinatairePersonneQualifiee();
            } else if (codeTypeDestinataire.equals(DestinataireEditionEnum.AvocatPersonneAffaire.getCode())) {
                destinataireEdition = getDestinataireAvocatPersonneAffaire();
            } else if (codeTypeDestinataire.equals(DestinataireEditionEnum.Interprete.getCode())) {
                destinataireEdition = getDestinataireInterprete();
            } else if (codeTypeDestinataire.equals(DestinataireEditionEnum.ActeurInterne.getCode())) {
                destinataireEdition = getDestinataireActeurInterne();
            }
        }
        return destinataireEdition;
    }

    public String getCodeTypeDestinataire() {
        return codeTypeDestinataire;
    }

    public void setCodeTypeDestinataire(String codeTypeDestinataire) {
        this.codeTypeDestinataire = codeTypeDestinataire;
    }

    public Personne getDestinatairePersonne() {
        return destinatairePersonne;
    }

    public void setDestinatairePersonne(Personne destinatairePersonne) {
        this.destinatairePersonne = destinatairePersonne;
    }

    public ElementStructure getDestinataireElementStructure() {
        return destinataireElementStructure;
    }

    public void setDestinataireElementStructure(ElementStructure destinataireElementStructure) {
        this.destinataireElementStructure = destinataireElementStructure;
    }

    public Service getDestinataireService() {
        return destinataireService;
    }

    public void setDestinataireService(Service destinataireService) {
        this.destinataireService = destinataireService;
    }

    public PersonneQualifiee getDestinatairePersonneQualifiee() {
        return destinatairePersonneQualifiee;
    }

    public void setDestinatairePersonneQualifiee(PersonneQualifiee destinatairePersonneQualifiee) {
        this.destinatairePersonneQualifiee = destinatairePersonneQualifiee;
    }

    public AvocatPersonneAffaire getDestinataireAvocatPersonneAffaire() {
        return destinataireAvocatPersonneAffaire;
    }

    public void setDestinataireAvocatPersonneAffaire(AvocatPersonneAffaire destinataireAvocatPersonneAffaire) {
        this.destinataireAvocatPersonneAffaire = destinataireAvocatPersonneAffaire;
    }

    public Interprete getDestinataireInterprete() {
        return destinataireInterprete;
    }

    public void setDestinataireInterprete(Interprete destinataireInterprete) {
        this.destinataireInterprete = destinataireInterprete;
    }

    public ActeurInterne getDestinataireActeurInterne() {
        return destinataireActeurInterne;
    }

    public void setDestinataireActeurInterne(ActeurInterne destinataireActeurInterne) {
        this.destinataireActeurInterne = destinataireActeurInterne;
    }

    /**
     * @return Returns the criteres.
     */
    public String getCriteres() {
        return criteres;
    }

    /**
     * @param criteres
     *            The criteres to set.
     */
    public void setCriteres(String criteres) {
        this.criteres = criteres;
    }

    public List<Long> getCriteresToListLong(final String separator) {
        List<Long> longList = new ArrayList<>();
        if (this.criteres != null && !this.criteres.isEmpty()) {
            //suppression des crochets si besoin :
            String criteresSansCrochets = this.criteres.replace("[", "").replace("]", "");
            if (separator != null && !separator.isEmpty() && criteresSansCrochets.indexOf(separator) != -1) {
                String[] criteresList = criteresSansCrochets.split(separator);
                for (String critere : criteresList) {
                    longList.add(new Long(critere.trim()));
                }
            } else {
                longList.add(new Long(criteresSansCrochets.trim()));
            }
        }
        return longList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime
                * result
                + ((codeTypeDestinataire == null) ? 0 : codeTypeDestinataire
                        .hashCode());
        result = prime * result
                + ((criteres == null) ? 0 : criteres.hashCode());
        result = prime
                * result
                + ((destinataireActeurInterne == null) ? 0
                        : destinataireActeurInterne.hashCode());
        result = prime
                * result
                + ((destinataireAvocatPersonneAffaire == null) ? 0
                        : destinataireAvocatPersonneAffaire.hashCode());
        result = prime
                * result
                + ((destinataireElementStructure == null) ? 0
                        : destinataireElementStructure.hashCode());
        result = prime
                * result
                + ((destinataireInterprete == null) ? 0
                        : destinataireInterprete.hashCode());
        result = prime
                * result
                + ((destinatairePersonne == null) ? 0 : destinatairePersonne
                        .hashCode());
        result = prime
                * result
                + ((destinatairePersonneQualifiee == null) ? 0
                        : destinatairePersonneQualifiee.hashCode());
        result = prime
                * result
                + ((destinataireService == null) ? 0 : destinataireService
                        .hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        EditionDemandeDiffereeAvecDestinataire other = (EditionDemandeDiffereeAvecDestinataire) obj;
        if (codeTypeDestinataire == null) {
            if (other.codeTypeDestinataire != null)
                return false;
        } else if (!codeTypeDestinataire.equals(other.codeTypeDestinataire))
            return false;
        if (criteres == null) {
            if (other.criteres != null)
                return false;
        } else if (!criteres.equals(other.criteres))
            return false;
        if (destinataireActeurInterne == null) {
            if (other.destinataireActeurInterne != null)
                return false;
        } else if (!destinataireActeurInterne
                .equals(other.destinataireActeurInterne))
            return false;
        if (destinataireAvocatPersonneAffaire == null) {
            if (other.destinataireAvocatPersonneAffaire != null)
                return false;
        } else if (!destinataireAvocatPersonneAffaire
                .equals(other.destinataireAvocatPersonneAffaire))
            return false;
        if (destinataireElementStructure == null) {
            if (other.destinataireElementStructure != null)
                return false;
        } else if (!destinataireElementStructure
                .equals(other.destinataireElementStructure))
            return false;
        if (destinataireInterprete == null) {
            if (other.destinataireInterprete != null)
                return false;
        } else if (!destinataireInterprete.equals(other.destinataireInterprete))
            return false;
        if (destinatairePersonne == null) {
            if (other.destinatairePersonne != null)
                return false;
        } else if (!destinatairePersonne.equals(other.destinatairePersonne))
            return false;
        if (destinatairePersonneQualifiee == null) {
            if (other.destinatairePersonneQualifiee != null)
                return false;
        } else if (!destinatairePersonneQualifiee
                .equals(other.destinatairePersonneQualifiee))
            return false;
        if (destinataireService == null) {
            if (other.destinataireService != null)
                return false;
        } else if (!destinataireService.equals(other.destinataireService))
            return false;
        return true;
    }

}
