import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class URITest {

    private static final String SCHEME = "scheme://authority/path?query#fragment";
    private static final String HTTPS = "https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top";
    private static final String LDAP = "ldap://[2001:db8::7]/c=GB?objectClass?one";
    private static final String MAILTO = "mailto:John.Doe@example.com";
    private static final String NEWS = "news:comp.infosystems.www.servers.unix";
    private static final String TEL = "tel:+1-816-555-1212";
    private static final String TELNET = "telnet://192.0.2.16:80/";
    private static final String URN = "urn:oasis:names:specification:docbook:dtd:xml:4.1.2";

    @Test
    public void uri_extracts_scheme() {
        Map<String, String> items = new HashMap<>();
        items.put(SCHEME, "scheme");
        items.put(HTTPS, "https");
        items.put(LDAP, "ldap");
        items.put(MAILTO, "mailto");
        items.put(NEWS, "news");
        items.put(TEL, "tel");
        items.put(TELNET, "telnet");
        items.put(URN, "urn");

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