package repositories.programador

import database.HibernateManager
import database.HibernateManager.manager
import models.Programador
import mu.KotlinLogging
import java.util.UUID
import javax.persistence.TypedQuery

private val log = KotlinLogging.logger { }

class ProgramadorRepositoryImpl : ProgramadorRepository {
    override fun findAll(): List<Programador> {
        log.debug { "findAll()" }
        var programadores = mutableListOf<Programador>()
        HibernateManager.query {
            val query: TypedQuery<Programador> =
                manager.createNamedQuery("Programador.findAll", Programador::class.java)
            programadores = query.resultList
        }
        return programadores
    }

    override fun findById(id: UUID): Programador? {
        log.debug { "findById($id)" }
        var programador: Programador? = null
        HibernateManager.query {
            programador = manager.find(Programador::class.java, id)
        }
        return programador
    }

    override fun save(entity: Programador): Programador {
        log.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Programador): Boolean {
        var result = false
        log.debug { "delete($entity)" }
        HibernateManager.transaction {
            val programador = manager.find(Programador::class.java, entity.uuid)
            programador?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}