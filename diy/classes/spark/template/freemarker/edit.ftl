<#include "base.ftl">

<#macro menu>
	<li><a href="/">Consultrar</a></li>
	<li><a href="/add">Añadir</a></li>
	<li class="active"><a href="/edit/0">Editar</a></li>
	<li><a href="/delete/0">Borrar</a></li>


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
	      <th></th>
	    </tr>
	  </thead>
	  <tbody>
	  	<#list games as game>

	    <tr>
	    <form action="/edit/id/${game.id}" method="post">
		      <td><input class="form-control input-sm" type="text" name="name"
		      value="${game.name}"/></td>
		      <td><input class="form-control input-sm" type="text" name="types"
		      value="${game.types}"/></td>
		      <td><input class="form-control input-sm" type="text" name="platform"
		      value="${game.platform}"/></td>
		      <td><input class="form-control input-sm" type="text" name="launchdate"
		      value="${game.launchdate}"/></td>
		      <td><input class="form-control input-sm" type="text" name="webPage"
		      value="${game.webPage}"/></td>
		      <td class="selectbtn"><input type="submit" value="Up" class="btn btn-info btn-sm"/></td>
	    </form>
	    </tr>
		</#list>
	  </tbody>
	</table>
	<ul class="pager">
	  <li><a href="prev/${page}">Previous</a></li>
	  <li><a href="next/${page}">Next</a></li>
	</ul>
	<p class="text-danger" style="display:${error2}">Alguno de los compos editados estaba vacio</p>
</#macro>

<@display_page />