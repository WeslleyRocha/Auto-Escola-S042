-- Populating instrucoes table
INSERT INTO instrucoes (aluno_id, instrutor_id, data, ativo, motivo)
VALUES
(1, 1, '2026-04-10 14:00:00', 1, NULL),
(2, 2, '2026-04-11 15:00:00', 0, 'ALUNO_DESISTIU'),
(3, 3, '2026-04-12 16:00:00', 1, NULL),
(4, 4, '2026-04-13 10:00:00', 0, 'INSTRUTOR_CANCELOU'),
(5, 5, '2026-04-14 09:00:00', 1, NULL),
(6, 6, '2026-04-15 11:00:00', 0, 'OUTROS'),
(7, 7, '2026-04-16 13:00:00', 1, NULL),
(1, 2, '2026-04-17 14:00:00', 0, 'ALUNO_DESISTIU'),
(2, 3, '2026-04-18 15:00:00', 1, NULL),
(3, 1, '2026-04-19 16:00:00', 0, 'INSTRUTOR_CANCELOU');
