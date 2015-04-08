<#----------------------------------->
<#-- macro for use case decoration -->
<#----------------------------------->
<#macro showConstraintDef constraintDef sectionStart>
	<@debug 'showConstraint (1): sectionStart='+sectionStart /><#t>
	<#if constraintDef??>
		<@debug 'showConstraint (2)' /><#t>
		<#assign constraint=constraintDef.element/>
		<#assign tag=transformer.transformToTag(constraint)/>
		<#assign id=transformer.transformToId(constraint)/>
		<@openSection sectionStart id />
			<@renderHeader constraint/>
			<#-------------------------->
			<#-- description -->
			<#-------------------------->
			<formalpara>
				<title>${i18n.get('code2doc.core.constraint.description.headline')}</title>
			</formalpara>
			<#if constraint.description??>
				<#assign description=constraint.description/>
			<#else>
				<#assign description=i18n.get('code2doc.core.constraint.description.content.notdefined')/>
			</#if>
			<para>${textDecorator.decorate(description)}</para>
			<@renderWikiURL constraint/>
			<#------------------------------------>
			<#-- additional defined content parts  -->
			<#------------------------------------>
			<@showElementContent constraint sectionStart+1/>
			<#---------------------------------------->
			<#-- tables with technical information  -->
			<#---------------------------------------->
			<@iterateTechInfo constraintDef sectionStart/>
		<@closeSection sectionStart/>
	</#if>
</#macro>