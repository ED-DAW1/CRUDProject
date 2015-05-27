<#include "base.ftl">

<#macro menu>
	<li><a href="/">Consultrar</a></li>
	<li class="active"><a href="/add">Añadir</a></li>
	<li><a href="/edit">Editar</a></li>
	<li><a href="/delete">Borrar</a></li>
</#macro>

<#macro content>
<form action="/add/${id}" method="post">
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
		
		    <tr class="active">
		      <td><input class="form-control input-sm" type="text" name="name"/></td>
		      <td><input class="form-control input-sm" type="text" name="types"/></td>
		      <td><input class="form-control input-sm" type="text" name="platform"/></td>
		      <td><input class="form-control input-sm" type="text" name="launchdate"/></td>
		      <td><input class="form-control input-sm" type="text" name="webPage"/></td>
			</tr>
	  </tbody>
	</table> 
	<p class="text-danger" style="display:${error}">Alguno de los compos introducidos esta vacio</p>
	<input type="submit" value="Guardar" class="btn btn-primary"/>
</form>

</#macro>

<@display_page />