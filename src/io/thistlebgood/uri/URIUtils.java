package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.COMPONENT_IS_EMPTY;

class URIUtils {
    static boolean optionalComponentIsPresent(String component) {
        return !component.equals(COMPONENT_IS_EMPTY);
    }

    static String cropToMarker(String uncropped, String marker) {
        int endMarker = uncropped.indexOf(marker);
        return uncropped.substring(0, endMarker);
    }

    static boolean indexFound(int markerIndex) {
        return !(markerIndex == -1);
    }
}
