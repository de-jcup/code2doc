<#-------------------------------->
<#-- macro for appending rows   -->
<#-------------------------------->
<#macro appendMaptoTable map>
<#list map?keys as key>
	<row>
		<entry>${key}</entry>
		<entry>
		<#list map[key] as item>
			<#assign itemAsString=transformer.transformToString(item)/>
			${itemAsString} 
		</#list>
		</entry>
	</row>
</#list>
</#macro>
<#---------------------------------->
<#-- macro for error in generation-->
<#---------------------------------->
<#macro error message>
${log.error(message)}
<para>
	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
</para>
<para>
	++ ERROR: ${message}
</para>
<para>
	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
</para>
</#macro>
<#---------------------------------->
<#-- macro for debug in generation-->
<#---------------------------------->
<#macro debug message>
	${log.debug(message)}
</#macro>