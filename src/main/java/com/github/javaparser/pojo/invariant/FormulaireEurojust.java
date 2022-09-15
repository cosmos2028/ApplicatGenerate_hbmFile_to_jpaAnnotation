package fr.gouv.justice.cassiopee.invariant.eurojust.model.formulaire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import fr.gouv.justice.cassiopee.administration.structure.model.Service;
import fr.gouv.justice.cassiopee.common.exception.TechnicalException;
import fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire;
import fr.gouv.justice.cassiopee.invariant.eurojust.dto.FormulaireEurojustContenu;

public class FormulaireEurojust implements Serializable {
    private static final long serialVersionUID = 6140033107068360145L;

    private Long id;
    private String codeTGI;
    private String numParquet;
    private String objetSaisine;
    private String referenceEurojust;
    private String etatCode;
    private Service serviceProprietaire;
    private byte[] datas;
    private String referenceCassiopee;
    private Affaire affaire;
    private Set<HistoriqueEtatFormulaireEurojust> historiqueEtatList = new HashSet<>();

    private boolean hasEquipeCommuneEnquete;
    private boolean hasCasParticulierementGrave;
    private boolean hasConflitCompetence;
    private boolean hasLivraisonControlee;
    private boolean hasDifficulteRefusDemandeEntraide;


    // numero chrono
    public String getNumeroSequentielFormulaire() {
        // On retourne l'id sur 5 caractères avec la réinitialisation du compteur tous les 100 000 :
        return String.format("%05d", this.id % 100000L);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeTGI() {
        return codeTGI;
    }

    public void setCodeTGI(String codeTGI) {
        this.codeTGI = codeTGI;
    }

    public String getNumParquet() {
        return numParquet;
    }

    public void setNumParquet(String numParquet) {
        this.numParquet = numParquet;
    }

    public String getObjetSaisine() {
        return objetSaisine;
    }

    public void setObjetSaisine(String objetSaisine) {
        this.objetSaisine = objetSaisine;
    }

    public String getReferenceEurojust() {
        return referenceEurojust;
    }

    public void setReferenceEurojust(String referenceEurojust) {
        this.referenceEurojust = referenceEurojust;
    }

    public String getEtatCode() {
        return etatCode;
    }

    public void setEtatCode(String etatCode) {
        this.etatCode = etatCode;
    }

    public String getReferenceCassiopee() {
        return referenceCassiopee;
    }

    public void setReferenceCassiopee(String referenceCassiopee) {
        this.referenceCassiopee = referenceCassiopee;
    }

    public Affaire getAffaire() {
        return affaire;
    }

    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
    }

    public Service getServiceProprietaire() {
        return serviceProprietaire;
    }

    public void setServiceProprietaire(Service serviceProprietaire) {
        this.serviceProprietaire = serviceProprietaire;
    }

    public byte[] getDatas() {
        return datas;
    }

    public void setDatas(byte[] datas) {
        this.datas = datas;
    }

    public void setContenu(FormulaireEurojustContenu contenu) {
        // Java to XML
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            JAXBContext jc = JAXBContext.newInstance(FormulaireEurojustContenu.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(contenu, byteArrayOutputStream);

            this.setDatas(byteArrayOutputStream.toByteArray());
        } catch (JAXBException e) {
            throw new TechnicalException("erreur lors de l'enregistrement XML du formulaire", e);
        } catch (IOException e1) {
            throw new TechnicalException("Problème de fermeture de ressource: ", e1);
        }
    }

    public FormulaireEurojustContenu getContenu() {
        // XML to Java
        FormulaireEurojustContenu form = null;

        try (InputStream input = new ByteArrayInputStream(this.datas)) {
            JAXBContext jc = JAXBContext.newInstance(FormulaireEurojustContenu.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            unmarshaller.setEventHandler(new ValidationEventHandler() {
                @Override
                public boolean handleEvent(ValidationEvent event) {
                    return true;
                }
            });

            form = (FormulaireEurojustContenu) unmarshaller.unmarshal(input);
        } catch (JAXBException e) {
            throw new TechnicalException("erreur lors la lecture du XML du formulaire", e);
        } catch (IOException e1) {
            throw new TechnicalException("Problème de fermeture de ressource", e1);
        }

        return form;
    }

    public Set<HistoriqueEtatFormulaireEurojust> getHistoriqueEtatList() {
        return historiqueEtatList;
    }

    public void setHistoriqueEtatList(Set<HistoriqueEtatFormulaireEurojust> historiqueEtatList) {
        this.historiqueEtatList = historiqueEtatList;
    }

    public boolean isHasCasParticulierementGrave() {
        return hasCasParticulierementGrave;
    }

    public void setHasCasParticulierementGrave(boolean hasCasParticulierementGrave) {
        this.hasCasParticulierementGrave = hasCasParticulierementGrave;
    }

    public boolean isHasConflitCompetence() {
        return hasConflitCompetence;
    }

    public void setHasConflitCompetence(boolean hasConflitCompetence) {
        this.hasConflitCompetence = hasConflitCompetence;
    }

    public boolean isHasEquipeCommuneEnquete() {
        return hasEquipeCommuneEnquete;
    }

    public void setHasEquipeCommuneEnquete(boolean hasEquipeCommuneEnquete) {
        this.hasEquipeCommuneEnquete = hasEquipeCommuneEnquete;
    }

    public boolean isHasLivraisonControlee() {
        return hasLivraisonControlee;
    }

    public void setHasLivraisonControlee(boolean hasLivraisonControlee) {
        this.hasLivraisonControlee = hasLivraisonControlee;
    }

    public boolean isHasDifficulteRefusDemandeEntraide() {
        return hasDifficulteRefusDemandeEntraide;
    }

    public void setHasDifficulteRefusDemandeEntraide(
            boolean hasDifficulteRefusDemandeEntraide) {
        this.hasDifficulteRefusDemandeEntraide = hasDifficulteRefusDemandeEntraide;
    }


}
