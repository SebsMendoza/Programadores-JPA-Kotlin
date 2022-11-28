package database

import mu.KotlinLogging
import java.io.Closeable
import java.sql.SQLException
import javax.persistence.EntityManager
import javax.persistence.EntityTransaction
import javax.persistence.Persistence

private val log = KotlinLogging.logger { }

object HibernateManager : Closeable {
    private var entityManagerFactory = Persistence.createEntityManagerFactory("default")
    var manager: EntityManager
    private var transaction: EntityTransaction

    init {
        manager = entityManagerFactory.createEntityManager()
        transaction = manager.transaction
    }

    fun open() {
        log.debug { "Iniciando EntityManagerFactory" }
        manager = entityManagerFactory.createEntityManager()
        transaction = manager.transaction
    }

    override fun close() {
        log.debug { "Cerrando EntityManager" }
        manager.close()
    }

    fun query(operations: () -> Unit) {
        open()
        try {
            operations()
        } catch (e: SQLException) {
            log.error { "Error en la consulta: ${e.message}" }
        } finally {
            close()
        }
    }

    fun transaction(operations: () -> Unit) {
        open()
        try {
            log.debug { "Transacción iniciada" }
            transaction.begin()
            operations()
            transaction.commit()
            log.debug { "Transaccion finalizada" }
        } catch (e: SQLException) {
            transaction.rollback()
            log.error { "Error en la transaccion: ${e.message}" }
            throw SQLException(e)
        } finally {
            close()
        }
    }
}