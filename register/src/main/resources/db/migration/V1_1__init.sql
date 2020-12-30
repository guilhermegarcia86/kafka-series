
INSERT INTO register.`user`
(email, pass)
VALUES('jonhdoe@email.com', '$2a$10$JgDI7KttG8BX9AO.3mGTref9mjDxHKtx3sjqnaP3Vq88BzUNxA38S');

INSERT INTO register.perfil
(name)
VALUES('USER');

INSERT INTO register.perfil
(name)
VALUES('ADMIN');

INSERT INTO register.user_perfis
(user_id, perfis_id)
VALUES(1, 1);
