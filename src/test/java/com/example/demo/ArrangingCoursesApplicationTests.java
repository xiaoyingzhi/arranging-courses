package com.example.demo;

import com.hut.kwk.ArrangingCoursesApplication;
import com.hut.kwk.service.IArrService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArrangingCoursesApplication.class)
public class ArrangingCoursesApplicationTests {

	@Autowired
	private IArrService iArrService;

	@Test
	public void contextLoads() {
		iArrService.arr();
	}

}
