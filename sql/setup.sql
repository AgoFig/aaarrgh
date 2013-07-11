create table persona (
	id int not null,
	nombre varchar(255) null,
	apellido varchar(255) null,
	edad int null
);

insert into persona values (1,'Cosme', 'Fulanito', 42);

# Aca habria que agregar nuestras tablas:               #
# usuario (id_user, user, pass, nombre, apellido, mail) #
# tweet (id_tweet, tweet, id_user -FK-)                 #
# sigue (seguidor, seguido)                             #

create table usuario (
	id_user int not null,
	user varchar(255) null,
	pass varchar(255) null,
	nombre varchar(255) null,
	apellido varchar(255) null,
	mail varchar(255) null
);

insert into usuario values (1,'cosmefu','1324','Cosme', 'Fulanito', 'cf@coco.com');
