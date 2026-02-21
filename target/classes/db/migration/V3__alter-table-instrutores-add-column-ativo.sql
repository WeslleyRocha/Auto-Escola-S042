ALTER TABLE instrutores ADD ativo TINYINT NOT NULL DEFAULT;
UPDATE instrutores SET ativo = 1;