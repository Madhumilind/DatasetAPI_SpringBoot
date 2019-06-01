package com.hackerrank.github.repository;

import com.hackerrank.github.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface EventRepository extends JpaRepository<Event, Long> {

    public List<Event> findEventsByActorId(Long id);
}
