import java.util.*;

public class Store {

    protected List<Product> productList;

    protected Map<Integer, Product> products;

    protected Map<Integer, Order> orders;

    protected Set<String> categories;

    protected Queue<Order> shippingQueue;

    protected Map<Integer, Order> deliveredOrders;

    protected List<Review> reviews;


    public Store() {

        productList = new ArrayList<>();

        products = new HashMap<>();

        orders = new HashMap<>();

        categories = new HashSet<>();

        shippingQueue = new LinkedList<>();

        deliveredOrders = new LinkedHashMap<>();

        reviews = new ArrayList<>();
    }


    public boolean addProduct(Product product) {

        if (products.containsKey(product.getId())) {

            return false;
        }

        productList.add(product);

        products.put(product.getId(), product);

        categories.add(product.getCategory());

        return true;
    }


    public boolean removeProduct(int id) {

        if (!products.containsKey(id)) {

            return false;
        }

        deleteProductEverywhere(id);

        return true;
    }


    private void deleteProductEverywhere(int id) {

        Product product = products.get(id);

        productList.remove(product);

        products.remove(id);
    }


    public Product searchProduct(int id) {

        return products.get(id);
    }


    public void displayProducts() {

        System.out.println(productList);
    }


    public void displayCategories() {

        System.out.println(categories);
    }


    public void displayProductsByPrice() {

        List<Product> copy = new ArrayList<>(productList);

        Collections.sort(copy);

        System.out.println(copy);
    }


    public boolean createOrder(Order order) {

        if (orders.containsKey(order.getOrderId())) {

            return false;
        }

        order.setStatus(OrderStatus.PENDING);

        orders.put(order.getOrderId(), order);

        return true;
    }


    public boolean addItem(
            int orderId,
            int productId,
            int quantity) {

        Order order = orders.get(orderId);

        Product product = products.get(productId);


        if (order == null || product == null) {

            return false;
        }


        if (order.getStatus() == OrderStatus.SHIPPED ||
                order.getStatus() == OrderStatus.DELIVERED ||
                order.getStatus() == OrderStatus.CANCELLED) {

            return false;
        }


        CartItem cartItem =
                new CartItem(product, quantity);

        order.addItem(cartItem);

        return true;
    }


    public boolean removeItem(
            int orderId,
            int productId) {

        Order order = orders.get(orderId);

        if (order == null) {

            return false;
        }


        if (order.getStatus() == OrderStatus.SHIPPED ||
                order.getStatus() == OrderStatus.DELIVERED ||
                order.getStatus() == OrderStatus.CANCELLED) {

            return false;
        }


        return order.removeItem(productId);
    }


    public Order searchOrder(int orderId) {

        return orders.get(orderId);
    }


    public boolean addShippingOrder(int orderId) {

        Order order = orders.get(orderId);


        if (order == null) {

            return false;
        }


        if (order.getStatus() != OrderStatus.PENDING) {

            return false;
        }


        if (order.getItemList().isEmpty()) {

            return false;
        }


        if (shippingQueue.contains(order)) {

            return false;
        }


        shippingQueue.add(order);

        order.setStatus(OrderStatus.SHIPPED);

        return true;
    }


    public boolean shipNextOrder() {

        if (shippingQueue.isEmpty()) {

            return false;
        }


        Order order = shippingQueue.peek();


        if (order.getItemList().isEmpty()) {

            System.out.println(
                    "This order has no items and cannot be shipped."
            );

            return false;
        }


        shippingQueue.poll();

        order.setStatus(OrderStatus.DELIVERED);

        deliveredOrders.put(order.getOrderId(), order);

        return true;
    }


    public boolean cancelOrder(int orderId) {

        Order order = orders.get(orderId);


        if (order == null) {

            return false;
        }


        if (order.getStatus() == OrderStatus.DELIVERED ||
                order.getStatus() == OrderStatus.CANCELLED) {

            return false;
        }


        if (order.getStatus() == OrderStatus.SHIPPED) {

            shippingQueue.remove(order);
        }


        order.setStatus(OrderStatus.CANCELLED);

        return true;
    }


    public void displayDeliveredOrders() {

        System.out.println(deliveredOrders);
    }


    public boolean addReview(Review review) {

        if (!products.containsKey(review.getProductId())) {

            return false;
        }

        reviews.add(review);

        return true;
    }


    public void displayReviews(int productId) {

        for (Review review : reviews) {

            if (review.getProductId() == productId) {

                System.out.println(review);
            }
        }
    }


    public void removeOutOfStockProducts() {

        Iterator<Product> iterator =
                productList.iterator();


        while (iterator.hasNext()) {

            Product product = iterator.next();


            if (product.getStockQuantity() == 0) {

                int id = product.getId();

                iterator.remove();

                products.remove(id);
            }
        }
    }


    public void displayOrdersByTotal() {

        List<Order> copy =
                new ArrayList<>(orders.values());


        Collections.sort(
                copy,
                new OrderTotalComparator()
        );


        System.out.println(copy);
    }
}