ALTER TABLE alunos ADD ativo TINYINT NOT NULL DEFAULT;
UPDATE instrutores SET ativo = 1;