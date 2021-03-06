<#----------------------------------->
<#-- macro for use case decoration -->
<#----------------------------------->
<#macro showConceptDef conceptDef sectionStart>
	<@debug 'showConcept (1): sectionStart='+sectionStart /><#t>
	<#if conceptDef??>
		<@debug 'showConcept (2)' /><#t>
		<#assign concept=conceptDef.element/>
		<#assign tag=transformer.transformToTag(concept)/>
		<#assign id=transformer.transformToId(concept)/>
		<@openSection sectionStart id />
			<@renderHeader concept/>
			<@renderDescription concept />
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