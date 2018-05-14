package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.COMPONENT_IS_EMPTY;
import static io.thistlebgood.uri.URIConstants.FRAGMENT_MARKER;
import static io.thistlebgood.uri.URIConstants.QUERY_MARKER;
import static io.thistlebgood.uri.URIIndexer.getPathEndIndex;
import static io.thistlebgood.uri.URIUtils.cropToMarker;
import static io.thistlebgood.uri.URIUtils.indexFound;

class QueryParser {

    static void parse(URIData uri) {
        uri.query = parse(uri.fullUri, getPathEndIndex(uri));
    }

    static String parse(String fullUri, int startMarker) {
        if (queryComponentIsPresent(fullUri, startMarker)) {
            String trimmedUri = fullUri.substring(startMarker + QUERY_MARKER.length());
            int endMarker = trimmedUri.indexOf(FRAGMENT_MARKER);
            if (indexFound(endMarker)) {
                return cropToMarker(trimmedUri, FRAGMENT_MARKER);
            } else {
                return trimmedUri;
            }
        } else {
            return COMPONENT_IS_EMPTY;
        }
    }

    private static boolean queryComponentIsPresent(String fullUri, int startMarker) {
        return optionalComponentsArePresent(fullUri, startMarker) && queryMarkerIsPresent(fullUri, startMarker);
    }

    private static boolean optionalComponentsArePresent(String fullUri, int startMarker) {
        return startMarker < fullUri.length();
    }

    private static boolean queryMarkerIsPresent(String fullUri, int startMarker) {
        return fullUri.substring(startMarker, startMarker+1).equals(QUERY_MARKER);
    }
}
