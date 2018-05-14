package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.AUTHORITY_MARKER;
import static io.thistlebgood.uri.URIConstants.QUERY_MARKER;
import static io.thistlebgood.uri.URIConstants.SCHEME_MARKER;
import static io.thistlebgood.uri.URIUtils.optionalComponentIsPresent;

public class URIIndexer {

    private static int getSchemeEndIndex(URIData uri) {
        return uri.scheme.length() + SCHEME_MARKER.length();
    }

    public static int getPathStartIndex(URIData uri) {
        int index = getSchemeEndIndex(uri);

        if (optionalComponentIsPresent(uri.authority)) {
            index += AUTHORITY_MARKER.length() + uri.authority.length();
        }

        return index;
    }

    public static int getPathEndIndex(URIData uri) {
        return getPathStartIndex(uri) + uri.path.length();
    }


    public static int getQueryEndIndex(URIData uri) {
        int index = getPathEndIndex(uri);
        if (optionalComponentIsPresent(uri.query)) {
            index += QUERY_MARKER.length() + uri.query.length();
        }
        return index;
    }
}
