module com.terfezio.di_parte1 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.terfezio.di_parte1 to javafx.fxml;
    exports com.terfezio.di_parte1;
}