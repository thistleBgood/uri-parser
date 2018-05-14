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
    private URIData uri;

    public URI(String scheme, String authority, String path, String query, String fragment) {
        this(new URIBuilder(scheme, authority, path, query, fragment).toString());
    }

    public URI(String uri) {
        this.uri = new URIParser(uri).getURIData();
    }

    @Override
    public String toString() {
        return this.uri.fullUri;
    }

    public String getScheme() {
        return this.uri.scheme;
    }

    public boolean hasAuthority() {
        return optionalComponentIsPresent(this.uri.authority);
    }

    public String getAuthority() {
        return this.uri.authority;
    }

    public String getPath() {
        return this.uri.path;
    }

    public boolean hasQuery() {
        return optionalComponentIsPresent(this.uri.query);
    }

    public String getQuery() {
        return this.uri.query;
    }

    public boolean hasFragment() {
        return optionalComponentIsPresent(this.uri.fragment);
    }

    public String getFragment() {
        return this.uri.fragment;
    }
}
