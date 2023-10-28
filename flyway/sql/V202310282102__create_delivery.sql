CREATE TABLE DELIVERY_INFO (
    DELIVERY_ID NUMBER(5, 0),
    RESTAURANT_ID NUMBER(5, 0) NOT NULL,
    ORDER_ID NUMBER(5, 0) NOT NULL,
    CORRELATION_ID VARCHAR2(50) NOT NULL,
    SUBMITTED_AT DATE,
    ADDRESS VARCHAR2(50) NOT NULL,
    DELIVERY_TIME DATE,
    DELIVERED_AT DATE,
    COST NUMBER(7, 2) NOT NULL,
    DELIVERY_STATUS VARCHAR2(30) NOT NULL,
    COURIER_ID NUMBER(5, 0),
    CONSTRAINT DELIVERY_PK PRIMARY KEY (DELIVERY_ID),
    CONSTRAINT DELIVERY_FK FOREIGN KEY (COURIER_ID) REFERENCES COURIERS (COURIER_ID)
);