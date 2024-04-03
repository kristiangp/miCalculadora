import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder

class CalculadoraViewModel : ViewModel() {
    private var input: String = "0"

    fun getInput(): String {
        return input
    }

    fun agregarNumero(numero: String) {
        if (input == "0") {
            input = numero
        } else {
            input += numero
        }
    }

    fun esOperador(caracter: Char): Boolean {
        return caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/'
    }

    fun agregarCaracter(caracter: String) {
        if (input == "0" && (caracter == "(" || caracter == ")")) {

            input = caracter
        } else {
            input += caracter
        }
    }

    fun agregarOperador(operador: String) {
        if (input == "0") {
            input = operador
        } else {
            if (!esOperador(input.last())) {
                input += operador
            }
        }
    }

    fun borrarUltimoCaracter() {
        if (input.length > 1) {
            input = input.substring(0, input.length - 1)
        } else {
            input = "0"
        }
    }

    fun reiniciarCalculadora() {
        input = "0"
    }

    fun onClickDivision() {
        val ultimoCaracterEsOperador = input.isNotEmpty() && esOperador(input.last())

        if (!ultimoCaracterEsOperador && input != "0") {
            agregarOperador("/")
        }
    }

    fun onClickMultiplicacion() {
        val ultimoCaracterEsOperador = input.isNotEmpty() && esOperador(input.last())

        if (!ultimoCaracterEsOperador && input != "0") {
            agregarOperador("*")
        }
    }

    fun onClickSuma() {
        val ultimoCaracterEsOperador = input.isNotEmpty() && esOperador(input.last())

        if (!ultimoCaracterEsOperador && input != "0") {
            agregarOperador("+")
        }
    }

    fun onClickResta() {
        val ultimoCaracterEsOperador = input.isNotEmpty() && esOperador(input.last())

        if (!ultimoCaracterEsOperador && input != "0") {
            agregarOperador("-")
        }
    }

    fun calcularResultado(expresion: String): String {
        return try {
            if (expresion.contains('(') && expresion.contains(')')) {
                val expresionConParentesis = ExpressionBuilder(expresion).build()
                val resultado = expresionConParentesis.evaluate().toString()
                resultado
            } else {
                val expresionBuilder = ExpressionBuilder(expresion)
                val resultado = expresionBuilder.build().evaluate().toString()
                resultado
            }
        } catch (e: Exception){
            "Error al calcular el resultado"
        }
    }

}
