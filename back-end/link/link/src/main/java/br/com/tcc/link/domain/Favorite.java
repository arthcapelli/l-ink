package br.com.tcc.link.domain;


import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "db_favorite")
@SequenceGenerator(name = "seq_db_favorite", sequenceName = "seq_db_favorite", allocationSize = 1)
public class Favorite {

    @Id
    @GeneratedValue(generator = "seq_db_favorite")
    @Column(name = "id", nullable = false)
    private Integer id;

    private Integer postId;

    private Integer userId;


    public Favorite(Integer postId, Integer userId) {
        this.postId = postId;
        this.userId = userId;
    }
}
