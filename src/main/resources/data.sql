-- magic of execution this field automatically is done by Spring Boot, @see https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html#howto-initialize-a-database-using-spring-jdbc

drop table if exists todo;
-- create table if not exists todo(id bigint auto_increment primary key, done boolean, text varchar(255));
create table if not exists todo(id identity primary key, done boolean, text varchar(255));
delete from todo;

-- ids removed from insert statements here to let identity auto-change.
-- otherwise we have to set identity manually
-- if we haven't done this, insertion of new records will fail with primary key duplication (identity will return records from 1)
insert into todo(done, text) values(true, 'Prepare presentation');
insert into todo(done, text) values(true, 'Procrastinate');
insert into todo(done, text) values(false, 'Have presentation');