<#---------------------------------------->
<#-- macro for rendering wiki url       -->
<#---------------------------------------->
<#macro renderWikiURL element >
	<#if element.wikiURL??>
		<@debug 'wiki url defined for element:'+element.wikiURL/><#t>
		<para>
			${i18n.get('code2doc.core.element.wikiURLPrefix')}<ulink url='${element.wikiURL}'>${textDecorator.decorate(element.wikiURL)}</ulink>${i18n.get('code2doc.core.element.wikiURLPostfix')}
		</para>
	</#if>
</#macro>
<#---------------------------------------->
<#-- macro for rendering element header -->
<#---------------------------------------->
<#macro renderHeader element >
	<@renderTitle element/>
	<@renderTag element/>
	<para> </para>
</#macro>
<#---------------------------------------->
<#-- macro for rendering element title  -->
<#---------------------------------------->
<#macro renderTitle element >
	<#-- FIXME i18n of headline (locale missing - or we handle this otherwise) -->
	<title>${textDecorator.decorate(element.headline)}</title>
</#macro>
<#---------------------------------------->
<#-- macro for rendering tag data       -->
<#---------------------------------------->
<#macro renderTag element>
	<@addToIndex tag/>
	<foreignphrase>(${tag})</foreignphrase>
</#macro>