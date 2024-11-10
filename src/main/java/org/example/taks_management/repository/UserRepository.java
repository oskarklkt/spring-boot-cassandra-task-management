package org.example.taks_management.repository;

import org.example.taks_management.table.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CassandraRepository<User, UUID> {}
