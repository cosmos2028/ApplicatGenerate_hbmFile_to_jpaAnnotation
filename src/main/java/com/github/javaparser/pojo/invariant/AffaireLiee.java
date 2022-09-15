package fr.gouv.justice.cassiopee.invariant.affaire.model;

import java.io.Serializable;

import fr.gouv.justice.cassiopee.administration.structure.model.Tgi;
import fr.gouv.justice.cassiopee.processus.parquet.model.IdentificationParquet;
import fr.gouv.justice.cassiopee.processus.parquet.service.enumeration.OrigineCreationEnum;

/**
 * La notion d'affaire liée permet de gérer les liens entre affaires. Les affaires liées peuvent ne pas exister dans la
 * base CASSIOPEE mais on garde la trace des numéros de parquet.<br>
 * Elle permet par exemple :<br>
 * - de connaître les affaires jointes sur une affaire d'accueil,<br>
 * - de connaître les affaires requête d'une affaire d'origine associée,<br>
 * - de connaître les affaires créées par duplication d'une affaire d'origine dans le cadre d'une instance modificative.<br>
 * <br>
 * Attention : en création d'affaire courrier extérieur, il n'y a pas création d'affaire mais juste création d'un
 * Identification Parquet, avec la même affaire utilisée. Donc l'AffaireLiee devrait pointer sur elle-même, ce qui est
 * inutile car les liens origine/destination ne signifient plus rien. Il a donc été décidé de n'utiliser qu'un seul lien
 * : AffaireLiee.affaireDestination car c'est selui qui est obligatoire et assure la persistence, et d'y enregistrer les
 * affaires d'origine, car elles peuvent être plusieurs. <br>
 * Contenu de la table AFF_AFFAIRE_LIEE en fonction du type de liaison :<br>
 * Jonction Affaire requête Courrier extér Duplication<br>
 * ID_AFFAIRE_DEST accueil aff requête affaire dupliquée<br>
 * ID_AFFAIRE_ORIGINE jointe origine nul origine<br>
 * CODE_TGI_AFF_LIEE nul aff requête tgi courr ext nul<br>
 * TGI_ORIGN_COUR_EXT nul nul tgi origine nul<br>
 * NUM_PQT_ORIGINE jointe aff origine origine origine<br>
 * NUM_PQT_DEST accueil aff requête aff courrier ext dupliquée<br>
 * ORIGN_CREA_AFF_DEST nul R C nul<br>
 */
public class AffaireLiee implements Serializable {

    /**
     * Identifiant de sérialisation
     */
    private static final long serialVersionUID = 843361216705004916L;

    private Long id;
    private Affaire affaireDestination;
    private Affaire affaireOrigine;
    private String origineCreationAffaire;
    private String numeroParquetOrigine;
    private String numeroParquetDestination;
    private Boolean lienRequeteAJour = false;
    private Tgi tgiAffaireDistante;
    private Tgi tgiAffaireOrigineCourrierExt;
    private Boolean existanceAffaireDansKcop = false;
    /**
     * TGI de l'affaire d'accueil (lors d'une jonction)
     */
    private Tgi tgiAffaireAccueil;
    /**
     * TGI de l'affaire jointe (lors d'une jonction)
     */
    private Tgi tgiAffaireJointe;

    /**
     * @return True si l'affaire existe dans Cassiopee, False sinon.
     */
    public Boolean getExistanceAffaireDansKcop() {
        return existanceAffaireDansKcop;
    }

    /**
     * @param existanceAffaireDansKcop
     *            True si l'affaire existe dans Cassiopee, False sinon.
     */
    public void setExistanceAffaireDansKcop(Boolean existanceAffaireDansKcop) {
        this.existanceAffaireDansKcop = existanceAffaireDansKcop;
    }

    /**
     * Selon le cas :<br>
     * - jonction : retourne nul.<br>
     * - requête : retourne le TGI de l'affaire distante.<br>
     * - courrier ext : retourne le TGI de l'affaire courrier extérieur.
     *
     * @return le TGI de l'affaire liée.
     */
    public Tgi getTgiAffaireDistante() {
        return tgiAffaireDistante;
    }

    /**
     * @param tgiAffaireDistante
     *            le TGI de l'affaire liée.
     */
    public void setTgiAffaireDistante(Tgi tgiAffaireDistante) {
        this.tgiAffaireDistante = tgiAffaireDistante;
    }

    /**
     * Dans le cas de l'affaire courrier extérieur, retourne le TGI de l'affaire d'origine.<br>
     * Retourne null dans les autres cas.
     *
     * @return le TGI de l'affaire d'origine pour les affaire courrier extérieur.
     */
    public Tgi getTgiAffaireOrigineCourrierExt() {
        return tgiAffaireOrigineCourrierExt;
    }

