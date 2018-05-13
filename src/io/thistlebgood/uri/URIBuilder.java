package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.*;

public class URIBuilder {
    private String fullUri;
    private String scheme;
    private String authority;
    private String path;
    private String query;
    private String fragment;

    public URIBuilder(String scheme, String authority, String path, String query, String fragment) {
        this.scheme = scheme;
        this.authority = authority;
        this.path = path;
        this.query = query;
        this.fragment = fragment;
        buildUri();
    }

    private void buildUri() {
        resetUri();
        fullUri += buildScheme();
        fullUri += buildAuthority();
        fullUri += buildPath();
        fullUri += buildQuery();
        fullUri += buildFragment();
    }

    private void resetUri() {
        fullUri = COMPONENT_IS_EMPTY;
    }

    private String buildScheme() {
        return scheme + SCHEME_MARKER;
    }

    private String buildAuthority() {
        if (!authority.equals(COMPONENT_IS_EMPTY)) {
            return AUTHORITY_MARKER + authority;
        } else {
            return COMPONENT_IS_EMPTY;
        }
    }

    private String buildPath() {
        return path;
    }

    private String buildQuery() {
        if (!query.equals(COMPONENT_IS_EMPTY)) {
            return QUERY_MARKER + query;
        } else {
            return COMPONENT_IS_EMPTY;
        }
    }

    private String buildFragment() {
        if (!fragment.equals(COMPONENT_IS_EMPTY)) {
            return FRAGMENT_MARKER + fragment;
        } else {
            return COMPONENT_IS_EMPTY;
        }
    }

    @Override
    public String toString() {
        return fullUri;
    }
}
