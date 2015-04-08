<#----------------------------------->
<#-- macro for use case decoration -->
<#----------------------------------->
<#macro showArchitecturDef architectureDef sectionStart>
	<@debug 'showArchitecturDef (1): sectionStart='+sectionStart /><#t>
	<#if architectureDef??>
		<@debug 'showArchitecturDef (2)' /><#t>
		<#assign architecture=architectureDef.element/>
		<#assign id=transformer.transformToId(architecture)/>
		<@openSection sectionStart id/>
			<@renderHeader architecture/>
			<@renderDescription architecture />
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