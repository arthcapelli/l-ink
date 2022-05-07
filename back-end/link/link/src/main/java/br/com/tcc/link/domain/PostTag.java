package br.com.tcc.link.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "db_post_tag")
@SequenceGenerator(name = "seq_db_post_tag", sequenceName = "seq_db_post_tag", allocationSize = 1)
public class PostTag {

    @Id
    @GeneratedValue(generator = "seq_db_post_tag")
    @Column(name = "id", nullable = false)
    private Integer id;

    private String tagName;

    private Integer postId;

    public PostTag(String tagName, Integer postId) {
        this.tagName = tagName;
        this.postId = postId;
    }
}
