package org.simulatest.springrunner;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.simulatest.environment.annotation.UseEnvironment;
import org.simulatest.springrunner.junit.EnvironmentSpringRunner;
import org.simulatest.springrunner.spring.example.LanguageTeacher;
import org.simulatest.springrunner.spring.example.SpringChildExampleEnvironment;
import org.simulatest.springrunner.spring.mock.DatabaseMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@RunWith(EnvironmentSpringRunner.class)
@UseEnvironment(SpringChildExampleEnvironment.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class SimpleSpringTest {
	
	@Autowired
	LanguageTeacher languageTeacher;
	
	@Test
	public void simpleSpringDITest() {
		String expected = "Hello";
		Assert.assertEquals(expected, languageTeacher.sayHello());
	}
	
	@Test
	public void environmentsTest() {
		Assert.assertEquals(2, DatabaseMock.getMessages().size());
		Assert.assertEquals(DatabaseMock.getMessages().get(0), "Hello");
		Assert.assertEquals(DatabaseMock.getMessages().get(1), "Hello by child");
		
		DatabaseMock.reseta();
		
		Assert.assertEquals(0, DatabaseMock.getMessages().size());
	}

}