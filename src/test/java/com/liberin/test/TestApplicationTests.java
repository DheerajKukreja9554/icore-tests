package com.liberin.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads() {


	}

	@Test
	void random(){
		StringBuilder st = new StringBuilder("dheeraj");
		System.out.println(st.substring(6));

	}

}
