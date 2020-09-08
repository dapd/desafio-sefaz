CREATE TABLE TELEFONE (
COD_TELEFONE INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
DDD VARCHAR (3) NOT NULL,
NUMERO VARCHAR (9) NOT NULL,
TIPO VARCHAR (1) NOT NULL,
COD_USUARIO INTEGER NOT NULL,
FOREIGN KEY (COD_USUARIO) REFERENCES USUARIO(COD_USUARIO)
);