package br.com.tcc.link.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "db_post")
@SequenceGenerator(name = "seq_db_post", sequenceName = "seq_db_post", allocationSize = 1)
public class Post {

    @Id
    @GeneratedValue(generator = "seq_db_post")
    @Column(name = "id", nullable = false)
    private Integer id;

    private String postImg;

    private String bodyLocal;

    private String measures;

    private LocalDateTime createdAt;

    private Integer userId;

}
