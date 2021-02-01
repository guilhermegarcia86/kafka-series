create table complaint_taxpayer (
	document varchar(255) not null,
    email varchar(255),
    name varchar(255),
    status varchar(255),
    primary key (document)
) engine=InnoDB;
    
create table defaulted_taxpayer (
	document varchar(255) not null,
    email varchar(255),
    name varchar(255),
    status varchar(255),
    primary key (document)
) engine=InnoDB;