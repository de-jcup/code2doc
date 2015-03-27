<#-- macro example with parameter -->
<#macro showLoad load>
	<br>
	Name:${load.name} - decorated: ${load.getDecorated('test')}, ${load.getDecorated(load.name)}
	<ol>
	<#list load.children as child>
     	<li><@showLoad child/></li>
    </#list>
    </ol>
</#macro>