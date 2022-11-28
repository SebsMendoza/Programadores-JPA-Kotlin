import controller.EmpresaController
import database.HibernateManager
import database.getDepartamentosInit
import database.getProgramadoresInit
import models.Programador
import mu.KotlinLogging
import repositories.departamento.DepartamentoRepositoryImpl
import repositories.programador.ProgramadorRepositoryImpl

private val log = KotlinLogging.logger { }
fun main() {
    log.info { "Gestor de BBDD" }
    initDataBase()

    val controller = EmpresaController(DepartamentoRepositoryImpl(), ProgramadorRepositoryImpl())

    val programadoresInit = getProgramadoresInit()
    programadoresInit.forEach { programador ->
        controller.createProgramador(programador)
    }

    val progs = controller.getProgramadores()
    progs.forEach { println(it) }

    val departamentos = getDepartamentosInit()
    departamentos[0].programadores = progs as MutableList<Programador>

    departamentos.forEach { programador ->
        controller.createDepartamento(programador)
    }

    val deps = controller.getDepartamentos()
    deps.forEach { println(it) }

}

fun initDataBase() {
    HibernateManager.open()
    HibernateManager.close()
}