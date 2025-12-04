INSERT INTO empresa (nome, cnpj)
VALUES ('Nintendo', '12.345.678/0001-90'),
       ('PlayStation Studios', '98.765.432/0001-10'),
       ('Xbox Game Studios', '11.222.333/0001-44'),
       ('Rockstar Games', '55.666.777/0001-22'),
       ('CD Projekt Red', '88.999.000/0001-55');

INSERT INTO jogo (nome, categoria, data_lancamento, nota, empresa_id)
VALUES ('The Legend of Zelda: Tears of the Kingdom', 'Aventura', '2023-05-12', 9.7, 1),
       ('Marvel’s Spider-Man 2', 'Ação', '2023-10-20', 9.3, 2),
       ('Starfield', 'RPG', '2023-09-06', 8.1, 3),
       ('Grand Theft Auto V', 'Mundo Aberto', '2013-09-17', 9.8, 4),
       ('Cyberpunk 2077: Phantom Liberty', 'RPG Futurista', '2023-09-26', 9.0, 5),
       ('Halo Infinite', 'FPS', '2021-12-08', 8.4, 3),
       ('Red Dead Redemption 2', 'Aventura/Faroeste', '2018-10-26', 9.9, 4),
       ('Pokémon Scarlet & Violet', 'RPG', '2022-11-18', 7.5, 1),
       ('The Witcher 3: Wild Hunt Next Gen', 'RPG', '2022-12-14', 9.6, 5),
       ('God of War Ragnarök', 'Ação/Aventura', '2022-11-09', 9.7, 2);
