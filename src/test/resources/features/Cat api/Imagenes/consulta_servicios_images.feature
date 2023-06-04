#language: es
@images_services
Característica: consultar los servicios del path /images del API

@get_all_images
Escenario: Consulta exitosa del servicio GET que todas las imagenes aprobadas
  Dado Realizo la conexion al API
  Cuando Realizo la peticion al servicio "search"
  Entonces Obtengo un codigo de repuesta 200