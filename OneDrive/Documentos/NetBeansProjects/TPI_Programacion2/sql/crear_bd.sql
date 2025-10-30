/* =============================================================== */
/* Script de Datos (Integrante 1) - Dominio: Pedido -> Envio       */
/* =============================================================== */

USE tfi_programacion2_db;

-- 1. Insertar Envios (B) primero
-- Dejamos el ID 1 libre para pruebas de creación desde la app
INSERT INTO envio (id, tracking, empresa, tipo, costo, fechaDespacho, fechaEstimada, estado) 
VALUES (2, 'TRACK456', 'CORREO_ARG', 'ESTANDAR', 800.00, '2025-10-29', '2025-11-05', 'EN_TRANSITO');

INSERT INTO envio (id, tracking, empresa, tipo, costo, fechaDespacho, fechaEstimada, estado) 
VALUES (3, 'TRACK789', 'ANDREANI', 'EXPRES', 1800.00, '2025-10-30', '2025-11-01', 'ENTREGADO');

-- 2. Insertar Pedidos (A) y asociarlos
INSERT INTO pedido (id, numero, fecha, clienteNombre, total, estado, envio_id) 
VALUES (1, 'P-001-A', '2025-10-28', 'Cliente Ejemplo 1', 8500.00, 'ENVIADO', 2);

INSERT INTO pedido (id, numero, fecha, clienteNombre, total, estado, envio_id) 
VALUES (2, 'P-002-B', '2025-10-29', 'Cliente Ejemplo 2', 21000.00, 'ENVIADO', 3);

-- 3. Pedido sin envío (para probar el alta de un envío por separado)
INSERT INTO pedido (id, numero, fecha, clienteNombre, total, estado) 
VALUES (3, 'P-003-C', '2025-10-30', 'Cliente Ejemplo 3', 5000.00, 'NUEVO');