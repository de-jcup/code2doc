<#---------------------------------->
<#-- macro for element tech info  -->
<#---------------------------------->
<#macro iterateTechInfo elementDef sectionStart>
	<#if elementDef.technicalDefinitions?size gt 0>
		<@openSection sectionStart+1/>
		<title>${i18n.get('code2doc.core.techinfo.title')}</title>
		<#-- tables with technical information  -->
		<#list elementDef.technicalDefinitions as technicalDef>
			<@showTechInfo technicalDef/>
		</#list>
		<@closeSection sectionStart+1/>
	</#if>
</#macro>
<#---------------------------------->
<#-- macro for showing tech info  -->
<#---------------------------------->
<#macro showTechInfo techInfo>
	<#if techInfo.containingLinks??>
		<para>
			<table>
				<title>${i18n.get('code2doc.core.techinfo.headline.prefix')}'${techInfo.headline}'</title>
				<tgroup align='left' cols='2' rowsep='1'>
					<colspec colname='c1' colwidth='0.25*' />
					<colspec colname='c2' colwidth='1*' />
					<thead>
						<row>
							<entry>${i18n.get('code2doc.core.table.header.type')}</entry>
							<entry>${i18n.get('code2doc.core.table.header.value')}</entry>
						</row>
					</thead>
					<tbody>
						<@appendMaptoTable techInfo.linkToJavaClasses/>
						<@appendMaptoTable techInfo.linkToJavaEnums/>
						<@appendMaptoTable techInfo.linkToURLs/>
					</tbody>
				</tgroup>
			</table>
		</para>
	</#if>
</#macro>