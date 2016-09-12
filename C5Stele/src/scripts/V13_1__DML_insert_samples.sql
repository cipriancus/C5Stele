INSERT INTO positions(code, description) VALUES ("Programator", "Software engineer");
INSERT INTO positions(code, description) VALUES ("Tester", "Quality assurance");

INSERT INTO projects(description, project_name, agencies_id) VALUES ("Good project", "SuperPro", 1);
INSERT INTO projects(description, project_name, agencies_id) VALUES ("Best project", "BestPro", 2);
INSERT INTO projects(description, project_name, agencies_id) VALUES ("Worst project", "WorstPro", 1);

INSERT INTO teams(team_name, projects_id) VALUES ("Racheta", 1);
INSERT INTO teams(team_name, projects_id) VALUES ("Bonfire", 2);
INSERT INTO teams(team_name, projects_id) VALUES ("Casa Nostra", 3);

INSERT INTO languages(CODE, NAME) VALUES ('en-GB', 'English');
INSERT INTO languages(CODE, NAME) VALUES ('jp-JP', 'Japanese');
INSERT INTO languages(CODE, NAME) VALUES ('de-DE', 'Germany');

INSERT INTO countries (NAME, LANGUAGES_ID) VALUES ('Great Britain', 2);
INSERT INTO countries (NAME, LANGUAGES_ID) VALUES ('Japan', 3);
INSERT INTO countries (NAME, LANGUAGES_ID) VALUES ('Germany', 4);
