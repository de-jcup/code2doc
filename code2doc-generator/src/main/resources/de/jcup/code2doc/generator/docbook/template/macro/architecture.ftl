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
			<#-- FIXME i18n -->
			<#assign title = textDecorator.decorate(architecture.headline)/>
			<@debug 'showArchitecturDef (3): title='+title />
			<title>${title}</title>
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
			<#assign tag=transformer.transformToTag(architecture)/>
			<indexterm><primary>${tag}</primary></indexterm>
			<para>${textDecorator.decorate(description)}</para>
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