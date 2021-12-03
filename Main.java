import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Cycle[] cycles = new Cycle[n];

        int wheels;
        for (int i=0; i<n; i++){
            wheels = (int)(Math.random()*100);
            switch (wheels%3){
                case 1:
                    cycles[i] = new Unicycle(Math.ceil(Math.random()*100));
                    break;
                case 2:
                    cycles[i] = new Bicycle(Math.ceil(Math.random()*100),Math.ceil(Math.random()*100));
                    break;
                case 0:
                    cycles[i] = new Tricycle(Math.ceil(Math.random()*100), Math.ceil(Math.random()*100), Math.ceil(Math.random()*100));
            }
        }

        for (Cycle bike: cycles){
            if (bike instanceof Unicycle){
                bike.bikeRepair();
            }
        }
        System.out.println();

        Arrays.sort(cycles, new totalDiameterCom());
        for (int i=0; i<n; i++){
            System.out.print(cycles[i].totalDiameter + ", ");
        }
        System.out.println();

        Workshop workshop = new Workshop();
        for (int i=0; i<n; i+=3){
            workshop.anyBikeRepair(cycles[i]);
        }
        System.out.println();
        cycles[0].bikeBuying();
        cycles[0].bikeUse();
        cycles[0].bikeAssembly();
    }
}

abstract class Cycle{
    void bikeBuying(){
        System.out.println("Покупка велосипеда");
    }
    abstract void bikeRepair();
    abstract void bikeUse();
    abstract void bikeAssembly();
    protected double totalDiameter;
}

class totalDiameterCom implements java.util.Comparator<Cycle> {
    @Override
    public int compare(Cycle bike1, Cycle bike2) {
        if (bike1.totalDiameter < bike2.totalDiameter)
            return -1;
        else if (bike1.totalDiameter > bike2.totalDiameter)
            return 1;
        else
            return 0;
    }
}

class Unicycle extends Cycle {
    final double wheelDiameter;

    Unicycle(double wheelDiameter) {
        this.wheelDiameter = wheelDiameter;
        this.totalDiameter = wheelDiameter;
    }

    @Override
    void bikeRepair() {
        System.out.println("Ремонт одноколесного велосипеда");
    }

    @Override
    void bikeUse() {
        System.out.println("Использование одноколесного велосипеда");
    }

    @Override
    void bikeAssembly() {
        System.out.println("Сборка одноколесного велосипеда");
    }
}

class Bicycle extends Cycle {
    final double frontWheelDiameter, backWheelDiameter;

    Bicycle(double frontWheelDiameter, double backWheelDiameter){
        this.frontWheelDiameter = frontWheelDiameter;
        this.backWheelDiameter = backWheelDiameter;
        this.totalDiameter = frontWheelDiameter+backWheelDiameter;
    }

    @Override
    void bikeRepair() {
        System.out.println("Ремонт двухколесного велосипеда");
    }

    @Override
    void bikeUse() {
        System.out.println("Использование двухколесного велосипеда");
    }

    @Override
    void bikeAssembly() {
        System.out.println("Сборка двухколесного велосипеда");
    }
}

class Tricycle extends Cycle {
    final double frontWheelDiameter, middleWheelDiameter, backWheelDiameter;

    Tricycle(double frontWheelDiameter, double middleWheelDiameter, double backWheelDiameter){
        this.frontWheelDiameter = frontWheelDiameter;
        this.middleWheelDiameter = middleWheelDiameter;
        this.backWheelDiameter = backWheelDiameter;
        this.totalDiameter = frontWheelDiameter+ middleWheelDiameter+ backWheelDiameter;
    }

    @Override
    void bikeRepair() {
        System.out.println("Ремонт трехколесного велосипеда");
    }

    @Override
    void bikeUse() {
        System.out.println("Использование трехколесного велосипеда");
    }

    @Override
    void bikeAssembly() {
        System.out.println("Сборка трехколесного велосипеда");
    }
}

class Workshop{
    void anyBikeRepair(Cycle bike){
        if (bike instanceof Unicycle)
            System.out.println("Ремонт одноколесного велосипеда");
        else if (bike instanceof Bicycle)
            System.out.println("Ремонт двухколесного велосипеда");
        else if (bike instanceof Tricycle)
            System.out.println("Ремонт трехколесного велосипеда");
        else
            System.out.println("Неизвестный велосипед");
    }
}