package models;

enum CurrencyCode {
    EUR, // Euro
    MGA // Ariary
}
public class Currency {
    private int id;
    private String name;
    private CurrencyCode code;

    public Currency(int id, String name, CurrencyCode code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CurrencyCode getCode() {
        return code;
    }

    public void setCode(CurrencyCode code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                '}';
    }
}
