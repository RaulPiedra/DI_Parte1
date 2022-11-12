module com.terfezio.di_parte1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires io.github.osobolev.swingx;
    requires com.google.gson;
    requires MathParser.org.mXparser;


    opens com.terfezio.di_parte1 to javafx.fxml;
    exports com.terfezio.di_parte1;
}