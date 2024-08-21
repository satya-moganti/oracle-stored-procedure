DROP TABLE TB_BOOK FORCE;
CREATE TABLE TB_BOOK (
BOOKID NUMBER PRIMARY KEY,
TITLE VARCHAR2(100),
AUTHOR VARCHAR2(100),
PUBLISHEDYEAR VARCHAR2(4),
GENRE VARCHAR2(50),
PRICE NUMBER
);

-- Create Book Object Type

DROP TYPE BOOKTYPE FORCE;
CREATE OR REPLACE TYPE BOOKTYPE AS OBJECT (
BOOKID NUMBER,
TITLE VARCHAR2(100),
AUTHOR VARCHAR2(100),
PUBLISHEDYEAR VARCHAR2(4),
GENRE VARCHAR2(50),
PRICE NUMBER
);
/

-- Create Nested Table of BookType

CREATE OR REPLACE TYPE TABBOOKTYPE AS TABLE OF BOOKTYPE;
/

--DROP TYPE VARRAY_BOOK_ID FORCE;
CREATE OR REPLACE TYPE VARRAY_BOOK_IDS AS VARRAY(5000) OF NUMBER;
/
CREATE OR REPLACE TYPE VARRAY_BOOK_TYPE AS VARRAY(5000) OF BOOKTYPE;
/

CREATE OR REPLACE PROCEDURE SP_CREATE_BOOK_BY_INPUT_PARAMS (
in_bookid IN NUMBER,
in_title IN VARCHAR2,
in_author IN VARCHAR2,
in_publishedyear IN VARCHAR2,
in_genre IN VARCHAR2,
in_price IN NUMBER,
out_result OUT NUMBER
) AS
BEGIN
INSERT INTO TB_BOOK (BOOKID, TITLE, AUTHOR, PUBLISHEDYEAR, GENRE, PRICE)
VALUES (in_bookid, in_title, in_author, in_publishedyear, in_genre, in_price);

out_result:=1;
EXCEPTION
WHEN OTHERS THEN
out_result:=-1;
END;
/

CREATE OR REPLACE PROCEDURE SP_CREATE_BOOK_BY_BOOK_TYPE(
in_book IN BOOKTYPE,
out_result OUT NUMBER
) AS

BEGIN
INSERT INTO TB_BOOK(bookid, title, author, publishedyear, genre, price)
VALUES (in_book.bookid, in_book.title, in_book.author, in_book.publishedyear, in_book.genre, in_book.price);
out_result:=1;
EXCEPTION
WHEN OTHERS THEN
out_result:=-1;
END;
/

CREATE OR REPLACE PROCEDURE SP_SAVE_BULK_BOOK_DATA (
in_book_list IN  TABBOOKTYPE,
out_result OUT NUMBER
) AS


BEGIN
FOR i IN 1 .. in_book_list.COUNT LOOP
    INSERT INTO TB_BOOK (bookid, title, author, publishedyear, genre, price)
    VALUES (in_book_list(i).bookid, in_book_list(i).title, in_book_list(i).author, 
	in_book_list(i).publishedyear, in_book_list(i).genre, in_book_list(i).price);
END LOOP;

out_result:=1;
EXCEPTION
WHEN OTHERS THEN
out_result:=-1;
END;
/



CREATE OR REPLACE PROCEDURE SP_GET_BOOK_INFO_BY_ID_TITLE_AUTHOR (
in_bookid IN NUMBER,
in_title IN VARCHAR2,
in_author IN VARCHAR2,
out_book OUT  BOOKTYPE,
out_result OUT NUMBER
) AS

BEGIN
SELECT BOOKTYPE(BOOKID, TITLE, AUTHOR, PUBLISHEDYEAR, GENRE, PRICE) INTO out_book  FROM TB_BOOK 
WHERE BOOKID = in_bookid;
out_result:=1;
EXCEPTION
WHEN OTHERS THEN
out_result:=-1;
END;
/

CREATE OR REPLACE PROCEDURE SP_GET_BOOKS_INFO_BY_ID (
in_bookid IN NUMBER,
out_book OUT  BOOKTYPE,
out_result OUT NUMBER
) AS

BEGIN
SELECT BOOKTYPE(BOOKID, TITLE, AUTHOR, PUBLISHEDYEAR, GENRE, PRICE) INTO out_book  FROM TB_BOOK 
WHERE BOOKID = in_bookid;
out_result:=1;
EXCEPTION
WHEN OTHERS THEN
out_result:=-1;
END;

CREATE OR REPLACE PROCEDURE SP_GET_BOOKS_BULK_DATA_BY_ID_LIST (
    in_bookids   IN  VARRAY_BOOK_IDS,
    out_book_list OUT VARRAY_BOOK_TYPE,
    out_result    OUT NUMBER
) AS
BEGIN
    -- Using COLUMN_VALUE to reference elements within the VARRAY
    SELECT BOOKTYPE(BOOKID, TITLE, AUTHOR, PUBLISHEDYEAR, GENRE, PRICE) 
    BULK COLLECT INTO out_book_list
    FROM TB_BOOK
    WHERE BOOKID
    IN (SELECT COLUMN_VALUE FROM TABLE(in_bookids));
    
    -- Indicating success
    out_result:= 1;
EXCEPTION
    WHEN OTHERS THEN
        -- Indicating failure
        out_result := -1;
END;
/










