-- database m2_final_project
BEGIN TRANSACTION;

--ROLLBACK; 

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS users, score, scene, choice CASCADE;

-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************

--users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);


CREATE TABLE scene (
    scene_id serial NOT NULL,
    description bpchar NOT NULL,
    CONSTRAINT PK_scene PRIMARY KEY (scene_id)
);

CREATE TABLE score (
    score_id serial NOT NULL,
    user_id INT NOT NULL,
	last_played_scene_id INT NOT NULL,
    score INT NOT NULL CHECK (score >= 0),
    play_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_score PRIMARY KEY (score_id),
	CONSTRAINT FK_score_scene FOREIGN KEY (last_played_scene_id) REFERENCES scene(scene_id),
    CONSTRAINT FK_score_user FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE choice (
    choice_id serial NOT NULL,
    scene_id INT NOT NULL,
    description bpchar NOT NULL,
	is_safe BOOLEAN NULL,
    consequence bpchar NULL,
    CONSTRAINT PK_choice PRIMARY KEY (choice_id),
    CONSTRAINT FK_choice_scene FOREIGN KEY (scene_id) REFERENCES scene(scene_id)
);


-- *************************************************************************************************
-- Insert some sample starting data
-- *************************************************************************************************

-- Users
-- Password for all users is password
INSERT INTO
    users (username, password_hash, role)
VALUES
    ('user', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
    ('admin','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN');

INSERT INTO users (username, password_hash, role) VALUES ('Judge Wargrave', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('Vera Claythorne', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('Philip Lombard', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('Dr. E.G. Armstrong', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('W.H. Blore', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('Emily Brent', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('General John G. Macarthur', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('Anthony Marston', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('Thomas Rogers', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER');

INSERT INTO scene (description) VALUES ('You awake in a mostly empty room, no recollection of how you got here. You see only a couch, beige and dusty-looking, and a fish tank. Closer inspection reveals those are piranhas inside the fish tank. Also inside the fish tank appears to be a house key, shiny against the blackstones of the aquarium. Seeing the key, you notice there is also a door, bright red against the white walls of the room. You check your pockets, and find, not a key, but a coin. No ordinary mint, no mere quarter, this coin features a skull on one side and two skulls on the other. And, sure enough, the door is locked. You check it again. You slam your shoulder against it. Great, now your shoulder hurts, too. It would appear that you have only a few choices:');
INSERT INTO scene (description) VALUES ('You’re soaking wet as you enter the next room. Immediately, your flesh prickles in the chilly air. This is a cold and dark place. Hear your footsteps resound with an eerily familiar, solemn timbre. ‘Hello!’ you try, and choke it back, afraid suddenly. It’s this darkness. You’ve never liked the darkness, especially behind you. Maybe there’s something you missed in the first room. Wait . . . Where’s the door? You gulp, but your throat seizes mid-swallow. Your jaw is chattering madly. Somehow, the door is gone. It’s just endless darkness in every direction. Wait. What’s that sound? Is that . . . Is that someone whispering? Is that someone whispering ‘This way’?! You think it’s coming from that direction. ');
INSERT INTO scene (description) VALUES ('bla bla bla');
INSERT INTO scene (description) VALUES ('hey hey hey');
										
INSERT INTO score (user_id, last_played_scene_id, score, play_date) VALUES (1, 1, 0, '12-25-2019');
INSERT INTO score (user_id, last_played_scene_id, score, play_date) VALUES (2, 1, 0, '12-24-2019');
INSERT INTO score (user_id, last_played_scene_id, score, play_date) VALUES (3, 1, 0, '12-25-2019');
INSERT INTO score (user_id, last_played_scene_id, score, play_date) VALUES (4, 1, 0, '12-24-2019');
INSERT INTO score (user_id, last_played_scene_id, score, play_date) VALUES (5, 1, 0, '12-24-2019');
INSERT INTO score (user_id, last_played_scene_id, score, play_date) VALUES (6, 1, 0, '12-25-2019');
INSERT INTO score (user_id, last_played_scene_id, score, play_date) VALUES (7, 1, 0, '12-24-2019');
INSERT INTO score (user_id, last_played_scene_id, score, play_date) VALUES (8, 1, 0, '12-25-2019');
INSERT INTO score (user_id, last_played_scene_id, score, play_date) VALUES (9, 1, 0, '12-24-2019');



INSERT INTO choice (scene_id, description, is_safe, consequence) VALUES (1, 'You could check underneath the couch, root through the cushions, and hope to find a duplicate key.', false,'Check the couch. First you take a look underneath. You can see well enough under there to conclude there is no key. Naturally, you decide to check through the cushions of the couch, where things are always hidden. Ouch! What was that? Something pricked your hand when you put it in there. You try again. This time the prick feels more like a bite. A bite from very sharp fangs. Quickly, you withdraw your hand. A scream escapes your throat as you see that attached to your now-bloody hand is a vicious, angry-looking snake with a shiny black head and a coppery-colored body. Vaguely, as consciousness, and your life, as it turns out, is leaving you, you recall this type of snake is called an inland taipan. Its venom is the most toxic in the world. Unfortunately, you are dead.');
INSERT INTO choice (scene_id, description, is_safe, consequence) VALUES (1, 'You could try to snatch that key out of the piranha tank, and hope that you can get it out faster than the fish can get you, not to mention, hope that this key is even the right one for the door.', true, 'Get the key from the piranha tank. Fortune favors the bold, you think as you plunge your hand into the water of the aquarium. You are fast, but not fast enough. The piranhas swarm your hand, ripping chunks of flesh off with with their razor-sharp teeth like tiny, tiny knives. You scream. Pull your hand out of the water so fast you lose your balance. Reeling backwards you reach out for the only thing within your grasp, which happens to be the lip of the piranha tank. As you fall backwards, you bring the piranha tank down with you. The tank crashes to the floor, shattering, spilling its contents throughout the room. The piranhas go chittering across the floorboards. And there at your feet is the key. You are now soaking wet, but the piranhas are no longer a threat. You use the key in the lock, and it works! You open the door and walk into the next room.');
INSERT INTO choice (scene_id, description, is_safe, consequence) VALUES (1, 'You could flip the coin.', NULL, NULL);
INSERT INTO choice (scene_id, description, is_safe, consequence) VALUES (2, 'You could try to follow the whispering and hope that it’s coming from someone or something friendly.', true, 'You do your best echolocation, following the persistent whispering. You know you must be getting closer because the words ‘This way’ are becoming louder and more distinct now. You bump into a tree. You’re certain you’ve found the source of the whispering. You feel all around the tree trunk, and discover a gaping, portal-like hole in its side. You enter the tree.');
INSERT INTO choice (scene_id, description, is_safe, consequence) VALUES (2, 'You could stumble through the darkness in the opposite direction and possibly find another way out.', false, 'Follow the whispering? Over your dead body, you think, not realizing how right you are. Instead you stay where you are, spinning around, trying to let your eyes adjust to the dark. But the things watching you have perfect night vision. They see you perfectly, they’re just biding your time until you’re nice and panicky and all the easier to take down. They’re circling you now, silently, licking their lips. You hear them coming, but too late.');
INSERT INTO choice (scene_id, description, is_safe, consequence) VALUES (2, 'You could flip the coin.', NULL, NULL);
INSERT INTO choice (scene_id, description, is_safe, consequence) VALUES (3, 'Choice 1.', true,'ya ya ya');
INSERT INTO choice (scene_id, description, is_safe, consequence) VALUES (3, 'Choice 2.',false,'na na na');
INSERT INTO choice (scene_id, description, is_safe, consequence) VALUES (3, 'You could flip the coin.', NULL, NULL);
INSERT INTO choice (scene_id, description, is_safe, consequence) VALUES (4, 'Choice 1.', false,'ya ya ya');
INSERT INTO choice (scene_id, description, is_safe, consequence) VALUES (4, 'Choice 2.', false,'na na na');
INSERT INTO choice (scene_id, description, is_safe, consequence) VALUES (4, 'You could flip the coin.', NULL, NULL);

COMMIT TRANSACTION;
