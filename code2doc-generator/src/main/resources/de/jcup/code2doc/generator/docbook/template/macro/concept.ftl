<#----------------------------------->
<#-- macro for use case decoration -->
<#----------------------------------->
<#macro showConceptDef conceptDef sectionStart>
	<@debug 'showConcept (1): sectionStart='+sectionStart />
	<#if conceptDef??>
		<@debug 'showConcept (2)' />
		<#assign concept=conceptDef.element/>
		<#assign tag=transformer.transformToTag(concept)/>
		<#assign id=transformer.transformToId(concept)/>
		<@openSection sectionStart id />
			<#-- FIXME i18n -->
			<title>${textDecorator.decorate(concept.headline)}</title>
			<formalpara>
				<title>${i18n.get('code2doc.core.concept.tag.headline')}</title>
			</formalpara>
			<para><indexterm><primary>${tag}</primary></indexterm>${tag}</para>
			<para/>
			<#-------------------------->
			<#-- description -->
			<#-------------------------->
			<formalpara>
				<title>${i18n.get('code2doc.core.concept.description.headline')}</title>
			</formalpara>
			<#if concept.description??>
				<#assign description=concept.description/>
			<#else>
				<#assign description=i18n.get('code2doc.core.concept.description.content.notdefined')/>
			</#if>
			<para>${textDecorator.decorate(description)}</para>
			<#------------------------------------>
			<#-- additional defined content parts  -->
			<#------------------------------------>
			<@showElementContent concept sectionStart+1/>
			
			<#------------------------------------>
			<#-- linked used cases              -->
			<#------------------------------------>
			<#if conceptDef.linkedUseCasesSorted?size gt 0>
				<para> </para>
				<para>
				${i18n.get('code2doc.core.concept.linkedusecases.start')}
				<#-- show linked useCaseDefinition  -->
				<simplelist type='inline'>
					<#list conceptDef.linkedUseCasesSorted as useCaseDef>
					<member>
						<#assign text=transformer.transformToString(useCaseDef.element)/>
						<link linkend='${transformer.transformToId(useCaseDef.element)}'>${text}</link>
					</member>
					</#list>
				</simplelist>
				</para>
			</#if>
			
			
		<@closeSection sectionStart/>
	</#if>
</#macro>