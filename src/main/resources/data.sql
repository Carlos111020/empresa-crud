INSERT INTO departamentos (id, descripcion, nombre) VALUES
    (1, 'Departamento encargado de la innovación y desarrollo de tecnologías de la información.', 'Departamento de Tecnologia'),
    (3, 'Departamento encargado de recursos humanos', 'Departamento de recursos humanos')
    ON CONFLICT (id) DO NOTHING;

INSERT INTO empleados (id, apellido, correo, nombre, salario, departamento_id) VALUES
    (1, 'Pérez', 'juan.perez@example.com', 'Juan', 5000000, 1)
    ON CONFLICT (id) DO NOTHING;

SELECT setval('departamentos_id_seq', GREATEST((SELECT COALESCE(MAX(id), 1) FROM departamentos), 3), true);
SELECT setval('empleados_id_seq', GREATEST((SELECT COALESCE(MAX(id), 1) FROM empleados), 1), true);