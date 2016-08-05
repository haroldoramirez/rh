# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table area (
  id                            bigserial not null,
  nome                          varchar(255),
  nome_gerente                  varchar(255),
  constraint pk_area primary key (id)
);

create table beneficio (
  id                            bigserial not null,
  nome                          varchar(255),
  constraint pk_beneficio primary key (id)
);

create table cargo (
  id                            bigserial not null,
  nome                          varchar(255),
  constraint pk_cargo primary key (id)
);

create table endereco (
  id                            bigserial not null,
  nome_rua                      varchar(255),
  bairro                        varchar(255),
  cep                           varchar(255),
  constraint pk_endereco primary key (id)
);

create table pessoa (
  id                            bigserial not null,
  nome                          varchar(255),
  cpf                           varchar(255),
  local_nascimento              varchar(255),
  uf_nascimento                 varchar(255),
  nome_conjuge                  varchar(255),
  nome_pai                      varchar(255),
  nome_mae                      varchar(255),
  rg                            varchar(255),
  orgao_emissor_rg              varchar(255),
  centro_custo                  varchar(255),
  telefone                      varchar(255),
  celular                       varchar(255),
  email                         varchar(255),
  escolaridade                  varchar(255),
  salario                       varchar(255),
  nome_banco                    varchar(255),
  conta_agencia                 varchar(255),
  conta_numero                  varchar(255),
  conta_digito                  varchar(255),
  saldo_horas                   varchar(255),
  numero_pis                    varchar(255),
  genero                        integer,
  estado_civil                  integer,
  tipo                          integer,
  endereco_id                   bigint,
  cargo_id                      bigint,
  area_id                       bigint,
  data_nascimento               date,
  data_emissao_rg               date,
  data_admissao                 date,
  data_demissao                 date,
  data_ferias_inicio            date,
  data_ferias_fim               date,
  data_cadastro                 date,
  data_alteracao                date,
  constraint ck_pessoa_genero check (genero in (0,1)),
  constraint ck_pessoa_estado_civil check (estado_civil in (0,1,2,3,4)),
  constraint ck_pessoa_tipo check (tipo in (0,1,2,3)),
  constraint pk_pessoa primary key (id)
);

create table pessoa_beneficio (
  pessoa_id                     bigint not null,
  beneficio_id                  bigint not null,
  constraint pk_pessoa_beneficio primary key (pessoa_id,beneficio_id)
);

alter table pessoa add constraint fk_pessoa_endereco_id foreign key (endereco_id) references endereco (id) on delete restrict on update restrict;
create index ix_pessoa_endereco_id on pessoa (endereco_id);

alter table pessoa add constraint fk_pessoa_cargo_id foreign key (cargo_id) references cargo (id) on delete restrict on update restrict;
create index ix_pessoa_cargo_id on pessoa (cargo_id);

alter table pessoa add constraint fk_pessoa_area_id foreign key (area_id) references area (id) on delete restrict on update restrict;
create index ix_pessoa_area_id on pessoa (area_id);

alter table pessoa_beneficio add constraint fk_pessoa_beneficio_pessoa foreign key (pessoa_id) references pessoa (id) on delete restrict on update restrict;
create index ix_pessoa_beneficio_pessoa on pessoa_beneficio (pessoa_id);

alter table pessoa_beneficio add constraint fk_pessoa_beneficio_beneficio foreign key (beneficio_id) references beneficio (id) on delete restrict on update restrict;
create index ix_pessoa_beneficio_beneficio on pessoa_beneficio (beneficio_id);


# --- !Downs

alter table if exists pessoa drop constraint if exists fk_pessoa_endereco_id;
drop index if exists ix_pessoa_endereco_id;

alter table if exists pessoa drop constraint if exists fk_pessoa_cargo_id;
drop index if exists ix_pessoa_cargo_id;

alter table if exists pessoa drop constraint if exists fk_pessoa_area_id;
drop index if exists ix_pessoa_area_id;

alter table if exists pessoa_beneficio drop constraint if exists fk_pessoa_beneficio_pessoa;
drop index if exists ix_pessoa_beneficio_pessoa;

alter table if exists pessoa_beneficio drop constraint if exists fk_pessoa_beneficio_beneficio;
drop index if exists ix_pessoa_beneficio_beneficio;

drop table if exists area cascade;

drop table if exists beneficio cascade;

drop table if exists cargo cascade;

drop table if exists endereco cascade;

drop table if exists pessoa cascade;

drop table if exists pessoa_beneficio cascade;

