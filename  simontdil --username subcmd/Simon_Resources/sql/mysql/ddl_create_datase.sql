CREATE DATABASE SIMON;

CREATE USER SIMON_USER IDENTIFIED BY 'SIMON_USER';

GRANT ALL ON SIMON.* TO SIMON_USER IDENTIFIED BY 'SIMON_USER';