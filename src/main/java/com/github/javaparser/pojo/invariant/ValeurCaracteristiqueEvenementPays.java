package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Pays;

//Mantis 125782 : ADF / Exception Technique sur édition AVISAUD (Andromed) sur une affaire contenant une récidive

/**
 * objet modele pour les caracteristique de type Pays
 *
 * @see fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum#PAYS
 */

public class ValeurCaracteristiqueEvenementPays extends ValeurCaracteristiqueEvenementExterieur<Pays> {
	
	private static final long serialVersionUID = -8684048943793856995L;

	private Pays pays;
	
	/*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getFormat()
     */
    public FormatCaracteristiqueEnum getFormat() {
        return FormatCaracteristiqueEnum.PAYS;
    }

    /**
     * réservé à hibernate
     */
    protected Pays getPays() {
        return pays;
    }

    /**
     * réservé à hibernate
     */
    protected void setPays(Pays value) {
        this.pays = value;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getValue()
     */
	@Override
	public Pays getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getValue()
     */
	@Override
	public void setValue(Pays value) {
		// TODO Auto-generated method stub
		
	}
    
    
}
