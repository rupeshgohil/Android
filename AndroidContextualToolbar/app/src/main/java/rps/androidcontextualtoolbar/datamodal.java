package rps.androidcontextualtoolbar;

public class datamodal {
    public String item;
    public boolean aBoolean;

    public datamodal(String item1, boolean b) {
        this.item = item1;
        this.aBoolean = b;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }
}
