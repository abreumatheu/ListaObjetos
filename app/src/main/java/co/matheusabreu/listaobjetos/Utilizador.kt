package co.matheusabreu.listaobjetos

class Utilizador(var username: String, var password: String) {
    override fun toString(): String {
        return username
    }
}