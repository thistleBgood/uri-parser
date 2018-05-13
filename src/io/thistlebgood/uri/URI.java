package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.COMPONENT_IS_EMPTY;
/**
 * The URI generic syntax consists of a hierarchical sequence of five components:
 *
 *     scheme:[//authority]path[?query][#fragment]
 *
 * where the authority component divides into three subcomponents:
 *
 *     authority = [userinfo@]host[:port]
 *
 * (brackets indicate optional components)
 */
public class URI {
    private String fullUri;
    private String scheme;
    private String authority;
    private String path;
    private String query;
    private String fragment;

    public URI(String uri) {
        fullUri = uri;
        URIParser parser = new URIParser(uri);
        scheme = parser.parseScheme();
        authority = parser.parseAuthority();
        path = parser.parsePath();
        query = parser.parseQuery();
        fragment = parser.parseFragment();
    }

    public String getScheme() {
        return scheme;
    }

    public boolean hasAuthority() {
        return checkOptionalComponentIsPresent(authority);
    }

    public String getAuthority() {
        return authority;
    }

    public String getPath() {
        return path;
    }

    public boolean hasQuery() {
        return checkOptionalComponentIsPresent(query);
    }

    public String getQuery() {
        return query;
    }

    public boolean hasFragment() {
        return checkOptionalComponentIsPresent(fragment);
    }

    public String getFragment() {
        return fragment;
    }

    private boolean checkOptionalComponentIsPresent(String component) {
        return !component.equals(COMPONENT_IS_EMPTY);
    }
}
