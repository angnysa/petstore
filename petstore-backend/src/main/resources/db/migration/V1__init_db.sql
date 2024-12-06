create table category (
    id int not null primary key,
    name varchar(50) not null unique
);

create table pet (
    id int not null primary key,
    name varchar(50) not null unique,
    category_id int not null references category(id),
    status varchar not null
);

create table tag (
    id int not null primary key,
    name varchar(50) not null unique
);

create table pet_tag (
    pet_id int not null references pet(id),
    tag_id int not null references tag(id),

    primary key (pet_id, tag_id)
);

create table order_ (
    id int not null primary key,
    pet_id int not null references pet(id),
    quantity int not null,
    ship_date timestamp with time zone not null,
    status varchar not null,
    complete boolean not null
);

create table user_ (
    id int not null primary key,
    username varchar(50) not null unique,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(100) not null,
    password varchar not null,
    phone varchar(20) not null,
    status int not null
);
