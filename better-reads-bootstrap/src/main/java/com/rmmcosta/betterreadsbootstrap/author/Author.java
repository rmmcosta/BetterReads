package com.rmmcosta.betterreadsbootstrap.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@Table(value = "author_by_id")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @PrimaryKeyColumn(name = "author_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id;
    @Column("author_name")
    @CassandraType(type = Name.TEXT)
    private String name;
    @Column("personal_name")
    @CassandraType(type = Name.TEXT)
    private String personalName;
}
