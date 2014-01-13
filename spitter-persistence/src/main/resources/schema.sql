drop table if exists spittle;
drop table if exists spitter;

CREATE TABLE spitter (
    id INT not null
                   GENERATED ALWAYS AS IDENTITY
                   (START WITH 1, INCREMENT BY 1),
    username varchar(50) NOT NULL,
    password varchar(25) NOT NULL,
    fullname varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

create table spittle (
    id INT not null
               GENERATED ALWAYS AS IDENTITY
               (START WITH 1, INCREMENT BY 1),
    spitter_id integer NOT NULL,
    message varchar(2000) NOT NULL,
    whencreated date NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (spitter_id) references spitter(id)
);