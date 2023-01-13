CREATE TABLE PlanOfStudies
(
    ID               SERIAL NOT NULL,
    ProfileID        int4   NOT NULL,
    FormOfStudiesID  int4   NOT NULL,
    PlanStatusID     int4   NOT NULL,
    EducationLevelID int4   NOT NULL,
    DegreeID         int4   NOT NULL,
    FieldOfStudyID   int4   NOT NULL,
    Identifier       varchar(255),
    DateOfCreation   date,
    Specialization   varchar(255),
    LanguageOfStudy  varchar(255),
    EducationCycle   varchar(255),
    PRIMARY KEY (ID)
);
CREATE TABLE Profile
(
    ID   SERIAL NOT NULL,
    Name int4,
    PRIMARY KEY (ID)
);
CREATE TABLE Semester
(
    ID                 SERIAL NOT NULL,
    PlanOfStudiesID    int4   NOT NULL,
    Code               varchar(255),
    Number             int4   NOT NULL,
    ECTSPointsDeficite int4   NOT NULL,
    PRIMARY KEY (ID)
);
CREATE TABLE CourseType
(
    ID   SERIAL NOT NULL,
    Name int4,
    PRIMARY KEY (ID)
);
CREATE TABLE Course
(
    ID                           SERIAL       NOT NULL,
    WayOfCreditingID             int4         NOT NULL,
    CourseTypeID                 int4         NOT NULL,
    CourseKindID                 int4         NOT NULL,
    CourseCharacterID            int4         NOT NULL,
    CourseFormID                 int4         NOT NULL,
    CourseID                     int4,
    Code                         varchar(255),
    Type                         int4,
    Name                         varchar(255),
    Form                         int4,
    Kind                         int4,
    Character                    int4,
    WayOfCrediting               int4,
    WeeklySumOfHours             int4         NOT NULL,
    SumOfZZUHours                int4         NOT NULL,
    SumOfCNPSHours               int4         NOT NULL,
    SumOFECTSPoints              int4         NOT NULL,
    SumOFECTSPointsFromBUClasses float4       NOT NULL,
    SumOFECTSPointsFromDNClasses float4       NOT NULL,
    Discriminator                varchar(255) NOT NULL,
    PRIMARY KEY (ID)
);
CREATE TABLE CourseKind
(
    ID   SERIAL NOT NULL,
    Name int4,
    PRIMARY KEY (ID)
);
CREATE TABLE BlockOfCourses
(
    ID               SERIAL NOT NULL,
    BlockCharacterID int4   NOT NULL,
    SemesterID       int4   NOT NULL,
    Code             varchar(255),
    Name             varchar(255),
    Character        int4,
    PRIMARY KEY (ID)
);
CREATE TABLE WayOfCrediting
(
    ID   SERIAL NOT NULL,
    Name int4,
    PRIMARY KEY (ID)
);
CREATE TABLE Degree
(
    ID   SERIAL NOT NULL,
    Name int4,
    PRIMARY KEY (ID)
);

