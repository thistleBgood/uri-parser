package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.*;

public class URIParser {
    private String fullUri;
    private String scheme;
    private String authority;
    private String path;
    private String query;
    private String fragment;

    public URIParser(String uri) {
        fullUri = uri;
        parseScheme();
        parseAuthority();
        parsePath();
        parseQuery();
        parseFragment();
    }

    public String parseScheme() {
        scheme = cropToMarker(fullUri, SCHEME_MARKER);
        return scheme;
    }

    private int getSchemeEndIndex() {
        return scheme.length() + SCHEME_MARKER.length();
    }

    public String parseAuthority() {
        String marker = AUTHORITY_MARKER;
        int startMarker = fullUri.indexOf(marker);

        if (startMarker == -1) {
            authority = COMPONENT_IS_EMPTY;
        } else {
            String trimmedUri = fullUri.substring(startMarker + marker.length());
            authority = cropToMarker(trimmedUri, "/");
        }
        return authority;
    }

    public String parsePath() {
        int startMarker = getPathStartIndex();

        String trimmedUri = fullUri.substring(startMarker);
        int queryMarker = trimmedUri.indexOf(QUERY_MARKER);

        if (queryMarker == -1) {
            int fragmentMarker = trimmedUri.indexOf(FRAGMENT_MARKER);
            if (fragmentMarker == -1) {
                path = trimmedUri;
            } else {
                path = cropToMarker(trimmedUri, FRAGMENT_MARKER);
            }
        } else {
            path = cropToMarker(trimmedUri, QUERY_MARKER);
        }

        return path;
    }

    private int getPathStartIndex() {
        int index = getSchemeEndIndex();

        if (authority.length() > 0) {
            index += AUTHORITY_MARKER.length() + authority.length();
        }

        return index;
    }

    private int getPathEndIndex() {
        return getPathStartIndex() + path.length();
    }

    public String parseQuery() {
        int startMarker = getPathEndIndex();

        if (startMarker < fullUri.length()) {
            if (fullUri.substring(startMarker, startMarker+1).equals(FRAGMENT_MARKER)) {
                query = COMPONENT_IS_EMPTY;
            } else {
                String trimmedUri = fullUri.substring(startMarker + QUERY_MARKER.length());
                int endMarker = trimmedUri.indexOf(FRAGMENT_MARKER);
                if (endMarker == -1) {
                    query = trimmedUri;
                } else {
                    query = cropToMarker(trimmedUri, FRAGMENT_MARKER);
                }
            }
        } else {
            query = COMPONENT_IS_EMPTY;
        }

        return query;
    }

    private int getQueryEndIndex() {
        int index = getPathEndIndex();
        if (query.length() > 0) {
            index += QUERY_MARKER.length() + query.length();
        }
        return index;
    }

    public String parseFragment() {
        int fragmentStartMarker = getQueryEndIndex();
        if(fragmentStartMarker < fullUri.length()) {
            fragment = fullUri.substring(fragmentStartMarker + FRAGMENT_MARKER.length());
        } else {
            fragment = COMPONENT_IS_EMPTY;
        }
        return fragment;
    }

    private String cropToMarker(String uncropped, String marker) {
        int endMarker = uncropped.indexOf(marker);
        return uncropped.substring(0, endMarker);
    }
}
