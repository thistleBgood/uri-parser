package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.COMPONENT_IS_EMPTY;
import static io.thistlebgood.uri.URIConstants.FRAGMENT_MARKER;
import static io.thistlebgood.uri.URIIndexer.getQueryEndIndex;

class FragmentParser {

    static void parse (URIData uri) {
        uri.fragment = parse(uri.fullUri, getQueryEndIndex(uri));
    }

    static String parse(String fullUri, int startMarker) {
        if(startMarker < fullUri.length()) {
            return fullUri.substring(startMarker + FRAGMENT_MARKER.length());
        } else {
            return COMPONENT_IS_EMPTY;
        }
    }
}
