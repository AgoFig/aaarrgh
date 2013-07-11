# Aca habria que agregar nuestras tablas:               #
# usuario (iduser, user, pass, nombre, apellido, mail) #
# tweet (idtweet, tweet, iduser -FK-)                 #
# sigue (seguidor, seguido)                             #

create table usuario (
	id_user int not null,
	user varchar(255) null,
	pass varchar(255) null,
	nombre varchar(255) null,
	apellido varchar(255) null,
	mail varchar(255) null
);

insert into usuario values (1,'cosmefu','comesf','Cosme', 'Fulanito', 'cf@coco.com');
