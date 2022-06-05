package br.com.tcc.link.service.tag;

import br.com.tcc.link.domain.PostTag;
import br.com.tcc.link.domain.StyleTags;
import br.com.tcc.link.repository.PostTagRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostTagServiceTest {

    @InjectMocks
    private PostTagService service;

    @Mock
    private PostTagRepository repository;

    @Captor
    private ArgumentCaptor<PostTag> captor;

    Integer idPost;
    String tagName;
    List<PostTag> postTags;

    @Before
    public void setUp() {
        idPost = nextInt();

        StyleTags[] styleTags = StyleTags.values();
        tagName = styleTags[nextInt(0, styleTags.length)].getDescription();

        postTags = Arrays.stream(StyleTags.values())
                .map(tag -> PostTag.builder().tagName(tag.getDescription()).id(nextInt()).postId(nextInt()).build())
                .collect(Collectors.toList());
    }

    @Test
    public void savePostTagWithSuccess() {

        service.save(tagName, idPost);

        verify(repository).save(captor.capture());

        PostTag postTag = captor.getValue();

        assertEquals(idPost, postTag.getPostId());
        assertEquals(tagName, postTag.getTagName());
    }

    @Test
    public void deleteAllTagsByPostIdWithSuccess() {
        when(repository.findAllByPostId(idPost)).thenReturn(postTags);

        service.deleteAllByPostId(idPost);

        verify(repository).findAllByPostId(idPost);
        verify(repository).deleteAll(postTags);
    }

    @Test
    public void findAllByPostIdWithSuccess() {
        when(repository.findAllByPostId(idPost)).thenReturn(postTags);

        List<String> response = service.findAllByPostId(idPost);

        for (int i = 0; i < postTags.size(); i++) {
            assertEquals(postTags.get(i).getTagName(), response.get(i));
        }
    }

    @Test
    public void findAllByTahNameIn() {
        when(repository.findAllByTagNameIn(asList(tagName))).thenReturn(postTags);

        List<PostTag> response = service.findAllByTagNameIn(asList(tagName));

        for (int i = 0; i < postTags.size(); i++) {
            assertEquals(postTags.get(i), response.get(i));
        }
    }
}
