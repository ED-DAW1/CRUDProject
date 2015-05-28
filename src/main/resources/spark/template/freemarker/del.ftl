<#include "base.ftl">

<#macro menu>
	<li><a href="/">Consultrar</a></li>
	<li><a href="/add">Añadir</a></li>
	<li><a href="/edit/0">Editar</a></li>
	<li class="active"><a href="/delete/0">Borrar</a></li>


</#macro>

<#macro content>
	<table class="table table-striped table-hover ">
	  <thead>
	    <tr>
	   	<th />
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
	    	<td class="selectbtn"><a href="/delete/${game.id}" class="btn btn-danger btn-xs" /></td>
	    	<td>${game.name}</td>
	    	<td>${game.types}</td>
	    	<td>${game.platform}</td>
	    	<td>${game.launchdate}</td>
	    	<td>${game.webPage}</td>
	    </tr>
		</#list>
	  </tbody>
	</table>

	<ul class="pager">
	  <li><a href="prev/${page}">Previous</a></li>
	  <li><a href="next/${page}">Next</a></li>
	</ul>
	<a href="/delete/all" class="btn btn-danger" style="float:right">Todo</a>
</#macro>

<@display_page />