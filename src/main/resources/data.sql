-- application.yml에 하기 설정이 있어야 H2 db 테이블 생성 이후에 데이터 입력 가능
-- jpa: defer-datasource-initialization: true
insert into account_user(id, name, created_at, updated_at)
values(1,'Pororo',now(),now());
insert into account_user(id, name, created_at, updated_at)
values(2,'Lupi',now(),now());
insert into account_user(id, name, created_at, updated_at)
values(3,'Eddie',now(),now());