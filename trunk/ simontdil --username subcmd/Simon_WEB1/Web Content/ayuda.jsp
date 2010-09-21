<%@ page info="helpPage"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ include file="includes/header.jsp" %>
<link href="styles/ayuda.css" rel="stylesheet" type="text/css" />
<div id="content" style="height:13500px;">
	<div id="alcien" style="height:13500px; padding-top:20px;">
	<!-- inicio ayuda -->
		<div id="contenedor">
			<div id="menu">
				<a name="top" id="top"></a>
				<ul>
					<li><a href="#ingresar">¿Cómo ingresar al Sitio Web &quot;Zona Restringida XX Cumbre Iberoamericana&quot; por primera vez?</a></li>
					<li><a href="#navegar">¿Cómo navegar el sitio?</a></li>
					<li><a href="#doc">¿Cómo navegar un Documento?</a></li>
					<li><a href="#obs">¿Cómo realizar una observación a un Documento?</a></li>
					<li><a href="#verobs">¿Cómo ver todas las observaciones realizadas a un Documento?</a></li>
					<li><a href="#buscar">¿Cómo buscar observaciones realizadas a un Documento?</a></li>
					<li><a href="#descarga">¿Cómo descargar el listado de observaciones y el documento?</a></li>
				</ul>
			</div>
			<!--fin menu-->
			<div id="item">
				<div id="titulo"><a name="ingresar" id="ingresar"></a>¿Cómo ingresar al Sitio Web "Zona Restringida XX Cumbre Iberoamericana" por primera vez?</div>
				<!--fin titulo-->
				<div id="cuerpo">
					<div id="texto">Usted recibir&aacute; en su correo electrónico  un mensaje proveniente de sejec@cancilleria.gob.ar, en cuyo asunto se leerá "Nueva Clave de Acceso".<br />En este mensaje usted será notificado de su alta como usuario en el &quot;Sitio web exclusivo para delegaciones de la XX Cumbre Iberoamericana&quot;, cuya URL es: completar. <br />El mensaje le informará su <strong>clave temporal</strong> de acceso al sitio (esta clave se genera de  manera aleatoria por el sistema y es única). <br />Usted deberá<strong> ingresar al sitio</strong> web, puede  hacerlo desde el enlace que figura en el cuerpo del mail, para <strong>cambiar su clave</strong>.</div><!--fin texto-->
					<div id="imagen" align="center">Vista del correo electrónico<br /><img src="images/help/mail00.jpg" width="635" height="401" border="1" /></div>
					<DIV id="texto"><br />Al presionar el botón "Ingresar al sitio" usted será dirigido al &quot;Sitio web exclusivo para delegaciones de la XX Cumbre Iberomaricana&quot;, en dónde deberá ingresar seleccionando la opción <strong>"Usuarios Nuevos"</strong> para poder cambiar su clave.</DIV>
					<div id="imagen" align="center">Vista de la pantalla de ingreso del sitio web<br /><img src="images/help/sitio01.jpg" width="600" height="345" /></div>
					<div id="texto"><br />Al seleccionar la opción "Usuarios Nuevos" usted será dirigido a la pantalla de <strong>"blanqueo de contraseñas"</strong>, en dónde deberá ingresar los siguientes datos:<br />-	Su nombre de usuario (es el mísmo que figura en el correo electrónico) <br />-	Su dirección de e-mail (la misma en la cual recibió este correo electrónico)<br />-	La clave provisoria que le fue proporcionada en el correo electrónico.<br />-	Su nueva contraseña (este dato se pide dos veces para evitar errores de tipeo) </div>
					<div id="imagen" align="center">Vista de la pantalla de blanqueo de contraseñas<br /><img src="images/help/sitio02.jpg" width="600" height="345" /></div><div id="texto"><br />Luego de completar sus datos presione el botón "<strong>Crear contraseña</strong>"<br /><br />Usted será  dirigido a la pantalla de "LOGIN", en dónde deberá identificarse con su nombre  de usuario y la clave que usted acaba de crear.</div>
					<div id="imagen" align="center">Vista de la pantalla de LOGIN<br /><img src="images/help/sitio04.jpg" width="600" height="345" /></div><DIV id="texto"><br />Al presionar sobre el botón "Ingresar" usted será dirigido a la página inicial del Sitio web.<br /><br />El proceso de creación de contraseña se realiza por única vez cuando usted es dado de alta como nuevo usuario del sistema, en lo sucesivo usted deberá ingresar al sitio seleccionando la opción "Ingresar al sitio" en la pantalla inicial y utilizando la clave creada por usted. </DIV>
					<div id="imagen" align="center">Vista de la opción para "ingresar al sitio"<br /><img src="images/help/sitio05.jpg" width="600" height="345" /></div>
				</div>
				<!--fin cuerpo-->
				<div id="subir"><a href="#top">subir</a></div>
			</div>
			<!--fin item-->
			<div id="item">
				<div id="titulo"><a name="navegar" id="navegar"></a>¿Cómo navegar el sitio?</div>
				<!--fin titulo-->
				<div id="cuerpo">
					<div id="texto">Una vez que usted se ha identificado como usuario, será dirigido a la "Pantalla inicial del sitio web"</div><!--fin texto-->
					<div id="imagen" align="center">Vista de pantalla inicial del sitio web:<br /><img src="images/help/sitio00.jpg" width="599" height="343" border="0" /></div>
					<div id="texto"><br />En esta pantalla usted pordrá elegir entre las siguientes opciones:<br />-	Ir a los documentos Principales:  "Proyecto de Declaración" y  "Plan de Acción"<br />-	Ir a los documentos Específicos<br />-	Ir a documentos de la librería de referencia.</div><!--fin texto-->
				</div><!--fin cuerpo-->
				<div id="subir"><a href="#top"><br />subir</a></div>
			</div>
			<!--fin item-->
			<DIV id="item">
				<div id="titulo"><a name="doc" id="doc"></a>¿Cómo navegar un Documento?</div> 
				<div id="cuerpo">
					<div id="texto">Usted debe seleccionar el documento que desea navegar presionando sobre el nombre del documento en cuestión.</div>
					<div id="imagen" align="center"><em>Vista de selección de un  Documento Principal</em>:<br />
					<img src="images/help/sitio07.jpg" width="599" height="361" /></div>
					<div id="texto"><br />Al presionar sobre el nombre del documento usted será direccionado a la pantalla del documento que ha seleccionado, en donde podrá comenzar a trabajar.</div>
					<div id="imagen" align="center"><em>Vista de la pantalla de un  Documento Principal</em>:<br /><img src="images/help/sitio08.jpg" width="599" height="361" /></div>
					<div id="texto"><br />En esta pantalla usted podrá observar: <br /><br /><strong>-	Una caja con los "Datos del Documento" que ha seleccionado.</strong><br />Estos datos son: Nombre del documento, Versión del mísmo, Fecha límite para realizar observaciones y el botón "Descargar PDF". </div><div id="imagen" align="center"><em><br />Vista de la caja  "Datos del  Documento Principal"</em>:<br /><img src="images/help/sitio09.jpg" width="599" height="361" /></div><div id="texto"><br /><strong>-Una caja de "Acciones Disponibles"</strong><br />En donde encontrará los siguientes botones: "Añadir observación", "Buscar Observaciones" y "Ver todas las observaciones".</div><div id="imagen" align="center"><em><br />Vista de la caja  "Acciones  Disponibles"</em>:<br /><img src="images/help/sitio10.jpg" width="599" height="361" /></div>
					<div id="texto"><br /><strong>-	Una caja que contiene el texto completo del documento con sus números de párrafos indicados.</strong></div>
					<div id="imagen" align="center"><em>Vista de la caja "Texto del  Docuemento Principal"</em>:<br /><img src="images/help/sitio11.jpg" width="599" height="361" /></div>
				</div>
				<!--fin cuerpo-->
				<div id="subir"><a href="#top"><br />subir</a></div>
			</DIV>
			<!--fin item-->
			<DIV id="item">
				<div id="titulo"><a name="obs" id="obs"></a>¿Cómo realizar una observación a un Documento?</div>
				<div id="cuerpo">
					<div id="texto">1.	Presionar el botón "<strong>Añadir Observación</strong>" (ubicado en la caja de acciones disponibles)<br />2.	Se abrirá una ventana para añadir su Observación.</div>
					<div id="imagen" align="center"><em>Vista de la ventana para  "Añadir Observación"</em>:<br /><img src="images/help/obs01.jpg" width="599" height="361" /></div>
					<div id="texto"><br />3.	En esta ventana usted deberá<strong> indicar el número de párrafo </strong>al cual hace referencia su observación, seleccionando el número correspondiente en la caja selectora ubicada al lado de la palabra párrafo.</div>
					<div id="imagen" align="center"><em>Vista de la &quot;Caja Selectora de  Párrafo&quot;</em>:<br /><img src="images/help/obs02.jpg" width="599" height="361" /></div>
					<div id="texto"><br />En caso de proponer un párrafo nuevo usted deberá tildar la casilla<strong> "Solicitar como nuevo párrafo" </strong>y seleccionar la ubicación que el mismo debería ocupar en el documento en cuestión.</div>
					<div id="imagen" align="center"><em>Vista de &quot;Solicitud de Nuevo  Párrafo&quot;</em>:<br /><img src="images/help/obs03.jpg" width="599" height="361" /></div>
					<div id="texto"><br />4.	Modificar el texto original de acuerdo a su voluntad.<br />Si usted ha seleccionado un párrafo determinado en el paso anterior, verá en la caja de observación el texto original correspondiente a dicho párrafo, usted deberá modificarlo de acuerdo a su voluntad. Para realizar esta acción usted cuenta con herramientas de edición de texto similares al del programa Word, que le permitirán utilizar colores, tachar o subrayar palabras, usar negrita. Etc.<br /><br />Si en el paso anterior ha seleccionado la opción "Solicitar como nuevo párrafo", usted deberá escribir todo el texto correspondiente al nuevo párrafo que desea incorporar. </div>
					<div id="imagen" align="center"><em>Vista de la &quot;Caja de  Observación</em>&quot;:<br /><img src="images/help/obs04.jpg" width="599" height="361" /></div>
					<div id="texto"><p>5.	Presione el botón "Agregar Observación". <br />Aparecerá una ventana que pedirá su confirmación sobre el envio de la observación, si usted está seguro que desea enviarla presione "aceptar". Aparecerá en pantalla una alerta indicando que su observación se ha enviado con Ã©xito.</p></div>
					<div id="imagen" align="center"><em>Vista del botón "Agregar  Observación"</em>:<br /><img src="images/help/obs05.jpg" width="599" height="361" /></div><div id="texto"> <br /><strong>Las observaciones una vez enviadas no podrán ser modificadas.</strong></div>
				</div>
				<!--fin cuerpo-->
				<div id="subir"><a href="#top"><br />subir</a></div>
			</DIV>
			<!--fin item--> 
			<div id="item">
				<div id="titulo"><a name="verobs" id="verobs"></a>¿Cómo ver todas las observaciones realizadas a un Documento?</div>
				<div id="cuerpo">
					<div id="texto">Usted podrá ver todas las observaciones realizadas al documento principal, tanto las formuladas por su delegación como las formuladas por las delegaciones de los otros paises.<br /><br />1. Presionar el botón<strong> "Ver todas las observaciones"</strong> (ubicado en la caja de acciones disponibles) </div>
					<div id="imagen" align="center"><em>Vista del botón "Ver Todas las  Observaciones"</em>:<br /><img src="images/help/ver-obs01.jpg" width="599" height="361" /></div>
					<div id="texto"><br />2.	Se abrirá una ventana con todo el listado de observaciones realizadas hasta el momento.<br />En esta ventana usted podrá ver el Párrafo original seguido del listado completo de observaciones realizadas a dicho párrafo hasta el momento. </div>
					<div id="imagen" align="center"><em>Vista del párrafo original en  el listado de observaciones</em>:<br /><img src="images/help/ver-obs03.jpg" width="599" height="343" /></div>
					<div id="texto"><br />Cada Observación realizada se encuentra dentro de una caja subdividida en dos secciones.<br />En la sección de la izquierda usted podrá observar los datos de la Delegacón que ha realizado la observación, incluyendo: Nombre del país y su bandera , número de Párrafo, fecha y hora de la observación. </div>
					<div id="imagen" align="center"><em>Vista de los datos de la  delegación que ha realizado la observación</em>:<br /><img src="images/help/ver-obs04.jpg" width="599" height="343" /></div>
					<div id="texto"><br />En la sección de la derecha se observará la observación en sí.</div>
					<div id="imagen" align="center"><em>Vista de la observación en sí</em>:<br /><img src="images/help/ver-obs05.jpg" width="599" height="343" /></div>
					<div id="texto"><br />3.	Para salir de la ventana "ver todas las observaciones" presione el botón <strong>"volver"</strong></div>
					<div id="imagen" align="center">Vista del botón "volver":<br /><img src="images/help/ver-obs06.jpg" width="599" height="343" /></div>
				</div>
				<!--fin cuerpo-->
				<div id="subir"><a href="#top"><br />subir</a></div>
			</div>
			<!--fin item-->
			<div id="item">
				<div id="titulo"><a name="buscar" id="buscar"></a>¿Cómo buscar observaciones realizadas a un Documento?</div>
				<div id="cuerpo">
					<div id="texto">1.	Presionar el botón <strong>"Buscar observaciones"</strong> (ubicado en la caja de acciones disponibles)</div>
					<div id="imagen" align="center"><em>Vista del botón "Buscar Â Observaciones"</em>:<br /><img src="images/help/ver-obs07.jpg" width="599" height="343" /></div>
					<div id="texto"><br />2.	Se abrirá la ventana que contiene el "Buscador de Observaciones".<br />En este buscador usted deberá ingresar los parámetros de su búsqueda. <br />Las opciones disponibles para filtrar resultados son: Delegación, fecha exacta, rango de fechas, número de párrafo y palabras. <br />Usted puede completar sólo los campos de su interÃ©s. </div>
					<div id="imagen" align="center"><em>Vista del &quot;Buscador de  Observaciones&quot;</em>:<br /><img src="images/help/buscar-obs01.jpg" width="599" height="343" /></div>
					<div id="texto"><br />3.	Una vez ingresados los parámetros de búsqueda presionar el botón "buscar"</div>
					<div id="imagen" align="center"><em>Vista del botón "Buscar"</em>:<br /><img src="images/help/buscar-obs02.jpg" width="599" height="343" /></div>
					<div id="texto"><br />4.	Se abrirá una ventana con los resultados de su búsqueda.</div>
					<div id="imagen" align="center"><em>Vista de los &quot;Resultados de una  Búsqueda&quot;</em>:<br /><img src="images/help/buscar-obs03.jpg" width="599" height="343" /></div>
					<div id="texto"><br />5.	Para salir de los resultados de la Búsqueda presiones el botón "volver" </div>
					<div id="imagen" align="center"><em>Vista del Botón "Volver"</em>:<br /><img src="images/help/ver-obs06.jpg" width="599" height="343" /></div>
					<div id="texto"><br />6.	Usted será dirigido nuevamente al buscador de observaciones en donde podrá reformular su búsqueda, ingresando nuevos parámetros o salir completamente del buscador de observaciones presionando el botón "volver".</div>
					<div id="imagen" align="center"><em>Vista del botón "volver" para  salir del &quot;Buscador de Observaciones</em>&quot;:<br /><img src="images/help/buscar-obs05.jpg" width="599" height="343" /></div>
				</div>
				<!--fin cuerpo-->
				<div id="subir"><a href="#top"><br />subir</a></div>
			</div>
			<!--fin item-->
			<div id="item">
				<div id="titulo"><a name="descarga" id="descarga"></a>¿Cómo descargar el listado de observaciones y el documento?</div>
				<div id="cuerpo">
					<div id="texto">1.	Presionar el botón "Descargar PDF" (ubicado en la caja de Datos del Documento Principal)</div>
					<div id="imagen" align="center"><em>Vista del botón " Descargar  PDF"</em>:<br /><img src="images/help/descarga01.jpg" width="599" height="343" /></div>
					<div id="texto"><br />Al presionar el botón "Descargar PDF" se abrirá un cuadro de dialogo  donde usted deberá indicar si desea abrir el documento o guardarlo en su disco rígido.<br />Para poder visualizar documentos de formato PDF usted necesita tener instalado en su ordenar el programa Adobe Acrobat Reader, si no lo posee puede descargarlo gratuitamente desde la siguiente dirección: <br /><br /><a href="http://get.adobe.com/es/reader/">http://get.adobe.com/es/reader/</a></div>
				</div>
				<!--fin cuerpo-->
				<div id="subir"><a href="#top"><br />subir</a></div>
			</div>
			<!--fin item-->
		</div>
		<!-- fin ayuda -->
	</div>
</div>
<%@ include file="includes/footer.jsp" %>