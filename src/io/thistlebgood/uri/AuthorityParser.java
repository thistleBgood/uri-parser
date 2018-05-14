package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.AUTHORITY_MARKER;
import static io.thistlebgood.uri.URIConstants.PATH_MARKER;
import static io.thistlebgood.uri.URIConstants.COMPONENT_IS_EMPTY;
import static io.thistlebgood.uri.URIUtils.cropToMarker;
import static io.thistlebgood.uri.URIUtils.hasOptionalComponent;

class AuthorityParser {

    static String parse(String fullUri) {
        String authority = COMPONENT_IS_EMPTY;

        if (hasOptionalComponent(fullUri, AUTHORITY_MARKER)) {
            int startMarker = fullUri.indexOf(AUTHORITY_MARKER);
            String trimmedUri = fullUri.substring(startMarker + AUTHORITY_MARKER.length());
            authority = cropToMarker(trimmedUri, PATH_MARKER);
        }

        return authority;
    }

}
