package com.jb.rabbitmq.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jb.rabbitmq.entities.Person;

@RestController
@RequestMapping("/api/v1")
public class TestController {

	@Autowired
	RabbitTemplate rabbitTemplate;

	/*
	 * @GetMapping("/test/{name}") public String testAPI(@PathVariable("name")
	 * String name) { Person person1 = new Person(1L, name);
	 * rabbitTemplate.convertAndSend("Mobile", person1);
	 * rabbitTemplate.convertAndSend("Direct-Exchange", "mobile", person1);
	 * rabbitTemplate.convertAndSend("Fanout-Exchange", "", person1);
	 * rabbitTemplate.convertAndSend("Topic-Exchange", "tv.mobile.ac", person1);
	 * return "Success!"; }
	 */

	  @GetMapping("/test/{name}")
	  public String testAPI(@PathVariable("name") String name) throws IOException { 
		  Person person1 = new Person(1L, name);
		  ByteArrayOutputStream bos = new ByteArrayOutputStream();
		  ObjectOutput out = new ObjectOutputStream(bos);
		  out.writeObject(person1);
		  out.flush();
		  out.close();
		  byte[] byteMessage = bos.toByteArray();
		  bos.close();
		  Message message = MessageBuilder.withBody(byteMessage)
				  .setHeader("item1", "mobile")
				  .setHeader("item2", "television")
				  .build();
		  
		  rabbitTemplate.convertAndSend("Headers-Exchange", null, message);
		  return "Success!"; }
	 
}
