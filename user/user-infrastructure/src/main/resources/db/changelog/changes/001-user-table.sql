-- liquibase formatted sql


-- changeset przemyslaw.nosek:1700142447000-1
CREATE TABLE app_user
(
    login         VARCHAR(25),
    request_count INTEGER
)