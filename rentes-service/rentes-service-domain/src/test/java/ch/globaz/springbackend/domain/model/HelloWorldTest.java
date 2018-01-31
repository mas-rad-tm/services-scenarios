package ch.globaz.springbackend.domain.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class HelloWorldTest {

	@Test
	public void assertIfBuilderworkCorrectly () {

		HelloWorld hw = HelloWorld.builder("hello","world");
		assertThat(hw).isNotNull();
		assertThat(hw.hello()).isNotNull();
		assertThat(hw.world()).isNotNull();
	}

}