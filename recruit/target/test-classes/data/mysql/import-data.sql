insert into ss_task (id, title, description, user_id) values(1, 'Study Play ','http://www.playframework.org/', 1);

insert into ss_user (id, login_name, name, password, salt, roles, register_date) values(1,'admin','Admin','691b14d79bf0fa2215f155235df5e670b64394cc','7efbd59d9741d34f','admin','2012-06-04 01:00:00');

insert into ss_resume (id, name, description, kvs,version) values(1, 'Ive','This is Ive'' desc', 'name:Ive;gendar:male;',1);
insert into ss_resume (id, name, description, kvs,version) values(2, 'Ive','This is Ive'' desc version2', 'name:Ive;gendar:male;',2);
insert into ss_resume (id, name, description, kvs,job_id) values(3, 'Jason','This is Jason'' desc', 'name:Jason;gendar:male;',1);
insert into ss_resumehis (id, name, description, kvs,job_id,pid,version) values(2, 'Jason','This is Jason'' desc', 'name:Jason;gendar:male;',1,1,1);

insert into ss_job (id, name, description, grade,version) values(1, '.Net Sr. Developer','http://www.playframework.org/', 'G1',1);
insert into ss_job (id, name, description, grade,version) values(2, 'C++ Sr. Developer','http://www.playframework.org/', 'G1',2);
insert into ss_jobhis (id, name, description, grade,pid,version) values(2, 'C++ Sr. Developer','http://www.playframework.org/', 'G1',2,1);
