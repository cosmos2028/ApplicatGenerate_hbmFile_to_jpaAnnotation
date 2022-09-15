package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.util.Date;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;
import fr.gouv.justice.cassiopee.invariant.personne.enumeration.EtatRecoursEnum;

public class EtatRecoursPersonne extends Codification{

    private static final long serialVersionUID = -794955326113000645L;

    /** Identifiant de l'état du recours  */
    private Long id;

    /** Personne à laquelle est rattachée l'EtatRecours */
    private Personne personne;

    /**
     * code de l'état du recours .
     */
    private EtatRecoursEnum etatRecoursEnum;
    private Date dateFraicheur;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public EtatRecoursEnum getEtatRecoursEnum() {
        return etatRecoursEnum;
    }

    public void setEtatRecoursEnum(EtatRecoursEnum etatRecoursEnum) {
        this.etatRecoursEnum = etatRecoursEnum;
    }

    public Date getDateFraicheur() {
        return dateFraicheur;
    }

    public void setDateFraicheur(Date dateFraicheur) {
        this.dateFraicheur = dateFraicheur;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((dateFraicheur == null) ? 0 : dateFraicheur.hashCode());
        result = prime * result + ((etatRecoursEnum == null) ? 0 : etatRecoursEnum.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((personne == null) ? 0 : personne.hashCode());
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
        EtatRecoursPersonne other = (EtatRecoursPersonne) obj;
        if (dateFraicheur == null) {
            if (other.dateFraicheur != null)
                return false;
        } else if (!dateFraicheur.equals(other.dateFraicheur))
            return false;
        if (etatRecoursEnum != other.etatRecoursEnum)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (personne == null) {
            if (other.personne != null)
                return false;
        } else if (!personne.equals(other.personne))
            return false;
        return true;
    }




}
