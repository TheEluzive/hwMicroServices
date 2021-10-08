CREATE TABLE users
(
    id       bigint  NOT NULL GENERATED ALWAYS AS IDENTITY,
    username TEXT        NOT NULL UNIQUE,
    PRIMARY KEY (id)
);


CREATE TABLE payments
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    "senderId" bigint,
    amount bigint,
    comment text,
    PRIMARY KEY (id),
    CONSTRAINT fkey_user_id FOREIGN KEY ("senderId")
        REFERENCES users (id)
);
