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
package de.jcup.code2doc.renderer.docbook;

import static de.jcup.code2doc.core.internal.util.Validation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import net.sf.xslthl.Config;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.google.code.docbook4j.Docbook4JException;
import com.google.code.docbook4j.renderer.PDFRenderer;
import com.google.common.io.Files;

import de.jcup.code2doc.core.define.Specification;
import de.jcup.code2doc.core.render.AbstractRenderer;
import de.jcup.code2doc.generator.docbook.XMLSpecificationFileGenerator;

public class PDFSpecificationRenderer extends AbstractRenderer<File> {

	private Specification spec;
	private File outputFile;

	public PDFSpecificationRenderer(Specification spec) {
		this(spec, new File(Files.createTempDir(), "pdf-output.pdf"));
	}

	public PDFSpecificationRenderer(Specification spec, File outputFile) {
		notNull(spec, "specification may not be null!");
		notNull(outputFile, "outputFile may not be null!");
		this.spec = spec;
		this.outputFile = outputFile;
	}

	@Override
	public File render() throws IOException {
		if (outputFile.exists()) {
			FileUtils.forceDelete(outputFile);

		}
		/* +++++++++++++++++++++++++++++++++++++++++++++++++ */
		/* Create XML */
		/* +++++++++++++++++++++++++++++++++++++++++++++++++ */
		String property = System.getProperty("code2doc.renderer.docbook.keep_temporary_files");
		boolean deleteTempFilesOnExit = property == null;

		XMLSpecificationFileGenerator xmlFileGEno = new XMLSpecificationFileGenerator();
		xmlFileGEno.setFilter(getFilter());
		xmlFileGEno.setOutputFileParentFolder(outputFile.getParentFile());
		xmlFileGEno.setOutputFileName(outputFile.getName());
		xmlFileGEno.setOutputFileDeleteOnExit(deleteTempFilesOnExit);

		File xmlFile = xmlFileGEno.generate(spec);

		/* +++++++++++++++++++++++++++++++++++++++++++++++++ */
		/* Prepare PDF rendering */
		/* +++++++++++++++++++++++++++++++++++++++++++++++++ */
		PDFRenderer pdfRenderer = PDFRenderer.create("file:" + xmlFile.getAbsolutePath());
		pdfRenderer.parameter("toc.section.depth", "8");
		pdfRenderer.parameter("chapter.autolabel", "1");
		pdfRenderer.parameter("section.autolabel", "1");
		/*
		 * http://docbook.sourceforge.net/release/xsl/current/doc/fo/page.width.
		 * portrait.html
		 */
		pdfRenderer.parameter("paper.type", "A4");

		/*
		 * http://www.sagehill.net/docbookxsl/SectionNumbering.html#
		 * HTMLDepthSectionNumbers
		 */
		pdfRenderer.parameter("section.autolabel.max.depth", "8");

		/* http://www.sagehill.net/docbookxsl/XrefPageNums.html */
		pdfRenderer.parameter("insert.xref.page.number", "yes");

		/* ------------------------- */
		/* Source code highlighting */
		/* ------------------------- */
		// http://www.sagehill.net/docbookxsl/SyntaxHighlighting.html
		pdfRenderer.parameter("highlight.source", "1");
		// http://sourceforge.net/p/xslthl/wiki/Usage/#xalan-27
		// http://sourceforge.net/p/xslthl/wiki/Xslthl%20Configuration/
		// works: System.setProperty(Config.CONFIG_PROPERTY,
		// "file:///C:/Temp/docbook/highlighting/xslthl-config.xml");

		/* create FOP table of contents inside PDF too */
		pdfRenderer.parameter("fop1.extensions", "1"); /*
														 * 'fop.extensions' did
														 * NOT work!
														 */
		pdfRenderer.parameter("ulink.show", "0");

		URL config = getClass().getResource("/xsl/docbook/highlighting/xslthl-config.xml");
		if (config == null) {
			throw new IllegalStateException("hightlight config not found! Must be inside docbook4j!");
		}
		System.setProperty(Config.CONFIG_PROPERTY, config.toExternalForm());

		/*
		 * customize:
		 * http://sagehill.net/docbookxsl/CustomMethods.html#WriteCustomization
		 */
		/*
		 * parameter reference:
		 * http://docbook.sourceforge.net/release/xsl/current /doc/fo/index.html
		 */
		pdfRenderer.xsl("res:pdf_template.xsl");
		try {
			/* +++++++++++++++++++++++++++++++++++++++++++++++++ */
			/* Start PDF rendering*/
			/* +++++++++++++++++++++++++++++++++++++++++++++++++ */
			InputStream in = pdfRenderer.render();
			FileOutputStream out = new FileOutputStream(outputFile);
			IOUtils.copy(in, out);
			return outputFile;
		} catch (Docbook4JException e) {
			throw new IOException("Cannot create file : " + outputFile.getAbsolutePath(), e);
		}
	}

}