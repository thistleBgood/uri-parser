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
    private String fullUri;
    private String scheme;

    public URI(String uri) {
        fullUri = uri;
        parseScheme();
    }

    public String getScheme() {
        return scheme;
    }

    private void parseScheme() {
        int firstColon = fullUri.indexOf(":");
        scheme = this.fullUri.substring(0, firstColon);

    }
}
