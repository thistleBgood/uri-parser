package io.thistlebgood.uri;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static io.thistlebgood.uri.URIConstants.COMPONENT_IS_EMPTY;

public class URITest {
    private static final String SCHEME = "scheme://authority/path?query#fragment";
    private static final String HTTPS = "https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top";
    private static final String LDAP = "ldap://[2001:db8::7]/c=GB?objectClass?one";
    private static final String MAILTO = "mailto:John.Doe@example.com";
    private static final String NEWS = "news:comp.infosystems.www.servers.unix";
    private static final String TEL = "tel:+1-816-555-1212";
    private static final String TELNET = "telnet://192.0.2.16:80/";
    private static final String URN = "urn:oasis:names:specification:docbook:dtd:xml:4.1.2";
    private static final String WIKIPEDIA = "https://en.wikipedia.org/wiki/Uniform_Resource_Identifier#Generic_syntax";

    private Map<String, Object> scenarios;

    @Before
    public void initialise_scenarios_collection() {
        scenarios = new HashMap<>();
    }

    private void add_scenario(String testValue, Object result) {
        scenarios.put(testValue, result);
    }

    @Test
    public void uri_extracts_scheme() {
        add_scenario(SCHEME, "scheme");
        add_scenario(HTTPS, "https");
        add_scenario(LDAP, "ldap");
        add_scenario(MAILTO, "mailto");
        add_scenario(NEWS, "news");
        add_scenario(TEL, "tel");
        add_scenario(TELNET, "telnet");
        add_scenario(URN, "urn");
        add_scenario(WIKIPEDIA, "https");

        scenarios.keySet().forEach(unprocessedUri -> {
            Object expectedScheme = scenarios.get(unprocessedUri);
            URI uri = new URI(unprocessedUri);
            String processedScheme = uri.getScheme();
            assertThat(processedScheme, is(equalTo(expectedScheme)));
        });
    }

    @Test
    public void uri_checks_whether_authority_exists() {
        add_scenario(SCHEME, true);
        add_scenario(HTTPS, true);
        add_scenario(LDAP, true);
        add_scenario(MAILTO, false);
        add_scenario(NEWS, false);
        add_scenario(TEL, false);
        add_scenario(TELNET, true);
        add_scenario(URN, false);
        add_scenario(WIKIPEDIA, true);

        scenarios.keySet().forEach(unprocessedUri -> {
            Object expectedAuthority = scenarios.get(unprocessedUri);
            URI uri = new URI(unprocessedUri);
            boolean parsedAuthority = uri.hasAuthority();
            assertThat(parsedAuthority, is(equalTo(expectedAuthority)));
        });
    }

    @Test
    public void uri_extracts_authority() {
        String NO_AUTHORITY = "";

        add_scenario(SCHEME, "authority");
        add_scenario(HTTPS, "john.doe@www.example.com:123");
        add_scenario(LDAP, "[2001:db8::7]");
        add_scenario(MAILTO, NO_AUTHORITY);
        add_scenario(NEWS, NO_AUTHORITY);
        add_scenario(TEL, NO_AUTHORITY);
        add_scenario(TELNET, "192.0.2.16:80");
        add_scenario(URN, NO_AUTHORITY);
        add_scenario(WIKIPEDIA, "en.wikipedia.org");

        scenarios.keySet().forEach(unprocessedUri -> {
            Object expectedAuthority = scenarios.get(unprocessedUri);
            URI uri = new URI(unprocessedUri);
            String parsedAuthority = uri.getAuthority();
            assertThat(parsedAuthority, is(equalTo(expectedAuthority)));
        });
    }

