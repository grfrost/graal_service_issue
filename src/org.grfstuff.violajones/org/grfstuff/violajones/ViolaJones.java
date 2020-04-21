package org.grfstuff.violajones;

class Range2D{
    int minX;
    int minY;
    int maxX;
    int maxY;

    public Range2D(int width, int height) {
        minX = 0;
        minY = 0;
        maxX = width;
        maxY = height;
    }
    public Range2D(Range2D r) {
        minX = r.minX;
        minY = r.minY;
        maxX = r.maxX;
        maxY = r.maxY;
    }
}
class Id2D extends Range2D{
    int x;
    int y;
    int groupX;
    int groupY;
    int xy;  // will be id.y*id.maxX+id.x
    Id2D(Range2D r, int x, int y, int groupX, int groupY){
        super(r);
        this.x = x;
        this.y = y;
        this.groupX = groupX;
        this.groupY = groupY;
    }

}

public class ViolaJones {
    private int []imageRGBA;
    private int []imageGrey;

    Range2D range;
    ViolaJones(int width, int height){
        this.imageRGBA = new int[width *height*4];
        this.imageGrey = new int[width *height];
        range = new Range2D(width, height);
    }
    void greyScale(Id2D id){
        int r = imageRGBA[id.xy*4+0];
        int g = imageRGBA[id.xy*4+1];
        int b = imageRGBA[id.xy*4+2];
        imageGrey[id.xy] = (int)(r*.07 + b*.72 + g *.21);
    }

    private void compute() {
        Id2D id2D = new Id2D(range, 0, 0, 0, 0);
        for (int x=range.minX; x< range.maxX; x++){
            id2D.x = x;
            for (int y=range.minY; y< range.maxY; y++){
                 id2D.y=y;
                 greyScale(id2D);
            }
        }

    }

    public static void main(String[] args){
        ViolaJones vj = new ViolaJones(1000,1000);
        for (int i=0; i< 1000; i++) {
            vj.compute();
        }
    }
}
