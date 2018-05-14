package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.COMPONENT_IS_EMPTY;
import static io.thistlebgood.uri.URIConstants.FRAGMENT_MARKER;
import static io.thistlebgood.uri.URIConstants.QUERY_MARKER;
import static io.thistlebgood.uri.URIIndexer.getPathEndIndex;
import static io.thistlebgood.uri.URIUtils.cropToMarker;

class QueryParser {

    static void parse(URIData uri) {
        uri.query = parse(uri.fullUri, getPathEndIndex(uri));
    }

    static String parse(String fullUri, int startMarker) {
        if (startMarker < fullUri.length()) {
            if (fullUri.substring(startMarker, startMarker+1).equals(FRAGMENT_MARKER)) {
                return COMPONENT_IS_EMPTY;
            } else {
                String trimmedUri = fullUri.substring(startMarker + QUERY_MARKER.length());
                int endMarker = trimmedUri.indexOf(FRAGMENT_MARKER);
                if (endMarker == -1) {
                    return trimmedUri;
                } else {
                    return cropToMarker(trimmedUri, FRAGMENT_MARKER);
                }
            }
        } else {
            return COMPONENT_IS_EMPTY;
        }
    }
}
