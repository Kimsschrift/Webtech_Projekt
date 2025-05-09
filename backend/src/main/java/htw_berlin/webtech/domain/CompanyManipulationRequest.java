package htw_berlin.webtech.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
 * Diese Klasse dient dazu, beim ersten Registrieren eines Unternehmens auf Karrierhub
 * die Unternehmensinformationen zu erfassen und in der Datenbank zu speichern.
 * **/


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyManipulationRequest {
    private Long id;
    private String name;
    private String address;
    private String legalForm;
    private String website;
    private String contactEmail;

}
