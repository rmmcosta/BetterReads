package com.rmmcosta.betterreadsbootstrap.work;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;

@Table("books_by_authorID")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkByAuthor {
    @PrimaryKey
    private String authorKey;
    private LocalDate releaseDt;
    private String bookKey;
    private String bookTitle;
    private String bookCover;
}
