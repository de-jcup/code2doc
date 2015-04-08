<#----------------------------------->
<#-- macro for role decoration -->
<#----------------------------------->
<#macro showRoleDef roleDef sectionStart>
	<@debug 'showRole (1): sectionStart='+sectionStart /><#t>
	<#if roleDef??>
		<@debug 'showRole (2)' /><#t>
		<#assign role=roleDef.element/>
		<#assign tag=transformer.transformToTag(role)/>
		<#assign id=transformer.transformToId(role)/>
		<@openSection sectionStart id />
			<@renderHeader role/>
			<@renderDescription role />
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