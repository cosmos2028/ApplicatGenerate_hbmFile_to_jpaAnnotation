package fr.gouv.justice.cassiopee.invariant.exterieur.model.peine;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import fr.gouv.justice.cassiopee.invariant.exterieur.model.DispositifExterieur;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.PeineOuMesureType;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.TypeCaractPeineMesure;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.service.enumeration.TypeCaractPeineMesureEnum;

/**
 * Classe permettant de d�crire les peines incluses dans le dispositif p�nal ext�rieur de l'affaire :
 * <ul>
 * <li>Peine prononc�e pendant le jugement,</li>
 * <li>Ex�cution provisoire de la peine prononc�e,</li>
 * <li>Sursis de la peine prononc�e,</li>
 * <li>Ex�cution provisoire du sursis.</li>
 * </ul>
 */
public class PeineExterieur implements java.io.Serializable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 7969863776870098650L;

    private Long id;

    private PeineExterieur peineMere;

    private PeineOuMesureType peineOuMesureType;

    private DispositifExterieur dispositifExterieur;

    private Set<ValeurCaracteristiquePeineExterieur> valeurCaracteristiques = new LinkedHashSet<>();

    private Set<PeineExterieur> peinesFilles = new LinkedHashSet<>();

    private boolean avecExecutionProvisoire = false;

    /**
     * @return Returns the avecExecutionProvisoire.
     */
    public boolean isAvecExecutionProvisoire() {
        return avecExecutionProvisoire;
    }

    /**
     * @param avecExecutionProvisoire
     *            The avecExecutionProvisoire to set.
     */
    public void setAvecExecutionProvisoire(boolean avecExecutionProvisoire) {
        this.avecExecutionProvisoire = avecExecutionProvisoire;
    }

    public DispositifExterieur getDispositifExterieur() {
        return dispositifExterieur;
    }

    public void setDispositifExterieur(DispositifExterieur dispositifExterieur) {
        this.dispositifExterieur = dispositifExterieur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PeineExterieur getPeineMere() {
        return peineMere;
    }

    public void setPeineMere(PeineExterieur peineMere) {
        this.peineMere = peineMere;
    }

    public PeineOuMesureType getPeineOuMesureType() {
        return peineOuMesureType;
    }

    public void setPeineOuMesureType(PeineOuMesureType peineOuMesureType) {
        this.peineOuMesureType = peineOuMesureType;
    }

    public Set<PeineExterieur> getPeinesFilles() {
        return peinesFilles;
    }

    public void setPeinesFilles(Set<PeineExterieur> peinesFilles) {
        this.peinesFilles = peinesFilles;
    }

    public Set<ValeurCaracteristiquePeineExterieur> getValeurCaracteristiques() {
        return valeurCaracteristiques;
    }

    public void setValeurCaracteristiques(Set<ValeurCaracteristiquePeineExterieur> valeurCaracteristiques) {
        this.valeurCaracteristiques = valeurCaracteristiques;
    }

    /**
     * retourne une map (cl�: codeTypeCaract, valeur: ValeurCaracteristiquePeineExterieur) de la peine exterieure
     *
     * @return map de valeur caracteristique de la peine
     */
    @SuppressWarnings("unchecked")
    public Map<String, ValeurCaracteristiquePeineExterieur> getMapValeursCaracteristiques() {
        Map<String, ValeurCaracteristiquePeineExterieur> mapValeursCaractByCode = new HashMap<>();
        for (ValeurCaracteristiquePeineExterieur valeurCaracteristique : this.valeurCaracteristiques) {
            TypeCaractPeineMesure typeCaractPeineMesure = valeurCaracteristique.getTypeCaractPeineMesure();
            mapValeursCaractByCode.put(typeCaractPeineMesure.getCode(), valeurCaracteristique);
        }
        return mapValeursCaractByCode;
    }

    /**
     * Retourne vrai si la valeur caract�ristique correspondant au code type caract�ristique est renseign�e avec une
     * valeur non nulle, faux sinon.
     *
     * @param typeCaractPeineMesureEnum
     * @return true si la caract�ristique est renseign�e avec une valeur non nulle, faux sinon.
     */
    @SuppressWarnings("unchecked")
    public boolean hasValeurCaracteristiqueByCode(TypeCaractPeineMesureEnum typeCaractPeineMesureEnum) {
        return getMapValeursCaracteristiques().get(typeCaractPeineMesureEnum.getCode()) != null
                && getMapValeursCaracteristiques().get(typeCaractPeineMesureEnum.getCode()).getValue() != null;
    }

}
