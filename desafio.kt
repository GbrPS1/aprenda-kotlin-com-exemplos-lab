enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(val nome: String, val email: String)

data class ConteudoEducacional(
    var nome: String,
    val duracao: Int = 60,
    val categoria: String,
    val autor: String,
    val dataCriacao: String
)

data class Formacao(
    val nome: String,
    val dataInicio: String,
    val dataTermino: String,
    val nivel: Nivel,
    var conteudos: List<ConteudoEducacional>
) {
    data class Inscricao(val usuario: Usuario, val conteudo: ConteudoEducacional)

    val inscritos = mutableListOf<Inscricao>()

    fun matricular(usuario: Usuario, conteudo: ConteudoEducacional) {
        inscritos.add(Inscricao(usuario, conteudo))
    }

    fun cancelarMatricula(usuario: Usuario, conteudo: ConteudoEducacional) {
        println("Cancelando a matricula:")
        inscritos.removeIf { it.usuario == usuario && it.conteudo == conteudo }
    }

    fun listarInscritos() {
        println("Inscritos na formação $nome ($nivel):")
        inscritos.forEach {
            println("${it.usuario.nome} - ${it.conteudo.nome}")
        }
    }
}

fun main() {
    val usuario1 = Usuario("Gabriel", "Gabriel@email.com")
    val usuario2 = Usuario("Carol", "Carol@email.com")

    val conteudo1 = ConteudoEducacional("Introdução ao Kotlin", 90, "Programação", "Autor1", "2024-01-19")
    val conteudo2 = ConteudoEducacional("Classes em Kotlin", 60, "Programação", "Autor2", "2024-01-20")

    val formacaoKotlin = Formacao(
        "Formação Kotlin",
        "2024-01-15",
        "2024-02-15",
        Nivel.INTERMEDIARIO,
        listOf(conteudo1, conteudo2)
    )

    formacaoKotlin.matricular(usuario1, conteudo1)
    formacaoKotlin.matricular(usuario2, conteudo2)

    formacaoKotlin.listarInscritos()

    formacaoKotlin.cancelarMatricula(usuario1, conteudo1)

    formacaoKotlin.listarInscritos()
}