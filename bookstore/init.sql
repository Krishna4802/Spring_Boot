CREATE USER bank WITH PASSWORD 'bank@123';
CREATE DATABASE bookstore;
GRANT ALL PRIVILEGES ON DATABASE bookstore TO bank;
