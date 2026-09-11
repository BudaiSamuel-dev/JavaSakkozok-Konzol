package classes;

public class Sakkozo {

    private String nev;
    private String nemzetiseg;
    private int maxEloPont;
    private String maxEloPontDatum;
    private String megjegyzes = null;

    public Sakkozo(String nev, String nemzetiseg, int maxEloPont, String maxEloPontDatum) {
        this.nev = nev;
        this.nemzetiseg = nemzetiseg;
        this.maxEloPont = maxEloPont;
        this.maxEloPontDatum = maxEloPontDatum;
    }

    public String getNev() {
        return nev;
    }

    public String getNemzetiseg() {
        return nemzetiseg;
    }

    public int getMaxEloPont() {
        return maxEloPont;
    }

    public String getMaxEloPontDatum() {
        return maxEloPontDatum;
    }

    public String getMegjegyzes() {
        return megjegyzes;
    }

    public void setMegjegyzes(String megjegyzes) {
        this.megjegyzes = megjegyzes;
    }

    public String getAdat(){
        return String.format("- %s -> %d pont (%s)",nev, maxEloPont, maxEloPontDatum.replace('-', '.')+".");
    }
}
