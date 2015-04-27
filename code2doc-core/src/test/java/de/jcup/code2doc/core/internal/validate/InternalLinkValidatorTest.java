package de.jcup.code2doc.core.internal.validate;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import de.jcup.code2doc.api.UseCase;
import de.jcup.code2doc.core.Factory;
import de.jcup.code2doc.core.define.Specification;
import de.jcup.code2doc.core.validate.ValidationException;
import static de.jcup.code2doc.core.internal.validate.InternalLinksValidator.*;
public class InternalLinkValidatorTest {

	@Test
	public void testFetchUrlFromHTMLWorks(){
		assertTrue(fetchURLsFromHTML(null, "without").isEmpty());
		assertTrue(fetchURLsFromHTML(null, "<a href='code2doc://'></a>").isEmpty());
		
		Collection<String> list = fetchURLsFromHTML(null, "<a href='code2doc://bla1'></a><a href='code2doc://bla2'></a>");
		assertTrue(list.contains("code2doc://bla1"));
		assertTrue(list.contains("code2doc://bla2"));
		assertEquals(2, list.size());
	}
	
	@Test
	public void test_validation_error_when_textcontent_link_not_correct() throws ValidationException{
		specification.addUseCase(InternalTestUseCaseWithInternalLinksNotCorrect1.class);
		try{
			validator.validate(specification);
			fail("no validation exception thrown!");
		}catch(InternalLinkValidationException e){
			/* OK */
		}
	}
	
	@Test
	public void test_validation_error_when_textcontentresource_link_not_correct() throws ValidationException{
		specification.addUseCase(InternalTestUseCaseWithInternalLinksNotCorrect2.class);
		try{
			validator.validate(specification);
			fail("no validation exception thrown!");
		}catch(InternalLinkValidationException e){
			/* OK */
		}
	}
	
	
	@Test
	public void test_validation_error_when_textcontentresource_link_correct_but_not_for_an_element() throws ValidationException{
		specification.addUseCase(InternalTestUseCaseWithInternalLinksNotCorrect3.class);
		try{
			validator.validate(specification);
			fail("no validation exception thrown!");
		}catch(NotAnElementValidationException e){
			/* OK */
		}
	}
	
	@Test
	public void test_validation_no_error_when_textcontent_link_correct() throws ValidationException{
		specification.addUseCase(InternalTestUseCaseWithInternalLinkCorrect1.class);
		validator.validate(specification);
	}
	
	@Test
	public void test_validation_no_error_when_textcontentresource_link_correct() throws ValidationException{
		specification.addUseCase(InternalTestUseCaseWithInternalLinkCorrect2.class);
		validator.validate(specification);
	}
	
	
	
	/* --------------------------------------------------- */
	/* - Helpers - */
	/* --------------------------------------------------- */
	private InternalLinksValidator validator;
	private Specification specification;
	
	@Before
	public void before(){
		specification = Factory.createEmptySpecification();
		validator = new InternalLinksValidator();
	}
	
	public static class InternalTestUseCaseWithInternalLinkCorrect1 extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup setup) {
			setup.content().addText("Link to a <a href='code2doc://de.jcup.code2doc.core.internal.validate.InternalLinksAreCorrectValidatorTest.InternalTestUseCaseWithInternalLinkCorrect1'> existing part </a>)");
		}
		
	}
	
	public static class InternalTestUseCaseWithInternalLinksNotCorrect1 extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup setup) {
			setup.content().addText("Link to a <a href='code2doc://not-existing'>not existing part </a>)");
		}
		
	}
	
	public static class InternalTestUseCaseWithInternalLinkCorrect2 extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup setup) {
			setup.content().addTextResource("/de/jcup/code2doc/core/validation/textcontent_resource_with_valid_link.txt");
		}
		
	}
	
	public static class InternalTestUseCaseWithInternalLinksNotCorrect2 extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup setup) {
			setup.content().addTextResource("/de/jcup/code2doc/core/validation/textcontent_resource_with_invalid_link.txt");
		}
		
	}
	
	public static class InternalTestUseCaseWithInternalLinksNotCorrect3 extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup setup) {
			setup.content().addTextResource("/de/jcup/code2doc/core/validation/textcontent_resource_with_invalid_link_existing_but_not_a_element.txt");
		}
		
	}
	
	
}
