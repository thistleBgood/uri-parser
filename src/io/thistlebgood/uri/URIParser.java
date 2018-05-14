package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.*;
import static io.thistlebgood.uri.URIUtils.cropToMarker;
import static io.thistlebgood.uri.URIUtils.optionalComponentIsPresent;

public class URIParser {
    private URIData uri;

    public URIParser(String uri) {
        this.uri = new URIData();

        this.uri.fullUri = uri;

        parseComponentsFromFullUri();
    }

    URIData getURIData() {
        return this.uri;
    }

    private void parseComponentsFromFullUri() {
        parseScheme();
        parseAuthority();
        parsePath();
        parseQuery();
        parseFragment();
    }

    private void parseScheme() {
        this.uri.scheme = SchemeParser.parse(this.uri.fullUri);
    }

    private void parseAuthority() {
        this.uri.authority = AuthorityParser.parse(this.uri.fullUri);
    }

    private void parsePath() {
        this.uri.path = PathParser.parse(this.uri.fullUri, getPathStartIndex());
    }

    private void parseQuery() {
        this.uri.query = QueryParser.parse(this.uri.fullUri, getPathEndIndex());
    }

    private void parseFragment() {
        this.uri.fragment = FragmentParser.parse(this.uri.fullUri, getQueryEndIndex());
    }

    private int getSchemeEndIndex() {
        return this.uri.scheme.length() + SCHEME_MARKER.length();
    }

    private int getPathStartIndex() {
        int index = getSchemeEndIndex();

        if (optionalComponentIsPresent(this.uri.authority)) {
            index += AUTHORITY_MARKER.length() + this.uri.authority.length();
        }

        return index;
    }

    private int getPathEndIndex() {
        return getPathStartIndex() + this.uri.path.length();
    }

    private int getQueryEndIndex() {
        int index = getPathEndIndex();
        if (optionalComponentIsPresent(this.uri.query)) {
            index += QUERY_MARKER.length() + this.uri.query.length();
        }
        return index;
    }
}
