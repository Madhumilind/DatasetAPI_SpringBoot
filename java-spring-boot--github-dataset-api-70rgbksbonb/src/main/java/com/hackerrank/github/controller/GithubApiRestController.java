package com.hackerrank.github.controller;


import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.util.stream.Collectors.toMap;

@RestController
public class GithubApiRestController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ActorRepository actorRepository;

    @RequestMapping(method = RequestMethod.POST, name = "/events")
    public void postEvents(@RequestBody Event event) {

            eventRepository.save(event);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/events")
    @ResponseBody
    public List<Event> getEvents() {

        return eventRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/erase")
    public void deleteEvents() {
        eventRepository.deleteAll();
    }

    @RequestMapping(method = RequestMethod.GET, value= "/events/actors/{actorId}")
    public List<Actor> getActorsById(@PathVariable(name="actorId")Long actorId) {
        return actorRepository.getActorsById(actorId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/actors/streak")
    @ResponseBody
    public List<Actor> getActorStreaks() {
        List<Actor> actors = actorRepository.findAll();

        actors.sort(new Comparator<Actor>() {
            @Override
            public int compare(Actor o1, Actor o2) {
                int result = 0;
                List<Event> events1 = eventRepository.findEventsByActorId(o1.getId());
                events1.sort(new Comparator<Event>() {
                    @Override
                    public int compare(Event o1, Event o2) {
                        return o1.getCreatedAt().compareTo(o2.getCreatedAt());

                    }
                });
                List<Event> events2 = eventRepository.findEventsByActorId(o2.getId());
                events2.sort(new Comparator<Event>() {
                    @Override
                    public int compare(Event o1, Event o2) {
                        return o1.getCreatedAt().compareTo(o2.getCreatedAt());

                    }
                });

                if(result == 0) {

                    Event o1LatestEvent = events1.get(events1.size() - 1);
                    Event o2LatestEvent = events2.get(events2.size() - 1);
                    result = o2LatestEvent.getCreatedAt().compareTo(o1LatestEvent.getCreatedAt());

                }

                if(result == 0) {
                    result = o1.getLogin().compareTo(o2.getLogin());
                }

                return result;
            }
        });


        return actors;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/actors")
    @ResponseBody
    public List<Actor> getActors() {

        List<Actor> actors = actorRepository.findAll();

        actors.sort(new Comparator<Actor>() {
            @Override
            public int compare(Actor o1, Actor o2) {
                int result = 0;
                List<Event> events1 = eventRepository.findEventsByActorId(o1.getId());
                events1.sort(new Comparator<Event>() {
                    @Override
                    public int compare(Event o1, Event o2) {
                         return o1.getCreatedAt().compareTo(o2.getCreatedAt());

                    }
                });
                List<Event> events2 = eventRepository.findEventsByActorId(o2.getId());
                events2.sort(new Comparator<Event>() {
                    @Override
                    public int compare(Event o1, Event o2) {
                        return o1.getCreatedAt().compareTo(o2.getCreatedAt());

                    }
                });

                if(events1.size() < events2.size()) {
                    result = 1;
                } else if(events1.size() > events2.size()) {
                    result = -1;
                } else {
                    result = 0;
                }

                if(result == 0) {

                    Event o1LatestEvent = events1.get(events1.size() - 1);
                    Event o2LatestEvent = events2.get(events2.size() - 1);
                    result = o2LatestEvent.getCreatedAt().compareTo(o1LatestEvent.getCreatedAt());

                }

                if(result == 0) {
                    result = o1.getLogin().compareTo(o2.getLogin());
                }

                return result;
            }
        });

        return actors;

    }

    @RequestMapping(method = RequestMethod.PUT, name = "/actors")
    public void updateActor(@RequestBody Actor actor) {

        actorRepository.save(actor);
    }
}
