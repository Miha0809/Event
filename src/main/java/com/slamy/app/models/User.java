package com.slamy.app.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Setter
    @Getter
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Setter
    @Getter
    @Column(name = "age", nullable = false)
    private int age;

    @Setter
    @Getter
    @Column(name = "first_name", nullable = false)
    private String firstName = "";

    @Setter
    @Getter
    @Column(name = "last_name", nullable = false)
    private String lastName = "";

    @Setter
    @Column(name = "password", nullable = false)
    private String password = "";

    @OneToOne
    @JoinColumn(name = "email_id", referencedColumnName = "id")
    private Email email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "events_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> events;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Override
    public String getPassword() {
        return this.password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.getRole().name()));
    }

    @Override
    public String getUsername() {
        return this.email.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