    /**
     * @param tgiAffaireOrigine
     *            le TGI de l'affaire d'origine.
     */
    public void setTgiAffaireOrigineCourrierExt(Tgi tgiAffaireOrigine) {
        this.tgiAffaireOrigineCourrierExt = tgiAffaireOrigine;
    }

    /**
     * Dans le cas d'une jonction, retourne le TGI de l'affaire d'accueil.<br>
     * Retourne null dans les autres cas.
     *
     * @return le TGI de l'affaire d'origine pour les affaires qui ont fait l'objet d'une jonction.
     */
    public Tgi getTgiAffaireAccueil() {
        return tgiAffaireAccueil;
    }

    /**
     * @param tgiAffaireAccueil
     *            le TGI de l'affaire d'accueil
     */
    public void setTgiAffaireAccueil(Tgi tgiAffaireAccueil) {
        this.tgiAffaireAccueil = tgiAffaireAccueil;
    }

    /**
     * Dans le cas d'une jonction, retourne le TGI de l'affaire jointe.<br>
     * Retourne null dans les autres cas.
     *
     * @return le TGI de l'affaire d'origine pour les affaires qui ont fait l'objet d'une jonction.
     */
    public Tgi getTgiAffaireJointe() {
        return tgiAffaireJointe;
    }

    /**
     * @param tgiAffaireJointe
     *            le TGI de l'affaire jointe
     */
    public void setTgiAffaireJointe(final Tgi tgiAffaireJointe) {
        this.tgiAffaireJointe = tgiAffaireJointe;
    }

    /**
     * @return le numéro de parquet de l'affaire de destination (affaire d'accueil ou affaire créée).
     */
    public String getNumeroParquetDestination() {
        return numeroParquetDestination;
    }

    /**
     * @param numeroParquetDestination
     *            le numéro de parquet de l'affaire de destination (affaire d'accueil ou affaire créée).
     */
    public void setNumeroParquetDestination(String numeroParquetDestination) {
        this.numeroParquetDestination = numeroParquetDestination;
    }

    /**
     * Retourne : <br>
     * - en cas de jonction : l'affaire d'accueil,<br>
     * - en cas d'affaire requête : l'affaire requête,<br>
     * - en cas d'affaire courrier extérieur : l'affaire d'origine (qui est la même que l'affaire de destination),<br>
     * - en cas de duplication : l'affaire dupliquée.<br>
     * Cet attribut est obligatoire, car c'est celui qui permet la relation à l'affaire.
     *
     * @return l'affaire de destination
     */
    public Affaire getAffaireDestination() {
        return affaireDestination;
    }

    /**
     * Positionne : <br>
     * - en cas de jonction : l'affaire d'accueil,<br>
     * - en cas d'affaire requête : l'affaire requête,<br>
     * - en cas d'affaire courrier extérieur : l'affaire d'origine (qui est la même que l'affaire de destination),<br>
     * - en cas de duplication : l'affaire dupliquée.<br>
     * Cet attribut est obligatoire, car c'est celui qui permet la relation à l'affaire.
     *
     * @param affairePrimaire
     *            l'affaire de destination.
     */
    public void setAffaireDestination(Affaire affairePrimaire) {
        this.affaireDestination = affairePrimaire;
    }

    /**
     * Retourne : <br>
     * - en cas de jonction : l'affaire jointe,<br>
     * - en cas d'affaire requête : null car l'affaire n'appartient pas forcément à Cassiopée,<br>
     * - en cas d'affaire courrier extérieur : null,<br>
     * - en cas de duplication : l'affaire d'origine.<br>
     *
     * @return l'affaire d'origine.
     */
    public Affaire getAffaireOrigine() {
        return affaireOrigine;
    }

    /**
     * Positionne : <br>
     * - en cas de jonction : l'affaire jointe,<br>
     * - en cas d'affaire requête : null car l'affaire n'appartient pas forcément à Cassiopée,<br>
     * - en cas d'affaire courrier extérieur : null,<br>
     * - en cas de duplication : l'affaire d'origine.<br>
     *
     * @param affaireSecondaire
     *            l'affaire d'origine.
     */
    public void setAffaireOrigine(Affaire affaireSecondaire) {
        this.affaireOrigine = affaireSecondaire;
    }

    /**
     * @return l'identifiant de AffaireLiee (de la table de jointure).
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            l'identifiant de AffaireLiee (de la table de jointure).
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Il s'agit du numéro de parquet complet (comprenant l'identifiant du TGI : code origine+n° élément de structure)
     * de l'affaire avec lequel on établit le lien depuis une autre affaire. <br>
     * Dans le cas de l'affaire requête, ce numéro est renseigné par l'utilisateur et non contrôlé. De tout façon,
     * l'affaire n'existe sans doute pas dans Cassiopée.<br>
     * Dans le cas de la jonction, le numéro réduit est utilisé (sur 11 caractères) car la jonction est intra-TGI.
     *
     * @return Le numéro de parquet complet de l'affaire d'origine.
     */
    public String getNumeroParquetOrigine() {
        return numeroParquetOrigine;
    }

