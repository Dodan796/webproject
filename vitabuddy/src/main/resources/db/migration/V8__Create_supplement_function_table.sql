CREATE TABLE supplement_function (
    SupID INTEGER NOT NULL PRIMARY KEY,
    FunctionID VARCHAR2(20) NOT NULL,
    CONSTRAINT FunctionID_FK FOREIGN KEY(FunctionID) REFERENCES function(FunctionID),
    CONSTRAINT SupID_FK FOREIGN KEY(SupID) REFERENCES supplement(SupID)
); 