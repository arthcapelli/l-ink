package br.com.tcc.link.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "db_comment")
@SequenceGenerator(name = "seq_db_comment", sequenceName = "seq_db_comment", allocationSize = 1)
public class Comment {

    @Id
    @GeneratedValue(generator = "seq_db_comment")
    @Column(name = "id", nullable = false)
    private Integer id;

    private String commentText;

    private LocalDateTime createdAt = LocalDateTime.now();

    private Integer postId;

    private Integer userId;

}
