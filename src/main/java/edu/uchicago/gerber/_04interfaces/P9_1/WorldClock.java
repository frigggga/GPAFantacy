package edu.uchicago.gerber._04interfaces.P9_1;

public class WorldClock extends Clock{
    private int offset;
    public WorldClock(int offset){
        super();
        this.offset = offset;
    }

    @Override
    public int getHours() {
        int h = super.getHours() + offset;
        if(h > 24){
            h -= 24;
            return h;
        }else if(h < 0){
            h += 24;
            return h;
        }else{
            return h;
        }
    }


}
