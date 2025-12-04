INSERT INTO autor (nome)
VALUES ('Alan Moore'),
       ('Neil Gaiman'),
       ('Frank Miller'),
       ('Marjane Satrapi'),
       ('Art Spiegelman'),
       ('Brian K. Vaughan'),
       ('Fiona Staples'),
       ('Osamu Tezuka'),
       ('Hergé'),
       ('Mauricio de Sousa');

INSERT INTO quadrinho (titulo, isbn, nota, data_lancamento, autor_id)
VALUES ('Watchmen', '9780857861936', 9.8, '1986-09-01', 1),
       ('V de Vingança', '9781401207922', 9.5, '1982-03-01', 1),

       ('Sandman: Prelúdios e Noturnos', '9781401238636', 9.6, '1989-01-01', 2),
       ('Sandman: A Casa de Bonecas', '9781401238650', 9.4, '1990-01-01', 2),

       ('O Cavaleiro das Trevas', '9781401263119', 9.7, '1986-02-01', 3),
       ('Sin City: A Cidade do Pecado', '9781593072957', 9.2, '1991-04-01', 3),

       ('Persépolis', '9780375714573', 9.3, '2000-10-01', 4),

       ('Maus: A História de um Sobrevivente', '9780394747231', 9.9, '1980-01-01', 5),

       ('Saga Vol. 1', '9781607066019', 9.5, '2012-03-01', 6),
       ('Saga Vol. 2', '9781607066927', 9.4, '2013-07-01', 6),

       ('Saga Vol. 3', '9781607069317', 9.4, '2014-03-01', 7),
       ('Saga Vol. 4', '9781632150776', 9.3, '2014-12-01', 7),

       ('Astro Boy Vol. 1', '9781569712842', 9.1, '1952-04-01', 8),
       ('Fênix: Dawn', '9781595823564', 9.4, '1967-01-01', 8),

       ('As Aventuras de Tintim: O Loto Azul', '9782874240004', 9.0, '1936-01-01', 9),
       ('As Aventuras de Tintim: O Segredo do Licorne', '9782203001024', 9.2, '1943-01-01', 9),
       ('As Aventuras de Tintim: O Tesouro de Rackham', '9782203001031', 9.1, '1944-01-01', 9),

       ('Turma da Mônica: Laços', '9788582465370', 9.3, '2013-06-01', 10),
       ('Turma da Mônica: Lições', '9788582467015', 9.2, '2015-12-01', 10),

       ('300 de Esparta', '9781569713641', 9.0, '1998-05-01', 3),
       ('From Hell', '9780954369588', 9.6, '1999-01-01', 1),

       ('Good Omens Graphic Novel', '9780060853983', 8.9, '2006-01-01', 2),

       ('Black Hole', '9780375714610', 9.2, '1995-01-01', 5),

       ('Y: O Último Homem Vol. 1', '9781563899808', 9.3, '2002-01-01', 6);
