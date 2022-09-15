package fr.gouv.justice.cassiopee.invariant.affaire.model;

import fr.gouv.justice.cassiopee.invariant.affaire.service.enumeration.TypeAffaireEnum;

/**
 * Cette classe est porteuse des sp�cificit�s des affaires civiles du juge<br>
 * des enfants, notamment, le sous-type de dossier.
 */
public class AffaireCivileJe extends Affaire {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 3609127600905568441L;

    /**
     * Le sous type de dossier �tablit le domaine sur lequel porte l'affaire trait�e par le JE. Pour une affaire civile
     * JE, le sous-type peut �tre : - AE pour assistance �ducative, - TPS pour tutelle aux prestations sociales, - PJM
     * pour protection jeune majeur, - AGBF Aide � la gestion du budget familial.
     */
    private SousTypeAffaire sousTypeAffaire;

    /**
     * Retourne le sous type de dossier
     *
     * @return le sous type de dossier
     */
    public SousTypeAffaire getSousTypeAffaire() {
        return sousTypeAffaire;
    }

    /**
     * Mise a jour du sous type de dossier
     *
     * @param civileSousTypeAffaire
     *            le sous type de dossier a affecter � l'affaire
     */
    public void setSousTypeAffaire(SousTypeAffaire civileSousTypeAffaire) {
        this.sousTypeAffaire = civileSousTypeAffaire;
    }

    /**
     * @see fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire#getTypeAffaire()
     */
    @Override
    public TypeAffaireEnum getTypeAffaire() {
        return TypeAffaireEnum.CIVILE_JE;
    }

    public AffaireCivileJe duplicationAffaireForDIM() {
        AffaireCivileJe affaireDuplicate = (AffaireCivileJe) super.duplicationAffaireForDIM();
        affaireDuplicate.setSousTypeAffaire(this.getSousTypeAffaire());
        return affaireDuplicate;
    }
}
