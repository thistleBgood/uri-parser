package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIUtils.optionalComponentIsPresent;
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

    public URI(String scheme, String authority, String path, String query, String fragment) {
        this(new URIBuilder(scheme, authority, path, query, fragment).toString());
    }

    public URI(String uri) {
        fullUri = uri;
        URIParser parser = new URIParser(uri);
        scheme = parser.parseScheme();
        authority = parser.parseAuthority();
        path = parser.parsePath();
        query = parser.parseQuery();
        fragment = parser.parseFragment();
    }

    @Override
    public String toString() {
        return fullUri;
    }

    public String getScheme() {
        return scheme;
    }

    public boolean hasAuthority() {
        return optionalComponentIsPresent(authority);
    }

    public String getAuthority() {
        return authority;
    }

    public String getPath() {
        return path;
    }

    public boolean hasQuery() {
        return optionalComponentIsPresent(query);
    }

    public String getQuery() {
        return query;
    }

    public boolean hasFragment() {
        return optionalComponentIsPresent(fragment);
    }

    public String getFragment() {
        return fragment;
    }
}
