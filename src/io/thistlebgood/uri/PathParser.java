package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.FRAGMENT_MARKER;
import static io.thistlebgood.uri.URIConstants.QUERY_MARKER;
import static io.thistlebgood.uri.URIUtils.cropToMarker;
import static io.thistlebgood.uri.URIUtils.indexFound;

class PathParser {

    static String parse(String fullUri, int startMarker) {

        String trimmedUri = fullUri.substring(startMarker);
        String path = trimmedUri;
        int queryMarker = trimmedUri.indexOf(QUERY_MARKER);
        int fragmentMarker = trimmedUri.indexOf(FRAGMENT_MARKER);

        if (indexFound(queryMarker)) {
            path = cropToMarker(trimmedUri, QUERY_MARKER);
        } else if (indexFound(fragmentMarker)) {
            path = cropToMarker(trimmedUri, FRAGMENT_MARKER);
        }

        return path;
    }
}
