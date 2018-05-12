import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class URITest {

    private static final String SCHEME = "scheme://authority/path?query#fragment";
    private static final String HTTPS = "https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top";

    @Test
    public void uri_extracts_scheme() {
        Map<String, String> items = new HashMap<>();
        items.put(SCHEME, "scheme");
        items.put(HTTPS, "https");

        for(String uri : items.keySet()) {
            String scheme = items.get(uri);
            assertScheme(uri, scheme);
        }
    }


    private void assertScheme(String unprocessedUri, String scheme) {
        URI uri = new URI(unprocessedUri);
        assertThat(uri.getScheme(), is(equalTo(scheme)));
    }
}