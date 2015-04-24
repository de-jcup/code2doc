package de.jcup.code2doc.core.internal.define;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import de.jcup.code2doc.api.UseCase;
import de.jcup.code2doc.core.define.TechInfoDefinition;
import de.jcup.code2doc.testdata.TestUseCases.UC_1__SHOW_ENTRIES;

public class AbstractElementDefinitionImplTest {

	@Test
	public void test_sorting() {
		elementDefinition.addTechInfo("group2");
		elementDefinition.addTechInfo();
		elementDefinition.addTechInfo("group1");
		elementDefinition.addTechInfo("group5");

		Collection<TechInfoDefinition<Object>> techInfos = elementDefinition.getTechnicalDefinitions();
		assertEquals(4, techInfos.size());

		Iterator<TechInfoDefinition<Object>> it = techInfos.iterator();
		assertNextElementHasHeadline(it, null);
		assertNextElementHasHeadline(it, "group1");
		assertNextElementHasHeadline(it, "group2");
		assertNextElementHasHeadline(it, "group5");
	}

	/*------------ */
	/* - Helpers - */
	/*------------ */
	private TestElementDefinitionImpl elementDefinition;
	public UseCase testUseCase;

	@Before
	public void before() {
		testUseCase= new UC_1__SHOW_ENTRIES();
		elementDefinition = new TestElementDefinitionImpl();
	}

	private void assertNextElementHasHeadline(Iterator<TechInfoDefinition<Object>> it, String group) {
		TechInfoDefinition<Object> def = it.next();
		if (!(def instanceof AbstractTechnicalDefinitionImpl)) {
			throw new IllegalArgumentException("test wrong implmeented");
		}
		@SuppressWarnings("rawtypes")
		AbstractTechnicalDefinitionImpl impl = (AbstractTechnicalDefinitionImpl) def;
		String headlineFound = impl.getHeadline();
		/* internally the element definition builds headline always in following way:*/
		String expectedHeadline2 = testUseCase.getHeadline();
		if (group!=null){
			expectedHeadline2+=" ("+group+")";
		}
		/* check */
		assertEquals(expectedHeadline2, headlineFound);

	}

	private class TestElementDefinitionImpl extends AbstractElementDefinitionImpl<Object, UseCase, Object> {

		TestElementDefinitionImpl() {
			super(null, null);
			this.element = testUseCase;
		}

		@Override
		public DefinitionType getDefinitionType() {
			return DefinitionType.ARCHITECTURE;
		}

		@Override
		protected TechInfoDefinition<Object> createNewTechnicalDefinition(String text) {
			return new AbstractTechnicalDefinitionImpl<Object>(this, text) {
			};
		}

	};

}
