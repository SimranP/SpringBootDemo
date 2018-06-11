package com.sample.demo.SpringbootDemo.Controller;

import com.sample.demo.SpringbootDemo.Model.Topic;
import com.sample.demo.SpringbootDemo.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/demo")
public class TopicController {

    @Autowired
    TopicService topicService;

    @GetMapping("/topics")
    public Flux<Topic> getAllTopics() {
        System.out.println("coming here");
        return topicService.getAllTopics();
    }

    @GetMapping("/topics/{id}")
    @ResponseBody
    public Mono<ResponseEntity<Topic>> getTopicById(@PathVariable(value="id") String topicId) {
        return topicService.getTopic(topicId)
                .map(topic -> ResponseEntity.ok(topic))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/topics")
    public void createTopic(@RequestBody Topic topic) {
        topicService.addTopic(topic);
    }

    @PutMapping("/topics/{id}")
    public Mono<Topic> updateTopic(@PathVariable(value="id") String topicId, @Valid @RequestBody Topic topic) {
        return topicService.modifyTopic(topicId, topic);
    }
//
//    @GetMapping(value = "/stream/topics", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<Topic> streamAllTopics() {
//        return topicService.getAllTopics();
//    }

}
