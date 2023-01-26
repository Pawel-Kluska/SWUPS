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

CREATE TABLE StudyEffects
(
    ID          SERIAL NOT NULL,
    CourseID    integer,
    code        varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
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
    AppUserID          integer      NOT NULL,
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
ALTER TABLE StudyEffects
    ADD FOREIGN KEY (CourseID) REFERENCES Courses (ID);


-- Sample data

BEGIN;
INSERT INTO public.blockcharacters (name)
VALUES ('obowiazkowy');
INSERT INTO public.blockcharacters (name)
VALUES ('wybieralny');
INSERT INTO public.coursecharacters (name)
VALUES ('Wyklad');
INSERT INTO public.coursecharacters (name)
VALUES ('cwiczenia');
INSERT INTO public.coursecharacters (name)
VALUES ('laboratorium');
INSERT INTO public.coursekinds (name)
VALUES ('podstawowy');
INSERT INTO public.coursekinds (name)
VALUES ('ogolnouczelniany');
INSERT INTO public.coursekinds (name)
VALUES ('ksztalcenia ogolnego');
INSERT INTO public.coursetypes (name)
VALUES ('Obowiazkowy');
INSERT INTO public.coursetypes (name)
VALUES ('Wybieralny');
INSERT INTO public.degrees (name)
VALUES ('Inzynier');
INSERT INTO public.degrees (name)
VALUES ('Inzynier Architekt');
INSERT INTO public.disciplines (name)
VALUES ('Informatyka techniczna i telekomunikacja');
INSERT INTO public.disciplines (name)
VALUES ('Architektura i urbanistyka');
INSERT INTO public.educationlevels (name)
VALUES ('Studia pierwszego stopnia inzynierskie');
INSERT INTO public.educationlevels (name)
VALUES ('Studia pierwszego stopnia licencjackie');
INSERT INTO public.educationlevels (name)
VALUES ('Studia drugiego stopnia');
INSERT INTO public.educationlevels (name)
VALUES ('Studia jednolite magisterskie');
INSERT INTO public.faculties (name, shortname)
VALUES ('Wydzial Architektury', 'W1');
INSERT INTO public.faculties (name, shortname)
VALUES ('Wydzial Informatyki i Telekomunikacji', 'W4n');
INSERT INTO public.faculties (name, shortname)
VALUES ('Wydzial Zarzadzania', 'W8n');
INSERT INTO public.fieldsofstudy (disciplineid, facultyid, name, shortname)
VALUES (1, 2, 'Informatyka stosowana', 'Ist');
INSERT INTO public.fieldsofstudy (disciplineid, facultyid, name, shortname)
VALUES (2, 1, 'Architektura', 'Arch');
INSERT INTO public.formsofstudies (name)
VALUES ('Stacjonarna');
INSERT INTO public.formsofstudies (name)
VALUES ('niestacjonarna');
INSERT INTO public.planstatuses (name)
VALUES ('Nierozpatrzony');
INSERT INTO public.planstatuses (name)
VALUES ('niezatwierdzony');
INSERT INTO public.planstatuses (name)
VALUES ('Zatwierdzony');
INSERT INTO public.profiles (name)
VALUES ('ogólnoakademicki');
INSERT INTO public.profiles (name)
VALUES ('Praktyczny');
INSERT INTO public.waysofcrediting (name)
VALUES ('Egzamin');
INSERT INTO public.waysofcrediting (name)
VALUES ('zaliczenie');
INSERT INTO public.plansofstudies (profileid, formofstudiesid, planstatusid, educationlevelid, degreeid,
                                   fieldofstudyid, identifier, dateofcreation, specialization, languageofstudy,
                                   educationcycle)
VALUES (1, 1, 1, 1, 1, 1, 'IST2022', '2023-01-14 18:04:16.000000', 'Brak', 'Polski', '2022/2023');
INSERT INTO public.plansofstudies (profileid, formofstudiesid, planstatusid, educationlevelid, degreeid,
                                   fieldofstudyid, identifier, dateofcreation, specialization, languageofstudy,
                                   educationcycle)
VALUES (1, 1, 2, 1, 2, 2, 'ARCH2022', '2023-01-14 18:04:56.000000', 'Wnetrza', 'Polski', '2022/2023');
INSERT INTO public.semesters (planofstudiesid, code, number, ectspointsdeficite)
VALUES (1, 'IST_2023_Zima', 3, 5);
INSERT INTO public.semesters (planofstudiesid, code, number, ectspointsdeficite)
VALUES (2, 'ARCH_2022_Zima', 2, 2);
INSERT INTO public.blocksofcourses (blockcharacterid, semesterid, code, name)
VALUES (1, 1, 'OB_IST_3', 'Paradygmaty Programowania');
INSERT INTO public.blocksofcourses (blockcharacterid, semesterid, code, name)
VALUES (2, 2, 'WYB_ARCH_1', 'Belki');
INSERT INTO public.authorities (disciplineid, facultyid, fieldofstudyid, name, code)
VALUES (1, 2, 1, 'Rada informatyki', 'IST1');
INSERT INTO public.authorities (disciplineid, facultyid, fieldofstudyid, name, code)
VALUES (2, 1, 2, 'Rada Architektury', 'ARCH1');
INSERT INTO public.authorities (disciplineid, facultyid, fieldofstudyid, name, code)
VALUES (2, 1, 2, 'SENAT', 'SEN1');
INSERT INTO public.appusers (authorityid, login, password, name, surname)
VALUES (2, 'TBroma', '$2a$10$h3FHbtqm4biDydKAlMXDmOnYx9tWRtGNP7rpNhsaf5pjUi4uvCznS', 'Tomasz', 'Broma');
INSERT INTO public.appusers (authorityid, login, password, name, surname)
VALUES (1, 'BHnatkowska', '$2a$10$h3FHbtqm4biDydKAlMXDmOnYx9tWRtGNP7rpNhsaf5pjUi4uvCznS', 'Bogumila', 'Hnatkowska');
INSERT INTO public.appusers (authorityid, login, password, name, surname)
VALUES (3, 'JKowalski', '$2a$10$h3FHbtqm4biDydKAlMXDmOnYx9tWRtGNP7rpNhsaf5pjUi4uvCznS', 'Jan', 'Kowalski');

INSERT INTO public.courseforms (name)
VALUES ('tradycyjna');
INSERT INTO public.courseforms (name)
VALUES ('zdalna');
INSERT INTO public.courses (wayofcreditingid, coursetypeid, coursekindid, coursecharacterid, courseformid, code,
                            name, weeklysumofhours, sumofzzuhours, sumofcnpshours, sumofectspoints,
                            sumofectspointsfrombuclasses, sumofectspointsfromdnclasses, discriminator)
VALUES (1, 1, 1, 1, 1, 'INZ004360W', 'Projektowanie oprogramowania', 2, 30, 90, 4, 1.8, 3, 'Course');
INSERT INTO public.courses (wayofcreditingid, coursetypeid, coursekindid, coursecharacterid, courseformid, code,
                            name, weeklysumofhours, sumofzzuhours, sumofcnpshours, sumofectspoints,
                            sumofectspointsfrombuclasses, sumofectspointsfromdnclasses, discriminator)
VALUES (2, 2, 2, 2, 2, 'INZ002012L', 'Programowanie systemow webowych', 4, 30, 90, 5, 2.4, 4, 'Group of courses');
INSERT INTO public.blockofcourses_courses (blockofcoursesid, courseid)
VALUES (1, 2);
INSERT INTO public.blockofcourses_courses (blockofcoursesid, courseid)
VALUES (1, 1);
INSERT INTO public.blockofcourses_courses (blockofcoursesid, courseid)
VALUES (2, 1);
INSERT INTO public.opinions (planofstudiesid, appuserid, ispositive, dateofopinion, content, dateofmodification)
VALUES (1, 1, true, '2023-01-14 19:16:34.000000', 'Swietny plan', '2023-01-14 19:18:03.000000');
INSERT INTO public.opinions (planofstudiesid, appuserid, ispositive, dateofopinion, content, dateofmodification)
VALUES (2, 2, false, '2023-01-02 19:18:35.000000', 'Zly plan', '2023-01-18 19:18:52.000000');
INSERT INTO public.studyeffects(code, description)
VALUES ('KINF_W14', 'Posiada szczegółową wiedzę na temat projektowania oprogramowania i projektowania baz danych');
INSERT INTO public.studyeffects(code, description)
VALUES ('KINF_W07', 'Posiada wiedzę na temat programowania aplikacji różnych typów, np. mobilnych, webowych, bazodanowych, rozproszonych');

COMMIT;