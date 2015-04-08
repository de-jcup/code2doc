<#include 'macro/macros.ftl'/>
<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>
<book>
	<title>${spec.title}</title>
	<info>
		<author>
			<personname>
				<firstname>${spec.authorFirstName}</firstname>
				<surname>${spec.authorLastName}</surname>
			</personname>
		</author>
		<legalnotice>
			<para>${spec.legalNotice}</para>
		</legalnotice>
	</info>
	<preface>
		<title>${i18n.get('code2doc.core.preface.title')}</title>
		<para>
			${textDecorator.decorate(spec.preface)}
		</para>
	</preface>
	<article>
		<@debug '######################## article: Concepts (1):######################## ' /><#t>
		<title>${i18n.get('code2doc.core.article.concepts.title')}</title>
		<#list spec.rootGroupDefinitions as rootGroupDefinition>
			<@groupIterator 'CONCEPT' rootGroupDefinition 1 />
    	</#list>
	</article>
	<article>
		<@debug '######################## article: Use cases (1):######################## ' /><#t>
		<title>${i18n.get('code2doc.core.article.usecases.title')}</title>
		<#list spec.rootGroupDefinitions as rootGroupDefinition>
     		<@groupIterator 'USECASE' rootGroupDefinition 1 />
    	</#list>
	</article>
	<article>
		<@debug '######################## article: Architectures (1):######################## ' /><#t>
		<title>${i18n.get('code2doc.core.article.architectures.title')}</title>
		<#list spec.rootGroupDefinitions as rootGroupDefinition>
     		<@groupIterator 'ARCHITECTURE' rootGroupDefinition 1 />
    	</#list>
	</article>
	<article>
		<@debug '######################## article: Roles (1):######################## ' /><#t>
		<title>${i18n.get('code2doc.core.article.roles.title')}</title>
		<#list spec.rootGroupDefinitions as rootGroupDefinition>
			<@groupIterator 'ROLE' rootGroupDefinition 1 />
    	</#list>
	</article>
	<article>
		<@debug '######################## article: Constraints (1):######################## ' /><#t>
		<title>${i18n.get('code2doc.core.article.constraints.title')}</title>
		<#list spec.rootGroupDefinitions as rootGroupDefinition>
			<@groupIterator 'CONSTRAINT' rootGroupDefinition 1 />
    	</#list>
	</article>
	<index><title>Index</title></index>
</book>