package de.jcup.code2doc.core.internal.decorate;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jcup.code2doc.api.InternalAccess;
import de.jcup.code2doc.api.UseCase;
import de.jcup.code2doc.core.define.TechInfoDefinition;
import de.jcup.code2doc.core.extend.ExampleURLExtension;
import de.jcup.code2doc.core.internal.define.AbstractTechnicalDefinitionImpl;
import de.jcup.code2doc.core.internal.define.DefinitionType;
import de.jcup.code2doc.core.internal.define.DummyTechInfoDefinitionImpl;
import de.jcup.code2doc.core.internal.define.AbstractElementDefinitionImpl;
import de.jcup.code2doc.core.internal.define.GroupDefinitionImpl;
import de.jcup.code2doc.core.internal.define.SpecificationImpl;
import de.jcup.code2doc.core.internal.util.Validation;

/* we use internal access so "deprecation" is well known and accepted */
@SuppressWarnings("deprecation")
public class UseCaseExampleURLDecorator extends AbstractSpecificationImplDecorator {

	private ExampleURLExtension extension;

	private static final Logger LOG = LoggerFactory.getLogger(UseCaseExampleURLDecorator.class);

	public UseCaseExampleURLDecorator(ExampleURLExtension extension) {
		Validation.notNull(extension, "extension must be NOT null!");
		this.extension = extension;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	protected void decorateImpl(SpecificationImpl specificationImpl) {
		for (GroupDefinitionImpl groupDefImpl : specificationImpl.getGroupDefinitions()) {
			List<? extends AbstractElementDefinitionImpl<?, ?, ?>> useCaseDefinitions = groupDefImpl.getDefinitions(DefinitionType.USECASE);
			for (AbstractElementDefinitionImpl<?, ?, ?> useCaseDef : useCaseDefinitions) {
				UseCase useCase = (UseCase) useCaseDef.getElement();
				String exampleURL = null;
				for (TechInfoDefinition<?> usecaseTechInfo : useCaseDef.getTechnicalDefinitions()) {
					if (exampleURL != null) {
						break;
					}
					if (usecaseTechInfo == null) {
						continue;
					}
					if (usecaseTechInfo instanceof AbstractTechnicalDefinitionImpl) {
						AbstractTechnicalDefinitionImpl auti = (AbstractTechnicalDefinitionImpl) usecaseTechInfo;
						Map<String, Collection<Object>> linksCombined = auti.getLinksCombined();
						for (String key : linksCombined.keySet()) {
							if (exampleURL != null) {
								break;
							}
							Collection<Object> data = linksCombined.get(key);
							for (Object obj : data) {
								exampleURL = extension.createExampleURL(obj);
								if (exampleURL != null) {
									break;
								}
							}
						}
					} else if (usecaseTechInfo instanceof DummyTechInfoDefinitionImpl) {
						if (LOG.isDebugEnabled()) {
							LOG.debug("Dummy tech info found at use case:" + useCase);
						}
						;
					} else {
						throw new IllegalStateException("Unsupported tech info found:" + usecaseTechInfo);
					}

				}
				/* */
				if (exampleURL!=null){
					InternalAccess.setExampleURL(useCase, exampleURL);
				}
			}
		}
	}
}
