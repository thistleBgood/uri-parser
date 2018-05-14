package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.SCHEME_MARKER;
import static io.thistlebgood.uri.URIUtils.cropToMarker;

class SchemeParser {

    static String parse(String fullUri) {
        return cropToMarker(fullUri, SCHEME_MARKER);
    }
}