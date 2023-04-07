insert into user (avatar, email, is_banned, password, username) values ('nekaSlika', 'email@email.com', false, 'lozinka1', 'pera');
insert into user (avatar, email, is_banned, password, username) values ('nekaSlika', 'email@email.com', false, 'lozinka2', 'mika');
insert into user (avatar, email, is_banned, password, username) values ('nekaSlika', 'email@email.com', false, 'lozinka3', 'zika');

insert into flair (name) values ('imeTaga1');
insert into flair (name) values ('imeTaga2');
insert into flair (name) values ('imeTaga3');

insert into community (description, is_suspended, name, rules, suspended_reason, user_username) values ('nekiOpis', false, 'ime1', 'listaPravila', 'razlogSuspendovanja', 'pera');
insert into community (description, is_suspended, name, rules, suspended_reason, user_username) values ('nekiOpis', false, 'ime2', 'listaPravila', 'razlogSuspendovanja', 'mika');
insert into community (description, is_suspended, name, rules, suspended_reason, user_username) values ('nekiOpis', false, 'ime3', 'listaPravila', 'razlogSuspendovanja', 'zika');

insert into post (image_path, text, title, community_id, flair_id, user_username) values ('nekaSlika', 'tekst1', 'naslov1', 1, 1, 'pera');
insert into post (image_path, text, title, community_id, flair_id, user_username) values ('nekaSlika', 'tekst2', 'naslov2', 1, 1, 'mika');
insert into post (image_path, text, title, community_id, flair_id, user_username) values ('nekaSlika', 'tekst3', 'naslov3', 1, 1, 'zika');

insert into comment (is_deleted, text, post_id, user_username) values (false, 'tekstKomentara', 1, 'pera');
insert into comment (is_deleted, text, post_id, user_username) values (false, 'tekstKomentara', 1, 'mika');
insert into comment (is_deleted, text, post_id, user_username) values (false, 'tekstKomentara', 1, 'zika');

insert into reaction (type, comment_id, post_id, user_username) values ('tipReackije1', 2, 1, 'pera');
insert into reaction (type, comment_id, post_id, user_username) values ('tipReackije2', 2, 1, 'mika');
insert into reaction (type, comment_id, post_id, user_username) values ('tipReackije2', 2, 1, 'zika');

insert into report (accepted, report_reason, comment_id, post_id, user_username) values (false, 'razlogSuspendovanja1', 2, 1, 'pera');
insert into report (accepted, report_reason, comment_id, post_id, user_username) values (false, 'razlogSuspendovanja2', 2, 1, 'mika');
insert into report (accepted, report_reason, comment_id, post_id, user_username) values (false, 'razlogSuspendovanja3', 2, 1, 'zika');