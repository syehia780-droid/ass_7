# E-Commerce Order & Inventory Manager

A console-based **Java E-Commerce Order & Inventory Management System** designed to manage products, customer orders, categories, shipping queues, delivered orders, and customer reviews.

The project focuses on using the appropriate **Java Collections Framework** data structure for each business requirement, while applying object-oriented programming concepts such as **Encapsulation, Enum, Comparable, Comparator, and Iterator**.

---

## 📌 Project Overview

The system allows a store to:

* Add and remove products.
* Search for products quickly using their IDs.
* Display products in their original insertion order.
* Sort products by price.
* Manage customer orders and their items.
* Automatically calculate order totals.
* Manage the complete order lifecycle.
* Process shipping requests using **FIFO** order.
* Keep a permanent record of all orders.
* Track delivered orders in delivery sequence.
* Manage unique product categories.
* Add and display customer reviews.
* Safely remove out-of-stock products while iterating.
* Sort orders by their total value.

---

## 🎯 Learning Objectives

This project demonstrates how to:

* Choose the appropriate Java Collection based on a business requirement.
* Perform fast lookup using IDs.
* Prevent duplicate values.
* Implement **FIFO** processing.
* Preserve insertion order.
* Define a default ordering using `Comparable`.
* Define alternative ordering using `Comparator`.
* Safely remove elements while looping using `Iterator`.
* Use `Enum` for a fixed set of states.
* Store and manage custom Java objects.
* Maintain consistency between multiple collections containing the same data.

---

## 🧩 Main Classes

### 1. Product

Represents a product available in the store.

**Attributes:**

* `int id`
* `String name`
* `double price`
* `String category`
* `int stockQuantity`

**Main Features:**

* Constructors
* Getters and setters
* `toString()`
* Default ordering by price from cheapest to most expensive

The product implements `Comparable<Product>` so its natural ordering is based on price.

---

### 2. CartItem

Represents a product and the quantity ordered.

**Attributes:**

* `Product product`
* `int quantity`

**Methods:**

* `getProduct()`
* `getQuantity()`
* `calculateSubtotal()`

The subtotal is calculated as:

```text
Product Price × Quantity
```

---

### 3. Order

Represents a customer's order.

**Attributes:**

* `int orderId`
* `String customerName`
* Order items
* `double total`
* Order status

**Main Methods:**

* `addItem()`
* `removeItem()`
* `calculateTotal()`
* `displayOrder()`
* Update order status

The order items are stored in the same sequence in which the customer added them.

The total is automatically recalculated whenever an item is added or removed.

---

### 4. OrderStatus

An `enum` is used to represent the order's status.

Available states:

```text
PENDING
SHIPPED
DELIVERED
CANCELLED
```

Using an enum prevents invalid values such as:

```text
"delivred"
"Shipped "
"done"
```

Only the predefined states are allowed.

---

### 5. Review

Represents a customer review for a product.

**Attributes:**

* `int productId`
* `String customerName`
* `String comment`

Reviews are stored in the same order in which they were added.

---

## 🗂️ Collections Used

The project uses different collections depending on the required behavior.

| Data             | Collection                           | Reason                                                        |
| ---------------- | ------------------------------------ | ------------------------------------------------------------- |
| All Products     | `LinkedList<Product>`                | Preserves insertion order and supports sequential traversal   |
| Products by ID   | `HashMap<Integer, Product>`          | Fast lookup using product ID                                  |
| Orders by ID     | `HashMap<Integer, Order>`            | Fast direct order lookup                                      |
| Categories       | `HashSet<String>`                    | Prevents duplicate categories                                 |
| Shipping List    | `Queue<Order>` / `LinkedList<Order>` | Provides FIFO processing                                      |
| Delivered Orders | `LinkedHashMap<Integer, Order>`      | Preserves delivery order while keeping orders reachable by ID |
| Reviews          | `ArrayList<Review>`                  | Preserves insertion order and supports sequential traversal   |

---

## 🔄 Order Lifecycle

Every order remains in the permanent order record throughout its entire lifetime.

```text
Create Order
     ↓
  PENDING
     ↓
Add to Shipping List
     ↓
  SHIPPED
     ↓
Ship Next Order
     ↓
 DELIVERED
```

An order can also be cancelled:

```text
PENDING
   ↓
CANCELLED
```

or:

```text
SHIPPED
   ↓
CANCELLED
```

