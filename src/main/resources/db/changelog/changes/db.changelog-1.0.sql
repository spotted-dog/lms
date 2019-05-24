CREATE TABLE student
(
  id   VARCHAR(20)  NOT NULL,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE course
(
  id          VARCHAR(20)  NOT NULL,
  name        VARCHAR(100) NOT NULL,
  description VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE registration
(
  student_id VARCHAR(20) REFERENCES student(id) ON UPDATE CASCADE ON DELETE CASCADE,
  course_id  VARCHAR(20) REFERENCES course(id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT student_course_pk PRIMARY KEY (student_id, course_id)
);
