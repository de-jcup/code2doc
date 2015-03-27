<#include "macro_showLoad.ftl">
<#compress>
<html>
<head>
  <title>Welcome!</title>
</head>
<body>
  <h1>Welcome ${user}!</h1>
  <p>Our latest product:
  <a href="${latestProduct.url}">${latestProduct.name}</a>!
    <@showLoad load1/>
</body>
</html>  
</#compress>  

