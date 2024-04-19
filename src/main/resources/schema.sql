DROP TABLE IF EXISTS item CASCADE;
DROP TABLE IF EXISTS event CASCADE;
DROP TABLE IF EXISTS feedback CASCADE;
DROP TABLE IF EXISTS member CASCADE;
DROP TABLE IF EXISTS member_role CASCADE;
DROP TABLE IF EXISTS member_event CASCADE;

-- Create tables
CREATE TABLE IF NOT EXISTS event
(
    event_id        BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    event_title     VARCHAR,
    event_date      TIMESTAMP,
    attendance_type TEXT,
    comment         TEXT,
    helpers_needed  INT
);

CREATE TABLE IF NOT EXISTS feedback
(
    feedback_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    event_id    BIGINT NOT NULL,
    comment     TEXT,
    FOREIGN KEY (event_id) REFERENCES event (event_id)
);

CREATE TABLE IF NOT EXISTS member
(
    member_id  BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    first_name VARCHAR NOT NULL,
    last_name  VARCHAR NOT NULL,
    birthday   TIMESTAMP,
    phone      VARCHAR,
    email      VARCHAR,
    points     INT,
    password   VARCHAR,
    photo_url  VARCHAR
);

CREATE TABLE IF NOT EXISTS member_role
(
    member_role_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    member_id      BIGINT NOT NULL,
    member_level   INT,
    FOREIGN KEY (member_id) REFERENCES member (member_id)
);

CREATE TABLE IF NOT EXISTS member_event
(
    member_event_id    BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    event_id           BIGINT NOT NULL,
    member_receiver_id BIGINT NOT NULL,
    member_manager_id  BIGINT NOT NULL,
    task               TEXT,
    points             INT,
    FOREIGN KEY (event_id) REFERENCES event (event_id),
    FOREIGN KEY (member_receiver_id) REFERENCES member (member_id),
    FOREIGN KEY (member_manager_id) REFERENCES member (member_id)
);


INSERT INTO member (first_name, last_name, birthday, phone, email, points, password)
VALUES ('Aarav', 'Patel', '1993-05-21 00:00:00', '555-6789', 'aarav.patel@example.com', 120, 'Aarav2024!'),
       ('Luna', 'Garcia', '1989-10-09 00:00:00', '555-4321', 'luna.garcia@example.com', 280, 'LunaMoon!'),
       ('Kai', 'Zhao', '1991-03-18 00:00:00', '555-9876', 'kai.zhao@example.com', 175, 'KaiPass#123'),
       ('Nia', 'Thompson', '1986-07-24 00:00:00', '555-6543', 'nia.thompson@example.com', 215, 'Nia!2024'),
       ('Omar', 'Alvarez', '1994-12-12 00:00:00', '555-3210', 'omar.alvarez@example.com', 195, 'Omar*2024'),
       ('Zara', 'Ibrahim', '1987-11-30 00:00:00', '555-2134', 'zara.ibrahim@example.com', 320, 'ZaraSecure!'),
       ('Finn', 'Reilly', '1992-09-15 00:00:00', '555-7894', 'finn.oreilly@example.com', 250, 'FinnPass2024'),
       ('Tara', 'Singh', '1985-01-25 00:00:00', '555-4567', 'tara.singh@example.com', 135, 'Tara#2024'),
       ('Yuto', 'Nakamura', '1990-04-05 00:00:00', '555-8765', 'yuto.nakamura@example.com', 180, 'Yuto1234$'),
       ('Sofia', 'Martinez', '1988-08-19 00:00:00', '555-5674', 'sofia.martinez@example.com', 305, 'Sofia!@#');

