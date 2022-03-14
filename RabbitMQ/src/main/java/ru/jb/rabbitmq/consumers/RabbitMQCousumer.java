package ru.jb.rabbitmq.consumers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import ru.jb.rabbitmq.entities.Person;

@Service
public class RabbitMQCousumer {
	
	/*
	 * @RabbitListener(queues = "Mobile") public void getMessage(Person person) {
	 * System.out.println(person.getName());
	 * 
	 * }
	 */

	 @RabbitListener(queues = {"Mobile","TV"}) 
	 public void getMessage(byte[] message) throws ClassNotFoundException, IOException {
	
		 ByteArrayInputStream bis = new ByteArrayInputStream(message);
		 ObjectInput in = new ObjectInputStream(bis);
		 Person p = (Person) in.readObject();
		 in.close();
		 bis.close();
		 
	 System.out.println(p.getName());
	 
	 }

	private ObjectOutput ObjectOutputStream(Object flush) {
		// TODO Auto-generated method stub
		return null;
	}
}