CREATE TABLE "User"
(
    ID          SERIAL NOT NULL,
    AuthorityID int4   NOT NULL,
    Login       varchar(255),
    Password    varchar(255),
    Name        varchar(255),
    Surname     varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE CourseForm
(
    ID   SERIAL NOT NULL,
    Name int4,
    PRIMARY KEY (ID)
);

CREATE TABLE BlockCharacter
(
    ID   SERIAL NOT NULL,
    Name int4,
    PRIMARY KEY (ID)
);

CREATE TABLE EducationLevel
(
    ID   SERIAL NOT NULL,
    Name int4,
    PRIMARY KEY (ID)
);

CREATE TABLE FormOfStudies
(
    ID   SERIAL NOT NULL,
    Name int4,
    PRIMARY KEY (ID)
);

CREATE TABLE CourseCharacter
(
    ID   SERIAL NOT NULL,
    Name int4,
    PRIMARY KEY (ID)
);

CREATE TABLE Opinion
(
    ID                 SERIAL NOT NULL,
    PlanOfStudiesID    int4   NOT NULL,
    UserID             int4   NOT NULL,
    IsPositive         bool   NOT NULL,
    DateOfOpinion      date,
    Content            varchar(255),
    DateOfModification date,
    PRIMARY KEY (ID)
);

CREATE TABLE Faculty
(
    ID        SERIAL NOT NULL,
    Name      varchar(255),
    ShortName varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE FieldOfStudy
(
    ID           SERIAL NOT NULL,
    DisciplineID int4   NOT NULL,
    FacultyID    int4   NOT NULL,
    Name         varchar(255),
    ShortName    varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE Discipline
(
    ID   SERIAL NOT NULL,
    Name varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE Authority
(
    ID             SERIAL NOT NULL,
    DisciplineID   int4   NOT NULL,
    FacultyID      int4   NOT NULL,
    FieldOfStudyID int4   NOT NULL,
    Name           varchar(255),
    Code           varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE PlanStatus
(
    ID   SERIAL NOT NULL,
    Name varchar(255),
    PRIMARY KEY (ID)
);

CREATE TABLE BlockOfCourses_Course
(
    BlockOfCoursesID int4 NOT NULL,
    CourseID         int4 NOT NULL,
    PRIMARY KEY (BlockOfCoursesID, CourseID)
);

ALTER TABLE BlockOfCourses_Course
    ADD CONSTRAINT zawiera FOREIGN KEY (BlockOfCoursesID) REFERENCES BlockOfCourses (ID);
ALTER TABLE BlockOfCourses_Course
    ADD CONSTRAINT zawiera2 FOREIGN KEY (CourseID) REFERENCES Course (ID);
ALTER TABLE Opinion
    ADD CONSTRAINT wydaje FOREIGN KEY (UserID) REFERENCES "User" (ID);
ALTER TABLE BlockOfCourses
    ADD CONSTRAINT " posiada" FOREIGN KEY (SemesterID) REFERENCES Semester (ID);
ALTER TABLE Opinion
    ADD CONSTRAINT dotyczy FOREIGN KEY (PlanOfStudiesID) REFERENCES PlanOfStudies (ID);
ALTER TABLE Semester
    ADD CONSTRAINT "podzielony na" FOREIGN KEY (PlanOfStudiesID) REFERENCES PlanOfStudies (ID);
ALTER TABLE Course
    ADD CONSTRAINT "składa się z" FOREIGN KEY (CourseID) REFERENCES Course (ID);
ALTER TABLE "User"
    ADD CONSTRAINT reprezentuje FOREIGN KEY (AuthorityID) REFERENCES Authority (ID);
ALTER TABLE FieldOfStudy
    ADD CONSTRAINT posiada FOREIGN KEY (FacultyID) REFERENCES Faculty (ID);
ALTER TABLE Authority
    ADD CONSTRAINT dotyczy2 FOREIGN KEY (FieldOfStudyID) REFERENCES FieldOfStudy (ID);
ALTER TABLE Authority
    ADD CONSTRAINT "działa na" FOREIGN KEY (FacultyID) REFERENCES Faculty (ID);
ALTER TABLE Authority
    ADD CONSTRAINT dotyczy3 FOREIGN KEY (DisciplineID) REFERENCES Discipline (ID);
ALTER TABLE PlanOfStudies
    ADD CONSTRAINT dotyczy4 FOREIGN KEY (FieldOfStudyID) REFERENCES FieldOfStudy (ID);
ALTER TABLE FieldOfStudy
    ADD CONSTRAINT "przypisany do" FOREIGN KEY (DisciplineID) REFERENCES Discipline (ID);
ALTER TABLE PlanOfStudies
    ADD CONSTRAINT uzyskany FOREIGN KEY (DegreeID) REFERENCES Degree (ID);
ALTER TABLE PlanOfStudies
    ADD CONSTRAINT dotyczy5 FOREIGN KEY (EducationLevelID) REFERENCES EducationLevel (ID);
ALTER TABLE PlanOfStudies
    ADD CONSTRAINT posiada2 FOREIGN KEY (PlanStatusID) REFERENCES PlanStatus (ID);
ALTER TABLE PlanOfStudies
    ADD CONSTRAINT ma FOREIGN KEY (FormOfStudiesID) REFERENCES FormOfStudies (ID);
ALTER TABLE PlanOfStudies
    ADD CONSTRAINT ma2 FOREIGN KEY (ProfileID) REFERENCES Profile (ID);
ALTER TABLE Course
    ADD CONSTRAINT jest FOREIGN KEY (CourseFormID) REFERENCES CourseForm (ID);
ALTER TABLE Course
    ADD CONSTRAINT ma3 FOREIGN KEY (CourseCharacterID) REFERENCES CourseCharacter (ID);
ALTER TABLE Course
    ADD CONSTRAINT ma4 FOREIGN KEY (CourseKindID) REFERENCES CourseKind (ID);
ALTER TABLE Course
    ADD CONSTRAINT ma5 FOREIGN KEY (CourseTypeID) REFERENCES CourseType (ID);
ALTER TABLE Course
    ADD CONSTRAINT ma6 FOREIGN KEY (WayOfCreditingID) REFERENCES WayOfCrediting (ID);
ALTER TABLE BlockOfCourses
    ADD CONSTRAINT jest2 FOREIGN KEY (BlockCharacterID) REFERENCES BlockCharacter (ID);