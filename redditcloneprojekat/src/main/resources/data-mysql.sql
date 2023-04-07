
/*insert into user (avatar, email, is_banned, password, registration_date, username) values ('nekaSlika', 'email@email.com', false, 'lozinka1', '2022-01-01', 'A');
insert into user (avatar, email, is_banned, password, registration_date, username) values ('nekaSlika', 'email@email.com', false, 'lozinka2', '2022-01-02', 'B');
insert into user (avatar, email, is_banned, password, registration_date, username) values ('nekaSlika', 'email@email.com', false, 'lozinka3', '2022-01-03', 'C');

insert into flair (name) values ('imeTaga1');
insert into flair (name) values ('imeTaga2');
insert into flair (name) values ('imeTaga3');

insert into community (creation_date, description, is_suspended, name, rules, suspended_reason) values ('2022-01-01', 'nekiOpis', false, 'ime1', 'listaPravila', 'razlogSuspendovanja');
insert into community (creation_date, description, is_suspended, name, rules, suspended_reason) values ('2022-01-02', 'nekiOpis', false, 'ime2', 'listaPravila', 'razlogSuspendovanja');
insert into community (creation_date, description, is_suspended, name, rules, suspended_reason) values ('2022-01-02', 'nekiOpis', false, 'ime3', 'listaPravila', 'razlogSuspendovanja');

insert into moderating (community_id, user_id) values (1, 1);
insert into moderating (community_id, user_id) values (2, 2);
insert into moderating (community_id, user_id) values (3, 3);

insert into post (creation_date, image_path, text, title, community_id, flair_id, user_id) values ('2022-01-01', 'nekaSlika', 'tekst1', 'naslov1', 1, 1, 1);
insert into post (creation_date, image_path, text, title, community_id, flair_id, user_id) values ('2022-01-02', 'nekaSlika', 'tekst2', 'naslov2', 1, 1, 1);
insert into post (creation_date, image_path, text, title, community_id, flair_id, user_id) values ('2022-01-02', 'nekaSlika', 'tekst3', 'naslov3', 1, 1, 1);

insert into comment (is_deleted, text, timestamp, post_id, user_id) values (false, 'tekstKomentara', '2022-01-01', 1, 1);
insert into comment (is_deleted, text, timestamp, post_id, user_id) values (false, 'tekstKomentara', '2022-01-02', 1, 1);
insert into comment (is_deleted, text, timestamp, post_id, user_id) values (false, 'tekstKomentara', '2022-01-02', 1, 1);

insert into reaction (timestamp, type, comment_id, post_id, user_id) values ('2022-01-01', 'tipReackije1', 2, 1, 1);
insert into reaction (timestamp, type, comment_id, post_id, user_id) values ('2022-01-02', 'tipReackije2', 2, 1, 1);
insert into reaction (timestamp, type, comment_id, post_id, user_id) values ('2022-01-02', 'tipReackije2', 2, 1, 1);

insert into report (accepted, report_reason, timestamp, comment_id, post_id, user_id) values (false, 'razlogSuspendovanja1', '2022-01-01', 2, 1, 1);
insert into report (accepted, report_reason, timestamp, comment_id, post_id, user_id) values (false, 'razlogSuspendovanja2', '2022-01-02', 2, 1, 1);
insert into report (accepted, report_reason, timestamp, comment_id, post_id, user_id) values (false, 'razlogSuspendovanja3', '2022-01-03', 2, 1, 1); */