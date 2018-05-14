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
        PathParser.parse(this.uri);
    }

    private void parseQuery() {
        QueryParser.parse(this.uri);
    }

    private void parseFragment() {
        this.uri.fragment = FragmentParser.parse(this.uri.fullUri, getQueryEndIndex());
    }

    private int getQueryEndIndex() {
        return URIIndexer.getQueryEndIndex(this.uri);
    }
}
