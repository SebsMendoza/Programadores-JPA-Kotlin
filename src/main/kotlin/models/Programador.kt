package models

import javax.persistence.*

@Entity
@Table(name = "programadores")
@NamedQueries(value = [
    NamedQuery(name = "Programador.findAll", query = "select p from Programador p")
])
data class Programador(
    @Id
    val id: Long,
    val nombre: String,
    val salario: Double,
    val tipo: TipoEmpleado,
    @ManyToOne()
    @JoinColumn(name = "departamento_id", referencedColumnName = "id")
    var departamento: Departamento? = null
) {

    enum class TipoEmpleado() {
        JEFE,
        EMPLEADO;
    }
    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , nombre = $nombre , salario = $salario , tipo = $tipo , departamento = ${departamento?.id} )"
    }

}