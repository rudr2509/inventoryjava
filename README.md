# InventoryPro – Inventory Purchase Request Management System

A Java 17 / Spring Boot web application with role-based access, inventory management, supplier records, purchase requests, approval workflow, low-stock alerts and PDF reporting.

## Demo accounts
- Administrator: `admin` / `Admin@123`
- Employee: `employee` / `Employee@123`

Change these credentials before using this application beyond a demo.

## Run locally
1. Install JDK 17, Maven 3.9+ and PostgreSQL.
2. Create a PostgreSQL database named `inventorypro`.
3. In `src/main/resources/application.properties`, set the local database URL, username and password (or set `DATABASE_URL`, `DB_USERNAME`, `DB_PASSWORD`).
4. Run `mvn spring-boot:run`.
5. Open http://localhost:8080.

The application creates tables automatically and inserts demo users, sample products and a supplier on first start.

## Deploy to Render
1. Push this project to a GitHub repository.
2. Sign in to Render and choose **New + → Blueprint**.
3. Connect the GitHub repository containing `render.yaml`.
4. Review and apply the blueprint. Render provisions the PostgreSQL database and Docker web service.
5. Wait for the first deployment to finish, then open the generated `onrender.com` URL.
6. Sign in with a demo account and immediately change demo passwords before real use.

If using a manually created Render service instead: choose Docker, set the root directory to the project root, and configure `DATABASE_URL`, `DB_USERNAME`, and `DB_PASSWORD` from your Render PostgreSQL instance.

## Main routes
- `/` Dashboard
- `/products` Products (admin)
- `/suppliers` Suppliers (admin)
- `/requests` Purchase requests
- `/reports/inventory.pdf` Inventory PDF report

## Notes
- `spring.jpa.hibernate.ddl-auto=update` is convenient for a student/demo deployment. Use schema migrations and managed secrets for production.
- Approval adds the approved requested quantity to the product stock.
- Demo accounts are seeded automatically only if their usernames do not already exist.
