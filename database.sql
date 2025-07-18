create database studyroom;
use studyroom;

create table member (
    id int auto_increment primary key,
    username varchar(50) not null unique,
    password varchar(100) not null,
    name varchar(50) not null
);

drop table study_application;

select * from member;
select * from study;
select * from study_apply;

SELECT * FROM study WHERE created_by IS NULL;


create table study (
    id int auto_increment primary key,
    title varchar(100) not null,
    description text,
    max_members int not null,
    deadline date,
    created_by int not null,
    foreign key (created_by) references member(id)
);

create table study_apply (
    id int auto_increment primary key,
    study_id int not null,
    member_id int not null,
    apply_at datetime default current_timestamp,
    unique (study_id, member_id),
    foreign key (study_id) references study(id),
    foreign key (member_id) references member(id)
);
