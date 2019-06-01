package com.hackerrank.github.repository;

import com.hackerrank.github.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ActorRepository extends JpaRepository<Actor, Long> {

   // public List<Actor> getActorsById(final String actorId);
   public List<Actor> getActorsById(long actorId);
}
