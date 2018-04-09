package j2se.collection.compare;

class HDTV2 implements Comparable<HDTV2> {
    private int size;
    private String brand;

    public HDTV2(int size, String brand) {
        this.size = size;
        this.brand = brand;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public int compareTo(HDTV2 tv) {

        if (this.getSize() > tv.getSize())
            return 1;
        else if (this.getSize() < tv.getSize())
            return -1;
        else
            return 0;
    }
}

class Main2 {
    public static void main(String[] args) {
        HDTV2 tv1 = new HDTV2(55, "Samsung");
        HDTV2 tv2 = new HDTV2(60, "Sony");

        if (tv1.compareTo(tv2) > 0) {
            System.out.println(tv1.getBrand() + " is better.");
        } else {
            System.out.println(tv2.getBrand() + " is better.");
        }
    }
}
