DROP DATABASE IF EXISTS urna_eletronica;

CREATE DATABASE IF NOT EXISTS urna_eletronica;

USE urna_eletronica;

CREATE TABLE candidato(
id_candidato VARCHAR(10) PRIMARY KEY,
nome_candidato VARCHAR(40),
nome_vice VARCHAR(40),
partido VARCHAR(40),
cargo VARCHAR(20),
img_index VARCHAR(20)
);

CREATE TABLE eleitor(
id_eleitor VARCHAR(12) PRIMARY KEY
);

CREATE TABLE votacao(
id_voto INT AUTO_INCREMENT PRIMARY KEY,
id_candidato VARCHAR(10),
id_eleitor VARCHAR(12),
CONSTRAINT voto_c FOREIGN KEY (id_candidato) REFERENCES candidato (id_candidato),
CONSTRAINT voto_e FOREIGN KEY (id_eleitor) REFERENCES eleitor (id_eleitor)
);

-- dados para teste --

INSERT INTO candidato(
id_candidato,
nome_candidato,
nome_vice,
partido,
cargo,
img_index
)
VALUES(
13,
'Octópode',
'Vampirão',
'TP',
'PRESIDENTE',
'13'
);

INSERT INTO candidato(
id_candidato,
nome_candidato,
nome_vice,
partido,
cargo,
img_index
)
VALUES(
45,
'Tucano',
'Pessoa Tucana',
'DF',
'PRESIDENTE',
'45'
);

INSERT INTO candidato(
id_candidato,
nome_candidato,
nome_vice,
partido,
cargo,
img_index
)
VALUES(
51,
'General brasileiro',
'Militar brasileiro',
'TT',
'PRESIDENTE',
'51'
);

INSERT INTO candidato(
id_candidato,
nome_candidato,
nome_vice,
partido,
cargo,
img_index
)
VALUES(
99,
'Presidente aleatório',
'Vice aleatório',
'XX',
'PRESIDENTE',
'99'
);

-- governador

INSERT INTO candidato(
id_candidato,
nome_candidato,
partido,
cargo,
img_index
)
VALUES(
'13a',
'Governador aleatório',
'TP',
'GOVERNADOR(A)',
'99'
);

INSERT INTO candidato(
id_candidato,
nome_candidato,
partido,
cargo,
img_index
)
VALUES(
'45a',
'Governador aleatório',
'DF',
'GOVERNADOR(A)',
'99'
);

INSERT INTO candidato(
id_candidato,
nome_candidato,
partido,
cargo,
img_index
)
VALUES(
'51a',
'Governador aleatório',
'TT',
'GOVERNADOR(A)',
'99'
);

INSERT INTO candidato(
id_candidato,
nome_candidato,
partido,
cargo,
img_index
)
VALUES(
'99a',
'Governador aleatório',
'XX',
'GOVERNADOR(A)',
'99'
);

-- senador

INSERT INTO candidato(
id_candidato,
nome_candidato,
partido,
cargo,
img_index
)
VALUES(
131,
'Senador aleatório',
'TP',
'SENADOR(A)',
'99'
);

INSERT INTO candidato(
id_candidato,
nome_candidato,
partido,
cargo,
img_index
)
VALUES(
451,
'Senador aleatório',
'DF',
'SENADOR(A)',
'99'
);

INSERT INTO candidato(
id_candidato,
nome_candidato,
partido,
cargo,
img_index
)
VALUES(
511,
'Senador aleatório',
'TT',
'SENADOR(A)',
'99'
);

INSERT INTO candidato(
id_candidato,
nome_candidato,
partido,
cargo,
img_index
)
VALUES(
999,
'Senador aleatório',
'XX',
'SENADOR(A)',
'99'
);

-- deputado federal

INSERT INTO candidato(
id_candidato,
nome_candidato,
partido,
cargo,
img_index
)
VALUES(
1311,
'Deputado aleatório',
'TP',
'DEPUTADO(A) FEDERAL',
'99'
);

INSERT INTO candidato(
id_candidato,
nome_candidato,
partido,
cargo,
img_index
)
VALUES(
4511,
'Deputado aleatório',
'DF',
'DEPUTADO(A) FEDERAL',
'99'
);

INSERT INTO candidato(
id_candidato,
nome_candidato,
partido,
cargo,
img_index
)
VALUES(
5111,
'Deputado aleatório',
'TT',
'DEPUTADO(A) FEDERAL',
'99'
);

INSERT INTO candidato(
id_candidato,
nome_candidato,
partido,
cargo,
img_index
)
VALUES(
9999,
'Deputado aleatório',
'XX',
'DEPUTADO(A) FEDERAL',
'99'
);

-- deputado estadual


INSERT INTO candidato(
id_candidato,
nome_candidato,
partido,
cargo,
img_index
)
VALUES(
13111,
'Deputado aleatório',
'TP',
'DEPUTADO(A) ESTADUAL',
'99'
);

INSERT INTO candidato(
id_candidato,
nome_candidato,
partido,
cargo,
img_index
)
VALUES(
45111,
'Deputado aleatório',
'DF',
'DEPUTADO(A) ESTADUAL',
'99'
);

INSERT INTO candidato(
id_candidato,
nome_candidato,
partido,
cargo,
img_index
)
VALUES(
51111,
'Deputado aleatório',
'TT',
'DEPUTADO(A) ESTADUAL',
'99'
);

INSERT INTO candidato(
id_candidato,
nome_candidato,
partido,
cargo,
img_index
)
VALUES(
99999,
'Deputado aleatório',
'XX',
'DEPUTADO(A) ESTADUAL',
'99'
);

-- branco e nulo

INSERT INTO candidato(
id_candidato,
nome_candidato)
VALUES(
'branco',
'BRANCO');

INSERT INTO candidato(
id_candidato,
nome_candidato)
VALUES(
'nulo',
'NULO');