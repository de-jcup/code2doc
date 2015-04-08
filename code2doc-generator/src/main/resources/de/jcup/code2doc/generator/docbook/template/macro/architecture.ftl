<#----------------------------------->
<#-- macro for use case decoration -->
<#----------------------------------->
<#macro showArchitecturDef architectureDef sectionStart>
	<@debug 'showArchitecturDef (1): sectionStart='+sectionStart />
	<#if architectureDef??>
		<@debug 'showArchitecturDef (2)' />
		<#assign architecture=architectureDef.element/>
		<#assign id=transformer.transformToId(architecture)/>
		<@openSection sectionStart id/>
			<@renderHeader architecture/>
			<#-- FIXME 08.04.2015: add headline and description  to renderHeader too -->
			<#-- FIXME 08.04.2015: handle empty descriptions there -->
			<#-- FIXME 08.04.2015: use a general i18n key for description headline and content not defined etc. instead always dedicated -->
			<#-- FIXME 08.04.2015: remove old tag parts -->
			<#-------------------------->
			<#--  description         -->
			<#-------------------------->
			<formalpara>
				<title>${i18n.get('code2doc.core.architecture.description.headline')}</title>
			</formalpara>
			<#if architecture.description??>
				<#assign description=architecture.description/>
			<#else>
				<#assign description=i18n.get('code2doc.core.architecture.description.content.notdefined')/>
			</#if>
			<para>${textDecorator.decorate(description)}</para>
			<@renderWikiURL architecture/>
			<#------------------------------------>
			<#-- additional defined content parts  -->
			<#------------------------------------>
			<@showElementContent architecture sectionStart+1/>
			<#---------------------------------------->
			<#-- tables with technical information  -->
			<#---------------------------------------->
			<@iterateTechInfo architectureDef sectionStart/>
		<@closeSection sectionStart/>
	</#if>
</#macro>