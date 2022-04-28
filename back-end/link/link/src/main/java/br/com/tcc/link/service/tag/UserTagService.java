package br.com.tcc.link.service.tag;

import br.com.tcc.link.domain.UserTag;
import br.com.tcc.link.repository.UserTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserTagService {

    @Autowired
    private UserTagRepository repository;

    public void save(final String userTag, final Integer userId) {
        UserTag userTags = new UserTag(userTag, userId);
        repository.save(userTags);
    }

}
