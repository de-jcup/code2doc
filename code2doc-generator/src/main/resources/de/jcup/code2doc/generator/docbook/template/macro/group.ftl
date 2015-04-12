<#------------------------------------->
<#-- macro for common group iteration-->
<#------------------------------------->
<#macro groupIterator definitionType groupDef sectionStart>
	<@debug '############### groupIterator (1): Group\''+groupDef.group+'\':'+definitionType+', section:'+sectionStart /><#t>
	<#assign definitions = getDefinitions(groupDef,definitionType)/>
	<#if definitions?? && definitions?size gt 0>
		<@debug 'groupIterator (2a):'+definitionType /><#t>
		<#if groupDef.group.name??>
			<#assign title =i18n.get('group.'+groupDef.group.name)/>
			<#if title?has_content>
				<@openSection sectionStart/>
				<title>${title}</title>
				<#assign newSection=sectionStart+1/>
			<#else>
				<@debug 'groupIterator (3c):'+definitionType /><#t>
				<#assign newSection=sectionStart/>
			</#if>
		<#else>
			<#-- this is the group() - element without a name -->
			<@debug 'groupIterator (3b):'+definitionType /><#t>
			<#assign newSection=sectionStart/>
		</#if>
		<#list definitions as definition>
			<#if definition.definitionType != definitionType>
				<@error 'Difference found between definition types. Expected:' + definitionType+" but got:"+definition.definitionType /><#t>
			</#if>
			<@debug 'groupIterator (5):'+definitionType+', description='+transformer.transformToString(definition.element.description) /><#t>
			<@callShowMacro definitionType definition, newSection />
		</#list>
		
		<#list groupDef.childrenSorted as childGroupDef>
			<@groupIterator definitionType childGroupDef newSection/>
		</#list>
		<#if groupDef.group.name??>
			<#assign title =i18n.get('group.'+groupDef.group.name)/>
			<#if title?has_content>
				<@closeSection sectionStart/>
			</#if>
		</#if>
	<#else>
		<#-- ignore this group (no output at all - but traverse to children -->
		<#list groupDef.childrenSorted as childGroupDef>
			<@groupIterator definitionType childGroupDef sectionStart/>
		</#list>
    </#if>
</#macro>
<#function getDefinitions groupDef definitionType >
	<#return groupDef.getDefinitionsSorted(definitionType) />
</#function>
<#----------------------------------------------------------->
<#-- macro for showing dedicated definition type info      -->
<#----------------------------------------------------------->
<#macro callShowMacro definitionType definition newSection >
	<@debug '###### callShowMacro:'+definitionType+'('+definition.element.headline+'), section:'+newSection /><#t>
	<#if definitionType='USECASE'>
		<@showUseCase definition newSection/>
	<#elseif definitionType='ARCHITECTURE'>
		<@showArchitecturDef definition newSection/>
	<#elseif definitionType='CONCEPT'>
		<@showConceptDef definition newSection/>
	<#elseif definitionType='CONSTRAINT'>
		<@showConstraintDef definition newSection/>
	<#elseif definitionType='ROLE'>
		<@showRoleDef definition newSection/>
	<#else>
		<@error 'Unsupported definition type:'+definitionType /><#t>
	</#if>
</#macro>