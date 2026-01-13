INSERT INTO category (id, name, description) VALUES (1, 'Електроніка', 'Гаджети, комп''ютери та аксесуари');
INSERT INTO category (id, name, description) VALUES (2, 'Книги', 'Паперові книги, навчальна література');
INSERT INTO category (id, name, description) VALUES (3, 'Дім та Сад', 'Товари для будинку, меблі та декор');
INSERT INTO category (id, name, description) VALUES (4, 'Спорт', 'Спортивний інвентар та одяг');

-- Електроніка (Category ID: 1)
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (1, 'Смартфон Galaxy X', 'Потужний смартфон з чудовою камерою', 50, 25000.00, 1);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (2, 'Ноутбук Pro 15', 'Робоча станція для професіоналів', 20, 65000.00, 1);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (3, 'Бездротові навушники', 'Навушники з шумопоглинанням', 100, 3500.00, 1);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (4, 'Смарт-годинник', 'Годинник з відстеженням активності', 75, 5000.00, 1);

-- Книги (Category ID: 2)
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (5, 'Clean Code', 'Книга для програмістів Роберта Мартіна', 200, 900.00, 2);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (6, 'Володар Перснів', 'Фентезі роман Дж.Р.Р. Толкіна', 150, 450.00, 2);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (7, 'Історія України', 'Навчальний посібник', 300, 350.00, 2);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (8, 'Java. Ефективне програмування', 'Посібник Джошуа Блоха', 80, 1200.00, 2);

-- Дім та Сад (Category ID: 3)
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (9, 'Кавоварка Automatic', 'Автоматична кавомашина для еспресо', 30, 12000.00, 3);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (10, 'Набір рушників', 'Бавовняні рушники, 3 шт.', 120, 800.00, 3);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (11, 'Настільна лампа', 'LED лампа з регулюванням яскравості', 90, 1500.00, 3);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (12, 'Блендер SuperMix', 'Потужний блендер для смузі', 45, 2200.00, 3);

-- Спорт (Category ID: 4)
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (13, 'Килимок для йоги', 'Нековзний килимок 6мм', 200, 600.00, 4);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (14, 'Гантелі 5кг', 'Набір гантелей з покриттям', 60, 1100.00, 4);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (15, 'Футбольний м''яч', 'Професійний м''яч розмір 5', 100, 950.00, 4);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (16, 'Рюкзак туристичний', 'Рюкзак 60л для походів', 25, 3000.00, 4);