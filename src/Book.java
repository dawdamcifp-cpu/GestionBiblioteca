public class Book {
    private String t;
    private String a;
    private String i;
    private int y;
    private boolean av;
    private String b;
    private int tc;

    public Book(String t, String a, String i, int y, String b) {
        this.t = t;
        this.a = a;
        this.i = i;
        this.y = y;
        this.av = true;
        this.b = b;
        this.tc = 0;
    }

    public String getT() { return t; }
    public void setT(String t) { this.t = t; }
    public String getA() { return a; }
    public void setA(String a) { this.a = a; }
    public String getI() { return i; }
    public void setI(String i) { this.i = i; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public boolean isAv() { return av; }
    public void setAv(boolean av) { this.av = av; }
    public String getB() { return b; }
    public void setB(String b) { this.b = b; }
    public int getTc() { return tc; }
    public void setTc(int tc) { this.tc = tc; }

    public void inc() {
        this.tc++;
    }

    public String getInfo() {
        return t + " - " + a + " (" + y + ") - ISBN: " + i + " - " + (av ? "Disponible" : "Prestado") + " - Categoría: " + b + " - Veces prestado: " + tc;
    }
}