    /**
     * Il s'agit du numéro de parquet complet (comprenant l'identifiant du TGI : code origine+n° élément de structure)
     * de l'affaire avec lequel on établit le lien depuis une autre affaire. <br>
     * Dans le cas de l'affaire requête, ce numéro est renseigné par l'utilisateur et non contrôlé. De tout façon,
     * l'affaire n'existe sans doute pas dans Cassiopée.<br>
     * Dans le cas de la jonction, le numéro réduit est utilisé (sur 11 caractères) car la jonction est intra-TGI.
     *
     * @param numeroParquetOrigine
     *            Le numéro de parquet complet de l'affaire d'origine.
     */
    public void setNumeroParquetOrigine(String numeroParquetOrigine) {
        this.numeroParquetOrigine = numeroParquetOrigine;
    }

    /**
     * Cette information permet de savoir de quel type est le numéro de parquet lié et notamment s'il s'agit:<br>
     * - d'une affaire "requête" (origine renseignée à R)<br>
     * - d’une affaire « courrier extérieure » (origine renseignée à C).<br>
     * Dans les autres cas d'affaire liée, l'origine de création ne sera pas renseignée (affaires jointes).<br>
     * Correspond aux valeurs de l'Enum OrigineCreationEnum.
     *
     * @return l'origine de création du lien.
     */
    public String getOrigineCreationAffaire() {
        return origineCreationAffaire;
    }

    /**
     * Cette information permet de savoir de quel type est le numéro de parquet lié.
     *
     * @return l'origine de création du lien.
     */
    public OrigineCreationEnum getOrigineCreationAffaireEnum() {
        return OrigineCreationEnum.resolve(origineCreationAffaire);
    }

    /**
     * Méthode rendue private (utilisée par hibernate uniquement) pour contrôler les valeurs rentrées avec l'énum, sinon
     * plantage contrainte de la base.<br>
     *
     * @param origineCreationAffaire
     *            l'origine de création du lien.
     */
    @SuppressWarnings("unused")
    private void setOrigineCreationAffaire(String origineCreationAffaire) {
        this.origineCreationAffaire = origineCreationAffaire;
    }

    /**
     * Cette information permet de savoir de quel type est le numéro de parquet lié et notamment s'il s'agit:<br>
     * - d'une affaire "requête" (origine renseignée à R)<br>
     * - d’une affaire « courrier extérieure » (origine renseignée à C).<br>
     * Dans les autres cas d'affaire liée, l'origine de création ne sera pas renseignée (affaires jointes).
     *
     * @param origineCreationAffaire
     *            l'origine de création du lien.
     */
    public void setOrigineCreationAffaire(OrigineCreationEnum origineCreationAffaire) {
        if (origineCreationAffaire == null) {
            this.origineCreationAffaire = null;
        } else {
            this.origineCreationAffaire = origineCreationAffaire.getCode();
        }
    }

    /**
     * Dans le cas de création d'affaire requête en reconnaissance d'identité, on établit un lien entre l'affaire
     * requête et les affaires d'origine. Lors du jugement de l'affaire requête, l'utilisateur peut mettre à jour les
     * affaires d'origine. Lorsque la mise à jour a été effectuée sur une affaire d'origine, on conserve cette
     * information sous forme de booléen sur le lien.
     *
     * @return Vrai si le lien a été mis à jour, faux sinon.
     */
    public Boolean getLienRequeteAJour() {
        return lienRequeteAJour;
    }

    /**
     * Dans le cas de création d'affaire requête en reconnaissance d'identité, on établit un lien entre l'affaire
     * requête et les affaires d'origine. Lors du jugement de l'affaire requête, l'utilisateur peut mettre à jour les
     * affaires d'origine. Lorsque la mise à jour a été effectuée sur une affaire d'origine, on conserve cette
     * information sous forme de booléen sur le lien.
     *
     * @param lienRequeteAJour
     *            Vrai si le lien a été mis à jour, faux sinon.
     */
    public void setLienRequeteAJour(Boolean lienRequeteAJour) {
        this.lienRequeteAJour = lienRequeteAJour;
    }

    /**
     * Retourne l'identification parquet de l'affaire d'origine
     *
     * @return
     */
    public IdentificationParquet getIdentificationParquetOrigine() {
        return affaireOrigine.getIdentificationParquet(tgiAffaireJointe.getCode(), numeroParquetOrigine);
    }

    /**
     * Retourne l'identification parquet de l'affaire de destination
     *
     * @return
     */
    public IdentificationParquet getIdentificationParquetDestination() {
        return affaireDestination.getIdentificationParquet(tgiAffaireAccueil.getCode(), numeroParquetDestination);
    }
}
