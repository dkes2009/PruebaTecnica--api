# README #
ExplicaicoComponente ApiPruebatecnica


COMPONENTE:  ApiPruebatecnica
Este proyecto es una Api de creacion de Usuarios y de tickets que cumple con los requisistos necesarios para 
la prueba tecnica
esta API elaborada en spring boot 2.2.7, usa una conexion de base de datos en H2, base que se guarda en memoria
con la siguiente URL http://localhost:8080/h2-console/login.do?jsessionid=fd6e12b956b922f17d82e6f69dca4982
como usuario: " sa " y la contraseña: "password"
como seguridad cuenta con una autenticacion JWT donde se contiene una 
secrekey "token.secrect.key =3TX#d8d*TZxNG881Yf8W5"
para la configuracion del mismo JWT como a su ves se realiza la creacion de un Usuario y contraña para realiza los consimos
de los endpoint, cada endpoint usa un JWT esto con el fin de tener uso exclusivo de consumos permitidos a solo los usuarios validos
generar um tocket

{
"userName": "ApiPruebatecnica",
"password": "123456"
}

ademas la contraseña al guardarla en la base de datos se en encryta con BCrypt 

se adjunta la collecion de postman para realizar las pruebas de cada uno de los endpoint
" PruebaTecnica.postman_collection.json "


Ambiente Pasos para la Instalacion
Para este ambiente es necesario tener java 11 openJDK

se recomienda antes de subir el servicio mandar por consola o ayudas de maven el comando "mvn clean"



Servidores: localhost
puerto 8080
	
