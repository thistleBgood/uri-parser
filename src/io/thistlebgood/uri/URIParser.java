package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.*;

public class URIParser {
    private URIData uri;

    public URIParser(String uri) {
        this.uri = new URIData();
        this.uri.fullUri = uri;
        parseAllComponents();
    }

    public URIData getURIData() {
        return this.uri;
    }

    private void parseAllComponents() {
        parseScheme();
        parseAuthority();
        parsePath();
        parseQuery();
        parseFragment();
    }

    public String parseScheme() {
        this.uri.scheme = cropToMarker(this.uri.fullUri, SCHEME_MARKER);
        return this.uri.scheme;
    }

    private int getSchemeEndIndex() {
        return this.uri.scheme.length() + SCHEME_MARKER.length();
    }

    public String parseAuthority() {
        String marker = AUTHORITY_MARKER;
        int startMarker = this.uri.fullUri.indexOf(marker);

        if (startMarker == -1) {
            this.uri.authority = COMPONENT_IS_EMPTY;
        } else {
            String trimmedUri = this.uri.fullUri.substring(startMarker + marker.length());
            this.uri.authority = cropToMarker(trimmedUri, "/");
        }
        return this.uri.authority;
    }

    public String parsePath() {
        int startMarker = getPathStartIndex();

        String trimmedUri = this.uri.fullUri.substring(startMarker);
        int queryMarker = trimmedUri.indexOf(QUERY_MARKER);

        if (queryMarker == -1) {
            int fragmentMarker = trimmedUri.indexOf(FRAGMENT_MARKER);
            if (fragmentMarker == -1) {
                this.uri.path = trimmedUri;
            } else {
                this.uri.path = cropToMarker(trimmedUri, FRAGMENT_MARKER);
            }
        } else {
            this.uri.path = cropToMarker(trimmedUri, QUERY_MARKER);
        }

        return this.uri.path;
    }

    private int getPathStartIndex() {
        int index = getSchemeEndIndex();

        if (this.uri.authority.length() > 0) {
            index += AUTHORITY_MARKER.length() + this.uri.authority.length();
        }

        return index;
    }

    private int getPathEndIndex() {
        return getPathStartIndex() + this.uri.path.length();
    }

    public String parseQuery() {
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

        return this.uri.query;
    }

    private int getQueryEndIndex() {
        int index = getPathEndIndex();
        if (this.uri.query.length() > 0) {
            index += QUERY_MARKER.length() + this.uri.query.length();
        }
        return index;
    }

    public String parseFragment() {
        int fragmentStartMarker = getQueryEndIndex();
        if(fragmentStartMarker < this.uri.fullUri.length()) {
            this.uri.fragment = this.uri.fullUri.substring(fragmentStartMarker + FRAGMENT_MARKER.length());
        } else {
            this.uri.fragment = COMPONENT_IS_EMPTY;
        }
        return this.uri.fragment;
    }

    private String cropToMarker(String uncropped, String marker) {
        int endMarker = uncropped.indexOf(marker);
        return uncropped.substring(0, endMarker);
    }
}
