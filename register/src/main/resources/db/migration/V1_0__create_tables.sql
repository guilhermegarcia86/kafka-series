
create table perfil (
    id integer not null auto_increment,
    name varchar(255),
    primary key (id)
) engine=InnoDB;

create table user (
    id integer not null auto_increment,
    email varchar(255),
    pass varchar(255),
    primary key (id)
) engine=InnoDB;

create table user_perfis (
    user_id integer not null,
    perfis_id integer not null,
    primary key (user_id, perfis_id)
) engine=InnoDB;

alter table user_perfis add constraint perfil_id_constraint foreign key (perfis_id) references perfil (id);

alter table user_perfis add constraint user_id_constraint foreign key (user_id) references user (id);
