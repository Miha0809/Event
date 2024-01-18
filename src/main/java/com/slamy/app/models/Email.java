package com.slamy.app.models;

import lombok.*;

import javax.persistence.*;
import static javax.persistence.GenerationType.SEQUENCE;

@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Email")
@Table(name = "emails")
public class Email {
    @Id
    @SequenceGenerator(
            name = "email_sequence",
            sequenceName = "email_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "email_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

