create table url_mapping
(
    id int primary key auto_increment comment '主键',
    source_url varchar(255),
    destination_url varchar(255)
)charset  utf8;