#language: es
@images_services
Característica: consultar los servicios del path /images del API

@get_all_images
Escenario: Consulta exitosa del servicio GET para obtener todas las imagenes aprobadas
  Dado se tiene un usuario con permisos del APICAT
  Cuando se envia la peticion al servicio "search" para traer todo el listado de imagenes aprobadas
  Entonces se obtiene una respuesta exitosa

@get_specific_image
Escenario: Consultar la información de una imagen en especifico
  Dado se tiene un usuario con permisos del APICAT
  Cuando se envia la peticion con el id "gwpO8VqF7" de la imagen
  Entonces se obtiene una respuesta exitosa
  Y la informacion recibida corresponde a la consultada segun el id "gwpO8VqF7"

@upload_image
Escenario: Subir una imagen al repositorio de imagenes del APICAT
  Dado se tiene un usuario con permisos del APICAT
  Cuando se envia la peticion con la informacion necesario para subir la imagen
  Entonces se obtiene una respuesta exitosa para la peticion realizada
  Y se valida que el registro de la imagen existe en el repositorio del APICAT

@delete_an_image
Escenario: Borrar registro de imagen cargada al repositorio de imagenes del APICAT
  Dado se tiene un usuario con permisos del APICAT
  Cuando se envia la peticion para borrar el registro con el id "svRROiE6l" de la imagen
  Entonces la imagen se elimina del repositorio de imagenes del APICAT
