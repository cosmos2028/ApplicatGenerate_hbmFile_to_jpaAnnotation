package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;


import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;
//Mantis : 144405 & 153245 & 153252 & 153267
public class ValeurCaracteristiqueEvenementElementStructure extends ValeurCaracteristiqueEvenementExterieur<ElementStructure> {

	/** Identifiant version pour la sérialisation */
	private static final long serialVersionUID = -1787146366589527127L;
	
	private ElementStructure elementStructure;

	/*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getFormat()
     */
	public FormatCaracteristiqueEnum getFormat() {
		return FormatCaracteristiqueEnum.ELEMENT_STRUCTURE;
	}
	
    /**
     * réservé à hibernate
     */
	protected ElementStructure getElementStructure() {
		return elementStructure;
	}
	
    /**
     * réservé à hibernate
     */
	
	protected void setElementStructure(ElementStructure elementStructure) {
		this.elementStructure = elementStructure;
	}
	
    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getValue()
     */
	@Override
	public ElementStructure getValue() {
		return this.elementStructure;
	}

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#setValue()
     */
	@Override
	public void setValue(ElementStructure value) {
		this.elementStructure = value;	
	}
	
}
