package de.jcup.code2doc.core.internal.decorate;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import de.jcup.code2doc.api.UseCase;
import de.jcup.code2doc.core.extend.ExampleURLExtension;
import de.jcup.code2doc.core.internal.define.SpecificationImpl;

public class UseCaseExampleURLDecoratorTest {

	

	@Test
	public void test_decoration_works(){
		/* ------- */
		/* prepare */
		/* ------- */
		
		/* @formatter:off*/
		specification.addUseCaseAndDefine(TestUseCase.class).
						addTechInfo().
							addLinkToJava("group", InternalTestEnum.VIEW_CREATE).
							addLinkToJava("group", InternalTestEnum.VIEW_DELETE).
							addLinkToJava("group", String.class);
		/* @formatter:on*/
		UseCase useCase = specification.getDefinition(TestUseCase.class).getElement();
		assertNull(useCase.getExampleURL());
		
		/* ------- */
		/* execute */
		/* ------- */
		
		decorator.decorate(specification);
		
		/* ------- */
		/* test */
		/* ------- */
		assertNotNull(useCase.getExampleURL());
		assertEquals(CREATE_URL, useCase.getExampleURL());
	}
	
	/* -------------------------------- */
	/* Helper parts:*/
	/* -------------------------------- */
	
	private static final String START_URL = "http://go.test.jcup.de";
	private static final String DELETE_URL = "http://terminator.test.jcup.de";
	private static final String CREATE_URL = "http://creator.test.jcup.de";
	
	private UseCaseExampleURLDecorator decorator;
	private ExampleURLExtension extension;
	private SpecificationImpl specification;
	
	@Before
	public void before(){
		specification = new SpecificationImpl();
		
		
		extension = mock(ExampleURLExtension.class);
		decorator = new UseCaseExampleURLDecorator(extension);
		
		when(extension.createExampleURL(InternalTestEnum.VIEW_START)).thenReturn(START_URL);
		when(extension.createExampleURL(InternalTestEnum.VIEW_CREATE)).thenReturn(CREATE_URL);
		when(extension.createExampleURL(InternalTestEnum.VIEW_DELETE)).thenReturn(DELETE_URL);
	}
	
	public static class TestUseCase extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup setup) {
			/* do nothing...*/
		}
		
	}
	
	private enum InternalTestEnum{
		VIEW_START,
		VIEW_CREATE,
		VIEW_DELETE;
	}
}
