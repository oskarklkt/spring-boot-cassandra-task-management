package org.example.taks_management.table;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table(value = "user_by_id")
@Getter
@Setter
public class User {

    @CassandraType(type = Name.UUID)
    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID userId;
    @CassandraType(type = Name.TEXT)
    private String username;
    @CassandraType(type = Name.TEXT)
    private String email;
}
