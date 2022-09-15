package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.gouv.justice.cassiopee.service.editiontype.service.enumeration.EditionTypeEnum;
import fr.gouv.justice.cassiopee.service.editiontype.service.enumeration.TypeDestinataireEditionSansEvtEnum;

/**
 * @author jai
 */
public class EditionSansEvenementType extends EditionType {

    /**
     *
     */
    private static final long serialVersionUID = 4859813024920672555L;

    private String typeDestinataireCode;
    private Set<String> typeDestinataireCodes;

    /**
     * @return
     */
    public TypeDestinataireEditionSansEvtEnum getTypeDestinataireEditionSansEvtEnum() {
        return getTypeDestinataireEditionSansEvtEnum(this.typeDestinataireCode);
    }

    private TypeDestinataireEditionSansEvtEnum getTypeDestinataireEditionSansEvtEnum(String typeDestinataireCode) {
        if (TypeDestinataireEditionSansEvtEnum.ActeurInterne.getCode().equals(typeDestinataireCode)) {
            return TypeDestinataireEditionSansEvtEnum.ActeurInterne;
        }
        if (TypeDestinataireEditionSansEvtEnum.AutreDestinataire.getCode().equals(typeDestinataireCode)) {
            return TypeDestinataireEditionSansEvtEnum.AutreDestinataire;
        }
        if (TypeDestinataireEditionSansEvtEnum.Avocat.getCode().equals(typeDestinataireCode)) {
            return TypeDestinataireEditionSansEvtEnum.Avocat;
        }
        if (TypeDestinataireEditionSansEvtEnum.ElementStructure.getCode().equals(typeDestinataireCode)) {
            return TypeDestinataireEditionSansEvtEnum.ElementStructure;
        }
        if (TypeDestinataireEditionSansEvtEnum.PersonneAffaire.getCode().equals(typeDestinataireCode)) {
            return TypeDestinataireEditionSansEvtEnum.PersonneAffaire;
        }
        if (TypeDestinataireEditionSansEvtEnum.PersonneQualifiee.getCode().equals(typeDestinataireCode)) {
            return TypeDestinataireEditionSansEvtEnum.PersonneQualifiee;
        }
        return TypeDestinataireEditionSansEvtEnum.AutreDestinataire;
    }

    /**
     * @return List<TypeDestinataireEditionSansEvtEnum>
     */
    public List<TypeDestinataireEditionSansEvtEnum> getTypeDestinatairesEditionSansEvtEnum() {
        List<TypeDestinataireEditionSansEvtEnum> result = new ArrayList<>();
        for (String codeTypeDestinataireEditionSansEvt : getTypeDestinataireCodes()) {
            result.add(getTypeDestinataireEditionSansEvtEnum(codeTypeDestinataireEditionSansEvt));
        }
        return result;
    }

    /**
     * @param listTypeDestinataireEditionSansEvtEnum
     */
    public void setTypeDestinatairesEditionSansEvtEnum(
            List<TypeDestinataireEditionSansEvtEnum> listTypeDestinataireEditionSansEvtEnum) {
        Set<String> setTypeDestinataireCodes = new HashSet<>();
        for (TypeDestinataireEditionSansEvtEnum typeDestinataireEditionSansEvtEnum : listTypeDestinataireEditionSansEvtEnum) {
            setTypeDestinataireCodes.add(typeDestinataireEditionSansEvtEnum.getCode());
        }
        setTypeDestinataireCodes(setTypeDestinataireCodes);

    }

    /**
     * @param typeDestinataireEditionSansEvtEnum
     */
    public void setTypeDestinataireEditionSansEvtEnum(
            TypeDestinataireEditionSansEvtEnum typeDestinataireEditionSansEvtEnum) {
        this.typeDestinataireCode = typeDestinataireEditionSansEvtEnum.getCode();
    }

    public void setTypeDestinataireCode(String typeDestinataireCode) {
        this.typeDestinataireCode = typeDestinataireCode;
    }

    protected String getTypeDestinataireCode() {
        return typeDestinataireCode;
    }

    /**
     * @return Returns the typeDestinataireCodes.
     */
    public Set<String> getTypeDestinataireCodes() {
        return typeDestinataireCodes;
    }

    /**
     * @param typeDestinataireCodes
     *            The typeDestinataireCodes to set.
     */
    protected void setTypeDestinataireCodes(Set<String> typeDestinataireCodes) {
        this.typeDestinataireCodes = typeDestinataireCodes;
    }

    @Override
    public EditionTypeEnum getEditionTypeEnum() {
        return EditionTypeEnum.NonEvenement;
    }

}