    @Test
    public void uri_extracts_path() {
        add_scenario(SCHEME, "/path");
        add_scenario(HTTPS, "/forum/questions/");
        add_scenario(LDAP, "/c=GB");
        add_scenario(MAILTO, "John.Doe@example.com");
        add_scenario(NEWS, "comp.infosystems.www.servers.unix");
        add_scenario(TEL, "+1-816-555-1212");
        add_scenario(TELNET, "/");
        add_scenario(URN, "oasis:names:specification:docbook:dtd:xml:4.1.2");
        add_scenario(WIKIPEDIA, "/wiki/Uniform_Resource_Identifier");

        scenarios.keySet().forEach(unprocessedUri -> {
            Object expectedPath = scenarios.get(unprocessedUri);
            URI uri = new URI(unprocessedUri);
            String parsedPath = uri.getPath();
            assertThat(parsedPath, is(equalTo(expectedPath)));
        });
    }

    @Test
    public void uri_checks_whether_query_exists() {
        add_scenario(SCHEME, true);
        add_scenario(HTTPS, true);
        add_scenario(LDAP, true);
        add_scenario(MAILTO, false);
        add_scenario(NEWS, false);
        add_scenario(TEL, false);
        add_scenario(TELNET, false);
        add_scenario(URN, false);
        add_scenario(WIKIPEDIA, false);

        scenarios.keySet().forEach(unprocessedUri -> {
            Object expectedQuery = scenarios.get(unprocessedUri);
            URI uri = new URI(unprocessedUri);
            boolean parsedQuery = uri.hasQuery();
            assertThat(parsedQuery, is(equalTo(expectedQuery)));
        });
    }

    @Test
    public void uri_extracts_query() {
        String NO_QUERY = "";

        add_scenario(SCHEME, "query");
        add_scenario(HTTPS, "tag=networking&order=newest");
        add_scenario(LDAP, "objectClass?one");
        add_scenario(MAILTO, NO_QUERY);
        add_scenario(NEWS, NO_QUERY);
        add_scenario(TEL, NO_QUERY);
        add_scenario(TELNET, NO_QUERY);
        add_scenario(URN, NO_QUERY);
        add_scenario(WIKIPEDIA, NO_QUERY);

        scenarios.keySet().forEach(unprocessedUri -> {
            Object expectedQuery = scenarios.get(unprocessedUri);
            URI uri = new URI(unprocessedUri);
            String parsedQuery = uri.getQuery();
            assertThat(parsedQuery, is(equalTo(expectedQuery)));
        });
    }

    @Test
    public void uri_checks_whether_fragment_exists() {
        add_scenario(SCHEME, true);
        add_scenario(HTTPS, true);
        add_scenario(LDAP, false);
        add_scenario(MAILTO, false);
        add_scenario(NEWS, false);
        add_scenario(TEL, false);
        add_scenario(TELNET, false);
        add_scenario(URN, false);
        add_scenario(WIKIPEDIA, true);

        scenarios.keySet().forEach(unprocessedUri -> {
            Object expectedQuery = scenarios.get(unprocessedUri);
            URI uri = new URI(unprocessedUri);
            boolean parsedQuery = uri.hasFragment();
            assertThat(parsedQuery, is(equalTo(expectedQuery)));
        });
    }

    @Test
    public void uri_extracts_fragment() {
        String NO_FRAGMENT = "";

        add_scenario(SCHEME, "fragment");
        add_scenario(HTTPS, "top");
        add_scenario(LDAP, NO_FRAGMENT);
        add_scenario(MAILTO, NO_FRAGMENT);
        add_scenario(NEWS, NO_FRAGMENT);
        add_scenario(TEL, NO_FRAGMENT);
        add_scenario(TELNET, NO_FRAGMENT);
        add_scenario(URN, NO_FRAGMENT);
        add_scenario(WIKIPEDIA, "Generic_syntax");

        scenarios.keySet().forEach(unprocessedUri -> {
            Object expectedFragment = scenarios.get(unprocessedUri);
            URI uri = new URI(unprocessedUri);
            String parsedFragment = uri.getFragment();
            assertThat(parsedFragment, is(equalTo(expectedFragment)));
        });
    }

