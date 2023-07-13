#language: es
Característica: consultar los servicios del path /favourites del API

@post_favourite_record
Escenario: Marcar como favorita una imagen
Dado se tiene un usuario con permisos del APICAT
Cuando se envia la peticion con la informacion necesario para marcar como favorita una imagen
Entonces se obtiene una respuesta exitosa