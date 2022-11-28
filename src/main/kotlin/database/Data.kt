package database

import models.Departamento
import models.Programador

fun getProgramadoresInit() = listOf(
    Programador(
        id = 1L,
        nombre = "Mario",
        salario = 2000.0,
        tipo = Programador.TipoEmpleado.EMPLEADO
    ),
    Programador(
        id = 2L,
        nombre = "Juliana",
        salario = 2000.0,
        tipo = Programador.TipoEmpleado.EMPLEADO
    ),
    Programador(
        id = 3L,
        nombre = "Alfredo",
        salario = 2000.0,
        tipo = Programador.TipoEmpleado.EMPLEADO
    ),
    Programador(
        id = 4L,
        nombre = "Sebastian",
        salario = 4000.0,
        tipo = Programador.TipoEmpleado.JEFE
    )
)

fun getDepartamentosInit() = listOf(
    Departamento(
        id = 1L,
        nombre = "Acceso a datos",
        presupuesto = 150000.0
    )
)