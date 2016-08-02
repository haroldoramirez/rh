# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table colaborador (
  id                            bigint not null,
  nome                          varchar(255),
  constraint pk_colaborador primary key (id)
);
create sequence colaborador_seq;


# --- !Downs

drop table if exists colaborador;
drop sequence if exists colaborador_seq;

