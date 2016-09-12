CREATE TABLE FAQ
(
	ID INT,
	QUESTION VARCHAR(255),
	ANSWER VARCHAR(255),
	ORDER_OF_QUESTIONS INT,
	PRIMARY KEY(ID)
);

CREATE TABLE PROJECTS
(
	ID INT,
	DESCRIPTION VARCHAR(255),
	AGENCIES_ID INT,
	PRIMARY KEY(ID),
	CONSTRAINT FK_PROJECTS__AGENCIES FOREIGN KEY(AGENCIES_ID)
	REFERENCES AGENCIES(ID)
);

CREATE TABLE TEAMS
(
	ID INT,
	TEAM_NAME VARCHAR(45),
	PROJECTS_ID INT,
	PRIMARY KEY(ID),
	CONSTRAINT FK_TEAMS__PROJECTS FOREIGN KEY(PROJECTS_ID)
	REFERENCES PROJECTS(ID)
);

ALTER TABLE USERS
ADD COLUMN TEAMS_ID INT;

ALTER TABLE USERS
ADD CONSTRAINT FK_USERS__TEAMS FOREIGN KEY(TEAMS_ID)
REFERENCES TEAMS(ID);

CREATE TABLE TEAM_CANDIDATES
(
	ID INT,
	PERIOD_ID INT,
	TEAMS_PROPOSED_BY_USER_ID INT,
	SELECTED_TEAMS_ID INT,
	REASONS VARCHAR(255),
	DATE DATE,
	ACTIVE BOOLEAN,
	LAST_MODIFIED_AT DATE,
    LAST_MODIFIED_BY VARCHAR(45),
    PRIMARY KEY(ID),
    CONSTRAINT FK_TEAM_CANDIDATES_PROPOSED_BY__USERS FOREIGN KEY(TEAMS_PROPOSED_BY_USER_ID)
    REFERENCES USERS(ID),
    CONSTRAINT FK_TEAM_CANDIDATES_SELECTED_BY__USERS FOREIGN KEY(SELECTED_TEAMS_ID)
    REFERENCES TEAMS(ID),
    CONSTRAINT FK_TEAMS_CANDIDATES__PERIODS FOREIGN KEY (PERIOD_ID)
    REFERENCES PERIODS(ID)
);

CREATE TABLE TEAM_VOTES
(
	ID INT,
	USERS_ID INT,
	TEAM_CANDIDATES_ID INT,
	ACTIVE BOOLEAN,
	LAST_MODIFIED_AT DATE,
    LAST_MODIFIED_BY VARCHAR(45),
    PRIMARY KEY(ID),
    CONSTRAINT UX_USERS_TEAM_CANDIDATES UNIQUE (USERS_ID,TEAM_CANDIDATES_ID),
    CONSTRAINT FK_TEAM_VOTES__USERS FOREIGN KEY(USERS_ID)
    REFERENCES USERS(ID),
    CONSTRAINT FK_VOTES__TEAM_CANDIDATES FOREIGN KEY(TEAM_CANDIDATES_ID)
    REFERENCES TEAM_CANDIDATES(ID)
);

ALTER TABLE CANDIDATES
ADD COLUMN REASONS VARCHAR(255);
