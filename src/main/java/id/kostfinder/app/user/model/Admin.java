package id.kostfinder.app.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User{
    @Column(name = "last_login")
    private LocalDate lastLogin;
}
