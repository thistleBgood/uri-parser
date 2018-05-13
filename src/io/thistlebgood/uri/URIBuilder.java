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
        toString();
    }

    @Override
    public String toString() {
        resetUri();
        fullUri += buildScheme();
        fullUri += buildAuthority();
        fullUri += buildPath();
        fullUri += buildQuery();
        fullUri += buildFragment();
        return fullUri;
    }

    private void resetUri() {
        fullUri = COMPONENT_IS_EMPTY;
    }

    private String buildScheme() {
        return scheme + SCHEME_MARKER;
    }

    private String buildAuthority() {
        return buildOptionalComponent(AUTHORITY_MARKER, authority);
    }

    private String buildPath() {
        return path;
    }

    private String buildQuery() {
        return buildOptionalComponent(QUERY_MARKER, query);
    }

    private String buildFragment() {
        return buildOptionalComponent(FRAGMENT_MARKER, fragment);
    }

    private String buildOptionalComponent(String marker, String component) {
        if (!component.equals(COMPONENT_IS_EMPTY)) {
            return marker + component;
        } else {
            return COMPONENT_IS_EMPTY;
        }
    }
}
