<#include "base.ftl">

<#macro menu>
	<li class="active"><a href="/">Consultrar</a></li>
	<li><a href="/add">Añadir</a></li>
	<li><a href="/edit">Editar</a></li>
	<li><a href="/delete">Borrar</a></li>
</#macro>

<#macro content>
	<table class="table table-striped table-hover ">
	  <thead>
	    <tr>
	      <th>Nombre</th>
	      <th>Generos</th>
	      <th>Plataforma</th>
	      <th>Lanzamiento</th>
	      <th>Web</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<#list games as game>
		    <tr>
		    	<td>${game.name}</td>
		    	<td>${game.types}</td>
		    	<td>${game.platform}</td>
		    	<td>${game.launchdate}</td>
		    	<td>${game.webPage}</td>
		    </tr>
		</#list>
	  </tbody>
	</table> 
</#macro>

<@display_page />