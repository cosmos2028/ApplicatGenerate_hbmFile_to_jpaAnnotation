package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement;
import fr.gouv.justice.cassiopee.invariant.evenement.model.interfaces.IEvenementCaracs;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.CaracteristiqueType;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.LienEvenementCaracteristiqueType;

/**
 * Composant générique permettant de prendre en compte toutes les caractéristiques d'un Evenement (ou d'un
 * EvenementExterieur). Permet d'accéder à toutes les caractéristiques possibles indiquées pour l'événement-type dans le
 * SR, qu'elles soient valorisées pour cet événement ou non.
 * <p>
 * Ce map a la même interface que java.util.Map mais a un comportement différent. La liste mininale des clés de ce map
 * est la liste des codes de caracteristique-type associés à l'evenement-type. En fonction du String (code
 * caractéristique) passé en argument, get(String) renvoie :
 * <ul>
 * <li>s'il s'agit d'une caractéristique de l'événement enregistrée en base : on obtient un objet Caracteristique
 * encapsulant le format de la caractéristique (date, montant...) et la valeur.
 * <li>s'il s'agit d'une caractéristique liée à l'événement-type dans le SR, mais n'ayant pas de valeur en base pour cet
 * événement : on obtient un objet Caracteristique qui encapsule le format de la caractéristique et une valeur null.</li>
 * <li>s'il ne s'agit pas d'une caractéristique possible pour cet événement-type : on obtient null.</li> </li>
 * </ul>
 *
 * @param <E>
 *            une classe d'événement (Evenement affaire ou EvenementExterieur)
 * @param <C>
 *            la classe de caractéristique correspondante (Caracteristique pour un événement affaire ou
 *            ValeurCaracteristiqueEvenementExterieur)
 */
