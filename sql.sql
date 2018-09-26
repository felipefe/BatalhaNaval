create database if not exists batalhanaval;

use batalhanaval;

CREATE TABLE jogador (
		  id int(11) NOT NULL AUTO_INCREMENT,
		  nome varchar(30) DEFAULT NULL,
		  tentativas integer(6) DEFAULT NULL,
          acertos integer(6) DEFAULT NULL,
		  PRIMARY KEY (id)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE computador (
		  id int(11) NOT NULL AUTO_INCREMENT,
		  nome varchar(30) DEFAULT NULL,
		  tentativas integer(6) DEFAULT NULL,
          acertos integer(6) DEFAULT NULL,
		  PRIMARY KEY (id)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;
        
        
CREATE TABLE jogo (
		  id int(11) NOT NULL AUTO_INCREMENT,
		  totalTentativas integer(6) DEFAULT NULL,
          totalAcertos integer(50) DEFAULT NULL,
          dataJogo date,
          id_jogador int,
          id_computador int,
		  PRIMARY KEY (id),
          FOREIGN KEY (id_jogador) REFERENCES jogador(id),
          FOREIGN KEY (id_computador) REFERENCES computador(id)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;
        
        select * from jogador;
        
        select * from computador;
        
        select * from jogo
