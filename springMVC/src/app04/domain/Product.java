package app04.domain;

public class Product
{
    private static final long serialVersionUID = 5784L;
    private long id;
    private String name;
    private String description;
    private float price;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    public Product()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public Product(String name, String description, float price)
    {
        super();
        this.name = name;
        this.description = description;
        this.price = price;
    }

}
