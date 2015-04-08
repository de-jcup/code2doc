<#----------------------------------->
<#-- macro for sectioning start    -->
<#-- when id is given an xml link  -->
<#-- is possible                   -->
<#----------------------------------->
<#macro openSection sectionStart id="NONE">
	<#assign sectionName = createSectionName(sectionStart) />
	<@debug '######## open section('+sectionName+') - nr:'+sectionStart /><#t>
	<#if id='NONE'>
		<${sectionName}>
	<#else>
		<${sectionName} xml:id='${id}'>
	</#if>
</#macro>	
<#----------------------------------->
<#-- macro for sectioning end      -->
<#----------------------------------->
<#macro closeSection sectionStart>
	<#assign sectionName = createSectionName(sectionStart) />
	<@debug '######## close section('+sectionName+') - nr:'+sectionStart /><#t>
	</${sectionName}>
</#macro>
<#----------------------------------->
<#-- function for section name     -->
<#----------------------------------->
<#function createSectionName sectionStart>
	<#if sectionStart gt 5  || onlySections=="true">
		<#-- when section numbering is greater 5 we only use section element -->
		<#assign sectionName='section'  />
	<#else>
		<#assign sectionName='sect'+sectionStart />
	</#if>
	<#return sectionName />
</#function>