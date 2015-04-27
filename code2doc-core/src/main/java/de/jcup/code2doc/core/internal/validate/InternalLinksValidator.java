/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.*/
package de.jcup.code2doc.core.internal.validate;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.jcup.code2doc.api.ContentElement;
import de.jcup.code2doc.api.Element;
import de.jcup.code2doc.api.Element.Content;
import de.jcup.code2doc.api.Element.LinkToURLContent;
import de.jcup.code2doc.api.Element.MarkupType;
import de.jcup.code2doc.api.Element.TextContent;
import de.jcup.code2doc.api.Element.TextContentResource;
import de.jcup.code2doc.core.internal.define.AbstractElementDefinitionImpl;
import de.jcup.code2doc.core.internal.define.SpecificationImpl;
import de.jcup.code2doc.core.internal.util.Transformer;
import de.jcup.code2doc.core.internal.util.Validation;
import de.jcup.code2doc.core.validate.ValidationException;

public class InternalLinksValidator extends AbstractSpecificationImplValidator {

	private static final String CODE2DOC = "code2doc://";
	private Transformer transformer = new Transformer();
	/*
	 * TODO ATR, 27.04.2015: this class should use code of html support. Think
	 * about html parser (in html support) instead of using always regexp!
	 */
	private static Pattern htmlLinkPattern = Pattern.compile("(code2doc://.+?)\\'", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

	@SuppressWarnings("rawtypes")
	@Override
	protected void validateImpl(SpecificationImpl specification) throws ValidationException {
		Collection<AbstractElementDefinitionImpl<?, Element, ?>> allDefinitions = specification.getDefinitions();
		for (AbstractElementDefinitionImpl elementDef : allDefinitions) {
			Element element = elementDef.getElement();
			if (element instanceof ContentElement) {
				ContentElement ce = (ContentElement) element;
				assertHasContentWithValidInternalLinks(ce);
			}
		}
	}

	private void assertHasContentWithValidInternalLinks(ContentElement ce) throws ValidationException {
		Set<Locale> locales = ce.getDefinedLocales();
		for (Locale locale : locales) {
			assertHasContentWithValidInternalLinks(ce, ce.getContent(locale));
		}
	}

	private void assertHasContentWithValidInternalLinks(ContentElement ce, Content content) throws ValidationException {
		List<Content> children = content.getChildren();
		/* check children... */
		if (!children.isEmpty()) {
			for (Content child : children) {
				assertHasContentWithValidInternalLinks(ce, child);
			}
		}
		/* check this */
		if (content instanceof LinkToURLContent) {
			LinkToURLContent l2uc = (LinkToURLContent) content;
			String url = l2uc.getUrl();
			assertIfURLInternalThenValid(ce, url);

		} else if (content instanceof TextContent) {
			TextContent textContent = (TextContent) content;
			assertTextContainsNoInvalidURLs(ce, textContent.getType(), textContent.getText());

		} else if (content instanceof TextContentResource) {
			TextContentResource textContentResource = (TextContentResource) content;
			String text = transformer.transformToStringContent(textContentResource.getResourcePath());
			if (text == null) {
				/*
				 * is not handled by this validator normally - so this is not a
				 * validation error but an illegal state!
				 */
				throw new IllegalStateException("At resource path:" + textContentResource.getResourcePath() + " no text resource can be found!\nWas defined at:"
						+ ce.getClass().getName());
			}
			assertTextContainsNoInvalidURLs(ce, textContentResource.getType(), text);
		}

	}

	private void assertTextContainsNoInvalidURLs(ContentElement ce, MarkupType markupType, String text) throws ValidationException {
		switch (markupType) {
		/* currently only html markup is supported */
		case HTML:
			/*
			 * example for correct url in html markup: <a
			 * href='code2doc://de.jcup.testdat.UseCase1'>linktext</a>
			 */
			Collection<String> urls = fetchURLsFromHTML(ce, text);
			for (String url : urls) {
				assertIfURLInternalThenValid(ce, url);
			}
			break;
		default:
			throw new IllegalArgumentException("markup type:" + markupType + " is not supported by this validator!");
		}

	}

	static Collection<String> fetchURLsFromHTML(ContentElement ce, String text) {
		Validation.notNull(text, "Text may not be null!");
		Set<String> urls = new HashSet<String>();
		Matcher m = htmlLinkPattern.matcher(text);
		while (m.find()) {
			urls.add(m.group(1));
		}
		return urls;
	}

	private void assertIfURLInternalThenValid(ContentElement ce, String url) throws ValidationException {
		if (url == null) {
			return;
		}
		if (url.startsWith(CODE2DOC)) {
			/* e.g. code2doc://de.jcup.testdat.UseCase1 */
			String urlTarget = url.substring(CODE2DOC.length());
			Class<?> clazz = transformer.transformToClass(urlTarget);
			if (clazz == null) {
				throw new InternalLinkValidationException("Illegal internal link - no class found for: '" + urlTarget + "'\nURL was:" + url);
			}
			boolean isElement = Element.class.isAssignableFrom(clazz);
			if (!isElement) {
				throw new NotAnElementValidationException(clazz);
			}
		}
	}

}