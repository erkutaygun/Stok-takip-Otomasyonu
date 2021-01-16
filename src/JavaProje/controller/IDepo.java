package JavaProje.controller;

import JavaProje.Database.Database;
import JavaProje.model.Depo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public interface IDepo {
        Task<ObservableList<Depo>> task2 = new depolar();
    }

    class depolar extends Task {
        @Override
        protected ObservableList<Depo> call() throws Exception {
            return FXCollections.observableArrayList(Database.getInstance().depolar());
        }
    }

