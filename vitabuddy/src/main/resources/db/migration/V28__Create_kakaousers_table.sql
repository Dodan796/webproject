CREATE TABLE kakaousers (
    UserID VARCHAR2(50) NOT NULL,          
    UserName VARCHAR2(20),
    UserEmail VARCHAR2(80) UNIQUE,
    PRIMARY KEY (UserID)                   
);