INSERT INTO member (first_name, last_name, birthday, phone, email, points, password, photo_url)
VALUES ('John', 'Doe', '1990-04-25 00:00:00', '555-1234', 'john.doe@example.com', 120, 'pass123',
        'http://example.com/photos/johndoe.jpg'),
       ('Jane', 'Smith', '1988-07-19 00:00:00', '555-5678', 'jane.smith@example.com', 150, 'pass456',
        'http://example.com/photos/janesmith.jpg'),
       ('Alice', 'Johnson', '1992-11-01 00:00:00', '555-9876', 'alice.johnson@example.com', 90, 'pass789',
        'http://example.com/photos/alicejohnson.jpg'),
       ('Bob', 'Lee', '1985-02-17 00:00:00', '555-6543', 'bob.lee@example.com', 110, 'pass101',
        'http://example.com/photos/boblee.jpg'),
       ('Charlie', 'Brown', '1993-05-22 00:00:00', '555-3214', 'charlie.brown@example.com', 130, 'pass102',
        'http://example.com/photos/charliebrown.jpg'),
       ('Daisy', 'Miller', '1991-08-15 00:00:00', '555-2134', 'daisy.miller@example.com', 80, 'pass103',
        'http://example.com/photos/daisymiller.jpg'),
       ('Eva', 'Green', '1989-03-30 00:00:00', '555-4321', 'eva.green@example.com', 95, 'pass104',
        'http://example.com/photos/evagreen.jpg'),
       ('Frank', 'Wright', '1987-01-26 00:00:00', '555-8765', 'frank.wright@example.com', 120, 'pass105',
        'http://example.com/photos/frankwright.jpg'),
       ('Grace', 'Hopper', '1994-12-09 00:00:00', '555-2323', 'grace.hopper@example.com', 85, 'pass106',
        'http://example.com/photos/gracehopper.jpg'),
       ('Harry', 'Potter', '1990-07-31 00:00:00', '555-7777', 'harry.potter@example.com', 200, 'pass107',
        'http://example.com/photos/harrypotter.jpg');


INSERT INTO event (event_title, event_date, attendance_type, comment, helpers_needed)
VALUES ('Community Clean-up', '2024-04-15 09:00:00', 'In-person', 'Annual neighborhood park clean-up.', 5),
       ('Online Workshop: Gardening Basics', '2024-05-01 18:00:00', 'Virtual',
        'Learn the basics of gardening from home.', 2),
       ('Local Farmer''s Market', '2024-04-20 08:00:00', 'In-person', 'Support local farmers and enjoy fresh produce.',
        10),
       ('Book Club Meeting', '2024-04-25 19:30:00', 'Hybrid',
        'Discussing "The Great Gatsby". Remote attendees welcome.', 1),
       ('Tech Conference 2024', '2024-09-05 10:00:00', 'Hybrid', 'Annual technology conference. Speakers wanted.', 20),
       ('City Marathon', '2024-06-12 06:00:00', 'In-person',
        'City-wide marathon. Volunteers needed for water stations.', 50),
       ('Coding Bootcamp Introduction', '2024-07-07 10:00:00', 'Virtual',
        'Free introductory session to our 10-week coding bootcamp.', 3),
       ('Photography Contest', '2024-08-15 00:00:00', 'Virtual', 'Submit your best photos to win prizes.', 0),
       ('Health and Wellness Fair', '2024-10-10 09:00:00', 'In-person',
        'Explore health services, attend workshops, and more.', 15),
       ('Youth Soccer Tournament', '2024-11-20 08:00:00', 'In-person',
        'Local youth soccer teams compete. Volunteers needed.', 25);

INSERT INTO feedback (event_id, comment)
VALUES (1, 'Very well organized'),
       (2, 'Interesting topics but needed better speakers'),
       (3, 'Helpful for quarterly planning'),
       (4, 'Great location and activities'),
       (5, 'Exciting to see new products'),
       (6, 'Fantastic party! Well done.'),
       (7, 'Useful training session, learned a lot'),
       (8, 'Important info but session was too long'),
       (9, 'Great cause, well supported'),
       (10, 'Good networking opportunity but needed more time');

INSERT INTO member_role (member_id, member_level)
VALUES (1, 2),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 2),
       (9, 2),
       (10, 1);

INSERT INTO member_event (event_id, member_receiver_id, member_manager_id, task, points)
VALUES (1, 3, 1, 'Coordinate venue', 10),
       (2, 3, 1, 'Handle registrations', 8),
       (3, 5, 1, 'Prepare presentations', 7),
       (4, 7, 1, 'Organize transportation', 5),
       (5, 9, 1, 'Marketing and promotions', 12),
       (6, 2, 1, 'Setup event space', 9),
       (7, 4, 1, 'Tech support', 11),
       (8, 6, 1, 'Manage guest list', 6),
       (9, 8, 1, 'Collect donations', 10),
       (10, 10, 1, 'Provide feedback forms', 8);


