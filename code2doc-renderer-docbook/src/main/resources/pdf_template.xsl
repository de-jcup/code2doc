<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xhl="http://net.sf.xslthl/ConnectorXalan" xmlns:xalan="http://xml.apache.org/xalan"
	xmlns:xslthl="http://xslthl.sf.net" extension-element-prefixes="xhl xslthl"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.0">
	<xsl:import href="res:xsl/docbook/fo/docbook.xsl" />
	<xsl:import href="res:xsl/docbook/fo/highlight.xsl" />

	<!-- linebreaks: http://www.sagehill.net/docbookxsl/LineBreaks.html -->
	<xsl:template match="processing-instruction('linebreak')">
	  <fo:block/>
	</xsl:template>

	<!-- http://www.sagehill.net/docbookxsl/ProgramListings.html -->
	<xsl:param name="shade.verbatim" select="1"/>
	
	<xsl:attribute-set name="shade.verbatim.style">
	  <xsl:attribute name="background-color">#E0E0E0</xsl:attribute>
	  <xsl:attribute name="border-width">0.5pt</xsl:attribute>
	  <xsl:attribute name="border-style">solid</xsl:attribute>
	  <xsl:attribute name="border-color">#575757</xsl:attribute>
	  <xsl:attribute name="padding">3pt</xsl:attribute>
	</xsl:attribute-set>

	<!-- http://www.dpawson.co.uk/docbook/styling/fo.html -->
	<xsl:attribute-set name="xref.properties">
		<xsl:attribute name="color">blue</xsl:attribute>
	</xsl:attribute-set>


	<!-- for Xalan 2.7 -->
	<xalan:component prefix="xhl" functions="highlight">
		<xalan:script lang="javaclass" src="xalan://net.sf.xslthl.ConnectorXalan" />
	</xalan:component>

	<!-- http://www.sagehill.net/docbookxsl/SyntaxHighlighting.html -->
	<xsl:template match='xslthl:keyword'>
		<fo:inline font-weight="bold" color="blue">
			<xsl:apply-templates />
		</fo:inline>
	</xsl:template>

	<xsl:template match='xslthl:comment'>
		<fo:inline font-style="italic" color="grey">
			<xsl:apply-templates />
		</fo:inline>
	</xsl:template>
	
	<!-- http://www.sagehill.net/docbookxsl/ImageSizing.html -->
	<!-- http://docbook.org/tdg/en/html/imagedata.html -->
	<!--  done inside generated docbook elements! -->
	
	<!--  left margin... http://stackoverflow.com/questions/17237093/xsl-fo-docbook-content-left-margin -->
	<xsl:param name="body.start.indent">0pt</xsl:param>
</xsl:stylesheet>  