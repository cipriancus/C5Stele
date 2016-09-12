INSERT INTO languages(CODE, NAME, LAST_MODIFIED_AT, LAST_MODIFIED_BY) VALUES ('RO', 'Romanian', '2016/08/05', 'cipriancus');
INSERT INTO countries (NAME, LAST_MODIFIED_AT, LAST_MODIFIED_BY, LANGUAGES_ID) VALUES ('Romania', '2016/08/05', 'cipriancus', 1);
INSERT INTO agencies (CITY, COUNTRIES_ID, LAST_MODIFIED_AT, LAST_MODIFIED_BY) VALUES ('Iasi', 1, '2016/08/05', 'cipriancus');
INSERT INTO agencies (CITY, COUNTRIES_ID, LAST_MODIFIED_AT, LAST_MODIFIED_BY) VALUES ('Cluj', 1, '2016/08/05', 'cipriancus');
INSERT INTO periods(LAST_RECOMMENDATION_DAY, LAST_VOTING_DAY, ACTIVE, COUNTRIES_ID) VALUES ('2016-08-16','2016-08-27',1,1);
INSERT INTO roles(ROLE_TYPE, DESCRIPTION) VALUES ("admin", "All powerful administrator");
INSERT INTO roles(ROLE_TYPE, DESCRIPTION) VALUES ("user", "Regular user");
INSERT INTO users (FIRST_NAME, LAST_NAME, TITLE, PHONE_NUMBER, EMAIL, USERNAME, PASSWORD, ACTIVE, ROLES_ID, AGENCIES_ID, LAST_MODIFIED_AT, LAST_MODIFIED_BY) VALUES ('Ciprian', 'Ciprian', 'Mr', '741025591', 'cipriancus@gmail.com', 'cipriancus', '1234', 1, 1, 1, '2016-08-05', 'cipriancus');
