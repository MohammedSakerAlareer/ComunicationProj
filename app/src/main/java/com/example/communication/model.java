package com.example.communication;

public class model {
    private String yourname;
    private String yournumber;
    private String youraddress;

    public model(String name, String number, String address) {
        this.yourname=name;
        this.yournumber=number;
        this.youraddress=address;

    }

    public String getName() {
        return yourname;
    }

    public void setName(String name) {
        this.yourname = name;
    }

    public String getPhone() {
        return yournumber;
    }

    public void setPhone(String name) {
        this.yournumber = name;
    }

    public String getAddress() {
        return youraddress;
    }

    public void setAdress(String name) {
        this.youraddress = name;
    }

}
