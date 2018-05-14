package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.FRAGMENT_MARKER;
import static io.thistlebgood.uri.URIConstants.QUERY_MARKER;
import static io.thistlebgood.uri.URIUtils.cropToMarker;
import static io.thistlebgood.uri.URIUtils.indexFound;

class PathParser {

    static String parse(String fullUri, int startMarker) {
        return cropAfterPath(cropBeforePath(fullUri, startMarker));
    }

    private static String cropBeforePath(String fullUri, int startMarker) {
        return fullUri.substring(startMarker);
    }

    private static String cropAfterPath(String trimmedUri) {
        if (hasQuery(trimmedUri)) {
            return cropToQuery(trimmedUri);
        } else if (hasFragment(trimmedUri)) {
            return cropToFragment(trimmedUri);
        } else /* neither optional component is present */ {
            return trimmedUri;
        }
    }

    private static boolean hasQuery(String trimmedUri) {
        return hasOptionalComponent(trimmedUri, QUERY_MARKER);
    }

    private static boolean hasFragment (String trimmedUri){
        return hasOptionalComponent(trimmedUri, FRAGMENT_MARKER);
    }
    private static boolean hasOptionalComponent(String trimmedUri, String marker) {
        int queryMarker = trimmedUri.indexOf(marker);
        return indexFound(queryMarker);
    }

    private static String cropToQuery(String trimmedUri) {
        return cropToMarker(trimmedUri, QUERY_MARKER);
    }

    private static String cropToFragment(String trimmedUri) {
        return cropToMarker(trimmedUri, FRAGMENT_MARKER);
    }
}
