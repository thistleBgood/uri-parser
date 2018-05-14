package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.*;
import static io.thistlebgood.uri.URIUtils.optionalComponentIsPresent;

public class URIBuilder {
    private StringBuilder builder;
    private URIData uri;

    public URIBuilder(String scheme, String authority, String path, String query, String fragment) {
        this.uri = new URIData();

        this.uri.scheme = scheme;
        this.uri.authority = authority;
        this.uri.path = path;
        this.uri.query = query;
        this.uri.fragment = fragment;

        this.uri.fullUri = buildFullUriFromComponents();
    }

    URIData getURIData() {
        return this.uri;
    }

    @Override
    public String toString() {
        return this.uri.fullUri;
    }

    private String buildFullUriFromComponents() {
        resetBuilder();
        buildScheme();
        buildAuthority();
        buildPath();
        buildQuery();
        buildFragment();

        return buildFullUri();
    }

    private void buildScheme() {
        append(this.uri.scheme + SCHEME_MARKER);
    }

    private void buildAuthority() {
        append(buildOptionalComponent(AUTHORITY_MARKER, this.uri.authority));
    }

    private void buildPath() {
        append(this.uri.path);
    }

    private void buildQuery() {
        append(buildOptionalComponent(QUERY_MARKER, this.uri.query));
    }

    private void buildFragment() {
        append(buildOptionalComponent(FRAGMENT_MARKER, this.uri.fragment));
    }

    private String buildOptionalComponent(String marker, String component) {
        if (optionalComponentIsPresent(component)) {
            return marker + component;
        } else {
            return COMPONENT_IS_EMPTY;
        }
    }

    private void resetBuilder() {
        builder = new StringBuilder();
    }

    private void append(String component) {
        builder.append(component);
    }

    private String buildFullUri() {
        return builder.toString();
    }
}
