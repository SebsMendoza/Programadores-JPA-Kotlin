package repositories.departamento

import database.HibernateManager
import database.HibernateManager.manager
import models.Departamento
import mu.KotlinLogging
import javax.persistence.TypedQuery

private val log = KotlinLogging.logger { }

class DepartamentoRepositoryImpl : DepartamentoRepository {
    override fun findAll(): List<Departamento> {
        log.debug { "findAll()" }
        var departamentos = mutableListOf<Departamento>()
        HibernateManager.query {
            val query: TypedQuery<Departamento> =
                manager.createNamedQuery("Departamento.findAll", Departamento::class.java)
            departamentos = query.resultList
        }
        return departamentos
    }

    override fun findById(id: Long): Departamento? {
        log.debug { "findById($id)" }
        var departamento: Departamento? = null
        HibernateManager.query {
            departamento = manager.find(Departamento::class.java, id)
        }
        return departamento
    }

    override fun save(entity: Departamento): Departamento {
        log.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Departamento): Boolean {
        var result = false
        log.debug { "delete($entity)" }
        HibernateManager.transaction {
            val departamento = manager.find(Departamento::class.java, entity.id)
            departamento?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}