<#----------------------------------->
<#-- macro for role decoration -->
<#----------------------------------->
<#macro showRoleDef roleDef sectionStart>
	<@debug 'showRole (1): sectionStart='+sectionStart />
	<#if roleDef??>
		<@debug 'showRole (2)' />
		<#assign role=roleDef.element/>
		<#assign tag=transformer.transformToTag(role)/>
		<#assign id=transformer.transformToId(role)/>
		<@openSection sectionStart id />
			<#-- FIXME i18n -->
			<title>${textDecorator.decorate(role.headline)}</title>
			<formalpara>
				<title>${i18n.get('code2doc.core.role.tag.headline')}</title>
			</formalpara>
			<para><indexterm><primary>${tag}</primary></indexterm>${tag}</para>
			<para/>
			<#-------------------------->
			<#-- description -->
			<#-------------------------->
			<formalpara>
				<title>${i18n.get('code2doc.core.role.description.headline')}</title>
			</formalpara>
			<#if role.description??>
				<#assign description=role.description/>
			<#else>
				<#assign description=i18n.get('code2doc.core.role.description.content.notdefined')/>
			</#if>
			<para>${textDecorator.decorate(description)}</para>
			<#------------------------------------>
			<#-- additional defined content parts  -->
			<#------------------------------------>
			<@showElementContent role sectionStart+1/>
			<#---------------------------------------->
			<#-- tables with technical information  -->
			<#---------------------------------------->
			<@iterateTechInfo roleDef sectionStart/>
		<@closeSection sectionStart/>
	</#if>
</#macro>