import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.havagas.R
import com.example.havagas.databinding.ActivityMainBinding

class CadastroActivity : AppCompatActivity() {

    private lateinit var nomeCompletoEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var receberEmailCheckBox: CheckBox
    private lateinit var telefoneEditText: EditText
    private lateinit var celularEditText: EditText
    private lateinit var celularCheckBox: CheckBox
    private lateinit var sexoRadioGroup: RadioGroup
    private lateinit var dataNascimentoEditText: EditText
    private lateinit var formacaoSpinner: Spinner
    private lateinit var anoFormacaoEditText: EditText
    private lateinit var anoConclusaoEditText: EditText
    private lateinit var instituicaoEditText: EditText
    private lateinit var tituloMonografiaEditText: EditText
    private lateinit var orientadorEditText: EditText
    private lateinit var vagasInteresseEditText: EditText

    companion object{
        val TAG = "CICLO_PDM"
        val NOME = ""
    }
    private val alb: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onStart(){
        super.onStart()
        Log.v(CadastroActivity.TAG, "Cadastro activity - onStart: Iniciando ciclo de vida VISÍVEL")
    }
    override fun onResume(){
        super.onResume()
        Log.v(CadastroActivity.TAG, "Cadastro activity - onResume Iniciando ciclo de vida EM PRIMEIRO PLANO")
    }
    override fun onRestart(){
        super.onRestart()
        Log.v(CadastroActivity.TAG, "Cadastro activity - onRestart Preparando execução do onStart")
    }
    override fun onPause(){
        super.onPause()
        Log.v(CadastroActivity.TAG, "Cadastro activity - onPause: Finalizando ciclo de vida em primeiro plano")
    }
    override fun onStop(){
        super.onStop()
        Log.v(CadastroActivity.TAG, "Cadastro activity - onStop Finalizando ciclo de vida VISÍVEL")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.v(CadastroActivity.TAG, "Cadastro activity - onDestroy: Finalizando!!")
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {


        supportActionBar?.subtitle = "Login"

        val mainIntent = Intent(this, CadastroActivity::class.java)
        startActivity(mainIntent)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // inicializando as views
        nomeCompletoEditText = findViewById(R.id.nomeCompletoEditText)
        emailEditText = findViewById(R.id.emailEditText)
        receberEmailCheckBox = findViewById(R.id.receberAtualizacoesCheckBox)
        telefoneEditText = findViewById(R.id.telefoneEditText)
        celularEditText = findViewById(R.id.celularEditText)
        celularCheckBox = findViewById(R.id.adicionarCelularButton)
        sexoRadioGroup = findViewById(R.id.sexoRadioGroup)
        dataNascimentoEditText = findViewById(R.id.dataNascimentoEditText)
        formacaoSpinner = findViewById(R.id.formacaoSpinner)
        anoFormacaoEditText = findViewById(R.id.anoFormaturaEditText)
        anoConclusaoEditText = findViewById(R.id.anoConclusaoEditText)
        instituicaoEditText = findViewById(R.id.instituicaoEditText)
        tituloMonografiaEditText = findViewById(R.id.tituloMonografiaEditText)
        orientadorEditText = findViewById(R.id.orientadorEditText)
        vagasInteresseEditText = findViewById(R.id.vagasInteresseEditText)

        // definindo as opções do spinner
        val formacaoOptions = arrayOf(
            "Fundamental",
            "Médio",
            "Graduação",
            "Especialização",
            "Mestrado",
            "Doutorado"
        )

        // criando o adapter para o spinner
        val formacaoAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            formacaoOptions
        )

        // definindo o layout que será utilizado para a lista de opções
        formacaoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // associando o adapter ao spinner
        formacaoSpinner.adapter = formacaoAdapter

        // Configuração de Listener para CheckBox de celular
        celularCheckBox.setOnCheckedChangeListener { _, isChecked ->
            celularEditText.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        // Configuração de Listener para Spinner de formação
        formacaoSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val formacaoSelecionada = parent?.getItemAtPosition(position).toString()
                when (formacaoSelecionada) {
                    "Fundamental", "Médio" -> {
                        anoFormacaoEditText.visibility = View.VISIBLE
                        anoConclusaoEditText.visibility = View.GONE
                        instituicaoEditText.visibility = View.GONE
                        tituloMonografiaEditText.visibility = View.GONE
                        orientadorEditText.visibility = View.GONE
                    }
                    "Graduação", "Especialização" -> {
                        anoFormacaoEditText.visibility = View.GONE
                        anoConclusaoEditText.visibility = View.VISIBLE
                        instituicaoEditText.visibility = View.VISIBLE
                        tituloMonografiaEditText.visibility = View.GONE
                        orientadorEditText.visibility = View.GONE
                    }
                    "Mestrado", "Doutorado" -> {
                        anoFormacaoEditText.visibility = View.GONE
                        anoConclusaoEditText.visibility = View.VISIBLE
                        instituicaoEditText.visibility = View.VISIBLE
                        tituloMonografiaEditText.visibility = View.VISIBLE
                        orientadorEditText.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // definindo a ação do botão salvar
        val nomeCompleto = findViewById<EditText>(R.id.nomeCompletoEditText).text.toString()
        val email = findViewById<EditText>(R.id.emailEditText).text.toString()
        val receberEmails = findViewById<CheckBox>(R.id.receberAtualizacoesCheckBox).isChecked
        val telefone = findViewById<EditText>(R.id.telefoneEditText).text.toString()
        val celular = findViewById<EditText>(R.id.celularEditText).text.toString()
        val celularTipo = findViewById<RadioGroup>(R.id.adicionarCelularButton).checkedRadioButtonId
        val sexo = findViewById<RadioGroup>(R.id.sexoRadioGroup).checkedRadioButtonId
        val dataNascimento = findViewById<EditText>(R.id.dataNascimentoEditText).text.toString()
        val formacao = findViewById<Spinner>(R.id.formacaoSpinner).selectedItem.toString()
        val builder = AlertDialog.Builder(this)
        var mensagem = "Nome completo: $nomeCompleto\n"
        mensagem += "E-mail: $email\n"
        mensagem += "Receber e-mails: ${if (receberEmails) "Sim" else "Não"}\n"
        mensagem += "Telefone: $telefone \n"
        mensagem += "Celular: $celular (${findViewById<RadioButton>(celularTipo).text})\n"

        mensagem += "Sexo: ${findViewById<RadioButton>(sexo).text}\n"
        mensagem += "Data de nascimento: $dataNascimento\n"
        mensagem += "Formação: $formacao\n"
        builder.setMessage(mensagem)
        builder.setPositiveButton("OK") { dialog, which ->
            // Código a ser executado quando o usuário clicar em OK
        }
        val dialog = builder.create()
        dialog.show()


        // definindo a ação do botão limpar
        val limparButton: Button = findViewById(R.id.limparButton)
        limparButton.setOnClickListener {
            limparFormulario()
        }

    }
    private fun limparFormulario() {
        // Limpa os campos de texto
        nomeCompletoEditText.setText("")
        emailEditText.setText("")
        telefoneEditText.setText("")
        celularEditText.setText("")
        dataNascimentoEditText.setText("")
        vagasInteresseEditText.setText("")
        anoFormacaoEditText.setText("")
        anoConclusaoEditText.setText("")
        instituicaoEditText.setText("")
        tituloMonografiaEditText.setText("")
        orientadorEditText.setText("")

        // Desmarca as opções de email e celular
        receberEmailCheckBox.isChecked = false
        celularCheckBox.isChecked = false

    }
}