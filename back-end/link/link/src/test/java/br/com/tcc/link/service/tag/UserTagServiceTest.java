package br.com.tcc.link.service.tag;

import br.com.tcc.link.domain.StyleTags;
import br.com.tcc.link.domain.UserTag;
import br.com.tcc.link.repository.UserTagRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserTagServiceTest {

    @InjectMocks
    private UserTagService service;

    @Mock
    private UserTagRepository repository;

    @Captor
    private ArgumentCaptor<UserTag> captor;

    Integer idUser;
    String tagName;

    @Before
    public void setUp() {
        idUser = nextInt();

        StyleTags[] styleTags = StyleTags.values();
        tagName = styleTags[nextInt(0, styleTags.length)].getDescription();
    }

    @Test
    public void success() {

        service.save(tagName, idUser);

        verify(repository).save(captor.capture());

        UserTag userTag = captor.getValue();

        assertEquals(idUser, userTag.getUserId());
        assertEquals(tagName, userTag.getTagName());
    }
}