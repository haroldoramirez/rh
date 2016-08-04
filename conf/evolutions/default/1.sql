# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table colaborador (
  id                            bigserial not null,
  nome                          varchar(255),
  telefone                      varchar(255),
  cpf                           varchar(255),
  email                         varchar(255),
  cargo                         varchar(255),
  data_admissao                 date,
  constraint pk_colaborador primary key (id)
);


# --- !Downs

drop table if exists colaborador cascade;

