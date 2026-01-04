import java.util.ArrayList;
import java.util.List;

public class User {
    private String n;
    private String id;
    private String e;
    private List<Book> bl;
    private int ml;
    private boolean act;

    public User(String n, String id, String e, int ml) {
        this.n = n;
        this.id = id;
        this.e = e;
        this.bl = new ArrayList<>();
        this.ml = ml;
        this.act = true;
    }

    public String getN() { return n; }
    public void setN(String n) { this.n = n; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getE() { return e; }
    public void setE(String e) { this.e = e; }
    public List<Book> getBl() { return bl; }
    public void setBl(List<Book> bl) { this.bl = bl; }
    public int getMl() { return ml; }
    public void setMl(int ml) { this.ml = ml; }
    public boolean isAct() { return act; }
    public void setAct(boolean act) { this.act = act; }

    public boolean canBorrow() {
        return act && bl.size() < ml;
    }

    public void addB(Book b) {
        bl.add(b);
    }

    public void removeB(Book b) {
        bl.remove(b);
    }

    public String getUserInfo() {
        return "Usuario: " + n + " (ID: " + id + ") - Email: " + e + " - Libros prestados: " + bl.size() + "/" + ml;
    }
}