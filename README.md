######### LAUNDRYMAN - 1 ##########

1. Intro
2. Class Diagram 
3. API Doc
4. API Flow




-----------------------------------------------------------------------------------------------------------------------------------------------------

INTRO:
======

The laundry management backend API currently provides core functionalities for laundry operations. It features RESTful endpoints, employs PostgreSQL for data storage, and follows domain-driven design principles, catering specifically to laundry management needs.

Current State:
- Core Functionalities: Includes Customer, Order, Inventory Item, Employee, ServiceType, Schedule, and Payment management.
- RESTful Endpoints: Designed for CRUD operations.
- Database Integration: Utilizes PostgreSQL for data persistence.
- Domain-Driven Design: Aligns software design with the business domain.

Potential Enhancements for Industry-Grade Production:
- Security Implementation: Introduce JWT-based authentication and authorization.
- Performance Optimization: Implement caching and query optimization.
- Error Handling: Develop robust error handling and validation mechanisms.
- Scalability: Prepare for scaling with microservices architecture or load balancing.
- Continuous Integration/Continuous Deployment (CI/CD): Set up pipelines for automated testing and deployment.
- Monitoring and Logging: Integrate tools for real-time monitoring and logging.
- API Documentation: Use tools like Swagger for comprehensive and interactive documentation.
- Compliance and Security Audits: Ensure compliance with data protection regulations and conduct regular security audits.

-----------------------------------------------------------------------------------------------------------------------------------------------------

Class Diagram:
==============

**Entity Classes (Regular Classes):**

- Customer
- Order
- OrderItem
- ServiceType
- Payment
- InventoryItem
- Employee
- Schedule

**DTO Classes (Regular Classes):**

- CustomerDTO
- OrderDTO
- OrderItemDTO
- ServiceTypeDTO
- PaymentDTO
- InventoryItemDTO
- EmployeeDTO
- ScheduleDTO

**Repository Interfaces:**

- CustomerRepository (extends JpaRepository<Customer, Long>)
- OrderRepository (extends JpaRepository<Order, Long>)
- OrderItemRepository (extends JpaRepository<OrderItem, Long>)
- ServiceTypeRepository (extends JpaRepository<ServiceType, Long>)
- PaymentRepository (extends JpaRepository<Payment, Long>)
- InventoryItemRepository (extends JpaRepository<InventoryItem, Long>)
- EmployeeRepository (extends JpaRepository<Employee, Long>)
- ScheduleRepository (extends JpaRepository<Schedule, Long>)

**Service Classes (Regular Classes):**

- CustomerService
- OrderService
- InventoryService
- PaymentService
- EmployeeService
- ScheduleService

**Controller Classes (Regular Classes):**

- CustomerController
- OrderController
- InventoryController
- PaymentController
- EmployeeController
- ScheduleController

**Utility Classes (Regular Classes):**

- EmailService
- PaymentGatewayIntegration

**Security Classes (Regular Classes):**

- User
- Role
- SecurityConfiguration

**Key Points:**

- Entity Classes: These are standard JPA entity classes.
- DTO Classes: Plain Java classes used for data transfer.
- Repository Interfaces: These interfaces extend Spring Data JPA's JpaRepository interface, providing CRUD operations for your entities.
- Service Classes: Contain business logic and interact with repository interfaces.
- Controller Classes: Handle HTTP requests and delegate business operations to service classes.
- Utility Classes: Provide additional functionalities like email sending or payment processing.
- Security Classes: Used for configuring and managing authentication and authorization.


-----------------------------------------------------------------------------------------------------------------------------------------------------

API Documentation
=================

1. Customer Endpoints
---------------------
- POST /api/customers:
  - Creates a new customer.
  - Request Body: Customer details (name, address, phone number, email).
  - Response: Details of the created customer.

- GET /api/customers/{id}:
  - Retrieves a specific customer by their ID.
  - Response: Customer details.

- GET /api/customers:
  - Retrieves all customers.
  - Response: List of all customers.

- PUT /api/customers/{id}:
  - Updates an existing customer.
  - Request Body: Updated customer details.
  - Response: Details of the updated customer.

- DELETE /api/customers/{id}:
  - Deletes a specific customer.
  - Response: Confirmation of deletion.

2. Order Endpoints
------------------
- POST /api/orders:
  - Creates a new order.
  - Request Body: Order details (items, payment info).
  - Response: Details of the created order.

- GET /api/orders/{id}:
  - Retrieves a specific order by ID.
  - Response: Order details.