public abstract class GenericCaracteristiquesUpdater<E extends IEvenementCaracs<C>, C extends CaracteristiqueEvenement>
        implements Map<String, C> {

    private static final long serialVersionUID = -1270917848206447156L;

    /**
     * Constructeur de classe
     *
     * @param evenement
     */
    public GenericCaracteristiquesUpdater(E evenement) {
        this.evenement = evenement;
    }

    private E evenement;

    /*
     * flag d'activation/desactivation des updates des caracs de l'evenement à positionner a false lors de la l'appel
     * depuis la partie edition
     */
    protected boolean updateEvenementCaracs = true;

    public void clear() {
        evenement.getCaracteristiquesPersistante().clear();
    }

    /**
     * Retourne <tt>true</tt> si le map de caracteristique contient une valeur pour le code donné ou si l'evenement type
     * associée contient ce code <b>sans valeur associé dans le map de caracteristique</b>. ce comportement est
     * différent aux implementation de map.
     *
     * @param code
     *            code à tester dans ce map
     * @return <tt>true</tt> si ce map contient une valeur pour le code donné
     * @see Map#containsKey(java.lang.Object)
     */
    public boolean containsKey(Object code) {
        // recherche l'existance du code dans le map des caracteristique en base
        if (evenement.getCaracteristiquesPersistante().containsKey(code))
            return true;

        // recherche l'existance de ce code pour l'evenement type associé à
        // l'evenement
        for (LienEvenementCaracteristiqueType caracteristiqueType : evenement.getEvenementTypePourCaracs()
                .getLienEvenementCaracteristiqueTypes()) {
            if (caracteristiqueType.getPk().getCaracteristiqueType().getCode().equals(code)) {
                return true;
            }
        }

        // si rien n'a été trouvé retourne false
        return false;

    }

    /**
     * retourne <tt>true</tt> si pour ce map un code à la caracteristique spécifiée.
     *
     * @param caracteristique
     *            caracteristique à rechercher
     * @return <tt>true</tt> si la caractistique a été trouvée.
     */
    public boolean containsValue(Object caracteristique) {
        // on ne recherche que dans les caracteristiques en base
        return evenement.getCaracteristiquesPersistante().containsValue(caracteristique);
    }

    /*
     * classe pour retourner des entry
     */
    private class KEntry implements Entry<String, C> {
        private String code;

        private C caracteristique;

        KEntry(String code) {
            this.code = code;
        }

        public String getKey() {
            return this.code;
        }

        public C getValue() {
            return this.caracteristique;
        }

        public C setValue(C value) {
            C old = this.caracteristique;
            this.caracteristique = value;
            return old;
        }
    }

    /**
     * retourne un ensemble representant les associations code caracteristique. le comportement different de
     * l'implementation de map.
     *
     * @return un ensemble representant les associations code caracteristique.
     */
    public Set<Entry<String, C>> entrySet() {
        Set<Entry<String, C>> set = new LinkedHashSet<>();
        Set<String> codeSRs = new HashSet<>();

        // On itère suivant l'ordre des types de caracteristique présents dans le SR
        for (LienEvenementCaracteristiqueType lienCaracteristiqueType : evenement.getEvenementTypePourCaracs()
                .getLienEvenementCaracteristiqueTypes()) {
            // Si la carac type n'a pas été fermé
            if ((lienCaracteristiqueType.getDateFermeture() == null)
                    || (lienCaracteristiqueType.getDateFermeture().after(evenement.getDateEvenement()))) {
                String code = lienCaracteristiqueType.getPk().getCaracteristiqueType().getCode();
                codeSRs.add(code);

                Entry<String, C> entry = new KEntry(code);

                // Si le type de caractéristique est présent en base alors on recopie la valeur
                // Sinon on crée une nouvelle caractéristique
                C carac = evenement.getCaracteristiquesPersistante().get(code);
                if (carac == null) {
                    carac = constructCaracteristique(lienCaracteristiqueType.getPk().getCaracteristiqueType(), evenement);
                }
                carac.setRang(lienCaracteristiqueType.getRang());
                entry.setValue(carac);
                set.add(entry);
            }
        }

        // Si un type de caractéristique est présent en base mais n'est pas présent dans le SR
        // On recopie la caractéristique à la fin
        for (String code : this.evenement.getCaracteristiquesPersistante().keySet()) {
            if (!codeSRs.contains(code)) {
                Entry<String, C> entry = new KEntry(code);
                entry.setValue(evenement.getCaracteristiquesPersistante().get(code));
                set.add(entry);
            }
        }

        return set;
    }

    protected abstract C constructCaracteristique(CaracteristiqueType caracteristiqueType, E evenement);

    /**
     * retourne la caracteristique associée au code donné.
     *
     * @return la caracteristique associée au code donné
     */
    public C get(Object code) {

        // On vérifie que le code du type de caractéristique est présent les
        // caractéristiques persistantes
        if (evenement.getCaracteristiquesPersistante().containsKey(code)) {
            return evenement.getCaracteristiquesPersistante().get(code);
        }

        // On vérifie que le code du type de caractéristique est présent les
        // caractéristiques du SR
        for (LienEvenementCaracteristiqueType caracteristiqueType : evenement.getEvenementTypePourCaracs()
                .getLienEvenementCaracteristiqueTypes()) {

            if (caracteristiqueType.getPk().getCaracteristiqueType().getCode().equals(code)) {
                C caracteristique = constructCaracteristique(caracteristiqueType.getPk().getCaracteristiqueType(),
                        evenement);
                // Si la caracteristique est présente dans le SR on la recopie
                // dans les caractéristiques persistantes
                if (isUpdateEvenementCaracs())
                    this.evenement.getCaracteristiquesPersistante().put((String) code, caracteristique);

                caracteristique.setRang(caracteristiqueType.getRang());
                return caracteristique;
            }
        }

        return null;
    }

    public boolean isEmpty() {
        return (evenement.getCaracteristiquesPersistante().isEmpty() && evenement.getEvenementTypePourCaracs()
                .getLienEvenementCaracteristiqueTypes().isEmpty());
    }

    public Set<String> keySet() {
        return evenement.getCaracteristiquesPersistante().keySet();
    }

    public C put(String code, C caracteristique) {
        return evenement.getCaracteristiquesPersistante().put(code, caracteristique);
    }

    // On ne s'en sert pas
    public void putAll(Map<? extends String, ? extends C> caracteristiques) {
        evenement.getCaracteristiquesPersistante().putAll(caracteristiques);
    }

    public C remove(Object arg0) {
        // Contournement BUG HIBERNATE : https://hibernate.onjira.com/browse/HHH-2112
        // Sera certainement corrigé dans les futures versions d'Hibernate
        evenement.getCaracteristiquesPersistante().containsKey(arg0); // Ici on "réveille" le lazy loading
        // Fin contournement bug hibernate
        return evenement.getCaracteristiquesPersistante().remove(arg0);
    }

    /**
     * Retourne le nombre des caractéristiques
     *
     * @see #entrySet()
     */
    public int size() {
        return entrySet().size();
    }

    /**
     * Retourne les Caractéristiques associées aux clés dans la table de hachage
     *
     * @see #entrySet()
     */
    public Collection<C> values() {

        Collection<C> values = new LinkedList<>();

        Set<Entry<String, C>> entrees = entrySet();

        Iterator<Entry<String, C>> iterator = entrees.iterator();

        while (iterator.hasNext()) {

            Entry<String, C> entree = iterator.next();
            values.add(entree.getValue());
        }

        return values;
    }

    /**
     * @return Returns the updateEvenementCaracs.
     */
    protected boolean isUpdateEvenementCaracs() {
        return updateEvenementCaracs;
    }

    /**
     * Permet de updater/ne pas updater les Caracs evenement selon qu'on est en mode consultation ou en mode creation
     *
     * @param updateEvenementCaracs
     *            The updateEvenementCaracs to set.
     */
    protected void setUpdateEvenementCaracs(boolean updateEvenementCaracs) {
        this.updateEvenementCaracs = updateEvenementCaracs;
    }
}
