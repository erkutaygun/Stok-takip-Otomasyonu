# Stok-takip-Otomasyonu

## Gerekli indirmeler

[Sqlite indirme link](https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.34.0/)<br>
[JavaFX indirme link](https://gluonhq.com/products/javafx/)<br>
[JFonenix indirme link](https://github.com/jfoenixadmin/JFoenix)

## Java VM options
```
--add-exports
javafx.controls/com.sun.javafx.scene.control.behavior=com.jfoenix
--add-exports
javafx.controls/com.sun.javafx.scene.control=com.jfoenix
--add-exports
javafx.base/com.sun.javafx.binding=com.jfoenix
--add-exports
javafx.graphics/com.sun.javafx.stage=com.jfoenix
--add-exports
javafx.base/com.sun.javafx.event=com.jfoenix
--add-opens
java.base/java.lang.reflect=com.jfoenix
```
