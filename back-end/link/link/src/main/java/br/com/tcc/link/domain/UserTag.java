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
@Table(name = "db_user_tag")
@SequenceGenerator(name = "seq_db_user_tag", sequenceName = "seq_db_user_tag", allocationSize = 1)
public class UserTag {

    @Id
    @GeneratedValue(generator = "seq_db_user_tag")
    @Column(name = "id", nullable = false)
    private Integer id;

    private String tagName;

    private Integer userId;

    public UserTag(String tagName, Integer userId) {
        this.tagName = tagName;
        this.userId = userId;
    }
}
