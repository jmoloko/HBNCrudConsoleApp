CREATE TABLE skill
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE developer
(
    id        SERIAL PRIMARY KEY,
    firstName VARCHAR(100) NOT NULL,
    lastName  VARCHAR(100) NOT NULL
);

CREATE TABLE dev_skill
(
    dev_id INT NOT NULL,
    sk_id  INT NOT NULL,
    PRIMARY KEY (dev_id, sk_id),
    FOREIGN KEY (dev_id)
        REFERENCES developer (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (sk_id)
        REFERENCES skill (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE team
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE tm_dev
(
    tm_id  INT NOT NULL,
    dev_id INT NOT NULL,
    PRIMARY KEY (tm_id, dev_id),
    FOREIGN KEY (tm_id)
        REFERENCES team (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (dev_id)
        REFERENCES developer (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

INSERT INTO skill
VALUES (DEFAULT, 'Java'),
       (DEFAULT, 'C#'),
       (DEFAULT, 'Ruby'),
       (DEFAULT, 'PHP');

INSERT INTO developer
VALUES (DEFAULT, 'John', 'Doe'),
       (DEFAULT, 'Mike', 'Foe');

INSERT INTO dev_skill
VALUES (1, 1),
       (1, 4),
       (2, 2),
       (2, 3);

INSERT INTO team
VALUES (DEFAULT, 'Banking System');

INSERT INTO tm_dev
VALUES (1, 1),
       (1, 2);

commit;