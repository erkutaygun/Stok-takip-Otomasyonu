package JavaProje.controller;

import JavaProje.Database.Database;
import JavaProje.model.Stok;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public interface IStok {
    Task<ObservableList<Stok>> task1 = new stokGetir();
}
class stokGetir extends Task {
    @Override
    protected ObservableList<Stok> call() throws Exception {
        return FXCollections.observableArrayList(Database.getInstance().stokGetir());
    }
}