INSERT INTO sub_categories (name, category_id, owner)
VALUES

-- Food & Beverage
('Groceries', (SELECT id FROM categories WHERE name = 'Food & Beverage'),'SYSTEM'),
('Restaurants', (SELECT id FROM categories WHERE name = 'Food & Beverage'),'SYSTEM'),
('Coffee', (SELECT id FROM categories WHERE name = 'Food & Beverage'),'SYSTEM'),
('Fast Food', (SELECT id FROM categories WHERE name = 'Food & Beverage'),'SYSTEM'),
('Delivery', (SELECT id FROM categories WHERE name = 'Food & Beverage'),'SYSTEM'),

-- Bills & Utilities
('Rentals', (SELECT id FROM categories WHERE name = 'Bills & Utilities'),'SYSTEM'),
('Water Bill', (SELECT id FROM categories WHERE name = 'Bills & Utilities'),'SYSTEM'),
('Phone Bill', (SELECT id FROM categories WHERE name = 'Bills & Utilities'),'SYSTEM'),
('Electricity Bill', (SELECT id FROM categories WHERE name = 'Bills & Utilities'),'SYSTEM'),
('Gas Bill', (SELECT id FROM categories WHERE name = 'Bills & Utilities'),'SYSTEM'),
('Television Bill', (SELECT id FROM categories WHERE name = 'Bills & Utilities'),'SYSTEM'),
('Internet Bill', (SELECT id FROM categories WHERE name = 'Bills & Utilities'),'SYSTEM'),
('Other Utility Bills', (SELECT id FROM categories WHERE name = 'Bills & Utilities'),'SYSTEM'),

-- Shopping
('Personal Items', (SELECT id FROM categories WHERE name = 'Shopping'),'SYSTEM'),
('Houseware', (SELECT id FROM categories WHERE name = 'Shopping'),'SYSTEM'),
('Makeup', (SELECT id FROM categories WHERE name = 'Shopping'),'SYSTEM'),

-- Family
('Home Maintenance', (SELECT id FROM categories WHERE name = 'Family'),'SYSTEM'),
('Home Services', (SELECT id FROM categories WHERE name = 'Family'),'SYSTEM'),
('Pets', (SELECT id FROM categories WHERE name = 'Family'),'SYSTEM'),

-- Transportation
('Vehicle Maintenance', (SELECT id FROM categories WHERE name = 'Transportation'),'SYSTEM'),
('Fuel', (SELECT id FROM categories WHERE name = 'Transportation'),'SYSTEM'),
('Taxi', (SELECT id FROM categories WHERE name = 'Transportation'),'SYSTEM'),
('Parking', (SELECT id FROM categories WHERE name = 'Transportation'),'SYSTEM'),
('Public Transport', (SELECT id FROM categories WHERE name = 'Transportation'),'SYSTEM'),

-- Health & Fitness
('Medical Check-up', (SELECT id FROM categories WHERE name = 'Health & Fitness'),'SYSTEM'),
('Pharmacy', (SELECT id FROM categories WHERE name = 'Health & Fitness'),'SYSTEM'),
('Doctor', (SELECT id FROM categories WHERE name = 'Health & Fitness'),'SYSTEM'),
('Dentist', (SELECT id FROM categories WHERE name = 'Health & Fitness'),'SYSTEM'),
('Fitness', (SELECT id FROM categories WHERE name = 'Health & Fitness'),'SYSTEM'),

-- Education
('Courses', (SELECT id FROM categories WHERE name = 'Education'),'SYSTEM'),
('Books', (SELECT id FROM categories WHERE name = 'Education'),'SYSTEM'),
('Certificates', (SELECT id FROM categories WHERE name = 'Education'),'SYSTEM'),

-- Entertainment
('Streaming Service', (SELECT id FROM categories WHERE name = 'Entertainment'),'SYSTEM'),
('Fun Money', (SELECT id FROM categories WHERE name = 'Entertainment'),'SYSTEM'),

-- Salary
('Main Job', (SELECT id FROM categories WHERE name = 'Salary'),'SYSTEM'),
('Bonus', (SELECT id FROM categories WHERE name = 'Salary'),'SYSTEM'),

-- Other Income
('Freelance', (SELECT id FROM categories WHERE name = 'Other Income'),'SYSTEM'),
('Gift', (SELECT id FROM categories WHERE name = 'Other Income'),'SYSTEM'),
('Cashback', (SELECT id FROM categories WHERE name = 'Other Income'),'SYSTEM');