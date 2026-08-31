import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderId;
    private String customerName;
    protected List<CartItem> itemList;
    private double total;
    protected OrderStatus status;

    public Order(int orderId, String customerName) {

        this.orderId = orderId;
        this.customerName = customerName;

        itemList = new ArrayList<>();

        total = 0;

        status = OrderStatus.PENDING;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<CartItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<CartItem> itemList) {
        this.itemList = itemList;
    }

    public double getTotal() {
        return total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void addItem(CartItem cartItem) {

        for (CartItem item : itemList) {

            if (item.getProduct().getId() ==
                    cartItem.getProduct().getId()) {

                item.setQuantity(
                        item.getQuantity() + cartItem.getQuantity()
                );

                calculateTotal();
                return;
            }
        }

        itemList.add(cartItem);

        calculateTotal();
    }

    public boolean removeItem(int productId) {

        for (int i = 0; i < itemList.size(); i++) {

            if (itemList.get(i).getProduct().getId() == productId) {

                itemList.remove(i);

                calculateTotal();

                return true;
            }
        }

        return false;
    }

    public void calculateTotal() {

        total = 0;

        for (CartItem item : itemList) {

            total += item.calculateSubtotal();
        }
    }

    public void displayOrder() {

        System.out.println("Order ID : " + orderId);
        System.out.println("Customer Name : " + customerName);
        System.out.println("Status : " + status);

        System.out.println("Items :");

        for (CartItem item : itemList) {

            System.out.println(item);
        }

        System.out.println("Total : " + total);
    }

    public void updateStatus(OrderStatus status) {

        this.status = status;
    }

    @Override
    public String toString() {

        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", itemList=" + itemList +
                ", total=" + total +
                ", status=" + status +
                '}';
    }
}