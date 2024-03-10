import java.lang.Math;
interface Shape{
    void printShape();
    double perimeter();
    double area();
}
class Circle implements Shape{
        private double r;

        Circle(double r){
            this.r = r;
        }
   public void printShape(){
        System.out.println("This is a circle \u25CB");
    }
   public double perimeter(){
        return Math.PI * r * 2;
    }
   public double area(){
        return Math.PI * r * r;
    }
}

class Square implements Shape{
    private double l;

    Square(double l){
        this.l = l;
    }

    public void printShape(){
        System.out.println("This is a square \u25A1");
    }

    public double perimeter(){
        return l * 4;
    }

    public double area(){
        return l*l;
    }
}

class Rectangular implements Shape{
    private double l,L;

    Rectangular(double l, double L){
        this.l = l;
        this.L = L;
    }

    public void printShape(){
        System.out.println("This is a rectangular \u25A1\u25A1");
    }

    public double perimeter(){
        return l * 2 + L*2;
    }

    public double area(){
        return l*L;
    }
}

public class Shapes {
    static void delimiter() {
        for (int i = 0; i < 150; i++)
            System.out.print('-');
        System.out.println();
    }

    public static void main(String[] args) {

        //CIRCLE
        Circle C = new Circle(1);
        C.printShape();
        System.out.println("Perimeter: " + C.perimeter());
        System.out.println("Area: " + C.area());
        delimiter();

        //SQUARE
        Square S = new Square(2);
        S.printShape();
        System.out.println("Perimeter: " + S.perimeter());
        System.out.println("Area: " + S.area());
        delimiter();

        //RECTANGULAR
        Rectangular R = new Rectangular(2,3);
        R.printShape();
        System.out.println("Perimeter: " + R.perimeter());
        System.out.println("Area: " + R.area());
        delimiter();
    }
}