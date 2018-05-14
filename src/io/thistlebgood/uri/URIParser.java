package io.thistlebgood.uri;

public class URIParser {
    private URIData uri;

    public URIParser(String uri) {
        this.uri = new URIData();

        this.uri.fullUri = uri;

        parseComponentsFromFullUri();
    }

    URIData getURIData() {
        return this.uri;
    }

    private void parseComponentsFromFullUri() {
        parseScheme();
        parseAuthority();
        parsePath();
        parseQuery();
        parseFragment();
    }

    private void parseScheme() {
        SchemeParser.parse(this.uri);
    }

    private void parseAuthority() {
        AuthorityParser.parse(this.uri);
    }

    private void parsePath() {
        this.uri.path = PathParser.parse(this.uri.fullUri, getPathStartIndex());
    }

    private void parseQuery() {
        this.uri.query = QueryParser.parse(this.uri.fullUri, getPathEndIndex());
    }

    private void parseFragment() {
        this.uri.fragment = FragmentParser.parse(this.uri.fullUri, getQueryEndIndex());
    }

    private int getPathStartIndex() {
        return URIIndexer.getPathStartIndex(this.uri);
    }

    private int getPathEndIndex() {
        return URIIndexer.getPathEndIndex(this.uri);
    }

    private int getQueryEndIndex() {
        return URIIndexer.getQueryEndIndex(this.uri);
    }
}
