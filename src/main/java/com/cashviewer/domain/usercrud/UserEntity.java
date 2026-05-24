package com.cashviewer.domain.usercrud;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;

import static com.cashviewer.infrastructure.api.validation.ValidationConstants.PASSWORD_MAX_SIZE;
import static com.cashviewer.infrastructure.api.validation.ValidationConstants.PASSWORD_MIN_SIZE;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "users_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "users_id_seq",
            sequenceName = "users_id_seq",
            allocationSize = 1
    )
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank
    @Size(min = PASSWORD_MIN_SIZE, max = PASSWORD_MAX_SIZE)
    private String password;

    private boolean enabled = true;

    private Collection<String> authorities = new HashSet<>();

    public UserEntity(final Collection<String> authorities, final boolean enabled, final String password, final String email, final String username) {
        this.authorities = authorities;
        this.enabled = enabled;
        this.password = password;
        this.email = email;
        this.username = username;
    }
}
