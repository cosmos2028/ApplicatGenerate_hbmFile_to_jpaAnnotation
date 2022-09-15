package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.common.exception.TechnicalException;
import fr.gouv.justice.cassiopee.commons.collectors.SingletonCollector;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.compare.CompareValeurCaracteristiquePrononcableParNumeroOrdre;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;
import fr.gouv.justice.cassiopee.referentiel.codification.model.enumeration.DeviseEnum;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.helper.PeineOuMesureTypeHelper;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.MotifPeineOuMesure;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.PeineOuMesureType;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.TypeCaractPeineMesure;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.TypeCaracteristiquePossible;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.service.enumeration.TitrePeineMesureEnum;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.service.enumeration.TypeCaractPeineMesureEnum;
import fr.gouv.justice.cassiopee.service.user.UserContextProvider;

/**
 * Classe "outil" Correspond à la peine ou mesure de base qui doit être proposée sur l'IHM
 */

public class PeineOuMesureBase implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8198209019307682734L;

    /** peine ou mesure type */
    protected PeineOuMesureType peineOuMesureType;

    /** peine ou mesure prononcée */
    protected PeineOuMesure peineOuMesure;

    /** Indique si la peine est Obligatoire ou non */
    private boolean obligatoire = false;

    /** Indicateur de peine prononcée ou non */
    private boolean prononcee = false;

    /** Indicateur permettant de savoir si il s agit d une peine ou mesure applicable sur un mineur */
    private boolean isForMineur;

    private TypePeineOuMesureEnum typePeineOuMesureEnum;

    /**
     * liste des valeurs caracteristiques (possible + les valeurs quand ca existent)
     */
    private List<ValeurCaracteristiquePrononcable> valeursCaracteristiquesPrononcables = new ArrayList<>();

    /**
     * constructeur vide
     */
    public PeineOuMesureBase() {
        super();
    }

    /**
     * @param peineOuMesureType
     * @param peineOuMesure
     * @param indicateurObligatoire
     */
    public PeineOuMesureBase(PeineOuMesureType peineOuMesureType, PeineOuMesure peineOuMesure,
            boolean indicateurObligatoire) {
        initPeineOuMesureBase(peineOuMesureType, peineOuMesure, indicateurObligatoire);
    }

    /**
     * Constructeur d'un objet simplifié représentant une peine ou mesure
     *
     * @param peineOuMesureType
     *            type de la peine ou mesure dans l'objet
     * @param peineOuMesure
     *            peine ou mesure a partir de laquelle on cree l'objet
     * @param indicateurObligatoire
     * @param typePeineOuMesureEnum
     *            type de la peine ou mesure calculé
     */
    public PeineOuMesureBase(final PeineOuMesureType peineOuMesureType, final PeineOuMesure peineOuMesure,
            final boolean indicateurObligatoire, final TypePeineOuMesureEnum typePeineOuMesureEnum) {
        initPeineOuMesureBase(peineOuMesureType, peineOuMesure, indicateurObligatoire, typePeineOuMesureEnum);
    }

    /**
     * permet d'initialiser l'objet PeineOuMesureBase (peineOuMesureType, peineOuMesure,
     * valeursCaracteristiquesPrononcables)
     *
     * @param peineOuMesureType
     * @param peineOuMesurePrononcee
     * @param indicateurObligatoire
     */
    protected void initPeineOuMesureBase(PeineOuMesureType peineOuMesureType, PeineOuMesure peineOuMesurePrononcee,
            boolean indicateurObligatoire) {
        this.peineOuMesureType = peineOuMesureType;

        this.peineOuMesure = peineOuMesurePrononcee;
        /*
         * les valeurs caract. prononcables sont chargées au besoin (c.a.dire au moment du
         * getValeursCaracteristiquesPrononcables)
         */
        if (this.peineOuMesure != null) {
            // la peine est prononcée
            this.prononcee = true;
        }

        this.obligatoire = indicateurObligatoire;

    }

    /**
     * Surcharge de la méthode initPeineOuMesureBase pour y ajouter le type de peine ou mesure enum d'une peine fille
     * (non renseigné sinon)
     *
     * @param peineOuMesureType
     *            : type de la peine ou mesure dans l'objet
     * @param peineOuMesurePrononcee
     *            : peine ou mesure a partir de laquelle on cree l'objet
     * @param indicateurObligatoire
     * @param typePeineOuMesureEnum
     *            : type de la peine ou mesure calculé
     */
    protected void initPeineOuMesureBase(final PeineOuMesureType peineOuMesureType,
            final PeineOuMesure peineOuMesurePrononcee, final boolean indicateurObligatoire,
            final TypePeineOuMesureEnum typePeineOuMesureEnum) {
        this.setTypePeineOuMesureEnum(typePeineOuMesureEnum);
        initPeineOuMesureBase(peineOuMesureType, peineOuMesurePrononcee, indicateurObligatoire);
    }

    /**
     * Permet de creer une liste de valeurs caracteristiques prononcables
     *
     * @return List<ValeurCaracteristiquePrononcable>
     */
    @SuppressWarnings("rawtypes")
    @RegleDeGestion(RG.TPMI057)
    private List<ValeurCaracteristiquePrononcable> creerValeursCaracteristiquesPrononcables() {
        // faire le merge
        List<ValeurCaracteristiquePrononcable> listeValeurCaracteristiquePrononcable = new ArrayList<>();
        Set<TypeCaracteristiquePossible> typeCaracteristiquePossibleSet = getTypesCaracteristiquesPossiblesAvecFP();
        for (TypeCaracteristiquePossible typeCaracteristiquePossible : typeCaracteristiquePossibleSet) {
            ValeurCaracteristiquePrononcable valeurCaracteristiquePrononcable = new ValeurCaracteristiquePrononcable();
            // numero Ordre
            valeurCaracteristiquePrononcable.setNumeroOrdre(typeCaracteristiquePossible.getNumeroOrdre());

            String codeTypeCaracteristique = typeCaracteristiquePossible.getTypeCaractPeineMesure().getCode();
            // rechercher la valeur caract. parmi les valeurCaract. de la peine
            // prononcée
            ValeurCaracteristique valeurCaract = getValeurCaracteristiqueByCodeType(codeTypeCaracteristique);
            if (valeurCaract == null) {
                /*
                 * valeur caract. non trouvée oubien la peine n'est pas prononcée donc pas de valeur caract. dans ce
                 * cas, il faut créer une valeurCaract. sans valeur à partir du code type
                 */

                valeurCaract = creerValeurCaracteristique(typeCaracteristiquePossible.getTypeCaractPeineMesure());
            }
            // valeurCaracteristiquePrononcable
            // .setCodeTypeCaract(codeTypeCaracteristique);
            valeurCaracteristiquePrononcable.setValeurCaracteristique(valeurCaract);

            // ajout dans la liste
            listeValeurCaracteristiquePrononcable.add(valeurCaracteristiquePrononcable);
        }
        Collections.sort(listeValeurCaracteristiquePrononcable,
                new CompareValeurCaracteristiquePrononcableParNumeroOrdre());
        return listeValeurCaracteristiquePrononcable;
    }

    /**
     * Fournit le code de la peine ou mesure type
     *
     * @return Long
     */
    public Long getCodeMesure() {
        return getPeineOuMesureType().getCodeMesure();
    }

    /**
     * Fournit le libellé court de la peine ou mesure type
     *
     * @return String
     */
    public String getLibelleCourt() {
        return getPeineOuMesureType().getLibelleCourt();
    }

    /**
     * Fournit la liste des caractéristiques possibles associées à la peine ou mesure type + carac FP
     *
     * @return List<ValeurCaracteristique>
     */
    protected Set<TypeCaracteristiquePossible> getTypesCaracteristiquesPossiblesAvecFP() {
        return getPeineOuMesureType().getTypesCaracteristiquesPossiblesAvecFP();
    }

    /**
     * Fournit la liste des caractéristiques valorisées associées à la peine ou mesure
     *
     * @return List<ValeurCaracteristique>
     */
    @SuppressWarnings("rawtypes")
    private Set<ValeurCaracteristique> getValeursCaracteristiques() {
        if (this.peineOuMesure != null) {
            return this.peineOuMesure.getValeursCaracteristiques();
        }
        return null;
    }

    /**
     * @return PeineOuMesure
     */
    public PeineOuMesure getPeineOuMesure() {
        return peineOuMesure;
    }

    /**
     * @param peineOuMesure
     */
    public void setPeineOuMesure(PeineOuMesure peineOuMesure) {
        this.peineOuMesure = peineOuMesure;
    }

    /**
     * @return PeineOuMesureType
     */
    public PeineOuMesureType getPeineOuMesureType() {
        return peineOuMesureType;
    }

    /**
     * @param peineOuMesureType
     */
    public void setPeineOuMesureType(PeineOuMesureType peineOuMesureType) {
        this.peineOuMesureType = peineOuMesureType;
    }

    /**
     * Indique si la peine ou mesure est cochée ou non
     *
     * @return Boolean
     */
    public boolean isObligatoire() {
        return obligatoire;
    }

    /**
     * Permet de mettre à jour l'indicateur indiquant si la peine est obligatoire ou non
     *
     * @param indicateurObligatoire
     */
    public void setObligatoire(boolean indicateurObligatoire) {
        this.obligatoire = indicateurObligatoire;
    }

    /**
     * @return true si la peine est prononcée
     */
    public boolean isPrononcee() {
        return prononcee;
    }

    /**
     * @param prononcee
     */
    public void setPrononcee(boolean prononcee) {
        this.prononcee = prononcee;
    }

    /**
     * @return valeursCaracteristiquesPrononcables
     */
    public List<ValeurCaracteristiquePrononcable> getValeursCaracteristiquesPrononcables() {
        if (this.valeursCaracteristiquesPrononcables.isEmpty()) {
            // les caract. ne sont pas encore chargées
            this.valeursCaracteristiquesPrononcables = chooseValeurCaracteristiqueUniteMonetaire(creerValeursCaracteristiquesPrononcables());
        }
        return this.valeursCaracteristiquesPrononcables;
    }

    /**
     * Si une caractéristique Euro existe, on regarde s il existe une caractéristique FP. Si oui on garde une seule des deux caractéristiques.
     * En création on garde la valeur correspondant à l unité monétaire du TGI de la personne connectée.
     * En modification (si la valeur de la caractéristique n est pas vide) on garde l unité monétaire existante.
     * @param listValeursCaracteristiquesPrononcables
     * @return
     */
    public List<ValeurCaracteristiquePrononcable> chooseValeurCaracteristiqueUniteMonetaire(List<ValeurCaracteristiquePrononcable> listValeursCaracteristiquesPrononcables) {
        ValeurCaracteristiquePrononcable valeurCaracteristiquePrononcableEuro = getValeurCaracteristiqueByCode(listValeursCaracteristiquesPrononcables, DeviseEnum.EURO.getCodeCaracMesure());
        if(valeurCaracteristiquePrononcableEuro != null) {
            ValeurCaracteristiquePrononcable valeurCaracteristiquePrononcableFP = getValeurCaracteristiqueByCode(listValeursCaracteristiquesPrononcables, DeviseEnum.FRANC_PACIFIQUE.getCodeCaracMesure());
            if(valeurCaracteristiquePrononcableFP != null) {
                if(valeurCaracteristiquePrononcableFP.getValeurCaracteristique().getValue() != null) {
                    removeValeurCaracteristiquePrononcableEuro(listValeursCaracteristiquesPrononcables, valeurCaracteristiquePrononcableFP, valeurCaracteristiquePrononcableEuro);
                } else if(valeurCaracteristiquePrononcableEuro.getValeurCaracteristique().getValue() != null) {
                    listValeursCaracteristiquesPrononcables.remove(valeurCaracteristiquePrononcableFP);
                } else if(UserContextProvider.getUserContext().getDeviseEnum().getCode() == DeviseEnum.EURO.getCode()) {
                        listValeursCaracteristiquesPrononcables.remove(valeurCaracteristiquePrononcableFP);
                } else {
                    removeValeurCaracteristiquePrononcableEuro(listValeursCaracteristiquesPrononcables, valeurCaracteristiquePrononcableFP, valeurCaracteristiquePrononcableEuro);
                }
            }
        }
        return listValeursCaracteristiquesPrononcables;
    }

    public ValeurCaracteristiquePrononcable getValeurCaracteristiqueByCode(List<ValeurCaracteristiquePrononcable> listValeursCaracteristiquesPrononcables, String code) {
        return listValeursCaracteristiquesPrononcables.stream()
        .filter(valeurCaract -> valeurCaract.getValeurCaracteristique().getTypeCaractPeineMesure().getCode().equals(code))
        .collect(SingletonCollector.collectOrNull());
    }
    /**
     * Méthode qui permet de supprimer la caractéristique "E" de la liste, et de set le numéro d'ordre de la caractéristique "FP" avec celui de la caractéristique "E".
     * @param listValeursCaracteristiquesPrononcables
     * @param valeurCaracteristiquePrononcableFP
     * @param valeurCaracteristiquePrononcableEuro
     */
    public void removeValeurCaracteristiquePrononcableEuro(List<ValeurCaracteristiquePrononcable> listValeursCaracteristiquesPrononcables, ValeurCaracteristiquePrononcable valeurCaracteristiquePrononcableFP, ValeurCaracteristiquePrononcable valeurCaracteristiquePrononcableEuro) {
        listValeursCaracteristiquesPrononcables.remove(valeurCaracteristiquePrononcableEuro);
        listValeursCaracteristiquesPrononcables.remove(valeurCaracteristiquePrononcableFP);
        valeurCaracteristiquePrononcableFP.setNumeroOrdre(valeurCaracteristiquePrononcableEuro.getNumeroOrdre());
        listValeursCaracteristiquesPrononcables.add(valeurCaracteristiquePrononcableFP);
    }


    /**
     * permet de créer un objet ValeurCaracteristique en fonction du code type caracteristique passé en paramète
     *
     * @param typeCaractPeineMesure
     * @return ValeurCaracteristique
     */
    @SuppressWarnings("rawtypes")
    public static ValeurCaracteristique creerValeurCaracteristique(TypeCaractPeineMesure typeCaractPeineMesure) {
        ValeurCaracteristique valeurCaracteristique;

        String codeTypeCaracteristique = typeCaractPeineMesure.getCode();

        if (codeTypeCaracteristique.equals(TypeCaractPeineMesureEnum.DATE.getCode())
                || codeTypeCaracteristique.equals(TypeCaractPeineMesureEnum.DATE_DEBUT.getCode())
                || codeTypeCaracteristique.equals(TypeCaractPeineMesureEnum.DATE_FIN.getCode())) {
            valeurCaracteristique = new ValeurCaracteristiqueDate();
        } else if (codeTypeCaracteristique.equals(TypeCaractPeineMesureEnum.DUREE_AN_MOIS_JOURS.getCode())
                || codeTypeCaracteristique.equals(TypeCaractPeineMesureEnum.DELAI_EXECUTION.getCode())
                || codeTypeCaracteristique.equals(TypeCaractPeineMesureEnum.DUREE_DEFENITIVE_OU_A_PERPETUITE.getCode()))  {
            valeurCaracteristique = new ValeurCaracteristiqueDuree();
        } else if (codeTypeCaracteristique.equals(TypeCaractPeineMesureEnum.NOMBRE_UNITE.getCode())
                || codeTypeCaracteristique.equals(TypeCaractPeineMesureEnum.DUREE_SEMAINE.getCode())
                || codeTypeCaracteristique.equals(TypeCaractPeineMesureEnum.DUREE_HEURE.getCode())) {
            valeurCaracteristique = new ValeurCaracteristiqueNombreEntier();
        } else if (codeTypeCaracteristique.equals(TypeCaractPeineMesureEnum.MONTANT_EURO.getCode())
                || codeTypeCaracteristique.equals(TypeCaractPeineMesureEnum.MONTANT_CENTIMES.getCode())
                || codeTypeCaracteristique.equals(TypeCaractPeineMesureEnum.MONTANT_FRANC_CFP.getCode())
                || codeTypeCaracteristique.equals(TypeCaractPeineMesureEnum.MONTANT_FRANC.getCode())){
            valeurCaracteristique = new ValeurCaracteristiqueMontant();
        } else if (codeTypeCaracteristique.equals(TypeCaractPeineMesureEnum.LIEU.getCode())
                || codeTypeCaracteristique.equals(TypeCaractPeineMesureEnum.ECHEANCIER.getCode())
                || codeTypeCaracteristique.equals(TypeCaractPeineMesureEnum.PRECISION.getCode())
                || codeTypeCaracteristique.equals(TypeCaractPeineMesureEnum.ORGANISME.getCode())) {
            valeurCaracteristique = new ValeurCaracteristiqueChaine();

        } else {
            throw new TechnicalException(
                    "impossible de creer l'objet de type ValeurCaracteristique pour le codeTypeCaracteristique:"
                            + codeTypeCaracteristique);
        }

        // construire une valeurCaracteristiqueId
        ValeurCaracteristiqueId valeurCaracteristiqueId = new ValeurCaracteristiqueId();
        valeurCaracteristiqueId.setTypeCaractMesure(typeCaractPeineMesure);
        valeurCaracteristique.setId(valeurCaracteristiqueId);

        return valeurCaracteristique;
    }

    /**
     * rechercher parmi les caracteristiques de la peine prononcée, la
     * valeurCaract (si elle existe)
     */
    @SuppressWarnings("rawtypes")
    private ValeurCaracteristique getValeurCaracteristiqueByCodeType(String codeTypeCaract) {
        if (getValeursCaracteristiques() != null) {
            for (ValeurCaracteristique valeurCaracteristique : getValeursCaracteristiques()) {
                if (valeurCaracteristique.getId().getTypeCaractMesure().getCode().equals(codeTypeCaract)) {
                    return valeurCaracteristique;
                }
            }

        }
        return null;
    }

    /**
     * verifie si parmi les filles de la peine prononcées passées en paramètre, il existe au moins une peine de type
     * typePeineOuMesureEnumRecherche
     *
     * @param typePeineOuMesureEnumRecherche
     * @param peineOuMesurePrononcee
     * @return boolean
     */
    protected boolean existeUneObligationPrononcee(TypePeineOuMesureEnum typePeineOuMesureEnumRecherche,
            PeineOuMesure peineOuMesurePrononcee) {
        if (peineOuMesurePrononcee != null) {
            for (PeineOuMesure peineOuMesure : peineOuMesurePrononcee.getPeinesOuMesuresFilles()) {
                if (peineOuMesure.getTypePeineOuMesureEnum().getType().equals(typePeineOuMesureEnumRecherche.getType())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * permet d'initialiser l'indicateur d'execution provisoire
     *
     * @param dateEvenement
     * @param codeTitrePeineMesure
     * @return Boolean.TRUE si la case est présente et cochée, Boolean.FALSE si la case est présente et non cochée, null
     *         si case absente
     */
    @RegleDeGestion({ RG.DIAI206, RG.DIAI215, RG.TPMM056 })
    public Boolean publierIndicateurExecutionProvisoire(Date dateEvenement, String codeTitrePeineMesure) {

        /*
         * Si la peine mere est deja prononcee, et s'il existe une peine fille de type EP ==> alors l'indicateur = true
         */
        if (this.peineOuMesure != null) {
            for (PeineOuMesure peineOuMesure : this.peineOuMesure.getPeinesOuMesuresFilles()) {
                String typePeineOuMesureEnum = peineOuMesure.getTypePeineOuMesureEnum().getType();
                if (typePeineOuMesureEnum.equals(TypePeineOuMesureEnum.EXECUTION_PROVISOIRE_SUR_MODALITE.getType())
                        || typePeineOuMesureEnum.equals(TypePeineOuMesureEnum.EXECUTION_PROVISOIRE_SUR_PEINE.getType())) {
                    return Boolean.TRUE;
                }
            }
        }

        // RG DIAI206, RGDIAI215, TPMM056
        /*
         * rechercher s'il faut proposer la mesure exécution provisoire, Pour les peines principales et les mesures de
         * niveau 2, présenter la case à cocher "exécution provisoire" par exploitation du lien mesure-mesure n°11 (type
         * de lien inclus). Si ce lien propose une mesure, présenter la case à cocher
         */
        if (codeTitrePeineMesure == null || codeTitrePeineMesure.equals(TitrePeineMesureEnum.PRINCIPAL.getCode())) {
            // je suis sur un Niv. >1 ou (je suis sur Niv1 ET Peine Principale)
            // ==> regarder dans le SR: si il n'existe pas dans le SR donc cet
            // indicateur est à ne pas afficher
            if (PeineOuMesureTypeHelper.publierIndicateurExecutionProvisoireSR(this.peineOuMesure,
                    this.peineOuMesureType, dateEvenement)) {
                return Boolean.FALSE; // case présente non cochée
            }
            return null; // case absente
        }
        // forcer l'indicateur dans le reste des cas
        return Boolean.FALSE; // case présente! non cochée
    }

    /**
     * les motifs possibles de la peine
     *
     * @return Set<MotifPeineOuMesure>
     */
    public Set<MotifPeineOuMesure> getMotifsPossibles() {
        return getPeineOuMesureType().getMotifPeineOuMesureSet();
    }

    /**
     * motifs enregistrés de la peine
     *
     * @return motifs
     */
    public Set<MotifPeineOuMesure> getMotifs() {
        if (this.peineOuMesure != null) {
            return this.peineOuMesure.getMotifs();
        }
        return null;
    }

    public void setForMineur(boolean isForMineur) {
        this.isForMineur = isForMineur;
    }

    public boolean isForMineur() {
        return isForMineur;
    }

    /**
     * Assesseur d'un type de la peine ou mesure
     *
     * @return le type de la peine ou mesure
     */
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return typePeineOuMesureEnum;
    }

    /**
     * Setter d'un type de la peine ou mesure
     *
     * @param typePeineOuMesureEnum
     *            : type de la peine ou mesure
     */
    public void setTypePeineOuMesureEnum(TypePeineOuMesureEnum typePeineOuMesureEnum) {
        this.typePeineOuMesureEnum = typePeineOuMesureEnum;
    }

}
