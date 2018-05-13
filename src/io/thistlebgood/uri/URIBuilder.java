package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.*;
import static io.thistlebgood.uri.URIUtils.optionalComponentIsPresent;

public class URIBuilder {
    private URIData uri;

    public URIBuilder(String scheme, String authority, String path, String query, String fragment) {
        this.uri = new URIData();
        this.uri.scheme = scheme;
        this.uri.authority = authority;
        this.uri.path = path;
        this.uri.query = query;
        this.uri.fragment = fragment;
        toString();
    }

    @Override
    public String toString() {
        resetFullUri();
        this.uri.fullUri += buildScheme();
        this.uri.fullUri += buildAuthority();
        this.uri.fullUri += buildPath();
        this.uri.fullUri += buildQuery();
        this.uri.fullUri += buildFragment();
        return this.uri.fullUri;
    }

    private void resetFullUri() {
        this.uri.fullUri = COMPONENT_IS_EMPTY;
    }

    private String buildScheme() {
        return this.uri.scheme + SCHEME_MARKER;
    }

    private String buildAuthority() {
        return buildOptionalComponent(AUTHORITY_MARKER, this.uri.authority);
    }

    private String buildPath() {
        return this.uri.path;
    }

    private String buildQuery() {
        return buildOptionalComponent(QUERY_MARKER, this.uri.query);
    }

    private String buildFragment() {
        return buildOptionalComponent(FRAGMENT_MARKER, this.uri.fragment);
    }

    private String buildOptionalComponent(String marker, String component) {
        if (optionalComponentIsPresent(component)) {
            return marker + component;
        } else {
            return COMPONENT_IS_EMPTY;
        }
    }
}
