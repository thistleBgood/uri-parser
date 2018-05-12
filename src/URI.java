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
    private static final String NO_COMPONENT_FOUND = "";

    private String fullUri;
    private String scheme;
    private String authority;

    public URI(String uri) {
        fullUri = uri;
        parseScheme();
        parseAuthority();
    }

    public String getScheme() {
        return scheme;
    }

    public String getAuthority() {
        return authority;
    }

    private void parseScheme() {
        scheme = cropToMarker(fullUri, ":");
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

    private String cropToMarker(String uncropped, String marker) {
        int endMarker = uncropped.indexOf(marker);
        return uncropped.substring(0, endMarker);
    }
}
