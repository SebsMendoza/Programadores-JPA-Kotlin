package controller

import models.Departamento
import models.Programador
import mu.KotlinLogging
import repositories.departamento.DepartamentoRepository
import repositories.programador.ProgramadorRepository

private val log = KotlinLogging.logger { }

class EmpresaController(
    private val departamentoRepository: DepartamentoRepository,
    private val programadorRepository: ProgramadorRepository
) {
    //Programadores
    fun getProgramadores(): List<Programador> {
        log.info { "Obteniendo programadores" }
        return programadorRepository.findAll()
    }

    fun createProgramador(programador: Programador): Programador {
        log.debug { "Creando programador $programador" }
        programadorRepository.save(programador)
        return programador
    }

    //Departamentos
    fun getDepartamentos(): List<Departamento> {
        log.info { "Obteniendo departamentos" }
        return departamentoRepository.findAll()
    }

    fun createDepartamento(departamento: Departamento): Departamento {
        log.debug { "Creando departamento $departamento" }
        departamentoRepository.save(departamento)
        return departamento
    }
}