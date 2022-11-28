package repositories.programador

import models.Programador
import repositories.CrudRepository

interface ProgramadorRepository : CrudRepository<Programador, Long> {
}