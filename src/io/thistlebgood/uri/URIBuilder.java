package io.thistlebgood.uri;

public class URIBuilder {
    public URIBuilder(String scheme, String authority, String path, String query, String fragment) {
    }

    @Override
    public String toString() {
        return "scheme://authority/path?query#fragment";
    }
}
