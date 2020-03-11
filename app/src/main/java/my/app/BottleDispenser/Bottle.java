package my.app.BottleDispenser;

public class Bottle {
    private String name;
    private String manufacturer;
    private float size;
    private float price;

    public Bottle(int i, int k){
        if (i < 3) {
            name = "Pepsi Max";
            manufacturer = "Pepsi";
            switch (k) {
                case 0:
                    size = 0.5f;
                    price = 1.0f;
                    break;
                case 1:
                    size = 1.0f;
                    price = 1.8f;
                    break;
                case 2:
                    size = 1.5f;
                    price = 2.5f;
                    break;
            }
        }
        else if (i < 6) {
            name = "Coca Cola";
            manufacturer = "Coca Cola";
            switch (k) {
                case 3:
                    size = 0.5f;
                    price = 1.0f;
                    break;
                case 4:
                    size = 1.0f;
                    price = 1.8f;
                    break;
                case 5:
                    size = 1.5f;
                    price = 2.5f;
                    break;
            }
        }
        else if (i < 9) {
            name = "Fanta";
            manufacturer = "Coca Cola";
            switch (k) {
                case 6:
                    size = 0.5f;
                    price = 1.0f;
                    break;
                case 7:
                    size = 1.0f;
                    price = 1.8f;
                    break;
                case 8:
                    size = 1.5f;
                    price = 2.5f;
                    break;
            }
        }

    }
    public String getName(){
        return name;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public float getSize() {
        return size;
    }

    public float getPrice() {
        return price;
    }
}
