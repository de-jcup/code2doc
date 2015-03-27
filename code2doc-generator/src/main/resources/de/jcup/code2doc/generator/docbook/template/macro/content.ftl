<#----------------------------------------->
<#-- macro for content of an base element-->
<#----------------------------------------->
<#macro showElementContent baseElement sectionStart>
	<@debug 'showElementContent (1)' />
	<#-- if locale is set use content of locale-->
	<#if locale??>
		<#assign content=baseElement.getContent(locale)/>
	<#else>
		<#assign content=baseElement.content/>
	</#if>
	<@iterateContentChildren content sectionStart/>
</#macro>
<#----------------------------------------->
<#-- macro for iterating content children-->
<#----------------------------------------->
<#macro iterateContentChildren content sectionStart>
	<@debug 'iterateContentChildren (1)' />
	<#list content.children as child>
		<@showContent child sectionStart/>
	</#list>
</#macro>	
<#----------------------------------------->
<#-- macro for content-locale done before-->
<#----------------------------------------->
<#macro showContent content sectionStart>
		<#assign name=content.class.simpleName/>
		<@debug 'showContent (1): content type=\''+name+'\'' />
		<#if name = 'TextContent'>	
			<@renderTextContent content sectionStart/>
		<#elseif name = 'TextContentResource'>	
			<@renderTextContentResource content sectionStart/>
		<#elseif name= 'HeadlineContainer'>	
			<@renderHeadlineContainer content sectionStart/>
		<#elseif name= 'PictureContentResource'>	
			<@renderPictureContentResource content sectionStart/>
		<#elseif name= 'LinkToURLContent'>	
			<@renderLinkToURLContent content sectionStart/>
		<#elseif name= 'CodeContent'>	
			<@renderCodeContent content sectionStart/>
		<#elseif name= 'CodeContentResource'>	
			<@renderCodeContentResource content sectionStart/>
		<#else>
			<@error 'unsupported content:'+name/>
		</#if>	
</#macro>
<#-------------------------------------------->
<#-- macro for rendering link to URL content-->
<#-------------------------------------------->
<#macro renderLinkToURLContent linkToURLContent sectionStart>
	<para>
		<ulink url='${linkToURLContent.url}'>${textDecorator.decorate(linkToURLContent.message)}</ulink>
	</para>	
</#macro>
<#----------------------------------------->
<#-- macro for rendering text content-->
<#----------------------------------------->
<#macro renderTextContent textContent sectionStart>
	<para>${textDecorator.decorate(textContent.text)}</para>	
</#macro>
<#----------------------------------------------->
<#-- macro for rendering text content resource -->
<#----------------------------------------------->
<#macro renderTextContentResource textContent sectionStart>
	<para>${textDecorator.decorate(transformer.transformToStringContent(textContent.resourcePath))}</para>	
</#macro>
<#------------------------------------------->
<#-- macro for rendering headline container-->
<#------------------------------------------->
<#macro renderHeadlineContainer headlineContainer sectionStart>
	<@openSection sectionStart/>
	<title>${textDecorator.decorate(headlineContainer.headline)}</title>
	<@iterateContentChildren headlineContainer sectionStart/>
	<@closeSection sectionStart/>
</#macro>
<#-------------------------------->
<#-- macro for rendering picture-->
<#-------------------------------->
<#macro renderPictureContentResource pictureContent sectionStart>
	<#if pictureContent.title??>
		<#if transformer.transformToFile(pictureContent.resourcePath)??>
		<figure>
			<title>${pictureContent.title}</title>
			<graphic fileref='${transformer.transformToFile(pictureContent.resourcePath)}' width='100%' scalefit='1'  />
		</figure>
		<#else>
			<@error 'no file found for:'+pictureContent.resourcePath/>
		</#if>
	<#else>
		<@error 'no title defined for picture content!'/>
	</#if>
</#macro>
<#-------------------------------->
<#-- macro for rendering code   -->
<#-------------------------------->
<#macro renderCodeContent content sectionStart>
	<#assign language=content.language/>
	<#assign code=content.code/>
	<programlisting language="${language}"><![CDATA[${code}]]></programlisting>
</#macro>
<#---------------------------------------->
<#-- macro for rendering code resource  -->
<#---------------------------------------->
<#macro renderCodeContentResource content sectionStart>
	<#assign language=content.language/>
	<#assign code=transformer.transformToStringContent(content.resourcePath)/>
	<programlisting language="${language}"><![CDATA[${code}]]></programlisting>
</#macro>
