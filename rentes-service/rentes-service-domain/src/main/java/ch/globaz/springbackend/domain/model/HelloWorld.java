package ch.globaz.springbackend.domain.model;

public class HelloWorld {

	private String hello;
	private String world;
	private Long id;

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

}
