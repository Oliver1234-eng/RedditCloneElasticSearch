insert into user (email, password, username, role) values ('pera@email.com', '$2a$12$huwEhYljMX2aosziF435wu4kexpbxWRdRPaT8l0BM9uzs1x0TiMWq','pera', 'USER');
insert into user (email, password, username, role) values ('mika@email.com', '$2a$12$3eAULuVdBA6UNLjdwTjrY.lR1gWwCFyH5bdm.kmrtWirBG4U6k1bG', 'mika', 'USER');
insert into user (email, password, username, role) values ('zika@email.com', '$2a$12$TLitb3VzcD3T5SpmKVnlee9FtmZR7TX325Vis9H4T7Bf72IhCsLE2', 'zika', 'USER');

insert into flair (name) values ('nauka');
insert into flair (name) values ('istorija');
insert into flair (name) values ('politika');
insert into flair (name) values ('hrana');
insert into flair (name) values ('pice');
insert into flair (name) values ('muzika');
insert into flair (name) values ('filmovi');
insert into flair (name) values ('knjige');
insert into flair (name) values ('sport');
insert into flair (name) values ('geografija');
insert into flair (name) values ('zabava');

insert into community (description, name, rules, suspended_reason, user_username) values ('Zajednica za diskusije o kafi, receptima, i kulturi kafe. Dobrodošli!', 'kafa', '1. Poštujte druge članove.\n2. Nema spamovanja.\n3. Nemojte deliti štetne informacije.', 'Ova zajednica trenutno nije suspendovana', 'pera');
insert into community (description, name, rules, suspended_reason, user_username) values ('Ova zajednica je posvećena razmeni informacija i diskusiji o svemiru, astronomiji i astrofizici.', 'svemir', '1. Budite uvažavajući prema drugim članovima.\n2. Nema političkih diskusija.\n3. Nema spamovanja.', 'Ova zajednica trenutno nije suspendovana', 'mika');
insert into community (description, name, rules, suspended_reason, user_username) values ('Zajednica za diskusije o omiljenim video igrama. Delite svoje iskustvo, savete i novosti!', 'igre', '1. Poštujte druge članove.\n2. Nema reklamiranja.\n3. Nemojte deliti neprimerene sadržaje.', 'Ova zajednica trenutno nije suspendovana', 'zika');
insert into community (description, name, rules, suspended_reason, user_username) values ('Zajednica za sve ljubitelje trčanja i fitnesa', 'trcanje', '1. Nema vređanja drugih članova 2. Poštujte tuđa mišljenja 3. Delite svoja iskustva i savete sa drugima', 'Ova zajednica trenutno nije suspendovana', 'pera');
insert into community (description, name, rules, suspended_reason, user_username) values ('Zajednica posvećena filmovima svih žanrova i vremena', 'filmovi', '1. Nema spamovanja 2. Nema uvredljivih komentara 3. Delite svoje omiljene filmove i preporuke sa drugima', 'Ova zajednica trenutno nije suspendovana', 'mika');
insert into community (description, name, rules, suspended_reason, user_username) values ('Zajednica za putnike, avanturiste i one koji vole da istražuju svet', 'putovanja', '1. Nema vređanja drugih članova 2. Nema širenja dezinformacija 3. Delite svoja iskustva i savete sa drugima', 'Ova zajednica trenutno nije suspendovana', 'zika');

insert into post (text, title, community_name, flair_name, user_username) values ('Počeo sam trčati i evo šta sam naučio', 'Moja prva 5K trka', 'trcanje', 'sport', 'pera');
insert into post (text, title, community_name, flair_name, user_username) values ('Posetio sam Tokio prvi put i evo mog iskustva', 'Moje putovanje u Tokio', 'putovanja', 'geografija', 'mika');
insert into post (text, title, community_name, flair_name, user_username) values ('Najbolji filmovi za gledanje kada ste dosadni', 'Top 10 filmova', 'filmovi', 'zabava', 'zika');

insert into comment (text, post_id, user_username) values ('Super ti ide! I ja sam krenuo da trčim pre nekoliko meseci i stvarno mi prija.', 1, 'pera');
insert into comment (text, post_id, user_username) values ('Sjajno iskustvo! Ja sam bio u Tokiju pre par godina i ostao sam bez reči.', 1, 'mika');
insert into comment (text, post_id, user_username) values ('Odlična lista! Moram da pogledam neke od ovih filmova koje nisam gledao.', 1, 'zika');

insert into reaction (type, comment_id, post_id, user_username) values ('UPVOTE', 2, 1, 'pera');
insert into reaction (type, comment_id, post_id, user_username) values ('UPVOTE', 2, 1, 'mika');
insert into reaction (type, comment_id, post_id, user_username) values ('UPVOTE', 2, 1, 'zika');

insert into report (accepted, report_reason, comment_id, post_id, user_username) values (false, 'kršenje pravila zajednice', 2, 1, 'pera');
insert into report (accepted, report_reason, comment_id, post_id, user_username) values (false, 'neaktivnost', 2, 1, 'mika');
insert into report (accepted, report_reason, comment_id, post_id, user_username) values (false, 'spamming', 2, 1, 'zika');

insert into reaction_on_post(reaction_type, user_username, post_id) values ('UPVOTE', 'pera', 1);
insert into reaction_on_post(reaction_type, user_username, post_id) values ('UPVOTE', 'mika', 2);
insert into reaction_on_post(reaction_type, user_username, post_id) values ('UPVOTE', 'zika', 3);