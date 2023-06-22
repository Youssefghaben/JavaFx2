package javafxproject;

import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CoffeeGUI extends Application {

    public TextField clientNumberField;
    public ChoiceBox<String> hotDrinksChoiceBox;
    public ChoiceBox<String> coldDrinksChoiceBox;
    public ChoiceBox<String> dessertsChoiceBox;
    public TextArea receiptTextArea;

    // Create an array list to store the orders
    public List<Order> orders = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {

        // Main layout and adjusting the size
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #FFF8D7");
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        
        
        //Coffee shop logo
        ImageView imageView = new ImageView("D:\\sssss\\JavaFXProject\\src\\javafxproject\\coffeeLogo.png");
        imageView.setFitHeight(170);
        imageView.setFitWidth(170);
        gridPane.add(imageView, 3, 2, 3, 2);  

        // Client number field
        Label clientNumber = new Label("Client Number:");
        clientNumberField = new TextField();
        gridPane.add(clientNumber, 0, 0);
        gridPane.add(clientNumberField, 1, 0);

        // Hot drinks section
        Label hotDrinks = new Label("Hot Drinks:");
        hotDrinksChoiceBox = new ChoiceBox<>();
        hotDrinksChoiceBox.getItems().addAll("Turkish Coffee\t\t15 EGP", "French Coffee\t\t25 EGP", "Tea\t\t\t\t  5 EGP");
        gridPane.add(hotDrinks, 0, 1);
        gridPane.add(hotDrinksChoiceBox, 1, 1);

        // Cold drinks section
        Label coldDrinks = new Label("Cold Drinks:");
        coldDrinksChoiceBox = new ChoiceBox<>();
        coldDrinksChoiceBox.getItems().addAll("Ice Coffee\t\t30 EGP", "Ice Latte\t\t\t35 EGP");
        gridPane.add(coldDrinks, 0, 2);
        gridPane.add(coldDrinksChoiceBox, 1, 2);

        // Desserts section
        Label desserts = new Label("Desserts:");
        dessertsChoiceBox = new ChoiceBox<>();
        dessertsChoiceBox.getItems().addAll("Cinnabon\t\t\t90 EGP", "Cheesecake\t\t60 EGP", "Ice Cream\t\t35 EGP");
        gridPane.add(desserts, 0, 3);
        gridPane.add(dessertsChoiceBox, 1, 3);

        // Order text area
        Label receipt = new Label("Receipt:");
        receiptTextArea = new TextArea();
        receiptTextArea.setEditable(false);
        gridPane.add(receipt, 0, 4);
        gridPane.add(receiptTextArea, 1, 4);

        // Add buttons section
        Button placeOrderButton = new Button("Place Order");
        Button viewOrdersButton = new Button("View Orders");
        Button clearButton = new Button("Clear");

        HBox buttonsBox = new HBox(10);
        buttonsBox.getChildren().addAll(placeOrderButton, viewOrdersButton, clearButton);
        gridPane.add(buttonsBox, 1, 6, 2, 1);

        // Set button actions
        placeOrderButton.setOnAction(e -> placeOrder());
        clearButton.setOnAction(e -> clearFields());
        viewOrdersButton.setOnAction(e -> viewOrders());

        // Set up the scene and stage
        Scene scene = new Scene(gridPane, 800, 420);
        primaryStage.setTitle("The Coffee House Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void clearFields() {
        clientNumberField.clear();
        hotDrinksChoiceBox.getSelectionModel().clearSelection();
        coldDrinksChoiceBox.getSelectionModel().clearSelection();
        dessertsChoiceBox.getSelectionModel().clearSelection();
        receiptTextArea.clear();
    }

    private List<String> getOrder() {
        // Generate order summaries for all orders in the orders ArrayList
        ArrayList<String> orderSummaries = new ArrayList<>();
        for (Order order : orders) {
            String orderSummary = "Client Number: " + order.getClientNumber()
                    + "    Order: " + order.getHotDrink() + ",  " + order.getColdDrink()
                    + ",  " + order.getDessert() + "    Price: " + order.getPrice() + " EGP";
            orderSummaries.add(orderSummary);
        }
        return orderSummaries;
    }

    private void viewOrders() {
        // Create stage to view orders
        Stage ordersStage = new Stage();
        ordersStage.setTitle("Orders");

        ListView<String> ordersListView = new ListView<>();
        ordersListView.getItems().addAll(getOrder());

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(ordersListView);

        Scene ordersScene = new Scene(vbox, 750, 250);
        ordersStage.setScene(ordersScene);
        ordersStage.show();
    }

    private void placeOrder() {
        // Get client information
        String clientNumber = clientNumberField.getText();

        
        // Generate order text
        StringBuilder orderBuilder = new StringBuilder();
        orderBuilder.append("Welcome to Coffee House!\n");
        orderBuilder.append("_______________________\n");
        orderBuilder.append("Client Number: ").append(clientNumber).append("\n");
        orderBuilder.append("Order:\n");

        // All possible choices 
        if (hotDrinksChoiceBox.getValue() != null && coldDrinksChoiceBox.getValue() != null && dessertsChoiceBox.getValue() != null) {
            
            orderBuilder.append("- ").append(hotDrinksChoiceBox.getValue()).append("\n");
            orderBuilder.append("- ").append(coldDrinksChoiceBox.getValue()).append("\n");
            orderBuilder.append("- ").append(dessertsChoiceBox.getValue());
            
        } else if (hotDrinksChoiceBox.getValue() != null && coldDrinksChoiceBox.getValue() != null) {
            
            orderBuilder.append("- ").append(hotDrinksChoiceBox.getValue()).append("\n");
            orderBuilder.append("- ").append(coldDrinksChoiceBox.getValue()).append("\n");

        } else if (hotDrinksChoiceBox.getValue() != null && dessertsChoiceBox.getValue() != null) {
            
            orderBuilder.append("- ").append(hotDrinksChoiceBox.getValue()).append("\n");
            orderBuilder.append("- ").append(dessertsChoiceBox.getValue());
            
        } else if (dessertsChoiceBox.getValue() != null && coldDrinksChoiceBox.getValue() != null) {
            
            orderBuilder.append("- ").append(coldDrinksChoiceBox.getValue()).append("\n");
            orderBuilder.append("- ").append(dessertsChoiceBox.getValue());
            
        } else if (hotDrinksChoiceBox.getValue() != null) {
            
            orderBuilder.append("- ").append(hotDrinksChoiceBox.getValue()).append("\n");
        } else if (coldDrinksChoiceBox.getValue() != null) {
            
            orderBuilder.append("- ").append(coldDrinksChoiceBox.getValue()).append("\n");
        } else if (dessertsChoiceBox.getValue() != null) {
            
            orderBuilder.append("- ").append(dessertsChoiceBox.getValue());
        }

        double price = calculatePrice(hotDrinksChoiceBox.getValue(), coldDrinksChoiceBox.getValue(), dessertsChoiceBox.getValue());
        
        orderBuilder.append("\n_______________________\n");
        orderBuilder.append("Total Price: ");
        receiptTextArea.setText(String.format("%f", price));
        orderBuilder.append(price).append(" EGP");

        orderBuilder.append("\nThank you for visiting :)");

        // Update Receipt text area 
        receiptTextArea.setText(orderBuilder.toString());

        //To add the data in the array list
        Order order = new Order(clientNumber, hotDrinksChoiceBox.getValue(), coldDrinksChoiceBox.getValue(), dessertsChoiceBox.getValue(), price);
        orders.add(order);

    }

    private double calculatePrice(String hotDrink, String coldDrink, String dessert) {
        // You can implement your own pricing logic here
        double hotDrinkPrice = 0.0;
        double coldDrinkPrice = 0.0;
        double dessertPrice = 0.0;

        // Assign prices based on selected items
        if (hotDrink != null) {
            if (hotDrink.matches("Turkish.*")) {
                hotDrinkPrice = 15.0;
            } else if (hotDrink.matches("French Coffee.*")) {
                hotDrinkPrice = 25.0;
            } else if (hotDrink.matches("Tea.*")) {
                hotDrinkPrice = 5.0;
            }
        }

        if (coldDrink != null) {
            if (coldDrink.matches("Ice Coffee.*")) {
                coldDrinkPrice = 30.0;
            } else if (coldDrink.matches("Ice Latte.*")) {
                coldDrinkPrice = 35.0;
            }
        }

        if (dessert != null) {
            if (dessert.matches("Cinnabon.*")) {
                dessertPrice = 90.0;
            } else if (dessert.matches("Cheesecake.*")) {
                dessertPrice = 60.0;
            } else if (dessert.matches("Ice Cream.*")) {
                dessertPrice = 35.0;
            }
        }

        return hotDrinkPrice + coldDrinkPrice + dessertPrice;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class Order {

        private String clientNumber;
        private String hotDrink;
        private String coldDrink;
        private String dessert;
        private double price;

        public Order(String clientNumber, String hotDrink, String coldDrink, String dessert, double price) {
            this.clientNumber = clientNumber;
            this.hotDrink = hotDrink;
            this.coldDrink = coldDrink;
            this.dessert = dessert;
            this.price = price;
        }

        public String getClientNumber() {
            return clientNumber;
        }

        public String getHotDrink() {
            return hotDrink;
        }

        public String getColdDrink() {
            return coldDrink;
        }

        public String getDessert() {
            return dessert;
        }

        public double getPrice() {
            return price;
        }
    }
}
