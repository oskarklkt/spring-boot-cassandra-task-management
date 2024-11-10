package org.example.taks_management.table;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("tasks_by_user")
@Getter
@Setter
public class TaskByUser {

    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.UUID)
    @Column(value = "user_id")
    private UUID userId;

    @PrimaryKeyColumn(name = "task_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID taskId;

    @CassandraType(type = CassandraType.Name.TEXT)
    private String title;

    @CassandraType(type = CassandraType.Name.TEXT)
    private String description;

    @CassandraType(type = CassandraType.Name.TEXT)
    private String status;

    @Column(value = "created_at")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    private long createdAt;
}
