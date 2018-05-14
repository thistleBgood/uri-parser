package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.AUTHORITY_MARKER;
import static io.thistlebgood.uri.URIConstants.COMPONENT_IS_EMPTY;
import static io.thistlebgood.uri.URIUtils.cropToMarker;

class AuthorityParser {

    static String parse(String fullUri) {
        String authority = COMPONENT_IS_EMPTY;

        int startMarker = fullUri.indexOf(AUTHORITY_MARKER);

        if (!(startMarker == -1)) {
            String trimmedUri = fullUri.substring(startMarker + AUTHORITY_MARKER.length());
            authority = cropToMarker(trimmedUri, "/");
        }

        return authority;
    }

}
