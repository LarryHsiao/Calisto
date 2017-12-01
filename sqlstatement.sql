DROP TABLE IF EXISTS tag;

CREATE TABLE IF NOT EXISTS tag
(
  id        INTEGER PRIMARY KEY AUTOINCREMENT,
  name      TEXT NOT NULL,
  uri_image TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS object (
  id   INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  uri  TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS object_tag (
  id        INTEGER PRIMARY KEY AUTOINCREMENT,
  object_id INTEGER,
  tag_id    INTEGER

);

INSERT INTO tag (name, uri_image) VALUES ('tag1', 'image');
INSERT INTO tag (name, uri_image) VALUES ('tag2', 'image2');
INSERT INTO object (name, uri) VALUES ('object1', 'uri1');
INSERT INTO object (name, uri) VALUES ('object2', 'uri2');
INSERT INTO object_tag (object_id, tag_id) VALUES (1, 2);
INSERT INTO object_tag (object_id, tag_id) VALUES (1, 1);
INSERT INTO object_tag (object_id, tag_id) VALUES (2, 1);

SELECT
  object.name,
  tag.name,
  tag.uri_image
FROM object
  LEFT JOIN tag
  LEFT JOIN object_tag
WHERE object.id = object_tag.object_id AND object_tag.tag_id = tag.id;

-- Use case: find Object`s tags
SELECT tag.*
FROM object_tag
  LEFT JOIN tag
WHERE object_tag.object_id = 1 AND tag.id = object_tag.tag_id;

-- Use case: find Tag`s Objects
SELECT object.*
FROM object
  LEFT JOIN object_tag
WHERE object_tag.tag_id = 1 AND object.id = object_tag.object_id;

-- Use case : all objects
SELECT *
FROM object;

-- Use case : create object
INSERT INTO object (name, uri) VALUES ('inserted object', 'uri of object');

-- Use case : create tag
INSERT INTO tag (name, uri_image) VALUES ('inserted tag', 'uri of image');

-- Use case : create relation of existing Object and Tag
INSERT INTO object_tag (object_id, tag_id) VALUES (1, 1);
INSERT INTO object_tag (object_id, tag_id) VALUES (1, 2);

