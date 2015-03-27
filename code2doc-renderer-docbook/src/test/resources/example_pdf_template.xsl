<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.0">
	<xsl:import href="res:xsl/docbook/fo/docbook.xsl" />

	<xsl:attribute-set name="xref.properties">
		<xsl:attribute name="color">
		    <xsl:choose>
		      <xsl:when test="self::ulink">blue</xsl:when>
		      <xsl:otherwise>inherit</xsl:otherwise>
		    </xsl:choose>
  		</xsl:attribute>
	</xsl:attribute-set>

<!-- http://www.sagehill.net/docbookxsl/PrintCustomEx.html -->
<!--  next lines are working, complete document is blue... -->
	<xsl:attribute-set name="root.properties">
		<xsl:attribute name="color">blue</xsl:attribute>
	</xsl:attribute-set>
	<!-- http://www.dpawson.co.uk/docbook/styling/fo.html -->
</xsl:stylesheet>  