package repositories.departamento

import models.Departamento
import repositories.CrudRepository

interface DepartamentoRepository : CrudRepository<Departamento, Long> {
}