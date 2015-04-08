<#---------------------------------------->
<#-- macro for indexing data            -->
<#---------------------------------------->
<#macro addToIndex data>
	<indexterm><primary>${data}</primary></indexterm>
</#macro>
<#-------------------------------->
<#-- macro for appending rows   -->
<#-------------------------------->
<#macro appendMaptoTable map>
<#list map?keys as key>
	<row>
		<entry>${key}</entry>
		<entry>
		<#list map[key] as item><#t>
			<#assign itemAsString=transformer.transformToString(item)/>
			<para>${itemAsString}</para>
		</#list><#t>
		</entry>
	</row>
</#list>
</#macro>
<#---------------------------------->
<#-- macro for error in generation-->
<#---------------------------------->
<#macro error message>
	<!-- -------------- -->
	<!-- error: message -->
	<!-- -------------- -->
	${log.error(message)}<#t>
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
	${log.debug(message)}<#t>
</#macro>