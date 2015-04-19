package de.jcup.code2doc.core.decorate;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.jcup.code2doc.api.Element.MarkupType;
import de.jcup.code2doc.core.internal.util.MarkupSupport;

public class AbstractMarkupTextDecoratorTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	private AbstractMarkupTextDecorator decorator;

	@Test
	public void test_already_registered_type_support_throws_exception(){
		expectedException.expect(IllegalArgumentException.class);

		new AbstractMarkupTextDecorator() {
			
			@Override
			protected void setup(MarkupTextDecoratorSetup setup) {
				setup.register(new TestHTMLMarkupSupport());
				/* register another markup support for HTML: */
				setup.register(new MarkupSupport() {
					
					@Override
					public String handleMarkup(String text) {
						return text;
					}
					
					@Override
					public MarkupType getMarkupType() {
						return MarkupType.HTML;
					}
				});
			}
		};
		
		
	}
	
	@Test
	public void test_registered_support_used(){
		assertEquals("<b>text</b>",decorator.decorate(MarkupType.HTML, "text"));
	}
	
	@Test
	public void test_null_markup_type_throws_exception(){
		expectedException.expect(IllegalArgumentException.class);
		decorator.decorate(null, "text");
	}
	
	@Test
	public void test_null_textmarkup_text_does_NOT_throw_exceptino(){
		decorator.decorate(MarkupType.HTML, null);
	}
	
	@Test
	public void test_registered_support_uses_default_means_html(){
		assertEquals("<b>text</b>",decorator.decorate("text"));
	}
	
	@Before
	public void before(){
		decorator = new AbstractMarkupTextDecorator() {
			
			@Override
			protected void setup(MarkupTextDecoratorSetup setup) {
				setup.register(new TestHTMLMarkupSupport());
			}
		};
	}
	
	private class TestHTMLMarkupSupport implements MarkupSupport{

		@Override
		public MarkupType getMarkupType() {
			return MarkupType.HTML;
		}

		@Override
		public String handleMarkup(String text) {
			return "<b>"+text+"</b>";
		}
		
	}
}
