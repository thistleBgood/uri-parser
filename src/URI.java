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
    private static final String NO_COMPONENT_FOUND = "";

    private String fullUri;
    private String scheme;
    private String authority;
    private String path;

    public URI(String uri) {
        fullUri = uri;
        parseScheme();
        parseAuthority();
        parsePath();
    }

    public String getScheme() {
        return scheme;
    }

    public String getAuthority() {
        return authority;
    }

    public String getPath() {
        return path;
    }

    private void parseScheme() {
        scheme = cropToMarker(fullUri, SCHEME_MARKER);
    }

    private void parseAuthority() {
        String marker = "//";
        int startMarker = fullUri.indexOf(marker);
        if (startMarker == -1) {
            authority = NO_COMPONENT_FOUND;
        } else {
            String trimmedUri = fullUri.substring(startMarker + marker.length());
            authority = cropToMarker(trimmedUri, "/");
        }
    }

    private void parsePath() {
        int startMarker = scheme.length() + SCHEME_MARKER.length();

        if (!authority.equals(NO_COMPONENT_FOUND)) {
            startMarker += 2 + authority.length();
        }

        String trimmedUri = fullUri.substring(startMarker);
        int endMarker = trimmedUri.indexOf("?");

        if (endMarker == -1) {
            path = trimmedUri;
        } else {
            path = cropToMarker(trimmedUri, "?");
        }

    }

    private String cropToMarker(String uncropped, String marker) {
        int endMarker = uncropped.indexOf(marker);
        return uncropped.substring(0, endMarker);
    }
}
