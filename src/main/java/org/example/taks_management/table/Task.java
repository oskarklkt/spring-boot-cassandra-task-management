package org.example.taks_management.table;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table(value = "task_by_id")
@Getter
@Setter
public class Task {

    @Id
    @CassandraType(type = Name.UUID)
    @PrimaryKeyColumn(name = "task_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID taskId;

    @CassandraType(type = Name.UUID)
    @Column(value = "user_id")
    private UUID userId;

    @CassandraType(type = Name.TEXT)
    private String title;

    @CassandraType(type = Name.TEXT)
    private String description;

    @CassandraType(type = Name.TEXT)
    private String status;

    @Column(value = "created_at")
    @CassandraType(type = Name.TIMESTAMP)
    private long createdAt;

}
