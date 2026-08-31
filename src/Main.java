import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        Store store = new Store();

        int choice;

        do {

            System.out.println("\n========= E-COMMERCE SYSTEM =========");

            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Display All Products");
            System.out.println("4. Search Product by ID");
            System.out.println("5. Show All Categories");
            System.out.println("6. Display Products Ordered by Price");
            System.out.println("7. Create Order");
            System.out.println("8. Add Item to Order");
            System.out.println("9. Remove Item from Order");
            System.out.println("10. Display Order");
            System.out.println("11. Add Order to Shipping List");
            System.out.println("12. Ship Next Order");
            System.out.println("13. Cancel Order");
            System.out.println("14. Search Order by ID");
            System.out.println("15. Add Review to a Product");
            System.out.println("16. Show All Reviews for a Product");
            System.out.println("17. Remove Out-of-Stock Products");
            System.out.println("18. Display Orders Ordered by Total");
            System.out.println("19. Exit");

            System.out.print("Choice : ");

            choice = in.nextInt();
            in.nextLine();


            switch (choice) {

                case 1:

                    System.out.print("Product ID : ");
                    int id = in.nextInt();
                    in.nextLine();

                    System.out.print("Product Name : ");
                    String name = in.nextLine();

                    System.out.print("Product Price : ");
                    double price = in.nextDouble();
                    in.nextLine();

                    System.out.print("Product Category : ");
                    String category = in.nextLine();

                    System.out.print("Stock Quantity : ");
                    int stock = in.nextInt();
                    in.nextLine();


                    Product product =
                            new Product(
                                    id,
                                    name,
                                    price,
                                    category,
                                    stock
                            );


                    if (store.addProduct(product)) {

                        System.out.println("Product is added.");

                    } else {

                        System.out.println(
                                "Failed, the ID of product is found."
                        );
                    }

                    break;


                case 2:

                    System.out.print("Product ID : ");

                    id = in.nextInt();
                    in.nextLine();


                    if (store.removeProduct(id)) {

                        System.out.println(
                                "Product is removed."
                        );

                    } else {

                        System.out.println(
                                "Product is not found."
                        );
                    }

                    break;


                case 3:

                    store.displayProducts();

                    break;


                case 4:

                    System.out.print("Product ID : ");

                    id = in.nextInt();
                    in.nextLine();


                    Product foundProduct =
                            store.searchProduct(id);


                    if (foundProduct != null) {

                        System.out.println(foundProduct);

                    } else {

                        System.out.println(
                                "Product is not found."
                        );
                    }

                    break;


                case 5:

                    store.displayCategories();

                    break;


                case 6:

                    store.displayProductsByPrice();

                    break;


                case 7:

                    System.out.print("Order ID : ");

                    int orderId = in.nextInt();
                    in.nextLine();

                    System.out.print("Customer Name : ");

                    String customerName =
                            in.nextLine();


                    Order order =
                            new Order(
                                    orderId,
                                    customerName
                            );


                    if (store.createOrder(order)) {

                        System.out.println(
                                "Order is created."
                        );

                    } else {

                        System.out.println(
                                "The order ID is already found."
                        );
                    }

                    break;


                case 8:

                    System.out.print("Order ID : ");

                    orderId = in.nextInt();

                    System.out.print("Product ID : ");

                    id = in.nextInt();

                    System.out.print("Quantity : ");

                    int quantity = in.nextInt();

                    in.nextLine();


                    if (store.addItem(
                            orderId,
                            id,
                            quantity)) {

                        System.out.println(
                                "Item is added."
                        );

                    } else {

                        System.out.println(
                                "Item cannot be added."
                        );
                    }

                    break;


                case 9:

                    System.out.print("Order ID : ");

                    orderId = in.nextInt();

                    System.out.print("Product ID : ");

                    id = in.nextInt();

                    in.nextLine();


                    if (store.removeItem(
                            orderId,
                            id)) {

                        System.out.println(
                                "Item is removed."
                        );

                    } else {

                        System.out.println(
                                "Item cannot be removed."
                        );
                    }

                    break;


                case 10:

                    System.out.print("Order ID : ");

                    orderId = in.nextInt();
                    in.nextLine();


                    Order foundOrder =
                            store.searchOrder(orderId);


                    if (foundOrder != null) {

                        foundOrder.displayOrder();

                    } else {

                        System.out.println(
                                "Order is not found."
                        );
                    }

                    break;


                case 11:

                    System.out.print("Order ID : ");

                    orderId = in.nextInt();
                    in.nextLine();


                    if (store.addShippingOrder(orderId)) {

                        System.out.println(
                                "Order added to shipping list."
                        );

                    } else {

                        System.out.println(
                                "Order cannot be added to shipping list."
                        );
                    }

                    break;


                case 12:

                    if (store.shipNextOrder()) {

                        System.out.println(
                                "Order has been delivered."
                        );

                    } else {

                        System.out.println(
                                "No order can be shipped."
                        );
                    }

                    break;


                case 13:

                    System.out.print("Order ID : ");

                    orderId = in.nextInt();
                    in.nextLine();


                    if (store.cancelOrder(orderId)) {

                        System.out.println(
                                "Order is cancelled."
                        );

                    } else {

                        System.out.println(
                                "Order cannot be cancelled."
                        );
                    }

                    break;


                case 14:

                    System.out.print("Order ID : ");

                    orderId = in.nextInt();
                    in.nextLine();


                    foundOrder =
                            store.searchOrder(orderId);


                    if (foundOrder != null) {

                        System.out.println(foundOrder);

                    } else {

                        System.out.println(
                                "Order is not found."
                        );
                    }

                    break;


                case 15:

                    System.out.print("Product ID : ");

                    id = in.nextInt();
                    in.nextLine();

                    System.out.print("Customer Name : ");

                    customerName =
                            in.nextLine();

                    System.out.print("Comment : ");

                    String comment =
                            in.nextLine();


                    Review review =
                            new Review(
                                    id,
                                    customerName,
                                    comment
                            );


                    if (store.addReview(review)) {

                        System.out.println(
                                "Review is added."
                        );

                    } else {

                        System.out.println(
                                "Product is not found."
                        );
                    }

                    break;


                case 16:

                    System.out.print("Product ID : ");

                    id = in.nextInt();
                    in.nextLine();


                    store.displayReviews(id);

                    break;


                case 17:

                    store.removeOutOfStockProducts();

                    System.out.println(
                            "Out-of-stock products removed."
                    );

                    break;


                case 18:

                    store.displayOrdersByTotal();

                    break;


                case 19:

                    System.out.println(
                            "Wishing you a wonderful experience."
                    );

                    break;


                default:

                    System.out.println(
                            "Invalid Choice."
                    );
            }

        } while (choice != 19);
    }
}