
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) values ('andres', '$2a$10$MTRC1iXus0451V1vOB5gAOXGcVlPoj0m59lErwYXkYsL/wg9mjwwO', 1, 'Andres', 'Prueba', 'andrew@gmail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) values ('admin', '$2a$10$TOCUfbe2iAQQ7zraRtWr1.DiwPepFs1A3wSl5FatCE9fO4KeIToje', 1, 'Administrador', 'Adm', 'admin@gmaio.com');

INSERT into roles (nombre) values ('ROLE_USER');
INSERT into roles (nombre) values ('ROLE_ADMIN');

insert into usuarios_roles (usuario_id, role_id) values (1,1);
insert into usuarios_roles (usuario_id, role_id) values (2,2);
insert into usuarios_roles (usuario_id, role_id) values (2,1);


INSERT INTO regiones (id, nombre) VALUES (1, 'Sudamérica');
INSERT INTO regiones (id, nombre) VALUES (2, 'Centroamérica');
INSERT INTO regiones (id, nombre) VALUES (3, 'Norteamérica');
INSERT INTO regiones (id, nombre) VALUES (4, 'Europa');
INSERT INTO regiones (id, nombre) VALUES (5, 'Asia');
INSERT INTO regiones (id, nombre) VALUES (6, 'Africa');

INSERT INTO clientes (region_id ,nombre, apellido, email, create_at) values (1, 'Andres', 'Cardozo', 'andres@gmail.com', '2020-01-15');
INSERT INTO clientes (region_id ,nombre, apellido, email, create_at) values (1,'Pepito', 'Peres', 'pepitp@gmail.com', '2019-04-05');
INSERT INTO clientes (region_id ,nombre, apellido, email, create_at) values (2,'Andrea', 'España', 'andrea1@gmail.com', '2004-01-15');
INSERT INTO clientes (region_id ,nombre, apellido, email, create_at) values (4, 'Claudio', 'Espinoza', 'claudio@gmail.com', '2010-01-20');
INSERT INTO clientes (region_id ,nombre, apellido, email, create_at) values (2, 'Andres', 'Cardozo', 'andres1@gmail.com', '2020-01-15');
INSERT INTO clientes (region_id ,nombre, apellido, email, create_at) values (3, 'Pepito', 'Peres', 'pepitp2@gmail.com', '2019-04-05');


insert into productos (nombre, precio, create_at) values ('Panasonic', 25900, curdate());
insert into productos (nombre, precio, create_at) values ('Sony', 20000, curdate());
insert into productos (nombre, precio, create_at) values ('Lenovo', 30000, curdate());
insert into productos (nombre, precio, create_at) values ('Asus', 40000, curdate());

insert into facturas(descripcion, observacion, cliente_id, create_at) VALUES ('Factura 1', null, 1, now());
insert into facturas_items(cantidad, factura_id, producto_id) VALUES (1,1,1);
insert into facturas_items(cantidad, factura_id, producto_id) VALUES (2,1,2);

insert into facturas(descripcion, observacion, cliente_id, create_at) VALUES ('Factura 2', null, 1, now());
insert into facturas_items(cantidad, factura_id, producto_id) VALUES (3,2,4);