package models

import javax.persistence.*

@Entity
@Table(name = "departamentos")
@NamedQueries(
    value = [
        NamedQuery(name = "Departamento.findAll", query = "select d from Departamento d")
    ]
)
data class Departamento(
    @Id
    val id: Long,
    val nombre: String,
    val presupuesto: Double,
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    var programadores: MutableList<Programador> = mutableListOf()
) {
    override fun toString(): String {
        return "Departamento(id=$id, nombre='$nombre', presupuesto=$presupuesto, programadores=${
            programadores.map { it.nombre }
        })"
    }
}