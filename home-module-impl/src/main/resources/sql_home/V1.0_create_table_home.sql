create table if not exists homes (
   name varchar(17) not null,
   player_owner varchar(17) not null,
   location text not null,
   home_type smallint not null default 0 -- 0 = publica
);

-- cria chave primaria composta devido ao fato que uma home eh identificada pelo dono e pelo seu nome
alter table homes add constraint pk_homes primary key (name, player_owner);
