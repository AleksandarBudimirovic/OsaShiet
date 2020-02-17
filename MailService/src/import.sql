INSERT INTO user (username, password, first_name,last_name) VALUES ('Tina', 't', 'Valentina', 'Mackovic');
INSERT INTO user (username, password, first_name,last_name) VALUES ('Marko', 'm', 'Marko', 'Markovic');

INSERT INTO account (user_id,smtp_address,smtp_port,in_server_type,in_server_address,in_server_port,username,password,display_name) VALUES (1,'testpmsu@gmail.com',567,2,'testpmsu@gmail.com',578,'a', 'TestPMSU1','user1');
INSERT INTO account (user_id,smtp_address,smtp_port,in_server_type,in_server_address,in_server_port,username,password,display_name) VALUES (1,'dopost123@gmail.com',568,2,'dopost123@gmail.com',538,'c', 'c','user1');

INSERT INTO message (draft,account_message_id,sender,recipient,date_time,subject,content,unread) VALUES (false,1,'Valentina','testpmsu@gmail.com','2018-12-12','Provera', 'Neki sadrzaj',true);
INSERT INTO message (draft,account_message_id,sender,recipient,date_time,subject,content,unread) VALUES (false,1,'Petar','testpmsu@gmail.com','2019-04-12','Poruka broj 2', 'Neki sadrzaj za prikazivanje',false);
INSERT INTO message (draft,account_message_id,sender,recipient,date_time,subject,content,unread) VALUES (false,2,'Ana','smtpAdresa2','2018-02-12','Provera', 'Neki sadrzaj',true);
INSERT INTO message (draft,account_message_id,sender,recipient,date_time,subject,content,unread) VALUES (false,2,'Misko','smtpAdresa2','2019-05-12','Poruka broj 2', 'Neki sadrzaj za prikazivanje',false);

INSERT INTO contact (first_name,last_name,display_name,email,note,user) VALUES ('Ana','Anic','Ana:)','ana@gmail.com', 'note',1);
INSERT INTO contact (first_name,last_name,display_name,email,note,user) VALUES ('Petar','Petoric','Pera:)','pera@gmail.com', 'notePera',2);

INSERT INTO folder (name,account_id) VALUES ('Drafts',1);
INSERT INTO folder (name,account_id) VALUES ('Inbox',1);
INSERT INTO folder (name,account_id) VALUES ('Outbox',1);
INSERT INTO folder (name,account_id) VALUES ('Drafts',2);
INSERT INTO folder (name,account_id) VALUES ('Inbox',2);
INSERT INTO folder (name,account_id) VALUES ('Outbox',2);

