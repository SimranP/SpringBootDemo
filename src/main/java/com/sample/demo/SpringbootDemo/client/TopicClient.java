package com.sample.demo.SpringbootDemo.client;

import com.sample.demo.SpringbootDemo.Model.Topic;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

@Component
public class TopicClient {

    private WebClient webClient =
                WebClient
                        .builder()
                        .baseUrl("http://localhost:3000")
                        .build();

    public Flux<Topic> getTopics() {
        return webClient.get().uri("/topics").retrieve().bodyToFlux(Topic.class);
    }

    public Mono<Topic> getTopic(String id) {
        return webClient.get().uri("/topics").retrieve().bodyToFlux(Topic.class)
                .filter(topic -> topic.getId().equals(id))
                .next();
    }

    public Mono<Topic> addTopic(Topic topic) {
        return webClient.post()
                .uri("/topics")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(topic), Topic.class)
                .exchange()
                .flatMap(response -> response.bodyToMono(Topic.class))
                .delayElement(Duration.ofMillis(5000))
                .doOnSuccess(a -> {
                    System.out.println("Something happened " + a);
                })
                .doOnError(a -> {
                    System.out.println("Something broke " + a);
                });
    }

    public Mono<Topic> modifyTopic(String id,Topic topic) {
        return webClient.put()
                .uri("/topics/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(topic), Topic.class)
                .exchange()
                .flatMap(response -> response.bodyToMono(Topic.class))
                .doOnSuccess(a -> {
                    System.out.println("Something happened " + a);
                })
                .doOnError(a -> {
                    System.out.println("Something broke " + a);
                });
    }
}