Cancelled and delivered orders remain in the permanent order record.

---

## 🚚 Shipping Queue

Orders waiting to be shipped are processed using **FIFO (First In, First Out)**.

Example:

```text
Order 501
Order 502
Order 503
```

Order `501` entered first, so it must be shipped first.

After shipping:

```text
Order 502
Order 503
```

The next order processed is `502`.

---

## 🔍 Fast Search

Products and orders can be searched directly by ID using `HashMap`.

For example:

```text
Product ID: 3
        ↓
HashMap
        ↓
Desk Lamp
```

The program does not need to scan every product to find the requested ID.

The same concept is used for searching orders.

---

## 📊 Product Ordering

Products have a default ordering based on price.

```text
USB Cable       60
Desk Lamp       180
Wireless Mouse  250
```

This ordering belongs to the `Product` class through `Comparable<Product>`.

When displaying products by price, a copy is sorted so the original product listing remains unchanged.

---

## 💰 Order Ordering

Orders have a different ordering rule: sorting by total value.

This ordering is implemented separately using:

```text
Comparator<Order>
```

For example:

```text
Order 502 → 120
Order 501 → 370
Order 503 → 800
```

The `Order` class itself does not need to know about this alternative ordering.

The permanent order record is also not modified because the program sorts a copy.

---

## 🗑️ Removing Out-of-Stock Products

Products with:

```text
stockQuantity == 0
```

are removed from the store.

The removal is performed during the same loop using an `Iterator`.

This is important because directly modifying a collection while using an enhanced `for` loop can cause:

```text
ConcurrentModificationException
```

The iterator allows safe removal using:

```java
iterator.remove();
```

The product is also removed from the ID-based `HashMap` to keep both product collections consistent.

---

## 🔐 Data Consistency

The same product is stored in more than one collection:

```text
Product List
     +
Product HashMap
```

Therefore, deleting a product must remove it from both places.

A private helper method is used for this purpose:

```java
deleteProductEverywhere(int id)
```

This ensures that a product cannot disappear from the product list while still being found through its ID.

---

## 🖥️ Application Menu

The application provides the following menu:

```text
1.  Add Product
2.  Remove Product
3.  Display All Products
4.  Search Product by ID
5.  Show All Categories
6.  Display Products Ordered by Price
7.  Create Order
8.  Add Item to Order
9.  Remove Item from Order
10. Display Order
11. Add Order to the Shipping List
12. Ship Next Order
13. Cancel Order
14. Search Order by ID
15. Add Review to a Product
16. Show All Reviews for a Product
17. Remove Out-of-Stock Products
18. Display Orders Ordered by Total
19. Exit
```

---

## 🧪 Example Data

### Products

| ID | Name           | Price | Category    | Stock |
| -: | -------------- | ----: | ----------- | ----: |
|  1 | Wireless Mouse |   250 | Electronics |    15 |
|  2 | Notebook       |    30 | Stationery  |     0 |
|  3 | Desk Lamp      |   180 | Home        |     8 |
|  4 | USB Cable      |    60 | Electronics |    20 |

### Example Order

```text
Order ID: 501
Customer: Sara
Status: Pending

Wireless Mouse × 1 = 250
USB Cable × 2 = 120

Total = 370
```

---

## 🛠️ Technologies Used

* Java
* Object-Oriented Programming (OOP)
* Java Collections Framework
* `ArrayList`
* `LinkedList`
* `HashMap`
* `LinkedHashMap`
* `HashSet`
* `Queue`
* `Iterator`
* `Comparable`
* `Comparator`
* `Enum`

---

## ▶️ How to Run

1. Clone the repository.
2. Open the project using a Java IDE such as IntelliJ IDEA, Eclipse, or NetBeans.
3. Compile the Java source files.
4. Run the main class.
5. Use the console menu to interact with the system.

---

## 📚 Concepts Demonstrated

This project demonstrates practical usage of:

```text
Collections
   ├── List
   ├── Set
   ├── Map
   └── Queue

Ordering
   ├── Comparable
   └── Comparator

Safe Modification
   └── Iterator

Fixed States
   └── Enum

OOP
   ├── Encapsulation
   ├── Classes & Objects
   └── Separation of Responsibilities
```

---

## 👨‍💻 Author

**Salah El-Din Yehia Suleiman**

Java Collections & OOP Assignment
