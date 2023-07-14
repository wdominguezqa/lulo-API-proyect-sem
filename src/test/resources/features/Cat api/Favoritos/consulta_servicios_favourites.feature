#language: es
Característica: consultar los servicios del path /favourites del API

@post_favourite_record
Escenario: Marcar como favorita una imagen
Dado se tiene un usuario con permisos del APICAT
Cuando se envia la peticion con la informacion necesario para marcar como favorita una imagen
Entonces se obtiene una respuesta exitosa

@delete_favourite_record
Escenario: Eliminar un registro de imagen favorita
Dado se tiene un usuario con permisos del APICAT
Y se consulta un registro de imagen favorita en el servicio "favourites"
Cuando se envia la peticion con el registro consultado para eliminarlo
Entonces se obtiene una respuesta exitosa
Y se consulta el registro
Y el registro no debe existir