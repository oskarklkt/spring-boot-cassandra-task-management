# Task Management with Spring Boot and Cassandra
Simple project to learn how to use Cassandra Database with Spring Boot.

## Tables:

### user_by_id
- user_id UUID PK
- username TEXT
- email TEXT

### task_by_id
- task_id UUID PK
- user_id UUID
- title TEXTgit fetch
- description TEXT
- status TEXT
- created_at TIMESTAMP

### task_by_user_id
- user_id UUID PK
- task_id UUID
- title TEXT
- description TEXT
- status TEXT
- created_at TIMESTAMP

## Why do I create two nearly the same tables? 
In NoSQL cassandra database queries are kings, and it is better to optimise tables for queries, not for data, since
one of the reasons for using NoSQL databases is that we have big loads of data that we want to read fast.
That's why I prefer to duplicate data and risk consistency for getting higher availability.
In the end it all depends on what we want to achieve, If we preferred to make it more consistent I could either
use only two tables or use batch loads, but both of those solutions would slow down our queries.

## CQL commands to create tables

```sql
CREATE TABLE main.task_by_id (
task_id uuid PRIMARY KEY,
created_at timestamp,
description text,
status text,
title text,
user_id uuid
);

CREATE TABLE main.tasks_by_user (
user_id uuid,
task_id uuid,
created_at timestamp,
description text,
status text,
title text,
PRIMARY KEY (user_id, task_id)
) WITH CLUSTERING ORDER BY (task_id ASC);

CREATE TABLE main.user_by_id (
user_id uuid PRIMARY KEY,
email text,
username text
);
```


## Methods:

All of the methods are declared in TaskManagerController class.


