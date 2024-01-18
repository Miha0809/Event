package com.slamy.app.repositories;

import com.slamy.app.models.Email;
import com.slamy.app.models.Event;
import com.slamy.app.models.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
     List<Event> getEventsBy();
     Event getEventById(Long id);
     List<Event> findByUsersContains(User user);
     List<Event> findAllBy();
}
