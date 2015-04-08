<#---------------------------------------->
<#-- macro for rendering wiki url       -->
<#---------------------------------------->
<#macro renderWikiURL element >
	<#if element.wikiURL??>
		<@debug 'wiki url defined for element:'+element.wikiURL/>
		<para>
			${i18n.get('code2doc.core.element.wikiURLPrefix')}<ulink url='${element.wikiURL}'>${textDecorator.decorate(element.wikiURL)}</ulink>${i18n.get('code2doc.core.element.wikiURLPostfix')}
		</para>
	<#else>
		<#--<@debug 'no wiki url defined for element:'+element/> -->
	</#if>
</#macro>
