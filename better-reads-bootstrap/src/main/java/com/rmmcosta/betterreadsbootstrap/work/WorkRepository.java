package com.rmmcosta.betterreadsbootstrap.work;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends CassandraRepository<Work, String> {
}
