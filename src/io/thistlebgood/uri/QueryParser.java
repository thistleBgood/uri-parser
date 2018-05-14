package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.COMPONENT_IS_EMPTY;
import static io.thistlebgood.uri.URIConstants.FRAGMENT_MARKER;
import static io.thistlebgood.uri.URIConstants.QUERY_MARKER;
import static io.thistlebgood.uri.URIIndexer.getPathEndIndex;
import static io.thistlebgood.uri.URIUtils.cropToMarker;
import static io.thistlebgood.uri.URIUtils.hasOptionalComponent;

class QueryParser {

    static void parse(URIData uri) {
        uri.query = parse(uri.fullUri, getPathEndIndex(uri));
    }

    static String parse(String fullUri, int startMarker) {
        return cropAfterQuery(cropBeforeQuery(fullUri, startMarker));
    }

    private static String cropAfterQuery(String trimmedUri1) {
        if (hasQuery(trimmedUri1)) {

            String trimmedUri2 = trimmedUri1.substring(QUERY_MARKER.length());

            if (hasFragment(trimmedUri1)) {
                trimmedUri2 = cropToMarker(trimmedUri2, FRAGMENT_MARKER);
            }

            return trimmedUri2;

        } else {
            return COMPONENT_IS_EMPTY;
        }
    }

    private static String cropBeforeQuery (String fullUri, int startMarker) {
        return fullUri.substring(startMarker);
    }

    private static boolean hasQuery(String trimmedUri) {
        return hasOptionalComponent(trimmedUri, QUERY_MARKER);
    }

    private static boolean hasFragment(String trimmedUri) {
        return hasOptionalComponent(trimmedUri, FRAGMENT_MARKER);
    }
}
