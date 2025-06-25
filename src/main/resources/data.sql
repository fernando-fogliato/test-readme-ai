-- Insert sample users
INSERT INTO users (name, email, address, phone) VALUES 
('John Doe', 'john.doe@example.com', '123 Main St, New York, NY', '+1-555-0101'),
('Jane Smith', 'jane.smith@example.com', '456 Oak Ave, Los Angeles, CA', '+1-555-0102'),
('Mike Johnson', 'mike.johnson@example.com', '789 Pine Rd, Chicago, IL', '+1-555-0103'),
('Sarah Wilson', 'sarah.wilson@example.com', '321 Elm St, Houston, TX', '+1-555-0104'),
('David Brown', 'david.brown@example.com', '654 Maple Dr, Phoenix, AZ', '+1-555-0105');

-- Insert sample customers
INSERT INTO customers (company_name, contact_name, email, phone, address, city, country, credit_limit, active) VALUES 
('Tech Solutions Inc', 'Alice Johnson', 'alice@techsolutions.com', '+1-555-1001', '100 Technology Blvd', 'San Francisco', 'USA', 50000.00, true),
('Global Trading Corp', 'Bob Martinez', 'bob@globaltrading.com', '+1-555-1002', '200 Commerce St', 'New York', 'USA', 75000.00, true),
('European Imports Ltd', 'Clara Schmidt', 'clara@europeanim.com', '+44-20-7946-0958', '15 London Bridge St', 'London', 'UK', 25000.00, true),
('Pacific Ventures', 'David Kim', 'david@pacificven.com', '+81-3-1234-5678', '5-1-1 Shibuya', 'Tokyo', 'Japan', 100000.00, true),
('Mountain Gear Co', 'Emma Thompson', 'emma@mountaingear.com', '+1-555-1005', '300 Alpine Way', 'Denver', 'USA', 30000.00, false),
('Coastal Services', 'Frank Wilson', 'frank@coastalservices.com', '+1-555-1006', '400 Ocean Drive', 'Miami', 'USA', 45000.00, true),
('Nordic Solutions AS', 'Greta Larsson', 'greta@nordicsol.no', '+47-22-12-34-56', 'Karl Johans gate 1', 'Oslo', 'Norway', 60000.00, true),
('Sunrise Industries', 'Henry Chen', 'henry@sunriseind.com', '+86-10-1234-5678', '88 Wangfujing Street', 'Beijing', 'China', 80000.00, true); 