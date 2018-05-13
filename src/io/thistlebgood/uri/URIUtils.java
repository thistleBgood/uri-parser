package io.thistlebgood.uri;

import static io.thistlebgood.uri.URIConstants.COMPONENT_IS_EMPTY;

class URIUtils {
    static boolean checkOptionalComponentIsPresent(String component) {
        return !component.equals(COMPONENT_IS_EMPTY);
    }
}
