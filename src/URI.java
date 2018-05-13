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
    private static final String SCHEME_MARKER = ":";
    private static final String AUTHORITY_MARKER = "//";
    private static final String QUERY_MARKER = "?";
    private static final String FRAGMENT_MARKER = "#";

    private static final String NO_COMPONENT_FOUND = "";

    private String fullUri;
    private String scheme;
    private String authority;
    private String path;
    private String query;
    private String fragment;

    public URI(String uri) {
        fullUri = uri;
        parseScheme();
        parseAuthority();
        parsePath();
        parseQuery();
        parseFragment();
    }

    public String getScheme() {
        return scheme;
    }

    public boolean hasAuthority() {
        return checkOptionalComponentIsPresent(authority);
    }

    public String getAuthority() {
        return authority;
    }

    public String getPath() {
        return path;
    }

    public boolean hasQuery() {
        return checkOptionalComponentIsPresent(query);
    }

    public String getQuery() {
        return query;
    }

    public boolean hasFragment() {
        return checkOptionalComponentIsPresent(fragment);
    }

    public String getFragment() {
        return fragment;
    }

    private boolean checkOptionalComponentIsPresent(String component) {
        return !component.equals(NO_COMPONENT_FOUND);
    }

    private void parseScheme() {
        scheme = cropToMarker(fullUri, SCHEME_MARKER);
    }

    private int getSchemeEndIndex() {
        return scheme.length() + SCHEME_MARKER.length();
    }

    private void parseAuthority() {
        String marker = AUTHORITY_MARKER;
        int startMarker = fullUri.indexOf(marker);

        if (startMarker == -1) {
            authority = NO_COMPONENT_FOUND;
        } else {
            String trimmedUri = fullUri.substring(startMarker + marker.length());
            authority = cropToMarker(trimmedUri, "/");
        }
    }

    private void parsePath() {
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

    }

    private int getPathStartIndex() {
        int index = getSchemeEndIndex();

        if (hasAuthority()) {
            index += AUTHORITY_MARKER.length() + authority.length();
        }

        return index;
    }

    private int getPathEndIndex() {
        return getPathStartIndex() + path.length();
    }

    private void parseQuery() {
        int startMarker = getPathEndIndex();

        if (startMarker < fullUri.length()) {
            if (fullUri.substring(startMarker, startMarker+1).equals(FRAGMENT_MARKER)) {
                query = NO_COMPONENT_FOUND;
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
            query = NO_COMPONENT_FOUND;
        }
    }

    private int getQueryEndIndex() {
        int index = getPathEndIndex();
        if (query.length() > 0) {
            index += QUERY_MARKER.length() + query.length();
        }
        return index;
    }

    private void parseFragment() {
        int fragmentStartMarker = getQueryEndIndex();
        if(fragmentStartMarker < fullUri.length()) {
            fragment = fullUri.substring(fragmentStartMarker + FRAGMENT_MARKER.length());
        } else {
            fragment = NO_COMPONENT_FOUND;
        }
    }

    private String cropToMarker(String uncropped, String marker) {
        int endMarker = uncropped.indexOf(marker);
        return uncropped.substring(0, endMarker);
    }
}
