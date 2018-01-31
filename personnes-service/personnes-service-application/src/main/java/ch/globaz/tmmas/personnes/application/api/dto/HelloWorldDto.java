package ch.globaz.tmmas.personnes.application.api.dto;

import ch.globaz.tmmas.personnes.domain.model.HelloWorld;

public class HelloWorldDto {

	private String hello = "Bonjour";
	private String world = "Le monde";

	public static HelloWorldDto sayHello(String hello){
		return new HelloWorldDto(hello);
	}

	public HelloWorldDto(){}

	private HelloWorldDto(String hello){
		this.hello = hello;
	}
	private HelloWorldDto(String hello, String world){

		this.hello = hello;
		this.world = world;
	}


	public String getHello() {

		return hello;
	}

	public String getWorld() {
		return world;
	}

	public static HelloWorldDto fromEntity(HelloWorld helloWorld){
		return new HelloWorldDto(helloWorld.hello(), helloWorld.world());
	}
}
