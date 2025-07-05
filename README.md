# E-Commerce System

A Java-based e-commerce system that demonstrates order processing, inventory management, shipping calculations, and customer balance handling with various product types and validation scenarios.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Product Types](#product-types)
- [Sample Output](#sample-output)
- [Design Patterns](#design-patterns)

## Overview

This e-commerce system simulates a complete shopping and checkout experience with the following key functionalities:

- Shopping cart management
- Product inventory tracking
- Order processing with validation
- Shipping calculations for physical products
- Customer balance management
- Product expiration date validation

## Features

- **Product Management**: Support for different product types (TV, Cheese, Mobile Scratch Cards)
- **Shopping Cart**: Add/remove products with quantity validation
- **Inventory Control**: Stock availability checking
- **Expiration Handling**: Automatic validation for perishable products
- **Shipping Service**: Weight-based shipping for physical products
- **Payment Processing**: Customer balance validation and deduction
- **Error Handling**: Comprehensive validation for various edge cases

## Project Structure

```
ecommerce-system/
├── src/main/java/
│   ├── Main.java                    # Application entry point with test scenarios
│   ├── cart/
│   │   ├── Cart.java                # Shopping cart management
│   │   └── CartItem.java            # Individual cart item representation
│   ├── core/
│   │   ├── Customer.java            # Customer with balance management
│   │   └── Product.java             # Base product class
│   ├── order/
│   │   └── OrderProcessor.java      # Order processing and validation logic
│   ├── product/
│   │   ├── Cheese.java              # Perishable, shippable product
│   │   ├── MobileScratchCard.java   # Digital product
│   │   └── TV.java                  # Physical product
│   ├── shipping/
│   │   ├── ProductShipmentItem.java # Shipping item wrapper
│   │   ├── Shippable.java           # Interface for shippable products
│   │   ├── ShippableItem.java       # Base shipping item
│   │   └── ShippingService.java     # Shipping calculation and notices
│   └── util/
│       └── Expirable.java           # Interface for products with expiration
```

## Product Types

### 1. **TV** (Physical Product)

- Implements `Shippable` interface
- Has weight for shipping calculations
- Non-perishable

### 2. **Cheese** (Perishable Product)

- Implements both `Shippable` and `Expirable` interfaces
- Has weight and expiration date
- Requires expiration date validation

### 3. **Mobile Scratch Card** (Digital Product)

- Digital product with no shipping requirements
- Instant delivery

## Sample Output

The application demonstrates various scenarios with the following expected outputs:

### Normal Checkout

```
=== Normal Checkout ===
** Shipment notice **
1x TV 5000.0g
2x Cheese 400.0g
Total package weight 5.4kg
** Checkout receipt **
2x Cheese 100.0
1x TV 500.0
1x Scratch Card 10.0
----------------------
Subtotal 710.0
Shipping 30.0
Amount 740.0
Customer current balance: 260.0
```

### Empty Cart

```
=== Empty Cart ===
Error: Cart is empty
```

### Insufficient Balance

```
=== Insufficient Balance ===
Error: Insufficient balance
```

### Out of Stock

```
=== Out of Stock ===
Requested quantity exceeds available stock
```

### Expired Product

```
=== Expired Product ===
Error: Product Expired Cheese is expired
```

## Design Patterns

### 1. **Strategy Pattern**

- Different product types implement different interfaces (`Shippable`, `Expirable`)
- Allows flexible handling of product-specific behaviors

### 2. **Interface Segregation**

- `Shippable` interface for products that require shipping
- `Expirable` interface for products with expiration dates
- Products implement only relevant interfaces

### 3. **Single Responsibility Principle**

- `Cart`: Manages shopping cart items
- `OrderProcessor`: Handles order validation and processing
- `ShippingService`: Calculates shipping costs and notices
- `Customer`: Manages customer balance

## Key Validation Rules

1. **Stock Validation**: Requested quantity must not exceed available stock
2. **Expiration Validation**: Perishable products must not be expired
3. **Balance Validation**: Customer must have sufficient balance for total amount
4. **Cart Validation**: Cart cannot be empty during checkout
5. **Shipping Calculation**: Only physical products contribute to shipping weight
