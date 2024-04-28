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
    helpers_needed  INT,
    created         TIMESTAMP
);

CREATE TABLE IF NOT EXISTS feedback
(
    feedback_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    event_id    BIGINT NOT NULL,
    comment     TEXT,
    created     TIMESTAMP,
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
    created    TIMESTAMP,
    photo_url  VARCHAR
);

CREATE TABLE IF NOT EXISTS member_role
(
    member_role_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    member_id      BIGINT NOT NULL,
    member_level   INT,
    created        TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES member (member_id)
);

CREATE TABLE IF NOT EXISTS member_event
(
    member_event_id    BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    event_id           BIGINT NOT NULL,
    member_receiver_id BIGINT NOT NULL,
    member_manager_id  BIGINT,
    task               TEXT,
    points             INT,
    created            TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES event (event_id),
    FOREIGN KEY (member_receiver_id) REFERENCES member (member_id),
    FOREIGN KEY (member_manager_id) REFERENCES member (member_id)
);


INSERT INTO member (first_name, last_name, birthday, phone, email, points, password)
VALUES
    ('User', '1', '1993-05-21 00:00:00', '555-6789', 'aarav.patel@example.com', 0, 'Aarav2024!'),
    ('Luna', 'Garcia', '1989-10-09 00:00:00', '555-4321', 'luna.garcia@example.com', 2, 'LunaMoon!'),
    ('Kai', 'Zhao', '1991-03-18 00:00:00', '555-9876', 'kai.zhao@example.com', 4, 'KaiPass#123'),
    ('Nia', 'Thompson', '1986-07-24 00:00:00', '555-6543', 'nia.thompson@example.com', 7, 'Nia!2024'),
    ('Omar', 'Alvarez', '1994-12-12 00:00:00', '555-3210', 'omar.alvarez@example.com', 10, 'Omar*2024'),
    ('Zara', 'Ibrahim', '1987-11-30 00:00:00', '555-2134', 'zara.ibrahim@example.com', 13, 'ZaraSecure!'),
    ('Finn', 'Reilly', '1992-09-15 00:00:00', '555-7894', 'finn.oreilly@example.com', 18, 'FinnPass2024'),
    ('Tara', 'Singh', '1985-01-25 00:00:00', '555-4567', 'tara.singh@example.com', 21, 'Tara#2024'),
    ('Yuto', 'Nakamura', '1990-04-05 00:00:00', '555-8765', 'yuto.nakamura@example.com', 15, 'Yuto1234$'),
    ('Sofia', 'Martinez', '1988-08-19 00:00:00', '555-5674', 'sofia.martinez@example.com', 25, 'Sofia!@#'),

    ('Aaliyah', 'Brown', '1992-06-14 00:00:00', '555-1111', 'aaliyah.brown@example.com', 1, 'Aaliyah2024!'),
    ('Chloe', 'Smith', '1991-03-03 00:00:00', '555-5555', 'chloe.smith@example.com', 9, 'Chloe2024%'),
    ('Emma', 'Lee', '1990-04-21 00:00:00', '555-7777', 'emma.lee@example.com', 16, 'Emma2024&'),
    ('Olivia', 'Rodriguez', '1986-02-14 00:00:00', '555-9999', 'olivia.rodriguez@example.com', 23, 'Olivia2024!@'),
    ('Lucas', 'Perez', '1994-11-08 00:00:00', '555-0000', 'lucas.perez@example.com', 1, 'Lucas2024*!');


INSERT INTO event (event_title, event_date, attendance_type, comment, helpers_needed)
VALUES ('Community Clean-up', '2024-04-15 09:00:00', 'Other', 'Annual neighborhood park clean-up.', 5),
       ('Monthly Board Meeting', '2024-04-20 14:00:00', 'Monthly meeting', 'Monthly board meeting to discuss plans.',
        3),
       ('Team Building Trip', '2024-04-25 08:00:00', 'Trip', 'Team-building trip to the mountains.', 10),
       ('Fun Event for Kids', '2024-04-30 10:00:00', 'Motivational event', 'A fun event with activities for kids.', 15),
       ('Exceptional Project Meeting', '2024-05-02 13:00:00', 'Exceptional meeting',
        'Special meeting for project updates.', 7),
       ('Community Outreach', '2024-05-10 11:00:00', 'Meeting', 'Community outreach program discussion.', 4),
       ('Leadership Training', '2024-05-15 09:00:00', 'Other', 'Training for new team leaders.', 6),
       ('Environmental Awareness', '2024-05-20 10:00:00', 'Meeting', 'Meeting to discuss environmental initiatives.',
        5),
       ('Company Outing', '2024-05-25 08:00:00', 'Trip', 'Company-wide outing for team bonding.', 20),
       ('Neighborhood Block Party', '2024-05-30 15:00:00', 'Motivational event',
        'Annual block party for community engagement.', 25),
       ('Community Clean-up', '2024-04-15 09:00:00', 'In-person', 'Annual neighborhood park clean-up.', 5),
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
       (10, 10, 1, 'Provide feedback forms', 8),
--        just user registration
       (1, 1, NULL, 'Coordinate venue', 0),
       (2, 1, NULL, 'Arrange speakers', 0),
       (3, 1, NULL, 'Organize catering', 0),
       (4, 1, NULL, 'Handle logistics', 0),
       (5, 1, NULL, 'Manage budget', 0),
       (6, 1, NULL, 'Coordinate volunteers', 0),
       (7, 1, NULL, 'Promote event', 0),
       (8, 1, NULL, 'Handle registration', 0),
       (9, 1, NULL, 'Arrange transport', 0),
       (10, 1, NULL, 'Supervise setup', 0),
       (11, 1, NULL, 'Monitor security', 0),
       (12, 1, NULL, 'Coordinate clean-up', 0);


