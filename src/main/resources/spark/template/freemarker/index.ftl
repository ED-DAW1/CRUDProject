<#include "base.ftl">

<#macro menu>
	<li class="active"><a href="#">Consultrar</a></li>
	<li><a href="#">Añadir</a></li>
	<li><a href="#">Editar</a></li>
	<li><a href="#">Borrar</a></li>
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
	    <tr>
	    <td></td>
	    <td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	    </tr>
	  </tbody>
	</table> 
</#macro>

<@display_page />