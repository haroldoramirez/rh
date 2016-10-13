# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table area (
  id                            bigserial not null,
  nome                          varchar(255),
  nome_gerente                  varchar(255),
  data_cadastro                 date,
  data_alteracao                date,
  constraint pk_area primary key (id)
);

create table beneficio (
  id                            bigserial not null,
  nome                          varchar(255),
  data_cadastro                 date,
  data_alteracao                date,
  constraint pk_beneficio primary key (id)
);

create table cargo (
  id                            bigserial not null,
  nome                          varchar(255),
  data_cadastro                 date,
  data_alteracao                date,
  constraint pk_cargo primary key (id)
);

create table endereco (
  id                            bigserial not null,
  nome_rua                      varchar(255),
  bairro                        varchar(255),
  cep                           varchar(255),
  constraint pk_endereco primary key (id)
);

create table escolaridade (
  id                            bigserial not null,
  nome                          varchar(255),
  data_cadastro                 date,
  data_alteracao                date,
  constraint pk_escolaridade primary key (id)
);

create table estado_civil (
  id                            bigserial not null,
  nome                          varchar(255),
  data_cadastro                 date,
  data_alteracao                date,
  constraint pk_estado_civil primary key (id)
);

create table genero (
  id                            bigserial not null,
  nome                          varchar(255),
  data_cadastro                 date,
  data_alteracao                date,
  constraint pk_genero primary key (id)
);

create table pais (
  id                            bigserial not null,
  nome                          varchar(255),
  constraint pk_pais primary key (id)
);

create table pessoa (
  id                            bigserial not null,
  nome                          varchar(255),
  cpf                           varchar(255),
  local_nascimento              varchar(255),
  nome_conjuge                  varchar(255),
  nome_pai                      varchar(255),
  nome_mae                      varchar(255),
  rg                            varchar(255),
  orgao_emissor_rg              varchar(255),
  centro_custo                  varchar(255),
  telefone                      varchar(255),
  celular                       varchar(255),
  email                         varchar(255),
  salario                       varchar(255),
  nome_banco                    varchar(255),
  nome_agencia                  varchar(255),
  conta_numero                  varchar(255),
  saldo_horas                   varchar(255),
  numero_pis                    varchar(255),
  endereco                      varchar(255),
  bairro                        varchar(255),
  cidade                        varchar(255),
  cep                           varchar(255),
  complemento                   varchar(255),
  escolaridade_id               bigint,
  genero_id                     bigint,
  estado_civil_id               bigint,
  tipo_id                       bigint,
  pais_id                       bigint,
  cargo_id                      bigint,
  area_id                       bigint,
  beneficio_id                  bigint,
  data_nascimento               date,
  data_emissao_rg               date,
  data_admissao                 date,
  data_demissao                 date,
  data_ferias_inicio            date,
  data_ferias_fim               date,
  data_cadastro                 date,
  data_alteracao                date,
  constraint pk_pessoa primary key (id)
);

create table tipo (
  id                            bigserial not null,
  nome                          varchar(255),
  data_cadastro                 date,
  data_alteracao                date,
  constraint pk_tipo primary key (id)
);

create table usuario (
  id                            bigserial not null,
  email                         varchar(60) not null,
  senha                         varchar(60) not null,
  data_cadastro                 date,
  data_alteracao                date,
  constraint uq_usuario_email unique (email),
  constraint pk_usuario primary key (id)
);

alter table pessoa add constraint fk_pessoa_escolaridade_id foreign key (escolaridade_id) references escolaridade (id) on delete restrict on update restrict;
create index ix_pessoa_escolaridade_id on pessoa (escolaridade_id);

alter table pessoa add constraint fk_pessoa_genero_id foreign key (genero_id) references genero (id) on delete restrict on update restrict;
create index ix_pessoa_genero_id on pessoa (genero_id);

alter table pessoa add constraint fk_pessoa_estado_civil_id foreign key (estado_civil_id) references estado_civil (id) on delete restrict on update restrict;
create index ix_pessoa_estado_civil_id on pessoa (estado_civil_id);

alter table pessoa add constraint fk_pessoa_tipo_id foreign key (tipo_id) references tipo (id) on delete restrict on update restrict;
create index ix_pessoa_tipo_id on pessoa (tipo_id);

alter table pessoa add constraint fk_pessoa_pais_id foreign key (pais_id) references pais (id) on delete restrict on update restrict;
create index ix_pessoa_pais_id on pessoa (pais_id);

alter table pessoa add constraint fk_pessoa_cargo_id foreign key (cargo_id) references cargo (id) on delete restrict on update restrict;
create index ix_pessoa_cargo_id on pessoa (cargo_id);

alter table pessoa add constraint fk_pessoa_area_id foreign key (area_id) references area (id) on delete restrict on update restrict;
create index ix_pessoa_area_id on pessoa (area_id);

alter table pessoa add constraint fk_pessoa_beneficio_id foreign key (beneficio_id) references beneficio (id) on delete restrict on update restrict;
create index ix_pessoa_beneficio_id on pessoa (beneficio_id);


# --- !Downs

alter table if exists pessoa drop constraint if exists fk_pessoa_escolaridade_id;
drop index if exists ix_pessoa_escolaridade_id;

alter table if exists pessoa drop constraint if exists fk_pessoa_genero_id;
drop index if exists ix_pessoa_genero_id;

alter table if exists pessoa drop constraint if exists fk_pessoa_estado_civil_id;
drop index if exists ix_pessoa_estado_civil_id;

alter table if exists pessoa drop constraint if exists fk_pessoa_tipo_id;
drop index if exists ix_pessoa_tipo_id;

alter table if exists pessoa drop constraint if exists fk_pessoa_pais_id;
drop index if exists ix_pessoa_pais_id;

alter table if exists pessoa drop constraint if exists fk_pessoa_cargo_id;
drop index if exists ix_pessoa_cargo_id;

alter table if exists pessoa drop constraint if exists fk_pessoa_area_id;
drop index if exists ix_pessoa_area_id;

alter table if exists pessoa drop constraint if exists fk_pessoa_beneficio_id;
drop index if exists ix_pessoa_beneficio_id;

drop table if exists area cascade;

drop table if exists beneficio cascade;

drop table if exists cargo cascade;

drop table if exists endereco cascade;

drop table if exists escolaridade cascade;

drop table if exists estado_civil cascade;

drop table if exists genero cascade;

drop table if exists pais cascade;

drop table if exists pessoa cascade;

drop table if exists tipo cascade;

drop table if exists usuario cascade;

