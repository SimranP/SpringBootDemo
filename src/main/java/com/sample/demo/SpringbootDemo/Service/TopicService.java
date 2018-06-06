package com.sample.demo.SpringbootDemo.Service;

import com.sample.demo.SpringbootDemo.Model.Topic;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicService {
    private Flux<Topic> topics = Flux.fromIterable(Arrays.asList(
            new Topic("spring", "Spring Framework", "Spring Framework"),
            new Topic("java", "Core Java", "Core Java Descr"),
            new Topic("javascript", "Javascript", "Javascript Descr")
    ));

    public Flux<Topic> getAllTopics() {
        return topics;
    }

    public Mono<Topic> getTopic(String id) {
        return topics.filter(topic -> topic.getId().equals(id))
                .next();
    }

    public Mono<Topic> addTopic(Topic topic) {
        Mono<Topic> newTopic = Mono.just(topic);
        topics = topics.concatWith(newTopic);
        return newTopic;
    }
}