- GET /api/orders:
  - Retrieves all orders.
  - Response: List of all orders.

- PUT /api/orders/{id}:
  - Updates an existing order.
  - Request Body: Updated order details.
  - Response: Details of the updated order.

- DELETE /api/orders/{id}:
  - Deletes a specific order.
  - Response: Confirmation of deletion.

3. Inventory Item Endpoints
----------------------------
- POST /api/inventory:
  - Creates a new inventory item.
  - Request Body: Inventory item details.
  - Response: Details of the created item.

- GET /api/inventory/{id}:
  - Retrieves a specific inventory item by ID.
  - Response: Inventory item details.

- GET /api/inventory:
  - Retrieves all inventory items.
  - Response: List of all inventory items.

- PUT /api/inventory/{id}:
  - Updates an existing inventory item.
  - Request Body: Updated item details.
  - Response: Details of the updated item.

- DELETE /api/inventory/{id}:
  - Deletes a specific inventory item.
  - Response: Confirmation of deletion.

4. Employee Endpoints
----------------------
- POST /api/employees:
  - Creates a new employee.
  - Request Body: Employee details.
  - Response: Details of the created employee.

- GET /api/employees/{id}:
  - Retrieves a specific employee by ID.
  - Response: Employee details.

- GET /api/employees:
  - Retrieves all employees.
  - Response: List of all employees.

- PUT /api/employees/{id}:
  - Updates an existing employee.
  - Request Body: Updated employee details.
  - Response: Details of the updated employee.

- DELETE /api/employees/{id}:
  - Deletes a specific employee.
  - Response: Confirmation of deletion.

5. ServiceType Endpoints
-------------------------
- POST /api/service-types:
  - Function: Creates a new service type.
  - Request Body: Service type information (e.g., name, rate).
  - Response: Details of the created service type.

- GET /api/service-types/{id}:
  - Function: Retrieves a specific service type by ID.
  - Response: Service type details.

- GET /api/service-types:
  - Function: Retrieves all service types.
  - Response: List of all service types.

- PUT /api/service-types/{id}:
  - Function: Updates an existing service type.
  - Request Body: Updated service type data.
  - Response: Details of the updated service type.

- DELETE /api/service-types/{id}:
  - Function: Deletes a specific service type.
  - Response: Confirmation of deletion.

6. Schedule Endpoints
----------------------
- POST /api/schedules:
  - Function: Creates a new schedule (e.g., for pickups or deliveries).
  - Request Body: Schedule details.
  - Response: Details of the created schedule.

- GET /api/schedules/{id}:
  - Function: Retrieves a specific schedule by ID.
  - Response: Schedule details.

- GET /api/schedules:
  - Function: Retrieves all schedules.
  - Response: List of all schedules.

- PUT /api/schedules/{id}:
  - Function: Updates an existing schedule.
  - Request Body: Updated schedule data.
  - Response: Details of the updated schedule.

- DELETE /api/schedules/{id}:
  - Function: Deletes a specific schedule.
  - Response: Confirmation of deletion.


7. Payment Endpoints
--------------------

- POST /api/payments:
  - Function: Processes a new payment.
  - Request Body: Payment details (amount, method, etc.).
  - Response: Details of the processed payment.

- GET /api/payments/{id}:
  - Function: Retrieves a specific payment by ID.
  - Response: Payment details.

- GET /api/payments:
  - Function: Retrieves all payments.
  - Response: List of all payments.

- PUT /api/payments/{id}:
  - Function: Updates an existing payment record.
  - Request Body: Updated payment details.
  - Response: Details of the updated payment.

- DELETE /api/payments/{id}:
  - Function: Deletes a specific payment record.
  - Response: Confirmation of deletion.

-----------------------------------------------------------------------------------------------------------------------------------------------------

API Flow
=========

### Customer Management Flow

1. **Creating a New Customer**
   - Flow: `User Interface -> POST /api/customers -> Customer Service -> Customer Repository -> Database`
   - Description: User submits customer details on the UI, which sends a POST request. The Customer Service processes and saves it via the Customer Repository to the database.

2. **Updating Customer Details**
   - Flow: `User Interface -> PUT /api/customers/{id} -> Customer Service -> Customer Repository -> Database`
   - Description: User modifies customer details on the UI, triggering a PUT request. The Customer Service updates the existing customer in the database.

3. **Retrieving Customer Information**
   - Flow: `User Interface -> GET /api/customers/{id} (or GET /api/customers) -> Customer Service -> Customer Repository -> Database -> Return Data to User`
   - Description: User requests to view customer information. The service retrieves data from the database and displays it on the UI.

