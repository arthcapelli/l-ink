package br.com.tcc.link.service.tag;

import br.com.tcc.link.domain.StyleTags;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TagServiceTest {

    private final TagsService service = new TagsService();

    @Test
    public void getAllTagsWithSuccess() {
        List<StyleTags> styleTags = Arrays.stream(StyleTags.values()).collect(Collectors.toList());
        List<String> tags = service.getAllTags();

        for (int i = 0; i < styleTags.size(); i++) {
            assertEquals(styleTags.get(i).getDescription(), tags.get(i));
        }
    }
}
