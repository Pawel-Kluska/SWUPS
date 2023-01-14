CREATE TABLE PlansOfStudies
(
    ID               SERIAL       NOT NULL,
    ProfileID        integer      NOT NULL,
    FormOfStudiesID  integer      NOT NULL,
    PlanStatusID     integer      NOT NULL,
    EducationLevelID integer      NOT NULL,
    DegreeID         integer      NOT NULL,
    FieldOfStudyID   integer      NOT NULL,
    Identifier       varchar(255),
    DateOfCreation   timestamp    NOT NULL,
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
    ID                 SERIAL  NOT NULL,
    PlanOfStudiesID    integer NOT NULL,
    Code               varchar(255),
    Number             integer NOT NULL,
    ECTSPointsDeficite integer NOT NULL,
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
    WayOfCreditingID             integer      NOT NULL,
    CourseTypeID                 integer      NOT NULL,
    CourseKindID                 integer      NOT NULL,
    CourseCharacterID            integer      NOT NULL,
    CourseFormID                 integer      NOT NULL,
    Code                         varchar(255),
    Name                         varchar(255),
    WeeklySumOfHours             integer      NOT NULL,
    SumOfZZUHours                integer      NOT NULL,
    SumOfCNPSHours               integer      NOT NULL,
    SumOFECTSPoints              integer      NOT NULL,
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
    ID               SERIAL  NOT NULL,
    BlockCharacterID integer NOT NULL,
    SemesterID       integer NOT NULL,
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

CREATE TABLE AppUsers
(
    ID          SERIAL  NOT NULL,
    AuthorityID integer NOT NULL,
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
    ID                 SERIAL       NOT NULL,
    PlanOfStudiesID    integer      NOT NULL,
    AppUserID             integer      NOT NULL,
    IsPositive         bool         NOT NULL,
    DateOfOpinion      timestamp    NOT NULL,
    Content            varchar(255) NOT NULL,
    DateOfModification timestamp    NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE Faculties
(
    ID        SERIAL NOT NULL,
    Name      varchar(255),
    ShortName varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE FieldsOfStudy
(
    ID           SERIAL  NOT NULL,
    DisciplineID integer NOT NULL,
    FacultyID    integer NOT NULL,
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
    ID             SERIAL  NOT NULL,
    DisciplineID   integer NOT NULL,
    FacultyID      integer NOT NULL,
    FieldOfStudyID integer NOT NULL,
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
    ADD FOREIGN KEY (BlockOfCoursesID) REFERENCES BlocksOfCourses (ID);
ALTER TABLE BlockOfCourses_Courses
    ADD FOREIGN KEY (CourseID) REFERENCES Courses (ID);
ALTER TABLE Opinions
    ADD FOREIGN KEY (AppUserID) REFERENCES AppUsers (ID);
ALTER TABLE BlocksOfCourses
    ADD FOREIGN KEY (SemesterID) REFERENCES Semesters (ID);
ALTER TABLE Opinions
    ADD FOREIGN KEY (PlanOfStudiesID) REFERENCES PlansOfStudies (ID);
ALTER TABLE Semesters
    ADD FOREIGN KEY (PlanOfStudiesID) REFERENCES PlansOfStudies (ID);
ALTER TABLE AppUsers
    ADD FOREIGN KEY (AuthorityID) REFERENCES Authorities (ID);
ALTER TABLE FieldsOfStudy
    ADD FOREIGN KEY (FacultyID) REFERENCES Faculties (ID);
ALTER TABLE Authorities
    ADD FOREIGN KEY (FieldOfStudyID) REFERENCES FieldsOfStudy (ID);
ALTER TABLE Authorities
    ADD FOREIGN KEY (FacultyID) REFERENCES Faculties (ID);
ALTER TABLE Authorities
    ADD FOREIGN KEY (DisciplineID) REFERENCES Disciplines (ID);
ALTER TABLE PlansOfStudies
    ADD FOREIGN KEY (FieldOfStudyID) REFERENCES FieldsOfStudy (ID);
ALTER TABLE FieldsOfStudy
    ADD FOREIGN KEY (DisciplineID) REFERENCES Disciplines (ID);
ALTER TABLE PlansOfStudies
    ADD FOREIGN KEY (DegreeID) REFERENCES Degrees (ID);
ALTER TABLE PlansOfStudies
    ADD FOREIGN KEY (EducationLevelID) REFERENCES EducationLevels (ID);
ALTER TABLE PlansOfStudies
    ADD FOREIGN KEY (PlanStatusID) REFERENCES PlanStatuses (ID);
ALTER TABLE PlansOfStudies
    ADD FOREIGN KEY (FormOfStudiesID) REFERENCES FormsOfStudies (ID);
ALTER TABLE PlansOfStudies
    ADD FOREIGN KEY (ProfileID) REFERENCES Profiles (ID);
ALTER TABLE Courses
    ADD FOREIGN KEY (CourseFormID) REFERENCES CourseForms (ID);
ALTER TABLE Courses
    ADD FOREIGN KEY (CourseCharacterID) REFERENCES CourseCharacters (ID);
ALTER TABLE Courses
    ADD FOREIGN KEY (CourseKindID) REFERENCES CourseKinds (ID);
ALTER TABLE Courses
    ADD FOREIGN KEY (CourseTypeID) REFERENCES CourseTypes (ID);
ALTER TABLE Courses
    ADD FOREIGN KEY (WayOfCreditingID) REFERENCES WaysOfCrediting (ID);
ALTER TABLE BlocksOfCourses
    ADD FOREIGN KEY (BlockCharacterID) REFERENCES BlockCharacters (ID);


-- Sample data

BEGIN;
INSERT INTO public.blockcharacters (id, name) VALUES (1, 'obowiazkowy');
INSERT INTO public.blockcharacters (id, name) VALUES (2, 'wybieralny');
INSERT INTO public.coursecharacters (id, name) VALUES (1, 'Wyklad');
INSERT INTO public.coursecharacters (id, name) VALUES (2, 'cwiczenia');
INSERT INTO public.coursecharacters (id, name) VALUES (3, 'laboratorium');
INSERT INTO public.coursekinds (id, name) VALUES (1, 'podstawowy');
INSERT INTO public.coursekinds (id, name) VALUES (2, 'ogolnouczelniany');
INSERT INTO public.coursekinds (id, name) VALUES (3, 'ksztalcenia ogolnego');
INSERT INTO public.coursetypes (id, name) VALUES (1, 'Obowiazkowy');
INSERT INTO public.coursetypes (id, name) VALUES (2, 'Wybieralny');
INSERT INTO public.degrees (id, name) VALUES (1, 'Inzynier');
INSERT INTO public.degrees (id, name) VALUES (2, 'Inzynier Architekt');
INSERT INTO public.disciplines (id, name) VALUES (1, 'Informatyka techniczna i telekomunikacja');
INSERT INTO public.disciplines (id, name) VALUES (2, 'Architektura i urbanistyka');
INSERT INTO public.educationlevels (id, name) VALUES (1, 'Studia pierwszego stopnia inzynierskie');
INSERT INTO public.educationlevels (id, name) VALUES (2, 'Studia pierwszego stopnia licencjackie');
INSERT INTO public.educationlevels (id, name) VALUES (3, 'Studia drugiego stopnia');
INSERT INTO public.educationlevels (id, name) VALUES (4, 'Studia jednolite magisterskie');
INSERT INTO public.faculties (id, name, shortname) VALUES (1, 'Wydzial Architektury', 'W1');
INSERT INTO public.faculties (id, name, shortname) VALUES (2, 'Wydzial Informatyki i Telekomunikacji', 'W4n');
INSERT INTO public.faculties (id, name, shortname) VALUES (3, 'Wydzial Zarzadzania', 'W8n');
INSERT INTO public.fieldsofstudy (id, disciplineid, facultyid, name, shortname) VALUES (1, 1, 2, 'Informatyka stosowana', 'Ist');
INSERT INTO public.fieldsofstudy (id, disciplineid, facultyid, name, shortname) VALUES (2, 2, 1, 'Architektura', 'Arch');
INSERT INTO public.formsofstudies (id, name) VALUES (1, 'Stacjonarna');
INSERT INTO public.formsofstudies (id, name) VALUES (2, 'niestacjonarna');
INSERT INTO public.planstatuses (id, name) VALUES (1, 'Nierozpatrzony');
INSERT INTO public.planstatuses (id, name) VALUES (2, 'niezatwierdzony');
INSERT INTO public.planstatuses (id, name) VALUES (3, 'Zatwierdzony');
INSERT INTO public.profiles (id, name) VALUES (1, 'ogólnoakademicki');
INSERT INTO public.profiles (id, name) VALUES (2, 'Praktyczny');
INSERT INTO public.waysofcrediting (id, name) VALUES (1, 'Egzamin');
INSERT INTO public.waysofcrediting (id, name) VALUES (2, 'zaliczenie');
INSERT INTO public.plansofstudies (id, profileid, formofstudiesid, planstatusid, educationlevelid, degreeid, fieldofstudyid, identifier, dateofcreation, specialization, languageofstudy, educationcycle) VALUES (1, 1, 1, 1, 1, 1, 1, 'IST2022', '2023-01-14 18:04:16.000000', 'Brak', 'Polski', '2022/2023');
INSERT INTO public.plansofstudies (id, profileid, formofstudiesid, planstatusid, educationlevelid, degreeid, fieldofstudyid, identifier, dateofcreation, specialization, languageofstudy, educationcycle) VALUES (2, 1, 1, 2, 1, 2, 2, 'ARCH2022', '2023-01-14 18:04:56.000000', 'Wnetrza', 'Polski', '2022/2023');
INSERT INTO public.semesters (id, planofstudiesid, code, number, ectspointsdeficite) VALUES (1, 1, 'IST_2023_Zima', 3, 5);
INSERT INTO public.semesters (id, planofstudiesid, code, number, ectspointsdeficite) VALUES (2, 2, 'ARCH_2022_Zima', 2, 2);
INSERT INTO public.blocksofcourses (id, blockcharacterid, semesterid, code, name) VALUES (1, 1, 1, 'OB_IST_3', 'Paradygmaty Programowania');
INSERT INTO public.blocksofcourses (id, blockcharacterid, semesterid, code, name) VALUES (2, 2, 2, 'WYB_ARCH_1', 'Belki');
INSERT INTO public.authorities (id, disciplineid, facultyid, fieldofstudyid, name, code) VALUES (1, 1, 2, 1, 'Rada informatyki', 'IST1');
INSERT INTO public.authorities (id, disciplineid, facultyid, fieldofstudyid, name, code) VALUES (2, 2, 1, 2, 'Rada Architektury', 'ARCH1');
INSERT INTO public.appusers (id, authorityid, login, password, name, surname) VALUES (1, 2, 'TBroma', 'TBroma', 'Tomasz', 'Broma');
INSERT INTO public.appusers (id, authorityid, login, password, name, surname) VALUES (2, 1, 'BHnatkowska', 'BHnatkowska', 'Bogumila', 'Hnatkowska');
INSERT INTO public.courseforms (id, name) VALUES (1, 'tradycyjna');
INSERT INTO public.courseforms (id, name) VALUES (2, 'zdalna');
INSERT INTO public.courses (id, wayofcreditingid, coursetypeid, coursekindid, coursecharacterid, courseformid, code, name, weeklysumofhours, sumofzzuhours, sumofcnpshours, sumofectspoints, sumofectspointsfrombuclasses, sumofectspointsfromdnclasses, discriminator) VALUES (1, 1, 1, 1, 1, 1, 'INZ004360W', 'Projektowanie oprogramowania', 2, 30, 90, 4, 1.8, 3, 'Course');
INSERT INTO public.courses (id, wayofcreditingid, coursetypeid, coursekindid, coursecharacterid, courseformid, code, name, weeklysumofhours, sumofzzuhours, sumofcnpshours, sumofectspoints, sumofectspointsfrombuclasses, sumofectspointsfromdnclasses, discriminator) VALUES (2, 2, 2, 2, 2, 2, 'INZ002012L', 'Programowanie systemow webowych', 4, 30, 90, 5, 2.4, 4, 'Group of courses');
INSERT INTO public.blockofcourses_courses (blockofcoursesid, courseid) VALUES (1, 2);
INSERT INTO public.blockofcourses_courses (blockofcoursesid, courseid) VALUES (1, 1);
INSERT INTO public.blockofcourses_courses (blockofcoursesid, courseid) VALUES (2, 1);
INSERT INTO public.opinions (id, planofstudiesid, appuserid, ispositive, dateofopinion, content, dateofmodification) VALUES (1, 1, 1, true, '2023-01-14 19:16:34.000000', 'Swietny plan', '2023-01-14 19:18:03.000000');
INSERT INTO public.opinions (id, planofstudiesid, appuserid, ispositive, dateofopinion, content, dateofmodification) VALUES (2, 2, 2, false, '2023-01-02 19:18:35.000000', 'Zly plan', '2023-01-18 19:18:52.000000');


COMMIT ;