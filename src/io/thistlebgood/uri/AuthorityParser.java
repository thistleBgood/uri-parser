package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.AUTHORITY_MARKER;
import static io.thistlebgood.uri.URIConstants.PATH_MARKER;
import static io.thistlebgood.uri.URIConstants.COMPONENT_IS_EMPTY;
import static io.thistlebgood.uri.URIUtils.cropToMarker;
import static io.thistlebgood.uri.URIUtils.hasOptionalComponent;

class AuthorityParser {

    static void parse(URIData uri) {
        uri.authority = parse(uri.fullUri);
    }

    static String parse(String fullUri) {
        if (hasAuthority(fullUri)) {
            //This is in two stages as path marker is substring of authority marker.
            String trimmedUri = cropToAuthorityMarker(fullUri);
            return cropToMarker(trimmedUri, PATH_MARKER);
        } else {
            return COMPONENT_IS_EMPTY;
        }
    }

    private static boolean hasAuthority(String fullUri) {
        return hasOptionalComponent(fullUri, AUTHORITY_MARKER);
    }

    private static String cropToAuthorityMarker(String fullUri) {
        int startMarker = fullUri.indexOf(AUTHORITY_MARKER);
        return fullUri.substring(startMarker + AUTHORITY_MARKER.length());
    }

}
