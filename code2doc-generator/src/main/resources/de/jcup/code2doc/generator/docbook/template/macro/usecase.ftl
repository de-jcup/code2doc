<#----------------------------------->
<#-- macro for use case decoration -->
<#----------------------------------->
<#macro showUseCase useCaseDef sectionStart>
	<@debug 'showUseCase (1): sectionStart='+sectionStart /><#t>
	<#if useCaseDef??>	
		<#assign useCase=useCaseDef.element/>
		<#assign tag=transformer.transformToTag(useCase)/>
		<#assign id=transformer.transformToId(useCase)/>
		<@debug 'showUseCase (2)' /><#t>
		<@openSection sectionStart id/>
		<@renderHeader useCase/>
		<@renderDescription useCase />
		<#--------------------->				
		<#-- exact URL       -->
		<#--------------------->
		<#if useCase.exactURL??>
		<para>
			<emphasis role='bold'>${i18n.get('code2doc.core.usecase.exact.url')}</emphasis>
			<ulink url='${useCase.exactURL}'>${useCase.exactURL}</ulink>
		</para>
		</#if>
		<#--------------------->	
		<#-- example URL     -->
		<#--------------------->
		<#if useCase.exampleURL??>
		<para>
			<emphasis role='bold'>${i18n.get('code2doc.core.usecase.example.url')}</emphasis>
			<ulink url='${useCase.exampleURL}'>${useCase.exampleURL}</ulink>
		</para>
		</#if>
		<#--------------------->
		<#-- example picture -->
		<#--------------------->
		<#if useCase.examplePictureResourcePath??>
		<figure>
			<title>${i18n.get('code2doc.core.usecase.example.picture.headline.prefix')} '${useCase.headline}'</title>
			<graphic fileref="${transformer.transformToFile(useCase.examplePictureResourcePath)}" scalefit="1" width="100%" contentdept="100%"/>
		</figure>
		</#if>
		<#------------------------------------>
		<#-- additional defined content parts  -->
		<#------------------------------------>
		<@showElementContent useCase sectionStart+1/>
		<#---------------------------------------->
		<#-- Roles (with or without constraints -->
		<#---------------------------------------->
		<#if useCase.roles?size gt 0>
		<para>
			<table>
				<title>${i18n.get('code2doc.core.usecase.role.constraint.headline.prefix')}'${useCase.headline}'</title>
				<tgroup align='left' cols='2' rowsep='1'>
					<colspec colname='c1' colwidth='1*' />
					<colspec colname='c2' colwidth='1*' />
					<thead>
						<row>
							<entry>${i18n.get('code2doc.core.table.header.role')}</entry>
							<entry>${i18n.get('code2doc.core.table.header.constraint')}</entry>
						</row>
					</thead>
					<tbody>
					<#list useCase.roles as roleDefClass>
						<#assign roleDef=spec.getDefinition(roleDefClass)/>
						<row>
							<#assign text=textDecorator.decorate(roleDef.element.headline)/>
							<entry><link linkend='${transformer.transformToId(roleDef.element)}'>${text}</link></entry>
							<entry>
								<#if useCase.getConstraint(roleDefClass)??>
									<#assign constraintDefClass=useCase.getConstraint(roleDefClass)/>
									<#assign constraintDef=spec.getDefinition(constraintDefClass)/>									
									<#assign text=textDecorator.decorate(constraintDef.element.headline)/>
									<link linkend='${transformer.transformToId(constraintDef.element)}'>${text}</link>
								</#if>
							</entry>
						</row>
					</#list>
					</tbody>
				</tgroup>
			</table>
		</para>
		</#if>
		<#------------------------------------>
		<#-- linked business cases          -->
		<#------------------------------------>
		<#if useCase.linkedConcepts?size gt 0>
			<para> </para>
			<para>
			${i18n.get('code2doc.core.usecase.linkedconcepts.start')}
			<#-- show linked conceptDefinition  -->
			<simplelist type='inline'>
				<#list useCase.linkedConcepts as conceptDefClass>
					<#assign conceptDef=spec.getDefinition(conceptDefClass)/>
				<member>
					<#assign text=transformer.transformToString(conceptDef.element)/>
					<link linkend='${transformer.transformToId(conceptDef.element)}'>${text}</link>
				</member>
				</#list>
			</simplelist>
			</para>
		</#if>
		
		<#---------------------------------------->
		<#-- tables with technical information  -->
		<#---------------------------------------->
		<@iterateTechInfo useCaseDef sectionStart/>
		<@closeSection sectionStart/>
	</#if>
</#macro>