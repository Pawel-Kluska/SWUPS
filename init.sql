CREATE TABLE PlansOfStudies
(
    ID               SERIAL NOT NULL,
    ProfileID        integer   NOT NULL,
    FormOfStudiesID  integer   NOT NULL,
    PlanStatusID     integer   NOT NULL,
    EducationLevelID integer   NOT NULL,
    DegreeID         integer   NOT NULL,
    FieldOfStudyID   integer   NOT NULL,
    Identifier       varchar(255),
    DateOfCreation   timestamp NOT NULL,
    Specialization   varchar(255),
    LanguageOfStudy  varchar(255) NOT NULL,
    EducationCycle   varchar(255),
    PRIMARY KEY (ID)
);
CREATE TABLE Profiles
(
    ID   SERIAL NOT NULL,
    Name varchar(255),
    PRIMARY KEY (ID)
);
CREATE TABLE Semesters
(
    ID                 SERIAL NOT NULL,
    PlanOfStudiesID    integer   NOT NULL,
    Code               varchar(255),
    Number             integer   NOT NULL,
    ECTSPointsDeficite integer   NOT NULL,
    PRIMARY KEY (ID)
);
CREATE TABLE CourseTypes
(
    ID   SERIAL NOT NULL,
    Name varchar(255),
    PRIMARY KEY (ID)
);
CREATE TABLE Courses
(
    ID                           SERIAL       NOT NULL,
    WayOfCreditingID             integer         NOT NULL,
    CourseTypeID                 integer         NOT NULL,
    CourseKindID                 integer         NOT NULL,
    CourseCharacterID            integer         NOT NULL,
    CourseFormID                 integer         NOT NULL,
    CourseID                     integer,
    Code                         varchar(255),
    CourseTypeID                 integer,
    Name                         varchar(255),
    CourseFormID                 integer,
    CourseKindID                 integer,
    CourseCharacterID            integer,
    WayOfCreditingID             integer,
    WeeklySumOfHours             integer         NOT NULL,
    SumOfZZUHours                integer         NOT NULL,
    SumOfCNPSHours               integer         NOT NULL,
    SumOFECTSPoints              integer         NOT NULL,
    SumOFECTSPointsFromBUClasses float4       NOT NULL,
    SumOFECTSPointsFromDNClasses float4       NOT NULL,
    Discriminator                varchar(255) NOT NULL,
    PRIMARY KEY (ID)
);
CREATE TABLE CourseKinds
(
    ID   SERIAL NOT NULL,
    Name varchar(255),
    PRIMARY KEY (ID)
);
CREATE TABLE BlocksOfCourses
(
    ID               SERIAL NOT NULL,
    BlockCharacterID integer   NOT NULL,
    SemesterID       integer   NOT NULL,
    Code             varchar(255),
    Name             varchar(255),
    PRIMARY KEY (ID)
);
CREATE TABLE WaysOfCrediting
(
    ID   SERIAL NOT NULL,
    Name varchar(255),
    PRIMARY KEY (ID)
);
CREATE TABLE Degrees
(
    ID   SERIAL NOT NULL,
    Name varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE "Users"
(
    ID          SERIAL NOT NULL,
    AuthorityID integer   NOT NULL,
    Login       varchar(255),
    Password    varchar(255),
    Name        varchar(255),
    Surname     varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE CourseForms
(
    ID   SERIAL NOT NULL,
    Name varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE BlockCharacters
(
    ID   SERIAL NOT NULL,
    Name varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE EducationLevels
(
    ID   SERIAL NOT NULL,
    Name varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE FormsOfStudies
(
    ID   SERIAL NOT NULL,
    Name varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE CourseCharacters
(
    ID   SERIAL NOT NULL,
    Name varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE Opinions
(
    ID                 SERIAL NOT NULL,
    PlanOfStudiesID    integer   NOT NULL,
    UserID             integer   NOT NULL,
    IsPositive         bool   NOT NULL,
    DateOfOpinion      timestamp NOT NULL,
    Content            varchar(255) NOT NULL,
    DateOfModification timestamp NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE Faculties
(
    ID        SERIAL NOT NULL,
    Name      varchar(255),
    ShortName varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE FielsdOfStudy
(
    ID           SERIAL NOT NULL,
    DisciplineID integer   NOT NULL,
    FacultyID    integer   NOT NULL,
    Name         varchar(255),
    ShortName    varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE Disciplines
(
    ID   SERIAL NOT NULL,
    Name varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE Authorities
(
    ID             SERIAL NOT NULL,
    DisciplineID   integer   NOT NULL,
    FacultyID      integer   NOT NULL,
    FieldOfStudyID integer   NOT NULL,
    Name           varchar(255),
    Code           varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE PlanStatuses
(
    ID   SERIAL NOT NULL,
    Name varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE BlockOfCourses_Courses
(
    BlockOfCoursesID integer NOT NULL,
    CourseID         integer NOT NULL,
    PRIMARY KEY (BlockOfCoursesID, CourseID)
);

ALTER TABLE BlockOfCourses_Courses
    ADD CONSTRAINT FOREIGN KEY (BlockOfCoursesID) REFERENCES BlocksOfCourses (ID);
ALTER TABLE BlockOfCourses_Courses
    ADD CONSTRAINT FOREIGN KEY (CourseID) REFERENCES Courses (ID);
ALTER TABLE Opinions
    ADD CONSTRAINT FOREIGN KEY (UserID) REFERENCES "Users" (ID);
ALTER TABLE BlocksOfCourses
    ADD CONSTRAINT FOREIGN KEY (SemesterID) REFERENCES Semesters (ID);
ALTER TABLE Opinions
    ADD CONSTRAINT FOREIGN KEY (PlanOfStudiesID) REFERENCES PlansOfStudies (ID);
ALTER TABLE Semesters
    ADD CONSTRAINT FOREIGN KEY (PlanOfStudiesID) REFERENCES PlansOfStudies (ID);
ALTER TABLE Courses
    ADD CONSTRAINT FOREIGN KEY (CourseID) REFERENCES Courses (ID);
ALTER TABLE "Users"
    ADD CONSTRAINT FOREIGN KEY (AuthorityID) REFERENCES Authorities (ID);
ALTER TABLE FieldsOfStudy
    ADD CONSTRAINT FOREIGN KEY (FacultyID) REFERENCES Faculties (ID);
ALTER TABLE Authorities
    ADD CONSTRAINT FOREIGN KEY (FieldOfStudyID) REFERENCES FieldsOfStudy (ID);
ALTER TABLE Authorities
    ADD CONSTRAINT FOREIGN KEY (FacultyID) REFERENCES Faculties (ID);
ALTER TABLE Authorities
    ADD CONSTRAINT FOREIGN KEY (DisciplineID) REFERENCES Disciplines (ID);
ALTER TABLE PlansOfStudies
    ADD CONSTRAINT FOREIGN KEY (FieldOfStudyID) REFERENCES FieldsOfStudy (ID);
ALTER TABLE FieldOfStudy
    ADD CONSTRAINT FOREIGN KEY (DisciplineID) REFERENCES Disciplines (ID);
ALTER TABLE PlansOfStudies
    ADD CONSTRAINT FOREIGN KEY (DegreeID) REFERENCES Degrees (ID);
ALTER TABLE PlansOfStudies
    ADD CONSTRAINT FOREIGN KEY (EducationLevelID) REFERENCES EducationLevels (ID);
ALTER TABLE PlansOfStudies
    ADD CONSTRAINT FOREIGN KEY (PlanStatusID) REFERENCES PlanStatuses (ID);
ALTER TABLE PlansOfStudies
    ADD CONSTRAINT FOREIGN KEY (FormOfStudiesID) REFERENCES FormsOfStudies (ID);
ALTER TABLE PlansOfStudies
    ADD CONSTRAINT FOREIGN KEY (ProfileID) REFERENCES Profiles (ID);
ALTER TABLE Courses
    ADD CONSTRAINT FOREIGN KEY (CourseFormID) REFERENCES CourseForms (ID);
ALTER TABLE Courses
    ADD CONSTRAINT FOREIGN KEY (CourseCharacterID) REFERENCES CourseCharacters (ID);
ALTER TABLE Courses
    ADD CONSTRAINT FOREIGN KEY (CourseKindID) REFERENCES CourseKinds (ID);
ALTER TABLE Courses
    ADD CONSTRAINT FOREIGN KEY (CourseTypeID) REFERENCES CourseTypes (ID);
ALTER TABLE Courses
    ADD CONSTRAINT FOREIGN KEY (WayOfCreditingID) REFERENCES WaysOfCrediting (ID);
ALTER TABLE BlocksOfCourses
    ADD CONSTRAINT FOREIGN KEY (BlockCharacterID) REFERENCES BlockCharacters (ID);