package com.sample.demo.SpringbootDemo.Service;

import com.sample.demo.SpringbootDemo.Model.Topic;
import com.sample.demo.SpringbootDemo.client.TopicClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
public class TopicService {
    @Autowired
    TopicClient topicClient;

    public Flux<Topic> getAllTopics() {
        return topicClient.getTopics();
    }

    public Mono<Topic> getTopic(String id) {
        return  topicClient.getTopic(id);
    }

    public Mono<Topic> addTopic(Topic topic) {
        return topicClient.addTopic(topic);
    }

    public Mono<Topic> modifyTopic(String id, @Valid Topic topic) {
        return topicClient.modifyTopic(id, topic);
    }
}