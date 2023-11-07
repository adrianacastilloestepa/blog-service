create table post
(
    post_id       bigint       not null GENERATED BY DEFAULT AS IDENTITY,
    body          TEXT         not null,
    create_date   timestamp    not null,
    title         varchar(255) not null,
    primary key (post_id)
);
create table comment
(
    comment_id    bigint    not null GENERATED BY DEFAULT AS IDENTITY,
    body          TEXT      not null,
    create_date   timestamp not null,
    post_id       bigint    not null,
    primary key (comment_id),
    constraint FKh4c7lvsc298whoyd4w9ta25cr
        foreign key (post_id)
            references post
);