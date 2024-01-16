package com.slamy.app.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;


@Entity(name = "Event")
@Table(name = "Events")
public class Event {
    @Id
    @Setter
    @Getter
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "event_sequence"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Setter
    @Getter
    @Column(name = "name", nullable = false)
    private String name;

    @Setter
    @Getter
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    @Column(name = "date_time_begin", nullable = false)
    private Date dateTimeBegin;

    @Setter
    @Getter
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    @Column(name = "date_time_end", nullable = false)
    private Date dateTimeEnd;

    @JsonIgnore
    @ManyToMany(mappedBy = "events", fetch = FetchType.EAGER)
    private List<User> users;

//    @OneToMany(mappedBy = "event")
//    private List<EventsUsers> eventsUsers;


    public void addUser(User user) {
        this.users.add(user);
    }

    public List<User> getUsers() {
        return this.users;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateTimeBegin=" + dateTimeBegin +
                ", dateTimeEnd=" + dateTimeEnd +
                ", users=" + users +
                '}';
    }
}
