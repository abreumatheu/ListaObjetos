import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import co.matheusabreu.listaobjetos.Utilizador
import co.matheusabreu.listaobjetos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var pos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listaUtilizadores = ArrayList<Utilizador>()
        listaUtilizadores.add(Utilizador("user", "pass"))
        listaUtilizadores.add(Utilizador("admin", "pwd123"))
        listaUtilizadores.add(Utilizador("aaa", "bbb"))

        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, listaUtilizadores)
        binding.listViewUtilizadores.adapter = adapter

        binding.listViewUtilizadores.setOnItemClickListener { _, _, position, _ ->
            // Mover estas linhas para dentro do bloco onItemClick
            binding.editUsername.setText(listaUtilizadores[position].username)
            binding.editPassword.setText(listaUtilizadores[position].password)
            pos = position
        }

        binding.buttonInserir.setOnClickListener {
            val username = binding.editUsername.text.toString().trim()
            val password = binding.editPassword.text.toString().trim()

            if(username.isEmpty()&& !password.isEmpty()){
                listaUtilizadores.add(Utilizador(username,password))
                adapter.notifyDataSetChanged()
                binding.editUsername.setText("")
                binding.editPassword.setText("")
                pos = -1
            }
        }
        binding.buttonEditar.setOnClickListener {
            if (pos>=0) {
                val username = binding.editUsername.text.toString().trim()
                val password = binding.editPassword.text.toString().trim()
                listaUtilizadores.get(pos).username = username
                listaUtilizadores.get(pos).password = password
                if(username.isEmpty()&& !password.isEmpty()){
                adapter.notifyDataSetChanged()
                binding.editUsername.setText("")
                binding.editPassword.setText("")
                pos = -1
                }
            }
        }
        binding.buttonEliminar.setOnClickListener {
            if (pos>=0) {
            listaUtilizadores.removeAt(pos)
            adapter.notifyDataSetChanged()
                binding.editUsername.setText("")
                binding.editPassword.setText("")
                pos = -1

            }
        }

        binding.buttonLimpar.setOnClickListener {
            listaUtilizadores.clear()
            adapter.notifyDataSetChanged()
            binding.editUsername.setText("")
            binding.editPassword.setText("")
            pos = -1
        }
    }
}
