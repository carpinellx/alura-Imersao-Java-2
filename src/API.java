public enum API {
    IMDB_TOP_MOVEIS("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", new ExtratorDeConteudoDoIMDb()),
    IMDB_MOST_POPULAR_TVS("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json", new ExtratorDeConteudoDoIMDb()),
    NASA("https://api.nasa.gov/planetary/apod?api_key=76hhyMbykQvFKN9SiygrkdHf56LZgg6zDo8HZaay&start_date=2022-06-12&end_date=2022-06-14", new ExtratorDeConteudoDaNasa());
    


    private String url;
    private ExtratorDeConteudo extrator;

    API(String url, ExtratorDeConteudo extrator) {
        this.url = url;
        this.extrator = extrator;
    }

    public String getUrl() {
        return url;
    }

    public ExtratorDeConteudo getExtrator() {
        return extrator;
    }

}