    @Test
    public void uri_toString_is_uri() {
        add_scenario(SCHEME, SCHEME);
        add_scenario(HTTPS, HTTPS);
        add_scenario(LDAP, LDAP);
        add_scenario(MAILTO, MAILTO);
        add_scenario(NEWS, NEWS);
        add_scenario(TEL, TEL);
        add_scenario(TELNET, TELNET);
        add_scenario(URN, URN);
        add_scenario(WIKIPEDIA, WIKIPEDIA);

        scenarios.keySet().forEach(unprocessedUri -> {
            Object expectedURI = scenarios.get(unprocessedUri);
            URI uri = new URI(unprocessedUri);
            String processedURI = uri.toString();
            assertThat(processedURI, is(equalTo(expectedURI)));
        });
    }


    @Test
    public void uri_builds_in_full_from_individual_components() {

        class TestURI extends URI {
            TestURI(String[] parts) {
                super(parts[0], parts[1], parts[2], parts[3], parts[4]);
            }
        }

        String EMPTY = COMPONENT_IS_EMPTY;

        String[] schemeParts = {"scheme", "authority", "/path", "query", "fragment"};
        String[] httpsParts = {"https", "john.doe@www.example.com:123", "/forum/questions/", "tag=networking&order=newest", "top"};
        String[] ldapParts = {"ldap", "[2001:db8::7]", "/c=GB", "objectClass?one", EMPTY};
        String[] mailtoParts = {"mailto", EMPTY, "John.Doe@example.com", EMPTY, EMPTY};
        String[] newsParts = {"news", EMPTY, "comp.infosystems.www.servers.unix", EMPTY, EMPTY};
        String[] telParts = {"tel", EMPTY, "+1-816-555-1212", EMPTY, EMPTY};
        String[] telnetParts = {"telnet", "192.0.2.16:80", "/", EMPTY, EMPTY};
        String[] urnParts = {"urn", EMPTY, "oasis:names:specification:docbook:dtd:xml:4.1.2", EMPTY, EMPTY};
        String[] wikipediaParts = {"https", "en.wikipedia.org", "/wiki/Uniform_Resource_Identifier", EMPTY, "Generic_syntax"};

        add_scenario(SCHEME, schemeParts);
        add_scenario(HTTPS, httpsParts);
        add_scenario(LDAP, ldapParts);
        add_scenario(MAILTO, mailtoParts);
        add_scenario(NEWS, newsParts);
        add_scenario(TEL, telParts);
        add_scenario(TELNET, telnetParts);
        add_scenario(URN, urnParts);
        add_scenario(WIKIPEDIA, wikipediaParts);

        scenarios.keySet().forEach(unprocessedUri -> {
            Object uriComponents = scenarios.get(unprocessedUri);
            URI uri = new TestURI((String[])uriComponents);
            String processedURI = uri.toString();
            assertThat(processedURI, is(equalTo(unprocessedUri)));
        });
    }

    @Test
    public void uri_rebuilds_itself () {
        add_scenario(SCHEME, SCHEME);
        add_scenario(HTTPS, HTTPS);
        add_scenario(LDAP, LDAP);
        add_scenario(MAILTO, MAILTO);
        add_scenario(NEWS, NEWS);
        add_scenario(TEL, TEL);
        add_scenario(TELNET, TELNET);
        add_scenario(URN, URN);
        add_scenario(WIKIPEDIA, WIKIPEDIA);

        scenarios.keySet().forEach(unprocessedUri -> {
            Object expectedURI = scenarios.get(unprocessedUri);

            URI parsedUri = new URI(unprocessedUri);
            String scheme = parsedUri.getScheme();
            String authority = parsedUri.getAuthority();
            String path = parsedUri.getPath();
            String query = parsedUri.getQuery();
            String fragment = parsedUri.getFragment();

            URI builtUri = new URI(scheme, authority, path, query, fragment);
            String processedURI = builtUri.toString();

            assertThat(processedURI, is(equalTo(expectedURI)));
        });
    }
}