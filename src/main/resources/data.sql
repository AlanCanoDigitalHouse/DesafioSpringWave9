INSERT INTO user (user_id, create_at, user_name, user_type) VALUES (1,'2021-06-05','user1','vendedor');
INSERT INTO user (user_id, create_at, user_name, user_type) VALUES (2,'2021-06-07','user2','usuario');
INSERT INTO user (user_id, create_at, user_name, user_type) VALUES (3,'2021-06-08','user3','usuario');
INSERT INTO user (user_id, create_at, user_name, user_type) VALUES (4,'2021-06-09','user4','vendedor');
INSERT INTO user (user_id, create_at, user_name, user_type) VALUES (5,'2021-06-10','user5','usuario');
INSERT INTO user (user_id, create_at, user_name, user_type) VALUES (6,'2021-06-10','user6','usuario');
INSERT INTO user (user_id, create_at, user_name, user_type) VALUES (7,'2021-06-10','user7','vendedor');
INSERT INTO user (user_id, create_at, user_name, user_type) VALUES (8,'2021-06-10','user8','usuario');
INSERT INTO user (user_id, create_at, user_name, user_type) VALUES (9,'2021-06-10','user9','usuario');
INSERT INTO user (user_id, create_at, user_name, user_type) VALUES (10,'2021-06-10','user10','vendedor');

INSERT INTO follow (follow_id, user_id, user_id_to_follow, follow_status) VALUES (1,'1','2',true);
INSERT INTO follow (follow_id, user_id, user_id_to_follow, follow_status) VALUES (2,'1','3',true);
INSERT INTO follow (follow_id, user_id, user_id_to_follow, follow_status) VALUES (3,'1','4',true);
INSERT INTO follow (follow_id, user_id, user_id_to_follow, follow_status) VALUES (4,'1','5',true);
INSERT INTO follow (follow_id, user_id, user_id_to_follow, follow_status) VALUES (5,'1','6',true);
INSERT INTO follow (follow_id, user_id, user_id_to_follow, follow_status) VALUES (6,'2','1',true);
INSERT INTO follow (follow_id, user_id, user_id_to_follow, follow_status) VALUES (7,'2','3',true);
INSERT INTO follow (follow_id, user_id, user_id_to_follow, follow_status) VALUES (8,'2','4',true);
INSERT INTO follow (follow_id, user_id, user_id_to_follow, follow_status) VALUES (9,'2','5',true);
INSERT INTO follow (follow_id, user_id, user_id_to_follow, follow_status) VALUES (10,'3','1',true);
INSERT INTO follow (follow_id, user_id, user_id_to_follow, follow_status) VALUES (11,'3','2',true);
INSERT INTO follow (follow_id, user_id, user_id_to_follow, follow_status) VALUES (12,'3','4',true);
INSERT INTO follow (follow_id, user_id, user_id_to_follow, follow_status) VALUES (13,'3','5',true);
INSERT INTO follow (follow_id, user_id, user_id_to_follow, follow_status) VALUES (14,'3','6',true);
INSERT INTO follow (follow_id, user_id, user_id_to_follow, follow_status) VALUES (15,'3','7',true);

INSERT INTO category (category_id, create_at, category_name) VALUES (1,'2021-06-10','category1');
INSERT INTO category (category_id, create_at, category_name) VALUES (2,'2021-06-10','category2');
INSERT INTO category (category_id, create_at, category_name) VALUES (3,'2021-06-10','category3');
INSERT INTO category (category_id, create_at, category_name) VALUES (4,'2021-06-10','category4');
INSERT INTO category (category_id, create_at, category_name) VALUES (5,'2021-06-10','category5');

INSERT INTO product (product_id, create_at, product_name, product_type, product_brand, product_color, product_notes) VALUES (1,'2021-06-10','productName1','productType1','productBrand1','productColor1','productNotes1');
INSERT INTO product (product_id, create_at, product_name, product_type, product_brand, product_color, product_notes) VALUES (2,'2021-06-10','productName2','productType2','productBrand2','productColor2','productNotes2');
INSERT INTO product (product_id, create_at, product_name, product_type, product_brand, product_color, product_notes) VALUES (3,'2021-06-10','productName3','productType3','productBrand3','productColor3','productNotes3');
INSERT INTO product (product_id, create_at, product_name, product_type, product_brand, product_color, product_notes) VALUES (4,'2021-06-10','productName4','productType4','productBrand4','productColor4','productNotes4');
INSERT INTO product (product_id, create_at, product_name, product_type, product_brand, product_color, product_notes) VALUES (5,'2021-06-10','productName5','productType5','productBrand5','productColor5','productNotes5');
INSERT INTO product (product_id, create_at, product_name, product_type, product_brand, product_color, product_notes) VALUES (6,'2021-06-10','productName6','productType6','productBrand6','productColor6','productNotes6');
INSERT INTO product (product_id, create_at, product_name, product_type, product_brand, product_color, product_notes) VALUES (7,'2021-06-10','productName7','productType7','productBrand7','productColor7','productNotes7');
INSERT INTO product (product_id, create_at, product_name, product_type, product_brand, product_color, product_notes) VALUES (8,'2021-06-10','productName8','productType8','productBrand8','productColor8','productNotes8');


INSERT INTO post (id_post, user_id, create_at, product_id, category_id, price) VALUES (1,1,'2021-07-10',1,1,1500.50);
INSERT INTO post (id_post, user_id, create_at, product_id, category_id, price) VALUES (2,1,'2021-07-11',2,1,150.50);
INSERT INTO post (id_post, user_id, create_at, product_id, category_id, price) VALUES (3,2,'2021-07-12',3,1,10.50);
INSERT INTO post (id_post, user_id, create_at, product_id, category_id, price) VALUES (4,2,'2021-07-13',4,2,500.50);
INSERT INTO post (id_post, user_id, create_at, product_id, category_id, price) VALUES (5,3,'2021-07-14',5,2,100.50);
INSERT INTO post (id_post, user_id, create_at, product_id, category_id, price) VALUES (6,4,'2021-06-10',6,3,15.50);
INSERT INTO post (id_post, user_id, create_at, product_id, category_id, price) VALUES (7,7,'2021-06-10',7,3,500.00);
INSERT INTO post (id_post, user_id, create_at, product_id, category_id, price) VALUES (8,8,'2021-06-10',8,4,140.50);
INSERT INTO post (id_post, user_id, create_at, product_id, category_id, price) VALUES (9,9,'2021-06-10',9,4,1100.50);
INSERT INTO post (id_post, user_id, create_at, product_id, category_id, price) VALUES (10,10,'2021-06-10',10,5,300.50);













