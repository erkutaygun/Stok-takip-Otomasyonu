package JavaProje.controller;

abstract class Bilgiler implements IDepo,IStok {
    static void depo(){
        new Thread(IDepo.task2).start();
    }
    static void stok(){
        new Thread(IStok.task1).start();
    }
}
class TumBilgiler extends Bilgiler{

}
