  CREATE TABLE naverusers (
      SocialId VARCHAR2(50) NOT NULL,
      UserName VARCHAR2(20),
      UserEmail VARCHAR2(80) UNIQUE,
      Gender VARCHAR2(10),
      BirthYear VARCHAR2(4),
      PRIMARY KEY (SocialId)
  );
