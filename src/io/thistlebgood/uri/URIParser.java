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

    private int getSchemeEndIndex() {
        return this.uri.scheme.length() + SCHEME_MARKER.length();
    }

    private void parseAuthority() {
        this.uri.authority = AuthorityParser.parse(this.uri.fullUri);
    }

    private int getPathStartIndex() {
        int index = getSchemeEndIndex();

        if (optionalComponentIsPresent(this.uri.authority)) {
            index += AUTHORITY_MARKER.length() + this.uri.authority.length();
        }

        return index;
    }

    private void parsePath() {
        this.uri.path = PathParser.parse(this.uri.fullUri, getPathStartIndex());
    }

    private int getPathEndIndex() {
        return getPathStartIndex() + this.uri.path.length();
    }

    private void parseQuery() {
        int startMarker = getPathEndIndex();

        if (startMarker < this.uri.fullUri.length()) {
            if (this.uri.fullUri.substring(startMarker, startMarker+1).equals(FRAGMENT_MARKER)) {
                this.uri.query = COMPONENT_IS_EMPTY;
            } else {
                String trimmedUri = this.uri.fullUri.substring(startMarker + QUERY_MARKER.length());
                int endMarker = trimmedUri.indexOf(FRAGMENT_MARKER);
                if (endMarker == -1) {
                    this.uri.query = trimmedUri;
                } else {
                    this.uri.query = cropToMarker(trimmedUri, FRAGMENT_MARKER);
                }
            }
        } else {
            this.uri.query = COMPONENT_IS_EMPTY;
        }
    }

    private int getQueryEndIndex() {
        int index = getPathEndIndex();
        if (optionalComponentIsPresent(this.uri.query)) {
            index += QUERY_MARKER.length() + this.uri.query.length();
        }
        return index;
    }

    private void parseFragment() {
        this.uri.fragment = FragmentParser.parse(this.uri.fullUri, getQueryEndIndex());
    }
}
