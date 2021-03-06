package org.simulatest.environment.test.infra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.simulatest.environment.annotation.UseEnvironment;
import org.simulatest.environment.environment.BigBangEnvironment;
import org.simulatest.environment.environment.Environment;
import org.simulatest.environment.infra.AnnotationUtils;
import org.simulatest.environment.test.testdouble.Environments.EmpresaEnvironment;
import org.simulatest.environment.test.testdouble.Environments.PessoaEnvironment;
import org.simulatest.environment.test.testdouble.Environments.PessoaFisicaEnvironment;
import org.simulatest.environment.test.testdouble.Environments.PessoaJuridicaEnvironment;
import org.simulatest.environment.test.testdouble.Environments.Root;

public class AnnotationUtilsTest {
	
	@Test
	public void extractEnvironmentTest() {
		assertEnvironmentEquals(BigBangEnvironment.class, Root.class);
		assertEnvironmentEquals(PessoaFisicaEnvironment.class, PessoaFisicaTest.class);
		assertEnvironmentEquals(PessoaFisicaEnvironment.class, UsuarioTest.class);
		assertEnvironmentEquals(EmpresaEnvironment.class, EmpresaTest.class);
		assertNull(AnnotationUtils.extractEnvironment(BigBangEnvironment.class));
	}
	
	@Test
	public void extractEnvironmentParentTest() {
		assertParentEnvirontmentEquals(Root.class, PessoaEnvironment.class);
		assertParentEnvirontmentEquals(PessoaEnvironment.class, PessoaFisicaEnvironment.class);
		assertParentEnvirontmentEquals(PessoaEnvironment.class, PessoaJuridicaEnvironment.class);
		assertParentEnvirontmentEquals(BigBangEnvironment.class, Root.class);
	}
	
	private void assertEnvironmentEquals(Class<?> expectedEnvironment, Class<?> clazz) {
		assertEquals(expectedEnvironment, AnnotationUtils.extractEnvironment(clazz));
	}
	
	private void assertParentEnvirontmentEquals(Class<?> expectedEnvironment, Class<? extends Environment> clazz) {
		assertEquals(expectedEnvironment, AnnotationUtils.extractEnvironmentParent(clazz));
	}
	
	@UseEnvironment(PessoaFisicaEnvironment.class)
	class PessoaFisicaTest { }
	
	@UseEnvironment(PessoaFisicaEnvironment.class)
	class UsuarioTest { }
	
	@UseEnvironment(EmpresaEnvironment.class)
	class EmpresaTest { }
	
}