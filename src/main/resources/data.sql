select nextval ('seq_usuario')
insert into tb_usuario (usu_login, usu_senha, usu_id) VALUES ('teste.higor', '$2a$10$8/t4vTLMX.fXX3icicuC.O.KaLS0wp/Y18gHn0YD8Wu8mXhUYSimy', 1)
select nextval ('seq_usuario')
insert into tb_usuario (usu_login, usu_senha, usu_id) VALUES ('teste.higor.2', '$2a$10$8/t4vTLMX.fXX3icicuC.O.KaLS0wp/Y18gHn0YD8Wu8mXhUYSimy', 2)
select nextval ('seq_postagem')
insert into tb_postagem (usu_id, post_id, post_texto) VALUES (1, 1, 'Postagem do higor araujo')
select nextval ('seq_comentario')
insert into tb_comentario (usu_id, coment_id, post_id, coment_texto) VALUES (1, 1, 1, 'Comentario do Higor Araujo')
