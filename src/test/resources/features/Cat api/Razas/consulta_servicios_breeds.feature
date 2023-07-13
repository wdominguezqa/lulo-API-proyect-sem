#language: es
Característica: consultar los servicios del path /breeds del API

@get_all_breads
Escenario: Consultar la  información de todas las razas
Dado se tiene un usuario con permisos del APICAT
Cuando se envia la peticion al servicio "breeds" para traer todo el listado de razas
Entonces se obtiene una respuesta exitosa