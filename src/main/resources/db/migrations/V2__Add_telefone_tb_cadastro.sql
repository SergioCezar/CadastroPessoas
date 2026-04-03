-- V2: Migration para adicionar a coluna de TELEFONE na tabela de cadastro

ALTER TABLE TB_CADASTRO
ADD COLUMN telefone VARCHAR(20);