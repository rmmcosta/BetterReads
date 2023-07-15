package com.rmmcosta.betterreadsbootstrap.work;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("books_by_id")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Work {
    @PrimaryKey
    private String bookKey;
    private String bookTitle;
    private String cover;
    private String authorName;
    private String authorKey;
}