4. **Deleting a Customer**
   - Flow: `User Interface -> DELETE /api/customers/{id} -> Customer Service -> Customer Repository -> Database`
   - Description: User initiates a customer deletion from the UI. The service removes the customer record from the database.



### Order Management Flow

1. **Placing an Order**
   - Flow: `User Interface -> POST /api/orders -> Order Service -> Order Repository -> Database`
   - Description: A customer or employee places an order through the UI. The order details are sent to the server, where the Order Service processes and stores it in the database.

2. **Updating an Order**
   - Flow: `User Interface -> PUT /api/orders/{id} -> Order Service -> Order Repository -> Database`
   - Description: Modification of an existing order is handled by the Order Service and updated in the database.

3. **Viewing Orders**
   - Flow: `User Interface -> GET /api/orders (or /api/orders/{id}) -> Order Service -> Order Repository -> Database -> Return Data`
   - Description: Retrieval of order details, either all orders or a specific one, displayed on the UI.

4. **Canceling/Deleting an Order**
   - Flow: `User Interface -> DELETE /api/orders/{id} -> Order Service -> Order Repository -> Database`
   - Description: Order cancellation results in deletion from the database.



### Inventory Management Flow

1. **Adding Inventory Items**
   - Flow: `User Interface -> POST /api/inventory -> Inventory Service -> Inventory Repository -> Database`
   - Description: Addition of new inventory items is processed and stored in the database.

2. **Updating Inventory Items**
   - Flow: `User Interface -> PUT /api/inventory/{id} -> Inventory Service -> Inventory Repository -> Database`
   - Description: Changes to inventory items are updated in the database.

3. **Checking Inventory Levels**
   - Flow: `User Interface -> GET /api/inventory -> Inventory Service -> Inventory Repository -> Database -> Return Data`
   - Description: Inventory levels and details are retrieved and displayed.



### Employee Management Flow

1. **Adding New Employees**
   - Flow: `User Interface -> POST /api/employees -> Employee Service -> Employee Repository -> Database`
   - Description: New employee data is processed and stored in the database.

2. **Updating Employee Details**
   - Flow: `User Interface -> PUT /api/employees/{id} -> Employee Service -> Employee Repository -> Database`
   - Description: Employee records are updated as needed.

3. **Viewing Employee Information**
   - Flow: `User Interface -> GET /api/employees/{id} (or /api/employees) -> Employee Service -> Employee Repository -> Database -> Return Data`
   - Description: Employee information is retrieved for viewing or management purposes.



### ServiceType Management Flow

1. **Creating Service Types**
   - Flow: `User Interface -> POST /api/service-types -> ServiceType Service -> ServiceType Repository -> Database`
   - Description: Defines new service types (like washing, dry cleaning) in the system.

2. **Updating Service Types**
   - Flow: `User Interface -> PUT /api/service-types/{id} -> ServiceType Service -> ServiceType Repository -> Database`
   - Description: Modifies existing service type details.

3. **Viewing Service Types**
   - Flow: `User Interface -> GET /api/service-types -> ServiceType Service -> ServiceType Repository -> Database -> Return Data`
   - Description: Retrieves all service type information for display.



### Schedule Management Flow

1. **Creating Schedules for Orders**
   - Flow: `User Interface -> POST /api/schedules -> Schedule Service -> Schedule Repository -> Database`
   - Description: Schedules pickups or deliveries for orders.

2. **Updating Schedules**
   - Flow: `User Interface -> PUT /api/schedules/{id} -> Schedule Service -> Schedule Repository -> Database`
   - Description: Changes to existing schedules are updated.

3. **Viewing Schedules**
   - Flow: `User Interface -> GET /api/schedules -> Schedule Service -> Schedule Repository -> Database -> Return Data`
   - Description: Accesses schedule information, useful for both customers and staff.



### Payment Processing Flow

1. **Processing Payments**
   - Flow: `User Interface -> POST /api/payments -> Payment Service -> Payment Repository -> Database`
   - Description: Handles payment transactions for orders.

2. **Updating Payment Information**
   - Flow: `User Interface -> PUT /api/payments/{id} -> Payment Service -> Payment Repository -> Database`
   - Description: Updates details of existing payment records.

3. **Viewing Payment History**
   - Flow: `User Interface -> GET /api/payments -> Payment Service -> Payment Repository -> Database -> Return Data`
   - Description: Retrieves payment records for orders, important for financial tracking.

