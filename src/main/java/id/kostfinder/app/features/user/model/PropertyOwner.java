package id.kostfinder.app.features.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PROPERTY_OWNER")
public class PropertyOwner extends User {
    @Column(name = "business_name")
    private String businessName;
    private String address;
    @Column(name = "is_verified")
    private Boolean isVerified;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "bank_account_number")
    private String bankAccountNumber;
}
