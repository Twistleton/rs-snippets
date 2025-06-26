ALTER TABLE
    `hostdaten`.`insight_umdat` ADD PRIMARY KEY (`insight_ord_id`)

ALTER TABLE insight_umpos
    ADD CONSTRAINT FK_UMPOS_UMDAT
        FOREIGN KEY (insight_ord_id)
            REFERENCES insight_umdat(insight_ord_id)
                ON DELETE CASCADE;


// --- example

CREATE TABLE buildings (
                           building_no BIGINT PRIMARY KEY AUTO_INCREMENT,
                           building_name VARCHAR(255) NOT NULL,
                           address VARCHAR(255) NOT NULL
);

CREATE TABLE rooms (
                       room_no BIGINT PRIMARY KEY AUTO_INCREMENT,
                       room_name VARCHAR(255) NOT NULL,
                       building_no BIGINT NOT NULL
);

ALTER TABLE rooms
    ADD CONSTRAINT fk_rooms_building_no
        FOREIGN KEY (building_no)
            REFERENCES buildings (building_no)
                ON DELETE CASCADE;


INSERT INTO buildings(building_name,address)
VALUES('ACME Headquaters','3950 North 1st Street CA 95134'),
      ('ACME Sales','5000 North 1st Street CA 95134');


INSERT INTO rooms(room_name,building_no)
VALUES('Amazon',1),
      ('War Room',1),
      ('Office of CEO',1),
      ('Marketing',2),
      ('Showroom',2);
