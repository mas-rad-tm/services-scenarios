package ch.globaz.tmmas.personnes.domain.model;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(exclude={"id"})
public class HelloWorld {

	private String hello;
	private String world;


	private HelloWorld(String hello, String world) {
		this.world = world;
		this.hello = hello;
	}

	public HelloWorld(){}

	public String hello() {
		return this.hello;
	}

	public String world() {
		return this.world;
	}

	public static HelloWorld builder(String hello, String world) {
		return new HelloWorld(hello, world);
	}

	//persistance
	private Long id;

	public void id(Long id){
		this.id = id;
	}

}
