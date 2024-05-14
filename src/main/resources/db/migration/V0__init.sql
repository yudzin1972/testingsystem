CREATE TABLE account
(
    id        BIGINT AUTO_INCREMENT       NOT NULL,
    created   datetime                    NULL,
    status    ENUM ('DONE', 'DEL', 'NEW') NULL,
    updated   datetime                    NULL,
    email     VARCHAR(128)                NOT NULL,
    password  VARCHAR(64)                 NULL,
    user_name VARCHAR(128)                NULL,
    CONSTRAINT PK_ACCOUNT PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE account_roles
(
    account_id BIGINT NOT NULL,
    role_id    BIGINT NOT NULL
);

CREATE TABLE options
(
    id          BIGINT AUTO_INCREMENT       NOT NULL,
    created     datetime                    NULL,
    status      ENUM ('DONE', 'DEL', 'NEW') NULL,
    updated     datetime                    NULL,
    question_id BIGINT                      NULL,
    text_opt    VARCHAR(255)                NOT NULL,
    correct     BIT(1)                      NOT NULL,
    CONSTRAINT PK_OPTIONS PRIMARY KEY (id)
);

CREATE TABLE questions
(
    id               BIGINT AUTO_INCREMENT       NOT NULL,
    created          datetime                    NULL,
    status           ENUM ('DONE', 'DEL', 'NEW') NULL,
    updated          datetime                    NULL,
    subject_id       BIGINT                      NULL,
    text_question    VARCHAR(255)                NOT NULL,
    content_question MEDIUMTEXT                  NULL,
    CONSTRAINT PK_QUESTIONS PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id        BIGINT AUTO_INCREMENT       NOT NULL,
    created   datetime                    NULL,
    status    ENUM ('DONE', 'DEL', 'NEW') NULL,
    updated   datetime                    NULL,
    name_role VARCHAR(128)                NOT NULL,
    CONSTRAINT PK_ROLES PRIMARY KEY (id)
);

CREATE TABLE subjects
(
    id             BIGINT AUTO_INCREMENT       NOT NULL,
    created        datetime                    NULL,
    status         ENUM ('DONE', 'DEL', 'NEW') NULL,
    updated        datetime                    NULL,
    count_question INT                         NULL,
    name_subject   VARCHAR(128)                NOT NULL,
    timer_minutes  INT                         NULL,
    use_timer      BIT(1)                      NOT NULL,
    CONSTRAINT PK_SUBJECTS PRIMARY KEY (id)
);

CREATE TABLE tests
(
    id             BIGINT AUTO_INCREMENT       NOT NULL,
    created        datetime                    NULL,
    status         ENUM ('DONE', 'DEL', 'NEW') NULL,
    updated        datetime                    NULL,
    count_answer   INT                         NULL,
    count_question INT                         NULL,
    itog           DOUBLE                      NULL,
    name_test      VARCHAR(255)                NOT NULL,
    time_finish    datetime                    NULL,
    time_start     datetime                    NULL,
    timer_minutes  INT                         NULL,
    use_timer      BIT(1)                      NOT NULL,
    account_id     BIGINT                      NOT NULL,
    subject_id     BIGINT                      NOT NULL,
    user_id        BIGINT                      NOT NULL,
    CONSTRAINT PK_TESTS PRIMARY KEY (id)
);

CREATE TABLE tests_history_questions
(
    id             BIGINT AUTO_INCREMENT       NOT NULL,
    created        datetime                    NULL,
    status         ENUM ('DONE', 'DEL', 'NEW') NULL,
    updated        datetime                    NULL,
    correct_answer BIT(1)                      NULL,
    question_id    BIGINT                      NOT NULL,
    test_id        BIGINT                      NULL,
    CONSTRAINT PK_TESTS_HISTORY_QUESTIONS PRIMARY KEY (id)
);

CREATE TABLE tests_user_options
(
    id        BIGINT AUTO_INCREMENT       NOT NULL,
    created   datetime                    NULL,
    status    ENUM ('DONE', 'DEL', 'NEW') NULL,
    updated   datetime                    NULL,
    correct   BIT(1)                      NOT NULL,
    id_option BIGINT                      NOT NULL,
    q_user_id BIGINT                      NOT NULL,
    CONSTRAINT PK_TESTS_USER_OPTIONS PRIMARY KEY (id)
);

CREATE TABLE user_id_seq
(
    next_val BIGINT NULL
);

CREATE TABLE users
(
    id       BIGINT                                           NOT NULL,
    fio      VARCHAR(255)                                     NULL,
    password VARCHAR(255)                                     NOT NULL,
    `role`   ENUM ('ROLE_USER', 'ROLE_TEACHER', 'ROLE_ADMIN') NOT NULL,
    status   ENUM ('DONE', 'DEL', 'NEW')                      NULL,
    username VARCHAR(255)                                     NOT NULL,
    CONSTRAINT PK_USERS PRIMARY KEY (id),
    UNIQUE (username)
);

CREATE INDEX FK5bmv46so2y5igt9o9n9w4fh6y ON options (question_id);

CREATE INDEX FK6r8nxkn3hctohyllteivfr5hy ON account_roles (role_id);

CREATE INDEX FK81af7vtp0dawu5yytdkaqkymy ON tests_history_questions (question_id);

CREATE INDEX FK9goq2vr8ctovulo8j3eokk6so ON tests_history_questions (test_id);

CREATE INDEX FKj48xn6umwt6ldy4v97iv2t1k3 ON tests (user_id);

CREATE INDEX FKmf4m9xyitnw6v5a76pq3qhtpq ON tests (subject_id);

CREATE INDEX FKo0h0rn8bxifrxmq1d8ipiyqv5 ON questions (subject_id);

CREATE INDEX FKpf2e4imtc8nvuaefpya5pas4k ON tests_user_options (q_user_id);

CREATE INDEX FKt8fnaeqdqmdlcsnt6wtrj3o8q ON tests (account_id);

CREATE INDEX FKtp61eta5i06bug3w1qr6286uf ON account_roles (account_id);

ALTER TABLE options
    ADD CONSTRAINT FK5bmv46so2y5igt9o9n9w4fh6y FOREIGN KEY (question_id) REFERENCES questions (id) ON UPDATE RESTRICT ON DELETE CASCADE;

ALTER TABLE account_roles
    ADD CONSTRAINT FK6r8nxkn3hctohyllteivfr5hy FOREIGN KEY (role_id) REFERENCES roles (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE tests_history_questions
    ADD CONSTRAINT FK81af7vtp0dawu5yytdkaqkymy FOREIGN KEY (question_id) REFERENCES questions (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE tests_history_questions
    ADD CONSTRAINT FK9goq2vr8ctovulo8j3eokk6so FOREIGN KEY (test_id) REFERENCES tests (id) ON UPDATE RESTRICT ON DELETE CASCADE;

ALTER TABLE tests
    ADD CONSTRAINT FKj48xn6umwt6ldy4v97iv2t1k3 FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE tests
    ADD CONSTRAINT FKmf4m9xyitnw6v5a76pq3qhtpq FOREIGN KEY (subject_id) REFERENCES subjects (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE questions
    ADD CONSTRAINT FKo0h0rn8bxifrxmq1d8ipiyqv5 FOREIGN KEY (subject_id) REFERENCES subjects (id) ON UPDATE RESTRICT ON DELETE CASCADE;

ALTER TABLE tests_user_options
    ADD CONSTRAINT FKpf2e4imtc8nvuaefpya5pas4k FOREIGN KEY (q_user_id) REFERENCES tests_history_questions (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE tests
    ADD CONSTRAINT FKt8fnaeqdqmdlcsnt6wtrj3o8q FOREIGN KEY (account_id) REFERENCES account (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE account_roles
    ADD CONSTRAINT FKtp61eta5i06bug3w1qr6286uf FOREIGN KEY (account_id) REFERENCES account (id) ON UPDATE RESTRICT ON DELETE CASCADE;

CREATE VIEW next_question AS
select `tests`.`id`                   AS `id_test`,
       `tests_history_questions`.`id` AS `id_historyquestion`,
       `questions`.`id`               AS `id_question`,
       `questions`.`text_question`    AS `text_question`,
       `questions`.`content_question` AS `content_question`
from ((`tests` join `tests_history_questions` on ((`tests`.`id` =
                                                                                   `tests_history_questions`.`test_id`))) join `questions`
      on ((`tests_history_questions`.`question_id` = `questions`.`id`)))
where ((`tests`.`status` <> 'DEL') and (`tests_history_questions`.`status` = 'NEW') and
       (`questions`.`status` <> 'DEL'));