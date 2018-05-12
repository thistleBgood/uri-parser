import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class URITest {

    private static final String SCHEME = "scheme://authority/path?query#fragment";
    private static final String HTTPS = "https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top";

    @Test
    public void uri_extracts_scheme_1() {
        String unprocessedUri = SCHEME;
        URI uri = new URI(unprocessedUri);
        assertThat(uri.getScheme(), is(equalTo("scheme")));
    }

    @Test
    public void uri_extracts_scheme_2() {
        String unprocessedUri = HTTPS;
        URI uri = new URI(unprocessedUri);
        assertThat(uri.getScheme(), is(equalTo("https")));
    }
}