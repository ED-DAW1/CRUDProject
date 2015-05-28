<#macro head>
	<title>Biblioteca de Videojuegos</title>
</#macro>


<#macro display_page>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 
	<html lang="es">
		<head>
			<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
			<#-- bootstrap -->
			<link href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.4/slate/bootstrap.min.css" rel="stylesheet" />
			<#-- Personal Styles -->
			<link rel="stylesheet" type="text/css" href="/css/styles.css" />
			<meta charset="UTF-8" />
			<meta name="description" content="VideoGame Library About my Played Games" />
			<meta name="keywords" content="VideoGame,List,Library,Games,Gamer,Gaming" />
			<meta name="author" content="Alex Valencia Gallego" />
			<@head />
		</head>
		<body>
			<div id="header">
				<h1>Biblioteca de Videojuegos</h1>
			</div>
			<div id="container">
				<div id="menu">
					<ul class="nav nav-pills nav-stacked">
						<@menu />
					</ul>
				</div>
				<div id="content">
					<@content />
				</div>
			</div>
			<div id ="footer">
				<p>Creado por Alex Valencia</p>
			</div>

		</body>
	</html>
</#macro>