package org.example.taks_management.repository;

import org.example.taks_management.table.TaskByUser;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskByUserRepository extends CassandraRepository<TaskByUser, UUID> {
    List<TaskByUser> findByUserId(UUID userId);
    void deleteByTaskId(UUID taskId);
}
